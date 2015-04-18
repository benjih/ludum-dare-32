package com.benjih.ld32;

import com.benjih.ld32.core.Game;
import com.benjih.ld32.core.TurnState;
import com.benjih.ld32.gl.GameDisplay;
import com.benjih.ld32.resources.ResourceManager;

public class Launcher {

	public static void main (String args[]) throws Exception {
		GameDisplay display = new GameDisplay();
		display.init();

		LoadingSplash loader = new LoadingSplash(display);
		loader.run(display.getScalingFactor());
		ResourceManager resources = loader.getResourceManager();

		Game game = new Game(display, resources);
		
		TurnState state = TurnState.PLAYER_DRAW;
		while (true) {
			display.blit();
			
			if(!state.equals(TurnState.ENEMY_WIN) && !state.equals(TurnState.PLAYER_WIN)) {
				state = game.run(state);
			} else if(state.equals(TurnState.ENEMY_WIN)) {
				System.out.println("Loser");
			} else if(state.equals(TurnState.PLAYER_WIN)) {
				System.out.println("Winner");
			}
			
			display.update();
			display.closeIfRequested();
		}
	}
}