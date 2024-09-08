[谭升的博客](https://face2ai.com/program-blog/#GPU%E7%BC%96%E7%A8%8B%EF%BC%88CUDA%EF%BC%89)
[教程对应的示例代码](https://github.com/Tony-Tan/CUDA_Freshman)

# 1. 基础篇
1. 如何查看CUDA版本
- 命令行运行 `nvidia-smi`，右上角CUDA Version便是CUDA Driver API版本。

2. 运行时API（Runtime API）
- 前提是已在电脑上安装CUDA Toolkit
- 命令行输入 `nvcc -v`，最下面一行即是CUDA Runtime API的版本。
- Driver API和Runtime API可以不同


