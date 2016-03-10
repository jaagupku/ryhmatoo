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
