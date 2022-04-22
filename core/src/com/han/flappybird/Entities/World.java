package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.han.flappybird.FlappyBird;

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

    public void update(float delta, boolean jumpBird){
        
        if(jumpBird) bird.jump();
        bird.update(delta);
        
        for(WorldObject worldObject : WorldObstacle.getObjects()) worldObject.update(delta);

        for(TubeSet tubeSet : TubeSet.getObjects()){
            if(tubeSet.isPassedByBird(bird.getPosition())) score.upScore();
            if(tubeSet.isOffScreen()) tubeSet.resetPosition();
        }
    }

    public void draw(SpriteBatch batch){
        for(WorldObject worldObject : WorldObject.getObjects()) worldObject.draw(batch);
        score.draw(batch);
    }

    public boolean colissionDetected(){
        for(WorldObject worldObject : WorldObstacle.getObjects()) if(worldObject.isCollision(bird.bounds))
        {
            return true;
        }
        return false;
    }

    public void dispose(){
        WorldObject.disposeObjects();
        WorldObstacle.disposeObjects();
        TubeSet.disposeObjects();
    }
}
