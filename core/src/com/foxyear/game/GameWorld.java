package com.foxyear.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.foxyear.game.helpers.LevelHelper;
import com.foxyear.game.helpers.PlayerContactListener;
import com.foxyear.game.helpers.PlayerController;
import com.foxyear.game.helpers.TestController;
import com.foxyear.game.objects.*;

import java.util.LinkedList;


public class GameWorld {

    private World world;
    public static LinkedList<GameObject> LevelObjects;
    public Floor floor;
    public StandardEnemy enemy;
    private Player player;
    Stone test;

    public GameWorld() {
        world = new World(new Vector2(0, -10f), true);
        player = new Player(world);
        world.setContactListener(new PlayerContactListener(player));
        InputMultiplexer mult = new InputMultiplexer();
        mult.addProcessor(new PlayerController(player));
        mult.addProcessor(new TestController(test));
        Gdx.input.setInputProcessor(mult);
        floor = new Floor(world, Gdx.files.internal("testMap.json"));
        LevelObjects = LevelHelper.loadLevelFromJSONFile("savedlevel0.json",world);


    }

    public void update(float delta) {
        player.update();
        if(LevelObjects.size()>0)
        for (GameObject gameobj : LevelObjects) {
            gameobj.update();
        }
        world.step(delta, 10, 10);
    }



    public Player getPlayer() {
        return player;
    }
}
