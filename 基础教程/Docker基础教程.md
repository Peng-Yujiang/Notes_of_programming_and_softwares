- [一、安装与卸载](#一安装与卸载)
  - [1. 安装docker](#1-安装docker)
  - [2. 验证已安装](#2-验证已安装)
  - [3. 检查docker Daemon状态](#3-检查docker-daemon状态)
  - [4. 检查Socket权限](#4-检查socket权限)
  - [5. 配置Docker以使用代理](#5-配置docker以使用代理)
  - [6. 网络连接检查](#6-网络连接检查)
  - [7. 禁用代理](#7-禁用代理)
  - [8. 卸载docker](#8-卸载docker)
- [二、 基本操作](#二-基本操作)
  - [1. 镜像获取与管理](#1-镜像获取与管理)
  - [2. 运行及管理容器](#2-运行及管理容器)
- [三、使用Dockerfile](#三使用dockerfile)
  - [1. 创建自定义Ubuntu镜像](#1-创建自定义ubuntu镜像)
   
Windows安装WSL
```bash
wsl.exe --list --online     # 列出可用的分发版
wsl.exe --install <Distro>  # 进行安装。
```

# 一、安装与卸载

## 1. 安装docker

  ```bash
  # Update the package index
  sudo apt-get update

  # Install packages to allow apt to use a repository over HTTPS
  sudo apt-get install \
      ca-certificates \
      curl \
      gnupg \
      lsb-release

  # Add Docker’s official GPG key
  sudo mkdir -p /etc/apt/keyrings
  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

  # Set up the stable repository
  echo \
    "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

  # Update the package index again
  sudo apt-get update

  # Install Docker Engine
  sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
  ```

## 2. 验证已安装

  ```bash
  docker --version
  # output: Docker version 27.3.1, build ce12230
  ```

## 3. 检查docker Daemon状态

1. 基于系统的系统（如Ubuntu、Debian等）

    ```bash
    sudo systemctl status docker
    # output:
    #● docker.service - Docker Application Container Engine
    #    Loaded: loaded (/lib/systemd/system/docker.service; enabled; vendor preset: enabled)
    #    Active: active (running) since Wed 2024-10-09 20:26:32 CST; 13h ago
    #TriggeredBy: ● docker.socket
    #      Docs: https://docs.docker.com
    #  Main PID: 320371 (dockerd)
    #      Tasks: 19
    #    Memory: 41.1M
    #    CGroup: /system.slice/docker.service
    #            └─320371 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock

    sudo systemctl start docker   # 启动docker daemon
    sudo systemctl restart docker # 重启docker
    sudo systemctl enable docker  # 开机启动docker

    ```

2. 非系统的系统

    ```bash
    sudo service docker start
    ```

## 4. 检查Socket权限

1. 创建并添加成员

    ```bash
    sudo groupadd docker          # 创建docker组
    sudo usermod -aG docker $USER # 将用户添加到docker组
    newgrp docker                 # 应用新组成员关系
    ```

## 5. 配置Docker以使用代理

1. 创建Docker Systemd服务目录及配置文件

    ```bash
    sudo mkdir -p /etc/systemd/system/docker.service.d  # 创建Docker Systemd服务目录
    sudo nano /etc/systemd/system/docker.service.d/http-proxy.conf  # 创建或编辑代理配置文件
    ```

2. 将下列内容添加到文件中

    ```ini
    [Service]
    Environment="HTTP_PROXY=http://127.0.0.1:7890/"
    Environment="HTTPS_PROXY=http://127.0.0.1:7890/"
    Environment="NO_PROXY=localhost,127.0.0.1,::1"
    ```

3. 重新加载Systemd及重启Docker

    ```bash
    sudo systemctl daemon-reload
    sudo systemctl restart docker
    sudo ufw disable
    ```

4. 检查Docker服务状态

    ```bash
    sudo systemctl status docker
    ```

5. 验证Docker的代理配置

    ```bash
    docker info | grep -i proxy
    # output: 
    # HTTP Proxy: http://127.0.0.1:7890/
    # HTTPS Proxy: http://127.0.0.1:7890/
    # No Proxy: localhost,127.0.0.1,::1
    ```

## 6. 网络连接检查

1. 检查网络连接

    ```bash
    ping google.com     # 检查网络连接
    curl -v <https://registry-1.docker.io/v2/>  # 检查Docker Hub连接
    ```

## 7. 禁用代理

1. 移除代理配置

    ```bash
    sudo rm /etc/systemd/system/docker.service.d/http-proxy.conf
    ```

2. 重新加载和启动Docker

    ```bash
    sudo systemctl daemon-reload
    sudo systemctl restart docker
    ```

3. 取消代理环境变量设置（可选）

    ```bash
    unset HTTP_PROXY
    unset HTTPS_PROXY
    unset NO_PROXY
    ```

## 8. 卸载docker

  ```bash
  sudo apt-get remove docker docker-engine docker.io containerd runc
  sudo rm -rf /var/lib/docker
  sudo rm -rf /var/lib/containerd
  sudo apt-get update
  sudo apt-get upgrade
  ```

# 二、 基本操作

## 1. 镜像获取与管理

1. 拉取镜像

    ```bash
    sudo docker pull ubuntu:20.04   # 从 Docker Hub 拉取 Ubuntu 20.04.6 的官方镜像
    ```

2. 镜像管理

    ```bash
    sudo docker images                      # 查看 Docker 镜像
    sudo docker image ls                    # 查看 Docker 镜像
    sudo docker images --format "table {{.Repository}}\t{{.Tag}}\t{{.ID}}"    # 以表格、JSON 或其他格式输出
    sudo docker rmi <IMAGE_ID>              # 删除一个镜像
    sudo docker rmi -f <IMAGE_ID>           # 强制删除镜像
    sudo docker rmi <REPOSITORY>:<TAG>      # 删除一个镜像
    sudo docker image prune                 # 删除所有未使用的镜像
    sudo docker rmi $(docker images -q)     # 删除所有镜像
      # docker images -q：列出所有镜像的 ID。
      # $(...)：将上述命令的输出作为参数传递给 docker rmi 命令。
    sudo docker rmi -f $(docker images -q)  # 强制删除所有镜像（如果有容器使用）
    sudo docker tag <镜像名>:<标签> <新镜像名>:<新标签>
    ```

### 3. 更新镜像
1. 使用镜像来创建一个容器

    ```bash
    docker run -t -i <image>:<tag> /bin/bash
    ```

2. 进入容器后，更新系统

    ```bash
    apt-get update
    apt-get upgrade -y
    ```

3. 完成操作后，输入exit退出这个容器

    ```bash
    exit
    ```

4. 通过`docker commit`来提交容器副本

    ```bash
    docker commit -m="has update" -a="pyj" <container-ID> <image>:<tag>
        #-m: 提交的描述信息
        #-a: 指定镜像作者
        # <container-ID>：容器 ID
        # <image>:<tag>: 指定要创建的目标镜像名
    ```

### 4. 确认镜像依赖关系
1. 列出所有镜像的

    ```bash
    docker images --digests
    ```
    
2. 


## 2. 运行及管理容器

1. 创建容器

    ```bash
    sudo docker run -it --name <容器名称> ubuntu:20.04
    # -it：以交互模式运行容器，并分配一个伪终端。
    # --name <容器名称>：为容器指定一个名称（可选）。
    # ubuntu:20.04：指定使用的镜像及标签。
    ```

2. 运行/结束容器

    ```bash
    sudo docker start <容器名称>   # 启动一个已存在的容器
    sudo docker stop <容器名称>    # 停止一个运行中的容器
    ```

3. 进入正在运行的容器及退出

    ```bash
    sudo docker exec -it <容器名称> bash
    exit    # 正常退出终端，终止容器
    Ctrl + D    # 终止当前会话，并退出容器
    Ctrl + P， 然后 Ctrl + Q    # 保持容器运行但退出终端
    ```

4. 容器管理

    ```bash
    sudo docker ps -a   # 查看所有容器（包括未运行的）
    sudo docker rm <容器名称>   # 删除一个已存在的容器
    sudo docker info | grep "Docker Root Dir" # 详细配置信息，包括数据根目录
    sudo docker info | grep "Storage Driver"  # 查看当前使用的存储驱动
    sudo docker system prune                # 删除所有未使用的镜像、未使用的容器和网络
    sudo docker system prune --volumes      # 删除包括卷在内的所有未使用资源（镜像、容器、网络、卷）
    ```

## 3. 私有registry

1. 创建private registry

    ```bash
    # pull images
    docker pull registry:latest
    # docker run
    docker run -d -p 5000:5000 --name local-registry registry:latest
    # docker push existed image
    docker push localhost:5000/<your-own-image>
    # check images pushed to the private registry
    curl -XGET -u admin:admin localhost:5000/v2/_catalog    # 使用 HTTP API 获取注册表中的所有镜像
    ```

2. **push 本地镜像之前，需要对镜像重新命名**

    ```bash
    # docker tag existed image
    docker tag <your-own-image>:<tag> localhost:5000/<your-own-image>:<tag>
    ```

3. 获取镜像的所有标签

    ```bash
    curl http://localhost:5000/v2/<image_name>/tags/list
    ```

# 三、使用Dockerfile

## 1. 创建自定义Ubuntu镜像

1. 在Ubuntu基础上安装特定的软件或进行配置，可以创建一个`Dockerfile`来构建自定义镜像。

    ```dockerfile
    # 使用 Ubuntu 20.04 作为基础镜像
    FROM ubuntu:20.04

    # 设置环境变量以避免在安装过程中被提示输入
    ENV DEBIAN_FRONTEND=noninteractive

    # 更新包列表并安装常用软件
    RUN apt-get update && \
        apt-get install -y \
            git \
            curl \
            g++ \
            libboost-all-dev \
            libeigen3-dev \
            libceres-dev \
            libyaml-cpp-dev \
            software-properties-common \
            wget \
            apt-transport-https \
            gnupg \
            libgl1-mesa-dev \
            libglu1-mesa-dev \
            freeglut3-dev \
            mesa-common-dev && \
        
        # 安装最新版本 cmake
        wget -O - https://apt.kitware.com/keys/kitware-archive-latest.asc | gpg --dearmor -o /usr/share/keyrings/kitware-archive-keyring.gpg && \
        echo "deb [signed-by=/usr/share/keyrings/kitware-archive-keyring.gpg] https://apt.kitware.com/ubuntu/ focal main" | tee /etc/apt/sources.list.d/kitware.list && \
        apt-get update && \
        apt-get install -y cmake && \

        # 安装JForg
        curl -fL https://getcli.jfrog.io | sh && \
        mv jfrog /usr/local/bin/ && \
        
        # 清理 APT 缓存以减小镜像体积
        rm -rf /var/lib/apt/lists/*

    # 设置工作目录
    WORKDIR /root/home

    # 默认启动 bash
    CMD ["bash"]
    ```

2. 构建自定义镜像：在该文件所在目录运行

    ```bash
    sudo docker build -t sys-algo-ubuntu:10.14 .
    ```

3. 运行自定义镜像的容器

    ```bash
    sudo docker run -it --name alg-common-ubuntu-container alg-common-ubuntu:20.04
    ```

## 2. 创建自定义Windows镜像
### 1. 选择基础镜像

- 根据需要选择合适的基础镜像：

        windows/nanoserver：更轻量，适用于需要较少依赖的应用。
        windows/servercore：功能更全面，适用于需要更多 Windows 功能的应用。

- 示例：使用 windows/servercore:ltsc2022 作为基础镜像。
    
        FROM mcr.microsoft.com/windows/servercore:ltsc2022


### 2. 创建一个`Dockerfile`来构建自定义镜像

1. 用 PowerShell 安装软件

    示例：在容器中安装Google Chrome
    ```dockerfile
    # 使用 Windows Server Core 作为基础镜像
    FROM mcr.microsoft.com/windows/servercore:ltsc2022

    # 设置工作目录
    WORKDIR C:\\Install

    # 下载 Google Chrome 安装包
    RUN powershell -Command `
        Invoke-WebRequest -Uri "https://dl.google.com/chrome/install/latest/chrome_installer.exe" -OutFile "chrome_installer.exe"

    # 安装 Google Chrome
    RUN powershell -Command `
        Start-Process -FilePath "chrome_installer.exe" -Args "/silent /install" -Wait

    # 清理安装文件
    RUN del chrome_installer.exe

    # 设置容器启动命令（可选）
    CMD ["powershell.exe"]
    ```

2. 使用Chocolatey包管理器

    示例：在容器中安装Git和Visual Studio Code
    ```dockerfile
    # 使用 Windows Server Core 作为基础镜像
    FROM mcr.microsoft.com/windows/servercore:ltsc2022

    # 设置工作目录
    WORKDIR C:\\

    # 安装 Chocolatey
    RUN powershell -NoProfile -ExecutionPolicy Bypass -Command `
        Set-ExecutionPolicy Bypass -Scope Process -Force; `
        [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; `
        iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))

    # 更新环境变量
    ENV PATH="C:\\ProgramData\\chocolatey\\bin;${PATH}"

    # 使用 Chocolatey 安装 Git 和 Visual Studio Code
    RUN choco install -y git vscode

    # 清理 Chocolatey 缓存
    RUN choco clean --yes

    # 设置容器启动命令（可选）
    CMD ["powershell.exe"]
    ```

3. 复制安装文件并运行安装程序

    - 使用方法：

    1. 将安装包放在与Dockerfile相同的目录中
    2. 在Dockerfile中复制并安装

    ```Dockerfile
    # 使用 Windows Server Core 作为基础镜像
    FROM mcr.microsoft.com/windows/servercore:ltsc2022

    # 设置工作目录
    WORKDIR C:\\Install

    # 复制安装包到容器
    COPY MyAppInstaller.exe .

    # 安装 MyApp
    RUN powershell -Command `
        Start-Process -FilePath "MyAppInstaller.exe" -Args "/silent /install" -Wait

    # 清理安装文件
    RUN del MyAppInstaller.exe

    # 设置容器启动命令（可选）
    CMD ["powershell.exe"]
    ```

### 3. 构建自定义镜像

1. **保存Dockerfile**：将上面内容保存为`Dockerfile`

2. **构建镜像**：打开`PowerShell`或命令提示符，导航到`Dockerfile`所在的目录，然后运行：

    ```powershell
    docker build -t <镜像名>:<标签> .
    ```

3. **运行容器**：

    ```powershell
    docker run -it <容器名>:<标签>
    ```