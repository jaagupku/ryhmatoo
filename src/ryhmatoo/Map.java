package ryhmatoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Map {
	public static final int WALL = 1, EMPTY = 0, DOOR = 2; // Map tiles
	private Integer[][] cells;
	private int sizeX, sizeY;
	private Random rng = new Random();
	
	public Map(int sizeX, int sizeY){
		this.sizeX = (sizeX > 4 ? sizeX : 4);
		this.sizeY = (sizeY > 4 ? sizeY : 4);
		this.cells = new Integer[this.sizeY][this.sizeX];
		fillWorldRandom();
	}
	
	public Map(File file){
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		ArrayList<Integer[]> data = new ArrayList<Integer[]>();
		while(input.hasNextLine()){
			char[] ridaChar = input.nextLine().toCharArray();
			Integer[] ridaInt = new Integer[ridaChar.length];
			for(int i=0;i<ridaChar.length;i++){
				ridaInt[i] = Character.getNumericValue(ridaChar[i]);
			}
			data.add(ridaInt);
		}
		input.close();
		sizeY = data.size();
		sizeX = data.get(0).length;
		cells = data.toArray(new Integer[sizeY][sizeX]);
	}
	
	public void changeMapTile(int x, int y, int tile){
		cells[y][x] = tile;
	}
	
	public void loadMapFromCharArray(char[] c){
		for(int x = 0; x < getSizeX(); x++) {
			for(int y = 0; y < getSizeY(); y++) {
				changeMapTile(x, y, Character.getNumericValue(c[x+y*getSizeX()]));
			}
		}
	}
	
	private void fillWorldRandom(){
		for(int x = 0; x < sizeX; x++) {
			for(int y = 0; y < sizeY; y++) {
				if(x == 0 || x == sizeX-1 || y == 0 || y == sizeY-1) {
					changeMapTile(x, y, WALL);
				} else {
					if(rng.nextInt(4)==0) changeMapTile(x, y, WALL);
					else changeMapTile(x, y, EMPTY);
				}
			}
		}
	}
	
	public int getCell(int x, int y){
		return (cells[y][x] == null ? -1 : cells[y][x]);
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}
	
}
