package ryhmatoo;

public class Player {
	private int x, y;
	private int maxHealth, health;
	
	public Player(int x, int y, int maxHealth) {
		super();
		this.x = x;
		this.y = y;
		this.maxHealth = maxHealth;
	}
	
	public void move(World w, int dir){
		switch(dir){
		case World.NORTH: {
			if(w.isCellEmpty(getX(), getY()-1)){
				setY(getY()-1);
			} else {
				System.out.println("You can't walk there.");
			}
			break;
		}
		case World.SOUTH: {
			if(w.isCellEmpty(getX(), getY()+1)){
				setY(getY()+1);
			} else {
				System.out.println("You can't walk there.");
			}
			break;
		}
		case World.WEST: {
			if(w.isCellEmpty(getX()-1, getY())){
				setX(getX()-1);
			} else {
				System.out.println("You can't walk there.");
			}
			break;
		}
		case World.EAST: {
			if(w.isCellEmpty(getX()+1, getY())){
				setX(getX()+1);
			} else {
				System.out.println("You can't walk there.");
			}
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
}
