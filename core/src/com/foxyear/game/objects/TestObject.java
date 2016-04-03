package com.foxyear.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The TestObject class is an example of extending GameObject.
 * All stuff created in static block and passed to the super() constructor.
 */
public class TestObject extends GameObject {
    private static BodyDef bodyDef;
    private static FixtureDef fixtureDef;
    private static FileHandle file;
    private static String bodyName;
    private static float scale;

    static {
        bodyDef = new BodyDef();
        bodyDef.position.set(4f, 4f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        fixtureDef = new FixtureDef();
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;
        file = Gdx.files.internal("test.json");
        bodyName = "test";
        scale = 1f;

    }

    public TestObject(World world) {
        super(world, bodyDef, fixtureDef, file, bodyName, scale);
        setFixturesData("GROUND");
    }

}
