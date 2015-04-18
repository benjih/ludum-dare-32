package com.benjih.ld32;

import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

public class Game {
	
	private Hand playerHand;
	private Deck playerDeck;
	
	private int playerHealth;
	private int enemyHealth;
	
	private PlayingCard lastCardPlayed;

	private Map<String, Texture> textureMap;

	public Game (Map<String, Texture> textureMap) {
		this.textureMap = textureMap;
 		
 		playerHand = new Hand();
 		playerDeck = new Deck(textureMap);
 		
 		playerHealth = 30;
 		enemyHealth = 30;
 		
 		Keyboard.enableRepeatEvents(false);
	}

	public void run () {
		render();
		PlayingCardPosition positionToPlay = null;
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
		        	PlayingCardPosition freePosition = playerHand.getFirstFreeSlot();
				
					if(freePosition != null) {
						playerHand.putCard(freePosition, playerDeck.getTopCard());
					}
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_1) {
		        	positionToPlay = PlayingCardPosition.POS_1;

		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_2) {
		        	positionToPlay = PlayingCardPosition.POS_2;
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_3) {
		        	positionToPlay = PlayingCardPosition.POS_3;
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_4) {
		        	positionToPlay = PlayingCardPosition.POS_4;
		        }
		    }
		}
		
		if(positionToPlay !=null) {
			lastCardPlayed = playerHand.getCard(positionToPlay);
			if(lastCardPlayed != null) {
				playerHand.useCard(positionToPlay);
				lastCardPlayed.setPosition(PlayingCardPosition.POS_PLAYED);
			}
		}
	}

	private void render() {
		playerHand.render();
		if(lastCardPlayed !=null) {
			lastCardPlayed.render();
		}
	}
}