package com.foxyear.game.helpers;


import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.foxyear.game.objects.GameObject;
import com.foxyear.game.objects.StandardEnemy;
import com.foxyear.game.objects.Stone;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class LevelHelper {
    public static LinkedList<GameObject> loadLevelFromJSONFile(String path, World world) {
        JSONParser parser = new JSONParser();
        JSONObject result = new JSONObject();
        LinkedList<GameObject> levelObjects = new LinkedList<GameObject>();
        try {
            result = (JSONObject) parser.parse(new FileReader(path));
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.print("Ошибка парсинга");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.print("Файл не найден");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
        ArrayList<JSONObject> JSONlevelobjects = new ArrayList<JSONObject>((Collection<? extends JSONObject>) result.get("objects"));
        for (JSONObject levelobject : JSONlevelobjects) {
            String type;
            type = (String) levelobject.get("type");
            JSONArray instanceArray = (JSONArray) levelobject.get("instances");
            Iterator<JSONObject> iterator = instanceArray.iterator();
            while (iterator.hasNext()) {
                JSONObject currentobj = iterator.next();
                JSONObject currentCoords = (JSONObject) currentobj.get("Coords");
                float x = Float.parseFloat((String) currentCoords.get("x"));
                float y = Float.parseFloat((String) currentCoords.get("y"));
                float scale =Float.parseFloat((String) currentobj.get("scale"));
                factoryObjectsBuilder(type, x, y, scale, levelObjects, world);
            }
            //TODO обсудить эти параметры со всеми
        }
        return levelObjects;

    }


    private static void factoryObjectsBuilder(String Type, float x, float y, float Scale, LinkedList<GameObject> levelObjects, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        if (Type.equals("stone")) {
            levelObjects.add(new Stone(world, bodyDef, Scale));
        } else if (Type.equals("StandardEnemy")) {
            levelObjects.add(new StandardEnemy(world, bodyDef, Scale));
        } else {
            System.err.println("Не существует такого типа объектов");
        }

    }
}
