package com.foxyear.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.GameWorld;

/**
 * Created by Xoul on 05.03.2016.
 */
public class StandardEnemy {

    private Body body;
    private static final float WIDTH = 1;
    private static final float HEIGHT = 1;

    public StandardEnemy(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(2f, 4f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.3f;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH / 2, HEIGHT / 2);
        fixtureDef.shape = shape;
        fixtureDef.density = 3.0f;



        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef).setUserData("dfs");
        //body.setUserData(this);

        shape.dispose();
    }

    public void update(Player player, float delta) {
//        body.getPosition().set(body.getPosition().x+(body.getPosition().x-player.getX())*delta,body.getPosition().y);
        if (player.getX() < body.getPosition().x)
            body.applyForceToCenter(new Vector2(-10, 0), true);
        else {
            body.applyForceToCenter(new Vector2(10, 0), true);
        }
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }
}
