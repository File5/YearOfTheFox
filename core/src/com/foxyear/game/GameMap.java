package com.foxyear.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.foxyear.game.helpers.AssetHelpers;
import com.foxyear.game.helpers.PlayerContactListener;
import com.foxyear.game.helpers.PlayerController;
import com.foxyear.game.objects.Floor;
import com.foxyear.game.objects.Player;
import com.foxyear.game.scene.HUD;

public class GameMap {

    public static final int V_WIDTH = 800;
    public static final int V_HEIGTH = 400;

    private World world;
    private Floor floor;
    private ShapeRenderer shapeRenderer;
    private Player player;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Player player1;
    private Box2DDebugRenderer debugRenderer;
    public HUD hud;
    private SpriteBatch batch;

    public int gameHeight;
    public int gameWidth;

    public GameMap() {
        gameHeight = Gdx.graphics.getHeight();
        gameWidth = Gdx.graphics.getWidth();

        world = new World(new Vector2(0, -10f), true);
        shapeRenderer = new ShapeRenderer();
        player = new Player(world);
        player1 = new Player(world, new Vector2(2f, 4f));
        world.setContactListener(new PlayerContactListener(player));
        Gdx.input.setInputProcessor(new PlayerController(player));
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new ScreenViewport(camera);
        debugRenderer = new Box2DDebugRenderer();
        floor = new Floor(world);
        batch = new SpriteBatch();
        hud = new HUD(batch);
    }

    public void resize(int width, int height) {
        // FOR DEBUG
        //MainClass.PIXELSINMETER = 2;
        float aspectRatio = (float) width / (float) height;
        //camera = new OrthographicCamera(10f * aspectRatio, 10f);
        camera = new OrthographicCamera(10f * MainClass.PIXELSINMETER * aspectRatio, 10f * MainClass.PIXELSINMETER);
        //camera.setToOrtho(false, width, height);
        viewport.update(width, height);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(135f / 255, 206f / 255, 235f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // FOR DEBUG
        //debugRenderer.render(world, camera.combined);

        //*
        batch.begin();
        batch.disableBlending();
        batch.draw(AssetHelpers.bg, 0, 0, gameWidth, gameHeight);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        floor.render(shapeRenderer);
        player.render(shapeRenderer);
        player1.render(shapeRenderer);
        shapeRenderer.setColor(Color.BLUE);
        Vector2 pos = player.getBody().getPosition();
        shapeRenderer.circle(pos.x * MainClass.PIXELSINMETER, pos.y * MainClass.PIXELSINMETER, 0.05f * MainClass.PIXELSINMETER);
        shapeRenderer.end();
        //*/
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public void update(float delta) {
        player.update();
        Vector2 pos = player.getBody().getPosition();
        camera.position.x = pos.x;
        camera.position.x = pos.x * MainClass.PIXELSINMETER;
        camera.position.y = pos.y;
        //camera.position.y = pos.y + 3f;
        camera.update();
        world.step(delta, 10, 10);
    }

}
