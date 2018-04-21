###	1. 抽象

*	abstract class, 抽象类, 只是为了告诉大家有这么一个规范和定义, 是一个声明, 是概念

	抽象类不能instantiate(实例化), 也就是不能制造对象,

	抽象类可以用于定义变量, 但赋值(管理)的时候只能用其非抽象子类.

	如果父类是abstract, 子类extends父类后, 要么自己也写成abstract, 否则必须Override覆盖(, 这里也可以称为实现)父类中的abstract方法.

	只有子类实现了父类的抽象方法后，才能用于产生(赋值、管理)具体对象.

*	抽象类可以含有非抽象方法

*	例子

	```java
	public abstract class Shape{
		// 抽象的方法, 注意没有大括号, ()后面直接分号结尾
		// 有abstract方法的类必须是abstract
		public abstract void draw();
	}

	// OK, 子类也是抽象类
	class abstract OneShape extends Shape{
		
	}

	// error, 子类不是抽象类, 却没有具体实现父类所有的抽象方法
	class Circle extends Shape{

	}

	// OK, 子类不是抽象类, 且具体实现了父类所有的抽象方法
	class Circle extends Shape{
		@Override
		public void draw(){
			//...
		}
	}

	public static void main(){
		Shape a;	// ok
		a = new Shape();	// error
		a = new Circle();	// ok
	}
	```

*	两种抽象

	与具体相对, 表示一种概念, 而非实体

	与细节相对, 表示忽略一些细节, 而着眼大局

*	疑问

	为什么要用abstract呢? Is 'abstract' a must? 之前没有abstract也能行得通, 也就是直接用父类进行接口的定义和规范

	而且python似乎也没有abstract

	只是因为软件工程的原因吗

<br><br>

###	2. 接口

*	对象，容器，只能管理自己认识的类型的对象，形状不对就放不进去

	Animal->Fox, Rabbit

	Cell

	Cell 与 Animal, 在逻辑关系上, 一个Cell对应一个Animal(Fox/Rabbit)，但是语义上不是，因为Animal不属于Cell

	要把两种类型联系起来管理，就需要接口

*	Java不支持多继承, 解决方法是interface

*	interface 接口，是一种特殊的class，interface是纯抽象类

	所有成员函数都是abstract

	所有的成员变量都是public static final, static表明它属于这个类而不是某个具体对象, final表明它一旦初始化就不会被改变, 是一个编译时刻已经确定的常量

	接口规定了类长什么样，但是不管里面有什么

*	不是Animal去实现Cell的接口，而是Fox, Rabbit增加Cell接口

	implements Cell, 告诉原来放Cell的容器，我有这个接口，你可以管理我，可以把我放进去

*	例子

	```java
	public interface Cell{
		void draw(Graphics g, int x, int y, int size);	// 不需要显示写abstract
	}

	public class Fox extends Animal implements Cell{
		@Override
		public void draw(...){
			// ...
		}

		// ...
	}
	```

*	接口(interface)可以继承接口，但是不能继承(extends)类

	接口不能实现接口，只有类能够实现(implements)接口

	一个类可以实现(implements)多个接口

*	面向接口编程

	Field只认识Cell，你有任何东西给我，你得符合Cell的规范，其他多出来的东西我不关心

	程序设计时，先定义接口，在实现类

	任何需要在函数间传入传出的，是接口，是规范，而不是具体的类。这样，长得像的就可以传

	也有缺点，代码量快速膨胀

<br><br>

###	3. 数据与表现分离

*	程序的业务逻辑与表现无关

	数据有自己的逻辑，不在数据层去考虑怎么表现，而是在更新完数据后，通知第三方来取数据。

	表现既可以是图形的也可以是文本的，可以是本地的也可以是web的

*	Field只管数据的存放

	View只根据Field画出图形

	Controller更新完Field后，通知View重新画出整个图形，而不是局部更新，以简化逻辑

*	责任驱动的设计

	各个功能应该分配到合适的类/对象

*	网格化

	以col, row划分board，每一个格子分配一个cell

	不是以pixel为单位划分

*	名词

	可扩展性，可维护性，耦合

<br><br>

###	4. TODO

*	实现Java扫雷, 和细胞自动机有很多类似之处.
