package com.benjih.ld32.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.opengl.Texture;

import com.benjih.ld32.core.DrawEffect;


public class Deck {
	
	private List<PlayingCard> cards;
	
	public Deck (Map<String, Texture> textureMap) {
		cards = new ArrayList<PlayingCard>();
		PlayingCard fist = new PlayingCard("Fist", textureMap.get("card-fist"), 1, 0, null);
		PlayingCard toothbrush = new PlayingCard("Toothbrush", textureMap.get("card-toothbrush"), 4, 0, null);
		PlayingCard hanger = new PlayingCard("Hanger", textureMap.get("card-hanger"), 3, 0, null);
		PlayingCard pillowfort = new PlayingCard("Pillowfort", textureMap.get("card-pillowfort"), 0, 1, null);
		PlayingCard tv = new PlayingCard("TV", textureMap.get("card-tv"), 1, 2, null);
		PlayingCard dodge = new PlayingCard("Dodge", textureMap.get("card-dodge"), 0, 0, new DrawEffect(1));
		PlayingCard hide = new PlayingCard("Hide", textureMap.get("card-hide"), 0, 0, new DrawEffect(2));
		PlayingCard runaway = new PlayingCard("Run Away", textureMap.get("card-runaway"), 0, 0, new DrawEffect(3));
		
		cards.add(fist);
		cards.add(fist);
		cards.add(fist);
		cards.add(fist);
		cards.add(toothbrush);
		cards.add(toothbrush);
		cards.add(hanger);
		cards.add(hanger);
		cards.add(pillowfort);
		cards.add(pillowfort);
		cards.add(pillowfort);
		cards.add(pillowfort);
		cards.add(tv);
		cards.add(tv);
		cards.add(dodge);
		cards.add(dodge);
		cards.add(dodge);
		cards.add(dodge);
		cards.add(hide);
		cards.add(runaway);
		
		Collections.shuffle(cards);
	}
	
	public PlayingCard getTopCard () {
		if(cards.size() == 0) {
			return null;
		}
		PlayingCard playingCard = cards.get(0);
		cards.remove(0);
		return playingCard;
	}
	
	public int size () {
		return cards.size();
	}
	
}
