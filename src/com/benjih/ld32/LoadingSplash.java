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
		
		long start = GameDisplay.getTime();
		display.blit();
		
		resourceManager = new ResourceManager();
		
		while (running) {
			display.blit();
			
			display.closeIfRequested();
			
			display.update();
			
			if (GameDisplay.getTime() >= start + 3000) {
				display.blit();
				running = false;
				display.update();
			}
			
		}
		
	}
	
	public ResourceManager getResourceManager () {
		return resourceManager;
	}
}