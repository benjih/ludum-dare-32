package com.benjih.ld32;

import com.benjih.ld32.core.ResourceManager;
import com.benjih.ld32.gl.GameDisplay;
import com.benjih.ld32.gl.Image;

public class LoadingSplash {
	
	private GameDisplay display;
	private ResourceManager resourceManager;
	
	
	public LoadingSplash (GameDisplay display) {
		this.display = display;
	}
	
	public void run () throws Exception {
		boolean running = true;
		
		Image splash = new Image(0, 0, FileLoader.loadTexture("res/splash.png"));
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