package com.benjih.ld32;

public enum PlayingCardPosition {
	POS_1(25, 338), POS_2(215, 338), POS_3(404, 338), POS_4(595, 338),
	POS_PLAYED(310, 40);
	
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
