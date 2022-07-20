# ESP-idf官方示例
Author: Yujiang Peng
Date: Oct 2, 2020

## 1. Hello world
1. hello_world_main
芯片信息，重启。
1. blink.c
引脚的简单定义方法与使用。

## 2. peripherals

### 1. ADC

### 2. GPIO
3. generic_gpio
GPIO引脚结构体定义与使用方法；FreeRTOS任务与队列。
    - 包含库文件
        ```c
        #include "driver/gpio.h"
        ```

    - 定义引脚
        ```c
        #define GPIO_FUNC_0 23  //定义引脚，GPIO_FUNC_0为实现某功能的引脚名
        // 配置GPIO_OUT位寄存器
        #define GPIO_OUTPUT_PIN_SEL  ((1ULL<<GPIO_FUNC_0) | (1ULL<<GPIO_FUNC_1)
        ```

    - 配置引脚
        ```c
        gpio_reset_pin(GPIO_FUNC_0); //重置引脚，配置引脚IOMUX register
        gpio_set_direction(GPIO_FUNC_0, GPIO_MODE_OUTPUT); //定义引脚为push/pull output
        //设置引脚电平
        gpio_set_level(GPIO_FUNC_0, 1); //0为低电平；1为高电平
        ```

    - 结构体方法定义GPIO
        ```c
        // 结构体方法定义GPIO
        #define GPIO_OUTPUT_IO_0    18
        #define GPIO_OUTPUT_IO_1    19
        // 配置GPIO_OUT位寄存器
        #define GPIO_OUTPUT_PIN_SEL  ((1ULL<<GPIO_OUTPUT_IO_0) | (1ULL<<GPIO_OUTPUT_IO_1))
        #define GPIO_INPUT_IO_0     4
        #define GPIO_INPUT_IO_1     5
        #define GPIO_INPUT_PIN_SEL  ((1ULL<<GPIO_INPUT_IO_0) | (1ULL<<GPIO_INPUT_IO_1))

        //这部分为结构体方法定义GPIO
        gpio_config_t io_conf; //定义一个gpio_config类型的结构体，下面的都算对其进行的配置
        io_conf.intr_type = GPIO_INTR_DISABLE;  //禁止中断
        io_conf.mode = GPIO_MODE_OUTPUT;        //选择输出模式
        io_conf.pin_bit_mask = GPIO_OUTPUT_PIN_SEL; //配置GPIO_OUT寄存器；GPIO18/19
        io_conf.pull_down_en = 0;               //禁止下拉
        io_conf.pull_up_en = 0;                 //禁止上拉
        gpio_config(&io_conf);                  //配置使能

        io_conf.intr_type = GPIO_INTR_POSEDGE;  //上升沿中断
        io_conf.pin_bit_mask = GPIO_INPUT_PIN_SEL;  //配置GPIO_OUT寄存器；GPIO4/5
        io_conf.mode = GPIO_MODE_INPUT;         //设置为输入模式
        io_conf.pull_up_en = 1;                 //启用上拉模式
        gpio_config(&io_conf);                  //配置使能
        ```

    - GPIO中断
        ```c
        //设置一个引脚的gpio中断模式
        gpio_set_intr_type(GPIO_INPUT_IO_0, GPIO_INTR_ANYEDGE);
        ```

4. matrix_keyboard_example_main.c
GPIO引脚集体定义与使用，程序有错误。

### 3. I2C
5. i2c_example_main.c
i2c主从通信、读写从机的i2c端口，读取光线传感器BH1750数据。

### 4. I2S
6. i2s_example_main.c
一种串行同步通信协议，通常用于在两个数字音频设备之间传输音频数据。
7. i2s_adc_dac.c
闪存分区、清除与写入，数/模和模/数转换，数值缩放。

### 5. LED control
8. ledc_example_main
输出PWM信号，高速/低速通道各8路，无需CPU干预可自动改变占空比，低速通道在sleep状态时依然可以运行。

### 6. MCPWM
ESP32有两组MCPEWM单元用来控制不同类型的电机，每组有三对PWM输出。
9. mcpwm_basic_config_example
10. mcpwm_bldc_control_hall_sensor_example
11. mcpwm_brushed_dc_control_example
12. mcpwm_servo_control_example //伺服电机

### 7. Pulse counter
输入信号上升沿或者下降沿的计数；可由接入控制信号。
13. pcnt_event_example_main
FreeRTOS任务调度，不同计数功能。
14. rotary_encoder_example_main.c
旋转编码器

### 8. Remote control

### 9. SDMMC Host Driver

### 10. SD SPI Host Driver

### 11. SDIO Card Slave Driver

### 12. Sigma-delta Modulation

### 13. SPI Master

### 14. SPI Slave

### 15. Secure element

### 16. Touch sensor

### 17. TWAI (CAN总线)
Tx, Rx发送及接收信号

### 18. UART