package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.viewport.*;
import com.han.flappybird.FlappyBird;
import com.badlogic.gdx.math.Vector2;

public class StartScreen implements Screen {
    
    private FlappyBird game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private Texture messageImg;

    public StartScreen(FlappyBird game){

        this.game = game;
    }

    @Override
    public void show() {
        gameCam = new OrthographicCamera();
        gameCam.position.x =+ (FlappyBird.CAM_WIDTH / 2);
        gameCam.position.y =+ (FlappyBird.CAM_HEIGHT / 2);
        gameCam.update();

        gamePort = new FitViewport(FlappyBird.CAM_WIDTH, FlappyBird.CAM_HEIGHT, gameCam);
        gamePort.apply();

        messageImg = new Texture("sprites/message.png");
    }

    @Override
    public void render(float delta){

        // OPENGL set background color
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Render gameCam projection only
        game.batch.setProjectionMatrix(gameCam.combined);

        // Catch user input
        if(Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));

        // Start batch
        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        game.batch.draw(messageImg, FlappyBird.CAM_WIDTH / 4, FlappyBird.CAM_HEIGHT / 4, FlappyBird.CAM_WIDTH / 2, FlappyBird.CAM_HEIGHT / 2);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        
        gamePort.update(width, height);
        gameCam.position.x =+ (FlappyBird.CAM_WIDTH / 2);
        gameCam.position.y =+ (FlappyBird.CAM_HEIGHT / 2);
        gameCam.update();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        
        // Clear textures
        messageImg.dispose();
    }

}
