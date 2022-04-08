package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Object {
    private Texture texture;
    private ObjectType type;
    private Vector2 position;
    private Vector2 measurements;
    private Rectangle bounds;

    Object(int posX, int posY, int width, int height, ObjectType type){
        this.position = new Vector2(posX, posY);
        this.measurements = new Vector2(width, height);
        this.type = type;

        switch(type){
            case TopTube:
                texture = new Texture("sprites/tube-top.png");
                break;
            case BottomTube:
                texture = new Texture("sprites/tube-bottom.png");
                break;
            case Base:
                texture = new Texture("sprites/base.png");
                break;
        }
    }

    public void setPosition(Vector2 position){
        this.position = position;
    }

    public ObjectType getType(){
        return type;
    }

    public Vector2 getPosition(){
        return position;
    }

    public Vector2 getMeasurements(){
        return measurements;
    }

    public Texture getTexture(){
        return texture;
    }

    public Rectangle getBounds(){
        return bounds;
    }
}