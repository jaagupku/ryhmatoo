package ryhmatoo;

import java.util.Scanner;

public class RPGMäng {
	
	static Map level1;
	static World w;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input;
		level1 = new Map(10, 10);
		w = new World(new Player(1, 1, 100), level1);
		Menu.displayWelcomeScreen();
		
		while(true){
			input = sc.nextLine();
			if(input.equalsIgnoreCase("exit")) break;
			processCommand(input);
		}
	}
	
	public static void processCommand(String input) {
		String[] command = input.split(" ");
		
		if(command[0].equalsIgnoreCase("move")){
			if(command.length != 2){
				System.out.println("Could you just say \"move <north|south|west|east>\"");
			} else {
				if(command[1].equalsIgnoreCase("north")){
					w.movePlayer(World.NORTH);
				} else if(command[1].equalsIgnoreCase("south")){
					w.movePlayer(World.SOUTH);
				} else if(command[1].equalsIgnoreCase("west")){
					w.movePlayer(World.WEST);
				} else if(command[1].equalsIgnoreCase("east")){
					w.movePlayer(World.EAST);
				} else {
					System.out.println("Could you just say \"move <north|south|west|east>\"");
				}
			}
		} else if(command[0].equalsIgnoreCase("look")) {
			w.printWorld();
		} else {
			System.out.println("What was that? I don't know that word...");
		}
	}

}
