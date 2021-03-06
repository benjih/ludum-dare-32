package com.benjih.ld32.card;

import java.util.HashMap;
import java.util.Map;

import com.benjih.ld32.gl.Image;

public class Hand {
	
	private Map<PlayingCardPosition, PlayingCard> hand;
	
	public Hand () {
		hand = new HashMap<PlayingCardPosition, PlayingCard>();
		hand.put(PlayingCardPosition.POS_1, null);
		hand.put(PlayingCardPosition.POS_2, null);
		hand.put(PlayingCardPosition.POS_3, null);
		hand.put(PlayingCardPosition.POS_4, null);
		hand.put(PlayingCardPosition.POS_5, null);
		hand.put(PlayingCardPosition.POS_6, null);
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
		} else if (hand.get(PlayingCardPosition.POS_5) == null) {
			return PlayingCardPosition.POS_5;
		} else if (hand.get(PlayingCardPosition.POS_6) == null) {
			return PlayingCardPosition.POS_6;
		}
		
		return null;
	}
	
	public void render () {
		for(PlayingCardPosition cardPosition : hand.keySet()) {
			PlayingCard card = hand.get(cardPosition);
			if(card != null) {
				card.setPosition(cardPosition);
				card.render();
			}
		}
	}

	public void renderHidden (Image cardBack) {
		for(PlayingCardPosition cardPosition : hand.keySet()) {
			PlayingCard card = hand.get(cardPosition);
			if(card != null) {
				cardBack.setX(cardPosition.getX());
				cardBack.setY(16);
				cardBack.render();
			}
		}
	}

	public boolean isEmpty() {
		int empty = 0;
		for(PlayingCard card : hand.values()) {
			if(card == null) {
				empty++;
			}
		}
		return empty == 6;
	}
}
