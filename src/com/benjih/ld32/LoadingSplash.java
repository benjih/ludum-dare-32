package com.benjih.ld32;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.opengl.Texture;

public class LoadingSplash {
	
	private GameDisplay display;
	private Map<String, Texture> textureMap;
	
	private boolean loadedFlag;
	
	public LoadingSplash (GameDisplay display) {
		this.display = display;
		textureMap = new HashMap<String, Texture>();
		loadedFlag = false;
	}
	
	public void run () throws Exception {
		boolean running = true;
		
		Image splash = new Image(0, 0, FileLoader.loadTexture("res/splash.png"));
		long start = display.getTime();
		
		loadFiles();
		
		while (running || !loadedFlag) {
			display.blit();
			
			splash.render();
			
			display.closeIfRequested();
			
			display.update();
			
			if (display.getTime() >= start + 3000) {
				running = false;
			}
			
		}
		
	}
	
	private void loadFiles () {
		textureMap.put("basic-card", FileLoader.loadTexture("res/card.png"));
		loadedFlag = true;
	}

	public Map<String, Texture> getLoadedTextures () {
		return textureMap;
	}
	
}