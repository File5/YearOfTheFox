package com.foxyear.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.foxyear.game.helpers.PlayerContactListener;
import com.foxyear.game.helpers.PlayerController;
import com.foxyear.game.objects.Player;


public class GameMap {
    private World world;
    private Body floor;
    private ShapeRenderer shapeRenderer;
    private Player player;
    private OrthographicCamera camera;
    private Viewport viewport;
    Player player1;

    public GameMap() {
        world = new World(new Vector2(0, -10f), true);
        BodyDef floorDef = new BodyDef();
        floorDef.position.set(0f, 0f);
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
        player1 = new Player(world, new Vector2(2f, 4f));
        world.setContactListener(new PlayerContactListener(player));
        Gdx.input.setInputProcessor(new PlayerController(player));
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new ScreenViewport(camera);
    }

    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        viewport.update(width, height);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        Vector2 pos = floor.getPosition();
        shapeRenderer.rect(-10f * MainClass.PIXELSINMETER,
                           -0.5f * MainClass.PIXELSINMETER,
                           MainClass.PIXELSINMETER * 10f * 2,
                           MainClass.PIXELSINMETER * 0.5f * 2);
        player.render(shapeRenderer);
        player1.render(shapeRenderer);
        shapeRenderer.setColor(Color.BLUE);
        pos = player.getBody().getPosition();
        shapeRenderer.circle(pos.x * MainClass.PIXELSINMETER, pos.y * MainClass.PIXELSINMETER, 5);
        shapeRenderer.end();
    }

    public void update(float delta) {
        player.update();
        Vector2 pos = player.getBody().getPosition();
        camera.position.x = pos.x * MainClass.PIXELSINMETER;
        //camera.position.y = pos.y * MainClass.PIXELSINMETER;
        camera.update();
        world.step(delta, 10, 10);
    }

}
