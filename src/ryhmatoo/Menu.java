package ryhmatoo;

public class Menu {
	public static final char BLOCK = '\u2588', FLOOR = '\u2591', SWORD = 'i', MAN = '\u263A';
	
	public static void displayWelcomeScreen(){
		String welcome = "This is filler text for welcome screen.";
		for(int i = 0; i<welcome.length()+4;i++) System.out.print(BLOCK);
		System.out.println();
		System.out.println(BLOCK + " " + welcome + " " +  BLOCK);
		System.out.println(BLOCK + " " + welcome + " " +  BLOCK);
		System.out.println(BLOCK + " " + welcome + " " +  BLOCK);
		for(int i = 0; i<welcome.length()+4;i++) System.out.print(BLOCK);
		System.out.println();
	}
}
