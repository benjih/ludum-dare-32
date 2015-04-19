package com.benjih.ld32.core;

import com.benjih.ld32.card.Hand;
import com.benjih.ld32.card.PlayingCardPosition;

public class AIController implements PlayerController {

	@Override
	public PlayingCardPosition chooseCard(Hand hand) {
		if (hand.getCard(PlayingCardPosition.POS_1) != null) {
			return PlayingCardPosition.POS_1;
		} else if(hand.getCard(PlayingCardPosition.POS_2) != null) {
			return PlayingCardPosition.POS_2;
		} else if(hand.getCard(PlayingCardPosition.POS_3) != null) {
			return PlayingCardPosition.POS_3;
		} else if(hand.getCard(PlayingCardPosition.POS_4) != null) {
			return PlayingCardPosition.POS_4;
		} else if(hand.getCard(PlayingCardPosition.POS_5) != null) {
			return PlayingCardPosition.POS_5;
		}
		return PlayingCardPosition.POS_6;
	}

}
