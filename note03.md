###	输入输出

*	输入

	```java
	Scanner in = new Scanner(System.in);
	int a = in.nextInt();	// 注意, 如果输入的不是整数, 而是小数/字符串, 则会报错. 因此需要try...catch...
	```

*	输出

	```java
	System.out.println("Hello, world");
	```

<br><br>

###	数据类型

*	基本数据类型，变量在栈，在这里

	复制变量的时候，复制的是值

*	引用类型，在堆，变量只是引用，类似于指针，只能指向特定对象，不能乱指

	复制变量的时候，复制的是引用

<br><br>

###	基本数据类型

*	整数型

	byte, 1字节

	short, 2字节

	int, 4字节

	long, 4字节

	Java中没有 `无符号数`

*	实数

	float, 4字节
	
	double, 8字节, 浮点数默认为double

*	逻辑

	boolean, 1bit, true/false, 不可以用 if(1) 或 if(0)

*	字符

	char, 2字节, 字符型, 字符统一使用Unicode编码，跨平台

*	强制类型转换

	```java
	double a = 10.3;
	int b = (int)a;
	int c = (int) (10/3.0);		// (10/3.0)要加括号, 因为(int)是单目运算, 优先级高
	```

<br><br>

###	引用类型

*	类、接口、数组

<br><br>

###	标识符identifier, 命名习惯

*	类名首字母大写

*	其余的，首字母小写

*	少用下划线

*	随写随用，而不是上方统一声明、分开使用
