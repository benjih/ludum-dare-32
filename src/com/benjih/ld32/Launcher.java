package com.benjih.ld32;

import java.util.Map;

import org.newdawn.slick.opengl.Texture;

public class Launcher {

	public static void main (String args[]) throws Exception {
		GameDisplay display = new GameDisplay();
		display.init();

		LoadingSplash loader = new LoadingSplash(display);
		loader.run();
		Map<String, Texture> textureMap = loader.getLoadedTextures(); 
		
		Sprite card1 = new PlayingCard(PlayingCardPosition.POS_1, textureMap.get("basic-card"));
		Sprite card2 = new PlayingCard(PlayingCardPosition.POS_2, textureMap.get("basic-card"));
		Sprite card3 = new PlayingCard(PlayingCardPosition.POS_3, textureMap.get("basic-card"));
		Sprite card4= new PlayingCard(PlayingCardPosition.POS_4, textureMap.get("basic-card"));
		
		while (true) {
			display.blit();
			
			card1.render();
			card2.render();
			card3.render();
			card4.render();
			
			display.update();
			display.closeIfRequested();
		}
	}
}