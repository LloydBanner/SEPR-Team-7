package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {

	// movement velocity
	private Vector2 velocity = new Vector2();
	private float speed = 60 * 2, gravity = 60 * 1.8f;

	private TiledMapTileLayer collisionLayer;

	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.collisionLayer = collisionLayer;

	}

	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}

	public void update(float delta) {
		// apply gravity
		//velocity.y -= gravity * delta;

		// clamp velocity
		//if (velocity.y > speed) {
		//	velocity.y = speed;
		//} else if (velocity.y <= speed) {
		//	velocity.y = -speed;
		//}

		// old position
		float oldX = getX(), oldY = getY();
		// get tile height and width
		float tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();

		// collisions
		boolean collisionX = false, collisionY = false;

		// movement x
		setX(getX() + velocity.x * delta);

		// check for x collision
		if (velocity.x < 0) {
			collisionX = collidesLeft();
		} else if (velocity.x > 0) {
			collisionX = collidesRight();
		}

		// react to x collision
		if (collisionX) {
			setX(oldX);
			velocity.x = 0;
		}

		// movement y
		setY(getY() + velocity.y * delta);

		// check for y collision
		if (velocity.y < 0) {
			collisionY = collidesBottom();
		} else if (velocity.y > 0) {
			collisionY = collidesTop();
		}

		// react to y collision
		if (collisionY) {
			setY(oldY);
			velocity.y = 0;
		}
	}
	
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
	}
	
	public boolean collidesRight() {
        for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
                if(isCellBlocked(getX() + getWidth(), getY() + step))
                        return true;
        return false;
	}
 
	public boolean collidesLeft() {
        for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
                if(isCellBlocked(getX(), getY() + step))
                        return true;
        return false;
	}
 
	public boolean collidesTop() {
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
                if(isCellBlocked(getX() + step, getY() + getHeight()))
                        return true;
        return false;
	}
 
	public boolean collidesBottom() {
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
                if(isCellBlocked(getX() + step, getY()))
                        return true;
        return false;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		case Keys.W:
			velocity.y = speed;
			break;
		case Keys.S:
			velocity.y = -speed;
			break;
		case Keys.A:
			velocity.x = -speed;
			break;
		case Keys.D:
			velocity.x = speed;
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
		case Keys.A:
		case Keys.D:
			velocity.x = 0;
		case Keys.W:
		case Keys.S:
			velocity.y = 0;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
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
