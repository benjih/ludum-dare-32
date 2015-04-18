package com.benjih.ld32.core;

import com.benjih.ld32.card.Deck;
import com.benjih.ld32.card.Hand;
import com.benjih.ld32.card.PlayingCardPosition;

public class DrawEffect implements Effect {
	
	private int drawTimes;
	
	public DrawEffect (int drawTimes) {
		this.drawTimes = drawTimes;
	}

	@Override
	public void useEffect(Deck deck, Hand hand) {
		for (int i = 1; i <= drawTimes; i++) {
			PlayingCardPosition freePosition = hand.getFirstFreeSlot();
			
			if(freePosition != null) {
				hand.putCard(freePosition, deck.getTopCard());
			}
		}
		
	}
	
}