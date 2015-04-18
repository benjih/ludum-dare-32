package com.benjih.ld32;

import com.benjih.ld32.gl.GameDisplay;
import com.benjih.ld32.gl.Image;
import com.benjih.ld32.resources.FileLoader;
import com.benjih.ld32.resources.ResourceManager;

public class LoadingSplash {
	
	private GameDisplay display;
	private ResourceManager resourceManager;
	
	
	public LoadingSplash (GameDisplay display) {
		this.display = display;
	}
	
	public void run (float scale) throws Exception {
		boolean running = true;
		
		Image splash = new Image(0, 0, 1920, 1080, FileLoader.loadTexture("res/spoopygames.png"), scale);
		long start = display.getTime();
		display.blit();
		
		splash.render();
		
		resourceManager = new ResourceManager();
		
		while (running) {
			display.blit();
			
			splash.render();
			
			display.closeIfRequested();
			
			display.update();
			
			if (display.getTime() >= start + 3000) {
				running = false;
			}
			
		}
		
	}
	
	public ResourceManager getResourceManager () {
		return resourceManager;
	}
}