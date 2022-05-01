package com.han.flappybird.Entities;

import com.badlogic.gdx.utils.Array;

/**
* @version 1
* This class extends the worldobject array.

* It's used to create objects and has no addition value but tracking the obstacles (all objects except the bird).
*/

public abstract class WorldObstacle extends WorldObject{
    private static Array<WorldObstacle> objects = new Array<WorldObstacle>();

    WorldObstacle(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height);
        objects.add(this);
    }

    public static void disposeObjects(){
        for(WorldObject worldObject : WorldObstacle.getObjects()) worldObject.disposeTexture();
        objects.clear();
    }

    public static Array<WorldObstacle> getObstacles(){
        return objects;
    }
}
