package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import sprites.Enemy;
import sprites.Player;

public class GameWorld implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	private boolean paused = false;
	
	public GameWorld(TiledMap map, Player player) {
		//set screen to loaded map
		this.map = map;
		
		//transfer player data
		this.player = player;
	}

	@Override
	public void show() {
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();
		
		//tile map position x across, y down
		player.setPosition(69 * player.getCollisionLayer().getTileWidth(), 21 * player.getCollisionLayer().getTileHeight());
		//apply inputs to player
		Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
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

	@Override
	public void resize(int width, int height) {
		//different divisions used to zoom
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
