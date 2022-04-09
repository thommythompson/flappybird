package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.han.flappybird.FlappyBird;

public class World {
    public static final float TUBE_COUNT = 4;
    public static final float TUBE_SPACING = 120 + Tube.MEASUREMENTS.x;
    private static final float WORLD_SPEED = 100;

    private Array<TubeSet> tubeSets;
    private Array<Base> baseObjects;
    private Score score;

    public World(){
        tubeSets = new Array<TubeSet>();
        baseObjects = new Array<Base>();
        score = new Score((FlappyBird.CAM_WIDTH / 2) - (Score.SCORE_WIDTH / 2), FlappyBird.CAM_HEIGHT / 4 * 3);

        for(int i = 2; i <= (TUBE_COUNT + 1); i++)
            tubeSets.add(new TubeSet(TUBE_SPACING * i));
        for(int i = 0; i < 2; i++)
            baseObjects.add(new Base(i * FlappyBird.CAM_WIDTH, 0));
    }

    public void updateWorld(float delta, Bird bird){

        for(TubeSet tubeSet : tubeSets){
            tubeSet.addPosition(new Vector2(-(delta * WORLD_SPEED), 0));
            if(tubeSet.isOffscreen()) tubeSet.resetPosition();
            if(tubeSet.birdPastTube(bird.getPosition())) score.upScore();
        }

        for(Base baseObject : baseObjects){
            baseObject.addPosition(new Vector2(-(delta * WORLD_SPEED), 0));
            if(baseObject.isOffscreen()) baseObject.resetPosition();
        }
    }

    public void draw(SpriteBatch batch){
        for(TubeSet tubeSet : tubeSets) tubeSet.draw(batch);
        for(Base baseObject : baseObjects) baseObject.draw(batch);
        score.draw(batch);
    }

    public boolean colissionDetected(Rectangle bounds){

        for(TubeSet tubeSet : tubeSets) if(tubeSet.colissionDetected(bounds)) return true;
        return false;
    }

    public void dispose(){
        // TODO implement custom dispose of textures
    }
}
