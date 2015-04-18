package com.benjih.ld32;

public class LoadingSplash {
	
	GameDisplay display;
	
	public LoadingSplash (GameDisplay display) {
		this.display = display;
	}
	
	public void run () throws Exception {
		boolean running = true;
		
		Sprite splash = new Sprite(0, 0, "res/splash.png");
		long start = display.getTime();
		
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
	
}