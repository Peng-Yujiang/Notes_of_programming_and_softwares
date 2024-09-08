[教程链接](https://github.com/HeKun-NVIDIA/CUDA-Programming-Guide-in-Chinese/tree/main)

[官方教程链接](https://docs.nvidia.com/cuda/cuda-c-programming-guide/contents.html)

## 1. CUDA简介
### 三个关键抽象
1. 线程组的层次结构
2. 共享内存
3. 屏障同步

- 提供了细粒度的数据并行性和线程并行性，并将嵌套在粗粒度的数据并行和任务并行中。

## 2. CUDA编程模型概述
### 2.1. 内核
1. 可定义称为**kernel**的C++函数来扩展C++，当调用内核时，由N个不同的CUDA线程并行执行N次。
2. 使用`__global__`声明说明符定义内核，并使用新的`<<<...>>>` 执行配置（execution configuration）语法指定内核调用时的 CUDA 线程数。
3. 每个执行内核的线程都有一个唯一的线程 ID，可以通过内置变量在内核中访问。
```c++
// Kernel definition
__global__ void VecAdd(float* A, float* B, float* C)
{
    int i = threadIdx.x;
    C[i] = A[i] + B[i];
}

int main()
{
    ...
    // Kernel invocation with N threads
    // 执行 VecAdd() 的 N 个线程中的每一个线程都会执行一个加法。
    VecAdd<<<1, N>>>(A, B, C);
    ...
}
```

### 2.2. 线程层次
1. 通过线程索引`thread index` 来识别线程，可以是一、二、三维。
2. 线程可组成线程块`thread block`，可以是一、二、三维。

### 2.3. 储存体系结构
1. 每个线程都有私有的本地内存，以及共享内存，该共享内存对该块中所有线程可见，并具有与该块相同的生命周期。
2. 两个额外的只读内存空间可供所有线程访问：常量和纹理内存空间。

### 2.4. 异构编程
1. CUDA 编程模型假定 CUDA 线程在物理独立的设备(device)上执行，该设备作为运行 C++ 程序的主机(host)的协处理器运行。

**注:串行代码在主机(host)上执行，并行代码在设备(device)上执行。**

### 2.5. 异步SIMT编程模型

#### 1. 异步操作
1. 在结构良好的程序中，一个或多个CUDA线程可与异步操作同步。发起异步操作的CUDA线程不需要在同步线程中。
2. 这样的异步线程（as-if 线程）总是与发起异步操作的 CUDA 线程相关联。异步操作使用同步对象来同步操作的完成。这样的同步对象可以由用户显式管理（例如，`cuda::memcpy_async`）或在库中隐式管理（例如，`cooperative_groups::memcpy_async`）。

同步对象可以是 `cuda::barrier` 或 `cuda::pipeline`。这些对象在[Asynchronous Barrier](https://docs.nvidia.com/cuda/cuda-c-programming-guide/index.html#aw-barrier) 和 [Asynchronous Data Copies using cuda::pipeline](https://docs.nvidia.com/cuda/cuda-c-programming-guide/index.html#memcpy_async_pipeline)中进行了详细说明。这些同步对象可以在不同的线程范围内使用。作用域定义了一组线程，这些线程可以使用同步对象与异步操作进行同步。下表定义了CUDA C++中可用的线程作用域，以及可以与每个线程同步的线程。


| Thread Scope	| Description |
| ----| ----|
|cuda::thread_scope::thread_scope_thread|	Only the CUDA thread which initiated asynchronous operations synchronizes.|
|cuda::thread_scope::thread_scope_block	|All or any CUDA threads within the same thread block as the initiating thread synchronizes.|
|cuda::thread_scope::thread_scope_device|	All or any CUDA threads in the same GPU device as the initiating thread synchronizes.|
|cuda::thread_scope::thread_scope_system|	All or any CUDA or CPU threads in the same system as the initiating thread synchronizes.|

### 2.6. Compute Capability
主版本号相同的设备具有相同的核心架构。若设备的主要修订号是 8，则说明设备基于NVIDIA Ampere GPU的体系结构,7 为设备基于Volta设备架构,6为设备基于Pascal架构,5为设备基于Maxwell架构,3为设备基于Kepler架构的设备,2为设备基于Fermi架构,1为设备基于Tesla架构的设备。

## 3. 编程接口
### 3.1. 利用NVCC编译
内核可使用 `PTX` 的 CUDA 指令集架构来编写，或使用高级编程语言（如 C++）；二者均须通过`nvcc` 编译成二进制代码才能在设备上执行。

#### 1. 编译流程
1. 离线编译
 - 源文件可以包含主机代码（即在host上执行的代码）和设备代码（即在device上执行的代码）。
 - 基本流程包括：
    1. 将设备代码与主机代码分离
    2. 将设备代码编译成汇编形式（PTX 代码）或二进制形式（cubin 对象）
    3. 通过 CUDA 运行时中的函数调用来替换主机代码中的 <<<...>>> 语法，对主机代码进行修改（更具体的描述可以参照执行配置），来从 PTX 代码或 cubin 对象中加载和启动每个编译好的内核。
    4. 修改后的主机代码
        - 要么作为 C++ 代码输出，然后使用另一个工具编译；
        - 要么作为目标代码直接输出——通过让 nvcc 在最后编译阶段调用主机编译器对代码进行编译。
    5. 应用程序可以
        - 链接已编译的主机代码（这是最常见的情况）；
        - 或者忽略修改后的主机代码（如果有），使用 CUDA 驱动程序 API（请参阅驱动程序 API）来加载和执行 PTX 代码或 cubin 对象。

2. 即时编译
 - 应用程序在运行时加载的任何 PTX 代码都由设备驱动程序进一步编译为二进制代码。这称为即时编译（just-in-time compilation）。
    - 增加了应用程序加载时间
    - 可以从每个新的设备驱动程序内置的新编译器中获得性能改进
    - 使得应用程序能够在那些编译时不存在的设备中运行的唯一方式
 - NVRTC 可在运行时将 CUDA C++ 设备代码编译为 PTX。 NVRTC 是 CUDA C++ 的运行时编译库。

 #### 2. Binary兼容性
 - 二进制代码是特定于体系结构的。 使用指定目标体系结构的编译器选项 -code 生成 cubin 对象：例如，使用 -code=sm_35 编译会为计算能力为 3.5 的设备生成二进制代码。

#### 3. PTX兼容性
 - 某些 PTX 指令仅在具有较高计算能力的设备上受支持。
 - -arch 编译器选项指定了将 C++ 编译为 PTX 代码时假定的计算能力。 因此，例如，包含 warp shuffle 的代码必须使用 -arch=compute_30（或更高版本）进行编译。

#### 4. 应用程序兼容性
 - 要在具有特定计算能力的设备上执行代码，应用程序必须加载与此计算能力兼容的二进制或 PTX 代码，如二进制兼容性和 PTX 兼容性中所述。
 - 哪些 PTX 和二进制代码会嵌入到 CUDA C++ 应用程序中由 -arch 和 -code 编译器选项或 -gencode 编译器选项控制，详见 nvcc 用户手册。

#### 5. C++兼容性
编译器前端根据 C++ 语法规则处理 CUDA 源文件。 主机代码支持完整的 C++。 但是，设备代码仅完整支持 C++ 的一个子集，如 C++ 语言支持中所述。

#### 6. 64位支持

### CUDA Runtime
