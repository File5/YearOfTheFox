package com.foxyear.game;

import com.badlogic.gdx.Game;
import com.foxyear.game.screens.GameScreen;


public class MainClass extends Game {
	public static final int PIXELSINMETER = 100;

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
