package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.han.flappybird.FlappyBird;
import com.han.flappybird.Entities.FlappyBirdFont;
import com.han.flappybird.Entities.FlappyBirdFontSize;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse extend de FBScreen klasse en representeert het start scherm.
*/
public class StartScreen extends FBScreen {
    private Texture instructionMessageImg;
    private FlappyBirdFont font;

    public StartScreen(FlappyBird game){
        super(game);
    }

    /**
     * Binnen de runOnce methode wordt de benodigde instructie texture en de font generator ingeladen.
     */
    @Override
    public void runOnce() {
        instructionMessageImg = new Texture("sprites/message.png");
        font = new FlappyBirdFont(FlappyBirdFontSize.Small);
    }

    /**
     * @param delta - De tijd verstreken vanaf de vorige aanroep in seconden
     * Binnen de render methode wordt user input afgevangen, indien deze gedetecteert wordt over wordt er over geschakelt naar het PlayScreen.
     */
    @Override
    public void update(float delta) {
        if(Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));
    }
    
    /**
     * @param batch - De spritebatch
     * De draw methode tekent de instructie message en de huidige highscore op het scherm.
     */
    @Override
    public void draw(SpriteBatch batch){

        game.batch.draw(
            instructionMessageImg, 
            ortoCam.viewportWidth / 4, // x positie
            ortoCam.viewportHeight / 8 * 1, // y positie
            ortoCam.viewportWidth / 2, // Breedte van de message
            ortoCam.viewportHeight / 2 // Hoogte van de message
        );

        font.draw(
            batch,
            "Current highscore: " + game.getHighScore(), 
            ortoCam.viewportHeight / 5 * 4
        );
    }
    
    /**
     * Bij het sluiten van het scherm word de ingeladen instructie texture en font generator uit het geheugen verwijderd. 
     */
    @Override
    public void dispose() {
        instructionMessageImg.dispose();
        font.dispose();
    }
}
