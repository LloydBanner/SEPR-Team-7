package com.zombiegame.game;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import helpers.GameWorldData;
import screens.GameWorld;
import screens.Menu;
import sprites.Player;

public class SEPRZombieGame extends Game {
	
	public HashMap<Integer,GameWorldData> level1;
	
	//Define parameters for each map
	//Heslington east
	private int[][] zombieCoordinatesHesEast = new int[][] {{50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}};
	private int[][] healthCoordinatesHesEast = new int[][] {{46,26}, {46,26}, {46,26}, {46,26}, {46,26}, {46,26}, {46,26}, {46,26}, {46,26}, {46,26}};
	private int[][] speedCoordinatesHesEast = new int[][] {{44,20}};
	private int[][] shieldCoordinatesHesEast = new int[][] {{40,23}};
	private int[][] missionItemCoordinatesHesEast = new int[][] {{44,25}};
	
	private GameWorldData heslingtonEastData = new GameWorldData(missionItemCoordinatesHesEast, zombieCoordinatesHesEast, healthCoordinatesHesEast,
																 speedCoordinatesHesEast, shieldCoordinatesHesEast, 50,50,"maps/east.tmx",10,10,1,10,1,20, 1, 1);
	
	//Heslington West
	private int[][] zombieCoordinatesHesWest = new int[][] {{50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}};
	private int[][] healthCoordinatesHesWest = new int[][] {{50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}};
	private int[][] speedCoordinatesHesWest = new int[][] {{50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}, {50,50}};
	private int[][] missionItemCoordinatesHesWest = new int[][] {{50,50}};
	private int[][] shieldItemCoordinatesHesWest = new int[][] {{44,25}};

	private GameWorldData heslingtonWestData = new GameWorldData(missionItemCoordinatesHesWest, zombieCoordinatesHesWest, healthCoordinatesHesWest,
																 speedCoordinatesHesEast, shieldItemCoordinatesHesWest,50,50, "maps/west.tmx",10,10,10,10,10,20,1, 1);
	
	// Create map loader
	TmxMapLoader loader = new TmxMapLoader();
	
	//Create levels
	public HashMap<Integer, GameWorldData> levels = new HashMap<Integer, GameWorldData>();
	
	private Player player;
	
	
	@Override
	public void create () {
		//everything in here is just to test at the  moment
		//getter and setter for collisionLayer in player to adjust to collisions on different screens
		this.setPlayer(new Player(new Sprite(new Texture("img/player.png")), this));

		addLevel(1, heslingtonEastData); 
		addLevel(2, heslingtonWestData);

		setScreen(new Menu(this));
	}
	
	public void addLevel (int levelNumber, GameWorldData worldData) {
		levels.put(levelNumber, worldData);
	}
	
	public void setLevel(int level) {
		
		String mapPath = levels.get(level).getMap();
		TiledMap map = loader.load(mapPath);

		setScreen(new GameWorld(map, levels.get(level), getPlayer()));
		
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
