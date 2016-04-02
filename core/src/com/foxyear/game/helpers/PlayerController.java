package com.foxyear.game.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.foxyear.game.GameWorld;
import com.foxyear.game.objects.Player;

public class PlayerController extends InputAdapter {
    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT: case Input.Keys.A :
                player.left();
                break;
            case Input.Keys.RIGHT: case Input.Keys.D :
                player.right();
                break;
            case Input.Keys.UP:case Input.Keys.W :
                player.jump();
                break;
            case Input.Keys.O:
                player.getBody().setTransform(2f,3f,0);
                player.setGrounded(true);
                player.jump();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT: case Input.Keys.A :
                player.leftReleased();
                break;
            case Input.Keys.RIGHT:case Input.Keys.D :
                player.rightReleased();
                break;
            case Input.Keys.UP:case Input.Keys.W :
                player.jumpReleased();
                break;
            case Input.Keys.O:
                player.jumpReleased();
                break;
        }
        return true;
    }

}
