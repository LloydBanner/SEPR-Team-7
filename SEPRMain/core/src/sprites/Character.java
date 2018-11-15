package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends Sprite {
	
	private int health;
	private int damage;
	private int speed = 60 * 2;
	private int range;
	
	//for movement
	private Vector2 velocity = new Vector2();
	//for collisions
	private TiledMapTileLayer collisionLayer;
	
	public Character(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}

	public void update(float delta) {
		// old position
		float oldX = getX(), oldY = getY();

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
	
	//for checking tiles, similar code could be used to check for other properties 
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

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public void setVelocityY(float y) {
		this.velocity.y = y;
	}
	
	public void setVelocityX(float x) {
		this.velocity.x = x;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
}
