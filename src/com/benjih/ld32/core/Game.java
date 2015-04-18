package com.benjih.ld32.core;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import com.benjih.ld32.card.Deck;
import com.benjih.ld32.card.PlayingCard;
import com.benjih.ld32.card.PlayingCardPosition;
import com.benjih.ld32.gl.GameDisplay;

public class Game {

	private GameDisplay display;
	
	private Player player;
	private Player enemy;

	private PlayingCard lastCardPlayed;

	private TurnState turnState;
	private long time;

	private ResourceManager resources;

	private TrueTypeFont font;

	public Game(GameDisplay display, ResourceManager resources) {
		this.display = display;
		this.resources = resources;
		player = new Player(new Deck(resources));
		enemy = new Player(new Deck(resources));

		turnState = TurnState.PLAYER_DRAW;
		Font awtFont = resources.getFont("oswald");
		font = new TrueTypeFont(awtFont.deriveFont(24f), false);
	}

	public void run () {
		render();
		if(turnState.isPlayerTurn()) {
			
			if(turnState.equals(TurnState.PLAYER_DRAW)) {
				player.drawCard();
				turnState = TurnState.PLAYER_USE;
			}
			
			if(turnState.equals(TurnState.PLAYER_USE)) {
				font.drawString(0, 0, "Your turn to play a card" , new Color(255, 255, 2));
				
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
				enemy.drawCard();
				turnState = TurnState.ENEMY_USE;
				time = display.getTime();
			} 
			
			if(turnState.equals(TurnState.ENEMY_USE)) {
				font.drawString(0, 0, "Your oponenet's turn to play a card" , new Color(255, 255, 2));
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