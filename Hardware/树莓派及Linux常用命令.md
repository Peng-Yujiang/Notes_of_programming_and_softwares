# 树莓派及Linux常用命令
Author: Yujiang Peng
Date: March 10, 2021

## 零、 从头开始配置树莓派

- 烧写系统
- 存入配置文件连接网络
- 更新软件

1. 更改配置
    ```
    ping raspberrypi.local  // Windows命令行
    sudo raspi-config
    ```
2. 升级软件
    ```
    sudo apt-get update
    sudo apt-get upgrade
    ```
3. 安装程序和库
```
sudo apt install -y python3-dev

sudo apt install -y python3-pil
sudo apt install -y python3-pip
sudo apt install -y python3-setuptools
sudo apt install -y python3-rpi.gpio
sudo apt install -y git
sudo apt-get install xrdp
sudo pip install Adafruit_BBIO
sudo pip3 install --upgrade Adafruit_BBIO
sudo apt install -y python-imaging python-smbus i2c-tools
sudo pip3 install adafruit-circuitpython-mpu6050

sudo apt install -y mosquitto mosquitto-clients

// esp-idf
sudo apt-get install git wget flex bison gperf python3 python3-venv cmake ninja-build ccache libffi-dev libssl-dev dfu-util libusb-1.0-0

mkdir -p ~/esp
cd ~/esp
git clone --recursive https://github.com/espressif/esp-idf.git

cd ~/esp/esp-idf
./install.sh esp32
. $HOME/esp/esp-idf/export.sh
```

- 查找I2C总线
    ```
    i2cdetect -y 1
    ```
- 安装0.96寸屏幕驱动
    ```
    git clone https://github.com/adafruit/Adafruit_Python_SSD1306.git
    cd Adafruit_Python_SSD1306
    sudo python3 setup.py install
    ```
4. python程序开机自启动
- 重点是需要安装的库一定要以root身份安装，即前面加`sudo`
    ```
    sudo nano /etc/rc.local
    ```
- 在exit0前输入
    ```
    python3 /home/pi/.autostart/pyj_pistatus.py &
    ```
5. 创建两个附加总线 nl总线3和4
    - 打开cli并运行
    ```
    cd /boot
    sudo nano config.txt
    ```
    添加以下代码，最好在启用spi和i2c的部分中。
    此行将在GPIO 23上以SDA和GPIO 24形式在SCL上创建一个附加的i2c总线（总线4）（GPIO 23和24为默认值）
    ```
    dtoverlay = i2c-gpio，bus = 4，i2c_gpio_delay_us = 1， i2c_gpio_sda = 23，i2c_gpio_scl = 24
    ```
    还添加以下行以创建i2c总线3
    GPIO 17将是i2c总线4的SDA，而GPIO 27将是SCL
    ```
    dtoverlay = i2c-gpio，bus = 3，i2c_gpio_delay_us = 1，i2c_gpio_sda = 17，i2c_gpio_scl = 27
    ```
    关于总线编号和顺序的说明：
    从不使用总线0和2，它用于其他的东西像帽子上的eprom这样的板子。
    对于2019年4月发布的Raspbian版本：
    您应该始终从config.txt中的最高总线（在这种情况下为4总线）开始，一直运行到最低总线（3总线）。最低的总线必须始终是3总线。
    如果您需要5条额外的总线，则这些总线必须双向接入7，6，5，4，3的顺序最初编写此Instructable时，此总线顺序上的问题不存在。似乎对内核进行了更改。
    关闭PI，将其关闭。将您的i2c设备连接到总线4（SDA到GPIO 23和SCL到GPIO 24），另一个连接到i2c总线3（SDA到GPIO 17和SCL到GPIO 27）。
    打开pi。
    运行：
    ```
    sudo i2cdetect -l（小写L）
    ```
    现在您将看到i2c总线3和4是也列出了。同时运行：
    ```
    sudo i2cdetect -y 3
    sudo i2cdetect -y 4
    ```

6. 调用摄像头

- 升级固件
    ```
    sudo rpi-update
    ```
- 通过下面命令行开启摄像头功能
    ```
    sudo raspi-config
    ```
- 拍照
    ```
    raspistill -t 5000 -o 1.jpg
    ```
7. 其它注意事项
    若出现错误，可用下列命令行更新树莓派固件
    一般情况不要用
    ```
    sudo rpi-update
    ```
    StringIO已并入io

    from io import BytesIO 才不会报错

    在Python 3中，range()与xrange()合并为range( )


8. 有线网络
    ```
    sudo nano /etc/network/interfaces
    ```
    改为
    ```
    auto lo
    iface lo inet loopback

    iface eth0 inet static
    address 192.168.1.1
    netmask 255.255.255.0
    gateway 192.168.1.1
    ```
9. 无线网络
    ```
    ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev
    update_config=1
    network={
    ssid="TP-Link_ED11"
    psk="16158184"
    }

    auto lo
    iface lo inet loopback
    iface eth0 inet dhcp
    auto wlan0
    allow-hotplug wlan0
    iface wlan0 inet dhcp
    wpa-ssid "MiWiFi"
    wpa-psk "hellworld"
    #wpa-roam /etc/wpa_supplicant/wpa_supplicant.conf
    ```

10. 使用samba
- 安装
    ```
    sudo apt-get install samba samba-common-bin
    ```
- 修改配置文件
    ```
    sudo nano /etc/samba/smb.conf
    ```

- 进行配置，使用户可以访问自己的目录

    - 开启用户认证，找到##### Authentication #####，将# security = user的#号去掉。
    -配置用户可以读写自己的 home 目录，在[homes]节中，把 read only = yes 改为read only = no 。
    - 找到browseable=no改为yes，否则等会访问时此文件夹会隐藏。

- 重启samba服务
    ```
    sudo /etc/init.d/samba restart
    ```
- 把系统默认用户pi添加到samba
    ```
    sudo smbpasswd -a pi
    ```
11. windows访问

    浏览器访问\\172.20.223.91\pi

12. 挂载u盘
    ```
    sudo mkdir /mnt/udisk
    ```
13. 手动挂载

- 挂载
    ```
    sudo mount -o uid=pi,gid=pi /dev/sda1 /mnt/udisk
    ```
- 卸载
    ```
    sudo umount /mnt/1GB_USB_flash
    ```
    >注意：
sda1 是取决于你的实际情况，a 表示第一个硬盘，1 表示第一个分区。
FAT 格式 U 盘 mount 本身就能支持，但如果你的 U 盘或移动硬盘使用的是 exFAT 格式，mount 会说不支持。没关系，安装 exfat-fuse 软件之后 mount 就支持了。sudo apt-get install exfat-fuse


14. 开机挂载

    开启自动挂载的话，可以编辑 /etc/fstab文件
    ```
    /dev/sda1 /mnt/udisk vfat rw,defaults 0 0
    ```

15. 修改wlan0为静态IP
    ```
    sudo vim /etc/network/interfaces
    ```
- 把原来关于wlan0的注释掉
    ```
    #auto wlan0
    #iface wlan0 inet dhcp
    #wpa-ssid "360WiFi-li"
    #wpa-psk "xiaolizi"
    ```
- 再添加
    ```
    iface wlan0 inet static
    address 192.168.0.1
    netmask 255.255.255.0
    gateway 192.168.0.1
    ```
- 最后重启

16. 安装Mosquitto (MQTT)
- 安装软件
    ```
    sudo apt install -y mosquitto
    sudo apt install mosquitto-clients
    ```
- 开启自启动
    ```
    sudo systemctl enable mosquitto.service
    ```
- 测试是否成功安装
    ```
    mosquitto -v
    ```
- 注意：如果提示Error: Address already in use.说明已成功启用
- 订阅消息
    ```
    mosquitto_sub -t 'test' -d
    ```
- 发布消息
    ```
    mosquitto_pub -d -t test -m 'Hello world'
    ```
- 查看树莓派IP地址
    ```
    hostname -I
    // https://blog.csdn.net/chentuo2000/article/details/115151756
    ```



## 一、树莓派相关命令
1. 清除终端窗口
    ```
    clear
    ```
2. 关闭Raspberry Pi
    ```
    sudo halt
    ```
3. 重新启动Raspberry Pi
    ```
    sudo reboot
    ```
4. Raspberry Pi配置工具
    ```
    sudo raspi-config
    ```
4. 启动桌面环境（LXDE）
    ```
    startx
    ```
5. 查找Raspberry Pi的IP地址
    ```
    ifconfig
    ```
6. 禁用树莓派电源指示灯
    ```
    echo "# Disable the PWR LED" | sudo tee -a /boot/config.txt
    echo "dtparam=pwr_led_trigger=none" | sudo tee -a /boot/config.txt
    echo "dtparam=pwr_led_activelow=off" | sudo tee -a /boot/config.txt
    ```
7. 禁用树莓派以太网指示灯
    ```
    echo "# Disable Ethernet LEDs" | sudo tee -a /boot/config.txt
    echo "dtparam=eth_led0=14" | sudo tee -a /boot/config.txt
    echo "dtparam=eth_led1=14" | sudo tee -a /boot/config.txt
    ```
8. 禁用树莓派wifi
    ```
    echo "dtoverlay=pi3-disable-wifi" | sudo tee -a /boot/config.txt
    ```
10. 禁用树莓派蓝牙
    ```
    echo "dtoverlay=pi3-disable-bt" | sudo tee -a /boot/config.txt
    ```
11. 禁用树莓派板载音频驱动程序
    ```
    echo "blacklist snd_bcm2835"> /etc/modprobe.d/alsa-blacklist.conf
    ```
12. 禁止树莓派自动休眠，防止树莓派黑屏
    1. 首先修改lightdm.conf文件
        ```
        sudo nano /etc/lightdm/lightdm.conf
        ```
    2. 接着找到[Seat:*]段下的 #xserver-command, 取消注释 , 修改为如下:
        ```
        xserver-command=X -s 0 -dpms
        # s 0 是设置屏幕保护不启用
        # -dpms 是关闭系统的电源节能管理
        ```
    3. 保存后退出,然后重启树莓派即可。
        ```
        sudo reboot
        ```
13. 在SD卡的boot分区种新建 wpa_supplicant.conf 文件，并且配置 WiFi 的 SSID 和密码，这样树莓派启动后会自行读取 wpa_supplicant.conf 配置文件连接 WiFi 设备。
    ```
    # wpa_supplicant.conf
    ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev
    update_config=1
    network={
    ssid="WLAN-854752"
    psk="republikplatz333"
    }
    ```
    - 更多SD卡操作[https://www.cxyzjd.com/article/rk2900/8640891]
    ```
    cd      // 返回到系统目录 ~
    cd /dev
    ls sd*  // 查看sd卡分区
    sudo mkdir sdka // 创建挂载点sdka
    sudo mount -o rw /dev/sdc sdka  // 将隐藏的Linux分区挂载到sdka
    cd sdka
    ls      // 查看文件目录
    sudo cp -r /dev/sdka /mnt/c/backup  // 复制文件到Windows盘
    ```

14. 配置python程序自启动，输入命令：
    ```
    sudo nano /etc/rc.local
    ```
    在exit 0上一行输入：
    ```
    python /home/pi/test.py
    ```
15. 配置用户界面显示此村
    ```
    sudo nano /boot/config.txt
    ```

16. 蓝牙配置

- 安装包
    ```
    sudo apt-get install bluetooth  bluez  python-bluez
    ```
- 进入蓝牙连接工具

    bluetoothctl
    agent on
    default-agent
- 扫描蓝牙设备

    scan on
- 连接蓝牙设备

    pair 06:60:9E:8B:F6:10
- 生成rfcomm0文件（要用）

    sudo rfcomm bind 0 06:60:9E:8B:F6:10


## 二、Linux常用命令
1. 成为root user
    ```
    sudo passwd root
    sudo -i
    ```

## 三、Linux文件及目录操作
1. 查看SD卡分区
    ```
    sudo fdisk -l
    ```
2. 移动文件
    ```
    mv file directory
    ```
17. 编辑文件
    ```
    nano file
    ```
18. 显示文件的内容
    ```
    cat file
    ```
19. 删除文件
    ```
    rm file
    ```
1. 列出目录内容
    ```
    ls
    ```
6. 更改目录
    ```
    cd
    ```
7. 创建目录
    ```
    mkdir
    ```
8. 删除目录
    ```
    rmdir
    ```
10. 显示目录树
    ```
    tree -d
    ```
11. 显示当前目录
    ```
    pwd
    ```
20. 复制文件或目录
    ```
    cp original_file new_file #在同一目录中复制文件
    cp original_file home/pi/subdirectory #将文件复制到另一个文件夹中
    cp -R home/pi/folder_one home/pi/folder_two #要复制整个目录（及其内容）
    ```

## 四、Linux常用apt命令列表
1. 更多常用命令，输入：
    ```
    apt
    apt-get
    apt-cache
    ```
2. 删除包，包括删除配置文件等
    ```
    sudo apt-get remove - - purge
    ```
3. 删除包及其依赖的软件包+配置文件等（只对6.10有效，强烈推荐）
    ```
    sudo apt-get autoremove --purge
    ```
4. 升级系统
    ```
    sudo apt-get dist-upgrade
    ```
5. 使用 dselect 升级
    ```
    sudo apt-get dselect-upgrade
    ```
6. 清理下载文件的存档 && 只清理过时的包
    ```
    sudo apt-get clean
    sudo apt-get autoclean
    ```
7. 了解使用依赖
    ```
    apt-cache depends
    ```
8. 显示所有依赖于该软件包的软件包名字
    ```
    apt-cache rdepends
    ```

