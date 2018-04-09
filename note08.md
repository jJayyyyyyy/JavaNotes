###	1. 成员访问控制

*	private

	私有的, 只有类内部的成员变量/成员函数可以使用

	是对`类`的限制, 而不是对`对象`的限制。同一个类的内部, 该类的不同对象实例之间, 可以互相访问private. 但是出了这个类，就无法访问private了。举例如下

	```java
	class Lower{
		private int val = 0;

		public void test(){
			Lower a = new Lower();
			Lower b = new Lower();
			a.val = b.val;			// 类的内部, 该类的不同对象实例之间, 可以互相访问private. 
		}
	}

	class Higher{
		public void test2(){
			Lower l1 = new Lower();
			Lower l2 = new Lower();
			l1.val = l2.val;		// error, not visible. 超出了Lower类的内部, private字段就变成了不可见.
		}
	}
	```

	个人感觉, 最好通过getter和setter来访问.

*	public

	公开的

*	friendly

	既没有前缀`public`, 也没有前缀`private`, 那么这个成员是`friendly`, 那么和它位于同一个包的其他类可以访问. 在同一个包, 可以简单地理解为在同一个文件夹里面.

	注意, 不需要/不能自己在前面加上前缀'friendly'

*	protected

	与继承有关, 后续再补充

<br><br>

###	2. 类

*	public

	`public 类的名字` 必须和 `文件名` 相同.

	一个 `.java` 文件就是一个编译单元, 一个编译单元里面可以有很多类, 其中只有一个类是`public`.

	如果没有public修饰, 那么这个类只能在这个 `package` 里面起作用

	所以如果想要在任何地方都能用, 那么就每个类定义在一个文件里面, 然后都用public修饰

<br><br>

###	3. 包

*	初步理解, 一个包就是一个文件夹

*	一个包里面所有的源代码文件, 第一行都是 `package xxx;`, 表明自己在这个package里面

*	引用其他包的东西, 需要在最上头加上 `import xxx.yyy;`

<br><br>

###	3. 类变量, 类函数, static

*	类变量与类函数, 就是在类的定义中加了 `static` 前缀的东西.

	初步理解, static变量, 相当于该类一个全局变量.

	类就相当于计网中的协议, 比如网络层协议, 规定了每个数据包应该有什么样的格式. 而对象则是一个个具体的实际的数据包.

	类变量, 相当于协议头部的一个字段, 所有这个类的对象实例都有相同的头部信息. 相对的, 成员变量则是数据部分.

*	一个static变量单独划分一块存储空间，不与具体的对象绑定在一起，该存储空间被类的各个对象所共享。static变量值在方法区加载一次，而非static在创建对象时会加载很多次。每次创建都会拷贝一份。

*	访问

	static 函数 --- 可以访问 static变量

	static 函数 -x- 不能访问 普通成员变量(无static前缀)

	普通成员函数 --- 可以访问 static变量

	普通成员函数 --- 可以访问 普通成员变量(无static前缀)

	可以通过 `类名.类变量` 访问类变量, 也可以通过 `对象名.类变量` 访问类变量

	```java
	class A{
		static int var = 1;

		public static void main(){
			A.var++;			// OK, 类名.类变量

			A a = new A();
			a.var++;			// OK, 对象名.类变量

			var++;				// OK, 直接访问
		}
	}
	```

*	static函数只能够访问static变量
	
	比如有 `static int var1 = 1;`

	因为 var1 是属于这个类的, 在每一个对象实例中的 var1 都是都是1, 而且类似于C中的extern全局变量, 是所有该类的对象实例所共享的.

	这样看来, Java的类是一个树的结构, 根结点中存储着公有信息, 而对象实例是衍生出来的子结点.

*	另一方面, static函数不能访问 `非` static 变量, 即每个对象实例的`各自的`成员变量.

	对成员变量的访问, 必须明确指明其所属的对象实例.

*	也就是说, static是一个公共的路灯, 只有一盏, 每个人可以去开关路灯, 但是如果你要去关掉某一户人家里的灯, 就要明确指明他家的门牌号

<br><br>