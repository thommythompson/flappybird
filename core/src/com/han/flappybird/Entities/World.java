package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.han.flappybird.FlappyBird;

/**
* @version 1
* @author Thomas Hofman
* De spelwereld is verantwoordelijk voor het initieren en beheren van alle spelobjecten.
*/
public class World {
    public static final float WORLD_SPEED = 100;
    public static final float TUBE_COUNT = 4;
    public static final float TUBE_SPACING = 120 + Tube.MEASUREMENTS.x;
    private static final int BIRD_LEFT_OFFSET = 60;

    private Bird bird;
    private Score score;
    
    public World(){
        for(int i = 2; i <= (TUBE_COUNT + 1); i++) new TubeSet(TUBE_SPACING * i);
        for(int i = 0; i < 2; i++) new Base(i * FlappyBird.CAM_WIDTH, 0, 0, 0);
        for(int i = 0; i < 2; i++) new Base(i * FlappyBird.CAM_WIDTH, FlappyBird.CAM_HEIGHT, 0, 0); // used as roof collision object, not visible 
        score = new Score((FlappyBird.CAM_WIDTH / 2) - (Score.SCORE_WIDTH / 2), FlappyBird.CAM_HEIGHT / 4 * 3);
        bird = new Bird(BIRD_LEFT_OFFSET, 0 + (FlappyBird.CAM_HEIGHT / 3 * 2), 0, 0);
    }

    /**
     * @param float delta
     * @param boolean jumpBird
     * De update methode is verantwoordelijk voor het veranderen van de staat van de spelwereld. Aan de hand van de delta time en of er gebruikersinvoer gedetecteerd is zal deze de objecten in de spel wereld triggeren om zich te herpositioneren.
     */
    public void update(float delta, boolean jumpBird){
        
        if(jumpBird) bird.jump();
        bird.update(delta);

        // Polymorfisme van obstacle > object
        for(WorldObject worldObject : WorldObstacle.getObstacles()) worldObject.update(delta);

        for(TubeSet tubeSet : TubeSet.getObjects()){
            if(tubeSet.isPassedByBird(bird.getPosition())) score.upScore();
            if(tubeSet.isOffScreen()) tubeSet.resetPosition();
        }
    }

    /**
     * @param SpriteBatch batch
     * De draw methode loopt door alle instanties van de WorldObject klasse en tekent deze op het scherm door de draw methode van de WorldObject klasse aan te roepen. Als laatste tekent deze ook de score op het scherm.
     */
    public void draw(SpriteBatch batch){
        for(WorldObject worldObject : WorldObject.getObjects()) worldObject.draw(batch);
        score.draw(batch);
    }

    /**
     * @return boolean
     * De collisionDetected geeft de waarde true terug wanneer het vogel object overlapt met een van de instanties van het WorldObstacle object.
     * Polymorfie: De getObstacles methode van de WorldObstacle klasse geeft een array terug gevuld met WorldObstacles, de isColission methode is onderdeel van de WorldObject klasse. Om deze te kunnen gebruiken met het object terug gecast worden naar een WorldObject.
     */
    public boolean colissionDetected(){

        // Polymorfisme van WorldObstacle > WorldObject
        for(WorldObject worldObject : WorldObstacle.getObstacles()) if(worldObject.isCollision(bird.bounds))
        {
            return true;
        }
        return false;
    }

    /**
     * Deze methode triggert alle door de wereld geinstantieerde klassen om hun textures uit het geheugen te verwijderen en array's die referenties bijhouden naar instanties van het eigen type leeg te gooien.
     */
    public void dispose(){
        WorldObject.disposeObjects();
        WorldObstacle.disposeObjects();
        TubeSet.disposeObjects();
    }
}
