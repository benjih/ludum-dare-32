package com.benjih.ld32.card.effects;

import com.benjih.ld32.core.Player;

public class ResetSurpriseEffect implements Effect {

	@Override
	public void useEffect(Player player, Player enemy, int surprise) {
		enemy.resetSurprise();
	}

}
