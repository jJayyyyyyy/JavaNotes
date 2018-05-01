###	0. 摘要

*	输入输出流, 类似于指针

	```java
	// 文件流
	OutputStream out1 = new FileOutputStream("test.txt");
	InputStream in1 = new FileInputStream("test.txt");

	// socket网络流
	Socket socket = new Socket("127.0.0.1", 8888);
	OutputStream out2 = socket.getOutputStream();
	InputStream in2 = socket.getInputStream();
	// 
	```

	此后可以构成 缓冲流, 也可以构成 二进制流 或者 文本流

*	缓冲流

	作用: 文本流的编码, // or 中间层, 接口, 容器, 装东西, 过滤器

	```java
	// 设 os 是一个 OutputStream, 如 FileOutputStream, socket.getOutputStream()
	// is 同理

	OutputStreamWriter osWriter = new OutputStreamWriter(os, "utf-8");
	InputStreamReader isReader = new InputStreamReader(is, "utf-8");
	```

*	更高级的封装

	二进制流

	```java
	// os 是一个 OutputStream, is 是一个 InputStream
	DataOutputStream dataOS = new DataOutputStream( os );
	DataInputStream dataIS = new DataInputStream( is );
	```

	文本流

	```java
	// 写
	// os 是一个 OutputStream, is 是一个 InputStream
	// pw可以编码
	PrintWriter pw1 = new PrintWriter( os );
	PrintWriter pw2 = new PrintWriter( osWriter );
	
	pw1.printf("%d", 123);	// 写入字符串123


	// 读
	// isReader可以编码, bufReader可以readLine()
	InputStreamReader isReader = new InputStreamReader( is, "utf-8");
	BufferedReader bufReader = new BufferedReader(isReader);
	
	bufReader.readLine();
	```

*	注意流的关闭

	```java
	out.close();
	in.close();
	```

<br><br>

###	1. 流

*	流是一维的, 单向的

*	流的基础类, 都在 `java.io` 这个包里面

	二进制流 -> InputStream, OutputStream

	文本流 -> Reader, Writer

*	所有 IO 操作 都有可能出现异常, 所以需要 try ... catch ...

*	注意流的关闭

<br><br>

###	2. 二进制流

*	InputStream

	输入单个字节, 或者字节数组

	```java
	byte[] buffer = new byte[1024];
	
	try {
		int len = System.in.read(buffer);

		String s = new String(buffer, 0, len);
		sysout(len);
		sysout(s);
		sysout(s.length());
	} catch ( Exception e ){
		// ....
	}
	```

	输入 `123abc\n(回车)`, 一共7个`字节`, len=7, s="123abc\n", s.length()=7, 一共7个`字符`

	输入 `123汉字abc\n(回车)`, 一共11个`字节`, len=11, s="123汉字abc\n", s.length()=9, 一共9个`字符`

*	OutputStream

	输出单个字节, 或者, 字节数组

<br><br>

###	3. 二进制流读写文件

*	文件流, 类似于文件指针

	FileInputStream, FileOutputStream

*	写文件

	```java
	byte[] buf = new byte[10];
	for( int i = 0; i < buf.length; i++ ){
		buf[i] = (byte)i;	// 初始化字节数组 buf[]
	}

	try{
		// 创建一个二进制文件流
		FileOutputStream out = new FileOutputStream("a.dat");

		out.write(buf);			// 写入文件
		out.close();			// 关闭文件流
	}catch( Exception e ){
		// ...	
	}
	```

	命令行查看二进制文件内容

	```bash
	$ hexdump a.dat
	```

*	读文件

	```java
	byte[] buf = new byte[10];
	try {
		// 创建一个二进制文件流
		FileInputStream inputStream = new FileInputStream("test.txt");

		inputStream.read(buf, 0, 10);
		String s = new String(buf);
		System.out.println(s);
		inputStream.close();
	}catch (Exception e) {
		// ...
	}
	```

<br><br>

###	4. 流过滤器(封装)

*	1~3小节中, stream只能处理单个字节, 需要用过滤器进一步封装, 使得stream可以一次写如更多的字节

*	基本数据类型 -> DataInputStream -> BufferInputStream -> FileInputStream

	Data可以读写基本类型, Buffer用于缓冲

*	写文件

	```java
	try {
		// 创建二进制输出流, 相当于 with open("test.txt", 'wb') as f
		DataOutputStream out = new DataOutputStream(
			new BufferedOutputStream(
				new FileOutputStream("test.txt")
			)
		);

		out.write(12);		// 0x0c -> 12 -> 写入最低的1个字节
		out.write(0x12345678);		// 0x78 -> 写入最低的1个字节
		out.writeInt(0x12345678);		//  0x12345678 -> 写入1个int, 即4个字节
		out.writeBytes("AB");		// 二进制写入字符串
		out.close();
	}catch (Exception e) {
		// ...
	}
	```

*	读文件

	```java
	try {
		// 创建二进制输入流
		DataInputStream in = new DataInputStream(
			new BufferedInputStream(
				new FileInputStream("test.txt") 
			)
		);

		int i = in.readByte();		// 12, readByte() 读取第一个字节
		System.out.println(i);
		i = in.readByte();		// 120 -> 0x78, readByte() 读取第一个字节
		System.out.println(i);
		int j = in.readInt();
		System.out.printf("%#x\n", j);		// 0x12345678
		
		String s = new String(in.readAllBytes());	// 读取剩余字节
		System.out.println(s);		// "AB"
		in.close();
	} catch (Exception e) {
		// ...
	}
	```

<br><br>

###	5. 文本流读写文件

*	二进制流 -> InputStream, OutputStream

*	文本流 -> Reader, Writer

*	写文件, BufferedWriter

	```java
	try {
		// 相当于 with open("test.txt", 'w') as f, 注意是文本输出
		BufferedWriter out = new BufferedWriter(
			new OutputStreamWriter( new FileOutputStream("test.txt"), "utf-8" )
		);
		
		out.write(65);		// 直接写 ascii
		out.write("123");	// 写入 '1', '2', '3'
		out.write('B');		// 写入 'A'
		
		PrintWriter p = new PrintWriter(out);	// 封装, 格式化写入
		p.printf("%d", 456);			// 写入 '4', '5', '6'
		out.close();
		p.close();
	}catch (Exception e) {
		// ...
	}
	```

*	读文件, BufferedReader

	```java
	// with open("test.txt", "r", encoding="utf-8") as f:
	BufferedReader in = new BufferedReader(
		new InputStreamReader( new FileInputStream("test.txt"), "utf-8")
	);
	
	in.mark(1);
	char ch = (char)in.read();
	System.out.println(ch);	// 'A' -> 读取一个字节byte, 以整数形式返回
	in.reset();				// 重置流, 回到起点
	
	String line = ""; 
	while ( true ) {
		line = in.readLine();	// "A123B456" -> 读取整行
		if( line == null ){
			break;				// 直到文件末尾
		}
		sysout(line);
	}

	in.close();
	```

<br><br>

###	6. 解析输入流

*	二进制 -> InputStream

*	文本 -> Reader

*	格式化文本 -> Scanner

<br><br>

###	7. 流的应用, 网络流, socket通信

*	server

	```bash
	$ nc -l 8888

	```

	先打开nc, 作为服务器, 并监听 8888 端口

	等到客户端发来信息后, 再手动发送响应 `hi`

*	client

	```java
	try {
		// 根据 host 和 port 创建一个 socket
		Socket socket = new Socket("127.0.0.1", 8888);

		// 创建文本输出流, 并连接到socket
		PrintWriter out = new PrintWriter(
			new BufferedWriter(
				new OutputStreamWriter(
					socket.getOutputStream()
				)
			)
		);

		out.printf("hello\n");	// 将字符串写到输出流 out
		out.flush();			// 刷新缓冲区, 发送
		
		// 创建文本输入流, 连接到socket, 接收服务器返回的信息
		BufferedReader in = new BufferedReader(
			new InputStreamReader(
				socket.getInputStream()
			)
		);

		String line = in.readLine();
		System.out.println(line);
		
		out.close();
		socket.close();
		in.close();
	}catch (Exception e) {
		// ...
	}
	```

<br><br>

###	8. 对象串行化

*	使用二进制流

*	首先实现 `Serializable` 接口

	```java
	class Person implements Serializable{
		private String name;
		int age;
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		public String toString() {
			return name + ", " + age;
		}
	}
	```

*	使用 `FileOutputStream` 和 `FileInputStream`, 进行二进制读写

	使用过滤器 `ObjectInputStream` 和 `ObjectOutputStream` 进行对字节制流封装

	```java
	try {
		Person person = new Person("joe", 10);
		System.out.println(person);
		
		ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("person.dat") );
		out.writeObject(person);
		out.close();
		
		ObjectInputStream in = new ObjectInputStream( new FileInputStream("person.dat") );
		Person person2 = (Person)in.readObject();		// cast 造型
		System.out.println(person2);
		in.close();
		
	}catch (Exception e) {
		// ...
	}
	```

	注意 readObject() 之后需要 cast 成原来的对象

<br><br>

###	TODO

*	Python 实现过的Socket通信, 用Java实现

*	多线程

*	Python 实现过的Spider, 用Java实现

<br><br>