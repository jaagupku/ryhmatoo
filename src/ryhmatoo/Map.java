package ryhmatoo;

import java.util.Random;

public class Map {
	public static final int WALL = 1, EMPTY = 0; // Map tiles
	private int[][] cells;
	private int sizeX, sizeY;
	private Random rng = new Random();
	
	public Map(int sizeX, int sizeY){
		this.sizeX = (sizeX > 4 ? sizeX : 4);
		this.sizeY = (sizeY > 4 ? sizeY : 4);
		this.cells = new int[this.sizeX][this.sizeY];
		fillWorldRandom();
	}
	
	private void changeMapTile(int x, int y, int tile){
		cells[x][y] = tile;
	}
	
	private void fillWorldRandom(){
		for(int x = 0; x < sizeX; x++) {
			for(int y = 0; y < sizeY; y++) {
				if(x == 0 || x == sizeX-1 || y == 0 || y == sizeY-1) {
					cells[x][y] = WALL;
				} else {
					if(rng.nextInt(4)==0) cells[x][y] = WALL;
					else cells[x][y] = EMPTY;
				}
			}
		}
	}
	
	public int getCell(int x, int y){
		return cells[x][y];
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}
	
}
