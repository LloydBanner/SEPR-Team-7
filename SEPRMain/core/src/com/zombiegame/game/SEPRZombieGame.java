package com.zombiegame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import screens.GameWorld;
import sprites.Player;

public class SEPRZombieGame extends Game {
	
	@Override
	public void create () {

		//need better way to load map to player for collisions
		//need getter and setter for collision layer player to adjust to collisions on different screens
		TmxMapLoader loader = new TmxMapLoader();
		TiledMap map = loader.load("maps/testmap.tmx");
		Player player = new Player(new Sprite(new Texture("img/player.png")), (TiledMapTileLayer) map.getLayers().get(0));
		setScreen(new GameWorld("maps/testmap.tmx", player));
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
