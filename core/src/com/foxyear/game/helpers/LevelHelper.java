package com.foxyear.game.helpers;



import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.foxyear.game.models.Coords;
import com.foxyear.game.objects.GameObject;
import com.foxyear.game.objects.Stone;
import com.sun.corba.se.spi.transport.CorbaAcceptor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
    //TODO написать нормальную обработку исключений
    public static JSONObject LoadLevelFromJSONFile(String path){
        JSONParser  parser = new JSONParser();
        JSONObject result = new JSONObject();
        try {
            result = (JSONObject)parser.parse(new FileReader(path));
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
        return result;
    }

    public static LinkedList<GameObject> BuildObjectsOnMapFromJSONObject(JSONObject jsonObject, World world){
        LinkedList<GameObject> levelObjects = new LinkedList<GameObject>();
        ArrayList<JSONObject> JSONlevelobjects =new ArrayList<JSONObject>((Collection<? extends JSONObject>) jsonObject.get("objects"));
        for (JSONObject levelobject : JSONlevelobjects) {
            String Type ;
            Type = (String) levelobject.get("type");
            JSONArray instanceArray = (JSONArray) levelobject.get("instances");
            Iterator<JSONObject> iterator = instanceArray.iterator();
            while(iterator.hasNext()){
                JSONObject currentobj = iterator.next();
                JSONObject currentCoords = (JSONObject) currentobj.get("Coords");
                float x = Float.parseFloat((String) currentCoords.get("x"));
                float y = Float.parseFloat((String) currentCoords.get("y"));
                String str =(String) currentobj.get("scale");

                factoryObjectsBuilder(Type,x,y,Float.parseFloat(str),levelObjects,world);

            }
            //TODO обсудить эти параметры со всеми

        }
        return levelObjects;
    }
    private static GameObject factoryObjectsBuilder(String Type,float x,float y,float Scale, LinkedList<GameObject> levelObjects, World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        if(Type.equals("stone")){
            levelObjects.add(new Stone(world,bodyDef,Scale));
        }
        System.err.println("Не существует такого типа объектов");
        return null;

    }
}
