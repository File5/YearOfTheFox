package com.foxyear.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.foxyear.game.scene.HUD;
import com.foxyear.game.screens.GameScreen;


public class MainClass extends Game {
	public static int PIXELSINMETER = 100;

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
