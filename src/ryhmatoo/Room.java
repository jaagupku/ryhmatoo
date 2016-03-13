package ryhmatoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room {
	private List<Monster> monsters = new ArrayList<Monster>();
	private List<Item> items = new ArrayList<Item>();
	private List<Connection> connections = new ArrayList<Connection>();
	private int entranceX, entranceY;
	private Map map;
	
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
				StringBuilder sb1 = new StringBuilder(command[1]);
				while(sb1.indexOf("$") != -1){
					int startPos = sb1.indexOf("$");
					int endPos = sb1.indexOf("$", startPos+1);
					connections.add(new Connection(sb1.substring(startPos+1, endPos), startPos));
					sb1.replace(startPos, endPos+1, "0");
				}
				map.loadMapFromCharArray(sb1.toString().toCharArray());
			}
			else if (command[0].equalsIgnoreCase("player_spawn")){
				String[] values = command[1].split(",");
				entranceX = Integer.parseInt(values[0]);
				entranceY = Integer.parseInt(values[1]);
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
		StringBuilder sb = new StringBuilder((getSizeX()*2+1)*getSizeY());
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
	
	public Room getNextRoom(int x, int y, List<Room> others){
		String connectionName = null;
		for(Connection c : getConnections()){
			if(c.coordinatesEqual(x, y)){
				connectionName = c.getName();
				break;
			}
		}
		for(Room r : others){
			if(r == this){
				continue;
			}
			for(Connection c : r.getConnections()){
				if(c.getName().equals(connectionName)){
					r.setEntranceX(c.getX());
					r.setEntranceY(c.getY());
					return r;
				}
			}
		}
		return null;
	}
	
	private List<Connection> getConnections(){
		return connections;
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
	
	private void setEntranceX(int entranceX) {
		if(entranceX < 0) this.entranceX = 0;
		else if(entranceX >= getSizeX()) this.entranceX = getSizeX()-1;
		else this.entranceX = entranceX;
	}

	private void setEntranceY(int entranceY) {
		if(entranceY < 0) this.entranceY = 0;
		else if(entranceY >= getSizeY()) this.entranceY = getSizeY()-1;
		else this.entranceY = entranceY;
	}
	
	private class Connection{
		private String name;
		private int x, y;
		
		public Connection(String name, int position) {
			super();
			this.name = name;
			x = position%getSizeX();
			y = (position - x)/getSizeX();
			if(x == 0){
				x--;
			}
			else if(x == getSizeX()-1){
				x++;
			}
			else if(y == 0){
				y--;
			}
			else if(y == getSizeY()-1){
				y++;
			}
		}
		
		public boolean coordinatesEqual(int x, int y){
			return this.x == x && this.y == y;
		}

		public String getName() {
			return name;
		}

		public int getX() {
			if(x == -1){
				return x+1;
			}
			else if(x == getSizeX()){
				return x-1;
			}
			return x;
		}

		public int getY() {
			if(y == -1){
				return y+1;
			}
			else if(y == getSizeY()){
				return y-1;
			}
			return y;
		}
	}
}
