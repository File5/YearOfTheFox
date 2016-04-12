package com.foxyear.game.objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;


public abstract class GameObject extends Sprite {
    protected Body body;

    /**
     * @param world   physical world to create object in.
     * @param bodyDef the body parameters to apply to the created body.
     */
    public GameObject(World world, BodyDef bodyDef) {
        body = world.createBody(bodyDef);
    }

    public GameObject(World world, BodyDef bodyDef, String Path) {
        this(world, bodyDef);
        Sprite sprite = new Sprite(new Texture(Path));
        set(sprite);
    }

    abstract public void update();

    public Body getBody() {
        return body;
    }
}
