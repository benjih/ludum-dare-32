package com.benjih.ld32.core;

import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import com.benjih.ld32.card.Deck;
import com.benjih.ld32.card.PlayingCard;
import com.benjih.ld32.card.PlayingCardPosition;

public class Game {

	private Player player;
	private Player enemy;

	private PlayingCard lastCardPlayed;

	private TurnState turnState;

	public Game(Map<String, Texture> textureMap) {
		player = new Player(new Deck(textureMap));
		enemy = new Player(new Deck(textureMap));

		turnState = TurnState.PLAYER_DRAW;
	}

	public void run () {
		render();
		
		if(turnState.isPlayerTurn()) {
			
			if(turnState.equals(TurnState.PLAYER_DRAW)) {
				player.drawCard();
				turnState = TurnState.PLAYER_USE;
			}
			
			if(turnState.equals(TurnState.PLAYER_USE)) {
				PlayingCardPosition positionToPlay = chooseCard();
		
				if(positionToPlay != null) {
					lastCardPlayed = player.playCard(positionToPlay, enemy);
					turnState = TurnState.ENEMY_DRAW;
					printStatus();
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

	private PlayingCardPosition chooseCard () {
		PlayingCardPosition positionToPlay = null;
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
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
		return positionToPlay;
	}

	private void printStatus() {
		System.out.println("Player : " + player.getHealth() + "/" + player.getArmour());
		System.out.println("Enemy : " + enemy.getHealth() + "/" + enemy.getArmour());
	}

	private void render() {
		player.getHand().render();
		if (lastCardPlayed != null) {
			lastCardPlayed.render();
		}
	}
}