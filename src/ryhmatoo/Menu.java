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
	// Konstandid
	public static final char BLOCK = '\u2588', FLOOR = '\u2591', KEY='k', DOOR = '\u00B6';
	public static final int BLACK = 0, RED = 1, GREEN = 2, YELLOW = 3, BLUE = 4, PURPLE = 5, CYAN = 6, WHITE = 7;
	// Muud muutujad.
	private static Scanner sc = new Scanner(System.in);
	private static boolean autoLook = false;
	private static boolean textColored = true;
	private static boolean monsterTurn = false;
	
	public static void displayWelcomeScreen() {
		// Väljastab tervitus teksti, failist welcome.txt
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
		// Väljastab welcome screeni ja läheb menüü tsüklisse.
		displayWelcomeScreen();
		String input;
		while(true){
			// Väljastab valikud
			System.out.println();
			System.out.println("\t\"1\" to start the game.");
			System.out.println("\t\"2\" to toggle text colors.");
			System.out.println("\t\"3\" to exit the game.");
			input = sc.nextLine();
			// Kontrollib sisestust
			if(input.equalsIgnoreCase("1")) {
				game();
			} else if(input.equalsIgnoreCase("2")) {
				textColored = !textColored;
				System.out.println("Colored text is now " + (textColored ? "on" : "off") + ".");
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
			// Küsib kasutajalt käsku
			input = sc.nextLine();
			if(input.equalsIgnoreCase("exit")){ // Kui oli exit, siis väljub mängu tsüklist 
												//ja läheb menüü oma edasi
				System.out.println("\n\n");
				displayWelcomeScreen();
				break;
			}
			Menu.processCommand(input);
			if(monsterTurn){
				Game.world.monsterTurn();
				monsterTurn = false;
			}
			System.out.println();
		}
	}
	
	private static void processCommand(String input) {
		// Lõikab käsu tühiku kaupa massiivi.
		String[] command = input.split(" ");
		
		// Kontrollib käsud läbi.
		if(command[0].equalsIgnoreCase("move")) {
			if(command.length != 2){
				System.out.println("Move where? \"move <north|south|west|east>\"");
			} else {
				if(command[1].equalsIgnoreCase("north")) {
					Game.world.movePlayer(World.NORTH);
					if(autoLook) Game.world.printWorld();
					monsterTurn = true;
				} else if(command[1].equalsIgnoreCase("south")) {
					Game.world.movePlayer(World.SOUTH);
					if(autoLook) Game.world.printWorld();
					monsterTurn = true;
				} else if(command[1].equalsIgnoreCase("west")) {
					Game.world.movePlayer(World.WEST);
					if(autoLook) Game.world.printWorld();
					monsterTurn = true;
				} else if(command[1].equalsIgnoreCase("east")) {
					Game.world.movePlayer(World.EAST);
					if(autoLook) Game.world.printWorld();
					monsterTurn = true;
				} else {
					System.out.println("What is " + command[1] + "? \"move <north|south|west|east>\"");
				}
			}
		} else if(command[0].equalsIgnoreCase("take")){	
			System.out.println("TODO"); //TODO
			monsterTurn = true;
		} else if(command[0].equalsIgnoreCase("attack")){
			if(command.length != 2){
				System.out.println("Attack in what direction? \"attack <north|south|west|east>\"");
			} else {
				if(command[1].equalsIgnoreCase("north")) {
					Game.world.playerAttack(World.NORTH);
					monsterTurn = true;
				} else if(command[1].equalsIgnoreCase("south")) {
					Game.world.playerAttack(World.SOUTH);
					monsterTurn = true;
				} else if(command[1].equalsIgnoreCase("west")) {
					Game.world.playerAttack(World.WEST);
					monsterTurn = true;
				} else if(command[1].equalsIgnoreCase("east")) {
					Game.world.playerAttack(World.EAST);
					monsterTurn = true;
				} else {
					System.out.println("What is " + command[1] + "? \"attack <north|south|west|east>\"");
				}
			}
		} else if(command[0].equalsIgnoreCase("look")) {
			if(command.length != 2){
				Game.world.printWorld();
			} else if(command[1].equalsIgnoreCase("auto")){
				autoLook = !autoLook;
				System.out.println("Auto calling look command is " + (autoLook ? "on" : "off") + ".");
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
		// Kui terminal/konsool toetab ANSI escape koode, siis värvib teksti ära.
		if(!textColored) return text;
		StringBuilder sb = new StringBuilder();
		switch(color){
		case BLACK:
			sb.append("\u001B[30m");
			break;
		case RED:
			sb.append("\u001B[31m");
			break;
		case GREEN:
			sb.append("\u001B[32m");
			break;
		case YELLOW:
			sb.append("\u001B[33m");
			break;
		case BLUE:
			sb.append("\u001B[34m");
			break;
		case PURPLE:
			sb.append("\u001B[35m");
			break;
		case CYAN:
			sb.append("\u001B[36m");
			break;
		case WHITE:
			sb.append("\u001B[37m");
			break;
		}
		sb.append(text);
		sb.append("\u001B[0m");
		return sb.toString();
	}
}
