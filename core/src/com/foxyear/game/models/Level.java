package com.foxyear.game.models;

import com.foxyear.game.YearOfTheFoxGame;

import java.util.List;



public class Level {
    String title;
    List<LevelObject> levelObjectList;
    Inventory inv[];
    List<Coords> checkpoints;
}
