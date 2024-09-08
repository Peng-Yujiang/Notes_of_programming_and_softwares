## 避免发生潜在问题

1. 避免使用`using namespace std;`
    - 使用该语句会将该命名空间中所有符号转为全局变量；非常难以发现问题

2. 避免使用`std::endl`
    - 立即刷新输出缓存，造成严重的性能下降
    - **推荐使用**：`\n`


## 注意事项
1. 基础类型变量**默认不会进行初始化**
```c++
int k;  // k not initialized!
cout << k << '\n';  // value might be anything
```

2. 函数声明在前，使用在后

3. 比较输入参数是否是特定字符串
```c++
std::string(argv[5]) == "true"
```


## 推荐使用
1. 如果变量初始化后不需要修改时，可以添加`const`，防止被修改
    - `int const k = i;  // "int constant"`

2. 类型别名
```c++
using NewType = OldType;  // C++11, 推荐使用
typedef OldType NewType;
```

3. 