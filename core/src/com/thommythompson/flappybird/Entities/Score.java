package com.thommythompson.flappybird.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse is verantwoordelijk voor het bijhouden van de score.
*/
public class Score{
    public static final int SCORE_WIDTH = 30;
    private Vector2 position;
    private int score;
    
    public Score(float xPos, float yPos){
        position = new Vector2(xPos, yPos); 
    }

    /**
     * Het aanroepen van deze methode hoogt de score met een punt op.
     */
    public void upScore(){
        score++;
    }


    /**
     * Geeft de waarde van het score attribuut terug.
     * @return int
     */
    public int getScore(){
        return score;
    }

    /**
     * @param batch - De spritebatch
     * Print de score op het scherm middels de fontgenerator.
     */
    public void draw(SpriteBatch batch){
    }    

    /**
     * verwijderd de font generator uit het geheugen.
     */
    public void dispose(){
    }
}