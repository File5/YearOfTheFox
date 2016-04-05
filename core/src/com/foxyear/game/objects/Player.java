package com.foxyear.game.objects;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.YearOfTheFoxGame;



public class Player extends Sprite{
    public static final String TAG = "PLAYER";
    private static final float WIDTH = 0.5f;
    private static final float HEIGHT = 1;

    public Texture tex;

    private Body body;
    private boolean grounded;
    private boolean left;
    private boolean right;
    private boolean jump;
    private FileHandle file;
    private float scale = 1f;

    public Player (World world) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(2f, 10f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.3f;
        fixtureDef.density = 1.0f;

        FixtureDef groundFixture = new FixtureDef();
        groundFixture.isSensor = true;
        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(new Vector2((int)(-WIDTH / 1.1) , -HEIGHT / 2), new Vector2((int)(WIDTH / 1.1) , -HEIGHT / 2));
        groundFixture.shape = edgeShape;

        file = Gdx.files.internal("Player.json");
        body = world.createBody(bodyDef);
        BodyEditorLoader loader = new BodyEditorLoader(file);
        body.createFixture(groundFixture).setUserData(TAG + "GROUND");
        loader.attachFixture(body, "Player", fixtureDef, scale);

       // body.setUserData(this);



        grounded = false;
        left = false;
        right = false;
        jump = false;


        set(new Sprite(new Texture(loader.getImagePath("Player"))));
       // setSize(50,100);
        Vector2 pos = loader.getOrigin("Player", scale);
        setOrigin(pos.x,pos.y);
        setScale(scale);
        update();



    }

    public Player(World world, Vector2 pos) {
        this(world);
        body.getPosition().set(pos);
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
    Vector2 pos = body.getPosition();

    setPosition(pos.x * YearOfTheFoxGame.PIXELSINMETER, pos.y * YearOfTheFoxGame.PIXELSINMETER);
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

    public float getX(){
        return body.getPosition().x;
    }
    public float getY(){
        return body.getPosition().y;
    }
}
