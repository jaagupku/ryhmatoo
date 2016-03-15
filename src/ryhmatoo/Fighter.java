package ryhmatoo;

import java.util.Random;

public class Fighter {
	
	String name;
	Random rng = new Random();
	private int maxHealth, health;
	private int attackPower, attackAccuracy;
	private int defense, agility;
	
	public Fighter(String name, int maxHealth,  int attackPower, int attackAccuracy, int defense, int agility) {
		super();
		this.name = name;
		this.maxHealth = maxHealth;
		health = maxHealth;
		this.attackPower = attackPower;
		this.attackAccuracy = attackAccuracy;
		this.defense = defense;
		this.agility = agility;
	}
	
	public void attackOther(Fighter o){
		int dmg = rng.nextInt(attackPower);
		if(rng.nextInt(attackAccuracy) > o.getAgility()){
			System.out.println(getName() + " attacks " + o.getName());
			o.defendFromAttack(dmg);
			
		} else {
			System.out.println(o.getName() + " dodged the attack from " + getName() + ".");
		}
		
	}
	
	public void defendFromAttack(int dmg){
		if(rng.nextInt(getDefense()*2-1) < dmg){
			takeDamage(dmg);
			if(health > 0)
				System.out.println(getName() + " got damaged for " + dmg + " hitpoints, it now has " + getHealth() + " health.");
		} else {
			System.out.println(getName() + " successfully blocked the attack.");
		}
	}
	
	private void takeDamage(int dmg){
		health -= dmg;
		if(health <= 0){
			health = 0;
			System.out.println(getName() + " is now dead.");
		}
	}
	
	public void heal(int health){
		health = this.health + health > maxHealth ? maxHealth : this.health + health;
	}
	
	public int getDefense(){
		return defense;
	}
	
	public int getAgility(){
		return agility;
	}
	
	public String getName(){
		return name;
	}
	
	public int getHealth(){
		return health;
	}
}
