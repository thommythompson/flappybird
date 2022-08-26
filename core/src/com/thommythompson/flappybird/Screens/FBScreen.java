package com.thommythompson.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.thommythompson.flappybird.*;

/**
* @version 1
* @author Thomas Hofman
* Deze abstracte klasse bevat een standaard implementatie van de LibGDX Screen interface. Alle scherm klassen binnen de Flappy Bird zullen over erven van deze klasse.
*/
public abstract class FBScreen implements Screen {
    protected FlappyBird game;
    protected OrthographicCamera ortoCam;
    protected Viewport viewPort;

    public FBScreen(FlappyBird game){
        this.game = game;
    }

    /**
     * De runOnce methode is abstract en bied overervende klassen de mogelijkheid om eenmalige acties uit te voeren bij het openen van een scherm.
     */
    public abstract void runOnce();

    /**
     * De update methode is abstract en bied overervende klassen de mogelijkheid om de state aan te passen voordat deze op het scherm weergegeven wordt middels de draw methode.
     * @param delta - De tijd verstreken vanaf de vorige aanroep in seconden
     */
    public abstract void update(float delta);

    /**
     * De draw methode is abstract en bied overervende klassen de mogelijkheid om sprites binnen de spritebatch te renderen, de inhoud van de spritebatch wordt ieder frame op het scherm weergegeven.
     * @param batch - De SpriteBatch
     */
    public abstract void draw(SpriteBatch batch);

    /** 
     * De dispose methode is abstract en bied overervende klassen de mogelijkheid om resources te disposen.
     */
    public abstract void dispose();

    /** 
     * De show klasse wordt standaard aangeroepen bij het openen van een scherm en maakt de Ortographic Camera en Viewport aan.
     */
    @Override
    public void show() {
        
        // een offset word toegepast op de x en y positie van de orto cam zodat het 0,0 coordinaat zich niet in het middel van het scherm maar in de hoek links onder bevind. 
        ortoCam = new OrthographicCamera();
        ortoCam.position.x =+ (Configuration.PROJECTION_WIDTH / 2);
        ortoCam.position.y =+ (Configuration.PROJECTION_HEIGHT / 2);
        ortoCam.update();

        viewPort = new FitViewport(
            Configuration.PROJECTION_WIDTH, // Viewport breedte
            Configuration.PROJECTION_HEIGHT, // Viewport hoogte
            ortoCam
        );
        viewPort.apply();
        
        runOnce();
    }

    /** 
     * @param delta - De tijd verstreken vanaf de vorige aanroep in seconden
     * De render methode van de Screen interface wordt continue aangeroepen. De tijd tussen deze aanroepen word als parameter meegegeven. 
     * Binnen deze methode word het scherm schoongeveegd en de Ortographic Camera toegepast.
     */
    @Override
    public void render(float delta){

        // LibGDX gebruikt OpenGL rendering, onderstaande commando's zorgen er voor dat het scherm "schoongeveegd" wordt.
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Zorgt er voor dat de spritebatch alleen rendert wat zichtbaar is door de orto cam.
        game.batch.setProjectionMatrix(ortoCam.combined);

        // Update state
        update(delta);

        // Render to screen
        game.batch.begin();
        game.batch.draw(
            game.getBackground(), 
            0, // x positie
            0, // y positie
            ortoCam.viewportWidth, // Breedte van de background (fullscreen) 
            ortoCam.viewportHeight // Hoogte van de background (fullscreen) 
        );
        draw(game.batch);
        game.batch.end();
        
    }

    /** 
     * @param width - Nieuwe breedte van het venster
     * @param height - Nieuwe hoogte van het venster
     * De resize methode wordt aangeroepen wanneer de user het scherm verkleint of vergroot en past opnieuw de viewport en ortocam toe.
     */
    @Override
    public void resize(int width, int height) {
        
        // Bij en resize dient de viewport en ortocam aangepast te worden naar de nieuwe resolutie.
        viewPort.update(width, height);
        ortoCam.position.x =+ (Configuration.PROJECTION_WIDTH / 2);
        ortoCam.position.y =+ (Configuration.PROJECTION_HEIGHT / 2);
        ortoCam.update();
    }

    /** 
     * De hide methode wordt aangeroepen wanneer het scherm gesloten wordt, standaard roept deze methode zijn eigen dispose methode aan.
     */
    @Override
    public void hide() {
        dispose();
    }

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
