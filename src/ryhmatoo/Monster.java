package ryhmatoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Monster extends Fighter implements Drawable {
	private int x, y;
	private String image;
	// monsterStrings ja monsterStats on kahedimensioonilised massiivid.
	// monster______[id][tunnus]
	// monsterStrings'il on {"koletise nimi", "kahe täheline string, mis on
	// kaardil näha"}
	// monsterStats'il on {max elud, attack power, attack accuracy, defense,
	// agility}
	
	private static List<String[]> names = new ArrayList<String[]>();
	private static List<int[]> stats = new ArrayList<int[]>();

	public Monster(int x, int y, int id) {
		super(names.get(id)[0], stats.get(id)[0], stats.get(id)[1], stats.get(id)[2], stats.get(id)[3], stats.get(id)[4]);
		image = names.get(id)[1];
		this.x = x;
		this.y = y;

	}

	static void loadMonstersFromFile(File f) {
		Scanner sc;
		try {
			sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if(line.startsWith("//") || line.length() < 14)
					continue;
				String[] data = line.split(",");
				String[] name = { data[0], data[1] };
				names.add(name);
				int[] stat = { Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]),
						Integer.parseInt(data[5]), Integer.parseInt(data[6]) };
				stats.add(stat);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Failed to load monsters.");
			e.printStackTrace();
		}

	}

	public void move(int dir) {
		switch (dir) {
			case World.NORTH: {
			setY(getY() - 1);
			break;
			}
		case World.SOUTH: {
			setY(getY() + 1);
			break;
		}
		case World.WEST: {
			setX(getX() - 1);
			break;
		}
		case World.EAST: {
			setX(getX() + 1);
			break;
		}
		}
	}

	@Override
	public String getImage() {
		return image;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
