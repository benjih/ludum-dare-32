package com.benjih.ld32.core;

public class UnconventionalEffect implements Effect {
	
	@Override
	public void useEffect(Player player, Player enemy, int surprise) {
		enemy.attack(surprise);
		
		player.resetSurprise();
	}
}