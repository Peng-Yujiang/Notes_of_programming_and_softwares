# ***FreeRTOS on ESP32***
Author: Yujiang Peng
Date: Oct 2, 2020
## **程序写入、运行及监视**
**1. 开发环境配置**
- 配置
    ```cmd
    cd %userprofile%\esp\hello_world
    idf.py set-target esp32
    idf.py menuconfig
    ```

- 安装CP2102的驱动，以便电脑可以识别ESP32开发板

- Generating x509_crt_bundle错误解决

    >Component config--> mbedTLS--> Certificate Bundle--> 取消Enable trusted root certificate bundle

- “ninja: error: loading ‘build.ninja’: 系统找不到指定的文件。 ”报错 看[这里](https://www.csdn.net/tags/MtTaAgysMzM1NTcxLWJsb2cO0O0O.html)

- 在vs code中安装Remote WSL插件后使用默认终端为wsl，可利用WSL来编译。但是需要每次使用都需要添加环境变量，否则回出现`idf.py: command not find`错误。

    >`. /home/pyj/esp/esp-idf/export.sh`
- 永久实现上一条功能
    - 查看所有文件
    >ls -a
    - 打开下列文件
    >nano .bashrc
    - 最底部添加下列语句
    >. ~/esp/esp-idf/export.sh

- 拒绝访问
    >拔下数据线，重新连接并写入

**2. 编译工程**
```cmd
idf.py build
```
**3. 烧录到设备**
```cmd
idf.py -p COM3 flash
```
出现错误`FAILED: CMakeFiles/flash`，可用下列命令改波特率
```cmd
idf.py flash -b 1152000
```
**4. 监视器**
```cmd
idf.py -p COM3 monitor //COM4为连接的端口
idf.py -p COM3 flash monitor //一次性执行构建、烧录和监视过程

idf.py monitor -p /dev/ttS3 //指定监视器的端口
```
ctrl+] //推出IDF监视器

**5. 新建工程**
```cmd
idf.py create-project
```

## **FreeRTOS**
**1. 核心功能**
内核管理 --任务管理--任务控制块
        --时间管理--时钟节拍产生
                 --任务延时管理
        --内存管理--heap_1/2/3/4
        --通信管理--消息管理--消息队列
                 --任务同步--信号量
                          --互斥量

**2. 内存管理**
| C文件 | 优点 | 缺点 |
| --- | :---: | :---: |
|heap1.c|分配简单、时间确定|只分配、不回收|
|heap2.c|动态分配、最佳匹配|碎片、时间不定|
|heap3.c|调用标准库函数|速度慢、时间不定|
|heap4.c|相邻空闲内存可合并|碎片、合并效率低|

**3. 任务管理**
>简要的执行过程
>1. 定义引脚中断服务的句柄函数`gpio_isr_handler(void* arg)` (主函数前)
>1. 定义任务函数`task_func`；其中包含读取队列任务（xQueueReceive）的语句
>1. 设置引脚的中断模式`gpio_set_intr_type(Pin, GPIO_INTR_ANYEDGE)`
>1. 注册引脚的中断服务`gpio_install_isr_service(ESP_INTR_FLAG_DEFAULT)`
>1. 设置引脚中断服务句柄`gpio_isr_handler_add(GPIO_num(引脚编号), gpio_isr_handler(中断句柄), (void*) GPIO_INPUT_IO_0(句柄参数))`
>1. 创建一个任务队列`xQueueCreate(10, sizeof(uint32_t))`
>1. 创建任务`xTaskCreate(task_func, 'Task 1', 2048, NULL, 10, NULL`, 包含该任务所执行的函数名
>

1. 创建一个任务
- 队列服务
    - 定义任务队列
        ```c
        static xQueueHandle gpio_evt_queue = NULL; //定义一个队列返回变量
        ```
    - 创建队列
        ```c
        //创建一个队列，从中获取队列句柄
        gpio_evt_queue = xQueueCreate(10, sizeof(uint32_t));//任务数；大小
        ```
    - 

- 中断服务
    - 配置引脚中断模式
        ```c
        //设置一个引脚的gpio中断模式
        gpio_set_intr_type(GPIO_INPUT_IO_0, GPIO_INTR_ANYEDGE);
        ```
    - 定义引脚中断句柄
        ```c
        static void IRAM_ATTR gpio_isr_handler(void* arg)
        {
            uint32_t gpio_num = (uint32_t) arg; //把中断消息插入到队列的后面
            xQueueSendFromISR(gpio_evt_queue, &gpio_num, NULL); //将gpio的io参数传递到队列中
        }
        ```
    - 定义引脚任务函数
        ```c
        static void gpio_task_example(void* arg)
        {
            uint32_t io_num;
            for(;;) {
                //不断读取gpio队列，读取完后将删除队列
                if(xQueueReceive(gpio_evt_queue, &io_num, portMAX_DELAY)) {
                    printf("GPIO[%d] intr, val: %d\n", io_num, gpio_get_level(io_num));
                }
            }
        }
        ```



- 创建任务
    ```c
    void vTask1( void * pvParameters )
    {
            const char *pcTaskName = "Task 1 is running\r\n";
            volatile uint32_t ul; //确保ul不会被优化掉
            for( ;; ) //无限循环
            {
                    vPrintString( pcTaskName );
                    //延时
                    for( ul = 0; ul < mainDELAY_LOOP_COUNT; ul++ )
                    {

                    }
            }
    }
    //同样的方法创建vTask2
    ```

- 在主函数中创建任务
    ```c
    int main( void )
    {
            // 创建任务；实际应用中应检查xTaskCreate()调用的返回值，以确保任务成功创建
            xTaskCreate( vTask1,    //指向执行任务的函数
                        "Task 1",  //任务的名称或说明，只在调试中有效
                        1000,      //堆栈深度
                        NULL,      //传递给任务函数的参数
                        1,         //优先级
                        NULL );    //任务句柄
            XTaskCreate( vTask2, "Task2", 1000, NULL, 1, NULL );

            //启动调度程序以便开始执行任务
            vTaskStartScheduler();

            //
    }
    ```


2. 优先级
- 优先级0为最低优先级（可在FreeRTOSConfig.h文件中设置configMAX_priorities来配置最高优先级）
- 不同任务可公用一个优先级

3. 关于vTaskDelay
在有两个任务的情况下
不用delay：每执行完一个任务，需要等到Tick中断，才能让调度器选择运行下一个任务。
使用delay：两个任务能够在一个周期内依次运行

4. 任务在不同的状态间调度

5. vTaskDelay和vTaskDelayUntil的区别
前者是相对的延时
后者是绝对的延时，适合需要固定频率执行的任务。

6. 对于空闲任务来说，它一直处于就绪态，只有当其它优先级比它高的任务都执行完了，都在阻塞态里，空闲任务idle task才会执行。

**4. （任务间）通信管理**
1. 对列的原理
队列，可以容纳有限数量固定大小的数据。一般采用FIFO存储方式（First in First Out）。而在freertos中，队列的传输用的是copy的方式。
采用copy传递的优势有：堆栈中的变量也可以直接发送到队列中，而不用担心是否会被释放掉。因次，发送和接收任务也不用去关心是哪一个任务“拥有”数据。

2. 创建一个队列
    ```c
    QueueHandle_t xQueueCreate( UBaseType_t exQueueLength, UBaseType_t uxItemSize );
    ```

3. 接收队列
    ```c
    BaseType_t xQueueReceive( QueueHandle_t xQueue,
                            void * const pvBuffer,
                            TickType_t xTicksToWait );
    ```

4. 利用指针传输特大变量

**5. 其它功能**
1. 软件时钟管理

2. 事件管理

3. 解决互斥问题的内存管理反法等

**6. 数据类型**
命名规则在portmarco.h文件里