package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.han.flappybird.FlappyBird;
import com.han.flappybird.Entities.Bird;
import com.han.flappybird.Entities.World;

public class PlayScreen implements Screen {

    private static final int BIRD_LEFT_OFFSET = 60;

    private FlappyBird game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Bird bird;
    private World world;
    private Sound die;

    public PlayScreen(FlappyBird game){
        this.game = game;
    }

    @Override
    public void show() {
        gameCam = new OrthographicCamera();
        gameCam.position.x =+ (FlappyBird.CAM_WIDTH / 2);
        gameCam.position.y =+ (FlappyBird.CAM_HEIGHT / 2);
        gameCam.update();

        die = Gdx.audio.newSound(Gdx.files.internal("audio/die.ogg"));

        gamePort = new FitViewport(FlappyBird.CAM_WIDTH, FlappyBird.CAM_HEIGHT, gameCam);
        gamePort.apply();

        bird = new Bird(BIRD_LEFT_OFFSET, 0 + (FlappyBird.CAM_HEIGHT / 3 * 2));
        world = new World();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1); // OpenGL set background color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // OpenGL apply backgtround color

        game.batch.setProjectionMatrix(gameCam.combined); // Render gameCam projection only
        
        if(Gdx.input.isTouched()) bird.jump(); // Catch user input

        bird.updatePosition(delta);
        world.updateWorld(delta, bird); // needs bird input to determine weheter the bird has past an obstacle

        if(world.colissionDetected(bird.getBounds()) || bird.colissionDetected()){
            die.play(); // play sound
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("Interruption occured!");
            }
            game.setScreen(new EndScreen(game));
        }

        // Start batch
        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        bird.draw(game.batch, delta); // delta is used to determine which animation frame to draw
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
        // TODO dispose textures & sounds
    }
}
