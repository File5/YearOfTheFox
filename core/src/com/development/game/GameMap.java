package com.development.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.development.game.helpers.PlayerContactListener;
import com.development.game.objects.Player;

public class GameMap {
    private World world;
    private Body floor;
    private ShapeRenderer shapeRenderer;
    private Player player;

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
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.isGrounded()) {
            Vector2 velocity = player.getBody().getLinearVelocity();
            velocity.add(0, 6);
            player.setGrounded(false);
            player.getBody().setLinearVelocity(velocity);
            //player.getBody().applyForceToCenter(new Vector2(0, 200), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.getBody().applyForceToCenter(new Vector2(-10, 0), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.getBody().applyForceToCenter(new Vector2(10, 0), true);
        }
        world.step(delta, 10, 10);
    }

}
