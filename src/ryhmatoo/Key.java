package ryhmatoo;

public class Key {
	private int x, y;
	private String name;
	private boolean taken;

	public Key(int x, int y, String name) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		taken = false;
	}
	
	public void setTaken(boolean b){
		taken = b;
	}
	
	public boolean isTaken(){
		return taken;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}
	
}
