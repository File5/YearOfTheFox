package com.foxyear.game.helpers.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.foxyear.game.GameWorld;
import com.foxyear.game.objects.Player;

public class PlayerActionListener extends InputAdapter {
    private Player player;
    private PlayerController playerController;

    public PlayerActionListener(Player player) {
        this.player = player;
        playerController = player.getController();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                playerController.left();
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                playerController.right();
                break;
            case Input.Keys.UP:
            case Input.Keys.W:
                playerController.jump();
                break;
            case Input.Keys.O:
                player.getBody().setTransform(2f, 3f, 0);
                player.setGrounded(false);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                playerController.leftReleased();
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                playerController.rightReleased();
                break;
            case Input.Keys.UP:
            case Input.Keys.W:
                playerController.jumpReleased();
                break;
            case Input.Keys.O:
                playerController.jumpReleased();
                break;
        }
        return true;
    }

}
