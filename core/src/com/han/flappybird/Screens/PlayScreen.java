package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.viewport.*;
import com.han.flappybird.FlappyBird;
import com.han.flappybird.Entities.*;

public class PlayScreen implements Screen {

    private static final int BIRD_LEFT_OFFSET = 60;

    private FlappyBird game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Bird bird;
    private World world;
    private boolean gameOver = false;

    public PlayScreen(FlappyBird game){
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

        // TODO 
        bird = new Bird(BIRD_LEFT_OFFSET, 0 + (FlappyBird.CAM_HEIGHT / 3 * 2));
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

        if(world.colissionDetected(bird.getBounds())){
            System.console().writer().println("World detected collision");
            gameOver = true;
        }
        if(bird.colissionDetected()){
            System.console().writer().println("Bird detected collision");
            gameOver = true;
        } 
        if(gameOver){
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("got interrupted!");
            }
            game.setScreen(new EndScreen(game));
        }

        bird.updatePosition(delta);
        world.updateWorld(delta);
        
        // Start batch
        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        // game.batch.draw(bird.getKeyFrame(timeElapsed), bird.getPosition().x, bird.getPosition().y);
        bird.draw(game.batch, delta);
        world.draw(game.batch);
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
        // TODO dispose textures
    }
}
