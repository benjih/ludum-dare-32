package com.benjih.ld32.core;

import org.lwjgl.input.Mouse;

import com.benjih.ld32.DisplayScale;
import com.benjih.ld32.card.Hand;
import com.benjih.ld32.card.PlayingCardPosition;

public class HumanController implements PlayerController {

	@Override
	public PlayingCardPosition chooseCard(Hand hand, DisplayScale displayScale) {
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_1.getX(), PlayingCardPosition.POS_1.getY(), 181, 252, displayScale)) {
			return PlayingCardPosition.POS_1;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_2.getX(), PlayingCardPosition.POS_2.getY(), 181, 252, displayScale)) {
			return PlayingCardPosition.POS_2;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_3.getX(), PlayingCardPosition.POS_3.getY(), 181, 252, displayScale)) {
			return PlayingCardPosition.POS_3;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_4.getX(), PlayingCardPosition.POS_4.getY(), 181, 252, displayScale)) {
			return PlayingCardPosition.POS_4;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_5.getX(), PlayingCardPosition.POS_5.getY(), 181, 252, displayScale)) {
			return PlayingCardPosition.POS_5;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_6.getX(), PlayingCardPosition.POS_6.getY(), 181, 252, displayScale)) {
			return PlayingCardPosition.POS_6;
		}
		
		return PlayingCardPosition.NONE;
	}

}
