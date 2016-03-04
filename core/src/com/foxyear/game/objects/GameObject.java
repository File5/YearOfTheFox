package com.foxyear.game.objects;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.YearOfTheFoxGame;

public class GameObject extends Sprite {
    private float width;
    private float height;
    private BodyEditorLoader loader;
    protected Body body;
    private Vector2 pos;

    public GameObject(World world, BodyDef bodyDef, FixtureDef fixtureDef, FileHandle fileHandle, String bodyName, float scale) {
        super();
        loader = new BodyEditorLoader(fileHandle);
        body = world.createBody(bodyDef);
        loader.attachFixture(body, bodyName, fixtureDef, scale);
        setTexture(new Texture(loader.getImagePath(bodyName)));
        width = getWidth();
        height = getHeight();
        System.out.println(width + " " + height);
        pos = loader.getOrigin(bodyName, scale);
        setOrigin(pos.x, pos.y);
        update();
    }

    public void update() {
        pos = body.getPosition();
        setPosition((pos.x - width / 2) * YearOfTheFoxGame.PIXELSINMETER, (pos.y - height / 2) * YearOfTheFoxGame.PIXELSINMETER);
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
