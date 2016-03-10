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
 * Switching between worlds doesn't carry over player - needs fix.
 * plus, isn't elegant yet. Needs better solution.
 * Add support for loading monsters from file.
 * 
 * Printing world needs improvements to print objects from List<Monsters> and List<Item>
 * 
 * Maybe interface for Item
 * and make Key implement Item
 * 
 * 
 */

package ryhmatoo;

import java.io.File;

public class Game {
	
	static World[] levels = new World[2];
	static World world;

	public static void main(String[] args) {
		levels[0] = new World(new File("worlds\\test.world"));
		levels[1] = new World(new File("worlds\\test2.world"));
		world = levels[0];
		Menu.displayWelcomeScreen();
		Menu.mainMenu();
		
	}
}
