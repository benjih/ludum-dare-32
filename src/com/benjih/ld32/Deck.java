package com.benjih.ld32;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.opengl.Texture;


public class Deck {
	
	private List<PlayingCard> cards;
	
	public Deck (Map<String, Texture> textureMap) {
		cards = new ArrayList<PlayingCard>();
		cards.add(new PlayingCard(textureMap.get("card-fist"), 1, 0));
		cards.add(new PlayingCard(textureMap.get("card-fist"), 1, 0));
		cards.add(new PlayingCard(textureMap.get("card-fist"), 1, 0));
		cards.add(new PlayingCard(textureMap.get("card-fist"), 1, 0));
		cards.add(new PlayingCard(textureMap.get("card-toothbrush"), 4, 0));
		cards.add(new PlayingCard(textureMap.get("card-toothbrush"), 4, 0));
		cards.add(new PlayingCard(textureMap.get("card-hanger"), 3, 0));
		cards.add(new PlayingCard(textureMap.get("card-hanger"), 3, 0));
		
		Collections.shuffle(cards);
	}
	
	public PlayingCard getTopCard () {
		PlayingCard playingCard = cards.get(0);
		cards.remove(0);
		return playingCard;
	}
	
}
