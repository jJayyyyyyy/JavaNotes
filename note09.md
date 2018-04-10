###	0. 容器

*	容器, container, 是放东西的东西. 数组可以看做一种容器, 但是数组的长度一旦确定就无法改变.

	一般地, 容器可以改变其容量.

*	一个类, 如果实现了 toString 方法, 就能直接sysout(对象名)

	```java
	class Num{
		private int val;

		public String toString() {
			return "" + val;
		}
	}

	public static void main(){
		Num num = new Num();
		sysout(num);
	}
	```

<br><br>

###	1. 顺序容器

*	人机交互, 业务逻辑, 两者要分离

	比如做一个记事本, 至少需要设计以下业务逻辑的接口

	```
	add(String note);
	size();
	getNote(int index);
	remove(int index);
	printAll();
	```

*	ArrayList<E>

	ArrayList<E>, 这是泛型类, 这是一种容器

	ArrayList<String> , ArrayList of String, 类似于 String[] 数组, 但是可以改变大小, 还增加了很多方法

*	容器类

	```java
	ArraList<String> notes = new ArrayList<String>();
	```

	容器类在定义的时候, 需要指明两个类型: 容器的类型 `ArrayList` ; 元素的类型 `String` .

*	`toArray()`

	直接用顺序容器的方法构建一个数组, 而不需要手动for循环逐个赋值.

	```java
	public String[] getNotes(){
		String[] a = new String[notes.size()];
		notes.toArray(a);
		return a;
	}
	```

<br><br>

###	2. 对象数组

*	对象数组与基本类型数组的区别

	*	int[] 数组, 基本数据类型数组, 每一个元素都是基本数据类型的对象

		int[] ia = new int[10];	// 每个元素被初始化为0

	*	String[] 数组, 对象数组, 每一个元素只是一个管理者, 一个指针, 而不是保存实际内容

		String[] a = new String[10];	// null, 实际内容还不存在, 还需要for循环去创建和赋值(指向)

		for( int i = 0; i < a.length; i++ ){
			// a[i] = new String("Hello");
			// a[i] = "hello";
		}

		每一个元素都是指向字符串的管理者, 没被初始化时指向null

*	null

	```java
	String s = null;
	
	sysout(s + "a");	// nulla

	s.length();			// error
	```

*	对象数组的 for-each 循环

	基本数据类型数组的 for-each 是只读的

	```java
	int[] ia = new int[10];
	for( int k : ia ){
		k++;	// error
	}
	```

	对象数组

	```java
	class Num{
		private int val;
		public void set(int v) {	this.val = v;	}
		public int get() {	return this.val;	}
	}

	public static void main(){
		Num[] a = new Num[10];
		for( int i = 0; i < a.length; i++ ){
			a[i] = new Num();	// 重要!!! 初始化, 赋值
			a[i].set(i);
		}

		for( Num n : a ){
			sysout(n);		// 得到对象的地址
			sysout(n.get());	// 得到值
			v.set(7);		// 地址是只读的, 但是可以通过对象地址来赋值. 类比指针数组
		}
	}
	```

<br><br>

###	3. 集合容器

*	HashSet

	```java
	HashSet<String> s = new HashSet<String>();
	s.add("first");
	s.add("second");
	s.add("first");
	sysout(s);			// ["second", "first"]
	```

<br><br>

###	4. 散列表

*	美元

	```java
	1		penny
	5		nickel
	10		dime
	25		quarter
	50		half-dollar
	```

	输入数字, 获得相应的英文字母, 这时候就需要 HashMap, 像是自定义可扩展的switch-case

*	先定义接口

	```
	public Class Coin{
		// 容器里面必须是类, 包裹类型, 不能是基本数据类型
		private HashMap<Integer, String> coin2name = new HashMap<Integer, String>();

		public Coin(){
			coin2name.put(1, "penny");
			coin2name.put(5, "nickel");
			coin2name.put(10, "dime");
			coin2name.put(25, "quarter");
		}

		public String getName(int amount){
			// return coin2name.get(amount);	// 如果没找到, 会返回 null

			if( coin2name.containsKey(amount) ){
				return coin2name.get(amount);
			}else{
				return "Not Found";
			}
		}

		public void trav(){
			for( Integer k : coin2name.keySet() ){
				String name = coin2name.get(k);
				sysout(name);
			}
		}
	}
	```

<br><br>