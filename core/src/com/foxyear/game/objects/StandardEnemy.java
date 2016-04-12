package com.foxyear.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Xoul on 05.03.2016.
 */
public class StandardEnemy extends RigitObject {

    private static BodyDef bodyDef;
    private static FixtureDef fixtureDef;
    private static FileHandle file;
    private static String bodyName;
    private static float scale;

    static {
        bodyDef = new BodyDef();
        bodyDef.position.set(6f, 6f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        fixtureDef = new FixtureDef();
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;
        file = Gdx.files.internal("Enemy.json");
        bodyName = "Enemy";
        scale = 1f;

    }

    public StandardEnemy(World world) {
        super(world, bodyDef, fixtureDef, file, bodyName, scale);
        setFixturesData("GROUND");
    }



}
