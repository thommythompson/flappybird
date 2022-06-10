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
     * Binnen de show methode wordt de benodigde instructie texture ingeladen.
     */
    @Override
    public void runOnce() {
        instructionMessageImg = new Texture("sprites/message.png");
        font = new FlappyBirdFont(FlappyBirdFontSize.Small);
    }

    @Override
    public void update(float delta) {
        if(Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));
    }
    /**
     * @param float delta
     * Binnen de render methode wordt user input afgevangen, indien deze gedetecteert wordt over wordt er over geschakelt naar het PlayScreen.
     * Verder wordt de achtergrond en de instructie texture op het scherm weergegeven.
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
     * Bij het sluiten van het scherm word de ingeladen instructie texture uit het geheugen verwijderd. 
     */
    @Override
    public void dispose() {
        instructionMessageImg.dispose();
        font.dispose();
    }
}
