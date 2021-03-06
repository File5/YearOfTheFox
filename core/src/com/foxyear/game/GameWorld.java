package com.foxyear.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.foxyear.game.helpers.PlayerContactListener;
import com.foxyear.game.helpers.PlayerController;
import com.foxyear.game.helpers.TestController;
import com.foxyear.game.objects.Floor;
import com.foxyear.game.objects.Player;

import com.foxyear.game.objects.TestObject;

import com.foxyear.game.objects.StandardEnemy;


public class GameWorld {
    public static final int V_WIDTH = 800;
    public static final int V_HEIGTH = 400;

    private World world;
    public Floor floor;
    public StandardEnemy enemy;
    private Player player;
    TestObject test;

    public GameWorld() {
        world = new World(new Vector2(0, -10f), true);
        player = new Player(world);
        test = new TestObject(world);
        world.setContactListener(new PlayerContactListener(player));
        InputMultiplexer mult = new InputMultiplexer();
        mult.addProcessor(new PlayerController(player));
        mult.addProcessor(new TestController(test));
        Gdx.input.setInputProcessor(mult);

        //floor = new Floor(world, Gdx.files.internal("ground.json"));
        floor = new Floor(world, Gdx.files.internal("testMap.json"));



        enemy = new StandardEnemy(world,0f,5f);
//        test = new GameObject(world, testBd, testFd, Gdx.files.internal("test.json"), "test", 1.0f);
//        test.setFixturesData("GROUND");

    }

    public void update(float delta) {
        player.update();

        test.update();

        //test.update();
        enemy.update(player,delta);

        world.step(delta, 10, 10);

    }

    public Player getPlayer() {
        return player;
    }
}
