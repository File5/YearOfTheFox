package com.foxyear.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.foxyear.game.helpers.AssetHelpers;
import com.foxyear.game.objects.Player;
import com.foxyear.game.scene.HUD;

/**
 * Created by Xoul on 28.02.2016.
 */
public class GameRenderer {
    private OrthographicCamera camera;
    public HUD hud;
    private SpriteBatch batch;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private GameWorld world;
    private Player player;
    public int gameHeight;
    public int gameWidth;

    public GameRenderer(GameWorld world) {
        this.world = world;
        this.camera = new OrthographicCamera();
        shapeRenderer = new ShapeRenderer();
        this.player = world.getPlayer();
        camera.setToOrtho(false);
        viewport = new ScreenViewport(camera);
        batch = new SpriteBatch();
        hud = new HUD(batch);
        gameHeight = Gdx.graphics.getHeight();
        gameWidth = Gdx.graphics.getWidth();
    }

    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        camera = new OrthographicCamera(10f * YearOfTheFoxGame.PIXELSINMETER * aspectRatio, 10f * YearOfTheFoxGame.PIXELSINMETER);
        viewport.update(width, height);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(135f / 255, 206f / 255, 235f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // FOR DEBUG
        //debugRenderer.render(world, camera.combined);

        //*


        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        world.floor.render(shapeRenderer);
        Vector2 pos;
        shapeRenderer.end();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.enableBlending();
        //TODO: fix this
        batch.draw(AssetHelpers.playertex, player.getBody().getPosition().x * YearOfTheFoxGame.PIXELSINMETER - AssetHelpers.playertex.getRegionWidth() / 2
                , player.getBody().getPosition().y * YearOfTheFoxGame.PIXELSINMETER - AssetHelpers.playertex.getRegionHeight() / 2);
        batch.end();
        //*/
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        pos = player.getBody().getPosition();
       // camera.position.x = pos.x;
        camera.position.x = pos.x * YearOfTheFoxGame.PIXELSINMETER;
       // camera.position.y = pos.y;
        camera.position.y = pos.y + 3f * YearOfTheFoxGame.PIXELSINMETER;
        camera.update();
    }
}
