package com.benjih.ld32;

import java.util.Map;

import org.newdawn.slick.opengl.Texture;

import com.benjih.ld32.core.Game;
import com.benjih.ld32.gl.GameDisplay;

public class Launcher {

	public static void main (String args[]) throws Exception {
		GameDisplay display = new GameDisplay();
		display.init();

		LoadingSplash loader = new LoadingSplash(display);
		loader.run();
		Map<String, Texture> textureMap = loader.getLoadedTextures(); 

		Game game = new Game(textureMap);
		
		while (true) {
			display.blit();
			
			game.run();
			
			display.update();
			display.closeIfRequested();
		}
	}
}