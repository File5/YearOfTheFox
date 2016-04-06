package com.foxyear.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.YearOfTheFoxGame;

public class Player extends GameObject {
    public static final String TAG = "PLAYER";
    private static final float WIDTH = 1f;
    private static final float HEIGHT = 2;
    private static BodyDef bodyDef;
    private static FixtureDef fixtureDef;
    protected static FileHandle file;
    private static String bodyName;
    private static float scale;

    static {
        bodyDef = new BodyDef();
        bodyDef.position.set(2f, 10f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.3f;
        fixtureDef.density = 1.0f;

        scale = 0.5f;

        file = Gdx.files.internal("Player.json");
        bodyName = "Player";
    }

    private boolean grounded;
    private boolean left;
    private boolean right;
    private boolean jump;

    public Player (World world) {
        super(world, bodyDef, fixtureDef, file, bodyName, scale);

        body.setUserData(TAG);
        FixtureDef groundFixture = new FixtureDef();
        groundFixture.isSensor = true;
        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(new Vector2(0.1f * scale , 0), new Vector2(((WIDTH - 0.1f)*scale) , 0));
        groundFixture.shape = edgeShape;
        body.createFixture(groundFixture).setUserData(TAG + "GROUND");

        grounded = false;
        left = false;
        right = false;
        jump = false;

    }

    @Deprecated
    public Player(World world, Vector2 pos) {
        this(world);
        body.getPosition().set(pos);
    }



    public void update() {
        super.update();
        if (left) {
            body.applyForceToCenter(new Vector2(-10, 0), true);
        }
        if (right) {
            body.applyForceToCenter(new Vector2(10, 0), true);
        }
        if (jump && isGrounded()) {
            setGrounded(false);
            Vector2 velocity = body.getLinearVelocity();
            velocity.set(0,6);
            body.setLinearVelocity(velocity);

        }
    Vector2 pos = body.getPosition();
    setPosition(pos.x * YearOfTheFoxGame.PIXELSINMETER, pos.y * YearOfTheFoxGame.PIXELSINMETER);
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

    public float getX(){
        return body.getPosition().x;
    }
    public float getY(){
        return body.getPosition().y;
    }
}
