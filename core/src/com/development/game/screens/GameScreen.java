package com.development.game.screens;

import com.badlogic.gdx.Screen;
import com.development.game.GameMap;

public class GameScreen implements Screen {
    private GameMap map;

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
