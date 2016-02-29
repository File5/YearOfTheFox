package com.foxyear.game.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.Texture.*;

/**
 * Created by MyFriend on 28.02.2016.
 */
public class AssetHelpers {
    public static Texture texture;
    public static TextureRegion bg;

    public static TextureRegion playertex;

    public static void load() {
        texture = new Texture("date/TextureMap.png");
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0,0,75,40);
        bg.flip(false, false);

        playertex = new TextureRegion(texture, 280, 0, 110, 100);
        playertex.flip(false, false);
    }

    public static void dispose(){
        texture.dispose();
    }
}