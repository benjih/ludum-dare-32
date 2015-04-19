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
			int screenX = displayScale.scaleX(1920);
			int imageX = 256;
			
			int screenY = displayScale.scaleY(1080);
			int imageY = 256;
			
			backkgroundImages = new ArrayList<Image>();
			
			for(int x = 0; x <= screenX + imageX; x = x + imageX) {
				for(int y = 0; y < screenY + imageY; y = y + imageY) {
					backkgroundImages.add(new Image(x, y, resources.getTexture("background"), new DisplayScale(1920, 1080)));
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
		topBarFont.drawString(displayScale.scaleX(1920 / 2), displayScale.scaleY(10), strings.get("top") , new Color(83, 83, 83), TrueTypeFont.ALIGN_CENTER);
	}

	public void drawScoreCards(Player player, Player enemy) {
		new Image(0, 199, resources.getTexture("player-card"), displayScale).render();
		playerCardNameFont.drawString(displayScale.scaleX(15), displayScale.scaleY(219), "Computer", new Color(83, 83, 83));
		playerCardNameFont.drawString(displayScale.scaleX(55), displayScale.scaleY(284), String.valueOf(enemy.getDeck().size()), new Color(83, 83, 83), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(displayScale.scaleX(170), displayScale.scaleY(284), String.valueOf(enemy.getSuprise()), new Color(217, 217, 0), TrueTypeFont.ALIGN_CENTER);
		playerCardNameFont.drawString(displayScale.scaleX(55), displayScale.scaleY(345), String.valueOf(enemy.getHealth()), new Color(217, 54, 0), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(displayScale.scaleX(170), displayScale.scaleY(345), String.valueOf(enemy.getArmour()), new Color(153, 153, 153), TrueTypeFont.ALIGN_CENTER);
		
		new Image(0, 648, resources.getTexture("player-card"), displayScale).render();
		playerCardNameFont.drawString(displayScale.scaleX(15), displayScale.scaleY(668), "You", new Color(83, 83, 83));
		playerCardNameFont.drawString(displayScale.scaleX(55), displayScale.scaleY(733), String.valueOf(player.getDeck().size()), new Color(83, 83, 83), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(displayScale.scaleX(170), displayScale.scaleY(733), String.valueOf(player.getSuprise()), new Color(217, 217, 0), TrueTypeFont.ALIGN_CENTER);
		playerCardNameFont.drawString(displayScale.scaleX(55), displayScale.scaleY(794), String.valueOf(player.getHealth()), new Color(217, 54, 0), TrueTypeFont.ALIGN_RIGHT);
		playerCardNameFont.drawString(displayScale.scaleX(170), displayScale.scaleY(794), String.valueOf(player.getArmour()), new Color(153, 153, 153), TrueTypeFont.ALIGN_CENTER);
	}

	public void drawTable() {
		drawBackground();
		drawMats();		
	}
}