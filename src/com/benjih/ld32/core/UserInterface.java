package com.benjih.ld32.core;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.benjih.ld32.gl.Image;

public class UserInterface {
	
	private ResourceManager resources;
	
	private List<Image> backkgroundImages, topbarImages;
	
	private Map<String, String> strings;

	private TrueTypeFont font;

	private Font awtFont;
	
	public UserInterface (ResourceManager resources) {
		this.resources = resources;
		
		this.strings = new HashMap<String, String>();
		strings.put("top", "");
		
		awtFont = resources.getFont("oswald");
		font = new TrueTypeFont(awtFont.deriveFont(24f), true);
	}
	
	public void setString(String id, String string) {
		strings.put(id, string);
	}
	
	public void drawBackground () {
		if(backkgroundImages == null) {
			int screenX = 1920;
			int imageX = 256;
			
			int screenY = 1080;
			int imageY = 256;
			
			backkgroundImages = new ArrayList<Image>();
			
			for(int x = 0; x < screenX + (imageX / 2); x = x + imageX) {
				for(int y = 0; y < screenY + (imageY / 2); y = y + imageY) {
					backkgroundImages.add(new Image(x, y, 256, 256, resources.getTexture("background"), 1.0f));
				}	
			}
		} else {
			for(Image image : backkgroundImages) {
				image.render();
			}
		}
	}
	
	public void drawTopbar () {
		if(topbarImages == null) {
			int screenX = 1920;
			int imageX = 256;
			
			
			topbarImages = new ArrayList<Image>();
			
			for(int x = 0; x < screenX + (imageX / 2); x = x + imageX) {
				topbarImages.add(new Image(x, 0, 256, 256, resources.getTexture("top"), 1.0f));
			}
		} else {
			for(Image image : topbarImages) {
				image.render();
			}
		}
	}

	public void drawTopbarMessage() {
		if(font == null) {
			initFonts();
		}
		font.drawString(1920/2, 10, strings.get("top") , new Color(83, 83, 83), TrueTypeFont.ALIGN_CENTER);
	}

	private void initFonts() {
		// TODO Auto-generated method stub
		
	}

}
