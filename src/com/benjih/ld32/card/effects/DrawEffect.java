package com.benjih.ld32.card.effects;

import com.benjih.ld32.core.Player;

public class DrawEffect implements Effect {
	
	private int drawTimes;
	
	public DrawEffect (int drawTimes) {
		this.drawTimes = drawTimes;
	}

	@Override
	public void useEffect(Player player, Player enemy, int surprise) {
		for (int i = 1; i <= drawTimes; i++) {
			player.drawCard();
		}
		
	}
	
}