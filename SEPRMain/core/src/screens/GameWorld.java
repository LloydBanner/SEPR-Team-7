package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import helpers.GameWorldData;
import sprites.Enemy;
import sprites.Player;
import sprites.items.Door;
import sprites.items.HealthConsumable;
import sprites.items.Item;
import sprites.items.MissionItem;
import sprites.items.ShieldConsumable;
import sprites.items.SpeedConsumable;
import sprites.items.Weapon;

public class GameWorld implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	
	private SpriteBatch uiRenderer;
	private Texture healthFull;
	private Texture healthThreeQuarters;
	private Texture healthHalf;
	private Texture healthQuarter;
	private Texture healthEmpty;
	private Texture healthFlash;
	private final int healthX = 50;
	private final int healthY = 600;
	private final int healthSize = 120;
	
	private Texture exitActive;
	private Texture exitInactive;
	private Texture continueActive;
	private Texture continueInactive;
	private Texture controlsActive;
	private Texture controlsInactive;
	private Texture backActive;
	private Texture backInactive;
	private Texture win;
	private int buttonSize = 250;
	private int buttonX = 500;
	private int exitY = 10;
	private int backY = 10;
	private int controlsY = 225;
	private int continueY = 460;
	private int winX = 125;
	private int winY = 1;
	private int winWidth = 1000;
	private int winHeight = 800;
	
	private Enemy[] enemies;
	private Weapon[] weapons;
	private HealthConsumable[] healthItems;
	private SpeedConsumable[] speedItems;
	private ShieldConsumable[] shieldItems;
	private MissionItem[] missionItems;
	private Door[] doors;
	private int playerXPosition;
	private int playerYPosition;
	
	private boolean paused = false;
	private boolean showingControls = false;
	private boolean hasWon = false;
	private boolean menuCooldown = true;
	private float timeCount = 0;
	
	public GameWorld(TiledMap map, GameWorldData levelData, Player player) {
		//set screen to loaded map
		this.map = map;
		this.player = player;
		
		player.setCollisionLayer((TiledMapTileLayer) map.getLayers().get(0));

		enemies = levelData.getEnemiesList();
		weapons = levelData.getWeaponsList();
		healthItems = levelData.getHealthItemList();
		speedItems = levelData.getSpeedItemList();
		missionItems = levelData.getMissionItemList();
		doors = levelData.getDoorItemList();
		playerXPosition = levelData.getXPosition();
		playerYPosition = levelData.getYPosition();
		//need to change this
		shieldItems = new ShieldConsumable[5];
		
		
		uiRenderer = new SpriteBatch();
		healthFull = new Texture("img/healthfull.png");
		healthThreeQuarters = new Texture("img/healththreequarters.png");
		healthHalf = new Texture("img/healthhalf.png");
		healthQuarter = new Texture("img/healthquarter.png");
		healthEmpty = new Texture("img/healthfinal.png");
		healthFlash = new Texture("img/healthflash.png");
		
		exitActive = new Texture("img/exit2.png");
		exitInactive = new Texture("img/exit1.png");
		//switch play with continue texture
		continueActive = new Texture("img/play2.png");
		continueInactive = new Texture("img/play1.png");
		controlsActive = new Texture("img/controls2.png");
		controlsInactive = new Texture("img/controls1.png");
		backActive = new Texture("img/back2.png");
		backInactive = new Texture("img/back1.png");
		win = new Texture("img/player.png");
		
		// TmxMapLoader loader = new TmxMapLoader(); 
		createEnemies(enemies);
		createItems();
		setSpriteCollisions();
	}
	
	/**
	 * Gives each character the information they need to be able to collide with each other.
	 */
	private void setSpriteCollisions() {
		for(Enemy enemy : enemies) {
			enemy.setSpriteCollisions(player, enemies);
		}
		player.setSpriteCollisions(player, enemies);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	

	@Override
	public void show() {
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();
		
		//tile map position x across, y down
		player.setPosition(playerXPosition * player.getCollisionLayer().getTileWidth(), playerYPosition * player.getCollisionLayer().getTileHeight());
		//apply inputs to player
		Gdx.input.setInputProcessor(player);
	}
	
	public void createEnemies(Enemy[] enemies) {
		
		int pos = 20;
		
		for (int i=0; i < enemies.length; i++) {
			
			pos += 2;
			
			enemies[i] = new Enemy(new Sprite(new Texture("img/zombie.png")),
								  (TiledMapTileLayer) map.getLayers().get(0), 
								  this.player);
			
			enemies[i].setPosition(55 * enemies[i].getCollisionLayer().getTileWidth(), 
								   pos * enemies[i].getCollisionLayer().getTileHeight());
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
	
	public void createWeapons(Weapon[] weapons) {
		
		for (int i=0; i < weapons.length; i++ ) {
			
			weapons[i] = new Weapon(new Sprite(new Texture("img/play2.png")), 
								   (TiledMapTileLayer) map.getLayers().get(0), 5, 7, player);
	
			weapons[i].setPosition(48 * weapons[i].getCollisionLayer().getTileWidth(),
					 			   29 * weapons[i].getCollisionLayer().getTileHeight());
			
		}
	}
	
	public void createHealthItems(HealthConsumable[] healthItems) {
		
		for (int i=0; i < healthItems.length; i++ ) {
			
			healthItems[i] = new HealthConsumable((TiledMapTileLayer) map.getLayers().get(0), 2, player);
	
			healthItems[i].setPosition(40 * healthItems[i].getCollisionLayer().getTileWidth(),
					 			   	   29 * healthItems[i].getCollisionLayer().getTileHeight());
			
		}
	}
	
	public void createSpeedItems(SpeedConsumable[] speedItems) {
		
		for (int i=0; i < speedItems.length; i++ ) {
			
			speedItems[i] = new SpeedConsumable((TiledMapTileLayer) map.getLayers().get(0), 20, player);
	
			speedItems[i].setPosition(44 * speedItems[i].getCollisionLayer().getTileWidth(),
					 			   	  29 * speedItems[i].getCollisionLayer().getTileHeight());
			
		}
	}
	
	public void createShieldItems(ShieldConsumable[] shieldItems) {
		
		for (int i=0; i < shieldItems.length; i++ ) {
			
			shieldItems[i] = new ShieldConsumable((TiledMapTileLayer) map.getLayers().get(0), player);
	
			shieldItems[i].setPosition(44 * shieldItems[i].getCollisionLayer().getTileWidth(),
					 			   	  25 * shieldItems[i].getCollisionLayer().getTileHeight());
			
		}
	}
	
	public void createMissionItems(MissionItem[] missionItems) {
		
		for (int i=0; i < missionItems.length; i++ ) {
			
			missionItems[i] = new MissionItem(new Sprite(new Texture("img/buspass.png")), 
											   (TiledMapTileLayer) map.getLayers().get(0), 
											   "win condition", player);
	
			missionItems[i].setPosition(52 * missionItems[i].getCollisionLayer().getTileWidth(),
					 			   	    29 * missionItems[i].getCollisionLayer().getTileHeight());
			
		}
	}
	
	public void createDoors(Door[] doors) {
		
		for (int i=0; i < doors.length; i++) {
		
			doors[i] = new Door(new Sprite(new Texture("img/player.png")), 2, 
							   (TiledMapTileLayer) map.getLayers().get(0), player,
								this.getPlayer().getGame(), missionItems[0]);
							   
			
			doors[i].setPosition(52 * doors[i].getCollisionLayer().getTileWidth(),
							 29 * doors[i].getCollisionLayer().getTileHeight());
		
		}
	}
	
	public void createItems() {
		
			createWeapons(weapons);
			createHealthItems(healthItems);
			createSpeedItems(speedItems);
			createMissionItems(missionItems);
			createShieldItems(shieldItems);
			createDoors(doors);
			
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
		renderItems(doors, batch);
	
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
		disposeItems(doors);
		
	}
	
	@Override
	public void render(float delta) {
		if (player.isEscPressed()) {
			pause();
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		renderer.setView(camera);
		
		//updates render for animated tiles
		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
			
		renderer.render();
			
		//rendering for main game
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
			
		uiRenderer.begin();
		//rendering for ui
		renderUI();
		uiRenderer.end();
		
		timeCount += delta;
		if (timeCount > 1) {
			timeCount = 0;
			menuCooldown = false;
		}
		if (!hasWon) {
			if (player.hasMissionItem(new MissionItem(new Sprite(new Texture("img/buspass.png")), 
														(TiledMapTileLayer) map.getLayers().get(0), 
														"win condition", player))) {
				hasWon = true;
				this.pause();
			}
		}
		
		if (paused) {
			if (hasWon) {
				//shows controls in pause menu
				uiRenderer.begin();
				//rendering for ui
				renderUI();
				//renders win screen
				renderWin();
				uiRenderer.end();
			}else if (showingControls) {
				//shows controls in pause menu
				uiRenderer.begin();
				//rendering for ui
				renderUI();
				//rendering for controls menu
				renderControls();
				uiRenderer.end();
			} else {
				uiRenderer.begin();
				//rendering for ui
				renderUI();
				//rendering for pause menu
				renderPause();
				uiRenderer.end();
			}
		}
		
	}
	
	private void renderUI() {
		//health bar
		if(player.isHealthChange()) {
			uiRenderer.draw(healthFlash, healthX, healthY, healthSize, healthSize);	
		}else if(player.getHealth() == 4) {
			uiRenderer.draw(healthFull, healthX, healthY, healthSize, healthSize);	
		}else if(player.getHealth() == 3) {
			uiRenderer.draw(healthThreeQuarters, healthX, healthY, healthSize, healthSize);	
		}else if(player.getHealth() == 2) {
			uiRenderer.draw(healthHalf, healthX, healthY, healthSize, healthSize);	
		}else if(player.getHealth() == 1) {
			uiRenderer.draw(healthQuarter, healthX, healthY, healthSize, healthSize);	
		}else if(player.getHealth() == 0) {
			uiRenderer.draw(healthEmpty, healthX, healthY, healthSize, healthSize);	
		}
		
		//uiRenderer.draw(new Texture("img/zombie_n_skeleton2.png"), 450, 300);
	}
	
	private void renderPause() {
		//for menu		
		if (withinButton(exitY)) {
			uiRenderer.draw(exitActive, buttonX, exitY, buttonSize, buttonSize);
			if (Gdx.input.isTouched() && !menuCooldown) {
				menuCooldown = true;
				Gdx.app.exit();
			}
		}else {
			uiRenderer.draw(exitInactive, buttonX, exitY, buttonSize, buttonSize);
		}
		if (withinButton(continueY)) {
			uiRenderer.draw(continueActive, buttonX, continueY, buttonSize, buttonSize);
			if (Gdx.input.isTouched() && !menuCooldown) {
				menuCooldown = true;
				pause();
		}
		}else {
			uiRenderer.draw(continueInactive, buttonX, continueY, buttonSize, buttonSize);
		}
		if (withinButton(controlsY)) {
			uiRenderer.draw(controlsActive, buttonX, controlsY, buttonSize, buttonSize);
			if (Gdx.input.isTouched() && !menuCooldown) {
				menuCooldown = true;
				showingControls = true;
		}
		}else {
			uiRenderer.draw(controlsInactive, buttonX, controlsY, buttonSize, buttonSize);
		}
	}
	
	public void renderControls() {
		//Separate pause screen
		if (withinButton(backY)) {
			uiRenderer.draw(backActive, buttonX, exitY, buttonSize, buttonSize);
			if (Gdx.input.isTouched() && !menuCooldown) {
				menuCooldown = true;
				showingControls = false;
			}
		}else {
			uiRenderer.draw(backInactive, buttonX, exitY, buttonSize, buttonSize);
		}
		//need to add image with controls as another texture
	}
	
	public void renderWin() {
		uiRenderer.draw(win, winX, winY, winWidth, winHeight);
	}
	
	public boolean withinButton(int buttonY) {
		//720 is screen height
		//20s used to make hit box better
		if(Gdx.input.getX() < buttonX + buttonSize) {
			if(Gdx.input.getX() > buttonX) {
				if((720 - Gdx.input.getY()) < buttonY + buttonSize - 20) {
					if((720 - Gdx.input.getY()) > buttonY + 20) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void resize(int width, int height) {
		//different divisions used to zoom
		camera.viewportWidth = width / 2;
		camera.viewportHeight = height / 2;
	}

	@Override
	public void pause() {
		paused = !paused;
		player.togglePaused();
		togglePauseEnemies();	
	}
	
	private void togglePauseEnemies() {
		for (Enemy enemy : enemies) {
			enemy.togglePaused();
		}
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
		
		player.getTexture().dispose();
		
		disposeEnemies(enemies);
		disposeItems();
	}

}
