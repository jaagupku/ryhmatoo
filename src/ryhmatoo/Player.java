package ryhmatoo;

public class Player extends Fighter implements Drawable {
	private int x, y;
	private int xp, level;
	private final char SWORD = 'i', MAN = '\u263A';
	
	public Player(int x, int y, int maxHealth) {
		super("Player", maxHealth, 10, 7, 3, 2);
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
	
	public String getImage(){
		return SWORD + "" + MAN;
	}
}
