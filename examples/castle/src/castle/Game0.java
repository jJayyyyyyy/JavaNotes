package castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game0 {
	private Room currentRoom;
	private HashMap<String, Handler> handlerMap = new HashMap<String, Handler>();

	public Game0() {
		handlerMap.put("bye", new HandlerBye());
		handlerMap.put("help", new HandlerHelp());
		handlerMap.put("go", new HandlerGo(this));	// 注入Game0, this是一个Game0的实例指针

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
		Game0 game = new Game0();
		game.printWelcome();
		game.play();

	}
}
