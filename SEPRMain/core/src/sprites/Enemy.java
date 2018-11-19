package sprites;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;


public class Enemy extends Character{
	
	private boolean isAttacking;
	private Vector2 velocity = new Vector2();
	private float speed = 10, gravity = 60 * 1.8f;
	private float timeCount;
	private TiledMapTileLayer collisionLayer;

	public Enemy(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.collisionLayer = collisionLayer;
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
		
		timeCount += delta;
		if(timeCount > 2) {
			timeCount = 0;
			randomMove();
		}		
	}
	
	private void randomMove() {
		Random rand = new Random();
		int direction = rand.nextInt(10);
		float speedMod = speed + rand.nextInt(21);
		// Change Direction based on RNG
		switch (direction) {
			// Up
			case 0: velocity.y = speedMod;		
					velocity.x = 0;	
					break;
			// Down
			case 1: velocity.y = -speedMod;		
					velocity.x = 0;	
					break;
			// Right
			case 2: velocity.y = 0;		
					velocity.x = speedMod;	
					break;
			// Left
			case 3: velocity.y = 0;		
					velocity.x = -speedMod;	
					break;
			// Up-Right
			case 4: velocity.y = speedMod;		
					velocity.x = speedMod;	
					break;
			// Down-Right
			case 5: velocity.y = -speedMod;		
					velocity.x = speedMod;	
					break;
			// Up-Left
			case 6: velocity.y = speedMod;		
					velocity.x = -speedMod;	
					break;
			// Down-Left
			case 7: velocity.y = -speedMod;		
					velocity.x = -speedMod;	
					break;
			// Stop
			default: velocity.y = 0;
					 velocity.x = 0;
					 break;
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
	
	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
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

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
}
