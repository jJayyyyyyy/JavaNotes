###	1. 类与对象

*	类是规范, 是定义, 是概念, 是抽象

	对象是实体

*	对象 = 数据 + 操作

	数据: 属性 or 状态

	操作: 方法

	class Circle, 圆类: 数据{圆心, 半径}, 方法{可以被画出来}

*	封装: 把数据以及相应的操作集合放在一起

<br><br>

###	2. 定义类

*	```java
	package vendingmachine;

	public class VendingMachine {
		int price = 80;
		int balance;
		int total;
		
		VendingMachine(){
			total = 0;
		}

		void setPrice(int price){
			this.price = price;
		}
		
		void insertMoney(int amount) {
			balance += amount;
		}
		
		void showBalance() {
			System.out.println(balance);
		}
		
		void getFood() {
			if(balance >= price) {
				System.out.println("food");
				balance -= price;
				total += price;
			}
		}
		
		public static void main(String[] args) {
			VendingMachine vm = new VendingMachine();
			vm.showBalance();
			vm.insertMoney(100);
			vm.getFood();
			vm.showBalance();

			VendingMachine vm2 = vm;
			vm2.insertMoney(10);
			vm.showBalance();
		}
	}
	```

<br><br>

###	3. 新的对象 or 新的管理者

*	对象是实体, 可以像理解C++的指针一样理解对象实例

	*	必须new, 才会分配具体的空间

		debug的时候,  对象的id不同

	*	如果是用另一个对象对其赋值, 则相当于两个人管理同一个对象实例, 和指针一样

		```java
		
		VendingMachine vm = new VendingMachine();
		VendingMachine vm1 = vm;

		vm.showBalance();	// 0 

		vm1.insertMoney(10);// 更新vm1
		vm.showBalance();	// 10, vm也会修改, 因为两者指向同一块区域
		```

<br><br>

###	4. 成员变量与成员函数

*	如`Section 2`所示, 在类的成员函数中, 可以省略前缀`this.`, 直接通过名字来调用成员变量 or 成员函数

	但如过成员函数的参数与成员变量同名, 如 `setPrice(int price)`, 则需要使用 `this.price`

	个人感觉, this相当于一个context

*	成员变量的生存期 ---> 该对象的生存期, new 开始, GC 收集

*	成员变量的作用域 ---> 类的内部

*	相对地, 本地变量的生存期和作用域都是函数内部

<br><br>

###	对象初始化

*	函数中的本地变量

	```java
	int a;
	a++;	// error
	```

*	类里面的成员变量

	new的时候自动初始化为0 / null / false, 相当于C/C++的全局变量

*	构造函数

	与类同名, 没有返回类型

	初始化顺序如下

	```
	1.	new
	2.	先跳到构造函数(但不进入)
	3.	跳到外面的 定义初始化
		int price = 80;
		int balance;
		int total;
	
	4.	进入构造函数
	5.	把构造出来的对象交给vm管理
	```

	构造函数可以Overload, 重载(参数表不同)

<br><br>

