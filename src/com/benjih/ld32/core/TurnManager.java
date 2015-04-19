package com.benjih.ld32.core;

import com.benjih.ld32.card.PlayingCardPosition;
import com.benjih.ld32.gl.GameDisplay;

public class TurnManager {
	
	private Player player;
	private Player enemy;
	private long time;
	private UserInterface userInterface;

	public TurnManager (Player player, Player enemy, UserInterface userInterface) {
		this.player = player;
		this.enemy = enemy;
		this.userInterface = userInterface;
	}

	public TurnState isGameOver (TurnState state) {
		if(player.getHealth() <= 0) {
			return TurnState.ENEMY_WIN;
		} else if(enemy.getHealth() <= 0) {
			return TurnState.PLAYER_WIN;
		}
		return state;
	}
	
	public TurnState drawCard (TurnState state) {
		if(state.equals(TurnState.PLAYER_DRAW)) {
			userInterface.setString("top", "Your turn to draw a card");
			return drawCard(player, state, TurnState.PLAYER_USE);
		} else if(state.equals(TurnState.ENEMY_DRAW)) {
			userInterface.setString("top", "Your oponenet's turn to draw a card");
			return drawCard(enemy, state, TurnState.ENEMY_USE);
		}
		
		return state;
	}
	
	private TurnState drawCard (Player currentPlayer, TurnState currentState, TurnState returnState) {
		if(time == 0) {
			time = GameDisplay.getTime();
		}
		if (GameDisplay.getTime() >= time + 500) {
			currentPlayer.drawCard();
			time = 0;
			return returnState;
		}
		return currentState;
	}

	public TurnState playCard(TurnState state, PlayerController controller, PlayerController enemyController) {
		if(state.equals(TurnState.PLAYER_USE)) {
			userInterface.setString("top", "Your turn to play a card");
			return playCard(player, enemy, state, TurnState.ENEMY_DRAW, controller);
		}
		
		if(state.equals(TurnState.ENEMY_USE)) {
			userInterface.setString("top", "Your oponenet's turn to play a card");
			return playCard(enemy, player, state, TurnState.PLAYER_DRAW, enemyController);
		}
		return state;
	}
	
	private TurnState playCard (Player currentPlayer, Player oppositePlayer, TurnState currentState, TurnState returnState, PlayerController controller) {
		if(currentPlayer.getHand().isEmpty()) {
			return returnState;
		}
		
		PlayingCardPosition positionToPlay = controller.chooseCard(currentPlayer.getHand());
		
		if(currentPlayer.getHand().getCard(positionToPlay) != null) {
			currentPlayer.setLastPlayed(currentPlayer.playCard(positionToPlay, oppositePlayer));
			
			return returnState;
		}
		
		return currentState;
	}
}