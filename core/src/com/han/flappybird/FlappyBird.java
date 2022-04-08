package com.han.flappybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.han.flappybird.Screens.StartScreen;
import com.badlogic.gdx.graphics.Texture;
import java.time.LocalTime;

public class FlappyBird extends Game {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String NAME = "Flappy Bird";

	public SpriteBatch batch;
	private Texture backgroundImg;

	@Override
	public void create () {
		batch = new SpriteBatch();
		
		// Load new background at the start of the game
		updateBackground();

		// Set active screen
		setScreen(new StartScreen(this));
	}

	public void updateBackground(){

		// Load speicifc background depending on if it's day or night
		int currentHour = LocalTime.now().getHour();
        if(currentHour >= 8 && currentHour <= 21) 
			backgroundImg = new Texture("sprites/background-day.png");
        else 
        	backgroundImg = new Texture("sprites/background-day.png");
	}

	public Texture getBackground(){
		return backgroundImg;
	}

	@Override
	public void render () {

		// Delegates render method to screen
		super.render();
	}
}
