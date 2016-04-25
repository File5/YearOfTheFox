package com.foxyear.game.helpers.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.foxyear.game.objects.Player;

public class PlayerController {

    private Player player;
    private Body body;
    private boolean left;
    private boolean right;
    private boolean jump;

    public PlayerController(Player player) {
        this.player = player;
        body = player.getBody();
    }

    public void updateMotion() {
        if (left) {
            body.applyForceToCenter(new Vector2(-6, 0), true);
        }
        if (right) {
            body.applyForceToCenter(new Vector2(6, 0), true);
        }
        if (jump && player.isGrounded()) {
            Vector2 velocity = body.getLinearVelocity();
            velocity.set(0, 16);
            body.applyForceToCenter(velocity,true);
            player.setGrounded(false);
        }
    }
    public void left() {
        left = true;
    }

    public void leftReleased() {
        left = false;
    }

    public void right() {
        right = true;
    }

    public void rightReleased() {
        right = false;
    }

    public void jump() {
        jump = true;
        System.out.println(jump+"  "+player.isGrounded());
    }

    public void jumpReleased() {
        jump = false;
    }

}
