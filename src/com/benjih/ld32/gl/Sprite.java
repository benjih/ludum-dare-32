package com.benjih.ld32.gl;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public abstract class Sprite {

	private int x, y;
	private float scale;
	private Texture resource;
	private boolean hide = false;
	private int width, height;
	
	public Sprite (int x, int y, int width, int height, Texture resource, float scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.resource = resource;
	}
	
	public void render () {
		if (!hide) {
			new Color(255, 255, 255).bind();
			
			GL11.glEnable(GL11.GL_TEXTURE_2D); 
			resource.bind();
			GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(x  / scale, y / scale);
			
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(x + resource.getTextureWidth() / scale, y  / scale);
			
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(x + resource.getTextureWidth() / scale, y + resource.getTextureHeight() / scale);
			
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(x  / scale, y + resource.getTextureHeight() / scale);
			
			GL11.glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D); 
		}
	}
	
	public int getX () {
		return x;
	}
	
	public void setX (int x) {
		this.x = x;
	}
	
	public int getY () {
		return y;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
	public void hide () {
		this.hide = true;
	}
	
}