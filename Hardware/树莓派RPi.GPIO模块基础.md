# 树莓派RPi.GPIO模块基础
Author: Yujiang Peng
Date: March 10, 2021


## 1. 导入模块
```python
import RPi.GPIO as GPIO
    # to import the module and check to see if it is successful
    try:
        import RPi.GPIO as GPIO
    except RuntimeError:
        print('Error importing RPi.GPIO!')
```

## 2. 引脚编号
```python
GPIO.setmode(GPIO.BOSRD) # pin numbers
    #or
GPIO.setmode(GPIO.BCM) # BCM number
mode = GPIO.getmode() # to specify which numbering is using
```

## 3. 警告
```python
GPIO.setwarnings(False) # to disable warnings 
```

## 4. 设置通道
### 4.1. 设置一个通道
```python
# channel is the channel number based on the numbering system you
    # have specified (BOARD or BCM))
GPIO.setup(channel, GPIO.IN) # to configure a channel as an input
GPIO.setup(channel, GPIO.OUT) # to configure a channel as an output
# specify a initial value
GPIO.setup(channel, GPIO.OUT, initial = GPIO.HIGH)
```

### 4.2. 设置多个通道
```python
chan_list [11, 15] # you can tuples instead, i.e.:
    # chan_list = (11, 15)
GPIO.setup(chan_list, GPIO.OUT)
```

## 5. 输入
```python
GPIO.input(channel) # to read the value of a GPIO pin
# this will return either 0 / GPIO.LOW / False or 1 / GPIO.HIGH / True
```

### 5.1. 上拉/下拉电阻
```python
# a 10K resistor between the input channel and 3.3V (pull-up)
    # or 0V (pull-down) is commonly used
GPIO.setup(channel, GPIO.IN, pull_up_down = GPIO.PUD_UP)
    #or
GPIO.setup(channel, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)
```

### 5.2. 测试输入（轮询）
```python
# take a snapshot of an input at a moment in time
if GPIO.input(channel):
    print('Input was HIGH')
else:
    print('Input was LOW')
# to wait for a button press by polling in a loop
while GPIO.input(channel) == GPIO.LOW:
    time.sleep(0.01) # wait 10 ms to give CPU chance to do other things
```

### 5.3. 中断与边沿检测
An edge is the change in state of an electrical signal from LOW to HIGH (rising edge) or from HIGH to LOW (falling edge).
there are two ways to address events, such as a button press (an event)
1. the wait_for_edge() function
2. the event_detected() function
3. a threaded callback function that is run when a edge is detected

#### 5.3.1. wait_for_edge()函数
```python
# to block excution of you program until an edge is detected
GPIO.wait_for_edge(cahnnel, GPIO.RISING) # wait for a button press
    # you can detect edges of type GPIO.RISING, GPIO.FALLING or GPIO.BOTH
# wait for a certain length of time, you can use the timeout parameter
# wait for up to 5 seconds for a rising edge (timeout is in milliseconds)
channel = GPIO.wait_for_edge(channel, GPIO_RISING, timeout=5000)
if channel is None:
    print('Timeout occurred')
else:
    print('Edge detected on channel', channel)
```

#### 5.3.2. evet_detected()函数
```python
GPIO.add_event_detect(channel, GPIO.RISING) # GPIO.FALLING or GPIO.BOTH
    # add rising edge detection on a channel
do_something()
if GPIO.event_detected(channel):
    print('Button pressed')
```

### 5.3.3. Threaded回调
```python
# RPi.GPIO runs a second thread for callback functions. This means that
    # callback functions can be run at the same time as your main program,
    # in immediate response to an edge. i.e.:
def my_callback(channel):
    print('This is a edge event callback function!')
    print('Edge detected on channel %s'%channel)
    print('This is run in a different thread to your main program')
GPIO.add_event_detect(channel, GPIO.RISING, callback=my_callback)
    # add rising edge detection on a channel
...the rest of your program...
# more than one callback function
def my_callback_one(channel): # run sequentially
    print('Callback one')
def my_callback_two(channel): # in the order in which they have been defined
    print('Callback two')
GPIO.add_event_detect(channel, GPIO.RISING)
GPIO.add_event_callback(channel, my_callback_one)
GPIO.add_event_callback(channel, my_callback_two)
```

### 5.4. 开关抖动
two ways of dealing with switch bounce:
1. add a 0.1 μF capacitor across your switch
2. software debouncing
3. a combination of both
```python
# To debounce using software, add the bouncetime= parameter to a function
    # where you specify a callback function. Bouncetime should be specified
    # in milliseconds. For example:
# add rising edge detection on a channel, ignoring further edges for 200ms
    # for switch bounce handling
GPIO.add_event_detect(channel, GPIO.RISING, callback=my_callback, bouncetime=200)
    #or
GPIO.add_event_callback(channel, my_callback, bouncetime=200)
```

### 5.5. 删除事件检测
```python
# If for some reason, your program no longer wishes to detect edge events,
    # it is possible to stop them:
GPIO.remove_event_detect(channel)
```

## 6. 输出
```python
GPIO.output(channel, state) # to set the output state of a GPIO pin
```

### 6.1. 首先设置RPi.GPIO
```python
import RPi.GPIO as GPIO
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.setup(15, GPIO.OUT)
```
### 6.2. 设置输出高电平
```python
GPIO.output(15, GPIO.HIGH) # 1 or rue
```

### 6.3. 设置输出低电平
```python
GPIO.output(15, GPIO.LOW) # 0 or False
```

### 6.4. 同时输出多个通道
```python
chan_list = (11,15) # chan_list = [11, 15]
GPIO.output(chan_list, GPIO.LOW) # all LOW
GPIO.output(chan_list, (GPIO.HIGH,GPIO.LOW))  # first HIGH, second LOW
```
Note that you can read the current state of a channel set up as an output using the input() function. For example to toggle an output:
```python
GPIO.output(15, not GPIO.input(15))
```

## 7. 清零
only clean up GPIO channels that your script has used, also clears the pin numbering system in use.

    GPIO.cleanup() # to clean up at the end of the script
clean up individual channels, a list or a tuple of channels
```python
GPIO.cleanup(channel)
GPIO.cleanup((channel1, channel2))
GPIO.cleanup([channel1, channel2])
```

## 8. 树莓派开发板信息及RPi.GPIO版本
```python
GPIO.RPI_INFO # to discover information about the RPi
GPIO.RPI_INFO['P1_REVISION'] # to discover the Raspberry Pi board revision
GPIO.VERSION # to discover the version of RPi.GPIO
```

## 9. GPIO功能
Shows the function of a GPIO channel. For example:
```python
import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BOARD)
func = GPIO.gpio_function(pin)
# will return a value from:
    # GPIO.IN, GPIO.OUT, GPIO.SPI, GPIO.I2C, GPIO.HARD_PWM,
    # GPIO.SERIAL, GPIO.UNKNOWN
```

## 10. 使用RPi.GPIO中的PWN
创建一个PWM实例：

    p = GPIO.PWM(channel, frequency)
开始PWM：

    p.start(dc)   # where dc is the duty cycle (0.0 <= dc <= 100.0)
改变输出频率：

    p.ChangeFrequency(freq)   # where freq is the new frequency in Hz
改变占空比：

    p.ChangeDutyCycle(dc)  # where 0.0 <= dc <= 100.0, 占空比
停止PWM：

    p.stop()
Note that PWM will also stop if the instance variable 'p' goes out of scope.

### 10.1. LED灯每两秒闪烁一次的示例程序：
```python
import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BOARD)
GPIO.setup(15, GPIO.OUT)

p = GPIO.PWM(15, 0.5)
p.start(1)
input('Press return to stop:')   # use raw_input for Python 2
p.stop()
GPIO.cleanup()
```

### 10.2. 改变LED灯亮度的示例程序：
```python
import time
import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BOARD)
GPIO.setup(15, GPIO.OUT)

p = GPIO.PWM(15, 50)  # channel=15 frequency=50Hz
p.start(0)
try:
    while 1:
        for dc in range(0, 101, 5): # brighten
            p.ChangeDutyCycle(dc)
            time.sleep(0.1)
        for dc in range(100, -1, -5): # dim
            p.ChangeDutyCycle(dc)
            time.sleep(0.1)
except KeyboardInterrupt:
    pass
p.stop()
GPIO.cleanup()
```