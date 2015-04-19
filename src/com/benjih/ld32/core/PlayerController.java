package com.benjih.ld32.core;

import com.benjih.ld32.DisplayScale;
import com.benjih.ld32.card.Hand;
import com.benjih.ld32.card.PlayingCardPosition;

public interface PlayerController {
	public PlayingCardPosition chooseCard(Hand hand, DisplayScale displayScale);
}