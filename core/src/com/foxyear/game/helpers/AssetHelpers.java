package com.foxyear.game.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.Texture.TextureFilter;

/**
 * Created by MyFriend on 28.02.2016.
 */
public class AssetHelpers {
    public static Texture texture;
    public static Texture playertex;
    public static Texture background;

    public static void load() {
        texture = new Texture("date/TextureMap.png");
        background = new Texture("img/testMap.png");
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        playertex = new Texture("img/pers1.png");
    }

    public static void dispose() {
        texture.dispose();
    }
}
