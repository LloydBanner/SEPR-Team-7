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
	
	//public HashMap<Integer,GameWorldData> level1;
	
	//Define parameters for each map
	// GameWorldData take parameters GameWorldData(int[][] missionItemCoordinates, int[][] zombieCoordinates, int[][] healthItemCoordinates, int[][] speedItemCoordinates, 
	// int[][] sheildItemCooridinates, int[][] doorCoordinates, int xPlayerPosition, int yPlayerPosition, String map, int maxEnemies,
	// int maxHealthItems, int maxSpeedItems, int maxWeapons, int maxMissionItems, 
	// int maxCollisions, int maxDoors, int maxShieldItems, String missionItemTexture, String missionItemID, int nextLevel)
	
	//Heslington east
	private int[][] zombieCoordinatesHesEast = new int[][] {{46,66}, {56,50}, {48,55}, {50,60}, {59,65}, {50,79}, {53,74}, {44,75}, {44,60}, {44,57}};
	private int[][] healthCoordinatesHesEast = new int[][] {{63,47}, {35,64}};
	private int[][] speedCoordinatesHesEast = new int[][] {{57,63}};
	private int[][] shieldCoordinatesHesEast = new int[][] {{51,42}};
	private int[][] missionItemCoordinatesHesEast = new int[][] {{59,48}};
	private int[][] doorCoordinatesHesEast = new int[][] {{50, 82}};
	//MissionItem Parameters
	private String missionItemIDHesEast = "key";
	private String missionItemTextureHesEast = "img/key.png";
	
	
	private GameWorldData heslingtonEastData = new GameWorldData(missionItemCoordinatesHesEast, zombieCoordinatesHesEast, healthCoordinatesHesEast,
																 speedCoordinatesHesEast, shieldCoordinatesHesEast, doorCoordinatesHesEast,
																 40,51,"maps/east.tmx",10,2,1,10,1,20, 1, 1, missionItemTextureHesEast, missionItemIDHesEast, 3);
	
	//Heslington West
	private int[][] zombieCoordinatesHesWest = new int[][] {{82,38}, {84,40}, {82,41}, {80,4}, {75,13}, {85,76}, {77,79}, {91,73}, {79,70}, {82,74}, {84,81}, {95,95},{66,36},{74,44},{70,49}};
	private int[][] healthCoordinatesHesWest = new int[][] {{65,93}, {72,30}};
	private int[][] speedCoordinatesHesWest = new int[][] {{64,7}, {93,70}};
	private int[][] missionItemCoordinatesHesWest = new int[][] {{80,73}};
	private int[][] shieldItemCoordinatesHesWest = new int[][] {{69,42}};
	private int[][] doorCoordinatesHesWest = new int[][] {{10000000,10000000}};
	
	//MissionITem paraemters
	private String missionItemIDHesWest = "win condition";
	private String missionItemTextureHesWest = "img/buspass.png";


	private GameWorldData heslingtonWestData = new GameWorldData(missionItemCoordinatesHesWest, zombieCoordinatesHesWest, healthCoordinatesHesWest,
																 speedCoordinatesHesWest, shieldItemCoordinatesHesWest, doorCoordinatesHesWest, 
																 85,8, "maps/west.tmx",15,2,2,10,1,20,1,1,missionItemTextureHesWest, missionItemIDHesWest, 1);
	
	//Inside ComputerScience
	
	private int[][] zombieCoordinatesCompSci = new int[][] {{57,28}, {65,36}, {66,40}, {69,51}, {71,56}, {67,60}, {46,43}, {49,41}, {52,47}, {41,44}};
	private int[][] healthCoordinatesCompSci = new int[][] {{32,29}, {59,62}, {45,45}};
	private int[][] speedCoordinatesCompSci = new int[][] {{51,25}};
	private int[][] missionItemCoordinatesCompSci = new int[][] {{59,65}};
	private int[][] shieldItemCoordinatesCompSci = new int[][] {{54,38}};
	private int[][] doorCoordinatesCompSci = new int[][] {{45, 52}};
	
	//MissionItem parameters
	private String missionItemIDCompSci = "key";
	private String missionItemTextureCompSci = "img/key.png";
	
	private GameWorldData compSciData = new GameWorldData(missionItemCoordinatesCompSci, zombieCoordinatesCompSci, healthCoordinatesCompSci,
			 speedCoordinatesCompSci, shieldItemCoordinatesCompSci, doorCoordinatesCompSci, 
			 44,24, "maps/insidecs.tmx",10,3,1,1,1,20,1,1,missionItemTextureCompSci, missionItemIDCompSci, 2);
	
	
	
	
	
	
	
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

		addLevel(1, compSciData); 
		addLevel(2, heslingtonEastData);
		addLevel(3, heslingtonWestData);

		setScreen(new Menu(this));
	}
	
	public void addLevel (int levelNumber, GameWorldData worldData) {
		levels.put(levelNumber, worldData);
	}
	
	public void setLevel(int level) {
		
		String mapPath = levels.get(level).getMap();
		TiledMap map = loader.load(mapPath);
		player.setXSpawn(levels.get(level).getPlayerXPosition());
		player.setYSpawn(levels.get(level).getPlayerYPosition());
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
