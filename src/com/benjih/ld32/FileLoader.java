package com.benjih.ld32;

import java.io.IOException;

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
}