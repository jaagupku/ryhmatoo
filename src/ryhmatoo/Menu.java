package ryhmatoo;

import java.util.Scanner;

public class Menu {
	public static final char BLOCK = '\u2588', FLOOR = '\u2591', SWORD = 'i', MAN = '\u263A', KEY='k', DOOR = '\u00B6';
	private static Scanner sc = new Scanner(System.in);
	
	public static void displayWelcomeScreen() {
		String welcome = "This is filler text for welcome screen.";
		for(int i = 0; i<welcome.length()+4;i++) System.out.print(BLOCK);
		System.out.println();
		System.out.println(BLOCK + " " + welcome + " " +  BLOCK);
		System.out.println(BLOCK + " " + welcome + " " +  BLOCK);
		System.out.println(BLOCK + " " + welcome + " " +  BLOCK);
		for(int i = 0; i<welcome.length()+4;i++) System.out.print(BLOCK);
		System.out.println();
	}
	
	public static void mainMenu() {
		String input;
		while(true){
			System.out.println();
			System.out.println("\"1\" to start the game.");
			System.out.println("\"2\" to exit the game.");
			input = sc.nextLine();
			if(input.equalsIgnoreCase("1")) {
				game();
			} else if(input.equalsIgnoreCase("2")) {
				break;
			} else {
				System.out.println("That wasn't an option. Please try again!");
			}
		}
	}
	
	private static void game() {
		String input;
		System.out.println("Type \"help\" for help or \"exit\" to exit game.");
		while(true) {
			input = sc.nextLine();
			if(input.equalsIgnoreCase("exit")) break;
			Menu.processCommand(input);
			System.out.println();
		}
	}
	
	private static void processCommand(String input) {
		String[] command = input.split(" ");
		
		if(command[0].equalsIgnoreCase("move")) {
			if(command.length != 2){
				System.out.println("Could you just say \"move <north|south|west|east>\"");
			} else {
				if(command[1].equalsIgnoreCase("north")) {
					Game.world.movePlayer(World.NORTH);
				} else if(command[1].equalsIgnoreCase("south")) {
					Game.world.movePlayer(World.SOUTH);
				} else if(command[1].equalsIgnoreCase("west")) {
					Game.world.movePlayer(World.WEST);
				} else if(command[1].equalsIgnoreCase("east")) {
					Game.world.movePlayer(World.EAST);
				} else {
					System.out.println("Could you just say \"move <north|south|west|east>\"");
				}
			}
		} else if(command[0].equalsIgnoreCase("take")){	
			Game.world.take();
		} else if(command[0].equalsIgnoreCase("look")) {
			Game.world.printWorld();
		} else if(command[0].equalsIgnoreCase("help")) {
			System.out.println("Commands:\nmove <north|south|west|east> - to move around.\nlook - shows map\nexit - exits game mode.");
		} else {
			System.out.println("What was that? I don't know that word...");
		}
	}
}
