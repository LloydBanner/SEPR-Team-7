package com.zombiegame.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zombiegame.game.SEPRZombieGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SEPRZombieGame(), config);
		
		config.useGL30 = false;
	    config.width = 1280;
	    config.height = 720;
	    config.resizable = false;
	}
}
