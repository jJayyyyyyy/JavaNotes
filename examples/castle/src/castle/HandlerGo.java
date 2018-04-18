package castle;

public class HandlerGo extends Handler {
	private Game0 game;

	public HandlerGo(Game0 game){
		// 类似C++的引用, 与Game.java中的this共享该game对象的管理权
		this.game = game;
	}

	@Override
	public void doCmd(String direction){
		// 不能直接操作 Game.java 中的 game和currentRoom, 但是可以通过公开的goRoom接口操作和改变那些对象
		game.goRoom(direction);
	}

}
