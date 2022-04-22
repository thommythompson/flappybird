package com.han.flappybird.Entities;

import com.badlogic.gdx.utils.Array;

public abstract class WorldObstacle extends WorldObject{

    private static Array<WorldObject> objects = new Array<WorldObject>();

    WorldObstacle(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height);
        objects.add(this);
    }

    public static void disposeObjects(){
        for(WorldObject worldObject : WorldObstacle.getObjects()) worldObject.disposeTexture();
        objects.clear();
    }

    public static Array<WorldObject> getObjects(){
        return objects;
    }
}
