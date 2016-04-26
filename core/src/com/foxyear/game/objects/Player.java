package com.foxyear.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.foxyear.game.YearOfTheFoxGame;
import com.foxyear.game.helpers.controllers.PlayerController;

public class Player extends RigitObject {
    public static final String TAG = "PLAYER";
    private static final float WIDTH = 1f;
    private static final float HEIGHT = 2;
    private static BodyDef bodyDef;
    private static FixtureDef fixtureDef;
    protected static FileHandle file;
    private static String bodyName;
    private static float scale;

    private boolean grounded;
    private PlayerController playerController;

    static {
        bodyDef = new BodyDef();
        bodyDef.position.set(2f, 5f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.4f;
        fixtureDef.density = 1.0f;
        fixtureDef.restitution = 0f;

        scale = 0.5f;

        file = Gdx.files.internal("Player.json");
        bodyName = "Player";
    }


    public Player(World world) {
        super(world, bodyDef, fixtureDef, file, bodyName, scale);
        FixtureDef groundFixture = new FixtureDef();
      //  groundFixture.isSensor = true;
        groundFixture.restitution = 0;
        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(new Vector2(0.1f * scale , 0f), new Vector2(((WIDTH/3 - 0.1f)*scale) , 0f));
        groundFixture.shape = edgeShape;
        body.createFixture(groundFixture).setUserData(TAG);

        grounded = false;
        playerController = new PlayerController(this);
    }

    public void update() {
        super.update();
        playerController.updateMotion();
        Vector2 pos = body.getPosition();
        setPosition(pos.x * YearOfTheFoxGame.PPM, pos.y * YearOfTheFoxGame.PPM);
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public boolean isGrounded() {
//        Array<Contact> cont = body.getWorld().getContactList();
//        for (Contact contact : cont) {
//            if (contact.getFixtureB().getUserData() != null && contact.getFixtureA().getUserData() != null)
//                if (contact.getFixtureA().getUserData().equals(TAG) || contact.getFixtureB().getUserData().equals(TAG)) {
//                    grounded = true;
//                    return grounded;
//                } else {
//                    grounded = false;
//                }
//        }
        return grounded;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public PlayerController getController() {
        return playerController;
    }
}
