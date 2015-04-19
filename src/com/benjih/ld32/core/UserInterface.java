package com.benjih.ld32.core;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;

import com.benjih.ld32.DisplayScale;
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

	private DisplayScale displayScale;
	
	public UserInterface (ResourceManager resources, DisplayScale displayScale) {
		this.resources = resources;
		this.displayScale = displayScale;
		
		this.strings = new HashMap<String, String>();
		strings.put("top", "");
		
		awtFont = resources.getFont("oswald");
		topBarFont = new TrueTypeFont(awtFont.deriveFont(displayScale.scaleFont(24)), true);
		playerCardNameFont = new TrueTypeFont(awtFont.deriveFont(displayScale.scaleFont(36)), true);
	}
	
	public void setString(String id, String string) {
		strings.put(id, string);
	}
	
	public void drawBackground () {
		if(backkgroundImages == null) {
			int screenX = displayScale.scale(1920);
			int imageX = 256;
			
			int screenY = displayScale.scale(1080);
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
			matImages.add(new Image(343, 740, resources.getTexture("player-hand-mat"), displayScale));
			matImages.add(new Image(343, 0, resources.getTexture("opponent-hand-mat"), displayScale));
			matImages.add(new Image(750, 353, resources.getTexture("played-cards-mat"), displayScale));
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
				topbarImages.add(new Image(x, 0, resources.getTexture("top"), displayScale));
			}
		}
		if(pause == null) {
				pause = new Image(screenX - 64, 0, resources.getTexture("pause"), displayScale);
		}
		for(Image image : topbarImages) {
			image.render();
		}
			
		pause.render();
	}

	public void drawTopbarMessage() {
		topBarFont.drawString(displayScale.scale(1920 / 2), displayScale.scale(10), strings.get("top") , new Color(83, 83, 83), TrueTypeFont.ALIGN_CENTER);
	}

	public void drawScoreCards(Player player, Player enemy) {
		new Image(0, 199, resources.getTexture("player-card"), displayScale).render();
		playerCardNameFont.drawString(displayScale.scale(15), displayScale.scale(219), "Computer", new Color(83, 83, 83));
		playerCardNameFont.drawString(displayScale.scale(55), displayScale.scale(284), String.valueOf(enemy.getDeck().size()), new Color(83, 83, 83), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(displayScale.scale(170), displayScale.scale(284), String.valueOf(enemy.getSuprise()), new Color(217, 217, 0), TrueTypeFont.ALIGN_CENTER);
		playerCardNameFont.drawString(displayScale.scale(55), displayScale.scale(345), String.valueOf(enemy.getHealth()), new Color(217, 54, 0), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(displayScale.scale(170), displayScale.scale(345), String.valueOf(enemy.getArmour()), new Color(153, 153, 153), TrueTypeFont.ALIGN_CENTER);
		
		new Image(0, 648, resources.getTexture("player-card"), displayScale).render();
		playerCardNameFont.drawString(displayScale.scale(15), displayScale.scale(668), "You", new Color(83, 83, 83));
		playerCardNameFont.drawString(displayScale.scale(55), displayScale.scale(733), String.valueOf(player.getDeck().size()), new Color(83, 83, 83), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(displayScale.scale(170), displayScale.scale(733), String.valueOf(player.getSuprise()), new Color(217, 217, 0), TrueTypeFont.ALIGN_CENTER);
		playerCardNameFont.drawString(displayScale.scale(55), displayScale.scale(794), String.valueOf(player.getHealth()), new Color(217, 54, 0), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(displayScale.scale(170), displayScale.scale(794), String.valueOf(player.getArmour()), new Color(153, 153, 153), TrueTypeFont.ALIGN_CENTER);
	}

	public void drawTable() {
		drawBackground();
		drawMats();		
	}
}