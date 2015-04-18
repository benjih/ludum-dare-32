package com.benjih.ld32;

import java.util.HashMap;
import java.util.Map;

public class Hand {
	
	private Map<PlayingCardPosition, PlayingCard> hand;
	
	public Hand () {
		hand = new HashMap<PlayingCardPosition, PlayingCard>();
		hand.put(PlayingCardPosition.POS_1, null);
		hand.put(PlayingCardPosition.POS_2, null);
		hand.put(PlayingCardPosition.POS_3, null);
		hand.put(PlayingCardPosition.POS_4, null);
	}
	
	public PlayingCard getCard (PlayingCardPosition position) {
		return hand.get(position);
	}
	
	public void putCard (PlayingCardPosition position, PlayingCard card) {
		hand.put(position, card);
	}
	
	public void useCard (PlayingCardPosition position) {
		hand.put(position, null);
	}
	
	public PlayingCardPosition getFirstFreeSlot () {
		if(hand.get(PlayingCardPosition.POS_1) == null) {
			return PlayingCardPosition.POS_1;
		} else if (hand.get(PlayingCardPosition.POS_2) == null) {
			return PlayingCardPosition.POS_2;
		} else if (hand.get(PlayingCardPosition.POS_3) == null) {
			return PlayingCardPosition.POS_3;
		} else if (hand.get(PlayingCardPosition.POS_4) == null) {
			return PlayingCardPosition.POS_4;
		}
		
		return null;
	}
	
	public void render () {
		for(PlayingCard card : hand.values()) {
			if(card != null) {
				card.render();
			}
		}
	}
}
