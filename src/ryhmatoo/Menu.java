package ryhmatoo;

import java.util.Scanner;

public class Menu {
	public static final char BLOCK = '\u2588', FLOOR = '\u2591', KEY='k', DOOR = '\u00B6';
	private static Scanner sc = new Scanner(System.in);
	private static boolean autoLook = false;
	
	public static void displayWelcomeScreen() {
		String welcome = "This is filler text for welcome screen.";
		for(int i = 0; i<welcome.length()+6;i++) System.out.print(BLOCK);
		System.out.println();
		System.out.println(BLOCK +""+ BLOCK + " " + welcome + " " +  BLOCK +  BLOCK);
		System.out.println(BLOCK +""+ BLOCK + " " + welcome + " " +  BLOCK +  BLOCK);
		System.out.println(BLOCK +""+ BLOCK + " " + welcome + " " +  BLOCK +  BLOCK);
		for(int i = 0; i<welcome.length()+6;i++) System.out.print(BLOCK);
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
					if(autoLook) Game.world.printWorld();
				} else if(command[1].equalsIgnoreCase("south")) {
					Game.world.movePlayer(World.SOUTH);
					if(autoLook) Game.world.printWorld();
				} else if(command[1].equalsIgnoreCase("west")) {
					Game.world.movePlayer(World.WEST);
					if(autoLook) Game.world.printWorld();
				} else if(command[1].equalsIgnoreCase("east")) {
					Game.world.movePlayer(World.EAST);
					if(autoLook) Game.world.printWorld();
				} else {
					System.out.println("Could you just say \"move <north|south|west|east>\"");
				}
			}
		} else if(command[0].equalsIgnoreCase("take")){	
			System.out.println("TODO"); //TODO
		} else if(command[0].equalsIgnoreCase("look")) {
			if(command.length != 2){
				Game.world.printWorld();
			} else if(command[1].equalsIgnoreCase("auto")){
				autoLook = !autoLook;
				System.out.println("Auto look: " + autoLook);
			}
		} else if(command[0].equalsIgnoreCase("help")) {
			System.out.println("Commands:\nmove <north|south|west|east> - to move around.\nlook - shows map\n"
					+ "look auto - if on then prints world out after moving\n"
					+ "take - pickes up an item below player.\nexit - exits game mode.");
		} else {
			System.out.println("What was that? I don't know that word...");
		}
	}
}
