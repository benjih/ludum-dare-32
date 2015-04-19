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
		
		state = checkHealth(state);
		
		
		if(state.isPlayerTurn() && !shouldPause) {
			
			if(state.equals(TurnState.PLAYER_DRAW)) {
				state = TurnState.PLAYER_USE;
				player.drawCard();
			}
			
			if(state.equals(TurnState.PLAYER_USE)) {
				userInterface.setString("top", "Your turn to play a card");
				
				PlayingCardPosition positionToPlay = chooseCard();
		
				if(player.getHand().getCard(positionToPlay) != null) {
					lastPlayerCard = player.playCard(positionToPlay, enemy);
					state = TurnState.ENEMY_DRAW;
				}
			}
		} else if(!shouldPause){
			if(state.equals(TurnState.ENEMY_DRAW)) {
				enemy.drawCard();
				state = TurnState.ENEMY_USE;
				time = display.getTime();
			} 
			
			if(state.equals(TurnState.ENEMY_USE)) {
				userInterface.setString("top", "Your oponenet's turn to play a card");
				if (display.getTime() >= time + 1600) {
					PlayingCardPosition positionToPlay = enemyChooseCard();
					
					if(positionToPlay != null) {
						lastEnemyCard = enemy.playCard(positionToPlay, player);
						System.out.println(lastEnemyCard.getName());
						state = TurnState.PLAYER_DRAW;
					}
				}
			}
		}
		
		userInterface.drawTable();
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
		
		return state;
	}

	private TurnState checkHealth(TurnState state) {
		if(player.getHealth() <= 0) {
			state = TurnState.ENEMY_WIN;
		} else if(enemy.getHealth() <= 0) {
			state = TurnState.PLAYER_WIN;
		}
		return state;
	}

	private boolean shouldPause() {
		return Mouse.isButtonDown(0) && MouseUtils.isClick(1920 - 64, 0, 64, 64);
	}

	private PlayingCardPosition chooseCard () {
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_1.getX(), PlayingCardPosition.POS_1.getY(), 181, 252)) {
			return PlayingCardPosition.POS_1;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_2.getX(), PlayingCardPosition.POS_2.getY(), 181, 252)) {
			return PlayingCardPosition.POS_2;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_3.getX(), PlayingCardPosition.POS_3.getY(), 181, 252)) {
			return PlayingCardPosition.POS_3;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_4.getX(), PlayingCardPosition.POS_4.getY(), 181, 252)) {
			return PlayingCardPosition.POS_4;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_5.getX(), PlayingCardPosition.POS_5.getY(), 181, 252)) {
			return PlayingCardPosition.POS_5;
		}
		if(Mouse.isButtonDown(0) && MouseUtils.isClick(PlayingCardPosition.POS_6.getX(), PlayingCardPosition.POS_6.getY(), 181, 252)) {
			return PlayingCardPosition.POS_6;
		}
		
		return PlayingCardPosition.NONE;
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

	private void render() {
		if (lastPlayerCard != null) {
			lastPlayerCard.setPosition(PlayingCardPosition.POS_PLAYED);
			lastPlayerCard.render();
		}
		if (lastEnemyCard != null) {
			lastEnemyCard.setPosition(PlayingCardPosition.POS_ENEMY_PLAYED);
			lastEnemyCard.render();
		}
		player.getHand().render();
		enemy.getHand().renderHidden(new Image(0, 0, resources.getTexture("card-back"), 1.0f));
	}
}