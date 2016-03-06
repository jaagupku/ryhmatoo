package ryhmatoo;

import java.io.File;
import java.util.Scanner;

public class Game {

	static World w;

	public static void main(String[] args) {
		w = new World(new File("C:\\oop\\rühmatöö\\src\\ryhmatoo\\test.world"));
		Menu.displayWelcomeScreen();
		Menu.mainMenu();
		
	}

}
