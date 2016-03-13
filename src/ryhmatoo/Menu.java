package ryhmatoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	public static final char BLOCK = '\u2588', FLOOR = '\u2591', KEY='k', DOOR = '\u00B6';
	public static final int BLACK = 0, RED = 1, GREEN = 2, YELLOW = 3, BLUE = 4, PURPLE = 5, CYAN = 6, WHITE = 7;
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_BLACK = "\u001B[30m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final String ANSI_WHITE = "\u001B[37m";
	private static Scanner sc = new Scanner(System.in);
	private static boolean autoLook = false;
	public static boolean textColored = true;
	
	public static void displayWelcomeScreen() {
		List<String> welcome = new ArrayList<String>();
		File f = new File("data\\welcome.txt");
		try {
			welcome = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String s : welcome) System.out.println(colorText(s, RED));
		System.out.println();
	}
	
	public static void mainMenu() {
		displayWelcomeScreen();
		String input;
		while(true){
			System.out.println();
			System.out.println("\t\"1\" to start the game.");
			System.out.println("\t\"2\" to toggle text colors.");
			System.out.println("\t\"3\" to exit the game.");
			input = sc.nextLine();
			if(input.equalsIgnoreCase("1")) {
				game();
			} else if(input.equalsIgnoreCase("2")) {
				textColored = !textColored;
			} else if(input.equalsIgnoreCase("3")) {
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
			if(input.equalsIgnoreCase("exit")){
				System.out.println("\n\n");
				displayWelcomeScreen();
				break;
			}
			Menu.processCommand(input);
			System.out.println();
		}
	}
	
	private static void processCommand(String input) {
		String[] command = input.split(" ");
		
		if(command[0].equalsIgnoreCase("move")) {
			if(command.length != 2){
				System.out.println("Move where? \"move <north|south|west|east>\"");
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
					System.out.println("What is " + command[1] + "? \"move <north|south|west|east>\"");
				}
			}
		} else if(command[0].equalsIgnoreCase("take")){	
			System.out.println("TODO"); //TODO
		} else if(command[0].equalsIgnoreCase("attack")){
			if(command.length != 2){
				System.out.println("Attack in what direction? \"attack <north|south|west|east>\"");
			} else {
				if(command[1].equalsIgnoreCase("north")) {
				Game.world.playerAttack(World.NORTH);
				} else if(command[1].equalsIgnoreCase("south")) {
				Game.world.playerAttack(World.SOUTH);
				} else if(command[1].equalsIgnoreCase("west")) {
				Game.world.playerAttack(World.WEST);
				} else if(command[1].equalsIgnoreCase("east")) {
				Game.world.playerAttack(World.EAST);
				} else {
				System.out.println("What is " + command[1] + "? \"attack <north|south|west|east>\"");
				}
			}
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
					+ "attack <north|south|west|east> - attacks in that direction.\n"
					+ "take - pickes up an item below player.\nexit - exits game mode.");
		} else {
			System.out.println("What was that? I don't know that word...");
		}
	}
	
	public static String colorText(String text, int color){
		if(!textColored) return text;
		StringBuilder sb = new StringBuilder();
		switch(color){
		case BLACK:
			sb.append(ANSI_BLACK);
			break;
		case RED:
			sb.append(ANSI_RED);
			break;
		case GREEN:
			sb.append(ANSI_GREEN);
			break;
		case YELLOW:
			sb.append(ANSI_YELLOW);
			break;
		case BLUE:
			sb.append(ANSI_BLUE);
			break;
		case PURPLE:
			sb.append(ANSI_PURPLE);
			break;
		case CYAN:
			sb.append(ANSI_CYAN);
			break;
		case WHITE:
			sb.append(ANSI_WHITE);
			break;
		}
		sb.append(text);
		sb.append(ANSI_RESET);
		return sb.toString();
	}
}
