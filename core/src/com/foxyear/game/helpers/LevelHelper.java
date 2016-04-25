package com.foxyear.game.helpers;


import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.foxyear.game.YearOfTheFoxGame;
import com.foxyear.game.objects.GameObject;
import com.foxyear.game.objects.StandardEnemy;
import com.foxyear.game.objects.Stone;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
        ArrayList<JSONObject> JSONlevelobjects = new ArrayList<JSONObject>((Collection<? extends JSONObject>) result.get("objects"));
        for (JSONObject levelobject : JSONlevelobjects) {
            String type;
            type = (String) levelobject.get("type");
            JSONArray instanceArray = (JSONArray) levelobject.get("instances");
            Iterator<JSONObject> iterator = instanceArray.iterator();
            while (iterator.hasNext()) {
                JSONObject currentobj = iterator.next();
                JSONObject currentCoords = (JSONObject) currentobj.get("Coords");
                float x = Float.valueOf(Double.valueOf((Double) currentCoords.get("x")).toString());
                float y = Float.valueOf(Double.valueOf((Double) currentCoords.get("y")).toString());
                float scale = Float.valueOf(Double.valueOf((Double) currentobj.get("scale")).toString());
                factoryObjectsBuilder(type, x, y, scale, levelObjects, world);
            }
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

    //serializes all objects on level to JSON file
    public static void saveLevel(LinkedList<GameObject> levelObjects) throws IOException {
        JSONObject object = new JSONObject();
        JSONArray ArrayOfObjectsOnlevel = new JSONArray();
        String currenttype = levelObjects.getFirst().type;
        JSONObject EmptyObject = new JSONObject();
        JSONArray instances = new JSONArray();
        for (GameObject gameobject : levelObjects) {
            if (gameobject.type.equals(currenttype)) {
                if (!EmptyObject.containsKey("type")) {
                    EmptyObject.put("type", gameobject.type);
                    instances.clear();
                }
                JSONObject inst = new JSONObject();
                inst.put("scale", gameobject.getScale());
                JSONObject Coord = new JSONObject();
                Coord.put("x", gameobject.getBody().getPosition().x);
                Coord.put("y", gameobject.getBody().getPosition().y);
                inst.put("Coords", Coord);
                instances.add(inst);
            } else {
                currenttype = gameobject.type;
                EmptyObject.put("instances", instances);
                ArrayOfObjectsOnlevel.add(EmptyObject);
                EmptyObject.clear();
                EmptyObject.put("type", gameobject.type);
                instances.clear();
                JSONObject inst = new JSONObject();
                inst.put("scale", Float.toString(gameobject.getScale()));
                JSONObject Coord = new JSONObject();
                String x = Float.toString(gameobject.getBody().getPosition().x);
                String y = Float.toString(gameobject.getBody().getPosition().y);
                Coord.put("x", x);
                Coord.put("y", y);
                inst.put("Coords", Coord);
                instances.add(inst);
            }
        }
        EmptyObject.put("instances", instances);
        ArrayOfObjectsOnlevel.add(EmptyObject);
        object.put("objects", ArrayOfObjectsOnlevel);
        FileWriter writer = new FileWriter("savedlevel" + YearOfTheFoxGame.SavedNumber + ".json");
        writer.write(object.toJSONString());
        writer.flush();
        writer.close();
        YearOfTheFoxGame.SavedNumber++;
    }
}
