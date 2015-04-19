package com.benjih.ld32;

public class DisplayScale {
	
	private float scale;
	
	public DisplayScale (float scale) {
		this.scale = scale;
	}
	
	public int scale (int toScale) {
		return Math.round(toScale / scale);
	}
	
	public float getScale () {
		return scale;
	}
}