package com.benjih.ld32.gl;

import org.newdawn.slick.opengl.Texture;

import com.benjih.ld32.DisplayScale;

public class Image extends Sprite {

	public Image(int x, int y, Texture resource, float scale) {
		super(x, y, resource, new DisplayScale(scale));
	}
	
	public Image(int x, int y, Texture resource, DisplayScale displayScale) {
		super(x, y, resource, displayScale);
	}

}
