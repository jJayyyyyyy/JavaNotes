###	Java程序的类型

*	Application, 小应用程序

*	Applet, 小应用程序

	嵌入到HTML

<br><br>

###	Java程序的基本构成

*	Application

	```java
	publicc class Hello{
		public static void main(String args[]){
			System.out.println("Hello");
		}
	}
	```

*	puclic类, 一个文件只能有一个 public类, 且与文件同名

*	main 方法必须是public static

	main方法是一个特殊的方法, 它是程序运行的入口.

	// main 还可用于测试, 直接运行该类的时候会调用main, 如果是其他类调用这个类的方法, 则不会运行main

*	类 = 类头 + 类体

	类成员 = 成员变量(field, 字段) + 成员函数(method, 方法)

	// 具有setter和getter的，才能称为属性

*	package, 包, 文件路径

*	import, 导入其他的类

<br><br>
