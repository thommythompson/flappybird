package com.han.flappybird.Entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse representeert de vogel die door de speler bestuurt wordt.
*/
public class Bird extends GameWorldObject{
    public static final Vector2 MEASUREMENTS = new Vector2(30,20);
    private Vector2 velocity;
    private Vector2 acceleration;
    private Animation<Texture> animation;
    private Array<Texture> textures;
    private Sound wingSound;

    public Bird(float xPos, float yPos){
        super(xPos, yPos, MEASUREMENTS.x, MEASUREMENTS.y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, -500);
        wingSound = Gdx.audio.newSound(Gdx.files.internal("audio/wing.ogg"));
        
        setAnimation();
    }

    /**
     * Deze methode update de positie van de vogel aan de hand van de delta time en bepaald de waarde van het texture attribuut aan de hand van de verstreken tijd.
     * @param delta - De tijd verstreken vanaf de vorige aanroep in seconden
     * @param timeElapsed - De tijd verstreken binnen de spel sessie in seconden
     * @param worldSpeed - De huidige snelheid van de wereld
     */
    @Override
    public void update(float delta, float timeElapsed, float worldSpeed){
        texture = animation.getKeyFrame(timeElapsed);
        fall(delta);
        accelerateBirdMovement(worldSpeed);
    }

    /** 
     * Bepaald willekeurig de kleur die vogel krijgt en vult het animation attribuut.
     */
    private void setAnimation(){

        Random random = new Random();
        int birdNr = random.nextInt(3) + 1;

        textures = new Array<Texture>();

        switch(birdNr){
            case 1:
                textures.add(new Texture("sprites/bird-blue_0.png"));
                textures.add(new Texture("sprites/bird-blue_1.png"));
                textures.add(new Texture("sprites/bird-blue_2.png"));
                break;
            case 2:
                textures.add(new Texture("sprites/bird-red_0.png"));
                textures.add(new Texture("sprites/bird-red_1.png"));
                textures.add(new Texture("sprites/bird-red_2.png"));
                break;
            case 3:
                textures.add(new Texture("sprites/bird-yellow_0.png"));
                textures.add(new Texture("sprites/bird-yellow_1.png"));
                textures.add(new Texture("sprites/bird-yellow_2.png"));
                break;
        }

        animation = new Animation<Texture>(0.5f, textures, PlayMode.LOOP);
    }

    /**
     * Deze methode update de positie van de vogel aan de hand van de delta time en zorgt voor een voortdurend versnelende val.
     * @param delta - De tijd verstreken vanaf de vorige aanroep in seconden
     */
    private void fall(float delta){
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > 180) velocity.y = 180;
        Vector2 position = getPosition().add(velocity.cpy().scl(delta));
        bounds.setPosition(position);
    }

    /** 
     * Wanneer deze methode wordt aangeroepen zal de positie van de vogel verhoogd worden, ook wordt er een geluid afgespeelt. 
     */  
    public void jump(){
        velocity.y = 150;
        wingSound.play();
    }

    /**
     * Wanneer de wereld versnelt zal ook de vogel sneller moeten kunnen bewegen om het spel speelbaar te houden, deze methode versnelt de snelheid en de acceleratie factor van de vogel aan de hand van de worldspeed.
     * @param worldSpeed - De huidige snelheid van de spel wereld
     */
    private void accelerateBirdMovement(float worldSpeed){
        velocity.y = velocity.y + (worldSpeed / 1000);
        acceleration.y = acceleration.y - (worldSpeed / 1000) ;
    }
}
