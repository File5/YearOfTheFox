package com.foxyear.game.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

/**
 * Created by MyFriend on 20.04.2016.
 */
public class AssetsLoader implements Disposable {

    private static AssetsLoader instance;

    private HashMap<String, Texture> textureHashMap;
    private Texture texture;


    private AssetsLoader() {
        textureHashMap = new HashMap<String, Texture>();
    }

    public Texture getTexture(String path) {
        if (textureHashMap.containsKey(path)) {
            return textureHashMap.get(path);
        } else {
            texture = new Texture(path);
            textureHashMap.put(path, texture);
            return texture;
        }
    }

    public static AssetsLoader getInstance() {
        if (instance == null) {
            instance = new AssetsLoader();
        }
        return instance;
    }

    @Override
    public void dispose() {
        for (Texture texture : textureHashMap.values()) {
            texture.dispose();
        }
    }
}

