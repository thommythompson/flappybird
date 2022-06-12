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
* Deze klasse extend de FBScreen klasse en representeert het game over scherm.
*/
public class EndScreen extends FBScreen  {
    private Texture gameOverMessageImg;
    private boolean newHighScore;
    private FlappyBirdFont font;

    public EndScreen(FlappyBird game, boolean newHighScore){
        super(game);
        this.newHighScore = newHighScore;
    }

    /**
     * Binnen de runOnce methode wordt de benodigde game over texture en de font generator ingeladen.
     */
    @Override
    public void runOnce() {
        gameOverMessageImg = new Texture("sprites/gameover.png");
        font = new FlappyBirdFont(FlappyBirdFontSize.Small);
    }

    /**
     * @param float delta
     * Binnen de render methode wordt user input afgevangen, indien deze gedetecteert wordt over wordt er over geschakelt naar het PlayScreen.
     */
    @Override
    public void update(float delta) {
        if(Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));
    }
    
    /**
     * @param SpriteBatch batch
     * De draw methode tekent de game over message en de huidige/nieuw behaalde highscore op het scherm.
     */
    @Override
    public void draw(SpriteBatch batch){
        
        batch.draw(
            gameOverMessageImg, 
            ortoCam.viewportWidth / 4, // x positie
            ortoCam.viewportHeight / 4, // y positie
            ortoCam.viewportWidth / 2, // Breedte van de message
            ortoCam.viewportHeight / 12 // Hoogte van de message
        );

        int highScore = game.getHighScore();
        String message = "current highscore: " + highScore;
        if(newHighScore) message = "Congrats with your new highscore " + highScore + "!";

        font.draw(
            batch,
            message, 
            ortoCam.viewportHeight / 5 * 4
        );
    }
    
    /**
     * Bij het sluiten van het scherm word de ingeladen game over texture en font generator uit het geheugen verwijderd.
     */
    @Override
    public void dispose() {
        gameOverMessageImg.dispose();
        font.dispose();
    }
}
