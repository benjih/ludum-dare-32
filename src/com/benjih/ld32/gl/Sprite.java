package com.benjih.ld32.gl;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.benjih.ld32.DisplayScale;

public abstract class Sprite {

	private int x, y;
	private Texture resource;
	private DisplayScale displayScale;
	private boolean hide = false;
	
	public Sprite (int x, int y, Texture resource, DisplayScale displayScale) {
		this.x = x;
		this.y = y;
		this.displayScale = displayScale;
		this.resource = resource;
	}
	
	public void render () {
		if (!hide) {
			new Color(255, 255, 255).bind();
			
			GL11.glEnable(GL11.GL_TEXTURE_2D); 
			resource.bind();
			GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(displayScale.scaleX(x), displayScale.scaleY(y));
			
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(displayScale.scaleX(x + resource.getTextureWidth()), displayScale.scaleY(y));
			
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(displayScale.scaleX(x + resource.getTextureWidth()), displayScale.scaleY(y + resource.getTextureHeight()));
			
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(displayScale.scaleX(x), displayScale.scaleY(y + resource.getTextureHeight()));
			
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