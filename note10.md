###	1. 继承

*	面向对象三大特性: 封装、继承、多态

*	继承, 减少代码复制. 否则, 将来进行维护的时候, 就需要修改每一个出现的地方. 这就有点像, C语言用 宏 定义一个 PI , 现在`#define PI 3.14`, 以后可以 `#define PI 3.14159` , 这样每一个用到 PI 的地方都会自动改.

	父类是对相似的类的抽象.

	同时, 继承可以增加可扩展性.

*	语法

	```java
	// 子类 `extends` 父类

	class Item{
		...
	}

	class CD extends Item{
		...
	}
	```

*	子类会继承得到父类的所有东西. 而父类的所有东西, `可能`在子类可用. 之所以是`可能`, 是因为还有访问权限的问题.

	父类的`private`, 子类就不能用, not visible.

	方法1: `protected`, 自己可以访问, 同一个包的其他类可以访问, 子类可以访问

	方法2: 父类增加相应的 `public/protected getter()`, 通过这个接口进行只读访问

	方法3: 对于父类的private, 只在父类进行操作 (比如调用父类的print, 进行只读操作).

<br><br>

###	2. 子类和父类的关系

*	初始化

	先做父类的初始化, 再做子类的初始化. 先做定义初始化, 再做构造函数中的初始化. 总之会先做父类的, 再做子类的初始化.

	如果没有显式地用 `super(a, b, c)` 来调用父类的构造器, 则会隐式的调用无参数的 `super()` . 这个过程自动进行, 不需要在子类的构造器中写出 `super()`. 

*	子类会得到与覆盖父类的变量和方法.

*	修改父类的private? 只能调用父类的方法. 4.2.2节

	```java
	class Item{
		private String title;

		public void setTitle(){
			this.title = "item";
		}
	}

	class DVD extends Item{
		private String title;

		public DVD(){
			this.title = "dvd";
			setTitle();
			// debug可以看到DVD里面有2个title
			// 经过setTitle(), 子类自己的title还是"dvd", 父类的title变成"item", 但是对子类不可见
		}
	}
	```

	貌似没什么意义... 只是有这么个过程, 面试可能会问

<br><br>

###	3. 多态变量和向上造型

*	子类的对象, 可以被当成父类的对象来使用:

	子类的对象, 可以赋值给父类的变量

	子类的对象, 可以传递给需要父类对象的函数

	如果一个容器存放的是父类对象, 那么子类对象也可以放进去.

	// 为什么? 因为继承了父类, 对外的接口是一样的, 

*	debug可以发现, 容器能够识别这是一个子类对象还是一个父类对象

*	对象变量

	多态变量的意思是, 这个变量运行的时候, 它所管理的对象类型可能会变化

	Java中, 所有的对象变量都是多态的, 能保存不止一种类型的对象

	它们可以保存声明类型(静态类型)的对象, 也可以保存声明类型的子类(动态类型)的对象

	当把子类的对象赋值给父类变量时, 发生了向上造型cast

*	向上造型

	子类的对象可以赋值给父类的变量. 注意, Java不存在`对象`对`对象`的赋值, 而是让两个管理员共同管理一个对象. 类比指针, 不是复制内容, 而是给地址.

	造型: 让静态类型为Item的变量, 去管理了动态类型与其不同的对象

	子类的变量不能接收父类的对象. 也就是, `=`左边是父类, 右边可父可子; `=`左边是子类, 那右边只能是子类

	```java
	Item item = new Item();
	CD cd = new CD();

	// cd = item;	// error
	
	item = cd;	// ok

	// CD cc = item;	// error, 就算现在item已经管理cd了, 但是编译器还看不明白, 出现编译错误

	CD cc = (CD)item;	// ok强制转换, 但是如果没有item = cd, 那会在运行的时候出现异常
	```

*	造型

	用括号围起来放在值的前面, 有点像强制类型转换

	但是, 类型转换, 比如 (int)10.2 , 那么10.2就不存在了, 值已经被换成了10

	而对于造型, 对象本身没有发生任何变化. 只是当做另外一个类型来看待, 而不是真的去改造和替换. 所以造型不是类型转换. 个人感觉这也是基本数据类型和引用类型的区别之一, 值和地址的区别.

	向上造型, 不需要显式的 (Item). 造型完成后, 动态类型变了.

	就算通过了编译, 但也可能出现运行时错误, ClassCastException

*	总结一下向上造型

	把子类的对象当做父类的对象

	向上造型是默认的, 不需要显式(Item)

	向上造型总是安全的

<br><br>

###	4. 多态

*	多态

	子类可以覆盖父类的方法. 同一个方法, 不同的对象会产生不同的效果.

	`item` 里面一定会有一个 `print()`, 至于它是怎么print的, 如何找到正确的print, 这个是通过Java的语言机制保证的.

	多态变量有两种类型, 声明(静态)类型、动态类型

	`.`操作符, 执行实际管理对象的方法

*	覆盖 override

	子类和父类, 名称和参数表都相同

	@Override, 可以当做注释, 同时编译器会帮你检查, 如果父类中没有相同的方法名+参数表, 那么会报错

*	技术基础

	当通过对象变量调用方法的时候, 选择调用哪个方法, 这个叫做绑定

	静态绑定, 由变量的声明类型决定

	动态绑定, 由变量的动态类型决定, 运行的时候才知道. Java默认动态绑定

<br><br>

###	5. 类型系统

*	单根结构

	所有的类, 都继承自Object类, Python也是这样, 但C++不是.

*	Object类的方法

	toString(), 可以直接print类名

	equals(), 比较两个对象的内容是否相同, `==`只能判断是否管理着同一个对象, 通常需要override覆盖equals方法

	```java
	// 方法1, 重载, overload
	public boolean equals(CD cd){
		return artist.equals(cd.artist);
	}
	```

	```java
	// 方法2, 覆盖/重写, override
	@Override
	public boolean equals(Object obj){
		CD cd = (CD)obj;	// 向下造型
		return artist.equals(cd.artist);
	}
	```

*	添加新的Item类的子类型

	可扩展性, 不需要修改原来的Database

<br><br>