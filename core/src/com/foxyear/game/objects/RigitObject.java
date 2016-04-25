package com.foxyear.game.objects;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.YearOfTheFoxGame;

public class RigitObject extends GameObject {

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
    float scale;
    public RigitObject(World world, BodyDef bodyDef, FixtureDef fixtureDef, FileHandle fileHandle, String bodyName, float scale) {
        super(world,bodyDef);
        BodyEditorLoader loader = new BodyEditorLoader(fileHandle);
        loader.attachFixture(body, bodyName, fixtureDef, scale);
        // setTexture() doesn't work, but this works
        // I don't know why
        Sprite sprite = new Sprite(new Texture(loader.getImagePath(bodyName)));
        set(sprite);
        Vector2 pos = loader.getOrigin(bodyName, scale);
        // float maxhw = Math.max(sprite.getHeight(), sprite.getWidth());
        setOrigin(pos.x, pos.y);
        setScale(YearOfTheFoxGame.PPM * scale / sprite.getWidth());
        this.scale = scale;
    }

    public void update() {
        Vector2 pos = body.getPosition();
        setPosition(pos.x * YearOfTheFoxGame.PPM, pos.y * YearOfTheFoxGame.PPM);
        setRotation((float) Math.toDegrees(body.getAngle()));
    }

    public void setFixturesData(Object object) {
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData(object);
        }
    }
    public float getScale() {
        return scale;
    }
}
