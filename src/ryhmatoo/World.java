package ryhmatoo;

import java.util.ArrayList;

public class World {
	public static final int NORTH = 0, SOUTH = 1, WEST = 2, EAST = 3; // Directions
	private Player player;
	private ArrayList<Monster> monsters;
	private Map map;
	
	public World(Player player, Map map) {
		super();
		this.player = player;
		this.map = map;
	}
	
	public void movePlayer(int dir){
		switch(dir){
			case World.NORTH: {
				if(map.getCell(player.getX()-1, player.getY()) != Map.WALL){
					player.setX(player.getX()-1);
				} else {
					System.out.println("You can't walk there.");
				}
				break;
			}
			case World.SOUTH: {
				if(map.getCell(player.getX()+1, player.getY()) != Map.WALL){
					player.setX(player.getX()+1);
				} else {
					System.out.println("You can't walk there.");
				}
				break;
			}
			case World.WEST: {
				if(map.getCell(player.getX(), player.getY()-1) != Map.WALL){
					player.setY(player.getY()-1);
				} else {
					System.out.println("You can't walk there.");
				}
				break;
			}
			case World.EAST: {
				if(map.getCell(player.getX(), player.getY()+1) != Map.WALL){
					player.setY(player.getY()+1);
				} else {
					System.out.println("You can't walk there.");
				}
				break;
			}
		}
	}
	
	public void printWorld() {
		for(int x = 0; x < map.getSizeX(); x++) {
			for(int y = 0; y < map.getSizeY(); y++) {
				int cell = map.getCell(x, y);
				if(cell == Map.WALL) System.out.print(Menu.BLOCK + "" + Menu.BLOCK);
				else if(player.getX() == x && player.getY() == y) System.out.print(Menu.SWORD + "" + Menu.MAN);
				else if(cell == Map.EMPTY) System.out.print(Menu.FLOOR + "" + Menu.FLOOR);
				else System.out.print("MISSING TILE");
			}
			System.out.println();
		}
	}
}
