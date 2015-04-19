package com.benjih.ld32.core;

public class TurnManager {
	
	private Player player;
	private Player enemy;

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
	
}