package com.benjih.ld32;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

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
		DisplayScale displayScale = display.getDisplayScale();
		display.init();

		ResourceManager resources = loadGame(display);

		UserInterface userInterface = new UserInterface(resources, displayScale);
		Game game = new Game(display, resources, userInterface);
		
		TurnState state = TurnState.PLAYER_DRAW;
		boolean menu = true;
		int tutorial = 0;
		long time = 0;
		
		Audio  wavEffect = AudioLoader.getAudio("WAV", resources.getMusic());
		wavEffect.playAsMusic(1.0f, 1.0f, true);
		
		while (true) {
			display.blit();
			
			if(menu) {
				userInterface.drawBackground();
				new Image(0, 0, resources.getTexture("start-menu"), displayScale.getScale()).render();
				
				if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 476, 500, 52, displayScale)) {
					menu = false;
				}
				
				if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 596, 500, 52, displayScale)) {
					menu = false;
					tutorial = 1;
				}
				
				
				if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 728, 500, 52, displayScale)) {
					display.end();
				}
			}
			
			if(tutorial > 0) {
				new Image(0, 0, resources.getTexture("tutorial-" + tutorial), displayScale).render();
				if(time == 0) {
					time = GameDisplay.getTime();
				}
				
				if (GameDisplay.getTime() >= time + 500) {
					if (Mouse.isButtonDown(0) && MouseUtils.isClick(0, 0, 1920, 1080, displayScale)) {
						if(tutorial  == 13) {
							tutorial = 0;
							menu = true;
						} else {
							tutorial++;
						}
						time = 0;
					}
				}
			}
			
			if(!state.equals(TurnState.END) && !menu && tutorial == 0) {
				state = game.run(state);
			} if(state.equals(TurnState.END)) {
				game = new Game(display, resources, userInterface);
				state = TurnState.PLAYER_DRAW;
				menu = true;
			}
			
			display.update();
			display.closeIfRequested();
		}
	}
	
	public static ResourceManager loadGame (GameDisplay display) throws Exception {
		LoadingSplash loader = new LoadingSplash(display);
		loader.run();
		return loader.getResourceManager();
	}
}