package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.han.flappybird.FlappyBird;
import com.han.flappybird.Entities.GameWorld;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse extend de FBScreen klasse en representeert het speel scherm.
*/
public class PlayScreen extends FBScreen {
    private GameWorld gameWorld;
    private Sound gameOverSound;

    public PlayScreen(FlappyBird game){
        super(game);
    }

    /**
     * Binnen de show word een instantie van de spelwereld aangemaakt. Ook wordt het geluid ingeladen dat afgespeeld wordt wanneer de vogel in aanraking komt met schermranden en/of obstakels.
     */
    @Override
    public void runOnce() {
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("audio/die.ogg"));
        gameWorld = new GameWorld();
    }

    /**
     * @param float delta
     * Binnen de render methode wordt de spelwereld aan de hand van de delta time geupate. 
     * Indien de wereld een botsing detecteert zal er een geluid afgespeeld worden en de game gefreezed worden voor 1 seconden waarnaar het over schakelt tot het game over scherm.
     * Indien er geen botsing gedetecteert is word de geupdate versie van de wereld op het scherm weergegeven.
     */
    @Override
    public void update(float delta) {

        gameWorld.update(delta, Gdx.input.isTouched());
        if(gameWorld.isCollisionDetected()){
            gameOverSound.play();
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("Interruption occured!");
            }

            int achievedScore =  gameWorld.getScore().getScore();
            if(achievedScore > game.getHighScore()) {
                game.setHighScore(achievedScore);
                game.setScreen(new EndScreen(game, true));
            }else{
                game.setScreen(new EndScreen(game,false));
            }
            
        }
    }
    
    /**
     * @param float delta
     * Binnen de render methode wordt de spelwereld aan de hand van de delta time geupate. 
     * Indien de wereld een botsing detecteert zal er een geluid afgespeeld worden en de game gefreezed worden voor 1 seconden waarnaar het over schakelt tot het game over scherm.
     * Indien er geen botsing gedetecteert is word de geupdate versie van de wereld op het scherm weergegeven.
     */
    @Override
    public void draw(SpriteBatch batch){
        gameWorld.draw(batch); 
    }
    
    /**
     * Bij het sluiten van het scherm word het ingeladen game over geluid en de spelwereld uit het geheugen verwijderd. 
     */
    @Override
    public void dispose() {
        gameWorld.dispose();
        gameOverSound.dispose();
    }
}
