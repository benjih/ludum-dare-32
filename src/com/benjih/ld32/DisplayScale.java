package com.benjih.ld32;

public class DisplayScale {
	
	private float scaleX;
	private float scaleY;
	
	public DisplayScale (float x, float y) {
		this.scaleX = 1920 / x ;
		this.scaleY = 1080 / y ;
	}
	
	public int scaleX (int toScale) {
		if(scaleX == 1f) {
			return toScale;
		}
		return Math.round(toScale / scaleX);
	}
	
	public int scaleY (int toScale) {
		if(scaleY == 1f) {
			return toScale;
		}
		return Math.round(toScale / scaleY);
	}
	
	public float scaleFont(int toScale) {
		return toScale / scaleX;
	}
}