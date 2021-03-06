package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombiegame.game.SEPRZombieGame;

import sprites.DramaPlayer;
import sprites.SportPlayer;

public class Menu implements Screen {
	
	private SpriteBatch renderer;
	private SEPRZombieGame game;
	
	private Texture exitActive;
	private Texture exitInactive;
	private Texture playActive;
	private Texture playInactive;
	private Texture dramaActive;
	private Texture dramaInactive;
	private Texture sportActive;
	private Texture sportInactive;
	
	private int buttonSize = 250;
	private int buttonX = 500;
	private int exitY = 100;
	private int playY = 350;
	
	private String menuType = "main";
	private boolean menuCooldown = false;
	private int timer = 0;
	
	public Menu(SEPRZombieGame game) {
		renderer = new SpriteBatch();
		exitActive = new Texture("img/exit2.png");
		exitInactive = new Texture("img/exit1.png");
		playActive = new Texture("img/play2.png");
		playInactive = new Texture("img/play1.png");
		dramaActive = new Texture("img/dramaactive.png");
		dramaInactive = new Texture("img/dramainactive.png");
		sportActive = new Texture("img/sportactive.png");
		sportInactive = new Texture("img/sportinactive.png");
		this.game = game;
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		renderer.begin();
		
		
		if (menuType == "main") {
			renderMainMenu();
		} else if(menuType == "class") {
			renderClassMenu();
		}
		
		renderer.end();
		timer += 1;
		if (timer >= 100) {
			menuCooldown = false;
			timer = 0;
		}
	}
	
	public void renderMainMenu() {
		if (withinButton(exitY)) {
			renderer.draw(exitActive, buttonX, exitY, buttonSize, buttonSize);
			if (Gdx.input.isTouched() && !menuCooldown) {
				menuCooldown = true;
				Gdx.app.exit();
			}
		}else {
			renderer.draw(exitInactive, buttonX, exitY, buttonSize, buttonSize);
		}
		if (withinButton(playY)) {
			renderer.draw(playActive, buttonX, playY, buttonSize, buttonSize);
			if (Gdx.input.isTouched() && !menuCooldown) {
				menuCooldown = true;
				menuType = "class";
			}
		}else {
			renderer.draw(playInactive, buttonX, playY, buttonSize, buttonSize);
		}	
	}
	
	public void renderClassMenu() {
		if (withinButton(exitY)) {
			renderer.draw(dramaActive, buttonX, exitY, buttonSize, buttonSize);
			if (Gdx.input.isTouched() && !menuCooldown) {
				menuCooldown = true;
				game.setPlayer(new DramaPlayer(new Sprite(new Texture("img/player.png")), game));
				this.game.setLevel(1);
			}
		}else {
			renderer.draw(dramaInactive, buttonX, exitY, buttonSize, buttonSize);
		}
		if (withinButton(playY)) {
			renderer.draw(sportActive, buttonX, playY, buttonSize, buttonSize);
			if (Gdx.input.isTouched() && !menuCooldown) {
				menuCooldown = true;
				game.setPlayer(new SportPlayer(new Sprite(new Texture("img/player.png")), game));
				this.game.setLevel(1);
			}
		}else {
			renderer.draw(sportInactive, buttonX, playY, buttonSize, buttonSize);
		}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}

}
