package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse is verantwoordelijk voor het bijhouden van de score.
*/
public class Score{
    public static final int SCORE_WIDTH = 30;
    private FlappyBirdFont font;
    private Vector2 position;
    private int score;
    
    public Score(float xPos, float yPos){
        position = new Vector2(xPos, yPos);
        font = new FlappyBirdFont(FlappyBirdFontSize.Medium);  
    }

    /**
     * Het aanroepen van deze methode hoogt de score met een punt op.
     */
    public void upScore(){
        score++;
    }

    public int getScore(){
        return score;
    }

    public void draw(SpriteBatch batch){
        // font.draw(batch, Integer.toString(score), position.x, position.y);
        font.draw(batch, Integer.toString(score), position.y);
    }    
}