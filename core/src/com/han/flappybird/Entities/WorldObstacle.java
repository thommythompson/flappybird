package com.han.flappybird.Entities;

import com.badlogic.gdx.utils.Array;

/**
* @version 1
* @author Thomas Hofman
* Deze abstracte klasse extend de WorldObject klasse. 
* Het enigste doel van deze klasse is het bijhouden van referenties naar alle obstakels die er binnen de applicatie aangemaakt worden zodat deze in bulk aangeroepen kunnen worden.
*/
public abstract class WorldObstacle extends WorldObject{
    private static Array<WorldObstacle> objects = new Array<WorldObstacle>();

    WorldObstacle(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height);
        objects.add(this);
    }

    /**
     * Verwijderd alle Textures die ingeladen zijn door instanties van het WorldObject type uit het geheugen en leegt vervolgens de statisch array waarin het referenties naar instanties van het eigen type bijhoud.
     */
    public static void disposeObjects(){
        for(WorldObject worldObject : WorldObstacle.getObjects()) worldObject.disposeTexture();
        objects.clear();
    }

    /**
     * @Return Array<WorldObstacle>
     * Geeft een array terug gevuld met referenties naar alle geinstantieerde WorldObstacles.
     */
    public static Array<WorldObstacle> getObstacles(){
        return objects;
    }
}
