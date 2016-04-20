package com.foxyear.game.objects;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.YearOfTheFoxGame;

public class Floor {
    public static final String TAG = "GROUND";
    private static final float WIDTH = 20f;
    private static final float HEIGHT = 1f;
    private Body floor;

    public Floor(World world) {
        BodyDef floorDef = new BodyDef();
        floorDef.position.set(0f, 0f);
        floorDef.type = BodyDef.BodyType.StaticBody;
        FixtureDef floorFixture = new FixtureDef();
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(WIDTH / 2, HEIGHT / 2);
        floorFixture.shape = rect;
        floorFixture.density = 1.0f;
        floorFixture.friction = 0.3f;
        floor = world.createBody(floorDef);
        floor.createFixture(floorFixture).setUserData(TAG);
    }

    public Floor(World world, FileHandle fileHandle) {
        BodyEditorLoader loader = new BodyEditorLoader(fileHandle);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.3f;
        floor = world.createBody(bodyDef);
        loader.attachFixture(floor, "ground", fixtureDef, 9.6f);
        for (Fixture fixture : floor.getFixtureList()) {
            fixture.setUserData(TAG);
        }
        Vector2 posBody = loader.getOrigin("ground", 1f).cpy();
        floor.getPosition().set(4.8f, 0f);
    }



}
