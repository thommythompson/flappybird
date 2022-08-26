package com.thommythompson.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse bevat alleen een constructor, de contructor bepaald aan de hand van het meegegeven TubeType of de texture van de TopTube of de BottomTube wordt ingeladen. 
*/
public class Tube extends GameWorldObstacle{
    public static final Vector2 MEASUREMENTS = new Vector2(50, 300);

    Tube(float xPos, float yPos, TubeType type){
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
