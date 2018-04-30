###	1. GUI

*	Swing 是Java中用于开发GUI的一套工具包

	图形界面中所有的东西都叫做 `Component, 组件` ,

	`Container, 容器`, 里面可以放 `组件`, 或者说 容器 可以管理 组件 , 比如

	```java
	JFrame frame = new JFrame();		// frame 是 容器
	
	JButton btn = new JButton(...);		// btn 是 组件

	frame.add(btn, BoardLayout.NORTH);	// 组件 可以被放在容器里面, frame 可以管理 btn, 比如btn的位置
	```

	容器 本身也是一种组件, 一个容器可以被放在另一个容器中, 就像是画图的一块 背景板

	容器 通过 `LayoutManager, 布局管理器` 对组件进行管理, 比如 frame 通过 `BoardLayout` 对 btn 进行管理

	// LayoutManager可以自适应调整长度和宽度, 以适应不同的设备

<br><br>

###	2. 控制反转

*	事件处理, 用于响应用户输入

	当我们 new 了一个组件, 比如btn, 并把它放到合适的位置后, 需要有办法来处理用户的输入, 也就是, 当用户点击按钮之后, 程序要做什么事情。

	在用户点击按钮之后, Java就会产生一个`事件`, 程序可以收到 关于这个事件的通知, 接着会执行某个方法/函数, 也就是业务逻辑.

*	swing 事件机制, 消息机制

	swing 采用 listener 进行事件处理 event handling

	例如, `btn` 是 `JButton` 类的一个对象, 是一个组件, 当用户按下图像界面上的按钮之后, btn 有办法知道自己被按下去了. 

	此后会触发系统进行检测, 有没有 `obj` 注册到 `btn` , 而且这个 `obj` 必须是实现了 `ActionListener` 接口的对象

	如果有, 就调用 `obj.actionPerformed()`

	注册到 btn , 就是 `btn.addActionListener( ... )`, 其中的 ... 代表一个对象, 它实现了 `ActionListener` 这个接口

	如果有, 那么这个新注册的对象一定实现了 `ActionListener` 的接口, 也就是有一个 `actionPerformed()` 方法, 这个方法的内容是自定义的。

	这样, 我们写在 `actionPerformed()` 里面的内容就会被调用.

	```java
	public class SomeClass{
		private class SomeListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e){
				sysout("clicked");
			}
		}

		public static void main(){
			// ...
			btn.addActionListener( SomeListener );
		}
	}
	```

	// 隔离, 我们不需要改变JButton内部, 也无法改变.

	// 可以随时 addActionListener(), 也可以 removeActionListener(), 按钮的逻辑, 是可以自定义维护的, 而不是写死的类库

*	控制反转, 注入反转

	http://www.cnblogs.com/simple96/p/7156435.html

	*	JButton 公布了一个监听器接口 `ActionListener`, 一对注册/注销函数, addActionListener(), removeActionListener()

	*	通过匿名内部类实现那个接口, Override 接口规定的函数, 如 `actionPerformed()`, 将实现了Listener接口的对象注册到按钮上

		```java
		btn.addActionListener( SomeListener );
		```

	*	一旦按钮被按下, 就会反过来调用注册在按钮上的实现了Listener接口的对象的某个函数

*	简书博客, 本节课的笔记

	https://www.jianshu.com/p/e6279eae69e2

*	Invertion of Control, 反转控制, 把创建对象的权利交给框架

<br><br>

###	3. 内部类

*	demo

	```java
	class A{
		private class B{
			// ...
		}
		private int n;
	}

	class C{

	}
	```

*	B 是 A 的一个`内部类`, 和成员变量、成员函数一样, B 也是 A 的一个成员.

*	B 可以访问 A 的所有成员变量和成员函数. 而 C 无法访问 A 的 private 成员

	因此, 在 B 中 不用 `注入` 或 `声明` A 的实例

<br><br>

###	4. 匿名类

*	demo

	```java
	btn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e){
			sysout("clicked");
		}
	});
	```

*	匿名类是特殊的内部类

*	Swing的消息机制广泛使用匿名类

	Swing 的消息机制里面, 每一个组件发出的消息, 都需要一个新的类/对象, 去实现一个方法, 去接收那个消息

	免去了给对象命名的麻烦, 尤其是 Listener 很多的时候

*	自动增加 implements 的含义, 变成一个实现了接口的对象

<br><br>

###	TODO

*	参考简书博客的学习过程, 了解和制定学习计划

	https://www.jianshu.com/p/e6279eae69e2
