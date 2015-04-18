package com.benjih.ld32.core;

import com.benjih.ld32.card.Deck;
import com.benjih.ld32.card.Hand;

public interface Effect {
	void useEffect (Deck deck, Hand hand);
}
