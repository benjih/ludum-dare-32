package com.benjih.ld32.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

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

	private PlayingCard lastPlayerCard;
	private PlayingCard lastEnemyCard;

	private long time;

	private ResourceManager resources;

	private UserInterface userInterface;

	private boolean shouldPause;

	public Game(GameDisplay display, ResourceManager resources) {
		this.display = display;
		this.resources = resources;
		
		player = new Player(new Deck(resources));
		enemy = new Player(new Deck(resources));
		
		userInterface = new UserInterface(resources);

		shouldPause = false;
		
	}

	public TurnState run (TurnState state) {
		userInterface.drawBackground();
		userInterface.drawMats();
		render();
		userInterface.drawTopbar();
		userInterface.drawTopbarMessage();
		userInterface.drawScoreCards(player, enemy);
		
		if(!shouldPause) {
			shouldPause = shouldPause();
		} else {
			new Image(0, 0, resources.getTexture("pause-menu"), 1.0f).render();
			
			if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 468, 500, 52)) {
				shouldPause = false;
			}
			
			if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 612, 500, 52)) {
				shouldPause = false;
				return TurnState.ENEMY_WIN;
			}
		}
		
		if(player.getHealth() <= 0) {
			state = TurnState.ENEMY_WIN;
		} else if(enemy.getHealth() <= 0) {
			state = TurnState.PLAYER_WIN;
		}
		
		
		if(state.isPlayerTurn() && !shouldPause) {
			
			if(state.equals(TurnState.PLAYER_DRAW)) {
				player.drawCard();
				state = TurnState.PLAYER_USE;
			}
			
			if(state.equals(TurnState.PLAYER_USE)) {
				userInterface.setString("top", "Your turn to play a card");
				
				PlayingCardPosition positionToPlay = chooseCard();
		
				if(positionToPlay != null) {
					lastPlayerCard = player.playCard(positionToPlay, enemy);
					state = TurnState.ENEMY_DRAW;
					printStatus();
				}
			}
		} else if(!shouldPause){
			if(state.equals(TurnState.ENEMY_DRAW)) {
				System.out.println("enemy turn");
				enemy.drawCard();
				state = TurnState.ENEMY_USE;
				time = display.getTime();
			} 
			
			if(state.equals(TurnState.ENEMY_USE)) {
				userInterface.setString("top", "Your oponenet's turn to play a card");
				if (display.getTime() >= time + 1600) {
					PlayingCardPosition positionToPlay = enemyChooseCard();
					
					if(positionToPlay != null) {
						if(positionToPlay == null) {
							lastPlayerCard = null;
						} else {
							lastEnemyCard = enemy.playCard(positionToPlay, player);
							state = TurnState.PLAYER_DRAW;
						}
						printStatus();
					}
				}
			}
		}
		return state;
	}

	private boolean shouldPause() {
		return Mouse.isButtonDown(0) && MouseUtils.isClick(1920 - 64, 0, 64, 64);
	}

	private PlayingCardPosition chooseCard () {
		PlayingCardPosition positionToPlay = null;
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_1.getX(), PlayingCardPosition.POS_1.getY(), 181, 252)) {
			positionToPlay = PlayingCardPosition.POS_1;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_2.getX(), PlayingCardPosition.POS_2.getY(), 181, 252)) {
			positionToPlay = PlayingCardPosition.POS_2;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_3.getX(), PlayingCardPosition.POS_3.getY(), 181, 252)) {
			positionToPlay = PlayingCardPosition.POS_3;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_4.getX(), PlayingCardPosition.POS_4.getY(), 181, 252)) {
			positionToPlay = PlayingCardPosition.POS_4;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_5.getX(), PlayingCardPosition.POS_5.getY(), 181, 252)) {
			positionToPlay = PlayingCardPosition.POS_5;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_6.getX(), PlayingCardPosition.POS_6.getY(), 181, 252)) {
			positionToPlay = PlayingCardPosition.POS_6;
		}
		
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_1) {
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
				if (Keyboard.getEventKey() == Keyboard.KEY_5) {
					positionToPlay = PlayingCardPosition.POS_5;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_6) {
					positionToPlay = PlayingCardPosition.POS_6;
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
		enemy.getHand().renderHidden(new Image(0, 0, resources.getTexture("card-back"), 1.0f));
		if (lastPlayerCard != null) {
			lastPlayerCard.render();
		}
		if (lastEnemyCard != null) {
			lastEnemyCard.setPosition(PlayingCardPosition.POS_ENEMY_PLAYED);
			lastEnemyCard.render();
		}
	}
}