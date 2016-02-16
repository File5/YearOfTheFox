package com.foxyear.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.MainClass;


public class Player {
    private final static float WIDTH = 1;
    private final static float HEIGHT = 1;
    private Body body;
    private boolean grounded;

    public Player(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(2, 2);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.3f;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH / 2, HEIGHT / 2);
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        grounded = false;
    }

    public void render(ShapeRenderer renderer) {
        Vector2 pos = body.getPosition();
        Color color = renderer.getColor();
        renderer.setColor(Color.WHITE);
        renderer.rect(pos.x * MainClass.PIXELSINMETER,
                      pos.y * MainClass.PIXELSINMETER,
                      WIDTH * MainClass.PIXELSINMETER,
                      HEIGHT * MainClass.PIXELSINMETER);
        renderer.setColor(color);
    }

    public Body getBody() {
        return body;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public boolean isGrounded() {
        return grounded;
    }

}
