package com.foxyear.game.screens;

import com.badlogic.gdx.Screen;
import com.foxyear.game.GameRenderer;
import com.foxyear.game.GameWorld;
import com.foxyear.game.scene.HUD;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private HUD hud;

    @Override
    public void show() {
        world = new GameWorld();
        renderer = new GameRenderer(world);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
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
