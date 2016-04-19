package com.foxyear.game.objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Item extends PermeableObject {
    String title;
    public Item(World world, BodyDef bodyDef, String Path) {
        super(world, bodyDef, Path);
    }
}
