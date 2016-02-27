package com.foxyear.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.MainClass;

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

    public Vector2 getPosition() {
        return floor.getPosition();
    }

    public void render(ShapeRenderer renderer) {
        Vector2 pos = floor.getPosition();
        Color color = renderer.getColor();
        renderer.setColor(Color.GREEN);
        renderer.rect(
                (pos.x) * MainClass.PIXELSINMETER,
                (pos.y) * MainClass.PIXELSINMETER,
                WIDTH / 2,
                HEIGHT / 2,
                WIDTH,
                HEIGHT,
                MainClass.PIXELSINMETER,
                MainClass.PIXELSINMETER,
                0
        );
        renderer.setColor(color);
    }

}
