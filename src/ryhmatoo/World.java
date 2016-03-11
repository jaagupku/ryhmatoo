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
		player.move(this, dir);
		int nextRoom = -1;
		if(player.getX() < 0){
			nextRoom = currentRoom.getRoomNext(Room.LEFT);
		}
		else if(player.getX() >= currentRoom.getSizeX()){
			nextRoom = currentRoom.getRoomNext(Room.RIGHT);
		}
		else if(player.getY() < 0){
			nextRoom = currentRoom.getRoomNext(Room.UP);
		}
		else if(player.getY() >= currentRoom.getSizeY()){
			nextRoom = currentRoom.getRoomNext(Room.DOWN);
		}
		if(nextRoom != -1){
			changeRooms(nextRoom-1);
		}
	}
	
	private void changeRooms(int newRoom){
		currentRoom.setEntranceX(player.getX());
		currentRoom.setEntranceY(player.getY());
		currentRoom = rooms.get(newRoom);
		player.setX(currentRoom.getEntranceX());
		player.setY(currentRoom.getEntranceY());
	}
	
	public boolean isCellEmpty(int x, int y){
		if(x < 0 || x >= currentRoom.getSizeX() || y < 0 || y >= currentRoom.getSizeY()){
			return true;
		}
		if(currentRoom.getCell(x, y) == Map.WALL){
			return false;
		}
		return true;
	}
	
	public void printWorld() {
		StringBuilder sb = currentRoom.getRoomAsString();
		int start = player.getX()*2 + (2*currentRoom.getSizeX()+1)*player.getY();
		sb.replace(start, start+2, Menu.SWORD + "" + Menu.MAN);
		System.out.print(sb.toString());
	}
}
