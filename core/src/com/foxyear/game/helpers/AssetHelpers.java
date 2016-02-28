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

    public static TextureRegion player;

    public static void load() {
        texture = new Texture("date/texture.png");
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0,0,75,40);
        bg.flip(false, false);

        player = new TextureRegion(texture, 80, 0, 15, 40);
        player.flip(false, false);
    }

    public static void dispose(){
        texture.dispose();
    }
}
