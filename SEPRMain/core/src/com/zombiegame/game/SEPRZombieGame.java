package com.zombiegame.game;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import helpers.GameWorldData;
import screens.GameWorld;
import sprites.Player;

public class SEPRZombieGame extends Game {
	
	public HashMap<Integer,GameWorldData> level1;
	
	//Define parameters for each map
	private GameWorldData heslingtonEastData = new GameWorldData(50,50,"maps/east.tmx",10,10,10,10,10,20, 1);
	private GameWorldData heslingtonWestData = new GameWorldData(50,50, "maps/west.tmx",10,10,10,10,10,20, 1); //don't know if parameters are right
	
	// Create map loader
	TmxMapLoader loader = new TmxMapLoader();
	
	//Create levels
	public HashMap<Integer, GameWorldData> levels = new HashMap<Integer, GameWorldData>();
	
	
	@Override
	public void create () {
		//everything in here is just to test at the  moment
		//getter and setter for collisionLayer in player to adjust to collisions on different screens
		Player player = new Player(new Sprite(new Texture("img/player.png")), this);

		addLevel(1, heslingtonEastData); 
		addLevel(2, heslingtonWestData);
		setLevel(1, player);
		//main menu test setScreen(new Menu());
	}
	
	public void addLevel (int levelNumber, GameWorldData worldData) {
		levels.put(levelNumber, worldData);
	}
	
	public void setLevel(int level, Player player) {
		
		String mapPath = levels.get(level).getMap();
		TiledMap map = loader.load(mapPath);

		setScreen(new GameWorld(map, levels.get(level), player));
		
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
