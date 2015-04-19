package com.benjih.ld32.core;

import com.benjih.ld32.card.Deck;
import com.benjih.ld32.card.Hand;
import com.benjih.ld32.card.PlayingCard;
import com.benjih.ld32.card.PlayingCardPosition;

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

	public void drawCard () {
		PlayingCardPosition freePosition = hand.getFirstFreeSlot();
		PlayingCard newCard = deck.getTopCard();
		if(newCard == null) {
			setHealth(health - 1);
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
			
			int damage = card.getDamage();
			int enemyArmour = enemy.getArmour();
			if(enemy.getArmour() > 0 && damage > 0) {
				enemy.setArmour(enemyArmour - damage);
				damage = damage - enemyArmour;
				
				if(enemy.getArmour() < 0) {
					enemy.setArmour(0);
				}
			}
			
			if(damage > 0) {
				enemy.setHealth(enemy.getHealth() - damage);
			}
			
			Effect effect = card.getEffect();
			if(effect != null) {
				effect.useEffect(deck, hand);
			}
		}
		return card;
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