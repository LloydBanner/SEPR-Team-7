package com.zombiegame.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import screens.GameWorld;
import screens.Menu;

public class SEPRZombieGame extends Game {
	
	// Maps level number to map path
	private Map<Integer, String> levels = createLevels();
	
	// Current level
	private int currentLevel = 2;
	
	// Create map loader
	TmxMapLoader loader = new TmxMapLoader();
	
	
	@Override
	public void create () {
		//everything in here is just to test at the  moment
		//getter and setter for collisionLayer in player to adjust to collisions on different screens
		
		setLevel(currentLevel);
		//main menu test setScreen(new Menu());
		
	}

	private Map<Integer, String> createLevels() {
		
		levels = new HashMap<Integer, String>();
		levels.put(1, "maps/testmap.tmx");
		levels.put(2, "maps/east.tmx");
		return levels;
		
	}
	
	public void setLevel(int level) {
		
		String mapPath = levels.get(level);
		TiledMap map = loader.load(mapPath);
		setScreen(new GameWorld(map));
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
	
	@Override
	public void resize (int width, int height) {
		super.resize(width, height);
	}
	
	@Override
	public void pause() {
		super.pause();
	}
	
	@Override
	public void resume() {
		super.resume();
	}
	
}
