package com.han.flappybird.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.han.flappybird.Configuration;

public class FlappyBirdFont {
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont font;
    
    public FlappyBirdFont(FlappyBirdFontSize size){

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.color = Color.WHITE;
        setFontSize(size);
        font = generator.generateFont(parameter);
    }
    
    /**
     * @param SpriteBatch batch
     * Deze methode zal score op het scherm tekenen.
     */
    public void draw(SpriteBatch batch, String value, float xPos, float yPos){
        font.draw(batch, "" +  value, xPos, yPos);
    }
    public void draw(SpriteBatch batch, String value, float yPos){
        font.draw(batch, "" +  value, 0, yPos, Configuration.PROJECTION_WIDTH, 1, true);
    }

    private void setFontSize(FlappyBirdFontSize size){
        switch(size){
            case Small: 
                parameter.size = 20;
                break;
            case Medium:
                parameter.size = 30;
                break;
            case Large: 
                parameter.size = 40;
                break;
        }
        font = generator.generateFont(parameter);
    }
    /**
     * Deze methode zal het ingeladen font uit het geheugen verwijderen.
     */
    public void dispose(){
        generator.dispose();
    }
}
