package com.benjih.ld32.core;

import java.util.ArrayList;
import java.util.List;

import com.benjih.ld32.gl.Image;

public class UserInterface {
	
	private ResourceManager resources;
	
	private List<Image> backkgroundImages;
	
	public UserInterface (ResourceManager resources) {
		this.resources = resources;
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
					backkgroundImages.add(new Image(x, y, resources.getTexture("background")));
				}	
			}
		} else {
			for(Image image : backkgroundImages) {
				image.render();
			}
		}
	}

}
