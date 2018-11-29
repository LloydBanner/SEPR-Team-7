package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends Sprite {
	
	private int health = 4;
	private int maxHealth = 4;
	private int minHealth = 0;
	private boolean healthChange = false;
	private int damage;
	private int speed = 60 * 2;
	private int range;
	private boolean paused = false;
	
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
		}
	}
	
	//for checking tiles, similar code could be used to check for other properties 
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
	}
	
	public boolean collidesRight() {
		//collision check starts a quarter of the way in to the highest block it can collide with
		//checks every quarter of a block for collision
        for(float step = (collisionLayer.getTileHeight() / 4); step < getHeight(); step += collisionLayer.getTileHeight() / 2)
                if(isCellBlocked(getX() + getWidth(), getY() + step))
                        return true;
        return false;
	}
 
	public boolean collidesLeft() {
        for(float step = (collisionLayer.getTileHeight() / 4); step < getHeight(); step += collisionLayer.getTileHeight() / 2)
                if(isCellBlocked(getX(), getY() + step))
                        return true;
        return false;
	}
 
	public boolean collidesTop() {
        for(float step = (collisionLayer.getTileWidth() / 4); step < getWidth(); step += collisionLayer.getTileWidth() / 2)
                if(isCellBlocked(getX() + step, getY() + getHeight()))
                        return true;
        return false;
	}
 
	public boolean collidesBottom() {
        for(float step = (collisionLayer.getTileWidth() / 4); step < getWidth(); step += collisionLayer.getTileWidth() / 2)
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

	public void increaseSpeed(int speed) {
		this.speed += speed;
	}
	
	public void decreaseSpeed(int speed) {
		this.speed -= speed;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void increaseHealth(int health) {
		int newHealth = this.health += health;
		if(newHealth <= maxHealth) {
			this.health = newHealth;
		} else {
			this.health = maxHealth;
		}
		healthChange = true;
	}
	
	public void decreaseHealth(int health) {
		int newHealth = this.health -= health;
		if(newHealth >= minHealth) {
			this.health = newHealth;
		} else {
			this.health = minHealth;
		}
		healthChange = true;
	}
	
	public boolean isHealthChange() {
		boolean change = healthChange;
		healthChange = false;
		return change;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void increaseDamage(int damage) {
		this.damage += damage;
	}
	
	public void decreaseDamage(int damage) {
		this.damage -= damage;
	}
	
	public void togglePaused() {
		this.paused = !paused;
		setVelocityX(0);
		setVelocityY(0);
	}
	
	public boolean isPaused() {
		return paused;
	}
}
