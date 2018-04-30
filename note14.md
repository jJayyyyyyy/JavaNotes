###	1. demo, 课程表

*	JTable 类的对象, 并不存储数据, 只是数据的表现. 数据和表现是分离的.

*	main

	```java
	JFrame frame = new JFrame();	// view
	JTable table = new JTable(new KCBData());	// model, 存储数据, KCBData实现了TableModel接口
	JScrollPane pane = new JScrollPane(table);	// 容器
	frame.add(pane);
	
	frame.pack();	// 计算界面大小
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 可以关闭
	frame.setVisible(true);	// 显示
	```

<br><br>

###	2. MVC设计模式

*	设计原则, 数据和表现分离

*	TableModel

	JTable 是设计好的类库, TableModel是一起设计好的 接口, 因此 JTable 知道 TableModel 长什么样子

	在构造 JTable 的时候, 需要给入一个 实现了 TableModel 接口的对象

	数据是由 TableModel 来维护的, JTable 只管表现

*	MVC

	数据模型、表现、控制, 三者分离

	M, Model, 模型, 保存和维护数据, 还可以 `通知` View 进行界面的更新.

	V, View, 表现, 从 Model 获得数据, 并以此画出表现(界面). View 是被动的, 只有在 `Model 通知 View` 之后, View才会去Model取数据, 并画出来.

	C, Control, 控制, 得到用户输入, 以此调整数据. C 和 V 并不直接交互, C只能更新Model中的数据, 也就是说, 用户在界面上所作的操作, 不会直接修改界面上的显示, 而是先去修改后台的数据, 再由Model通知View, 再整体重画.

	这样, View不用管单个cell, 每次都把数据重新画一遍就好了, 不知道数据变化的细节. C也不用考虑画画的细节. 

*	示例

	课程表demo中, Model就是TableModel, View就是JTable, C也被合并到JTable中了.

	设计理念中, V和C是没有直接联系的, 但是具体实现中, 两者有很多共同的地方, 所以被合并起来做到一个类里面了. 于是, JFrame 既用于表现, 同时也可以用于得到用户输入.

<br><br>

###	TODO
