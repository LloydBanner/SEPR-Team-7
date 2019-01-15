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

import helpers.GameWorldData;
import screens.GameWorld;
import screens.Menu;

public class SEPRZombieGame extends Game {
	
	public HashMap<Integer,GameWorldData> level1;
	
	//Define parameters for each map
	private GameWorldData heslingtonEastData = new GameWorldData("maps/east.tmx",10,10,10,10,10,20);
	private GameWorldData heslingtonWestData = new GameWorldData("maps/west.tmx",10,10,10,10,10,20); //don't know if parameters are right
	
	
	
	// Current level
	private int currentLevel = 1;
	
	// Create map loader
	TmxMapLoader loader = new TmxMapLoader();
	
	//Create levels
	public HashMap<Integer, GameWorldData> levels = new HashMap<Integer, GameWorldData>();
	
	
	@Override
	public void create () {
		//everything in here is just to test at the  moment
		//getter and setter for collisionLayer in player to adjust to collisions on different screens
		addLevel(1, heslingtonEastData); 
		addLevel(2, heslingtonWestData);
		setLevel(1);
		//main menu test setScreen(new Menu());
	}
	
	public void addLevel (int levelNumber, GameWorldData worldData) {
		levels.put(levelNumber, worldData);
	}
	
	
	public void setLevel(int level) {
		
		String mapPath = levels.get(level).getMap();
		TiledMap map = loader.load(mapPath);
		
		setScreen(new GameWorld(map, levels.get(level)));
		
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
