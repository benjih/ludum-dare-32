package com.benjih.ld32.card;

public enum PlayingCardPosition {
	POS_1(364, 800), POS_2(566, 800), POS_3(768, 800), 
	POS_4(970, 800), POS_5(1172, 800), POS_6(1374, 800),
	POS_PLAYED(768, 400), NONE(0, 0), POS_ENEMY_PLAYED(972, 400);
	
	int x;
	int y;
	
	PlayingCardPosition (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
}
