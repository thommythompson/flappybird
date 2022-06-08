package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.han.flappybird.FlappyBird;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse extend de FBScreen klasse en representeert het game over scherm.
*/
public class EndScreen extends FBScreen  {
    private Texture gameOverImg;

    public EndScreen(FlappyBird game){
        super(game);
    }

    /**
     * Binnen de show methode wordt de benodigde game over texture ingeladen.
     */
    @Override
    public void show() {
        super.show();
        gameOverImg = new Texture("sprites/gameover.png");
    }

    /**
     * @param float delta
     * Binnen de render methode wordt user input afgevangen, indien deze gedetecteert wordt over wordt er over geschakelt naar het PlayScreen.
     * Verder wordt de achtergrond en de game over texture op het scherm weergegeven.
     */
    @Override
    public void render(float delta){
        super.render(delta);

        if(Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));

        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        game.batch.draw(gameOverImg, FlappyBird.CAM_WIDTH / 4, FlappyBird.CAM_HEIGHT / 4, FlappyBird.CAM_WIDTH / 2, FlappyBird.CAM_HEIGHT / 12);
        game.batch.end();
    }

    /**
     * Bij het sluiten van het scherm word de ingeladen game over texture uit het geheugen verwijderd. 
     */
    @Override
    public void dispose() {
        gameOverImg.dispose();
    }
}
