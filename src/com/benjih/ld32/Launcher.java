package com.benjih.ld32;

import com.benjih.ld32.core.Game;
import com.benjih.ld32.core.ResourceManager;
import com.benjih.ld32.gl.GameDisplay;

public class Launcher {

	public static void main (String args[]) throws Exception {
		GameDisplay display = new GameDisplay();
		display.init();

		LoadingSplash loader = new LoadingSplash(display);
		loader.run(display.getScalingFactor());
		ResourceManager resources = loader.getResourceManager();

		Game game = new Game(display, resources);
		
		while (true) {
			display.blit();
			
			game.run();
			
			display.update();
			display.closeIfRequested();
		}
	}
}