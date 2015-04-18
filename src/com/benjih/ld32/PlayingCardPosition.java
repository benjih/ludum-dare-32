package com.benjih.ld32;

public enum PlayingCardPosition {
	POS_1(25), POS_2(215), POS_3(404), POS_4(595);
	
	int x;
	int y;
	
	PlayingCardPosition (int x) {
		this.x = x;
		this.y = 338;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
}
