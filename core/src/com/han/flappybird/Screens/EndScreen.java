package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.han.flappybird.FlappyBird;

/**
* @version 1
* This class represents they game over screen.
*
* When touched the game will start over again.
*/

public class EndScreen extends FBScreen  {
    private Texture gameOverImg;

    public EndScreen(FlappyBird game){
        super(game);
    }

    @Override
    public void show() {
        gameOverImg = new Texture("sprites/gameover.png");
    }

    @Override
    public void render(float delta){

        if(Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));

        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        game.batch.draw(gameOverImg, FlappyBird.CAM_WIDTH / 4, FlappyBird.CAM_HEIGHT / 4, FlappyBird.CAM_WIDTH / 2, FlappyBird.CAM_HEIGHT / 12);
        game.batch.end();
    }

    @Override
    public void dispose() {
        gameOverImg.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

}
