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
	private static Scanner sc = new Scanner(System.in);
	private static boolean autoLook = false;
	
	public static void displayWelcomeScreen() {
		List<String> welcome = new ArrayList<String>();
		File f = new File("data\\welcome.txt");
		try {
			welcome = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String s : welcome) System.out.println(s);
		System.out.println();
	}
	
	public static void mainMenu() {
		displayWelcomeScreen();
		String input;
		while(true){
			System.out.println();
			System.out.println("\t\"1\" to start the game.");
			System.out.println("\t\"2\" to exit the game.");
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
}
