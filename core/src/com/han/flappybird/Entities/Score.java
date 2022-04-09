package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Score{
    public static final int SCORE_WIDTH = 30;
    private int score;
    private Vector2 position;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public Score(float xPos, float yPos){

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.color = Color.WHITE;
        parameter.size = SCORE_WIDTH;

        position = new Vector2(xPos, yPos);
    }

    public void upScore(){
        score++;
    }

    public void draw(SpriteBatch batch){
        generator.generateFont(parameter).draw(batch, "" + score, position.x, position.y);
    }
}