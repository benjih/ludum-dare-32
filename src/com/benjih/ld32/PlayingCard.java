package com.benjih.ld32;

import org.newdawn.slick.opengl.Texture;

public class PlayingCard extends Sprite {
	
	private PlayingCardPosition position;

	public PlayingCard(PlayingCardPosition position, Texture resource) {
		super(position.getX(), position.getY(), resource);
		this.position = position;
	}

}
