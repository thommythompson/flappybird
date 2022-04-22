package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.han.flappybird.FlappyBird;

/**
* @version 1
* This abstract class includes the ortocam and viewport used in all screens. 
*/
public abstract class FBScreen implements Screen {
    protected FlappyBird game;
    protected OrthographicCamera gameCam;
    protected Viewport gamePort;

    public FBScreen(FlappyBird game){
        this.game = game;
    }

    /**
     * The show method runs once when the screen is first opened.
     * It created the ortocam and resets it's position so that 0, 0 is in the left bottom corner.
     * This cam is then applied to the newly created viewport.
     */
    @Override
    public void show() {
        gameCam = new OrthographicCamera();
        gameCam.position.x =+ (FlappyBird.CAM_WIDTH / 2);
        gameCam.position.y =+ (FlappyBird.CAM_HEIGHT / 2);
        gameCam.update();

        gamePort = new FitViewport(FlappyBird.CAM_WIDTH, FlappyBird.CAM_HEIGHT, gameCam);
        gamePort.apply();
    }

    /**
     * The render method is callsed every time the screen gets rendered.
     * glClear command's clear the screen from it's previous render.
     * At tlast the ortocam get's applied to the spritebatch.
     */
    @Override
    public void render(float delta){

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.setProjectionMatrix(gameCam.combined);
    }

    /** The resize is called when the user resizes the window, this method ensures the dimensions of the viewport and ortocam get changed accordingly. */
    @Override
    public void resize(int width, int height) {
        
        gamePort.update(width, height);
        gameCam.position.x =+ (FlappyBird.CAM_WIDTH / 2);
        gameCam.position.y =+ (FlappyBird.CAM_HEIGHT / 2);
        gameCam.update();
    }
}
