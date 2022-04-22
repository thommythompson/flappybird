package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.han.flappybird.FlappyBird;

/**
* @version 1
* This is the first screen shown while starting the application. 
*
* It draw's the background and message textures to the screen and waits for user input to switch to the second screen. 
*/

public class StartScreen extends FBScreen {
    private Texture messageImg;

    public StartScreen(FlappyBird game){
        super(game);
    }

    @Override
    public void show() {
        super.show();
        messageImg = new Texture("sprites/message.png");
    }

    @Override
    public void render(float delta){
        super.render(delta);

        if(Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));

        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        game.batch.draw(messageImg, FlappyBird.CAM_WIDTH / 4, FlappyBird.CAM_HEIGHT / 4, FlappyBird.CAM_WIDTH / 2, FlappyBird.CAM_HEIGHT / 2);
        game.batch.end();
    }
    
    @Override
    public void dispose() {
        messageImg.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

}
