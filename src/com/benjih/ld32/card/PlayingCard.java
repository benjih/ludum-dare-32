package com.benjih.ld32.card;

import org.newdawn.slick.opengl.Texture;

import com.benjih.ld32.DisplayScale;
import com.benjih.ld32.card.effects.Effect;
import com.benjih.ld32.gl.Sprite;

public class PlayingCard extends Sprite {
	
	private int damage;
	private int armour;
	private Effect effect;
	private String name;
	
	public PlayingCard (String name, Texture resource, int damage, int armour, Effect effect, DisplayScale displayScale) {
		super(0, 0, resource, displayScale);
		this.name = name;
		this.damage = damage;
		this.armour = armour;
		this.effect = effect;
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
	
	public Effect getEffect () {
		return effect;
	}
	
	public String getName () {
		return name;
	}

}
