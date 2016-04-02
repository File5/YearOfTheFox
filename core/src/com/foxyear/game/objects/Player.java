package com.foxyear.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.YearOfTheFoxGame;
import com.foxyear.game.helpers.AssetHelpers;


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

    public Player (World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(2f, 2f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.3f;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH / 2, HEIGHT / 2);
        fixtureDef.shape = shape;
        fixtureDef.density = 3.0f;

        FixtureDef groundFixture = new FixtureDef();
        groundFixture.isSensor = true;
        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(new Vector2(-WIDTH / 4 , -HEIGHT / 2), new Vector2(WIDTH / 4 , -HEIGHT / 2));
        groundFixture.shape = edgeShape;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef).setUserData(TAG);
        body.createFixture(groundFixture).setUserData(TAG + "GROUND");
        //body.setUserData(this);

        shape.dispose();

        grounded = false;
        left = false;
        right = false;
        jump = false;

        tex = AssetHelpers.playertex;
       set(new Sprite(tex));
        setSize(50,100);



    }

    public Player(World world, Vector2 pos) {
        this(world);
        body.getPosition().set(pos);
    }

//    public void render(ShapeRenderer renderer) {
//        Vector2 pos = body.getPosition();
//        float angle = (float) Math.toDegrees(body.getAngle());
//        Color color = renderer.getColor();
//        renderer.setColor(Color.WHITE);
//        renderer.rect(
//                (pos.x) * YearOfTheFoxGame.PIXELSINMETER,
//                (pos.y) * YearOfTheFoxGame.PIXELSINMETER,
//                WIDTH / 2,
//                HEIGHT / 2,
//                WIDTH,
//                HEIGHT,
//                YearOfTheFoxGame.PIXELSINMETER,
//                YearOfTheFoxGame.PIXELSINMETER,
//                angle
//        );
//        renderer.setColor(Color.BLUE);
//        renderer.rect(
//                (pos.x) * YearOfTheFoxGame.PIXELSINMETER,
//                (pos.y) * YearOfTheFoxGame.PIXELSINMETER,
//                WIDTH / 2,
//                HEIGHT / 2,
//                WIDTH,
//                0.1f,
//                YearOfTheFoxGame.PIXELSINMETER,
//                YearOfTheFoxGame.PIXELSINMETER,
//                angle
//        );
//        renderer.setColor(color);
//    }

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

    setPosition(pos.x * YearOfTheFoxGame.PIXELSINMETER-25, pos.y * YearOfTheFoxGame.PIXELSINMETER-50);
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
