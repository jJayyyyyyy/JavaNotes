对象

###	字符类型 char

*	能被单引号包围, 如 'a', '+', '你'

	unicode16编码, 2个字节, 在所有机器上是一致和统一的

<br><br>

###	字符串

*	String, 第一个字母大写, 说明是一个类, 是管理者

	```java
	String s1 = new String("123");
	String s2 = "abc";
	String s3 = s1 + s2 + 12 + 24;		// "123abc1224", 
	String s4 = s1 + s2 + (12 + 24);		// "123abc36"
	```

*	读取输入

	```
	next --- cin
	nextLine --- getline
	```

*	操作

	字符串是对象, 对它的所有操作都要通过 `.` 这个运算符

*	长度

	```java
	s.length();			// 注意要加括号 () , 是length()方法, 而且 s 应该指向一块字符串, 不能是未初始化的s
	```

*	访问字符

	```
	s.charAt(index)		// read-only
	```

*	遍历

	```java
	for( int i = 0; i < s.length(); i++ ){
		s.charAt(i);
	}
	```

	注意字符串无法使用 for(char ch : s)

*	子串

	```java
	s.substring(n);		// 从 n 到 末尾
	s.substring(n, len);	// 从 n 开始, 截取长度为len的子串
	```

*	内容是否相同

	```java
	if( s.equals("Hello") )			// .equals
	```

*	比较大小

	```java
	s1.compareTo(s2);		// unicode 编码相减
	```

*	其他操作

	```java
	int loc = s.indexOf('a');
	s.indexOf('a', loc + 1);
	s.indexOf("abc");

	s.lastIndexOf('a');

	s.startsWith(ch);
	s.endsWith(ch);
	s.trim();		// 去掉两端空格
	s.replace(c1, c2);
	s.toLowerCase();
	s.toUpperCase();	// 全部变成大写字符
	```

*	注意, 这些操作, 生成了新的字符串, 而不是修改原来的字符串. 字符串本身是不可变的.

<br><br>

###	包裹类型

	```
	boolean	--- Boolean
	byte --- Byte
	short --- Short
	long --- Long
	float --- Float
	double --- Double

	char --- Character
	int --- Integer
	```

*	基本数据类型 + 更多方法和字段

	```java
	int a = Integer.MAX_VALUE;		// 2^31 - 1
	boolean b = Character.isDigit('a');	// false
	char c = Character.toLowerCase('A');	// a
	```

*	Math

	```
	Math.abs()
	Math.round()
	Math.random()
	Math.pow()
	```

<br><br>