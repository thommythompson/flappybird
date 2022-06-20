package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.han.flappybird.Configuration;

/**
* @version 1
* @author Thomas Hofman
* De spelwereld is verantwoordelijk voor het initiëren, beheren en weergeven van alle spelobjecten.
*/
public class GameWorld {
    public static final float TUBE_SET_COUNT = 4;
    public static final float SPACE_BETWEEN_TUBES = 120 + Tube.MEASUREMENTS.x;
    private static final int BIRD_X_POSITION = 60;

    private float worldSpeed = 100;
    private float timeElapsed;
    private Bird bird;
    private Score score;
    
    public GameWorld(){

        // aanmaken van alle tubes
        for(int i = 2; i <= (TUBE_SET_COUNT + 1); i++) new TubeSet(SPACE_BETWEEN_TUBES * i);
        
        // aanmaken van het grond object
        for(int i = 0; i < 2; i++) 
            new Ground(
                i * Configuration.PROJECTION_WIDTH, // xPos
                0 // yPos
            );
        
        // aanmaken van de vogel
        bird = new Bird(
            BIRD_X_POSITION, 
            0 + (Configuration.PROJECTION_HEIGHT / 3 * 2)
        );

        // aanmaken van de score
        score = new Score((Configuration.PROJECTION_WIDTH / 2) - (Score.SCORE_WIDTH / 2), Configuration.PROJECTION_HEIGHT / 4 * 3);
    }

    /**
     * @param delta - De tijd verstreken vanaf de vorige aanroep in seconden
     * @param userInputDetected - True indien gebruikersinvoer gedetecteert is
     * De update methode is verantwoordelijk voor het veranderen van de staat van de spelwereld. Aan de hand van de delta time en of er gebruikersinvoer gedetecteerd is zal deze de objecten in de spel wereld triggeren om zich te herpositioneren.
     */
    public void update(float delta, boolean userInputDetected){

        // houd de vertreken tijd in de huidige spel sessie bij
        timeElapsed = timeElapsed + delta;

        // versnelt het spel naarmaten de tijd vordert.
        accelerateWorld();

        // laat de vogel springen indien de vogel het scherm aangeraakt heeft
        if(userInputDetected) bird.jump();

        // Update de state van alle objecten geinstantieerd de GameWorldObject klasse of overervende klassen die hun eigen implementatie hebben van de update methode (polymorfie).
        for(GameWorldObject worldObject : GameWorldObject.getAllInstances()) worldObject.update(delta, timeElapsed, worldSpeed);

        for(TubeSet tubeSet : TubeSet.getAllInstances()){

            // Indien de vogel de set buizen gepasseerd heeft hogen we de score op.
            if(tubeSet.isPassedByBird(bird.getPosition())) score.upScore();

            // De tubes dienen zich als set de gedragen daarom word de positie niet gereset binnen de individuele tube instantie maar via de set die deze geinstantieert heeft.
            if(tubeSet.isOffScreen()) tubeSet.resetTubeSet();
        }
    }

    /**
     * @param batch - De spritebatch
     * De draw methode loopt door alle instanties van de WorldObject klasse en tekent deze op het scherm door de draw methode van de WorldObject klasse aan te roepen. Als laatste tekent deze ook de score op het scherm.
     */
    public void draw(SpriteBatch batch){
        for(GameWorldObject worldObject : GameWorldObject.getAllInstances()) worldObject.draw(batch);
        score.draw(batch);
    }

    /**
     * @return boolean
     * De collisionDetected geeft de waarde true terug wanneer het vogel object overlapt met een van de instanties van het WorldObstacle object.
     */
    public boolean isCollisionDetected(){

        // Controleer of de bird overlapt met een van de obstakels
        for(GameWorldObject worldObject : GameWorldObstacle.getAllInstances()) if(worldObject.isCollisionDetected(bird.bounds)) return true;
        // Controleer of de bird zich buiten het scherm bevind
        if(bird.getPosition().y >= Configuration.PROJECTION_HEIGHT - Bird.MEASUREMENTS.y) return true;
        // Indien geen van de statement true zijn, return false waarde
        return false;
    }

    /**
     * Geeft de score van de huidige spel sessie terug.
     * @return Score
     */
    public Score getScore(){
        return score;
    }

    /**
     * Geeft de huidige spel snelheid terug.
     * @return float
     */
    public float getWorldSpeed(){
        return worldSpeed;
    }

    /**
     * Verhoogt de snelheid van het spel naarmaten de tijd vordert.
     */
    private void accelerateWorld(){
        if(worldSpeed <= Configuration.MAX_WORLD_SPEED) worldSpeed = worldSpeed + (timeElapsed / 1200);
    }

    /**
     * Deze methode triggert alle door de wereld geinstantieerde klassen om hun textures uit het geheugen te verwijderen en array's die referenties bijhouden naar instanties van het eigen type leeg te gooien.
     */
    public void dispose(){
        GameWorldObject.disposeAllInstances();
        GameWorldObstacle.disposeAllInstances();
        TubeSet.disposeAllInstances();
        score.dispose();
    }
}
