package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.han.flappybird.FlappyBird;

public abstract class FBScreen implements Screen {
    protected FlappyBird game;
    protected OrthographicCamera gameCam;
    protected Viewport gamePort;

    public FBScreen(FlappyBird game){
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
    }

    @Override
    public void render(float delta){

        // OPENGL set background color
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Render gameCam projection only
        game.batch.setProjectionMatrix(gameCam.combined);
    }

    @Override
    public void resize(int width, int height) {
        
        gamePort.update(width, height);
        gameCam.position.x =+ (FlappyBird.CAM_WIDTH / 2);
        gameCam.position.y =+ (FlappyBird.CAM_HEIGHT / 2);
        gameCam.update();
    }
}
