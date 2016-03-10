package ryhmatoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public World(File file){
		StringBuilder sb = new StringBuilder();
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(input.hasNextLine()){
			String line = input.nextLine();
			if(!line.startsWith("//")) sb.append(line);
		}
		input.close();
		String[] mapLines = sb.toString().split(";");
		for(String st : mapLines){
			String[] command = st.split("=");
			if(command[0].equalsIgnoreCase("map_size")){
				String[] values = command[1].split(",");
				map = new Map(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
			}
			else if (command[0].equalsIgnoreCase("map_tiles")){
				map.loadMapFromCharArray(command[1].toCharArray());
			}
			else if (command[0].equalsIgnoreCase("player_spawn")){
				String[] values = command[1].split(",");
				player = new Player(Integer.parseInt(values[0]), Integer.parseInt(values[1]), 100);
			}
		}
	}
	
	public void movePlayer(int dir){
		player.move(this, dir);
	}
	
	public boolean isCellEmpty(int x, int y){
		return map.getCell(x, y) != Map.WALL;
	}
	
	public void printWorld() {
		for(int y = 0; y < map.getSizeY(); y++) {
			for(int x = 0; x < map.getSizeX(); x++) {
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
