###	1. 异常

*	demo

	```java
	int[] a = new int[10];
	a[10] = 1;

	// 可以通过编译
	// 运行的时候报错, 因为数组下标越界了, 会出现 Exception, 异常
	// ArrayIndexOutOfBoundsException
	```

	出现异常时, 如果不加处理, 则会导致程序停止

<br><br>

###	2. 捕捉异常

*	捕捉异常

	try ... catch ...

	```java
	try{
		int[] a = new int[10];
		a[10] = 1;
	} catch(ArrayIndexOutOfBoundsException e) {
		// ...
	}
	```

	捕捉异常后, 最近的 try{} 中剩下的内容都不再执行, 而是进入 catch. 接着, 程序可以继续运行 try ... catch ... 块之后的内容, 而不是退出整个程序

*	可以捕捉不同种类的异常

	举例, 读文件伪代码

	```java
	try{
		openFile();
		getSize();
		allocateMemory();
		loadFileIntoMemory();
		closeFile();
	} catch ( OpenFileException e ) {
		// ... 
	} catch ( GetSizeException e ) {
		// ... 
	} catch ( AllocateMemoryException e ) {
		// ... 
	} catch ( LoadFileException e ) {
		// ... 
	}
	```

	不知道是用switch-case实现的, 还是用 HashMap 实现的

*	匹配异常

	子类异常 可以被 父类异常 的捕捉器 catch到

	```java
	class RootException extends Exception{}

	class ChildException extends RootException{}

	class SomeClass {
		public void foo() throws ChildException {
			throw new ChildException();
		}

		public void g() {
			try{
				foo();			// 虽然扔出的是子类异常
			} catch ( RootException e ) {
				sysout(e);		// 但是父类捕捉器 可以 catch
			}
		}
	}
	```

*	捕捉任何异常

	`catch (Exception e)` 可以 catch 到任何异常, 因为所有异常都继承自 Exception. 另外, Exception 继承自 Throwable

<br><br>

###	3. 处理异常

*	catch 捉到异常以后, 可以对其进行处理

	```java
	try {
		...
	} catch(ArrayIndexOutOfBoundsException e) {
		sysout( e );				// 相当于 e.toString()
		e.printStackTrace();		// 跟踪异常, 找到出错的那一行代码
		throw e;					// 抛出异常, 交给上层处理
	}
	```

<br><br>

###	4. 抛出异常

*	抛出自定义异常

	```java
	// 继承了 Throwable 才可以 throw
	class OpenException extends Throwable{ }

	class A{
		// 如果要 throw 异常, 那么就要在函数头的后面, 显式添加 throws OpenException
		// 让别人知道这里可能会出异常
		public void openFile() throws OpenException {
			throw -1;	// error, 只有 throwable 类才能被抛出, int不能被抛出
			throw new OpenException();	// ok
		}
	}
	```

*	运行时异常

	像 ArrayIndexOutOfBoundsException, ArithmeticException 这样的异常, 不需要在函数头声明, 这一类是运行时异常.

<br><br>

###	5. 继承与异常

*	子类 Override 父类 的 `成员函数` 时, 不能比父类抛出更多异常

*	子类的 `构造函数`, 必须声明父类可能抛出的所有异常, 还可以抛出新的异常

<br><br>

###	TODO
