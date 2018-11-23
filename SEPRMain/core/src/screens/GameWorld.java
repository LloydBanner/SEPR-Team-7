package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import sprites.Enemy;
import sprites.Player;
import sprites.items.HealthConsumable;
import sprites.items.Item;
import sprites.items.MissionItem;
import sprites.items.SpeedConsumable;
import sprites.items.Weapon;

public class GameWorld implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	
	private final int MAX_ENEMIES = 10;
	private final int MAX_HEALTH_ITEMS = 10;
	private final int MAX_SPEED_ITEMS = 10;
	private final int MAX_WEAPONS = 10;
	private final int MAX_MISSION_ITEMS = 10;
	
	private Enemy[] enemies = new Enemy[MAX_ENEMIES];
	private Weapon[] weapons = new Weapon[MAX_WEAPONS];
	private HealthConsumable[] healthItems = new HealthConsumable[MAX_HEALTH_ITEMS];
	private SpeedConsumable[] speedItems = new SpeedConsumable[MAX_SPEED_ITEMS];
	private MissionItem[] missionItems = new MissionItem[MAX_MISSION_ITEMS];
	
	private boolean paused = false;
	
	public GameWorld(TiledMap map) {
		//set screen to loaded map
		this.map = map;
		player = new Player(new Sprite(new Texture("img/player.png")), (TiledMapTileLayer) map.getLayers().get(0));

		
		TmxMapLoader loader = new TmxMapLoader();
		showEnemies(enemies);
		showItems();
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
	
	public void showEnemies(Enemy[] enemies) {
		
		for (int i=0; i < enemies.length; i++) {
			
			enemies[i] = new Enemy(new Sprite(new Texture("img/zombie.png")),
								  (TiledMapTileLayer) map.getLayers().get(0), 
								  this.player);
			
			enemies[i].setPosition(55 * enemies[i].getCollisionLayer().getTileWidth(), 
								   21 * enemies[i].getCollisionLayer().getTileHeight());
		}
		
	}
	
	public void renderEnemies(Enemy[] enemies, Batch batch) {
		
		for (int i=0; i < enemies.length; i++) {
			enemies[i].draw(batch);
		}	
	}
	
	public void disposeEnemies(Enemy[] enemies) {
		
		for (Enemy enemy : enemies) {
			enemy.getTexture().dispose();
		}
	}
	
	public void showWeapons(Weapon[] weapons) {
		
		for (int i=0; i < weapons.length; i++ ) {
			
			weapons[i] = new Weapon(new Sprite(new Texture("img/zombie.png")), 
								   (TiledMapTileLayer) map.getLayers().get(0), 5, 7);
	
			weapons[i].setPosition(44 * weapons[i].getCollisionLayer().getTileWidth(),
					 			   29 * weapons[i].getCollisionLayer().getTileHeight());
			
		}
	}
	
	public void showHealthItems(HealthConsumable[] healthItems) {
		
		for (int i=0; i < healthItems.length; i++ ) {
			
			healthItems[i] = new HealthConsumable(new Sprite(new Texture("img/zombie.png")), 
												 (TiledMapTileLayer) map.getLayers().get(0), 5);
	
			healthItems[i].setPosition(44 * healthItems[i].getCollisionLayer().getTileWidth(),
					 			   	   29 * healthItems[i].getCollisionLayer().getTileHeight());
			
		}
	}
	
	public void showSpeedItems(SpeedConsumable[] speedItems) {
		
		for (int i=0; i < speedItems.length; i++ ) {
			
			speedItems[i] = new SpeedConsumable(new Sprite(new Texture("img/zombie.png")), 
											   (TiledMapTileLayer) map.getLayers().get(0), 5);
	
			speedItems[i].setPosition(44 * speedItems[i].getCollisionLayer().getTileWidth(),
					 			   	  29 * speedItems[i].getCollisionLayer().getTileHeight());
			
		}
	}
	
	public void showMissionItems(MissionItem[] missionItems) {
		
		for (int i=0; i < missionItems.length; i++ ) {
			
			missionItems[i] = new MissionItem(new Sprite(new Texture("img/zombie.png")), 
											   (TiledMapTileLayer) map.getLayers().get(0), 
											   "some test mission item");
	
			missionItems[i].setPosition(44 * missionItems[i].getCollisionLayer().getTileWidth(),
					 			   	    29 * missionItems[i].getCollisionLayer().getTileHeight());
			
		}
	}
	
	public void showItems() {
		
			showWeapons(weapons);
			showHealthItems(healthItems);
			showSpeedItems(speedItems);
			showMissionItems(missionItems);
			
	}
	
	public void renderItems(Item[] items, Batch batch) {
		
		for (Item item : items) {
			item.draw(batch);
		} 
	}
	
	public void renderItems(Batch batch) {
		
		renderItems(weapons, batch);
		renderItems(healthItems, batch);
		renderItems(speedItems, batch);
		renderItems(missionItems, batch);
	
	}
	
	public void disposeItems(Item[] items) {
		
		for (Item item : items) {
			
			item.dispose();
		}
	}
	
	public void disposeItems() {
		
		disposeItems(weapons);
		disposeItems(healthItems);
		disposeItems(speedItems);
		disposeItems(missionItems);
		
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
		
		// render enemies
		renderEnemies(enemies, renderer.getBatch());
		
		// render items
		renderItems(renderer.getBatch());
		
				
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
		
		disposeEnemies(enemies);
		disposeItems();
	}

}
