package com.foxyear.game.objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class PermeableObject extends GameObject{
    public PermeableObject(World world, BodyDef bodyDef) {
        super(world, bodyDef);
    }
    public PermeableObject(World world, BodyDef bodyDef, String Path) {
        super(world, bodyDef, Path);
    }
    @Override
    public void update() {

    }

    @Override
    public float getScale() {
        return 0;
    }
}
