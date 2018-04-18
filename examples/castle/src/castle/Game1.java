package castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game1 {
	// inner class, 内部类, have access to [this]
	private class Handler{
		public boolean isBye(){
			return false;
		}
		public void doCmd(String cmd){}
	}

	private Room currentRoom;
	private HashMap<String, Handler> handlerMap = new HashMap<String, Handler>();

	public Game1() {
		// Anonymous classes, 匿名类, 省去命名的烦恼
		handlerMap.put("bye", new Handler(){
			@Override
			public boolean isBye(){
				System.out.print("\nSee you next time.");
				return true;
			}
		});

		handlerMap.put("help", new Handler(){
			@Override
			public void doCmd(String help){
				System.out.print("Input [bye] to exit. Input [help] for help. Input [go east] to go east.\n");
			}
		});

		handlerMap.put("go", new Handler(){
			@Override
			public void doCmd(String direction){
				goRoom(direction);
			}
		});

		createRoomList();
	}

	protected void goRoom(String direction){
		Room nextRoom = currentRoom.getNextRoom(direction);
		if( nextRoom == null ){
			System.out.print("There is no door in that direction\n");
		}else{
			currentRoom = nextRoom;
		}
		currentRoom.printInfo();
	}

	public void play(){
		Scanner in = new Scanner(System.in);

		while( true ){
			String[] cmdList = in.nextLine().split(" ");
			String cmd0 = cmdList[0];

			if( handlerMap.containsKey(cmd0) ){
				Handler handler = handlerMap.get(cmd0);
				if( handler.isBye() ){
					break;
				}

				String cmd1 = "";
				if( cmdList.length == 2 ){
					cmd1 = cmdList[1];
				}

				handler.doCmd(cmd1);
			}
		}
	}

	private void createRoomList() {
		Room outside, lobby, pub, study, bedroom;

		outside = new Room("outside");
		lobby = new Room("lobby");
		pub = new Room("pub");
		study = new Room("study");
		bedroom = new Room("bedroom");

		outside.setExit("east", lobby);
		lobby.setExit("west", outside);

		outside.setExit("south", study);
		study.setExit("north", outside);

		outside.setExit("west", pub);
		pub.setExit("east", outside);

		study.setExit("east", bedroom);
		bedroom.setExit("west", study);

		currentRoom = outside;
	}

	private void printWelcome() {
		System.out.print("Welcome! Input [help] for help.\n");
		currentRoom.printInfo();
	}

	public static void main(String[] args) {
		Game1 game = new Game1();
		game.printWelcome();
		game.play();

	}
}
