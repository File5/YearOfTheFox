package com.foxyear.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Floor {
    public static final String TAG = "GROUND";
    private Body floor;

    public Floor(World world) {
        BodyDef floorDef = new BodyDef();
        floorDef.position.set(0f, 0f);
        floorDef.type = BodyDef.BodyType.StaticBody;
        FixtureDef floorFixture = new FixtureDef();
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(10f, 0.5f);
        floorFixture.shape = rect;
        floorFixture.density = 1.0f;
        floorFixture.friction = 0.3f;
        floor = world.createBody(floorDef);
        floor.createFixture(floorFixture).setUserData(TAG);
    }

    public Vector2 getPosition() {
        return floor.getPosition();
    }
}
