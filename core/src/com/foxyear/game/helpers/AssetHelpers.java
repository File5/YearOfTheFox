package com.foxyear.game.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.Texture.*;

/**
 * Created by MyFriend on 28.02.2016.
 */
public class AssetHelpers {
    public static Texture texture;
    public static Texture texture1;
    public static TextureRegion bg;
    public static TextureRegion enem;

    public static Texture playertex;

    public static void load() {
        texture = new Texture("date/TextureMap.png");
        texture1 = new Texture("date/texture.png");


        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);


        bg = new TextureRegion(texture, 0,0,75,40);
        bg.flip(false, false);
        enem = new TextureRegion(texture1, 0,0,80,40);
        enem.flip(false, false);

        playertex = new Texture("img/pers1.png");


    }

    public static void dispose(){
        texture.dispose();
    }
}
