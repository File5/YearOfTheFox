package com.foxyear.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.foxyear.game.GameMap;
import com.foxyear.game.scene.HUD;


public class GameScreen implements Screen {
    private GameMap map;
    private HUD hud;

    @Override
    public void show() {
        map = new GameMap();
    }

    @Override
    public void render(float delta) {
        map.update(delta);
        map.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        map.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
