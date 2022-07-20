# Arduino
## 1. 串口通讯（UART/USART）
引脚的串口通讯TX/RX使用TTL逻辑电平(5V or 3.3V)。
若要利用串口与外部TTL串口装置通讯，连接TX引脚到外部的RX引脚，RX到外部TX引脚，两者地线相连。

**串口通讯函数**
```c
//Serial为串口对象
if (Serial); //判断串口是否准备好
        //while (!Serial){}
Serial.available(); //返回可读的字节数
        //判断是否接收到数据
Serial.availableForWrite(); //可写的字节数

Serial.begin(speed, config); //设置部分；串口波特率9600、115200
        //config为可选，默认为8位、无奇偶校验、1停止位
Serial.end(); //关闭串口通讯，使RX/TX引脚可被用于通用输入与输出

Serial.find(target, length); //从串口缓存读取数据，直到发现目标；返回true/false
        //target:搜索的字符串，char数据类型；length：可选，字符串长度，size_t数据类型
Serial.findUntil(target, terminal); //从串口缓存读取数据，直到发现目标字符串，或者发现终止字符串
Serial.flush(); //等待外传的串口数据的传输完成
Serial.perseInt(); //在进入的串口数据中查找下一个有效整数；具体可查官网
Serial.peek(); //返回进入的串口数据的下一个字节（character），而不将其从内部串口缓存中移除

Serial.print(string, format); //格式参数可选，格式有不同进制BIN, OCT, DEC, HEX
        //对于float类型，则数字表示小数点后位数
Serial.println(value, format); //一个carriage return character (\r)和一个newline character (\n)

Serial.read(); //读取进入串口的数据；返回进入串口可获取的的第一个字节，int类型
Serial.readBytes(buffer, length); //从串口进入缓存的字符；读取完指定长度后终止
Serial.readBytesUntil(character, buffer, length); //返回读入缓存中的字节数
Serial.readString(); //从串口缓存中读取字符到字符串
Serial.readStringUntil(terminator); //从串口缓存中读取指定字符串

Serial.setTimeOut(time); //串口数据的等待时间；默认1000毫秒
Serial.write(val/str);
Serial.write(buf, len); //写入二进制数据到串口
serialEvent(); //called when data is available
```

**数据流**
Serial、Wire、Ethernet、SD等库均依赖于数据流Stream。有以下函数：
```c
available()
read()
flush()
find()
findUntil()
peek()
readBytes()
readBytesUntil()
readString()
readStringUntil()
parseInt()
parseFloat()
setTimeout()
```

**实用工具**
- PROGMEM
-- 将数据保存到flash (program)而不是SRAM
-- 该关键字是变量修饰符，仅可用于*pgmspace.h*中的数据类型，且包含于该头文件
-- 使用时分两步：在将数据存入Flash中后，需要某些方法（函数，也包含于*pgmspace.h*中）来从程序内存中读取数据并保存到SRAM中。
```c
const dateTyep variableName[] PROGMEM = {data0, data1, data2...}
```


## 2. 函数
- 数字I/O
```c
pinMode(pin, OUTPUT); //设置引脚输入、输出、上拉输出模式(INPUT/OUTPUT, INPUT_PULLUP)
digitalWrite(pin, HIGH); //数字引脚输出电平(HIGH/LOW)
digitalRead(pin); //读取引脚状态(HIGH/LOW)
```

- 模拟I/O
```c
analogRead(pin); //读取模拟引脚状态(一般为0~1023，取决于ADC位数)
analogWrite(pin, voltage); //模拟引脚输出电压（通过调节数字引脚输出的PWM的占空比实现，0~255）
```

- 高级I/O
```c
tone(pin, frequency, duration); //输出方波信号; 后二者分别为unsigned int和unsigned long型
noTone(pin); //停止输出方波信号
pulseIn(pin, value, timeout); //等待读取引脚的脉冲并返回脉冲持续时间；value可为HIGH/LOW，或int型；timeout可设置等待的毫秒时间unsigned long
pulseInlong(pin, value, timeout); //上一个的长时间版本
byte incoming = shiftIn(dataPin, clockPin, bitOrder); //一次移入一个bit的数据；MSBFIRST/LSBFIRST
shiftOut(dataPin, clockPin, bitOrder, value); //value为移出的值
```

- 外部中断
```c
interruptNum = digitalPinToInterrupt(pin); //将数字引脚转换为具体的中断数
        //delay(), millis(),micros()均不能正常工作，delayMicroseconds()可以
```
```c
attachInterrupt(digitalPinToInterrupt(pin), ISR, mode); //ISR: interrupt service routines
        //mode有LOW, CHANGE, RISING, FALLING; 某些支持HIGH
detachInterrupt(digitalPinToInterrupt(pin)); //关闭中断
```

- 中断
```c
interrupts(); //重新开启中断，在由noInterrupts()关闭中断后
noInterrupts(); //关闭中断
```

- 时间
```c
delay(100); //单位为毫秒(ms)
delayMicroseconds(); //单位为微秒(us)
micros();
millis();
``` 

- 数学
```c
abs(value);
constain(value, low, high);
map(value, fromLow, fromHigh, toLow, toHigh);
max(value1, value2);
min(value1, value2);
pow(base, exponent);
sq(value); //平方
sqrt(value); //平方根
```

- 三角函数
```c
cos();
sin();
tan();
```

- 随机数
```c
random(min, max); //min为可选参数；上下界
randomSeed(seed); //unsigned long
```

- 字符
```c
isAlpha(thisChar);
isAlphaNumeric(thisChar); //字母或者数字
isAscii(thisChar);
isControl(thisChar); //控制字符
isDigit(); //是否为数字
isGraph(); //可打印且有内容
isHexadecimalDigit(); //十六进制字符
isLowerCase();
isPrintable();
isPunct(); //标点符号
isSpace();
isUpperCase();
isWhitespace(); //空格或者\t
```

- 位与字节
```c
bit(n); //特定位所对应的值
bitClear(value, n); //清除值中第n位
bitRead(value, n);
bitSet(value, n);
bitWrite(value, n, b); //b为要写入到值的第n位的值
highBite(); //获取高字节
lowByte();
```

## 3. 常用库

**LiquidCrystal**
```c
# include <LiquidCrystal.h>
//此处lcd为一个LiquidCrystall类型的变量
LiquidCrystal lcd(rs, enable, d0, d1, d2, d3, d4, d5, d6, d7); //rs：连接到LCD上RS引脚的引脚
        //rw:连接到RW的引脚，为可选参数
        //enable:连接到enable的引脚
        //d0到d3可省略
lcd.begin(cols, rows); //显示的行列数
lcd.clear();
lcd.home(); //将光标移动到左上角
lcd.setCursor(col, row); //设置光标位置
lcd.write(data); //将字符写到LCD
lcd.print(data, BASE); //将文字打印到LCD；BASE为可选参数，有BIN,DEC,OCT,HEX
lcd.cursor(); //显示光标
lcd.noCursor(); //影藏光标
lcd.blink(); //显示闪烁光标
lcd.noBlink();
lcd.display(); //打开LCD显示屏
lcd.noDisplay();
lcd.scrollDisplayLeft(); //屏幕内容向左滚动一格
lcd.scrollDisplayRight();
lcd.autoscroll();
lcd.noAutoscroll();
lcd.leftToRight(); //设置文字显示为从左向右
lcd.rightToLeft();
lcd.createChar(num, data); //自定义文字显示
```

**SD**
```c
#include <SPI.h>
#include <SD.h>

//SD 类
SD.begin(); //cspin为可选参数，片选引脚
SD.exists(filename);
SD.mkdir(filename); //需要创建的文件夹名；参数如"a/b/c"
SD.open(filepath, mode); //模式为可选参数，FILE_READ/FILE_WRITE
SD.remove(filename);
SD.rmdir(filename); //需要删除的文件夹名

//文件类
//此处file为File类的一个实例
file.name()
file.availabel()
file.close()
file.flush()
file.peek()
file.position()
file.print()
file.println()
file.seek()
file.size()
file.read()
file.write()
file.isDirectory()
file.openNextFile()
file.rewindDirectory()
```

**WiFi**
- WiFi类
```c
#include <SPI.h>
#include <WiFi.h>
IPAddress ip(192.168.2.2) //设置IP
//WiFi类
WiFi.begin(); //自动初始化WiFi库的网络设置并提供当前状态，使用DHCP
WiFi.begin(ssid);
WiFi.begin(ssid, pass);
WiFi.begin(ssid, keyIndex, key); //keyIndex:WEP中指定要使用的密码序号
        //返回值WL_CONNECTED:当成功连接到网络后
            //WL_IDLE_STATUS:未连接到网络，但是接通电源状态
WiFi.disconnect(); //断开网络
WiFi.config(); //手动配置静态IP，改变DNS、网关及子网地址
        //用在begin()前可使其按照给定参数设置网络
WiFi.config(ip, dns, gateway, subnet); //参数可为前1，2，3个
WiFi.setDNS(dns_server1, dns_server2); //主/副DNS服务器，第二个为可选
WiFi.SSID(wifiAccessPoint); //获取当前网络的SSID；参数可选，指明从那个网络获取信息
WiFi.BSSID(bssid); //获取路由器的MAC地址
WiFi.RSSI(wifiAccessPoint); //获取连接的路由器的信号强度；参数可选；单位dBm
WiFi.encryptionType(wifiAccessPoint); //获取当前网络加密类型；参数可选
        //TKIP(WPA)=2, WEP=5, CCMP(WPA)=4, NONE=7, AUTO=8
WiFi.scanNetworks(); //返回网络数量
WiFi.status(); //返回网络状态
        //WL_CONNECTED, WL_NO_SHIELD, WL_IDLE_STATUS暂时状态
        //WL_NO_SSID, WL_SCAN_COMPLETED
        //WL_CONNECT_FAILED, WL_CONNECTION_LOST, WL_DISCONNECTED
WiFi.getSocket(); //获取可得的第一个socket
WiFi.macAddress(mac); //获取WiFi shield的MAC地址
```
- IP地址类
```c
//IP地址类
WiFi.localIP(); //获取WiFi shield的IP地址
WiFi.subnet(); //获取子网掩码
WiFi.gatewayIP(); //获取网关IP地址
```
- 服务器类
```c
WiFiServer Server(port); //放到最前面常量定义处；监听某端口的入口连接
server.begin(); //开始监听入口连接
server.available(); //得到一个连接到服务器的客户端并有数据可读
        //WiFiClient client = server.available(); //监听进入的客户端
server.write(data); //写入数据到所有连接的客户端
server.print(data, BASE); //打印数据到所有连接的客户端
        //BASE可选：BIN, DEC, OCT, HEX
server.println(data, BASE); //参数均为可选
```
- 客户端类
```c
WiFiClient client; //初始化客户端库
client.connected(); //判断客户端是否连接
client.connect(ip/URL, port); //连接到指定的地址和端口；返回连接是否成功
client.write(data); //写入数据到客户端给连接到的服务器
client.print(data, BASE); //打印数据到客户端连接到的服务器，BASE可选参数；返回写入的字节数
client.println(data, BASE); //参数依次可选
client.available(); //返回可读的字节数，即写入客户端的数据
client.read(); //读取从连接到的服务器接收到的下一个字节；返回下一字节或-1
client.flush(); //丢弃所有已经写入到客户端但是没有读取的字节
client.stop(); //从服务器断开
```
- UDP类
```c
WiFiUDP.begin(port); //初始化WiFi UDP库和网络设置；开启WiFiUDP socket，监听端口；返回1或0
WiFiUDP.available(); //获取可从缓存中读取的字节数
WiFiUDP.beginPacket(hostName/hostIp, port); //开启连接以将UDP数据写入远端连接；返回1或0
WiFiUDP.endPocket(); //UDP数据写入完成后调用
WiFiUDP.write(byte); //写入数据；需放在beginPacket()和endPacket()之间
        //WiFiUDP.write(buffer, size); //buffer:the outgoing message; 有返回值
WiFiUDP.parsePacket(); //开始处理进入的下一个包，检查UDP包是否存在并报告其大小
        //必须在用UDP.read()读取缓存前调用
WiFiUDP.peek(); //从文件读取一个字节而不继续读取下一个
WiFiUDP.read(buffer, len); //从缓存读取UDP；两个参数同时使用并为可选；有返回值
WiFiUDP.flush(); //丢弃所有已经写入到客户端但是没有读取的字节
WiFiUDP.stop(); //断开连接
WiFiUDP.remoteIP(); //获取IP
WiFiUDP.remotePort(); //获取远程UDP连接的端口
```

**蓝牙**
- BLEPeripheral类
```c
#include <CurieBLE.h>
BLEPeripheral blePeripheral;
blePeripheral.begin()
blePeripheral.poll()
blePeripheral.end()
blePeripheral.setAdvertisedServiceUuid()
blePeripheral.setLocalName()
blePeripheral.setDeviceName()
blePeripheral.setAppearance()
blePeripheral.setEventHandler()
blePeripheral.addAttribute()
blePeripheral.disconnect()
blePeripheral.central()
blePeripheral.connected()
```
- BLEDescriptor类
```c
BLEDescriptor(const char* uuid, const unsigned char value[], unsigned char valueSize);
BLEDescriptor(const char* uuid, const char* value);
```
- BLECentral类
```c
BLECentral central = blePeripheral.central();
central.connected()
central.address()
central.disconnect()
central.poll()
```
- BLECharacteristic类
```c
BLECharacteristic
BLEBoolCharacteristic yourCharacteristicName(UUID, properties)
BLECharCharacteristic yourCharacteristicName(UUID, properties)
BLEUnsignedCharCharacteristic yourCharacteristicName(UUID, properties)
BLEShortCharacteristic yourCharacteristicName(UUID, properties)
BLEUnsignedShortCharacteristic yourCharacteristicName(UUID, properties)
BLEIntCharacteristic yourCharacteristicName(UUID, properties)
BLEUnsignedIntCharacteristic yourCharacteristicName(UUID, properties)
BLELongCharacteristic yourCharacteristicName(UUID, properties)
BLEUnsignedLongCharacteristic yourCharacteristicName(UUID, properties)
BLEFloatCharacteristic yourCharacteristicName(UUID, properties)
BLEDoubleCharacteristic yourCharacteristicName(UUID, properties)
//properties: what remote clients will be able to get notifications if this characteristic changes. It can assume the following values:
//BLERead, BLEWrite, BLENotify
```
- BLEService类
```c
BLEService ledService(const char* uuid)
```

**IMU**
- 函数
```c
#include <CurieIMU.h>
CurieIMU.begin()
CurieIMU.getGyroRate()
CurieIMU.setGyroRate()
CurieIMU.getAccelerometerRate()
CurieIMU.setAccelerometerRate()
CurieIMU.getGyroRange()
CurieIMU.setGyroRange()
CurieIMU.getAccelerometerRange()
CurieIMU.setAccelerometerRange()
autoCalibrateGyroOffset()
autoCalibrateAccelerometerOffset()
noGyroOffset()
noAccelerometerOffset()
gyroOffsetEnabled()
accelerometerOffsetEnabled()
getGyroOffset()
getAccelerometerOffset()
setGyroOffset()
setAccelerometerOffset()
getDetectionThreshold()
setDetectionThreshold()
getDetectionDuration()
setDetectionDuration()
interrupts()
noInterrupts()
interruptEnabled()
getInterruptStatus()
getStepDetectionMode()
setStepDetectionMode()
readMotionSensor()
readAccelerometer()
readGyro()
readTemperature()
shockDetected()
motionDetected()
tapDetected()
stepsDetected()
attachInterrupt()
detachInterrupt()
```

**Wire**
- 函数
```c
#include <Wire.h>
TwoWire Wire = TwoWire(0)       // the first i2c interface
TwoWire Wire1 = TwoWire(1)      // the second i2c interface
begin()
Wire.begin(sda, scl);   // define the sda/scl pins
end()
requestFrom()
beginTransmission()
endTransmission()
write()
available()
read()
setClock()
onReceive()
onRequest()
setWireTimeout()
clearWireTimeoutFlag()
getWireTimeoutFlag()
```

**EEPROM**
**Ethernet**
**Firmata**
**GSM**
**Servo**
**SPI**
```c
#include <SPI.h>
SPISettings
begin()
beginTransaction()
endTransaction()
end()
setBitOrder()
setClockDivider()
setDataMode()
transfer()
usingInterrupt()
```
**SoftwareSerial**
**Stepper**
**TFT**