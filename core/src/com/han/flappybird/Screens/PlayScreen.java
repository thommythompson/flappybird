package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.viewport.*;
import com.han.flappybird.FlappyBird;
import com.han.flappybird.Entities.*;

public class PlayScreen implements Screen {

    public static final int CAM_WIDTH = FlappyBird.WIDTH / 2; // default: 240
    public static final int CAM_HEIGHT = FlappyBird.HEIGHT / 2; // default: 400
    private static final int BIRD_LEFT_OFFSET = 60;

    private FlappyBird game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Bird bird;
    private float timeElapsed;
    private World world;

    public PlayScreen(FlappyBird game){
        this.game = game;
    }

    @Override
    public void show() {
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(CAM_WIDTH, CAM_HEIGHT, gameCam);
        gamePort.apply();

        bird = new Bird(0 - CAM_WIDTH + BIRD_LEFT_OFFSET, 0 - (CAM_HEIGHT / 3));
        world = new World();
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
            bird.jump();
        }

        if(world.hasCollided(bird.getBounds())){

        }

        timeElapsed =+ delta;

        bird.updatePosition(delta);
        world.updateWorld(delta);

        gameCam.position.x = BIRD_LEFT_OFFSET + bird.getPosition().x;
        gameCam.position.y = 0;
        gameCam.update();

        // Start batch
        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        game.batch.draw(bird.getKeyFrame(timeElapsed), bird.getPosition().x, bird.getPosition().y);
        world.addTextures(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height);
        gameCam.position.set(BIRD_LEFT_OFFSET + bird.getPosition().x, 0, 0);
        gameCam.translate(FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
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
    }
}
