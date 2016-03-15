package ryhmatoo;

import java.util.function.Predicate;

public class Monster extends Fighter implements Drawable{
	private int x, y;
	private int maxHealth, health;
	private String image;
	// monsterStrings ja monsterStats on kahedimensioonilised massiivid.
	// monster______[id][tunnus]
	// monsterStrings'il on {"koletise nimi", "kahe täheline string, mis on kaardil näha"}
	// monsterStats'il on {max elud, attack power, attack accuracy, defense, agility}
	private static final String[][] monsterStrings = {{"TestMonster1", "T1"}, {"TestMonster2", "T2"}};
	private static final int monsterStats[][] = {{140, 7, 5, 3, 3},
										   {25, 11, 7, 4, 4}};
	
	public Monster(int x, int y, int id) {
		super(monsterStrings[id][0], monsterStats[id][0], monsterStats[id][1],
				monsterStats[id][2], monsterStats[id][3], monsterStats[id][4]);
		image = monsterStrings[id][1];
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
	
	@Override
	public String getImage(){
		return image;
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	@Override
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
