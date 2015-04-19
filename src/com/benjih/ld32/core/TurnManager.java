package com.benjih.ld32.core;

import com.benjih.ld32.card.PlayingCard;
import com.benjih.ld32.card.PlayingCardPosition;
import com.benjih.ld32.gl.GameDisplay;

public class TurnManager {
	
	private Player player;
	private Player enemy;
	private long time;
	private UserInterface userInterface;
	private PlayingCard lastPlayerCard;
	private PlayingCard lastEnemyCard;

	public TurnManager (Player player, Player enemy, UserInterface userInterface) {
		this.player = player;
		this.enemy = enemy;
		this.userInterface = userInterface;
		this.lastPlayerCard = null;
		this.lastEnemyCard = null;
	}

	public TurnState isGameOver () {
		if(player.getHealth() <= 0) {
			return TurnState.ENEMY_WIN;
		} else if(enemy.getHealth() <= 0) {
			return TurnState.PLAYER_WIN;
		}
		return null;
	}
	
	public TurnState drawCard (TurnState state) {
		if(state.equals(TurnState.PLAYER_DRAW)) {
			player.drawCard();
			return TurnState.PLAYER_USE;
		} else if(state.equals(TurnState.ENEMY_DRAW)) {
			if(time == 0) {
				time = GameDisplay.getTime();
			}
			if (GameDisplay.getTime() >= time + 1600) {
				enemy.drawCard();
				time = 0;
				return TurnState.ENEMY_USE;
			}
		}
		
		return state;
	}

	public TurnState playCard(TurnState state, PlayerController controller, PlayerController enemyController) {
		if(state.equals(TurnState.PLAYER_USE)) {
			userInterface.setString("top", "Your turn to play a card");
			
			PlayingCardPosition positionToPlay = controller.chooseCard(player.getHand());
	
			if(player.getHand().getCard(positionToPlay) != null) {
				lastPlayerCard = (player.playCard(positionToPlay, enemy));
				state = TurnState.ENEMY_DRAW;
			}
		}
		
		if(state.equals(TurnState.ENEMY_USE)) {
			userInterface.setString("top", "Your oponenet's turn to play a card");
			PlayingCardPosition positionToPlay = enemyController.chooseCard(enemy.getHand());
				
			if(positionToPlay != null) {
				lastEnemyCard = enemy.playCard(positionToPlay, player);
				state = TurnState.PLAYER_DRAW;
			}
		}
		return state;
	}

	public PlayingCard getLastPlayerCard() {
		return lastPlayerCard;
	}
	
	public PlayingCard getLastEnemyCard() {
		return lastEnemyCard;
	}

}