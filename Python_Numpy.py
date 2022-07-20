### Author: Yujiang Peng */
### Date: Sep 9, 2019  */

# Numpy basics
import numpy as np

### a, b, c为数组名; r为行数; c为列数

# 1. 创建数组
np.array([1,2,3])
np.array([(1.5,2,3),(4,5,6)], dtype=float)
np.array([[(1.5,2,3),(4,5,6)], [(3,2,1),(4,5,6)]], dtype=float) #三维数组
#三维数组shape结果(0,1,2)中，0代表通道；1和2与二维数组相同，分别表示行和列
# 1.1 初始化
np.zeros((r,c)) #创建零数组
np.ones((Channel,r,c),dtype=np.int16)
np.arange(start, end, step)
np.linspace(start, stop, number)
np.full((r,c), value)
np.eye(value) #单位矩阵 identity matrix
np.random.random((r,c)) #创建由随机数填充的矩阵
np.empty((r,c)) #创建空矩阵

# 2. i/o
# 2.1 保存到磁盘和载入
np.save('my_array', a)
np.savez('array.npz', a, b) #保存非数组数据
np.load('my_array.npy')
# 2.2 保存和载入文本文件
np.loadtxt("file.txt")
np.genfromtxt("file.csv", deliiter=',')
np.savetxt("file.txt", a, delimiter=" ")

# 3. 数据类型
np.int64
np.float32
np.complex #复数, 128浮点数
np.bool
np.object
np.string_ #固定长度字符串
np.unicode_ #固定长度Unicode

# 4. 检查数组
a.shape #数组维度
len(a)
a.ndim #维度数量
a.size #数组元素数量
a.dtype
a.dtype.name #数据类型名称
a.astype(int) #转换数组数据类型

# 5. 查询帮助
np.info(np.ndarray.dtype)

# 6. 数学运算
# 6.1 算术运算
a - b   np.subtract(a, b)
b + a   np.add(b, a)
a/b     np.divide(a, b)
a*b     np.multiply(a, b)
np.exp(a) #取幂
np.sqrt(a) #开方
np.sin(a) #元素逐个求正弦
np.cos(a) #元素逐个求余弦
np.log(a) #元素逐个求对数
a.dot(b) #数组点乘
# 6.2 比较
a == b #元素逐个比较
a < 2  #元素逐个比较
np.array_equal(a, b) #元素逐个比较
# 6.3 统计函数
a.sum() #数组各列元素分别求和；axis=0,1
a.min() #数组元素中最小值
a.max(axis=0) #数组行最大值
a.cumsum(axis=1) #每列元素之和
a.mean() # np.sum(a)
a.median()
a.correcoef()
np.std(a)
# 6.4 复制数组
b = a.view()
np.copy(a)
b = a.copy() #深度复制
# 6.5 数组排序
a.sort() #数组排序
np.sort(a) #返回一个排序后数组的复制
a.sort(axis=0) #按行或列排序
# 6.6 数组的布尔方法
(a > 0).sum() #数组中条件为真的个数
a.any() #如果至少一个为真
a.all() #所有值均为真
# 6.7 集合方法
np.unique(a) #返回排序后的唯一值
np.in1d(a, b) #检查数组a的值是否在数组b中

# 7. 取子集、切片、索引
#取子集
a[2]
b[1, 2]
#切片
a[0:2]
a[0:2,1]
a[:1]
a[1,...] #[1,:,:]
a[::-1] #倒序数组a
#布尔值索引
a[a<2] #取数组a中小于2的元素
a[(name == 'BO')|(name == 'CO'), 2:] #更复杂的条件索引
#魔法索引
a[[1,2,3,4], [5,6,7,8]] #取元素(1,5),(2,6),(3,7),(4,8)
a[[1,0,1,0]] #取某些行按特定顺序组成新数组
a[[1,0,1,0]][:,[0,1,2,0]]
        #第二个列表中：表示保留所有行，后一个子列表为在每行中选值时对应的列索引

# 8. 数组操作
# 8.1 转置数组
np.transpose(a)
    #or
a.transpose()
a.T
a.swapaxes(0,1)
# 8.2 改变数组形状
a.ravel() #展平数组
a.reshape(3, -2) #改变数组形状，但不改变数据
# 8.3 增加/删除元素
a.resize(r, c)
np.append(a, element)
np.insert(a, index, element)
np.delete(a, element)
# 8.4 组合数组
np.concatenate((a,b), axis=1) #组合数组
np.vstack((a,b)) #垂直堆叠
np.r_[a,b] #垂直堆叠
np.hstack((a,b)) #水平堆叠
np.column_stack(a,b) #创建水平堆叠的数组
np.c_[a,b] #创建水平堆叠的数组
# 8.5 分分割数组
np.hsplit(a, indices_or_sections) #将数组水平分割成几个子数组
np.vsplit(a, 2) #将数组竖直分割成两个子数组