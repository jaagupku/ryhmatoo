package ryhmatoo;

import java.util.function.Predicate;

public class Monster extends Fighter implements Drawable{
	private int x, y;
	private int maxHealth, health;
	private String image;
	private static final String[][] monsterNames = {{"TestMonster1", "T1"}, {"TestMonster2", "T2"}};
	private static int monsterStats[][] = {{14, 10, 5, 3, 1},
										   {25, 15, 7, 4, 2}};
	
	public Monster(int x, int y, int id) {
		super(monsterNames[id][0], monsterStats[id][0], monsterStats[id][1],
				monsterStats[id][2], monsterStats[id][3], monsterStats[id][4]);
		image = monsterNames[id][1];
		this.x = x;
		this.y = y;
		
	}
	
	public void move(int dir){
		switch(dir){
			case World.NORTH: {
				setY(getY()-1);
				break;
			}
			case World.SOUTH: {
				setY(getY()+1);
				break;
			}
			case World.WEST: {
				setX(getX()-1);
				break;
			}
			case World.EAST: {
				setX(getX()+1);
				break;
			}
		}
	}
	
	public String getImage(){
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
