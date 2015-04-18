package com.benjih.ld32;

import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

public class Game {
	
	private Sprite card1;
	private Sprite card2;
	private Sprite card3;
	private Sprite card4;
	
	private int playerHealth;
	private int enemyHealth;

	public Game (Map<String, Texture> textureMap) {
		card1 = new PlayingCard(PlayingCardPosition.POS_1, textureMap.get("basic-card"));
		card2 = new PlayingCard(PlayingCardPosition.POS_2, textureMap.get("basic-card"));
		card3 = new PlayingCard(PlayingCardPosition.POS_3, textureMap.get("basic-card"));
 		card4 = new PlayingCard(PlayingCardPosition.POS_4, textureMap.get("basic-card"));
 		playerHealth = 30;
 		enemyHealth = 30;
	}

	public void run () {
		render();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			System.out.println("hello");
			card1.hide();
		}

	}

	private void render() {
		card1.render();
		card2.render();
		card3.render();
		card4.render();		
	}

}