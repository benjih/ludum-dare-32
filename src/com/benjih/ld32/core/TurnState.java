package com.benjih.ld32.core;

public enum TurnState {
	PLAYER_DRAW(true), PLAYER_USE(true), PLAYER_END(true),
	ENEMY_DRAW(false), ENEMY_USE(false), ENMEMY_END(false);
	
	private boolean playerTurn;
	
	TurnState (boolean playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public boolean isPlayerTurn () {
		return playerTurn;
	}
}