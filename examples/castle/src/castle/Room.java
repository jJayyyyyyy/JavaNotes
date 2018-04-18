package castle;

import java.util.HashMap;

public class Room {
	private String roomName;
	private HashMap<String, Room> exitMap = new HashMap<String, Room>();

	public Room(String roomName) {
		this.roomName = roomName;
	}

	public void setExit(String direction, Room room){
		exitMap.put(direction, room);
	}

	public void printInfo(){
		System.out.print("\nNow you are at " + roomName + ". Exits are listed below:\n");
		for( String exit: exitMap.keySet() ){
			System.out.print(exit + " \n");
		}
	}

	public Room getNextRoom(String direction){
		Room nextRoom = null;
		if( exitMap.containsKey(direction) ){
			nextRoom = exitMap.get(direction);
		}
		return nextRoom;
	}

	public String toString(){
		return roomName;
	}

}
