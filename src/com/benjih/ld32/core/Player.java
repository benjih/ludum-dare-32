package com.benjih.ld32.core;

import com.benjih.ld32.card.Deck;
import com.benjih.ld32.card.Hand;
import com.benjih.ld32.card.PlayingCard;
import com.benjih.ld32.card.PlayingCardPosition;
import com.benjih.ld32.card.effects.Effect;

public class Player {
	
	private int health;
	private int armour;
	private int deathPulseRate;
	private Deck deck;
	private Hand hand;
	private int suprise;
	private PlayingCard lastPlayed;
	
	public Player (Deck deck) {
		this.setHealth(30);
		this.setArmour(0);
		this.deathPulseRate = 1;
		this.suprise = 0;
		this.deck = deck;
		this.hand = new Hand();
		this.setLastPlayed(null);
		
		drawCard();
		drawCard();
		drawCard();
	}

	public void drawCard () {
		PlayingCardPosition freePosition = hand.getFirstFreeSlot();
		PlayingCard newCard = deck.getTopCard();
		if(newCard == null) {
			deathPulse();
		} else {
			if(freePosition != null) {
				hand.putCard(freePosition, newCard);
			}		
		}
	}
	
	public PlayingCard playCard (PlayingCardPosition positionToPlay, Player enemy) {
		PlayingCard card = hand.getCard(positionToPlay);
		if(card != null) {
			hand.useCard(positionToPlay);
			card.setPosition(PlayingCardPosition.POS_PLAYED);
			setArmour(armour + card.getArmour());
			
			enemy.attack(card.getDamage());
			
			Effect effect = card.getEffect();
			if(effect != null) {
				effect.useEffect(this, enemy, suprise);
			}
		}
		
		gainSuprise();
		
		return card;
	}
	
	private void deathPulse () {
		health = health - deathPulseRate;
		deathPulseRate++;
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

	public int getSuprise () {
		return suprise;
	}
	
	public void gainSuprise () {
		if(suprise < 6) {
			suprise++;
		}
	}

	public void attack(int damage) {
		int oldArmour = armour;
		if(armour > 0 && damage > 0) {
			setArmour(armour - damage);
			damage = damage - oldArmour;
			
			if(armour < 0) {
				setArmour(0);
			}
		}
		
		if(damage > 0) {
			setHealth(health - damage);
		}
	}

	public void resetSurprise() {
		suprise = 0;
	}

	public PlayingCard getLastPlayed () {
		return lastPlayed;
	}

	public void setLastPlayed (PlayingCard lastPlayed) {
		this.lastPlayed = lastPlayed;
	}
}