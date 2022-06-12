package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.han.flappybird.Configuration;

/**
* @version 1
* Deze klasse representeert het grond object.
*/
public class Ground extends GameWorldObstacle{
    public static final Vector2 MEASUREMENTS = new Vector2(Configuration.PROJECTION_WIDTH, Configuration.PROJECTION_WIDTH / 4);

    Ground(float xPos, float yPos){
        super(xPos, yPos, MEASUREMENTS.x, MEASUREMENTS.y);
        texture = new Texture("sprites/base.png");
    }

    /**
     * De update methode is overschreven zodat hier funtionaliteit aan toegevoegd kan worden. In ground object is namelijk in staat zijn positie zelf te resetten. 
     */
    @Override
    public void update(float delta, float timeElapsed, float worldSpeed) {
        super.update(delta, timeElapsed, worldSpeed);
        if(isOffScreen()) resetPosition();
    }

    /**
     * Deze methode is verantwoordelijk voor het daadwerkelijk resetten van de positie van het grond object, na het aanroepen van de methode zal het grond object zich rechts buiten het scherm begeven.
     */
    private void resetPosition(){
        Vector2 newPosition = new Vector2(getPosition().x + (bounds.width * 2), getPosition().y);
        setPosition(newPosition);
    }
}