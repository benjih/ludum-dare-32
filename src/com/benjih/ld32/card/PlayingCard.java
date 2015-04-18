package com.benjih.ld32.card;

import org.newdawn.slick.opengl.Texture;

import com.benjih.ld32.gl.Sprite;

public class PlayingCard extends Sprite {
	
	private int damage;
	private int armour;
	
	public PlayingCard (Texture resource, int damage, int armour) {
		super(0, 0, resource);
		this.damage = damage;
		this.armour = armour;
	}
	
	public void setPosition (PlayingCardPosition position) {
		setX(position.getX());
		setY(position.getY());
	}
	
	public int getDamage () {
		return damage;
	}
	
	public int getArmour () {
		return armour;
	}

}
