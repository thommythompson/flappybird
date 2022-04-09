package com.han.flappybird.Entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.han.flappybird.FlappyBird;

public class TubeSet {
    private static final float TUBE_GAP = 100;
    private static final float TOPTUBE_OFFSET = 20;
    private static final float TOPTUBE_OFFSET_BOTTOM = Base.MEASUREMENTS.y + TOPTUBE_OFFSET + TUBE_GAP;
    private static final float TOPTUBE_FLUCATUATION = FlappyBird.CAM_HEIGHT - TOPTUBE_OFFSET_BOTTOM - TOPTUBE_OFFSET;
    public boolean passedByBird;
    
    private float yPosOffset;
    public Array<Tube> tubes;

    public TubeSet(float xPos){
        updateyPosOffset();

        tubes = new Array<Tube>();
        tubes.add(new Tube(xPos, yPosOffset, TubeType.TopTube));
        tubes.add(new Tube(xPos, yPosOffset - Tube.MEASUREMENTS.y - TUBE_GAP, TubeType.BottomTube));

        passedByBird = false;
    }

    private void updateyPosOffset(){
        Random random = new Random();
        this.yPosOffset = TOPTUBE_OFFSET_BOTTOM + random.nextInt((int)TOPTUBE_FLUCATUATION) ;
    }

    public void draw(SpriteBatch batch){
        for(Tube tube : tubes) tube.draw(batch);
    }

    public void addPosition(Vector2 position){
        for(Tube tube : tubes) tube.addPosition(new Vector2(position.x, position.y));
    }

    private void setPosition(Vector2 position){
        tubes.get(0).setPosition(position);
        tubes.get(1).setPosition(new Vector2(position.x, yPosOffset - Tube.MEASUREMENTS.y - TUBE_GAP));
    }

    public boolean isOffscreen(){
        if(tubes.get(0).getPosition().x <= -Tube.MEASUREMENTS.x) return true;
        return false;
    }

    public void resetPosition(){
        updateyPosOffset();
        setPosition(new Vector2(tubes.get(0).getPosition().x + (World.TUBE_COUNT * World.TUBE_SPACING), yPosOffset));

        passedByBird = false;
    }

    public boolean colissionDetected(Rectangle bounds){
        for(Tube tube : tubes) if(tube.colissionDetected(bounds)) return true;
        return false;
    }

    public boolean birdPastTube(Vector2 birdPosition){
        if((birdPosition.x + Bird.MEASUREMENTS.x) >= (tubes.get(0).getPosition().x + Tube.MEASUREMENTS.x) && !passedByBird){
            passedByBird = true;
            return true;
        }
        return false;
    }
}
