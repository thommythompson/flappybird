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
* @author Thomas Hofman
* Deze abstracte klasse bevat een standaard implementatie van de LibGDX Screen interface. Alle scherm klassen binnen de Flappy Bird zullen over erven van deze klasse.
*/
public abstract class FBScreen implements Screen {
    protected FlappyBird game;
    protected OrthographicCamera gameCam;
    protected Viewport gamePort;

    /** 
     * @param FlappyBird game
     * De constructor verwacht dat je een referentie naar de FlappyBird instantie meegeeft.
     */
    public FBScreen(FlappyBird game){
        this.game = game;
    }

    /** 
     * De show klasse wordt standaard aangeroepen bij het openen van een scherm en maakt de Ortographic Camera en Viewport aan.
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
     * @param float delta
     * De render methode van de Screen interface wordt continue aangeroepen. De tijd tussen deze aanroepen word als parameter meegegeven. 
     * Binnen deze methode word het scherm schoongeveegd en de Ortographic Camera toegepast.
     */
    @Override
    public void render(float delta){

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.setProjectionMatrix(gameCam.combined);
    }

    /** 
     * @param int width
     * @param int height
     * De render methode van de Screen interface wordt continue aangeroepen. De tijd tussen deze aanroepen word als parameter meegegeven. 
     * Binnen deze methode word het scherm schoongeveegd en de Ortographic Camera toegepast.
     */
    @Override
    public void resize(int width, int height) {
        
        gamePort.update(width, height);
        gameCam.position.x =+ (FlappyBird.CAM_WIDTH / 2);
        gameCam.position.y =+ (FlappyBird.CAM_HEIGHT / 2);
        gameCam.update();
    }

    /** 
     * De hide methode wordt aangeroepen wanneer het scherm gesloten wordt, standaard roept deze methode zijn eigen dispose methode aan.
     */
    @Override
    public void hide() {
        dispose();
    }

    /** 
     * De dispose methode is abstract en bied overervende klassen de mogelijkheid om resources te disposen.
     */
    @Override
    public abstract void dispose();

    /** 
     * De pause methode is alleen van toepassing op smartphones en wordt aangeroepen wanneer de gebruiker naar een andere applicatie schakelt.
     */
    @Override
    public void pause() {}

    /** 
     * De resume methode is alleen van toepassing op smartphones en wordt aangeroepen wanneer de gebruiker weer terug keert naar deze applicatie.
     */
    @Override
    public void resume() {}
}
