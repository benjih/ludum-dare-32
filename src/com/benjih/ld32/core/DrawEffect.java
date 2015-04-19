package com.benjih.ld32.core;

public class DrawEffect implements Effect {
	
	private int drawTimes;
	
	public DrawEffect (int drawTimes) {
		this.drawTimes = drawTimes;
	}

	@Override
	public void useEffect(Player player, Player enemy) {
		for (int i = 1; i <= drawTimes; i++) {
			player.drawCard();
		}
		
	}
	
}