package com.benjih.ld32.core;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;

import com.benjih.ld32.gl.Image;
import com.benjih.ld32.gl.TrueTypeFont;
import com.benjih.ld32.resources.ResourceManager;

public class UserInterface {
	
	private ResourceManager resources;
	
	private List<Image> backkgroundImages, matImages, topbarImages;
	private Image pause;
	
	private Map<String, String> strings;

	private TrueTypeFont topBarFont, playerCardNameFont;

	private Font awtFont;
	
	public UserInterface (ResourceManager resources) {
		this.resources = resources;
		
		this.strings = new HashMap<String, String>();
		strings.put("top", "");
		
		awtFont = resources.getFont("oswald");
		topBarFont = new TrueTypeFont(awtFont.deriveFont(24f), true);
		playerCardNameFont = new TrueTypeFont(awtFont.deriveFont(36f), true);
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
					backkgroundImages.add(new Image(x, y, resources.getTexture("background"), 1.0f));
				}	
			}

		} else {
			for(Image image : backkgroundImages) {
				image.render();
			}
		}
	}
	
	public void drawMats () {
		if(matImages == null) {
			matImages = new ArrayList<Image>();
			matImages.add(new Image(343, 740, resources.getTexture("player-hand-mat"), 1.0f));
			matImages.add(new Image(343, 0, resources.getTexture("opponent-hand-mat"), 1.0f));
			matImages.add(new Image(750, 353, resources.getTexture("played-cards-mat"), 1.0f));
		} else {
			for(Image image : matImages) {
				image.render();
			}
		}
	}
	
	public void drawTopbar () {
		int screenX = 1920;
		int imageX = 256;
		if(topbarImages == null) {
			
			
			topbarImages = new ArrayList<Image>();
			
			for(int x = 0; x < screenX + (imageX / 2); x = x + imageX) {
				topbarImages.add(new Image(x, 0, resources.getTexture("top"), 1.0f));
			}
		}
		if(pause == null) {
				pause = new Image(screenX - 64, 0, resources.getTexture("pause"), 1.0f);
		}
		for(Image image : topbarImages) {
			image.render();
		}
			
		pause.render();
	}

	public void drawTopbarMessage() {
		topBarFont.drawString(1920/2, 10, strings.get("top") , new Color(83, 83, 83), TrueTypeFont.ALIGN_CENTER);
	}

	public void drawScoreCards(Player player, Player enemy) {
		new Image(0, 199, resources.getTexture("player-card"), 1.0f).render();
		playerCardNameFont.drawString(15, 219, "Computer", new Color(83, 83, 83));
		playerCardNameFont.drawString(55, 284, String.valueOf(enemy.getDeck().size()), new Color(83, 83, 83), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(170, 284, String.valueOf(enemy.getSuprise()), new Color(217, 217, 0), TrueTypeFont.ALIGN_CENTER);
		playerCardNameFont.drawString(55, 345, String.valueOf(enemy.getHealth()), new Color(217, 54, 0), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(170, 345, String.valueOf(enemy.getArmour()), new Color(153, 153, 153), TrueTypeFont.ALIGN_CENTER);
		
		new Image(0, 648, resources.getTexture("player-card"), 1.0f).render();
		playerCardNameFont.drawString(15, 668, "You", new Color(83, 83, 83));
		playerCardNameFont.drawString(55, 733, String.valueOf(player.getDeck().size()), new Color(83, 83, 83), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(170, 733, String.valueOf(player.getSuprise()), new Color(217, 217, 0), TrueTypeFont.ALIGN_CENTER);
		playerCardNameFont.drawString(55, 794, String.valueOf(player.getHealth()), new Color(217, 54, 0), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(170, 794, String.valueOf(player.getArmour()), new Color(153, 153, 153), TrueTypeFont.ALIGN_CENTER);
	}

	public void drawTable() {
		drawBackground();
		drawMats();		
	}
}