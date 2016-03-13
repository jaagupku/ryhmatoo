package ryhmatoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class World {
	public static final int NORTH = 0, SOUTH = 1, WEST = 2, EAST = 3; // Directions
	private Player player;
	private List<Room> rooms = new ArrayList<Room>();
	private Room currentRoom;
	
	public World(int numberOfRooms) {
		for(int i=1; i<=numberOfRooms; i++) {
				File f = new File("data\\room" + i + ".txt");
				rooms.add(new Room(f));
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
	
	private void changeRooms(Room newRoom){
		
	}
	
	public void printWorld() {
		System.out.print(currentRoom.getRoomAsString(player));
	}
	
	private int getStartPos(int x, int y){
		return x*2 + (2*currentRoom.getSizeX()+1)*y;
	}
}
