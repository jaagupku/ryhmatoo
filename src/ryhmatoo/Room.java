package ryhmatoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room {
	private List<Monster> monsters = new ArrayList<Monster>();
	private List<Item> items = new ArrayList<Item>();
	private int entranceX, entranceY;
	private Map map;
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	private Integer[] roomsAroundThis = new Integer[4];
	
	public Room(File file){
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
				entranceX = Integer.parseInt(values[0]);
				entranceY = Integer.parseInt(values[1]);
			}
			else if (command[0].equalsIgnoreCase("room_left")){
				roomsAroundThis[LEFT] = Integer.parseInt(command[1]);
			}
			else if (command[0].equalsIgnoreCase("room_right")){
				roomsAroundThis[RIGHT] = Integer.parseInt(command[1]);
			}
			else if (command[0].equalsIgnoreCase("room_up")){
				roomsAroundThis[UP] = Integer.parseInt(command[1]);
			}
			else if (command[0].equalsIgnoreCase("room_down")){
				roomsAroundThis[DOWN] = Integer.parseInt(command[1]);
			}
			else if (command[0].equalsIgnoreCase("add_monster")){
				String[] values = command[1].split(",");
				monsters.add(new Monster(Integer.parseInt(values[0]), 
										 Integer.parseInt(values[1]), 
										 Integer.parseInt(values[2])));
			}
		}
	}
	
	public boolean isCellEmpty(int x, int y){
		if(x < 0 || x >= getSizeX() || y < 0 || y >= getSizeY()){
			return true;
		}
		if(getCell(x, y) == Map.WALL){
			return false;
		}
		for(Monster m : monsters){
			if(m.getX() == x && m.getY() == y){
				return false;
			}
		}
		return true;
	}
	
	public String getRoomAsString(Player player){
		StringBuilder sb = new StringBuilder((getSizeY()+2) * getSizeX() * 1);
		for(int y = 0; y < getSizeY(); y++) {
			for(int x = 0; x < getSizeX(); x++) {
				int cell = getCell(x, y);
				if(cell == Map.WALL) {
					sb.append(Menu.BLOCK);
					sb.append(Menu.BLOCK);
				}
				else if(cell == Map.DOOR) {
					sb.append(Menu.DOOR);
					sb.append(Menu.DOOR);
				}
				else if(cell == Map.EMPTY) {
					sb.append(Menu.FLOOR);
					sb.append(Menu.FLOOR);
				}
			}
			sb.append("\n");
		}
		List<Drawable> tempDrawList = new ArrayList<Drawable>(items);
		tempDrawList.addAll(monsters);
		tempDrawList.add(player);
		for(Drawable d : tempDrawList){
			int start = getStartPos(d.getX(), d.getY());
			sb.replace(start, start+2, d.getImage());
		}
		return sb.toString();
	}
	
	private int getStartPos(int x, int y){
		return x*2 + (2*getSizeX()+1)*y;
	}
	
	public int getRoomNext(int side){
		if(roomsAroundThis[side] == null) return -1;
		return roomsAroundThis[side];
	}
	
	public int getCell(int x, int y){
		return map.getCell(x, y);
	}
	
	public int getSizeX() {
		return map.getSizeX();
	}

	public int getSizeY() {
		return map.getSizeY();
	}

	public int getEntranceX() {
		return entranceX;
	}

	public int getEntranceY() {
		return entranceY;
	}

	public void setEntranceX(int entranceX) {
		if(entranceX < 0) this.entranceX = 0;
		else if(entranceX >= getSizeX()) this.entranceX = getSizeX()-1;
		else this.entranceX = entranceX;
	}

	public void setEntranceY(int entranceY) {
		if(entranceY < 0) this.entranceY = 0;
		else if(entranceY >= getSizeY()) this.entranceY = getSizeY()-1;
		else this.entranceY = entranceY;
	}
}
