package com.benjih.ld32;

import org.newdawn.slick.opengl.Texture;

public class PlayingCard extends Sprite {
	
	private PlayingCardPosition position;

	public PlayingCard(Texture resource) {
		super(0, 0, resource);
	}
	
	public void setPosition(PlayingCardPosition position) {
		this.position = position;
		setX(position.getX());
		setY(position.getY());
	}

}
