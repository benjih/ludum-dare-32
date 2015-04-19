package com.benjih.ld32.card.effects;

import com.benjih.ld32.core.Player;

public interface Effect {
	void useEffect (Player player, Player enemy, int surprise);
}