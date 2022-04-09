package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.han.flappybird.FlappyBird;

public class Base {
    public static final Vector2 MEASUREMENTS = new Vector2(FlappyBird.CAM_WIDTH, FlappyBird.CAM_WIDTH / 4);

    private Texture texture;
    private Vector2 position;

    Base(int xPos, int yPos){
        this.position = new Vector2(xPos, yPos);
        texture = new Texture("sprites/base.png");
    }

    public void setPosition(Vector2 position){
        this.position = position;
    }

    public void addPosition(Vector2 position){
        setPosition(new Vector2(this.position.x + position.x, this.position.y + position.y));
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, getPosition().x, getPosition().y, MEASUREMENTS.x, MEASUREMENTS.y);
    }

    public Vector2 getPosition(){
        return position;
    }

    public boolean isOffscreen(){
        if(getPosition().x <= -Base.MEASUREMENTS.x){
            return true;
        }else{
            return false;
        }
    }

    public void resetPosition(){
        Vector2 newPosition = new Vector2(
            getPosition().x + (Base.MEASUREMENTS.x * 2),
            getPosition().y
        );
        setPosition(newPosition);
    }
}