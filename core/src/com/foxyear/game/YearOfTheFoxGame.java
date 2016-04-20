package com.foxyear.game;

import com.badlogic.gdx.Game;


import com.foxyear.game.helpers.AssetsLoader;
import com.foxyear.game.screens.GameScreen;


public class YearOfTheFoxGame extends Game {
	public static final int PIXELSINMETER = 100;
	public static final int INVENTORYSIZE = 10;

	@Override
	public void create() {
		setScreen(new GameScreen());
	}

	@Override
	public void dispose(){
		AssetsLoader.getInstance().dispose();
		super.dispose();
	}
}
