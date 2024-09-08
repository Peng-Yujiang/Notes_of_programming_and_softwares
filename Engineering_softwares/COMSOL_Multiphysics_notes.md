# COMSOL Multiphysics

## 1. 工作流程
- 定义需要求解的问题模型 Define the problem model to be solved
- 绘制/导入CAD几何 Import/ build CAD model
- 定义每个求解域的材料属性 Define material properties for each solution domain
- 设定载荷和边界条件 Set loads and boundary conditions
- 网格划分 Meshing
- 求解模型 Solve
- 后处理和报告结果 Postprocessing and reporting results
- 修改和优化 Modify and optimize

## 2. 网格划分
1. 为什么要划分网格？
    - 有限元(FEM)基于几何离散化为最小的单元-- 网格单元
    - 有限元网格有两个目的
        - 表征几何
        - 表征求解域
    - 使用更多单元意味着
        - 更精确的近似和求解
        - 更长的求解时间和更多的内存需求
    - 在数值稳定性方面，是否获得良好的刚度矩阵
        - 很大程度上取决于网格单元的大小和形状（可以通过网格质量来作为指示器）

2. 需要多少网格单元？
    - 事先我们并不清楚
    - 足够的精细程度充分描述几何
    - 足够的精细程度来解析所有结果的梯度

3. 物理场控制网格剖分
    - 根据模型中的物理场设置自动生成网格
    - 可校准为
        - 普通物理
        - 流体力学
        - 传热
        - 等离子体
        - RF
        - 半导体
        - 结构力学
    - 可作为手动网格剖分的出发点

4. 用户控制网格剖分
    - 手动添加网格操作
    - 网格操作均存在网格剖分指令序列中
    - 同一模型中可保存多个网格剖分指令序列
    - 九个预置网格尺寸设定
    - 手动控制网格尺寸

5. 网格剖分指令序列
    - 添加、删除、禁用、编辑网格
    - 可对网格操作使用参数化设置
        - 使用带有变量的表达式来定义网格尺寸参数
    - 几何参数化扫描时可自动重新生成网格

6. 二维的网格划分
    - 使用三角形或四边形（quadrailateral）网格单元将区域离散化
    - 对于弯曲边界，网格单元时真实几何的近似等效
    - 几何定义的边界离散化为网格单元，称为边界单元（或边单元）
        - 相邻的网格单元必须一致（在形成联合体之后，或形成带有压印的转配体）

7. 三维的网格划分
    - 剖分工具将域离散化为四面体(tetrahedron)、六面体(hexahedron)、棱柱(prism)或金字塔(pyramid)形网格单元
        - 面、边、角分别称为网格面、网格边以及网格顶点
    - 对于三维网格剖分，由于不同平台对浮点运算处理方式不同
        - 