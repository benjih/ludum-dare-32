package com.benjih.ld32.core;

import org.lwjgl.input.Mouse;

import com.benjih.ld32.DisplayScale;
import com.benjih.ld32.card.Deck;
import com.benjih.ld32.card.PlayingCardPosition;
import com.benjih.ld32.gl.GameDisplay;
import com.benjih.ld32.gl.Image;
import com.benjih.ld32.resources.ResourceManager;

public class Game {

	private Player player;
	private Player enemy;

	private ResourceManager resources;

	private UserInterface userInterface;

	private TurnManager turnManager;

	private boolean shouldPause;
	private long time = 0;
	private DisplayScale displayScale;

	public Game(GameDisplay display, ResourceManager resources, UserInterface userInterface) {
		this.resources = resources;

		displayScale = display.getDisplayScale();
		player = new Player(new Deck(resources, displayScale));
		enemy = new Player(new Deck(resources, displayScale));

		this.userInterface = userInterface;

		shouldPause = false;

		this.turnManager = new TurnManager(player, enemy, userInterface, displayScale);

	}

	public TurnState run (TurnState state) {
		state = turnManager.isGameOver(state);
		
		state = turnManager.drawCard(state);
		state = turnManager.playCard(state, new HumanController(), new AIController());
		
		userInterface.drawTable();
		render();
		userInterface.drawTopbar();
		userInterface.drawTopbarMessage();
		userInterface.drawScoreCards(player, enemy);
		
		if(state.equals(TurnState.ENEMY_WIN)) {
			if(time == 0) {
				time = GameDisplay.getTime();
			}
			new Image(0, 0, resources.getTexture("loser"), displayScale).render();
			if (GameDisplay.getTime() >= time + 500) {
				if (Mouse.isButtonDown(0) && MouseUtils.isClick(0, 0, 1920, 1080, displayScale)) {
					return TurnState.END;
					
				}
			}
		} else if(state.equals(TurnState.PLAYER_WIN)) {
			if(time == 0) {
				time = GameDisplay.getTime();
			}
			new Image(0, 0, resources.getTexture("winner"), displayScale).render();
			if (GameDisplay.getTime() >= time + 500) {
				if (Mouse.isButtonDown(0) && MouseUtils.isClick(0, 0, 1920, 1080, displayScale)) {
					return TurnState.END;
					
				}
			}
		}
		
		if(!shouldPause) {
			shouldPause = shouldPause();
		} else {
			new Image(0, 0, resources.getTexture("pause-menu"), displayScale).render();
			
			if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 468, 500, 52, displayScale)) {
				shouldPause = false;
			}
			
			if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 612, 500, 52, displayScale)) {
				shouldPause = false;
				return TurnState.ENEMY_WIN;
			}
		}
		
		return state;
	}

	private boolean shouldPause() {
		return Mouse.isButtonDown(0) && MouseUtils.isClick(1920 - 64, 0, 64, 64, displayScale);
	}

	private void render() {
		if (player.getLastPlayed() != null) {
			player.getLastPlayed().setPosition(PlayingCardPosition.POS_PLAYED);
			player.getLastPlayed().render();
		}
		if (enemy.getLastPlayed() != null) {
			enemy.getLastPlayed().setPosition(PlayingCardPosition.POS_ENEMY_PLAYED);
			enemy.getLastPlayed().render();
		}
		player.getHand().render();
		enemy.getHand().renderHidden(new Image(0, 0, resources.getTexture("card-back"), displayScale));
	}
}