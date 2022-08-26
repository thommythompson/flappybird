package com.thommythompson.flappybird;

/**
* @version 1
* @author Thomas Hofman
* Deze methode bevat publiekelijk beschikbare constante variabele die de presentatie/ervaring van het spel beinvloeden. 
*/
public class Configuration {

    // Windows settings
    public static final String WINDOW_TITLE = "Flappy Bird";
	public static final int WINDOW_WIDTH_IN_PIXELS = 480;
	public static final int WINDOW_HEIGHT_IN_PIXELS = 800;

    // Size of the world
    public static final int GAME_WORLD_WIDTH = 480;
	public static final int GAME_WORLD_HEIGHT = 800;

    // Size of the part of the world that is displayed
	public static final int PROJECTION_WIDTH = GAME_WORLD_WIDTH / 2;
	public static final int PROJECTION_HEIGHT = GAME_WORLD_HEIGHT / 2;

    // Game settings
    public static final int MAX_WORLD_SPEED = 110;
}
