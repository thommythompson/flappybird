package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.han.flappybird.FlappyBird;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse extend de FBScreen klasse en representeert het start scherm.
*/
public class StartScreen extends FBScreen {
    private Texture messageImg;

    public StartScreen(FlappyBird game){
        super(game);
    }

    /**
     * Binnen de show methode wordt de benodigde instructie texture ingeladen.
     */
    @Override
    public void show() {
        super.show();
        messageImg = new Texture("sprites/message.png");
    }

    /**
     * @param float delta
     * Binnen de render methode wordt user input afgevangen, indien deze gedetecteert wordt over wordt er over geschakelt naar het PlayScreen.
     * Verder wordt de achtergrond en de instructie texture op het scherm weergegeven.
     */
    @Override
    public void render(float delta){
        super.render(delta);

        if(Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));

        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        game.batch.draw(messageImg, FlappyBird.CAM_WIDTH / 4, FlappyBird.CAM_HEIGHT / 4, FlappyBird.CAM_WIDTH / 2, FlappyBird.CAM_HEIGHT / 2);
        game.batch.end();
    }
    
    /**
     * Bij het sluiten van het scherm word de ingeladen instructie texture uit het geheugen verwijderd. 
     */
    @Override
    public void dispose() {
        messageImg.dispose();
    }
}
