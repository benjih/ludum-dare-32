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
		textures.put("basic-card", FileLoader.loadTexture("res/card.png"));
		textures.put("card-toothbrush", FileLoader.loadTexture("res/card-toothbrush.png"));
		textures.put("card-hanger", FileLoader.loadTexture("res/card-hanger.png"));
		textures.put("card-fist", FileLoader.loadTexture("res/card-fist.png"));
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