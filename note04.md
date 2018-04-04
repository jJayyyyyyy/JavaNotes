###	比较与判断

*	优先级(从高到低)

	*	(), 括号
	*	! +(正) -(负) ++ --, 单目运算符
	*	* / %, 算术
	*	+ -, 算术
	*	> < ..., 关系比较
	*	== !=, 关系比较
	*	&&, 逻辑
	*	||, 逻辑
	*	=, 赋值

	整体规律: 单目 > 算数 > 关系 > 赋值

	如 7 >= 3 + 4, 先做 3 + 4	

*	以下情况不能做比较/运算, 会报错

	```java
	2 + true;			// error
	true + true;		// error, 就算换成 boolean a, b; 然后 a + b; 仍然是error
	true > false;			// error
	1 > false;			// error
	true == 6;			// error
	a == b > false			// error, ==, != 优先级低, 先做 b > false, 这里也会出错
	( a == b ) > false		// error, 类似于 true > false 的error
	```

	总之, boolean 类型就不能用于大小比较, 以及算术运算

	逻辑运算的 `!`, `&&`, `||` 只能用于 boolean, 作用到其他类型上面会出错, 这点也和 C 不一样.

*	int 与 double

	```java
	if( 1 == 1.0 )			// true

	int a = 1;
	double b = 1.0;
	if( a == b )			// true


	double c = 1.0 + 0.5;
	double d = 1.5;
	if( c == d )			// true

	double e = 1.5 - 0.2 - 0.3;
	double f = 0.5 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1;
	if( e == f )			// false, 累计误差过大

	if( Math.abs(a - b) < 1e-6 )		// true
	```

*	switch

	```java
	switch( exp ){			// exp只能是整型
		case 1:
			// ...
			// break;	//寻找匹配的case, 一直执行, 直到break
		case 2:
			// ...
			break;
		default:
			// ...
			break;
	}
	```

*	风格

	```java
	if( ... )
	{

	}
	// 换行写 else, 方便 ctrl+/ , cmd+/ 对区块进行注释
	else
	{

	}
	```

<br><br>

###	循环

*	学习编程, 一个关键的步骤就是, 如何从问题到程序, 中间是怎么过来的

	我有什么样的数据, 会得到什么样的结果

	阶乘, int有边界, [2^31 - 1, 2^31]

<br><br>

###	数组

*	与C语言的两大区别

	1.必须用 new 来分配空间

	2.数组元素默认初始化

*	例子

	```java
	// 数组是引用类型
	int []a = new int[3];

	// error, 数组是引用类型, 理解为指针, 不能直接给它分配空间, 分配的空间在堆, 只能指向那里
	int b[5];

	//静态初始化, 如果没有初始化, 则会隐式初始化为0 或 null
	int []c = {3, 1, 2};
	int []d = new int[]{3, 1, 2};
	```

*	每个数组, 都有一个length属性

	```java
	for( int i = 0; i < a.length; i++ ){
		// ...
	}
	```

*	增强的 for, 只读式的遍历

	```java
	for( int n : a ) {
		System.out.println(n);
	}
	```

*	多维数组

	```java
	int [][]a = new int[3][5];
		
	for( int i = 0; i < 3; i++ ) {
		for( int j = 0; j < 5; j++ ) {
			a[i][j] = i * 5 + j; 
		}
	}
	```