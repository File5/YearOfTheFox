package com.foxyear.game;

import com.badlogic.gdx.Game;

import com.foxyear.game.helpers.AssetHelpers;
import com.foxyear.game.screens.GameScreen;


public class YearOfTheFoxGame extends Game {
	public static int PIXELSINMETER = 100;

	@Override
	public void create() {
		AssetHelpers.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose(){
		super.dispose();
		AssetHelpers.dispose();
	}
}
