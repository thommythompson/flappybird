package com.han.flappybird.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score{
    private int score;
    private Vector2 position;
    private Font font;
    
    public Score(float xPos, float yPos){
        position = new Vector2(xPos, yPos);
        font = new Font();
    }

    public void upScore(){
        score++;
    }

    public void draw(SpriteBatch batch){
        font.getBitmap().draw(batch, "" + score, position.x, position.y);
    }
}