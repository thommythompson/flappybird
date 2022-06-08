package com.han.flappybird.Entities;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.han.flappybird.FlappyBird;

/**
* @version 1
* @author Thomas Hofman
* De tube set is verantwoordelijk voor het beheren van onder andere de positie van twee buizen die zich op dezelfde x positie begeven.
*/
public class TubeSet {
    private static final float TUBE_GAP = 100;
    private static final float TOPTUBE_OFFSET = 20;
    private static final float TOPTUBE_OFFSET_BOTTOM = Base.MEASUREMENTS.y + TOPTUBE_OFFSET + TUBE_GAP;
    private static final float TOPTUBE_FLUCATUATION = FlappyBird.CAM_HEIGHT - TOPTUBE_OFFSET_BOTTOM - TOPTUBE_OFFSET;
    private static Array<TubeSet> objects = new Array<TubeSet>();

    private float yOffset;
    private boolean passedByBird;
    private Array<Tube> tubes;

    public TubeSet(float xPos){
        objects.add(this);
        tubes = new Array<Tube>();
        passedByBird = false;

        updateYOffset();
        tubes.add(new Tube(xPos, yOffset, 0, 0, TubeType.TopTube));
        tubes.add(new Tube(xPos, yOffset - Tube.MEASUREMENTS.y - TUBE_GAP, 0, 0, TubeType.BottomTube));
    }

    /**
     * Deze methode herpositioneert de buizen set achteraan in de rij,et het attribuut passedByBird op false en bepaald opniew die positie van de opening tussen de buizen.
     */
    public void resetPosition(){
        updateYOffset();
        tubes.get(0).setPosition(new Vector2(tubes.get(0).getPosition().x + (World.TUBE_COUNT * World.TUBE_SPACING), yOffset));
        tubes.get(1).setPosition(new Vector2(tubes.get(1).getPosition().x + (World.TUBE_COUNT * World.TUBE_SPACING), yOffset - Tube.MEASUREMENTS.y - TUBE_GAP));
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
     * @Return Array<TubeSet>
     * Geeft een array terug gevuld met referenties naar alle geinstantieerde TubeSets.
     */
    public static Array<TubeSet> getObjects(){
        return objects;
    }

    /**
     * Gooi de array die alle TubeSet referenties bijhoud leeg.
     */
    public static void disposeObjects(){
        objects.clear();
    }

    /**
     * Deze methode update het yOffset atribuut, deze zal de positie waarop de opening tussen de buizen zich bevind bepalen.
     */
    private void updateYOffset(){
        Random random = new Random();
        this.yOffset = TOPTUBE_OFFSET_BOTTOM + random.nextInt((int)TOPTUBE_FLUCATUATION) ;
    }
}
