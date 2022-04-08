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

    private Texture backgroundImg;
    private Texture messageImg;

    public StartScreen(FlappyBird game){

        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, gameCam);

        backgroundImg = game.getBackground();
        messageImg = new Texture("sprites/message.png");
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
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
        game.batch.draw(backgroundImg, 0 - (FlappyBird.WIDTH / 2), 0 - (FlappyBird.HEIGHT / 2), FlappyBird.WIDTH, FlappyBird.HEIGHT);

        Vector2 newMessageMeasurements = new Vector2(messageImg.getWidth() * 2, messageImg.getHeight() * 2);
        game.batch.draw(messageImg, 0 - (newMessageMeasurements.x / 2), 0 - (newMessageMeasurements.y / 2), newMessageMeasurements.x, newMessageMeasurements.y);
        game.batch.end();
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
        
        // Clear textures
        backgroundImg.dispose();
        messageImg.dispose();
    }

}
