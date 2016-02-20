package com.foxyear.game.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.foxyear.game.objects.Player;

public class PlayerController extends InputAdapter {
    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                player.left();
                break;
            case Input.Keys.RIGHT:
                player.right();
                break;
            case Input.Keys.UP:
                player.jump();
                break;
        }
        return true;
    }
}
