package com.benjih.ld32;

import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

public class Game {

	private Hand playerHand;
	private Deck playerDeck;

	private int playerHealth;
	private int playerArmour;
	private int enemyHealth;
	private int enemyArmour;

	private PlayingCard lastCardPlayed;

	private Map<String, Texture> textureMap;

	private TurnState turnState;

	public Game(Map<String, Texture> textureMap) {
		this.textureMap = textureMap;

		playerHand = new Hand();
		playerDeck = new Deck(textureMap);

		playerHealth = 30;
		enemyHealth = 30;

		playerArmour = 0;
		enemyArmour = 0;

		turnState = TurnState.PLAYER_DRAW;
	}

	public void run () {
		render();
		
		if(turnState.isPlayerTurn()) {
			
			if(turnState.equals(TurnState.PLAYER_DRAW)) {
				PlayingCardPosition freePosition = playerHand.getFirstFreeSlot();
				
				if(freePosition != null) {
					playerHand.putCard(freePosition, playerDeck.getTopCard());
				}
				turnState = TurnState.PLAYER_USE;
			}
			
			if(turnState.equals(TurnState.PLAYER_USE)) {
				PlayingCardPosition positionToPlay = null;
				
				while (Keyboard.next()) {
					if (Keyboard.getEventKeyState()) {
						if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
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
						enemyHealth = enemyHealth - lastCardPlayed.getDamage();
						printStatus();
						turnState = TurnState.ENEMY_DRAW;
					}
				}
			}
		} else {
			if(turnState.equals(TurnState.ENEMY_DRAW)) {
				System.out.println("enemy turn");
				turnState = TurnState.ENEMY_USE;
			} 
			
			
			while (Keyboard.next()) {
			    if (Keyboard.getEventKeyState()) {
			        if (Keyboard.getEventKey() == Keyboard.KEY_T) {
			        	turnState = TurnState.PLAYER_DRAW;
			        }
			    }
			}
		}
	}

	private void printStatus() {
		System.out.println("Player : " + playerHealth + "/" + playerArmour);
		System.out.println("Enemy : " + enemyHealth + "/" + enemyArmour);
	}

	private void render() {
		playerHand.render();
		if (lastCardPlayed != null) {
			lastCardPlayed.render();
		}
	}
}