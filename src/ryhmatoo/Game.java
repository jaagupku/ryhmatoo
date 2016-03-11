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
 * 
 * Add support for loading monsters from file.
 * Solution change World --> Room
 * Make new World. World contains all Rooms and connections between them. World has a Player.
 * Switching between rooms needs polishing.
 * 
 * Printing world needs improvements to print objects from List<Monsters> and List<Item>
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
		world = new World(3);
		Menu.displayWelcomeScreen();
		Menu.mainMenu();
		
	}
}
