package com.foxyear.game.objects;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.YearOfTheFoxGame;

public class GameObject extends Sprite {
    protected Body body;


    /**
     * Creates a object from JSON with specified parameters
     *
     * @param world      physical world to create object in.
     * @param bodyDef    the body parameters to apply to the created body.
     * @param fixtureDef the fixture parameters to apply to the created body fixture.
     * @param fileHandle file to read JSON from.
     * @param bodyName   name of the body in the file.
     * @param scale      multiplier of the object's size.
     */
    public GameObject(World world, BodyDef bodyDef, FixtureDef fixtureDef, FileHandle fileHandle, String bodyName, float scale) {

        BodyEditorLoader loader = new BodyEditorLoader(fileHandle);
        body = world.createBody(bodyDef);
        loader.attachFixture(body, bodyName, fixtureDef, scale);
        // setTexture() doesn't work, but this works
        // I don't know why
        set(new Sprite(new Texture(loader.getImagePath(bodyName))));
        Vector2 pos = loader.getOrigin(bodyName, scale);

        setOrigin(pos.x, pos.y);
        setScale(scale);
     //   update();
    }

    public GameObject(World world, BodyDef bodyDef) {
        body = world.createBody(bodyDef);
    }


    public void update() {
        Vector2 pos = body.getPosition();
        setPosition(pos.x * YearOfTheFoxGame.PIXELSINMETER, pos.y * YearOfTheFoxGame.PIXELSINMETER);
        setRotation((float) Math.toDegrees(body.getAngle()));
    }

    public Body getBody() {
        return body;
    }

    public void setFixturesData(Object object) {
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData(object);
        }
    }

}
