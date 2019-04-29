###	历史

*	Java 与 Internet 一起发展

	www, 万维网, 所有信息用链接连接起来

	Java, 静态网页 -> 动态网页

*	Java的出现, 1995年, SUN, Stanford University Network, JDK 1.0

*	JDK, Java Development Kit, Java开发工具包

	```
	1995, JDK 1.0
	1998, JDK 1.2, Java2
	2000, JDK 1.3
	2002, JDK 1.4, assert, logging, re
	2004, JDK 1.5, 语法增加
	2006, JDK 1.6, 广泛, Compiler API(动态编译), 脚本语言支持, WebService支持
	2010, Oracle并购SUN
	2011, JDK 1.7, 带资源的try, 重抛异常
	2014, JDK 1.8, 大改进, lambda表达式

	注: 从 JDK 1.5 之后, JDK 1.x 也被称为 JDK x, 如 JDK 1.8 也被叫做 Java 8
	```

*	JCP, Java Community Process, 社区

	JSR, Java Specification Requests, 规范

<br><br>

###	三大平台

*	Java SE, J2SE, Java 2 Platform Standard Edition, 标准版, 桌面引用

*	Jave EE, J2EE, Java 2 Platform Enterprise Edition, 企业版, Web应用

*	Java ME, J2ME, Micro Edition, 微型版, 嵌入式设备

<br><br>

###	特点

*	跨平台, 安全稳定(不易内存溢出), 支持多线程, 丰富的类库

*	纯的面向对象，变量和方法，都在对象里头

*	C++ --

	无直接指针, 自动内存管理

	数据类型长度固定

	不使用头文件

	不支持宏

	无多重继承, (使用接口)

	不存在 类外的全局变量

<br><br>

###	三种核心机制

*	Java Virtual Machine, Java虚拟机

*	Code Security, 代码安全性检测

*	Garbage Collection, 垃圾回收

<br><br>

###	Java的编译与运行

*	src.java (源程序)

*	--- 通过 `javac` 进行编译, c代表compiler

*	---> src.class 目标文件, 字节码bytecode, 不是实际机器的最终执行代码

*	--- 通过 `java` 来运行

*	---> JVM for Winx/Unix/... (JVM模拟了一个操作系统, 或者是接口)

	JVM读取并处理 class 文件, 最终转化成CPU的指令

<br><br>

###	JVM

JVM规定了如下内容(虚拟的CPU和内存)

*	指令集合

*	寄存器集合

*	类文件结构

*	堆栈

*	垃圾收集堆

*	内存区域

<br><br>

###	JRE, Java Runtime Environment

*	在具体运行的时候, Java需要一个运行环境, JRE

*	JRE = JVM + API(Lib)

*	JRE运行程序时的三项主要功能

	加载代码、校验代码、执行代码

*	相当于一个高级解释器

*	平台无关: 把class文件放到不同的系统, 虚拟机可以执行, 不需要重新编译

<br><br>

###	垃圾回收, Garbage Collection

*	自动GC, 不需要程序员delete

	系统级线程会跟踪存储空间的分配情况

	JVM空闲时，检查和释放那些可以释放的空间

*	JRE = JVM + API

	JDK = JRE + Tools(编译工具, 打包工具, 调试工具, etc)

	开发程序需要JDK, 如果只是运行程序则JRE够了

	先从 Java SE 开始学习
