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
