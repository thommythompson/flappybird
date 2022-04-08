package com.han.flappybird.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.han.flappybird.Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import java.util.Random;

public class Bird{
    private static final int GRAVITY = -12;
    private Vector2 position;
    private Vector2 velocity;
    private Animation<Texture> animation;
    private Array<Texture> textures;
    private Rectangle bounds;

    public Bird(int posX, int posY){
        position = new Vector2(posX, posY);
        velocity = new Vector2(0, 0);
        updateAnimation();
    }

    public Vector2 getPosition(){
        return this.position;
    }

    public Texture getKeyFrame(float timeElapsed){
        return animation.getKeyFrame(timeElapsed, true);
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

        if(position.y >= (PlayScreen.CAM_HEIGHT / 2) - textures.get(0).getHeight()){
            position.y = (PlayScreen.CAM_HEIGHT / 2) - textures.get(0).getHeight();
        }else if(position.y <= (0 - PlayScreen.CAM_HEIGHT / 2) + World.GROUND_HEIGHT){
            position.y = (0 - PlayScreen.CAM_HEIGHT / 2) + World.GROUND_HEIGHT;
        }

        velocity.scl(1 / delta);
    }

    

    public void jump(){
        velocity.y =+ 250;
    }
}
