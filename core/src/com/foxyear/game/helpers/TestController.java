package com.foxyear.game.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.foxyear.game.objects.Stone;


public class TestController extends InputAdapter {
    private Stone test;

    public TestController(Stone test) {
        this.test = test;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.P: {
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
