package sprites;

import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player extends Character implements InputProcessor {
	
	private HashMap<Item, Integer> inventory;
	private Collection<String> missionItems;
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.setCollisionLayer(collisionLayer);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		case Keys.W:
			setVelocityY(getSpeed());
			break;
		case Keys.S:
			setVelocityY(-getSpeed());
			break;
		case Keys.A:
			setVelocityX(-getSpeed());
			break;
		case Keys.D:
			setVelocityX(getSpeed());
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
		case Keys.A:
			if(!Gdx.input.isKeyPressed(Input.Keys.D)) {
				setVelocityX(0);
			}
			break;
		case Keys.D:
			if(!Gdx.input.isKeyPressed(Input.Keys.A)) {
				setVelocityX(0);
			}
			break;
		case Keys.W:
			if(!Gdx.input.isKeyPressed(Input.Keys.S)) {
				setVelocityY(0);
			}
			break;
		case Keys.S:
			if(!Gdx.input.isKeyPressed(Input.Keys.W)) {
				setVelocityY(0);
			}
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	



}
