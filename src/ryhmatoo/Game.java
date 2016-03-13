/* TODO
 * interface for Fighter  somewhat done.
 * attackOther(Fighter other)
 * defend(int damage)
 * 
 * methods to Monster class
 * constructor for monster
 * Monster class implements Fighter
 * 
 * methods to Player class
 * Player implements Fighter
 * 
 * mängijal privaatne isendiväli Inventory inventory ?
 * et saaks kanda võtmeid seal ja muid asju, nt vb health potionid, relvad etc.
 * 
 * Maybe interface for Item
 * and make Key implement Item
 * 
 * 
 */

package ryhmatoo;

public class Game {

	static World world;

	public static void main(String[] args) {
		world = new World();
		Menu.mainMenu();
		
	}
}
