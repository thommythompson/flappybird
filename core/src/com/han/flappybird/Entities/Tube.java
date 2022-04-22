package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Tube extends WorldObstacle{

    public static final Vector2 MEASUREMENTS = new Vector2(50, 300);

    Tube(float xPos, float yPos, float width, float height, TubeType type){
        super(xPos, yPos, MEASUREMENTS.x, MEASUREMENTS.y);

        switch(type){
            case TopTube:
                texture = new Texture("sprites/tube-top.png");
                break;
            case BottomTube:
                texture = new Texture("sprites/tube-bottom.png");
                break;
        }
    }
}
