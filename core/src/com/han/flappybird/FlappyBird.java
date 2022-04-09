package com.han.flappybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.han.flappybird.Screens.StartScreen;
import com.badlogic.gdx.graphics.Texture;
import java.time.LocalTime;

public class FlappyBird extends Game {

	public static final String WINDOW_TITLE = "Flappy Bird";
	public static final int WINDOW_WIDTH = 480;
	public static final int WINDOW_HEIGHT = 800;
	public static final int CAM_WIDTH = WINDOW_WIDTH / 2;
	public static final int CAM_HEIGHT = WINDOW_HEIGHT / 2;

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
