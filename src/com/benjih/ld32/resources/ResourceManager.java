package com.benjih.ld32.resources;

import java.awt.Font;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;

public class ResourceManager {
	
	private HashMap<String, Texture> textures;
	private HashMap<String, Font> fonts;

	public ResourceManager () {
		this.textures = new HashMap<String, Texture>();
		textures.put("background", FileLoader.loadTexture("res/background.png"));
		textures.put("top", FileLoader.loadTexture("res/top.png"));
		textures.put("pause", FileLoader.loadTexture("res/pause.png"));
		textures.put("pause-menu", FileLoader.loadTexture("res/pause-menu.png"));
		
		textures.put(("start-menu"), FileLoader.loadTexture("res/start-menu.png"));
		
		textures.put("player-hand-mat", FileLoader.loadTexture("res/player-hand-mat.png"));
		textures.put("opponent-hand-mat", FileLoader.loadTexture("res/opponent-hand-mat.png"));
		textures.put("played-cards-mat", FileLoader.loadTexture("res/played-cards-mat.png"));
		
		textures.put("player-card", FileLoader.loadTexture("res/player-card.png"));
		
		textures.put("basic-card", FileLoader.loadTexture("res/card.png"));
		textures.put("card-back", FileLoader.loadTexture("res/card-back.png"));
		textures.put("card-toothbrush", FileLoader.loadTexture("res/card-toothbrush.png"));
		textures.put("card-hanger", FileLoader.loadTexture("res/card-hanger.png"));
		textures.put("card-fist", FileLoader.loadTexture("res/card-fist.png"));
		textures.put("card-foot", FileLoader.loadTexture("res/card-foot.png"));
		textures.put("card-pillowfort", FileLoader.loadTexture("res/card-pillowfort.png"));
		textures.put("card-tv", FileLoader.loadTexture("res/card-tv.png"));
		textures.put("card-hide", FileLoader.loadTexture("res/card-hide.png"));
		textures.put("card-dodge", FileLoader.loadTexture("res/card-dodge.png"));
		textures.put("card-runaway", FileLoader.loadTexture("res/card-runaway.png"));
		
		this.fonts = new HashMap<String, Font>();
		fonts.put("oswald", FileLoader.loadFont("res/Oswald.ttf"));
	}

	public Texture getTexture (String resourceName) {
		return textures.get(resourceName);
	}
	
	public Font getFont (String resourceName) {
		return fonts.get(resourceName);
	}
}