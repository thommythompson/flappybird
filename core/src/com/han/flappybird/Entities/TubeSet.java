package com.han.flappybird.Entities;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.han.flappybird.Configuration;

/**
* @version 1
* @author Thomas Hofman
* De tube set is verantwoordelijk voor het beheren van onder andere de positie van twee buizen die zich op dezelfde x positie begeven.
*/
public class TubeSet {
    private static final float TUBE_GAP_SIZE = 100; // Grote van het gat tussen de buizen
    private static final float MINIMAL_TUBE_EXPOSURE = 20; // Minimaal gedeelte van de tube dat zichtbaar blijft
    private static final float TOPTUBE_YPOS_MINIMUM = Ground.MEASUREMENTS.y + MINIMAL_TUBE_EXPOSURE + TUBE_GAP_SIZE; // De minimale y positie waarop de toptube geplaatst kan worden
    private static final float TOPTUBE_YPOS_MAXIMUM = TOPTUBE_YPOS_MINIMUM + (Configuration.PROJECTION_HEIGHT - TOPTUBE_YPOS_MINIMUM - MINIMAL_TUBE_EXPOSURE - 10); 
    private static Array<TubeSet> instances = new Array<TubeSet>();

    private float topTubeYPos;
    private boolean passedByBird;
    private Array<Tube> tubes;

    public TubeSet(float xPos){
        instances.add(this);
        tubes = new Array<Tube>();
        passedByBird = false;

        updateTopTubeYPos();
        tubes.add(new Tube(xPos, topTubeYPos, TubeType.TopTube));
        tubes.add(new Tube(xPos, topTubeYPos - Tube.MEASUREMENTS.y - TUBE_GAP_SIZE, TubeType.BottomTube));
    }

    /**
     * Deze methode herpositioneert de buizen set achteraan in de rij en zet het attribuut passedByBird op false en bepaald opnieuw die positie van de opening tussen de buizen.
     */
    public void resetTubeSet(){

        updateTopTubeYPos();

        // Plaatst de top tube
        tubes.get(0).setPosition(
            new Vector2(
                tubes.get(0).getPosition().x + (GameWorld.TUBE_SET_COUNT * GameWorld.SPACE_BETWEEN_TUBES), 
                topTubeYPos
            )
        );

        // Plaatst de bottom tube op basis van de positie van de top tube
        tubes.get(1).setPosition(
            new Vector2(
                tubes.get(1).getPosition().x + (GameWorld.TUBE_SET_COUNT * GameWorld.SPACE_BETWEEN_TUBES), 
                topTubeYPos - Tube.MEASUREMENTS.y - TUBE_GAP_SIZE
            )
        );

        passedByBird = false;
    }

    /**
     * @return boolean
     * Deze methode geeft de waarde true terug indien de buizen set zich links buiten het scherm begeeft.
     */
    public boolean isOffScreen(){
        return tubes.get(0).isOffScreen();
    }

    /**
     * @param Vector2 birdPosition
     * @return boolean
     * Deze methode geeft eenmalig de waarde true terug wanneer deze door de als parameter meegegeven coordinaten gepasserd is. Deze methode word gebruikt om de score op te hogen wanneer de vogel een buis succesvol passeert.
     */
    public boolean isPassedByBird(Vector2 birdPosition){
        if((birdPosition.x + Bird.MEASUREMENTS.x) >= (tubes.get(0).getPosition().x + Tube.MEASUREMENTS.x) && !passedByBird){
            passedByBird = true;
            return true;
        }
        else return false;
    }

    /**
     * Deze methode update het yOffset atribuut, deze zal de positie waarop de opening tussen de buizen zich bevind bepalen.
     */
    private void updateTopTubeYPos(){
        this.topTubeYPos = ThreadLocalRandom.current().nextInt((int)TOPTUBE_YPOS_MINIMUM, (int)TOPTUBE_YPOS_MAXIMUM);
    }

    /**
     * @Return Array<TubeSet>
     * Geeft een array terug gevuld met referenties naar alle geinstantieerde TubeSets.
     */
    public static Array<TubeSet> getAllInstances(){
        return instances;
    }

    /**
     * Gooit de array die alle TubeSet referenties bijhoud leeg.
     */
    public static void disposeAllInstances(){
        instances.clear();
    }
}
