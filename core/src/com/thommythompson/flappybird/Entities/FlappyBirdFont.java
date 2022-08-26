package com.thommythompson.flappybird.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @version 1
 * @author Thomas Hofman
 * Deze klasse representeert de font generator.
 */
public class FlappyBirdFont {
    
    public FlappyBirdFont(FlappyBirdFontSize size){
    }

    /**
     * Deze methode zal de opgegeven value op de opgegeven y-positie weergeven, de text centraliseert zich vanzelf over de x-as.
     * @param batch - De spritebatch
     * @param value - De string waarde die weergegeven dient te worden
     * @param yPos - De y positie waar de waarde weergegeven dient te worden
     */
    public void draw(SpriteBatch batch, String value, float yPos){
    }

    /**
     * bepaald de grootte van het lettertype.
     * @param size - De grote van het lettertype
     */
    
    /**
     * Deze methode zal het ingeladen font uit het geheugen verwijderen.
     */
    public void dispose(){
    }
}
