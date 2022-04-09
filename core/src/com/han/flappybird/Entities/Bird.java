package com.han.flappybird.Entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.han.flappybird.FlappyBird;

public class Bird{
    private static final Vector2 MEASUREMENTS = new Vector2(30,20);
    private static final int GRAVITY = -12;
    private Vector2 position;
    private Vector2 velocity;
    private Animation<Texture> animation;
    private Array<Texture> textures;
    private Rectangle bounds;
    private float timeElapsed;

    public Bird(int posX, int posY){
        position = new Vector2(posX, posY);
        velocity = new Vector2(0, 0);
        updateAnimation();

        bounds = new Rectangle(posX, posY, MEASUREMENTS.x, MEASUREMENTS.y);
    }

    public Vector2 getPosition(){
        return this.position;
    }

    public void draw(SpriteBatch batch, float delta){
        timeElapsed += delta;

        batch.draw(animation.getKeyFrame(timeElapsed), getPosition().x, getPosition().y, MEASUREMENTS.x, MEASUREMENTS.y);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    private void updateAnimation(){

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

    public void updatePosition(float delta){
        velocity.add(0, GRAVITY);
        velocity.scl(delta);
        position.add(0, velocity.y);
        bounds.y = position.y + velocity.y;

        if(position.y >= FlappyBird.CAM_HEIGHT - textures.get(0).getHeight()) position.y = FlappyBird.CAM_HEIGHT - textures.get(0).getHeight();
        else if(position.y <= 0  + Base.MEASUREMENTS.y) position.y = 0  + Base.MEASUREMENTS.y;

        velocity.scl(1 / delta);
    }

    public boolean colissionDetected(){
        if(position.y >= FlappyBird.CAM_HEIGHT - textures.get(0).getHeight()) return true;
        else if(position.y <= 0  + Base.MEASUREMENTS.y) return true;
        return false;
    }

    public void jump(){
        velocity.y =+ 150;
    }
}
