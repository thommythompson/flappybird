package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.han.flappybird.FlappyBird;

public class Base extends WorldObstacle{
    public static final Vector2 MEASUREMENTS = new Vector2(FlappyBird.CAM_WIDTH, FlappyBird.CAM_WIDTH / 4);

    Base(float xPos, float yPos, float width, float height){
        super(xPos, yPos, MEASUREMENTS.x, MEASUREMENTS.y);
        texture = new Texture("sprites/base.png");
    }

    @Override
    public void update(float delta){
        super.update(delta);
        if(isOffScreen()) resetPosition();
    }

    private void resetPosition(){
        Vector2 newPosition = new Vector2(getPosition().x + (bounds.width * 2), getPosition().y);
        setPosition(newPosition);
    }
}