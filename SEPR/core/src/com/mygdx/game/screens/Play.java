package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.entities.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Play implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	private Player player;
	
	private boolean paused = false;
	
	@Override
	public void show() {
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("maps/testmap.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();
		//camera.translate(2000, 2000);
		
		player = new Player(new Sprite(new Texture("img/player.png")), (TiledMapTileLayer) map.getLayers().get(0));
		//tile map position x across, y down
		player.setPosition(69 * player.getCollisionLayer().getTileWidth(), 21 * player.getCollisionLayer().getTileHeight());
		//apply inputs to player
		Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		//for camera movement independent of player
		//handleInput();
		//camera.update();
		
		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
		
		renderer.setView(camera);
		
		//updates render for animated tiles
		if(!paused) {
			renderer.render();
		}
		
		renderer.getBatch().begin();
		//render background
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Background"));
		//render player
		player.draw(renderer.getBatch());
		//render foreground
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Foreground"));
		renderer.getBatch().end();
		
	}

	//move camera with arrows
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.translate(-3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.translate(3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.translate(0, -3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.translate(0, 3, 0);
		}
	}
	
	//zoom
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 2;
		camera.viewportHeight = height / 2;

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void dispose() {
		map.dispose();
		
		renderer.dispose();
		
		player.getTexture().dispose();
	}

}
