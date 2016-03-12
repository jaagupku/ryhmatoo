package ryhmatoo;

public class Monster implements Fighter, Drawable {
	private int x, y;
	private int maxHealth, health;
	
	public Monster(int x, int y, int id) {
		super();
		this.x = x;
		this.y = y;
		switch(id){
			/* TODO
			 * Koletised id järgi, näiteks, kui id on 0, siis see tähendab, 
			 * et koletis on zombi, kellel on x elusi, y damage jne
			 * kui id 1, siis näiteks on Hiigel rott vms ja eludega z. Või näiteks 
			 * Et siis on elusi randomiga. Random.nextInt(10)+30 annab suvalise numbri 30-39
			 * ja muud ka umbes nii.
			 */
		}
	}
	@Override
	public void attackOther(Fighter o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void defendFromAttack(int dmg) {
		// TODO Auto-generated method stub
		
	}
	
	public String getImage(){
		return "Mo"; //TODO
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
