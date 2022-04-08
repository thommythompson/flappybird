package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.han.flappybird.Screens.PlayScreen;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class World {
    private static final int WORLD_SPEED = 100;
    private static final int GROUND_WIDTH = PlayScreen.CAM_WIDTH;
    public static final int GROUND_HEIGHT = PlayScreen.CAM_WIDTH / 4;
    private static final int TUBE_COUNT = 4;
    private static final int TUBE_SPACING = 140;
    private static final int TUBE_HEIGT = 300;
    private static final int TUBE_WIDTH = 50;
    private static final int TUBE_FLUCATUATION = 200 - GROUND_HEIGHT;
    private static final int TUBE_GAP = 80;
    private static final int TUBE_X_MIN = 0 - (PlayScreen.CAM_HEIGHT / 2) + GROUND_HEIGHT - TUBE_HEIGT + 50;

    private Array<Object> tubeObjects;
    private Array<Object> groundObjects;

    public World(){
        tubeObjects = new Array<Object>();
        groundObjects = new Array<Object>();

        for(int i = 1; i <= TUBE_COUNT; i++){

            Random random = new Random();
            int randomAdditionalBaseHeight = random.nextInt(TUBE_FLUCATUATION);

            tubeObjects.add(new Object(
                (TUBE_SPACING * i) - PlayScreen.CAM_WIDTH, TUBE_X_MIN + randomAdditionalBaseHeight, // position
                TUBE_WIDTH, TUBE_HEIGT, // measurements
                ObjectType.BottomTube
            ));
            tubeObjects.add(new Object(
                (TUBE_SPACING * i) - PlayScreen.CAM_WIDTH, TUBE_X_MIN + TUBE_GAP + randomAdditionalBaseHeight + TUBE_HEIGT, // position
                TUBE_WIDTH, TUBE_HEIGT, // measurements
                ObjectType.TopTube
            ));
        }

        for(int i = 0; i < 2; i++){
            groundObjects.add(new Object(
                (GROUND_WIDTH * i) - PlayScreen.CAM_WIDTH, 0 - (PlayScreen.CAM_HEIGHT / 2),
                GROUND_WIDTH, GROUND_HEIGHT,
                ObjectType.Base
            ));
        }
    }

    public void updateWorld(float delta){

        for(int i = 0; i < tubeObjects.size; i++){
            Object object = tubeObjects.get(i);

            object.setPosition(new Vector2(object.getPosition().x - (WORLD_SPEED * delta), object.getPosition().y));
            if(object.getPosition().x == (0 - PlayScreen.CAM_WIDTH - TUBE_WIDTH) && object.getType() == ObjectType.BottomTube){
                Object object2 = tubeObjects.get(i + 1);
                
                Random random = new Random();
                int randomAdditionalBaseHeight = random.nextInt(TUBE_FLUCATUATION);

                object.setPosition(new Vector2(object.getPosition().x - (WORLD_SPEED * delta), object.getPosition().y));
                object2.setPosition(new Vector2(object.getPosition().x - (WORLD_SPEED * delta), object.getPosition().y));
            }
        }
        
    }

    public void addTextures(SpriteBatch batch){

        for(Object object : tubeObjects){
            batch.draw(object.getTexture(), object.getPosition().x, object.getPosition().y, object.getMeasurements().x, object.getMeasurements().y);
        }
        for(Object object : groundObjects){
            batch.draw(object.getTexture(), object.getPosition().x, object.getPosition().y, object.getMeasurements().x, object.getMeasurements().y);
        }
    }

    public boolean hasCollided(Rectangle bird){
        return false;
    }

}
