package com.zombiegame.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import screens.GameWorld;
import sprites.Player;

public class SEPRZombieGame extends Game {
	
	@Override
	public void create () {
		//everything in here is just to test at the  moment
		
		//getter and setter for collisionLayer in player to adjust to collisions on different screens
		TmxMapLoader loader = new TmxMapLoader();
		TiledMap map = loader.load("maps/testmap.tmx"); //required by player and screen
		Player player = new Player(new Sprite(new Texture("img/player.png")), (TiledMapTileLayer) map.getLayers().get(0));
		//should probably load all sprites apart from player on screen
		setScreen(new GameWorld(map, player));
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
