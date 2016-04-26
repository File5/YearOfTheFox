package com.foxyear.game.helpers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.foxyear.game.objects.Player;

public class PlayerContactListener implements ContactListener {
    private Player player;

    public PlayerContactListener(Player player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact) {
        Object obj1 = contact.getFixtureA().getUserData();
        Object obj2 = contact.getFixtureB().getUserData();
        // do not check (null & something) collusion
        if (obj1 == null || obj2 == null) {
            return;
        }
        // if it is (Floor & Player) collusion
        if (obj1.toString().equals(Player.TAG) || obj2.toString().equals(Player.TAG)) {
            player.setGrounded(true);
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object obj1 = contact.getFixtureA().getUserData();
        Object obj2 = contact.getFixtureB().getUserData();
        // do not check (null & something) collusion ending
        if (obj1 == null || obj2 == null) {
            return;
        }
        // if it is (Floor & Player) collusion ending
        if (obj1.toString().equals(Player.TAG) || obj2.toString().equals(Player.TAG)) {
            player.setGrounded(false);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
