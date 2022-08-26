package com.thommythompson.flappybird.Entities;

import com.badlogic.gdx.utils.Array;

/**
* @version 1
* @author Thomas Hofman
* Deze abstracte klasse extend de WorldObject klasse. 
* Het voornaamste doel van deze klasse is het bijhouden van referenties naar alle obstakels die er binnen de applicatie aangemaakt worden zodat deze in bulk aangeroepen kunnen worden.
*/
public abstract class GameWorldObstacle extends GameWorldObject{
    private static Array<GameWorldObject> instances = new Array<GameWorldObject>();

    GameWorldObstacle(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height);
        instances.add(this);
    }

    /**
     * De WorldObject klasse heeft zijn eigen implementatie van de update methode, alle obstakels moeten namelijk van rechts naar links door het scherm bewogen worden.
     * @param delta - De tijd verstreken vanaf de vorige aanroep in seconden
     * @param timeElapsed - De tijd verstreken binnen de spel sessie in seconden
     * @param worldSpeed - De huidige snelheid van de wereld
     */
    @Override
    public void update(float delta, float timeElapsed, float worldSpeed) {
        moveToTheLeft(delta, worldSpeed);
    }

    /**
     * Geeft een array terug gevuld met referenties naar alle geinstantieerde WorldObstacles.
     * @return {Array&lt;GameWorldObject&gt;}
     */
    public static Array<GameWorldObject> getAllInstances(){
        return instances;
    }

    /**
     * Verwijderd alle Textures die ingeladen zijn door instanties van het WorldObstacle type uit het geheugen en leegt vervolgens de statisch array waarin het referenties naar instanties van het eigen type bijhoud.
     */
    public static void disposeAllInstances(){
        for(GameWorldObject worldObject : GameWorldObstacle. getAllInstances()) worldObject.dispose();
        instances.clear();
    }
}
