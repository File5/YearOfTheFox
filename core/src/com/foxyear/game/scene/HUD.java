package com.foxyear.game.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Xoul on 24.02.2016.
 */
public class HUD {
    public Stage stage;
    private Viewport viewPort;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;


    public HUD(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewPort = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewPort,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreLabel).expand().padTop(10);
        table.add(timeLabel).expand().padTop(10);
        table.add().row();
        table.add(levelLabel).expand();
        table.add(countdownLabel).expand();

        stage.addActor(table);

    }

}
