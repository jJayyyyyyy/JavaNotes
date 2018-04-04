###	方法

*	定义(同一个类中)

	```java
	public static void foo(){
		// ...
	}
	```

*	调用

	参数类型不匹配, 太胖了进不去, 瘦一点可以进去

	case1, 形参 int, 实参 double, 实参宽度大, 会报错, 除非对实参进行强制类型转换

	```java
	public static void f(int a){...}

	f(10.0);	// error
	f((int) 10.0);	// ok
	```

	形参 double, 实参 int, 实参宽度小, 不会报错

	true 和 int 无法转换, 所以也不行

*	方法的每一次运行, 都会产生独立的本地变量空间, 参数也是本地变量

*	由于没有C的指针, 也没有C++的引用&, 所以一般方法无法实现swap

	需要使用数组或者对象

<br><br>
