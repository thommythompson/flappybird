package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tube {
    public static final Vector2 MEASUREMENTS = new Vector2(50, 300);

    private Texture texture;
    private TubeType type;
    private Vector2 position;
    private Rectangle bounds;

    Tube(float xPos, float yPos, TubeType type){
        this.position = new Vector2(xPos, yPos);
        this.type = type;

        switch(type){
            case TopTube:
                texture = new Texture("sprites/tube-top.png");
                break;
            case BottomTube:
                texture = new Texture("sprites/tube-bottom.png");
                break;
        }

        bounds = new Rectangle(xPos, yPos, MEASUREMENTS.x, MEASUREMENTS.y);
    }

    public void setPosition(Vector2 position){
        this.position = position;
        this.bounds.setPosition(position);
    }

    public void addPosition(Vector2 position){
        setPosition(new Vector2(this.position.x + position.x, this.position.y + position.y));
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, getPosition().x, getPosition().y);
    }

    public Vector2 getPosition(){
        return position;
    }

    public boolean colissionDetected(Rectangle bounds){
        if(this.bounds.overlaps(bounds)) return true;
        return false;
    }
}
