package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.han.flappybird.FlappyBird;
import com.han.flappybird.Entities.World;

/**
* @version 1
* This class represents they screen at which the game is played.
*
* It creates a world, updates it acording to the delta time and wheter user input is detected & draws it to the screen.
* If a collision is detected it will freeze the screen and switch to the EndScreen.
*/
public class PlayScreen extends FBScreen {
    private World world;
    private Sound die;

    public PlayScreen(FlappyBird game){
        super(game);
    }

    @Override
    public void show() {
        super.show();

        die = Gdx.audio.newSound(Gdx.files.internal("audio/die.ogg"));
        world = new World();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        world.update(delta, Gdx.input.isTouched());

        if(world.colissionDetected()){
            die.play();
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("Interruption occured!");
            }
            game.setScreen(new EndScreen(game));
        }

        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        world.draw(game.batch);  
        game.batch.end();
    }

    @Override
    public void dispose() {
        world.dispose();
        die.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
}
