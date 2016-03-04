package com.foxyear.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.foxyear.game.helpers.PlayerContactListener;
import com.foxyear.game.helpers.PlayerController;
import com.foxyear.game.objects.Floor;
import com.foxyear.game.objects.GameObject;
import com.foxyear.game.objects.Player;

public class GameWorld {
    public static final int V_WIDTH = 800;
    public static final int V_HEIGTH = 400;

    private World world;
    public Floor floor;
    private Player player;
    GameObject test;

    public GameWorld() {
        world = new World(new Vector2(0, -10f), true);
        player = new Player(world);
        world.setContactListener(new PlayerContactListener(player));
        Gdx.input.setInputProcessor(new PlayerController(player));
        //floor = new Floor(world, Gdx.files.internal("ground.json"));
        floor = new Floor(world, Gdx.files.internal("testMap.json"));
        BodyDef testBd = new BodyDef();
        testBd.type = BodyDef.BodyType.StaticBody;
        testBd.position.set(2f, 0f);
        testBd.fixedRotation = true;
        FixtureDef testFd = new FixtureDef();
        testFd.friction = 0.3f;
        testFd.density = 1.0f;
//        test = new GameObject(world, testBd, testFd, Gdx.files.internal("test.json"), "test", 1.0f);
//        test.setFixturesData("GROUND");
    }

    public void update(float delta) {
        player.update();
        //test.update();
        world.step(delta, 10, 10);
    }

    public Player getPlayer() {
        return player;
    }
}
