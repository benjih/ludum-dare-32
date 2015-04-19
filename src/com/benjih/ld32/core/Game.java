package com.benjih.ld32.core;

import org.lwjgl.input.Mouse;

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

	public Game(GameDisplay display, ResourceManager resources) {
		this.resources = resources;

		player = new Player(new Deck(resources));
		enemy = new Player(new Deck(resources));

		userInterface = new UserInterface(resources);

		shouldPause = false;

		this.turnManager = new TurnManager(player, enemy, userInterface);

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
		
		if(!shouldPause) {
			shouldPause = shouldPause();
		} else {
			new Image(0, 0, resources.getTexture("pause-menu"), 1.0f).render();
			
			if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 468, 500, 52)) {
				shouldPause = false;
			}
			
			if (Mouse.isButtonDown(0) && MouseUtils.isClick(710, 612, 500, 52)) {
				shouldPause = false;
				return TurnState.ENEMY_WIN;
			}
		}
		
		return state;
	}

	private boolean shouldPause() {
		return Mouse.isButtonDown(0)
				&& MouseUtils.isClick(1920 - 64, 0, 64, 64);
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
		enemy.getHand().renderHidden(new Image(0, 0, resources.getTexture("card-back"), 1.0f));
	}
}