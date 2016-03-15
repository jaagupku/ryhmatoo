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
	
	public String getImage(){
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
