package com.development.game;

import com.badlogic.gdx.Game;
import com.development.game.screens.GameScreen;

public class Main extends Game {
	public static final int PIXELSINMETER = 100;

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
