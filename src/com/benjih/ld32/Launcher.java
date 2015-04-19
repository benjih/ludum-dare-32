package com.benjih.ld32;

import org.lwjgl.input.Mouse;

import com.benjih.ld32.core.Game;
import com.benjih.ld32.core.MouseUtils;
import com.benjih.ld32.core.TurnState;
import com.benjih.ld32.core.UserInterface;
import com.benjih.ld32.gl.GameDisplay;
import com.benjih.ld32.gl.Image;
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
		boolean menu = true;
		UserInterface userInterface = new UserInterface(resources);
		while (true) {
			display.blit();
			
			if(menu) {
				userInterface.drawBackground();
				new Image(0, 0, resources.getTexture("start-menu"), 1.0f).render();
				
				if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 468, 500, 52)) {
					menu = false;
				}
				
				if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 612, 500, 52)) {
					display.end();
				}
			}
			
			if(!state.equals(TurnState.ENEMY_WIN) && !state.equals(TurnState.PLAYER_WIN) && !menu) {
				state = game.run(state);
			} else if(state.equals(TurnState.ENEMY_WIN)) {
				new Image(0, 0, resources.getTexture("loser"), 1.0f).render();
				
				if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 210, 500, 600)) {
					game = new Game(display, resources);
					state = TurnState.PLAYER_DRAW;
					menu = true;
				}
			} else if(state.equals(TurnState.PLAYER_WIN)) {
				new Image(0, 0, resources.getTexture("winner"), 1.0f).render();
				
				if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 210, 500, 600)) {
					game = new Game(display, resources);
					state = TurnState.PLAYER_DRAW;
					menu = true;
				}
			}
			
			display.update();
			display.closeIfRequested();
		}
	}
}