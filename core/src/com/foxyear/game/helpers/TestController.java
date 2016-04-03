package com.foxyear.game.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.foxyear.game.objects.TestObject;

/**
 * Created by Xoul on 02.04.2016.
 */
public class TestController extends InputAdapter {
    private TestObject test;

    public TestController(TestObject test) {
        this.test = test;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.P: {
                test.getBody().setTransform(4f, 3f, 0);
                test.update();
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
