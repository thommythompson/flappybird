package com.han.flappybird.Entities;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.han.flappybird.FlappyBird;

public class TubeSet {
    private static final float TUBE_GAP = 100;
    private static final float TOPTUBE_OFFSET = 20;
    private static final float TOPTUBE_OFFSET_BOTTOM = Base.MEASUREMENTS.y + TOPTUBE_OFFSET + TUBE_GAP;
    private static final float TOPTUBE_FLUCATUATION = FlappyBird.CAM_HEIGHT - TOPTUBE_OFFSET_BOTTOM - TOPTUBE_OFFSET;
    public static Array<TubeSet> objects = new Array<TubeSet>();

    private float yOffset;
    public boolean passedByBird;
    public Array<Tube> tubes;

    public TubeSet(float xPos){
        objects.add(this);
        tubes = new Array<Tube>();
        passedByBird = false;

        updateYOffset();
        tubes.add(new Tube(xPos, yOffset, 0, 0, TubeType.TopTube));
        tubes.add(new Tube(xPos, yOffset - Tube.MEASUREMENTS.y - TUBE_GAP, 0, 0, TubeType.BottomTube));
    }

    public void resetPosition(){
        updateYOffset();
        tubes.get(0).setPosition(new Vector2(tubes.get(0).getPosition().x + (World.TUBE_COUNT * World.TUBE_SPACING), yOffset));
        tubes.get(1).setPosition(new Vector2(tubes.get(1).getPosition().x + (World.TUBE_COUNT * World.TUBE_SPACING), yOffset - Tube.MEASUREMENTS.y - TUBE_GAP));
        passedByBird = false;
    }

    public boolean isOffScreen(){
        return tubes.get(0).isOffScreen();
    }

    public boolean isPassedByBird(Vector2 birdPosition){
        if((birdPosition.x + Bird.MEASUREMENTS.x) >= (tubes.get(0).getPosition().x + Tube.MEASUREMENTS.x) && !passedByBird){
            passedByBird = true;
            return true;
        }
        else return false;
    }

    public static Array<TubeSet> getObjects(){
        return objects;
    }

    public static void disposeObjects(){
        objects.clear();
    }

    private void updateYOffset(){
        Random random = new Random();
        this.yOffset = TOPTUBE_OFFSET_BOTTOM + random.nextInt((int)TOPTUBE_FLUCATUATION) ;
    }
}
