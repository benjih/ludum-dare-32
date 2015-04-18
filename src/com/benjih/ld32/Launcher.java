package com.benjih.ld32;

public class Launcher {

	public static void main(String args[]) throws Exception {
		GameDisplay display = new GameDisplay();
		display.init();

		new LoadingSplash(display).run();
		
		while (true) {
			display.blit();
			display.update();
			display.closeIfRequested();
		}
	}
}