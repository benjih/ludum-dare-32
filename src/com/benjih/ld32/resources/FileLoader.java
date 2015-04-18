package com.benjih.ld32.resources;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class FileLoader {
	
	public static Texture loadTexture(String fileUri) {
		try {
			return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(fileUri));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static Font loadFont(String fileUri) {
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream(fileUri);
			return Font.createFont(Font.TRUETYPE_FONT, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}