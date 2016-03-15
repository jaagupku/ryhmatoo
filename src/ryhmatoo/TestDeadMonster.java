package ryhmatoo;

import java.util.function.Predicate;

public class TestDeadMonster implements Predicate<Monster>{

	@Override
	public boolean test(Monster t) {
		return t.getHealth() < 1;
	}
	
}