package com.benjih.ld32.core;

import com.benjih.ld32.gl.GameDisplay;

public class TurnManager {
	
	private Player player;
	private Player enemy;
	private long time;

	public TurnManager (Player player, Player enemy) {
		this.player = player;
		this.enemy = enemy;
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
	
}