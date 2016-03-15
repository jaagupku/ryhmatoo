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
		// loob nii palju ruume, kui on kaustas "data" faile nimega "room<number>.txt"
		while(true) {
			f = new File("data\\room" + counter + ".txt");
			if(!f.exists()) break;
			rooms.add(new Room(f));
			counter++;
		}
		currentRoom = rooms.get(0);
		player = new Player(currentRoom.getEntranceX(), currentRoom.getEntranceY(), 100);
	}
	
	public void playerAttack(int dir){
		int x = 0;
		int y = 0;
		switch(dir){
		case World.NORTH: {
			x = player.getX();
			y = player.getY()-1;
			break;
		}
		case World.SOUTH: {
			x = player.getX();
			y = player.getY()+1;
			break;
		}
		case World.WEST: {
			x = player.getX()-1;
			y = player.getY();
			break;
		}
		case World.EAST: {
			x = player.getX()+1;
			y = player.getY();
			break;
		}
		}
		Monster m = currentRoom.getMonsterAt(x, y);
		if(m != null){
			player.attackOther(m);
		} else {
			System.out.println("You swing your sword in empty air.\nIt was pointless.");
		}
	}
	
	public void monsterTurn(){
		currentRoom.updateMonsters();
	}
	
	public void movePlayer(int dir) {
		// liigutab mängijat
		player.move(currentRoom, dir);
		if(player.getX() < 0 || player.getX() >= currentRoom.getSizeX() ||
		   player.getY() < 0 || player.getY() >= currentRoom.getSizeY()) {
			// Kui mängija liikus kaardist välja, siis see tähendab, et ta peab minema uute ruumi
			// Leiame selle ja vahetame ruumid ära.
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
