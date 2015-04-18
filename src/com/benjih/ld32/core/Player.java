package com.benjih.ld32.core;

import com.benjih.ld32.card.Deck;
import com.benjih.ld32.card.Hand;

public class Player {
	
	private int health;
	private int armour;
	private Deck deck;
	private Hand hand;
	
	public Player (Deck deck) {
		this.setHealth(30);
		this.setArmour(0);
		this.deck = deck;
		this.hand = new Hand();
		
	}

	public int getHealth () {
		return health;
	}

	public void setHealth (int health) {
		this.health = health;
	}

	public int getArmour () {
		return armour;
	}

	public void setArmour (int armour) {
		this.armour = armour;
	}
	
	public Deck getDeck () {
		return deck;
	}
	
	public Hand getHand () {
		return hand;
	}
}