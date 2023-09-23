# 第一部分 基本语言

## 第二章 变量和基本类型

### 2.1 基本内置类型
1. 整数（`short`、`int`、`long`）、字符（`char`）和布尔值（`bool`）合称为整型（integral type）。

    >除bool型外，整型可以是**带符号的**（`signed`）或**无符号的**（`unsigned`）。
    ```c++
    unsign int/ short/ long     // 显式指定为无符号类型；否则默认为带符号型
    ```
    >要获得无符号类型则必须指定该类型为`unsigned`。

2. 浮点型
    >`float`           单精度浮点数
    >`double`          双精度浮点数
    >`long double`     扩展精度浮点数

### 2.2 字面值常量

1. 整型字面值

    >默认类型是`int`或`long`类型
    ```c++
    20      // 十进制
    024     // 0开头，表示八进制
    0x14    // 0x或0X开头表示十六进制
    // 通过增加后缀，可强制转换为long、unsigned或unsigned long类型
    128u    // 无符号型；加 U 或 u
    1L      // 长整型；加 L 或 l
    1024UL  // 无符号长整型；或为1024LU
    ```
2. 浮点字面值规则

    >默认为 `double` 类型
    >使用科学计数法时，指数用 `E` 或者 `e` 表示
    >数值后加上 `F` 或 `f` 表示单精度

3. 布尔字面值和字符字面值
    >布尔型：`true` / `false`   
    >字符型：`'a'   //用单引号定义`     
    >字符串：`"a"   //用双引号定义`     
    >wchar_t类型：字符型前面加 `L` ， 如 `L'a'`     
    >宽字符串类型：字符型前面加 `L` ， 如 `L"a"`        

4. 非打印字符的转义序列
    ```c++
    \n      //换行符  
    \v      //纵向制表符
    \r      //回车符
    \a      //报警符
    \?      //疑问号
    \"      //双引号
    \t      //水平制表符
    \b      //退格符
    \f      //进纸符
    \\      //反斜线
    \'      //单引号
    ```
5. 字符串字面值
    >为兼容C语言，所有的字符串字面值都会在末尾添加一个空字符。

6. 多行字面值
    >在一行的末尾加一反斜线符号可将此行与下一行当作同一行处理
    >>不允许在后面有注释或空格
    >>后继行行首的任何空格和制表符都是字符串字面值的一部分

### 2.3 变量

1. 变量命名习惯
    
    - **变量名一般用小写字母**
    - 标识符应使用帮助记忆的名字
    - 包含多个词的标识符可在每个词之间加一个**下划线**，或者用**驼峰拼写法**

2. 定义对象

    2.1 初始化
    ```c++
    int ival (1024);    // 直接初始化；更灵活更高效
    int ival = 1024;    // 复制初始化
    ```
    2.2 使用多个初始化式
    >定义如何进行初始化的成员函数成为**构造函数（constructor）**
    - 可接受多个参数
    - 一个类可以定义几个构造函数；每个构造函数必须接收不同数目或者不同数目的参数
    - 可以通过一个计数器和一个字符串初始化`string`对象，创建的对象包含重复多次的指定字符
        ```c++
        std::string all_nines (10, '9');    // all_nines = '9999999999'
        // 只能直接初始化
        ```
    - 有多个初始化式时不能使用复制初始化

3. 类类型变量的初始化
    
    - 如果定义某个类的变量时没有提供初始化式，这个类也可以定义初始化时的操作。
    - 通过定义一个特殊的构造函数即**默认构造函数（default constructor）**来实现

4. 声明和定义

    - 由`extern`关键字声明变量名而不定义它
    ```c++
    extern int i;   // 声明而不定义；只有在函数外部时才可以函数初始化
    int i;          // 声明与定义
    ```

### 2.4 `const`限定符
1. 把一个对象转换成一个常量
2. `const`对象默认为文件的局部变量

    - 通过指定`const`变量为`extern`，就可以在整个程序中访问`const`对象

### 2.5 引用
- 是一种**复合类型**，通过在变量名前添加 `&` 符号来定义
- 引用必须用与该引用同类型的对象初始化
    ```c++
    int ival = 1024;
    int &refVal = ival;
    ```
1. 引用是别名
    - 作用在引用上的所有操作事实上都是作用在该引用绑定的对象上。
    - 引用只是对象的另一个名字
2. 定义多个引用
    ```c++
    int i = 1024, i2 = 2048;
    int &r1 = i, &r2 = i2;
    ```
3. `const`引用
    - 是指向`const`对象的引用，可以读取但不能修改

### 2.6 typedef名字

- 定义类型的同义词
    ```c++
    typedef double wages;   // wages可代替double使用，可用作类型说明符
    wages hourly, weekly;   // double hourly, weekly
    ```

### 2.7 枚举
1. 定义和初始化枚举
    ```c++
    enum open_modes {input, output, append};    // 默认赋值从零递增
    ```
2. 枚举成员是常量
    ```c++
    enum Forms{shape = 1, sphere, cylinder, polygen};
    enum Points {point2d = 2, point2w,
                 point3d = , point3w};  //枚举成员值可以是不唯一的；2，3，3，4
    ```

### 2.8 类类型
1. 定义类

    - 第一个访问标号前的任何成员都隐式指定为`private`
    ```c++
    class Class_item{
        public:     //访问标号，可多次出现
            // operations on object will go here
        private:    // 访问标号，可多次出现
            // 只有类组成部分的代码可以访问该部分
            std::string isbn;   //数据成员
            unsigned units_sold;
            double revenue;
    };  // 类体位于花括号里面，花括号后必须要跟一个分号。
    ```

2. 使用`struct`关键字

    - 第一个访问标号前的成员都是`public`
    ```c++
    struct Class_item{
        //无需访问标号，成员均为公开成员
            // operations on object will go here
        private:    // 访问标号，可多次出现
            // 只有类组成部分的代码可以访问该部分
            std::string isbn;   //数据成员
            unsigned units_sold;
            double revenue;
    };
    ```

### 2.9 编写自己的头文件
- 头文件为相关声明提供一个集中存放的位置。
- 头文件一般包含类的定义、`extern`变量的声明和函数的声明。
- 好处：
    - 保证所有文件使用给定实体的同一声明；
    - 当声明需要修改时，只有头文件需要更新。
- 需要注意的点：
    - 所做的声明在逻辑上应该是适于放在一起的；
    - 编译头文件需要一定时间。

1. 头文件用于声明而不是用于定义
    ```c++
    // 定义只能出现一次，声明则可以出现多次
    // 下列同时出现回导致多重定义连接错误
    extern int ival = 10;   // 初始化式；所以是定义
    double fica_rate;       // 无extern；所以是也定义
    ```
    - 对于不应该含有定义这一规则，有三个例外：
        - 可以定义**类、值在编译时就已知到的`const`对象**和**`inline`函数**
        - 这些实体可在多个源文件中定义，只要每个源文件中的定义是相同的
2. 一些`const`对象定义在头文件中
    - 当在头文件中定义了`const`变量后，每个包含该头文件的源文件都有了自己的`const`变量，其名称和值都一样。
    - 如果定义在头文件中，则需要用常量表达式初始化。

3. 预处理器
    - 1. 头文件经常需要其它头文件
        - 必须保证多次包含同一头文件不会引起该头文件定义的类和对象被多次定义。
        - 可使用头文件保护符来避免上述问题
    - 2. 避免多重包含
        - 为避免名字冲突，预处理器变量经常使用全大写字母
        ```c++
        #ifndef EXAMPLE_H
        #define EXAMPLE_H
        // example 类和相关函数的定义
        #endif
        ```
    - 3. 使用自定义的头文件
        ```c++
        #include <standard_header>  // 标准头文件
        #include "my_file.h"        // 自定义的头文件
        ```


## 第三章 标准库类型
- 标准库类型`string`和`vector`，分别定义了大小可变的字符串和集合。
- `string`和`vector`往往将迭代器用作配套类型（companion type），用于访问`string`中的字符，或者`vector`中的元素。
- `bitset`提供了一种抽象方法来操作位的集合。

### 3.1 命名空间的`using`声明
- 使用**using声明**可以在不需要加前缀`namespace_name::`的情况下访问命名空间中的名字
```c++
#include <iostream>
using namespace::name;  // using声明的形式
using std::cin;     // 例子；后续可直接使用cin
using namespace std;    // 一次性声明std库中的名字
```
1. 每个名字都需要一个`using`声明
    ```c++
    using std::cin;
    using std::cout;
    using std::endl;
    ```
2. 使用标准库类型的类定义
    - 在头文件中，必须总是使用完全限定的标准库名字
    - 头文件中应该只定义确实必要的东西

### 3.2 标准库string类型
```c++
#include <string>   // 包含相关头文件
using std::string;  // using声明
```
1. `string`对象的定义初始化
    ```c++
    // 几种初始化string对象的方式
    string s1;      // 默认钩爪函数，s1为空串
    string s2 (s1); // 将s2初始化为s1的一个副本
    string s3 ("value");    // 将s3初始化为一个字符串字面值副本
    string s4 (n, 'c');     // 将s4初始化为字符'c'的n个副本
    // 字符串字面值与标准库string类型不是同一种类型
    ```

2. `string`对象的读写
        从标准输入读取`string`：
            + 读取并忽略开头所有的空白字符（如空格、换行符、制表符）
            + 读取字符直至**再次遇到空白字符**，读取终止
    - 读入未知数目的`string`对象
        - 和内置类型的输入操作符一样，`string`的输入操作符也会返回所读的数据流
    - 用`getline`读取整行文本
        - 接受两个参数：一个输入流对象和一个`string`对象；**`getline(cin, line)`**
        - 从输入流的下一行读取，并保存读取的内容到`string`中，但不包括换行符；即换行符不会保存到`string`对象中
        - 不忽略行开头的换行符
        - 如果第一个字符就是换行符，则`string`参数将被置为空`string`
        - `getline`函数`istream`参数作为返回值
        - `line`不含换行符，可用`endl`来输出一个换行符并刷新输出缓冲区
3. `string`对象的操作
    ```c++
    // 常用的string操作
    s.empty()   // 如果s为空串，则返回true，否则返回false
    s.size()    // 返回s中字符的个数
    s[n]        // 返回s中位置为n的字符，位置从0开始计数
                // 索引数据类型是unsigned类型string::size_type
    s1 + s2     // 把s1和s2连接成一个新字符串，返回新生成的字符串；
                // +的左右操作数必须至少有一个是string类型的
                // s1 + ", " + "world"合法
                // "hello" + ", " + s1非法
    s1 = s2     // 把s1内容替换为s2的副本；
                // 内存效率低，要先释放原来的s1，再重新分配内存空间
    v1 == v2    // 比较二者内容，相等则返回true，否则返回false
    !=, <, <=, >, >=
    ```
    - `string`对象中字符的处理
    ```c++
    // 判断是否是...；返回int值作为真值。
    isalnum(c)  // 字母或数字
    isalpha(c)  // 字母
    iscntrol(c) // 控制字符
    isdigit(c)  // 数字
    isgraph(c)  // 不是空格，但可打印
    islower(c)  // 小写字母
    isprint(c)  // 可打印的字符
    ispunct(c)  // 标点符号；除了数字、字母或空白字符以外的其它字符
    isspace(c)  // 空白字符；空格、制表符、垂直制表符、回车符、换行符、进纸符
    isupper(c)  // 大写字母
    isxdigit(c) // 十六进制数
    tolower(c)  // 如是大写，则返回小写；否则直接返回
    toupper(c)  // 如是小写，则返回大写；否则直接返回
    ```

### 3.3 标准库`vector`类型
```c++
#include <vector>   // 包含相关头文件；vector被成为容器，是一个类模板
using std::vector;  // using声明
```
- 必须说明`vector`保存何种对象的类型，通过将类型放在类模板名称后面的尖括号中来指定类型：
    ```c++
    vector<int> ivec;       // int类型
    vector<Class_item> Class_vec;
    ```
- `vector`不是一种数据类型，而是一个类模板
1. `vector`对象的定义和初始化
    ```c++
    // 几种初始化vector对象的方式
    vector<T> v1;       // vector保存类型为T的对象，默认构造函数v1为空
    vector<T> v2(v1);   // v2是v1的一个副本
    vector<T> v3(n, i); // n个值为i的元素
    vector<T> v4(n);    // 含有值初始化的元素的n个副本
    ```
2. `vector`对象的操作
    ```c++
    v.empty()
    v.size()        // vector<int>::size_type // ok
                    // vector::size_type      // error
    v.puch_back(t)  // 在末尾增加一个值为t的元素
    v[n]            // 通过下标赋值时不会添加任何元素
                    // 仅能对确知已存在的元素进行下标操作
    v1 = v2
    v1 == v2
    !=, <, <=, >, >=
    ```
    ```c++
    for (vector<int>::size_type i = 0; i < ivec.size(); i++)
        {   ivec[i];    }   // 遍历vector对象

    // 操作string类型的vector容器时，将vector的字符串元素视为数组进行操作
    // 如ivec[0] = "this", ivec[1] = "is"
    // ivec[0][0] = 't'
    // 转换为大写, 组个元素操作
    // ivec[0][0] = toupper(ivec[0][0])
    ```

### 3.4 迭代器简介
- 所有的标准库容器都定义了相应的迭代器类型，只有少数的容器支持下标操作
- 更倾向于使用迭代器而不是下标操作
1. 容器的`iterator`类型
    ```c++
    // vector的迭代器
    vector<int>::iterator iter; // 定义了iter变量
                    // 其数据类型是由vector<int>定义的iterator类型
    ```
2. `begin`和`end`操作
    ```c++
    // vector的迭代器
    vector<int>::iterator iter=ivec.begin;
            // 把iter初始化为由名为begin的vector操作返回的值
            // 假设vector不空，初始化后，iter则指向该元素为ivec[0]
        // 由end操作返回的迭代器指向vector的“末端元素的下一个”；
        // 不指向任何实际元素，只是作为一个哨兵（sentinal）；表示已处理完元素
    ```
3. `vector`迭代器的自增和解引用运算
    - 定义了一些操作来获取迭代器所指向的元素，并允许程序员将迭代器从一个元素移动到另一个元素
    - 迭代器类型可使用**解引用操作符**（*操作符）来访问迭代器所指向的元素
        ```c++
        *iter = 0;  // 解引用操作符返回迭代器所指向的元素
                    // 这个语句是把这个元素赋值为0
        ```
    - 迭代器使用自增操作符向前移动迭代器指向容器中下一个元素
    - 由于end操作返回的迭代器不指向任何元素，因此不能对它进行解引用或自增操作
4. 迭代器的其它操作
    - 比较：用==或!=操作符来比较两个迭代器，如果指向同一个元素则相等，否则不等
5. 迭代器的程序示例
    ```c++
    vector<int> ivec;   // 声明一个vector<int>型的ivec变量
    // 把所有元素重置为0
    // 1. 使用下标操作
    for (vector<int>::size_type ix = 0; ix != ivec.size(); ++ix)
        ivec[ix] = 0;
    // 2. 使用迭代器
    for (vector<int>::iterator iter = ivec.begin(); iter != ivec.end(); ++iter)
        *iter = 0;  // 用解引用操作来访问当前元素的值
    ```
6. const_iterator
    - 该类型只能用于读取容器内元素，但不能改变其值
    - 对普通iterator类型解引用时，得到对某个元素的非`const`引用；
    - 对const_iterator类型解引用时，则得到一个指向`const`对象的引用。
        - 迭代器自身的值可以改变，但不能用于改变其指向的值
        - 可以对迭代器进行自增以及使用解引用操作符来读取值，但不能对该元素赋值

### 3.5 标准库bitset类型
```c++
#include <bitset>   // 包含相关头文件；是一个类模板，与vector的区别仅在于长度

using std::bitset;  // using声明
```
1. bitset对象的定义和初始化
    - 在定义时，要明确bitset含有多少位，须在尖括号内给出其长度值
        ```c++
        bitset<32> bitvec;      // 32位，全为0；长度须为常量表达式
        ```
    - 初始化方法
        ```c++
        bitset<n> b;    // b有n位，每位都为0
        bitset<n> b(u); // b是unsigned long型u的一个副本
        bitset<n> b(s); // b是string对象s中含有的位串的副本；读入顺序从右向左
        bitset<n> b(s, pos, u); // b是s中从位置pos开始的n个位的副本
        ```
2. bitset对象上的操作
    - 以0位开始的位串是低阶位
    ```c++
    b.any()     // 是否存在置为1的二进制位？
    b.nono()    // 不存在置为1的二进制位吗？
    b.count()   // 置为1的二进制位的个数；返回类型是标准库中名为size_t的类型
    b.size()    // 二进制位的个数
    b[pos]      // 访问b中在pos处的二进制位
    b.test(pos) // 在pos处的二进制位是否为1？
    b.set()     // 把所有二进制位都置为1
    b.set(pos)  // 把在pos处的二进制位置为1
    b.reset()   // 把所有二进制位都置为0
    b.reset(pos)// 把在pos处的二进制位置为0
    b.flip()    // 所有二进制位逐位取反
    b.flip(pos) // 在pos处的二进制位取反
    b.to_ulong()// 用同样的二进制位返回一个unsigned long值；
                // 包含的二进制位数应小于或等于unsigned long的长度
    os << b     // 把b中的位集输出到os流
    ```

## 第四章 数组和指针
- 数组和指针是两种类似于`vector`和迭代器类型的低级复合类型
- 相较于`vector`，数组长度固定，且一经创建就不允许添加新的元素
- 应经量使用`vector`和迭代器类型，而避免使用低级的数组和指针
- 设计良好的程序只有在强调速度时才在类实现的内部使用数组和指针
### 4.1 数组
- 数组是由类型名、标识符和维数组成的复合数据类型
1. 定义和初始化
    - 数组的维数必须用值大于等于1的常量表达式定义
    - 此常量表达式只能包含整型字面值常量、枚举常量或者用常量表达式初始化的**整型`const`对象**
    - 非`const`变量预计要到运行阶段才知道其值得`const`变量都不能用于定义数组维数
    ```c++
    const unsigned array_size = 3;
    int vals[array_size] = {0, 1, 2};   // 显式初始化
    ```
    - 在函数体外定义得内置数组，其元素均初始化为0
    - 在函数体内定义得内置数组，其元素均无初始化
    - 如果元素为类类型，则默认调用默认构造函数初始化；如果没有默认构造函数，则必须显式初始化
    - 字符串字面值包含一个额外得空字符（null）用于结束字符串
    - 不允许数组直接复制和赋值
2. 数组操作
    - 数组下标的正确类型是size_t
    - 必须保证下标值在正确范围之内

### 4.2 指针的引入
- 指针是指向某种类型对象的复合数据类型，是**用于数组的迭代器**：指向数组中的一个元素
- 对指针进行解引用操作，可获得该指针所指对象的值
1. 什么是指针
    - 指针保存的是另一个对象的地址
    ```c++
    string s("hello world");
    string *sp = &s;    // *sp中的*操作符表明sp是一个指针
            // &s中的&符号是取地址（dress-of）操作符；返回的是该对象的存储地址；只能用于左值
    ```
    - 尽量避免使用指针和数组（容易产生不可预料的错误）
2. 指针的定义和初始化
    - 每个指针都有一个与之关联的数据类型
    - 用*符号把一个标识声明为指针
    ```c++
    string *pstring;
    string *ps1, *ps2;  // 推荐这种写法
    string* ps;     // 合法但容易误导
    ```
    - 指针可能的取值
        - 保存一个特定对象的地址
        - 指向某个对象后面的另一个对象
        - 0值；表示其不指向任何对象
    - void*指针，可保存任何类型对象的地址：
        - 与另一个指针进行比较
        - 向函数传递该指针或从函数返回void*指针
        - 给另外一个void*指针赋值
        - 不允许使用void*指针操纵它指向的对象
3. 指针操作
- 对指针进行解引用可访问它所指的对象，*操作符（解引用操作符）将获取指针所指的对象
    ```c++
    string s("hello world");
    string *sp = &s;    // sp保存了s的地址
    cout << *sp;        // 打印hello world
    ```
    - 生成左值的解引用操作
        - 解引用操作符返回指定对象的左值，利用这个功能可修改指针所指对象的值
        ```c++
        *sp = "goodbye";    // 因为sp指向s，所以给*sp赋值也就修改了s的值
        ```
        - 也可以修改指针sp本身的值，使sp指向另外一个新对象
        ```c++
        string s2 = "some value";
        sp  = &s2;  // sp指向s2
        ```
    - 指针和引用的比较
        ```c++
        // 指针
        int ival = 1024, ival2 = 2048;
        int *pi = &ival, *pi2 = &ival2;
        pi = pi2;   // pi现在指向ival2
        ```
        ```c++
        // 引用
        int &ri = ival, &ri2 = ival2;
        ri = ri2;   // 将ival2赋值给ival
        ```
    - 指向指针的指针
4. 使用指针访问数组元素
- 在表达式中使用数组名时，该名字会自动转换为指向数组第一个元素的指针
    ```c++
    int is[] = {0, 2, 4, 6, 8};
    int *ip = ia;   // ip指向ia[0]
    ip = &ia[4];    // 使ip指向ia最后一个元素
    ```
    - 指针的算术操作
        ```c++
        // 加法
        ip = ia;
        int *ip2 = ip + 4;  // ip2指向ia[4]
        ```
        ```c++
        // 减法
        ptrdiff_t n = ip2 - ip;  // 两指针之间额差距
        ```
    - 输出数组元素
        ```c++
        const size_t arr_sz = 5;
        int int_arr[arr_sz] = {0, 1, 2, 3, 4};
        // pend指向超出末端的位置；是for循环结束的哨兵
        for (int *pbegin = int_arr, *pend = int_arr + arr_sz;
                    pbegin != pend; ++pbegin)
            cout << *pbegin << ' ;
        ```
5. 指针和`const`限定符

### 4.3 C风格字符串
- 以空字符null结束的字符数组
1. C风格字符串的使用
    - 通过`(const) char*`类型的指针来操纵C风格字符串
    - 遍历时每次对指针进行测试并递增1，直到到达结束符null为止
2. 标准库函数
    ```c++
    #include <cstring>
    ```
    ```c++
    strlen(s)           // 返回长度，不包括字符串结束符null
    stecmp(s1, s2)      // 比较，s1大于s2则返回正数
    strcat(s1, s2)      // 将s2连接到s1后， 并返回s1
    strcpy(s1, s2, n)   // 将s2复制给s1， 并返回s1
    strncat(s1, s2, n)  // 将s2的前n个字符连接到s1后面， 并返回s1
    strncpy(s1, s2, n)  // 将s2的前n个字符复制给s1， 并返回s1
    ```
1. 创建动态数组
    数组类型的变量有三个重要的限制：
    + 数组长度不变
    + 在编译时必须知道其长度
    + 数组旨在定义它的块语句内存在
    C语言程序使用一对标准库函数malloc和free在自由存储区中分配储存空间
    C++语言则使用**new**和**delete**表达式实现相同的功能

- 动态分配数组时，只需要指定类型和数组长度，不必为数组对象命名，new表达式返回指向新分配数组的第一个元素的指针：
    ```c++
    int *pia = new int[10]; // 10个未初始化的int类型元素组成的数组
        // new表达式需要指定指针类型以及在方括号中给出数组维数，该维数可以是任意的复杂表达式
        // 在自由存储区创建的数组对象没有名字
    ```
- 初始化动态分配的数组
    ```c++
    // 如果数组元素具有类类型，将使用该类的默认构造函数
    // 如果数组元素是内置类型，则无初始化
    string *psa = new string[10];   // array of 10 empty strings
    int *pia = new int[10];         // array of 10 uninitiated ints
    // 也可使用跟在数组长度后面的一队空圆括号，对数组元素做值初始化
    int *pia2 = new int[10]();      // array of 10 uninitiated ints; 元素全置为0
    ```
- `const`对象的动态数组
    ```c++
    // error: uninitated const array
    const int *pci_bad = new const int[100];
    // ok: value-initated const array
    const int *pci_ok = new const int[100]();
    // ok: array of 100 empty strings
    // C++允许定义类类型的const数组，但该类类型必须提供默认构造函数
    const string *pcs = new const string[100];
    ```
- 允许动态分配空数组
    ```c++
    size_t n = get_size();
    int *p = new int[n];
    for (int *q = p; q != p + n; ++q)
        /* process the array */;
    ```
- 动态空间的释放
    ```c++
    delete [] pia;  // 释放指针所指向的数组空间；[]不可少
    ```
- 新旧代码的兼容

### 4.4 多维数组
```c++
ia[2][3]    // 2行，3列
```
1. 多维数组的初始化
    ```c++
    int ia[3][4] = { {0, 1, 2, 3},
                     {4, 5, 6, 7},
                     {8, 9, 10, 11}};    // 3行，4列
    int ia[3][4] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}; // 也可以
    ia[2]   // 表示第三行
    ```
2. 指针和多维数组
    ```c++
    int ia[3][4];
    int (*ip)[4] = ia;  // ip指向一个含有4个int值的数组
    ip = &ia[2];        // 
3. 用typedef简化指向多维数组的指针
    ```c++
    typedef int int_array[4];
    int_array *ip = ia;
    ```

## 第五章 表达式

### 5.1 算术操作符
```c++
// 一元操作符优先级最高，其次是乘、除，接着是二元的加减法
+       // 一元正号
-       // 一元负号
*       // 乘法
/       // 除法
%       // 求余
+       // 加法
-       // 减法
```

### 5.2 关系操作符和逻辑操作符
```c++
// 使用算术或指针类型的操作数，并返回bool类型的值
！      // 逻辑非
<       // 小于
<=      // 小于等于
>       // 大于
>=      // 大于等于
==      // 相等
!=      // 不等
&&      // 逻辑与
||      // 逻辑或
```
- 逻辑与一个很有价值的用法是：如果边界条件使expr2的计算变得危险，则应该在该条件出现之前，先让expr1的计算结果为false

### 5.3 位操作符
```c++
~       // 位求反
<<      // 左移
>>      // 右移
&       // 位与
^       // 位异或；两个操作数只有一个（不是两个）为1，则结果为1，否则为0
|       // 位或
```
1. bitset对象或整型值的使用
    ```c++
    // 储存30个学生测验是否通过
        // 使用bitset类型
    bitset<30> bitset_quiz1;    // bitset solution
        // 使用内置类型
    unsigned long int_quiz1 = 0;    // simulated collection of bits; by default 0

    // 将第27位置1
        // 直接传递要置位的位
    bitset_quiz1.set(27);       // indicate student number 27 passed
        // 先获得一个第27位为1，其它为0的数；再经行位或操作
    int_quiz1 |= 1UL<<27;       // indicate student number 27 passed

    // 将第27位置0
    bitset_quiz1.reset(27);     // indicate student number 27 failed
        // 先获得一个第27位为0，其它为1的数；再经行位与操作
    int_quiz1 &= ~(1UL<<27);    // indicate student number 27 failed
    ```
2. 将一位操作符用于IO
    - 输入输出标准库（IO library）分别重载了位操作符>>和<<用于输入和输出

### 5.4 赋值操作符
- 复合赋值操作符
    ```c++
    a op= b;    // op=可以是下列10个操作符之一
    += -= *= /= %=  // 算术运算符
    <<= >>= &= ^= |=    // 位运算符
    ```

### 5.5 自增和自减操作符
- 自增（++）和自减（--）
    ```c++
    int i = 0, j;
    j = ++i; // j = 1, i = 1;获得自增后的值
    j = i++; // j = 1, i = 2;获得未自增的值，再加一

    *iter++ //等效于*(iter++)
            //子表达式iter++使iter加1，然后返回iter原值得副本作为该表达式得结果
    ```

### 5.6 箭头操作符
- ->  : 位包含点操作和解引用操作符的表达式提供了一个同义词

    ```c++
    // 点操作符
    item1.same_isbn(item2); // run the same_isbn member of item1
    // 如果有一个指向Salse_item对象的指针（或迭代器），则在使用点操作符前，需对该指针进行解引用
    Sales_item *sp = &item1;
    (*sp).same_isbn(item2); // run same_isbn object to which sp points

    // 使用箭头操作符
    sp->same_isbn(item2);   // 等价于(*sp).same_isbn(item2)
    ```

### 5.7 条件操作符

```c++
cond ? expr1 : expr2;
// 例子
int i = 10, j = 20, k = 30;
int maxVal = i > j ? i : j;

cout << (i > j ? i : j);    // 应用圆括号括起来
```

### 5.8 sizeof操作符
```c++
// 返回一个对象或类型名的长度，返回值的类型位size_t
// 三种语法形式
sizeof (type_name);
sizeof (expr);
sizeof expr;    // 并没有计算过expr的值
```

### 5.9 逗号操作符
- 一组由逗号分隔的表达式，从左向右计算

### 5.10 复合表达式的求值
- 具体顺序见书本

### 5.11 new和delete表达式
- 只需指定其数据类型，而不必为该对象命名
- new表达式返回指向新创建对象的指针，通过该指针可以访问此对象
1. 动态创建对象的初始化
    ```c++
    int i(1024);        // 
    int *pi = new int(1024);    // object to which pi points is 1024
    string s(10, '9');  // s值为“9999999999”
    string *ps = new string(10, '9');   // *ps为“9999999999”
    ```
2. 动态创建对象的默认初始化
    ```c++
    // 如果不提供显式初始化，动态创建的对象与在函数内定义的变量初始化方式相同（见2.3.4）
    // 对于类类型的对象，用该类的默认构造函数初始化；而内置类型的对象则无初始化
    string *ps = new string;    // 初始化为空string
    int *ps = new int;  // pi指向一个未初始化的int
    ```
    ```c++
    // 也可对动态创建的对象做值初始化
    string *ps = new string();  // 初始化为空string
    int *pi = new int();    // pi指向一个被初始化为0的int值
    cls *pc = new cls();    // pc指向一个用值初始化的cls类型对象
    ```
3. 耗尽内存
    - 系统将抛出bad_alloc异常
4. 撤销动态创建的对象
    ```c++
    // 必须显式地将动态创建的对象占用的内存返回给自由存储区
    delete pi;
    ```
5. `const`对象的动态分配和回收
    ```c++
    //分配和初始化一个const对象
    const int *pci = new const int(1024);   // 动态创建的const对象必须在创建时初始化
                // 且一经初始化，其值就不能再修改
    
    // 对于类类型的const动态对象，如果该类提供了默认的构造函数，则此对象可隐式初始化
    const string *pcs = new const string;

    // 删除const对象
    delete pci;
    ```

### 5.12 类型转换
- 左操作数的类型占主导地位
1. 何时发生隐式类型转换
    - 在混合类型的表达式中
        ```c++
        int ival;
        double dval;
        ival >= dval;   //ival converted to double
        ```
    - 用作条件的表达式被转换为bool类型
        ```c++
        int ival;
        if (ival)   // ival converted to bool
        while (cin) // cin converted to bool
        val1 ? expre1 : expre2  // 第一个操作数为条件表达式
        ！  &&  ||  // 的操作数均为条件表达式 
        ```
    - 用一表达式初始化某个变量，或将一表达式赋值给某个变量，则该表达式被转换为该变量的类型
        ```c++
        int ival = 3.14;    // 3.14 converted to int
        int *ip;
        ip = 0;     // the int 0 converted to a null pointer of type int *
        ```
2. 算术转换
    - 有符号与无符号类型之间的转换
    - 理解算术转换
        >将操作数转换为表达式中的最大类型
        >将右操作数转换为做操作数的类型
3. 其它隐式转换
    - 指针转换
        >使用数组时，大多数情况下数组都会自动转换为指向第一个元素的指针
        >不将数组转换为指针的例外情况有：
        >   >数组用作取地址（`&`）或`sizeof`操作符的操作数
        >   >用数组对数组的引用进行初始化时
        >
        >指向任意数据类型的指针都可以转换为`void*`类型
        >整型数值常量0可转换为任意指针类型
    - 转换为`bool`类型
        >算术值和指针值都可以转换为`bool`类型
    - 算术类型与`bool`类型的转换
        >false/0 true/1
    - 转换与枚举类型
    - 转换为`const`对象
        ```c++
        int i;
        const int ci = 0;
        const int &j = i;   // ok: convert non-const to reference to const int
        const int *p = &ci; // ok: convert address of non-const to address of a const
        ```
    - 由标准库类型定义的转换；如iostream
4. 显式转换
    - 强制类型转换操作符：static_cast, dynamic_cast, const_cast, reinterpret_cast
5. 何时需要强制类型转换
    ```c++
    double dval;
    int ival;
    ival *= dval;   //先将ival转换为double型，然后将乘法操作的double型结果截尾为int型赋给ival
    ival *= static_cast<int>(dval); // converts dval ot int
    ```
6. 命名的强制类型转换
    ```c++
    cast-name<type>(expression)

    dynamic_cast    // 运行时识别指针或引用所指向的对象
    
    const_cast      // 转换掉表达式的const性质
    /* 修改，使其接受const char* 类型的参数 */
    const char *pc_str;
    char *pc = string_copy(const_cast<cahr*>(pc_str));  

    static_cast     // 隐式执行的任何类型转换都可以由其显式完成
    /* 实例 */
    double d = 97.0;
    char ch = static_cast<char>(d); // 表明转换为刻意完成
    void* p = &d;   // ok，任何类型数据均可以存入void*
    double *dp = static_cast<double*>(p);   // ok，将void*转换回原来的指针类型

    reinterpret_cast    // 通常为操作数的位模式提供较低层次的重新解释
    /* 实例 */
    int *ip;
    char *pc = reinterpret_cast<char*>(ip); // pc所指向的真实对象是int型，而并非字符数组
    ```
7. 旧式强制类型转换
    ```c++
    char *pc = (char*) ip;  // 可视性较差，难以最终错误的转换

    // 有下列两种类型
    type (expr);
    (type) expr;
    ```

## 第六章 语句
### 6.1 简单语句

```c++
ival + 5;
;       // 空语句
```

### 6.2 声明语句

### 6.3 复合语句（块）
- 一对花括号括起来的语句序列（可以是空的）
- 标识了一个作用域，在块中引入的名字只能在该块内部或者嵌套在块中的子块里访问
- 通常，一个名字只从其定义处到该块的结尾这段范围内可见

### 6.4 语句作用域
- 一个在控制结构里引入的名字是该语句的局部变量，其作用域局限在语句内部

### 6.5 if语句
```c++
if (condition)  // 如果定义了变量，那么变量必须初始化
    statement   // 可以是复合语句，即用花括号括起来的块语句

// if语句的else分支
if (condition)
    statement1
else if (contition)
    statement2
else
    statement3
```

### 6.6 switch语句
```c++
switch (arg){
    case label1:
        statement1;
        break;
    case label2:
        statement2;
        break;
    case label3: case label4: case label5:  // 向下执行多个case标号
        statement2;
        break;
    default:
        statement2;
        break;
}
```

### 6.7 while语句
```c++
while (condition)
    statement
```

### 6.8 for循环语句
```c++
for (init-statement condition; expression)  // 初始化语句必须是声明语句、表达式语句或空语句
    statement
// 也可以看成是
for (initializer; condition; expression)
    statement
```
- 省略condition，则等效于循环条件永远为true
- 如果省略expression，则必须利用break或return语句跳出循环

### 6.9 do while语句
```c++
do  // 至少执行一次
    statement
while (condition)
```

### 6.10 break语句
- 用于结束最近的while、do while、for或switch语句

### 6.11 continue语句
- 导致最近的循环语句的单次迭代提前结束
    - 对于while和do while语句，继续求解循环条件
    - 对于for循环，程序流程接着求解for语句中的expression
- 只能出现在for、while或者do while循环中，包括嵌套在这些循环内部的块语句中

### 6.12 goto语句
- 提供函数内部的无条件跳转，实现从goto语句跳转到同一函数内某个带标号的语句
- 尽量避免使用

### 6.13 try块和异常处理

### 6.14 使用预处理器进行调试

## 第七章 函数
### 7.1 函数的定义
- 形参：parameter
- 实参：argument
- 函数体是一个作用域：在函数体内定义的变量只在该函数中才可以访问，即局部变量。
- 形参和实参：形参为函数提供已命名的局部储存空间；并由调用函数时传递给函数的实参初始化
1. 函数返回类型
- ***函数必须显式指定返回类型***
    - 返回类型可以是内置类型（如int或者double）、类类型或者复合类型（如int&或string*），还可以是void类型，表示函数不返回任何值。
    - 函数不能返回另一个函数或者内置数组类型，但可以返回指向函数的指针，或指向数组元素的指针的指针：
        ```c++
        int *foo_bar () { /*...*/}  // 返回一个int型指针，该指针可以指向数组中的一个元素
        ```
2. 函数形参表
    - 函数形参表可以为空，但不能省略
    - 如果两个参数具有相同的类型则其类型必须重复声明

### 7.2 参数传递
1. 非引用形参
    - 通过复制对应的实参实现初始化
    - 当用实参副本初始化形参时，函数并没有访问调用所传递的实参本身，因此**不会修改实参的值**
    1. 指针形参
        - 函数的形参也可以是指针，此时将复制实参指针
        - 该类形参的任何改变也仅作用于局部副本
        - 如果函数形参是非`const`类型的指针，则函数可通过指针实现赋值，修改指针所指向的对象
        ```c++
        void reset(int *p)
        {
            *ip = 0;    // 改变ip指向的对象的值
            ip = 0;     // 仅改变局部变量ip的值，实参数值不变
        }

        int i = 42;
        int *p = &i;
        cout << "i: " << *p << '\n';    // points i: 42
        reset(p);   // changes *p but not p
        cout << "i: " << *p << endl;    // ok: points i: 0

        // 如需保护指针指向的值，则形参需定义为指向`const`对象的指针
        void use_ptr(const int *p)
        {
            // use_ptr may read but not write to *p
        }
        ```
    2. `const`形参
        - 在调用函数时，如果该函数使用非引用的`const`形参/非`const`形参，则既可给该函数传递`const`实参也可传递非`const`的实参。
        - 如果该函数使用非引用的`const`形参，不可以改变实参的局部副本。
    3. 复制实参的局限性
        - 不适宜复制实参的情况
            - 当需要在函数中修改实参的值时
            - 当需要以大型对象作为实参传递时
            - 当没有办法实现对象的复制时
        - 对于上述情况，有效的办法时将形参定义为引用或指针类型
2. 引用形参
    ```c++
    // 要交换实参的值，需要将形参定义为引用类型
    // 通过交换引用的值来交换
    void swap(int &va, int &v2)
    {
        int tmp = v2;
        v2 = v1;
        v1 = tmp;
    }
    ```
    1. 使用引用形参返回额外的信息
        ```c++
        // 定义既返回一个迭代器又返回出现次数的函数
        // 返回一个指向value第一次出现的迭代器
        // 引用形参occurs包含有第二个返回值
        vector<int>::const_iterator find_val(
            vector<int>::const_iterator beg,    // first element
            vector<int>::const_iterator end,    // one past last element
            int value,                          // 查询值
            vector<int>::size_type &occours)    // 出现次数
        {
            // 如果存在，res_iter将保存第一次出现的位置
            vector<int>::const_iterator res_iter = end;
            occours = 0;    // 设置出现次数形参
            for (; beg != end; ++beg)
                if (*beg == value){
                    //remember first occurrence of value
                    if (res_iter == end)
                        res_iter = beg;
                    ++occurs;   // increment occurrence count
                }
            return res_iter;    // count returned implicity in occurs
        }

        // 如果：ivec是vector<int>类型对象，it是一个适当类型的迭代器，而ctr则是size_type类型的变量
        it = find_val(ivec.gebin(), ivec.end(), 42, ctr)
        ```
    2. 利用`const`引用避免复制
        ```c++
        // 比较两个字符串长度
        bool isShorter(const string &s1, const string &s2)
        {
            return s1.size() < s2.size();
        }
        ```
    3. 更灵活的指向`const`的引用
    4. 传递指向指针的引用
        ```c++
        // 获得指向指针的引用
        // 通过交换指针来交换值
        void ptrswap(int *&va, int *&v2)   // v1是一个引用，与指向int型对象的指针相关联
                                        // v1只是传递进ptrswap函数的任意指针的别名
        {
            int *tmp = v2;
            v2 = v1;
            v1 = tmp;
        }
        ```
3. `vector`和其他类型的形参
    ```c++
    // 通过传递指向容器中需要处理的元素的迭代器来传递容器
    void print(vector<int>::const_iterator beg, vector<int>::const_iterator end)
    {
        while (beg != end)
        {
            cout << *beg++;
            if (beg != end) cout << " ";    // 最后一个元素后无空格
        }
        cout << endl;
    }
4. 数组形参
    - 数组不能复制，所以无法编写使用数组类型形参的函数
    - 使用数组名时，数组名会自动转化为指向其第一个元素的指针
    1. 数组形参的定义
        ```c++
        // 三种方式指定数组形参
        // 虽然不能直接传递数组，但是函数的形参可以写成数组的形式
        // 可以把下面形参看作指向数组元素类型的指针；三者等价，形参类型都是int *
        void printValues(int *) { /*...*/}
        void printValues(int []) { /*...*/}
        void printValues(int [10]) { /*...*/}
        ```
    2. 形参的长度会引起误解
        - 编译器会忽略为任何数组形参指定的长度
        - 长度不同时，编译没问题，但是调用错误，可能导致运行失败
    3. 数组实参
        - 传递数组时，实参是指向数组第一个元素的指针，形参复制的是这个指针的值，而不是数组元素本身
        - 通过指针形参做的任何改变都在修改数组元素本身
        ```c++
        // 不需要修改数组形参的元素是，函数应该将形参定义为指向const对象的指针
        void f(const int*) { /*...*/}   // f不改变数组元素的值
        ```
    4. 通过引用传递数组
        - 如果形参是数组的引用，编译器不会将数组实参转化为指针，而是传递数组的引用本身
        - 此时，数组大小成为形参和实参类型的一部分；会检查数组实参大小与形参大小是否匹配
        ```c++
        // &arr两边的圆括号是必需的，因为下表操作具有更高的优先级
        void printValues(int (&arr) [10]) { /*...*/}
        ```
    5. 多维数组的传递
        - 多维数组的元素本身就是数组
        - 除了第一维以外的所有维的长度都是元素类型的一部分，必须指明
        ```c++
        // 将matrix声明为指向含有10个int型元素的数组的指针
        void printValues(int (*matrix)[10], int rowSize);
        // 形参是一个指针，指向数组的数组中的元素
        void printValues(int matrix[][10], int rowSize);
        ```
5. 传递给函数的数组的处理
    1. 使用标准库规范
    2. 显式传递表示数组大小的形参
6. main: 处理命令行选项
7. 含有可变形参的函数

### 7.3 return语句
```c++
return;
return expression;
```
1. 没有返回值的函数
    - 只能用于返回类型为void的函数
2. 具有返回值的函数
    - 任何返回类型不是void的函数都必须返回一个值，而且类型必须和函数的返回类型相同
3. 递归
    ```c++
    // 必须定义一个终止条件
    int factorial(int val)
    {
        if (val > 1)
            return factorial(val-1) * val;
        return 1;
    }

    int rgcd(int v1, int v2)
    {
        if (v2 != 0)    // 当v2为零的时候停止
        {
            return rgcd(v2, v1%v2); // 递归，每次调用都减小v2
        }
        return v1;
    }
    ```
    
### 7.4 函数声明
- 函数也必须在被调用之前先声明
- 函数声明有函数返回类型、函数名和形参列表组成
- 形参列表必须包括形参类型，但是不必对形参命名

### 7.5 局部对象
- static静态局部对象
- 在该函数被多次调用的过程中，静态局部对象会持续存在并保持它的值

### 7.6 内联函数
- 调用函数会增加额外的开销


1. 内联函数避免函数调用的开销
    - 在函数返回类型前加上关键字inline即可
        ```c++
        inline const string &shorterString(const string &s1, const string &s2)
        {
            return s1.size() < s2.size() ? s1 : s2;
        }
        ```
2. 把内联函数放入头文件

### 7.7 类的成员函数
- 函数原型必须在类中定义
- 函数体既可以在类中也可以在类外定义
1. 定义成员函数的函数体
    ```c++
    bool same_isbn(const Sales_item &rhs) const
        {   return isbn == rhs.isbn;    }
    ```
    - 编译器隐式地将在类内定义的成员函数当作内联函数
    - 类的成员函数可以访问该类的private成员
    1. 成员函数含有额外的、隐含的形参
        ```c++
        if (total.same_isbn(trans))
        // trans初始化形参rhs
        // 没有前缀的isbn使用了相同的实参绑定过程，绑定名为total的对象。
        ```
    2. `this`指针的引入
        - 每个成员函数都有一个额外的、隐含的形参this；类似于python里的self
        ```c++
        total.same_isbn(trans); // 我们写的
        Sale_item::same_isbn(&total, trans);    // 伪码，编译器调用时的情形
        ```
    3. `const`成员函数的引入
        - `const`改变了隐含的`this`形参的类型
        - 在调用`total.same_isbn(trans)`时，隐含的`this`形参将是一个指向`total`对象的`const Sales_Item*`类型的指针
        ```c++
        // 伪码，用来解释this指针如何使用
        // this是一个指向const的指针，因为same_isbn为const成员
        bool Sales_item::same_isbn(const Sales_item *const this,
                                    const Sales_item &rhs)  const
            {   return (this->isbn == rhs.isbn);    }
        ```
        - 用这种方法使用`const`的函数称为**常量成员函数**
        - 由于`this`是指向`const`对象的指针，`const`成员函数不能修改调用该函数的对象。
    4. `this`指针的使用
        - 在成员函数中，不必显式地使用`this`指针来访问被调用函数所属对象的成员
2. 在类外定义成员函数
    ```c++
    // 1. 说明现在正在定义类Sales_item的函数avg_price
    // 2. 且为一个const成员函数
    // 3. 没有（显式的）形参，返回double类型的值
    double Sales_item::avg_price() const
    {
        if (units_sold)
            return revenuw/units_sold;
        else
            return 0;
    }
    ```
3. 编写Sales_item类的构造函数
    1. 构造函数是特殊的成员函数
        - 构造函数和类同名，且没有返回类型
        - 也有形参表和函数体
        - 一个类可以有多个构造函数，每个构造函数必须有与其它构造函数不同数目或类型的形参
        - 构造函数的形参指定了创建类类型对象时使用的初始化式（用于初始化新创建对象的数据成员）
    2. 构造函数的定义
        - 构造函数同样必须在类中声明，但可以在类中或类外定义
        ```c++
        class Sales_item{
        public:
            // 操作Sales_item对象
            double avg_price() const;
            bool same_isbn(const Sales_item &rhs) const
                {   return isbn == rhs.isbn;    }
            // 默认构造函数，用于初始化内置类型的成员
            Sales_item(): units_sold(0), revenue(0.0)   {} // 形参表和函数体均为空

        private:
            std::string isbn;
            unsigned units_sold;
            double revenue;
        }
    3. 构造函数的初始化列表
        - 在冒号和花括号之间的代码称为**构造函数初始化列表**
        - 为类的一个或多个数据成员指定初值
    4. 合成的默认构造函数
        - 如果没有一个类显式定义任何构造函数，编译器将自动为这个类生成默认构造函数

### 7.8 重载函数
- 相同作用域中的两个函数，如果具有相同的名字而形参表不同，则被称为***重载函数***
- 函数不能仅仅基于不同的返回类型而实现重载
1. 重载与作用域
2. 函数匹配与实参转换
    - 首先是最佳匹配，不然就进行隐式转换，实在没有则报错
3. 重载确定的三个步骤
    1. 候选函数
    2. 选择可行函数
    3. 寻找最佳匹配（如果有的话）
    4. 含有多个形参的重载确定
        - 如果调用有二义性，比如有两个参数，两个重名函数分别匹配其中一个参数类型
        - 可通过显式的强制类型转换强制函数匹配
        ```c++
        f (static_cast<double>(43), 2.56); // calls f(double, double))
        f (43, static_cast<int>(2.56)); // calls f(int, int)
4. 实参类型转换
- 转换等级
    - 精准匹配。实参与形参类型相同
    - 通过类型提升实现的匹配
    - 通过标准转换实现的匹配
    - 通过类类型实现的匹配
    1. 需要类型提升或转换的匹配
        - int型版本优于short型版本的较佳匹配
        - 通过类型提升实现的转换优于其它标准转换
    2. 参数匹配和枚举类型
        - 无法将整型值传递给枚举类型的实参，但可以将枚举值传递给整型形参
    3. 重载和const形参
        - 可基于函数的引用形参是指向const对象还是指向非const对象，实现函数重载。

### 7.9 指向函数的指针
```c++
bool (*pf) (const string &, const string &);    // 将pf声明为指向函数的指针
```
1. 用typedef简化函数指针的定义
    ```c++
    typedef bool (*cmpFcn) (const string &, const string &);
    // 指向返回bool类型并带有两个const string引用形参的函数的指针
    // 在要使用这种函数指针类型时，只需直接使用cmpFcn即可
    ```
2. 指向函数的指针的初始化和赋值
    - 在引用函数名但又没有调用该函数时，函数名将被自动解释为指向函数的指针
    ```c++
    bool lengthCompare(const string &, const string &);
    // 对lengthCompare的任何使用都被解释为如下类型的函数：
    bool (*) (const string &, const string &);
    // 可使用函数名对函数指针做初始化赋值
    cmpFcn pf1 = 0; // ok: unbound pointer to function
    cmpFcn pf2 = lengthCompare; // ok: pointer type matches funciton's type
    pf1 = lengthCompare;    // ok: pointer tpe matches function's type
    pf2 = pf1;  // ok: pointer types match
    // 直接引用函数名等效于在函数名上应用区地址操作符
    cmpFcn pf1 = lengthCompare;
    cmpFcn pf1 = &lengthCompare;
    ```
3. 通过指针调用函数
- 指向函数的指针可用于调用它所指向的函数。可以不需要使用解引用操作符，直接通过指针调用函数。
    ```c++
    cmpFcn pf = lengthCompare;
    lengthCompare("hi", "bye"); // direct call
    pf("hi", "bye");    // equivalent call: pf1 implicitly dereferenced
    (*pf)("hi", "bye");    // equivalent call: pf1 implicitly dereferenced
    ```

4. 函数指针形参
    ```c++
    void useBigger(const string &, const string &, bool (const string &, const string &));
    void useBigger(const string &, const string &, bool (*)(const string &, const string &));
    ```

5. 返回指向函数的指针
    ```c++
    int (*ff(int)) (int*, int); // 将ff声明为一个函数，它带有一个int型的形参
                                // 该ff函数返回int (*)(int*, int)

    // 等效于
    typedef int (*PF)(int*, int);
    PF ff(int);
    ```
    - 具有函数类型的形参所对应的实参将被自动转换为指向相应函数类型的指针
        ```c++
        // func为函数类型，不是指向函数的指针
        typedef int func(int*, int);
        void f1(func);  // oK: f1 has a parameter of function type
        func f2(int);   // error: f2 has a return type of funciton type
        func *f3(int);  // ok: f3 returns a pointer to funciton type
            // 等效于
            func (*f3)(int);
        ```
6. 指向重载函数的指针
    - c++语言允许使用函数指针指向重载的函数
    ```c++
    extern void ff(vector<double>);
    extern void ff(unsigned int);

    void (*pf1)(unsinged int) = &ff;    // 指向ff(unsinged)
    ```

## 第八章 标准IO库
- istream输入流类型
- ostream输出流类型
- cin
- cout 
- cerr
- `>>`
- `<<`
- getline函数，需要分别取istream类型和string类型的两个引用形参，其功能是从istream对象读取一个单词，然后写入string对象中

### 8.1 面向对象的标准库
2. IO对象不可复制或赋值

### 8.2 条件状态

### 8.3 输出缓冲区的管理

### 8.4 文件的输入和输出

### 8.5 字符串流



# 第二部分 容器和算法
## 第九章 顺序容器
1. 顺序容器
    - vector    支持快速随机访问
    - list  支持快速插入/删除
    - deque 双端队列
2. 顺序容器适配器
    - stack 后进先出（LIFO）栈
    - queue 先进先出（FIFO）队列
    - priority_queue    有优先级管理的队列
### 9.1 顺序容器的定义
```c++
// 为了定义一个容器类型的对象，必须先包含相关的头文件
#include <vector>
#include <list>
#include <deque>
// 所有的容器都是类模板。要定义某种特殊的容器，必须在容器后加一对尖括号，尖括号里面提供容器中存放的元素类型
vector<string> svec;    // empty vector that can hold strings
list<int> ilist;    // empty list that can hold ints
deque<Sales_item> items;    // empty deque that holds Sales_items
```
1. 容器元素的初始化
    ```c++
    // 容器构造函数
    C<T> c; // C为容器类型名；T为元素类型；c为容器名
    C c(c2);    // 创建容器c2的副本
    C c(begin, end);  // 创建c，其元素是迭代器b和e标示的范围内元素的副本
    C c(n, t);  // 用n个值为t的元素创建容器c，其中值t必须是容器类型C的元素类型的值，或者是可转换为该类型的值
                //只适用于顺序容器
    C c(n);     // 创建有n个值初始化元素的容器c
                //只适用于顺序容器
    ```

### 9.2 迭代器和迭代器范围
```c++
//常用迭代器运算
*iter   // 返回迭代器iter所指向元素的引用
iter->mem   // 对iter进行解引用，获取指定元素中名为mem的成员。等效于(*iter).mem
++iter  // iter加1，并指向下一个元素
iter++
--iter  // iter减1，并指向前一个元素
iter--
iter1 == iter2  // 比较两个迭代器是否相等
iter1 != iter2
```

- vector和deque容器的迭代器提供额外的运算
    ```c++
    iter + n    // 指向前面第n个元素的迭代器
    iter - n
    iter1 += iter2  // 迭代器加减法的复合赋值运算
    iter1 -= iter2
    iter1 - iter2   // 
    >, >=, <, <=

    // 计算vector对象的中点位置
    vector<int>::iterator iter = vec.begin() + vec.size()/2;
    ```

1. 迭代器范围
    - first和last，或者beg和end
    - 左闭合区间 [first, last)
    - 当first与last相等时，迭代器范围为空
    - `first == last`，可作为退出循环
    ```c++
    while (first != last)
    {
        ++first;
    }
    ```

### 9.3 顺序容器的操作
2. begin和end成员
    ```c++
    c.begin()   // 返回一个迭代器，指向容器c的第一个元素
    c.end()     // 返回一个迭代器，指向容器c的最后一个元素的下一个位置
    c.rbegin()  // 返回一个逆序迭代器，指向容器c的最后一个元素
    c.rend()    // 返回一个逆序迭代器，指向容器c的第一个元素前面的位置
    ```

3. 在顺序容器中添加元素
    - 容器元素都是副本
    - 所有顺序容器都支持push_back操作，可在容器尾插入一个元素
    ```c++
    string text_word;
    while (cin >> text_word)
        container.push_back(text_word); // container类型可以是list、vector、deque
    ```
    ```c++
    // 在顺序容器中添加元素的操作
    c.push_back(t)
    c.push_front(t) // 只适用于list和deque容器类型

    c.insert(p, t)      // 在迭代器p所指向的元素前面插入值为t的新元素。返回指向新元素的迭代器
    c.insert(p, n, t)   // 在迭代器p所指向的元素前面插入n个值为t的新元素
    c.insert(p, b, e)   // 在迭代器p所指向的元素前面插入由迭代器b和e标记的范围内的元素
    ```
    - 为避免存储end操作返回的迭代器，可在每次做完插入运算后重新计算end迭代器值
4. 关系操作符
    - 比较的容器必须具有相同的容器类型，而且其元素类型也必须相同
5. 容器大小的操作
    ```c++
    c.size()    // 返回元素个数
    c.max_size()    // 返回可容纳的最多元素个数
    c.empty()   // 返回标记容器大小是否为0的布尔值
    c.resize(n) // 调整容器长度大小，使其能容纳n个元素
    c.resize(n, t)  // 调整容器长度大小，使其能容纳n个元素，所有新添加的元素值都为t
    ```
6. 访问元素
    ```c++
    c.back()    // 返回容器c的最后一个元素的引用
    c.front()   // 返回容器c的第一个元素的引用
    c[n]        // 返回下标为n的元素的引用，只适用于vector和deque容器
    c.at[n]     // 返回下标为n的元素的引用，只适用于vector和deque容器
    ```
7. 删除元素
    ```c++
    // 删除顺序容器内元素的操作
    c.erase(p)  // 删除p所指向元素
                // 返回一个迭代器，指向被删除元素后面的元素
    c.erase(b, e)   // 删除迭代器b和e所标记的范围内所有的元素
    c.clear()       // 删除所有元素
    c.pop_back()    // 删除最后一个元素
    c.pop_front()   // 删除第一个元素
        // 只适用于list或deque容器
    ```
8. 赋值与swap
    ```c++
    c1 = c2         // 产出容器c1的所有元素，然后将c2的元素复制给c1
    c1.swap(c2)     // 交换内容；二者类型必须想相同
    c.assign(b, e)  // 重新设置c的元素；将迭代器b和e标记的范围内所有的元素复制到c中
    c.assign(n, t)  // 将容器c重新设置为储存n个值为t的元素
    ```

### 9.4 vector容器的自增长
- 一般而言，使用list容器优于vector容器。
- 对于大部分应用，使用vector容器是最好的。
    ```c++
    vector<int> ivec;
    ivec.capacity();    // 输出为 ivec: size: 24 capacity:32

    ivec.reserve(50);   // 设置容量
    ```

### 9.5 容器的选用
1. 插入操作如何影响容器的选择
    - list表示不连续的内存区域，不支持随机访问
    - vector，除首尾外，其它任何位置插入或删除元素都需要移动其右边的元素
    - deque拥有更复杂的元素，两端插入删除较快，但是中间元素的操作很复杂
2. 元素的访问如何影响容器的选择
    - vector和deque都支持对元素实现高效的随机访问
    - list只能逐个遍历
3. 选择容器的提示
    - 如果程序要求随机访问元素，则应使用vector或deque容器
    - 如果程序必须在中间插入或删除元素，则应采用list容器
    - 如果程序不是在容器的中间位置，而是在容器首部或尾部插入或删除元素，则应采用deque容器
    - 如果只需在读取输入时在容器中位置插入元素，然后需要随机访问元素，则可考虑在输入时将元素读入到一个list容器，接着对此容器重新排序，最后复制到一个vector容器

### 9.6 再谈string类型
```c++
string s;   // 新建空string
string s(cp);   // 定义一个新string，用cp所指向的字符串来初始化该对象
string s(s2);   // 定义一个新string对象，并将它初始化为s2的副本
is >> s;        // 从输入流is中读取一个以空白字符分隔符的字符串，写入s
os << s;        // 将s写道输出流os中
getline(is, s)  // 从输入流is中读取一行字符，写入s
s1 + s2         // 把s1和s2串接起来，产生一个新的string对象
s1 += s2        // 把s2拼接到s1后面
关系操作符      // == != < <= > >=
```

1. 构造string对象的其它方法
    ```c++
    string s(cp, n)
    string s(s2, pos2)          // 从pos2开始的元素
    string s(s2, pos2, len2)    // 从pos2开始的len2个元素
    ```
2. 修改string对象的其它方法
    ```c++
    s.insert(p, t)      // p之前插入一个值为t的新元素
    s.insert(p, n, t)
    s.insert(p, b, e)

    s.assign(b, e)      // 用b, e之间的元素替换s
    s.assign(n, t)

    s.erase(p)
    s.arase(b, e)

    // string类型特有的版本
    s.insert(pos, n, c)
    s.insert(pos, s2)
    s.insert(pos, s2, pos2, len)
    s.insert(pos, cp, len)
    s.insert(pos, cp)

    s.assign(s2)
    s.assign(s2, pos2, len)
    s.assign(cp, len)
    s.assign(cp)

    s.erase(pos, len)
    ```
3. 只适用于string类型的操作
    ```c++
    s.substr(pos, n)
    s.substr(pos)
    s.substr()

    s.append(args)
    s.replace(pos, len, args)
    s.replace(b, e, args)

    // append和replace操作的参数：args
    s2
    s2, pos2, len2
    cp              // 该指针指向以空字符结束的数组
    cp, len2
    n, c
    b2, e2
    ```
4. string类型的查找操作
    ```c++
    s.find(args)
    s.rfind(args)       // 最后一次出现
    s.find_first_of(args)
    s.find_last_of(args)
    s.find_first_not_of(args)
    s.find_last_not_of(args)

    // find操作的参数
    c, pos  // 查找字符c
    s2, pos // 查找对象s2
    cp, pos // cp为空字符
    cp, pos, n
    ```
5. string对象的比较
    ```c++
    s.comapre(s2)
    s.comapre(pos1, n1, s2) // 将2中从pos下标位置开始的n1个字符与s2做比较
    s.comapre(pos1,n1, s2, pos2, n2)
    s.comapre(cp)       // cp为空字符
    s.comapre(pos1, n1, cp)
    s.comapre(pos1, n1, cp, n2)
    ```

### 9.7 容器适配器
```c++
// 容器适配器通用的操作和类型
size_type   // 一种类型，足以储存此适配器类型最大对象的长度
value_type  // 元素类型
container_type // 基础容器的类型
A a;        // 创建一个新的空适配器
A a(c);
关系操作符  // == != < <= > >=

// 使用适配器时，必须包含相关的头文件
#include <stack>
#include <queue>
```
- 适配器的初始化
    ```c++
    stack<int> stk(deq) // 假如deq是deque<int>类型的容器
    ```
- 覆盖基础容器类型
    ```c++
    stack< string, vector<string> > str_stk;    // 基于vector的空stack
    stack< string, vector<string> > str_stk2(svec); // 复制svec
    ```
1. 栈适配器
    ```c++
    s.empty()   // 若为空则返回true
    s.size()
    s.pop()     // 删除栈顶元素，但不返回其值
    s.top()     // 返回栈顶元素的值，但不删除该元素
    s.push(item)
    ```
2. 队列和优先级队列
    ```c++
    q.empty()   // 若为空则返回true
    q.size()
    q.pop()
    q.front()   // 只适用于队列
    q.back()    // 只适用于队列
    q.top()     // 只适用于优先级队列
    q.push(item)
    ```

## 第十章 关联容器
### 10.1 pair类型

### 10.2 

## 第十一章 泛型算法