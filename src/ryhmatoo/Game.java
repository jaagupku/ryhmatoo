/* TODO
 * 
 * mängijal privaatne isendiväli Inventory inventory ?
 * et saaks kanda võtmeid seal ja muid asju, nt vb health potionid, relvad etc.
 * 
 * Maybe interface for Item
 * and make Key implement Item
 * 
 */

// Et eclipses värvid ka töötaksid
//https://marketplace.eclipse.org/content/ansi-escape-console

package ryhmatoo;

import java.io.File;

public class Game {

	static World world;

	public static void main(String[] args) {
		Monster.loadMonstersFromFile(new File("data\\monsters.txt"));
		world = new World();
		Menu.mainMenu();

	}
}