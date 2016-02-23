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
    private boolean left;
    private boolean right;
    private boolean jump;

    public Player(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(2f, 2f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //bodyDef.fixedRotation = true;
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.3f;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH / 2, HEIGHT / 2);
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        shape.dispose();
        grounded = false;
        left = false;
        right = false;
        jump = false;
    }

    public Player(World world, Vector2 pos) {
        this(world);
        body.getPosition().set(pos);
    }

    public void render(ShapeRenderer renderer) {
        Vector2 pos = body.getPosition();
        float angle = (float) Math.toDegrees(body.getAngle());
        Color color = renderer.getColor();
        renderer.setColor(Color.WHITE);
        renderer.rect(
                (pos.x - WIDTH / 2) * MainClass.PIXELSINMETER,
                (pos.y - HEIGHT / 2) * MainClass.PIXELSINMETER,
                WIDTH / 2,
                HEIGHT / 2,
                WIDTH,
                HEIGHT,
                MainClass.PIXELSINMETER,
                MainClass.PIXELSINMETER,
                angle
        );
        /*
        renderer.rect((pos.x - WIDTH / 2) * MainClass.PIXELSINMETER,
                      pos.y * MainClass.PIXELSINMETER,
                      WIDTH * MainClass.PIXELSINMETER,
                      HEIGHT * MainClass.PIXELSINMETER);
        //*/
        renderer.setColor(color);
    }

    public void update() {
        if (left) {
            body.applyForceToCenter(new Vector2(-10, 0), true);
        }
        if (right) {
            body.applyForceToCenter(new Vector2(10, 0), true);
        }
        if (jump && isGrounded()) {
            setGrounded(false);
            Vector2 velocity = body.getLinearVelocity();
            velocity.add(0, 6);
            body.setLinearVelocity(velocity);
        }
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

    public void left() {
        left = true;
    }

    public void leftReleased() {
        left = false;
    }

    public void right() {
        right = true;
    }

    public void rightReleased() {
        right = false;
    }

    public void jump() {
        jump = true;
    }

    public void jumpReleased() {
        jump = false;
    }

}
