package ryhmatoo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class World {
	public static final int NORTH = 0, SOUTH = 1, WEST = 2, EAST = 3; // Directions
	private Player player;
	private List<Room> rooms = new ArrayList<Room>();
	private Room currentRoom;
	
	public World() {
		int counter = 0;
		File f;
		while(true) {
			f = new File("data\\room" + counter + ".txt");
			if(!f.exists()) break;
			rooms.add(new Room(f));
			counter++;
		}
		currentRoom = rooms.get(0);
		player = new Player(currentRoom.getEntranceX(), currentRoom.getEntranceY(), 100);
	}
	
	public void movePlayer(int dir) {
		player.move(currentRoom, dir);
		if(player.getX() < 0 || player.getX() >= currentRoom.getSizeX() ||
		   player.getY() < 0 || player.getY() >= currentRoom.getSizeY()) {
			Room nextRoom = currentRoom.getNextRoom(player.getX(), player.getY(), rooms);
			if(nextRoom != null) currentRoom = nextRoom;
			player.setX(currentRoom.getEntranceX());
			player.setY(currentRoom.getEntranceY());
		}
	}
	
	public void printWorld() {
		System.out.print(currentRoom.getRoomAsString(player));
	}
}
