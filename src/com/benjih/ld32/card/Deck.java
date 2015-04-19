package com.benjih.ld32.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.benjih.ld32.core.DrawEffect;
import com.benjih.ld32.core.UnconventionalEffect;
import com.benjih.ld32.resources.ResourceManager;


public class Deck {
	
	private List<PlayingCard> cards;
	
	public Deck (ResourceManager resources) {
		cards = new ArrayList<PlayingCard>();
		PlayingCard fist = new PlayingCard("Fist", resources.getTexture("card-fist"), 1, 0, null);
		PlayingCard foot = new PlayingCard("Foot", resources.getTexture("card-foot"), 2, 1, null);
		PlayingCard toothbrush = new PlayingCard("Toothbrush", resources.getTexture("card-toothbrush"), 4, 0, new UnconventionalEffect());
		PlayingCard hanger = new PlayingCard("Hanger", resources.getTexture("card-hanger"), 3, 0, new UnconventionalEffect());
		PlayingCard pillowfort = new PlayingCard("Pillowfort", resources.getTexture("card-pillowfort"), 0, 1, null);
		PlayingCard tv = new PlayingCard("TV", resources.getTexture("card-tv"), 1, 2, new UnconventionalEffect());
		PlayingCard dodge = new PlayingCard("Dodge", resources.getTexture("card-dodge"), 0, 0, new DrawEffect(1));
		PlayingCard hide = new PlayingCard("Hide", resources.getTexture("card-hide"), 0, 0, null);
		PlayingCard runaway = new PlayingCard("Run Away", resources.getTexture("card-runaway"), 0, 0, new DrawEffect(3));
		
		
		
		
		cards.add(fist);
		cards.add(fist);
		cards.add(fist);
		cards.add(fist);
		cards.add(fist);
		cards.add(foot);
		cards.add(foot);
		cards.add(foot);
		cards.add(foot);
		cards.add(foot);
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
		cards.add(hide);
		cards.add(hide);
		cards.add(hide);
		cards.add(hide);
		cards.add(hide);
		cards.add(runaway);
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
