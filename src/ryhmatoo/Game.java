package ryhmatoo;

import java.util.Scanner;

public class Game {
	
	static Map level1;
	static World w;

	public static void main(String[] args) {
		level1 = new Map(13, 13);
		w = new World(new Player(1, 1, 100), level1);
		Menu.displayWelcomeScreen();
		Menu.mainMenu();
		
	}

}
