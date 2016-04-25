package com.foxyear.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.foxyear.game.objects.GameObject;
import com.foxyear.game.helpers.graphics.AssetsLoader;
import com.foxyear.game.helpers.graphics.TexturePaths;
import com.foxyear.game.objects.Player;
import com.foxyear.game.scene.HUD;

public class GameRenderer {

    private OrthographicCamera camera;
    public HUD hud;
    private SpriteBatch batch;
    private Viewport viewport;
    //    private ShapeRenderer shapeRenderer;
    private GameWorld world;
    private Player player;
    public int gameHeight;
    public int gameWidth;
    Texture background;

    public GameRenderer(GameWorld world) {
        this.world = world;
        this.camera = new OrthographicCamera();
//        shapeRenderer = new ShapeRenderer();
        this.player = world.getPlayer();
        camera.setToOrtho(false);
        viewport = new ScreenViewport(camera);
        batch = new SpriteBatch();
        hud = new HUD(batch);
        gameHeight = Gdx.graphics.getHeight();
        gameWidth = Gdx.graphics.getWidth();
        background = AssetsLoader.getInstance().getTexture(TexturePaths.BACKGROUND);
    }

    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        camera = new OrthographicCamera(5f * YearOfTheFoxGame.PPM * aspectRatio, 5f * YearOfTheFoxGame.PPM);
        viewport.update(width, height);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(135f / 255, 206f / 255, 235f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // FOR DEBUG
        //debugRenderer.render(world, camera.combined);
//        shapeRenderer.setProjectionMatrix(camera.combined);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        //world.floor.render(shapeRenderer);
//        shapeRenderer.end();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.disableBlending();

        batch.draw(background, 0, 0, 960, 600);
        batch.draw(background, -960, 0, 960, 600);
        batch.draw(background, 960, 0, 960, 600);
        batch.enableBlending();
        for (GameObject gameobj : world.LevelObjects) {
            gameobj.draw(batch);
        }
        world.getPlayer().draw(batch);


      //  batch.draw(AssetHelpers.enem, world.enemy.getX() * YearOfTheFoxGame.PPM - 50, world.enemy.getY() * YearOfTheFoxGame.PPM - 50, 100, 100);
        //pos = world.test.getBody().getPosition();
        //world.test.draw(batch);
        //batch.draw(world.test.getTexture(), pos.x * YearOfTheFoxGame.PPM, pos.y * YearOfTheFoxGame.PPM);
        batch.end();
//        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        Vector2 pos = player.getBody().getPosition();
        camera.position.x = pos.x * YearOfTheFoxGame.PPM;
        camera.position.y = pos.y + 3f * YearOfTheFoxGame.PPM;
        camera.update();
    }
}
