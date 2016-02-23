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
        Object userData = contact.getFixtureA().getBody().getUserData();
        if (userData != null && userData.toString().equals("GROUND")) {
            player.setGrounded(true);
        }
    }

    @Override
    public void endContact(Contact contact) {
//        if (contact.getFixtureA().getBody().getUserData().toString().equals("GROUND")) {
//            player.setGrounded(false);
//        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
