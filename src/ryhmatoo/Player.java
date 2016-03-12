package ryhmatoo;

public class Player implements Fighter, Drawable {
	private int x, y;
	private int maxHealth, health;
	private int xp, level;
	private final char SWORD = 'i', MAN = '\u263A';
	
	public Player(int x, int y, int maxHealth) {
		super();
		this.x = x;
		this.y = y;
		this.maxHealth = maxHealth;
		health = maxHealth;
	}
	
	public void move(Room r, int dir){
		switch(dir){
		case World.NORTH: {
			if(r.isCellEmpty(getX(), getY()-1)){
				setY(getY()-1);
			} else {
				System.out.println("You can't walk there.");
			}
			break;
		}
		case World.SOUTH: {
			if(r.isCellEmpty(getX(), getY()+1)){
				setY(getY()+1);
			} else {
				System.out.println("You can't walk there.");
			}
			break;
		}
		case World.WEST: {
			if(r.isCellEmpty(getX()-1, getY())){
				setX(getX()-1);
			} else {
				System.out.println("You can't walk there.");
			}
			break;
		}
		case World.EAST: {
			if(r.isCellEmpty(getX()+1, getY())){
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
	
	public String getImage(){
		return SWORD + "" + MAN;
	}

	@Override
	public void attackOther(Fighter o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defendFromAttack(int dmg) {
		// TODO Auto-generated method stub
		
	}
}
