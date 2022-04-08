package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.viewport.*;
import com.han.flappybird.FlappyBird;

public class EndScreen implements Screen {
    
    private FlappyBird game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public EndScreen(FlappyBird game){

        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, gameCam);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {

        // OPENGL set background color
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Render gameCam projecten only
        game.batch.setProjectionMatrix(gameCam.combined);

        // Catch user input
        if(Gdx.input.isTouched()){
            game.setScreen(new StartScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        
        // Adjust viewport
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
    
}
