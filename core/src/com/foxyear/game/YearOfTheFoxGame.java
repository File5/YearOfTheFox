package com.foxyear.game;

import com.badlogic.gdx.Game;


import com.foxyear.game.helpers.graphics.AssetsLoader;
import com.foxyear.game.screens.GameScreen;


public class YearOfTheFoxGame extends Game {
	public static final int PPM = 100;
	public static final int INVENTORYSIZE = 10;
	public static  int SavedNumber = 0;

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
