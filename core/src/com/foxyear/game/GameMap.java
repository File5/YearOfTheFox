package com.foxyear.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.foxyear.game.helpers.PlayerContactListener;
import com.foxyear.game.helpers.PlayerController;
import com.foxyear.game.objects.Player;


public class GameMap {
    private World world;
    private Body floor;
    private ShapeRenderer shapeRenderer;
    private Player player;
    private OrthographicCamera camera;

    public GameMap() {
        world = new World(new Vector2(0, -10f), true);
        BodyDef floorDef = new BodyDef();
        floorDef.position.set(0, 0);
        floorDef.type = BodyDef.BodyType.StaticBody;
        FixtureDef floorFixture = new FixtureDef();
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(10f, 0.5f);
        floorFixture.shape = rect;
        floorFixture.density = 1.0f;
        floorFixture.friction = 0.3f;
        floor = world.createBody(floorDef);
        floor.createFixture(floorFixture);
        floor.setUserData("GROUND");
        shapeRenderer = new ShapeRenderer();
        player = new Player(world);
        world.setContactListener(new PlayerContactListener(player));
        Gdx.input.setInputProcessor(new PlayerController(player));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        Vector2 pos = floor.getPosition();
        shapeRenderer.rect(pos.x, pos.y, 1000, 100);
        player.render(shapeRenderer);
        shapeRenderer.end();
    }

    public void update(float delta) {
        world.step(delta, 10, 10);
    }

}
