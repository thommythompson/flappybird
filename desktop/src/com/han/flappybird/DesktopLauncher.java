package com.han.flappybird;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);

		// Apply custom configuration
		config.setWindowedMode(Configuration.WINDOW_WIDTH_IN_PIXELS, Configuration.WINDOW_HEIGHT_IN_PIXELS);
		config.setTitle(Configuration.WINDOW_TITLE);
		
		// TODO fix windows icon
		// config.setWindowIcon("assets/favicon.png");

		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		// Launch game with custom config
		new Lwjgl3Application(new FlappyBird(), config);
	}
}
