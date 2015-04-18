package com.benjih.ld32.core;

import java.awt.Font;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.benjih.ld32.card.Deck;
import com.benjih.ld32.card.PlayingCard;
import com.benjih.ld32.card.PlayingCardPosition;
import com.benjih.ld32.gl.GameDisplay;
import com.benjih.ld32.gl.Image;
import com.benjih.ld32.resources.ResourceManager;

public class Game {

	private GameDisplay display;
	
	private Player player;
	private Player enemy;

	private PlayingCard lastCardPlayed;

	private TurnState turnState;
	private long time;

	private ResourceManager resources;

	private TrueTypeFont font;

	private UserInterface userInterface;

	private boolean shouldPause;

	public Game(GameDisplay display, ResourceManager resources) {
		this.display = display;
		this.resources = resources;
		
		player = new Player(new Deck(resources));
		enemy = new Player(new Deck(resources));
		
		userInterface = new UserInterface(resources);

		turnState = TurnState.PLAYER_DRAW;
		
		shouldPause = false;
		
	}

	public void run () {
		userInterface.drawBackground();
		userInterface.drawTopbar();
		userInterface.drawTopbarMessage();
		render();
		
		if(!shouldPause) {
			shouldPause = shouldPause();
		} else {
			new Image(0, 0, resources.getTexture("pause-menu"), 1.0f).render();
			
			if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 468, 500, 52)) {
				shouldPause = false;
			}
		}
		
		
		if(turnState.isPlayerTurn() && !shouldPause) {
			
			if(turnState.equals(TurnState.PLAYER_DRAW)) {
				player.drawCard();
				turnState = TurnState.PLAYER_USE;
			}
			
			if(turnState.equals(TurnState.PLAYER_USE)) {
				userInterface.setString("top", "Your turn to play a card");
				
				PlayingCardPosition positionToPlay = chooseCard();
		
				if(positionToPlay != null) {
					lastCardPlayed = player.playCard(positionToPlay, enemy);
					turnState = TurnState.ENEMY_DRAW;
					printStatus();
				}
			}
		} else if(!shouldPause){
			if(turnState.equals(TurnState.ENEMY_DRAW)) {
				System.out.println("enemy turn");
				enemy.drawCard();
				turnState = TurnState.ENEMY_USE;
				time = display.getTime();
			} 
			
			if(turnState.equals(TurnState.ENEMY_USE)) {
				userInterface.setString("top", "Your oponenet's turn to play a card");
				if (display.getTime() >= time + 1600) {
					PlayingCardPosition positionToPlay = enemyChooseCard();
					
					if(positionToPlay != null) {
						if(positionToPlay == null) {
							lastCardPlayed = null;
						} else {
							lastCardPlayed = enemy.playCard(positionToPlay, player);
							turnState = TurnState.PLAYER_DRAW;
						}
						printStatus();
					}
				}
			}
		}
	}

	private boolean shouldPause() {
		return Mouse.isButtonDown(0) && MouseUtils.isClick(1920 - 64, 0, 64, 64);
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
	
	private PlayingCardPosition enemyChooseCard () {
		if (enemy.getHand().getCard(PlayingCardPosition.POS_1) != null) {
			return PlayingCardPosition.POS_1;
		} else if(enemy.getHand().getCard(PlayingCardPosition.POS_2) != null) {
			return PlayingCardPosition.POS_2;
		} else if(enemy.getHand().getCard(PlayingCardPosition.POS_3) != null) {
			return PlayingCardPosition.POS_3;
		}
		return PlayingCardPosition.POS_4;
	}

	private void printStatus() {
		System.out.println("Player : " + player.getHealth() + "/" + player.getArmour() + " " + player.getDeck().size());
		System.out.println("Enemy : " + enemy.getHealth() + "/" + enemy.getArmour() + " " + enemy.getDeck().size());
		
	}

	private void render() {
		player.getHand().render();
		if (lastCardPlayed != null) {
			lastCardPlayed.render();
		}
	}
}