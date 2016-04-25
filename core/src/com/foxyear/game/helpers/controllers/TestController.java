package com.foxyear.game.helpers.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.foxyear.game.GameWorld;
import com.foxyear.game.helpers.LevelHelper;
import com.foxyear.game.objects.Stone;

import java.io.IOException;


public class TestController extends InputAdapter {
    private Stone test;

    public TestController(Stone test) {
        this.test = test;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.P: {
                try {
                    LevelHelper.saveLevel(GameWorld.LevelObjects);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Не получилось сохранить файл");
                }
                break;
            }
        }
        return true;

    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }
}
