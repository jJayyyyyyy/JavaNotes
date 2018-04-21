###	1. 消除代码复制(函数和父类)

*	重复的代码都提取出来, 统一定义, 分开调用, 便于维护.

<br><br>

###	2. 封装

*	耦合: 类与类之间的关联程度

	对一个类做了修改, 其他的类不必修改, 而程序仍能正常运行, 这是好的.

	自己的东西放在自己的类里面, 只给出必要的对外的接口. 接口是信号, 而不是数据.

*	高内聚, 低耦合

<br><br>

###	3. 可扩展性

*	无需修改即可适应新的变化

*	接口, 把细节隐藏在类的内部

*	用`HashMap`, 而不是大量的`if-else`

	```java
	class Room{
		private HashMap<String, Room> exitMap = new HashMap<String, Room>();

		public void setExitMap(String direction, Room room){
			exitMap.put(direction, room);
		}

		public Room getExit(String direction){
			Room room = null;
			if( exitMap.containsKey(direction) ){
				room = exitMap.get(direction);
			}
			return room;
		}	
	}
	```

<br><br>

###	4. 框架+数据

*	第3节, hashmap的是对象. 如果要对函数做hashmap, 比如下面这个例子, 该怎么办呢? 

	```java
	// in main()
	while(true){
		String line = in.nextLine();
		String cmdList = line.split(" ");
		if( cmdList[0].equals("help") ){
			game.printHelp();					// if 对应函数操作
		}else if( cmdList[0].equals("go") ){
			game.goRoom(cmdList[1]);			// if 对应函数操作
		}else if( cmdList[0].equals("bye") ){
			break;								// if 退出循环
		}
	}
	```

*	框架

	硬编码 ---> HashMap + 接口 === 框架 + 数据

	定义Handler类来处理命令, 若要增加命令, 只需要增加Handler类, 在Handler类里面加方法

	框架 + 数据, 提高可扩展性

<br><br>