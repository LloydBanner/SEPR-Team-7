package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
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
	private int damage = 1;
	private int speed = 60 * 2;
	private int baseSpeed = 60 * 2;
	private int range;
	private boolean paused = false;
	private Sound hitSFX, missSFX;
	
	//for movement
	private Vector2 velocity = new Vector2();
	//for collisions with tile map
	private TiledMapTileLayer collisionLayer;
	private Player player;
	private Enemy[] enemies;

	private int animationFrame = 1;
	private float timeSinceLastAnimation;
	private Texture neturalAnimation;
	private Texture upAnimation1;
	private Texture upAnimation2;
	private Texture downAnimation1;
	private Texture downAnimation2;
	private Texture leftAnimation1;
	private Texture leftAnimation2;
	private Texture rightAnimation1;
	private Texture rightAnimation2;
	private Texture upLeftAnimation1;
	private Texture upLeftAnimation2;
	private Texture downLeftAnimation1;
	private Texture downLeftAnimation2;
	private Texture upRightAnimation1;
	private Texture upRightAnimation2;
	private Texture downRightAnimation1;
	private Texture downRightAnimation2;
	
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
		}else if (velocity.x > 0) {
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
		}else if (velocity.y > 0) {
			collisionY = collidesTop();
		}

		// react to y collision
		if (collisionY) {
			setY(oldY);
		}
		
		//animations
		animate(delta);
	}
	
	public void animate(float delta) {
		timeSinceLastAnimation += delta;
		if (animationFrame > 2) {
			animationFrame = 1;
		}
		if (velocity.y > 0.5) {
			if (velocity.x > 0.5) {
				if (animationFrame == 1) {
					this.setTexture(getUpRightAnimation1());
				}else {
					this.setTexture(getUpRightAnimation2());
				}
			}else if (velocity.x < -0.5) {
				if (animationFrame == 1) {
					this.setTexture(getUpLeftAnimation1());
				}else {
					this.setTexture(getUpLeftAnimation2());
				}
			}else {
				if (animationFrame == 1) {
					this.setTexture(getUpAnimation1());
				}else {
					this.setTexture(getUpAnimation2());
				}
			}
		}else if (velocity.y < -0.5) {
			if (velocity.x > 0.5) {
				if (animationFrame == 1) {
					this.setTexture(getDownRightAnimation1());
				}else {
					this.setTexture(getDownRightAnimation2());
				}
			}else if (velocity.x < -0.5) {
				if (animationFrame == 1) {
					this.setTexture(getDownLeftAnimation1());
				}else {
					this.setTexture(getDownLeftAnimation2());
				}
			}else {
				if (animationFrame == 1) {
					this.setTexture(getDownAnimation1());
				}else {
					this.setTexture(getDownAnimation2());
				}
			}
		}else {
			if (velocity.x > 0.5) {
				if (animationFrame == 1) {
					this.setTexture(getRightAnimation1());
				}else {
					this.setTexture(getRightAnimation2());
				}
			}else if (velocity.x < -0.5) {
				if (animationFrame == 1) {
					this.setTexture(getLeftAnimation1());
				}else {
					this.setTexture(getLeftAnimation2());
				}
			}else {
				this.setTexture(getNeturalAnimation());
			}	
		}
		if (timeSinceLastAnimation >= 0.2) {
			animationFrame += 1;
			timeSinceLastAnimation = 0;
		}
	}
	
	/**
	 * Sets information about other characters need for collisions with this character.
	 * @param player
	 * @param enemies
	 */
	public void setSpriteCollisions(Player player, Enemy[] enemies) {
		this.player = player;
		this.enemies = enemies;
	}
	
	/**
	 * Checks for character collisions to the right.
	 * @param character
	 * @return boolean. True if there is a collision with another character to the right.
	 */
	public boolean collisionCharacterRight(Character character) {
        if (5 < ((character.getX() + (character.getWidth() / 2)) - (getX() + (getWidth() / 2)))) {
        	if (((character.getX() + (character.getWidth() / 2)) - (getX() + (getWidth() / 2))) < 25) {
        		if (Math.abs((character.getY() + (character.getHeight() / 2)) - (getY() + (getHeight() / 2))) < 25) {
        			return true;
        		}   
        	}
        }
        return false;
	}
	
	/**
	 * Checks for character collisions to the left.
	 * @param character
	 * @return boolean. True if there is a collision with another character to the left.
	 */
	public boolean collisionCharacterLeft(Character character) {
        if (-5 > ((character.getX() + (character.getWidth() / 2)) - (getX() + (getWidth() / 2)))) {
        	if (((character.getX() + (character.getWidth() / 2)) - (getX() + (getWidth() / 2))) > -25) {
        		if (Math.abs((character.getY() + (character.getHeight() / 2)) - (getY() + (getHeight() / 2))) < 25) {
        			return true;    
        		}    
        	}
        }
        return false;
	}
	
	/**
	 * Checks for character collisions above.
	 * @param character
	 * @return boolean. True if there is a collision with another character above this character.
	 */
	public boolean collisionCharacterTop(Character character) {
        if (5 < ((character.getY() + (character.getHeight() / 2)) - (getY() + (getHeight() / 2)))) {
            if (((character.getY() + (character.getHeight() / 2)) - (getY() + (getHeight() / 2))) < 25) {
            	if (Math.abs((character.getX() + (character.getWidth() / 2)) - (getX() + (getWidth() / 2))) < 25) {
                	return true;  
            	}
            }     	
        }
        return false;
	}
	
	/**
	 * Checks for character collisions below.
	 * @param character
	 * @return boolean. True if there is a collision with another character below this character.
	 */
	public boolean collisionCharacterBottom(Character character) {
        if (-5 > ((character.getY() + (character.getHeight() / 2)) - (getY() + (getHeight() / 2)))) {
        	if (((character.getY() + (character.getHeight() / 2)) - (getY() + (getHeight() / 2))) > -25) {
        		if (Math.abs((character.getX() + (character.getWidth() / 2)) - (getX() + (getWidth() / 2))) < 25) {
        			return true;
        		}
        	}
        }
        return false;
	}
	
	//for checking tiles, similar code could be used to check for other properties 
	/**
	 * Checks if the cell is blocked to allow the character to collide with blocked tiles.
	 * @param x coordinate
	 * @param y coordinate
	 * @return boolean. True if tile at the coordinate (x, y) has been assigned the sting "blocked".
	 */
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
	}
	
	/**
	 * Checks for tile or character collisions to the right using collisionCharacterRight() and isCellBlocked() methods.
	 * @return boolean. True if there is any type of collision to the right.
	 */
	public boolean collidesRight() {
		//collision check starts a quarter of the way in to the first tile and end a three quarters of the way through the last tile
		//checks every quarter of a tile for a collision
        for (float step = (collisionLayer.getTileHeight() / 4); step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
                if (isCellBlocked(getX() + getWidth(), getY() + step)) {
                        return true;
                }
        }
		if (player != this) {
			if (collisionCharacterRight(player)) {
				return true;
			}
		}
		for (Enemy enemy : enemies) {
			if (enemy != this) {
				if (collisionCharacterRight(enemy)) {
					return true;
				}
			}
		}
        return false;
	}
 
	/**
	 * Checks for tile or character collisions to the left using collisionCharacterLeft() and isCellBlocked() methods.
	 * @return boolean. True if there is any type of collision to the left.
	 */
	public boolean collidesLeft() {
        for (float step = (collisionLayer.getTileHeight() / 4); step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
                if (isCellBlocked(getX(), getY() + step)) {
                        return true;
                }
        }
		if (player != this) {
			if (collisionCharacterLeft(player)) {
				return true;
			}
		}
		for (Enemy enemy : enemies) {
			if (enemy != this) {
				if (collisionCharacterLeft(enemy)) {
					return true;
				}
			}
		}
        return false;
	}
 
	/**
	 * Checks for tile or character collisions above using collisionCharacterTop() and isCellBlocked() methods.
	 * @return boolean. True if there is any type of collision above.
	 */
	public boolean collidesTop() {
        for (float step = (collisionLayer.getTileWidth() / 4); step < getWidth(); step += collisionLayer.getTileWidth() / 2) {
                if (isCellBlocked(getX() + step, getY() + getHeight())) {
                        return true;
                }
        }
		if (player != this) {
			if (collisionCharacterTop(player)) {
				return true;
			}
		}
		for (Enemy enemy : enemies) {
			if (enemy != this) {
				if (collisionCharacterTop(enemy)) {
					return true;
				}
			}
		}
        return false;
	}
 
	/**
	 * Checks for tile or character collisions below using collisionCharacterBottom() and isCellBlocked() methods.
	 * @return boolean. True if there is any type of collision below.
	 */
	public boolean collidesBottom() {
        for (float step = (collisionLayer.getTileWidth() / 4); step < getWidth(); step += collisionLayer.getTileWidth() / 2) {
                if (isCellBlocked(getX() + step, getY())) {
                        return true;
                }
        }
		if (player != this) {
			if (collisionCharacterBottom(player)) {
				return true;
			}
		}
		for (Enemy enemy : enemies) {
			if (enemy != this) {
				if (collisionCharacterBottom(enemy)) {
					return true;
				}
			}
		}
        return false;
	}

	public void increaseSpeed(int speed) {
		this.speed += speed;
	}
	
	public void decreaseSpeed(int speed) {
		this.speed -= speed;
	}
	
	public void increaseHealth(int health) {
		int newHealth = this.health += health;
		if(newHealth <= getMaxHealth()) {
			this.health = newHealth;
		} else {
			this.health = getMaxHealth();
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
	
	public void increaseDamage(int damage) {
		this.damage += damage;
	}
	
	public void decreaseDamage(int damage) {
		this.damage -= damage;
	}
	
	public void attack(int damage, int direction) {
		// Initialise parameters for hitboxes to check for enemies
		int hitboxLength = 60;
		int hitboxWidth = 25;
		int lowerBound = 5;
		int upperBound = 40;
		// Knockback strength
		int knockback = 30;
		// Import sound effects for combat 
		hitSFX = Gdx.audio.newSound(Gdx.files.internal("sounds/playerAttack.mp3"));
		missSFX = Gdx.audio.newSound(Gdx.files.internal("sounds/playerMiss.mp3"));
		// Start cooldown on player's attack
		player.cooldown(true);
		
		// Check for enemies in a hitbox based on the player's position and direction of attack
		switch (direction) {
		// North West
		case 1: 
			//System.out.print(player.getX() + " " + player.getY());
			for (Enemy enemy : enemies) {
				if (enemy != this) {
					if (lowerBound<=(enemy.getY()-player.getY()) && (enemy.getY()-player.getY())<=upperBound) {
						if (lowerBound<=(player.getX()-enemy.getX()) && (player.getX()-enemy.getX())<=upperBound) {						
							hitSFX.play();	
							enemy.setX(enemy.getX()-(knockback/2));
							enemy.setY(enemy.getY()+(knockback/2));
							enemy.decreaseHealth(1);
							if (enemy.getHealth() <= 0) {
								/* 
								 * Unless better method is found
								 * set enemy coordinates to 1 million
								 * freeze enemy and reset health
								 * call back coordinates > 999000 when needed to spawn again?
								 */
								enemy.setX(1000000);
								enemy.setY(1000000);
								enemy.increaseHealth(4);
								enemy.isPaused();
							}
						}
					}	
					else {
						missSFX.play();
					}
				}							
			}		
			break;
		// North
		case 2:  
			for (Enemy enemy : enemies) {
				if (enemy != this) {
					if (enemy.getX()<=(player.getX()+hitboxWidth) && enemy.getX()>=(player.getX()-hitboxWidth)) {
						if (enemy.getY()<=(player.getY()+hitboxLength) && enemy.getY()>=player.getY()) {
							hitSFX.play();	
							enemy.setY(enemy.getY()+knockback);
							enemy.decreaseHealth(1);
							if (enemy.getHealth() <= 0) {
								enemy.setX(1000000);
								enemy.setY(1000000);
								enemy.increaseHealth(4);
								enemy.isPaused();
							}		
						}
					}	
					else {
						missSFX.play();
					}
				}	
			}
			break;
		// North East
		case 3:  
			for (Enemy enemy : enemies) {
				if (enemy != this) {
					if (lowerBound<=(enemy.getY()-player.getY()) && (enemy.getY()-player.getY())<=upperBound) {
						if (lowerBound<=(enemy.getX()-player.getX()) && (enemy.getX()-player.getX())<=upperBound) {
							hitSFX.play();	
							enemy.setX(enemy.getX()+(knockback/2));
							enemy.setY(enemy.getY()+(knockback/2));
							enemy.decreaseHealth(1);
							if(enemy.getHealth() <= 0) {
								enemy.setX(1000000);
								enemy.setY(1000000);
								enemy.increaseHealth(4);
								enemy.isPaused();
							}		
						}
					}	
					else {
						missSFX.play();
					}
				}	
			}
			break;
		// East
		case 4: 
			for (Enemy enemy : enemies) {
				if (enemy != this) {
					if (enemy.getX()<=(player.getX()+hitboxLength) && enemy.getX()>=player.getX()) {
						if (enemy.getY()<=(player.getY()+hitboxWidth) && enemy.getY()>=(player.getY()-hitboxWidth)) {
							hitSFX.play();	
							enemy.setX(enemy.getX()+knockback);
							enemy.decreaseHealth(1);
							if (enemy.getHealth() <= 0) {
								enemy.setX(1000000);
								enemy.setY(1000000);
								enemy.increaseHealth(4);
								enemy.isPaused();
							}
						}
					}
					else {
						missSFX.play();
					}
				}	
			}		
			break;	
		// South East
		case 5:  
			for (Enemy enemy : enemies) {
				if (enemy != this) {
					if (lowerBound<=(player.getY()-enemy.getY()) && (player.getY()-enemy.getY())<=upperBound) {
						if (lowerBound<=(enemy.getX()-player.getX()) && (enemy.getX()-player.getX())<=upperBound) {
							hitSFX.play();	
							enemy.setX(enemy.getX()+(knockback/2));
							enemy.setY(enemy.getY()-(knockback/2));
							enemy.decreaseHealth(1);
							if (enemy.getHealth() <= 0) {
								enemy.setX(1000000);
								enemy.setY(1000000);
								enemy.increaseHealth(4);
								enemy.isPaused();
							}								
						}
					}	
					else {
						missSFX.play();
					}
				}	
			}
			break;
		// South
		case 6: 
			for (Enemy enemy : enemies) {
				if (enemy != this) {
					if (enemy.getX()<=(player.getX()+hitboxWidth) && enemy.getX()>=(player.getX()-hitboxWidth)) {
						if (enemy.getY()>=(player.getY()-hitboxLength) && enemy.getY()<=player.getY()) {
							hitSFX.play();	
							enemy.setY(enemy.getY()-knockback);
							enemy.decreaseHealth(1);
							if (enemy.getHealth() <= 0) {
								enemy.setX(1000000);
								enemy.setY(1000000);
								enemy.increaseHealth(4);
								enemy.isPaused();
							}
						}
					}	
					else {
						missSFX.play();
					}
				}	
			}	
			break;
		// South West
		case 7:  
			for (Enemy enemy : enemies) {
				if (enemy != this) {
					if (lowerBound<=(player.getY()-enemy.getY()) && (player.getY()-enemy.getY())<=upperBound) {
						if (lowerBound<=(player.getX()-enemy.getX()) && (player.getX()-enemy.getX())<=upperBound) {
							hitSFX.play();	
							enemy.setX(enemy.getX()-(knockback/2));
							enemy.setY(enemy.getY()-(knockback/2));
							enemy.decreaseHealth(1);
							if(enemy.getHealth() <= 0) {
								enemy.setX(1000000);
								enemy.setY(1000000);
								enemy.increaseHealth(4);
								enemy.isPaused();
							}		
						}
					}	
					else {
						missSFX.play();
					}
				}	
			}
			break;
		// West
		case 8: 
			for (Enemy enemy : enemies) {
				if (enemy != this) {
					if (enemy.getX()>=(player.getX()-hitboxLength) && enemy.getX()<=player.getX()) {
						if (enemy.getY()<=(player.getY()+hitboxWidth) && enemy.getY()>=(player.getY()-hitboxWidth)) {
							hitSFX.play();	
							enemy.setX(enemy.getX()-knockback);
							enemy.decreaseHealth(1);
							if (enemy.getHealth() <= 0) {
								enemy.setX(1000000);
								enemy.setY(1000000);
								enemy.increaseHealth(4);
								enemy.isPaused();
							}
						}
					}	
					else {
						missSFX.play();
					}
				}	
			}	
			break;
		}
	}
	
	public void togglePaused() {
		this.paused = !paused;
		setVelocityX(0);
		setVelocityY(0);
	}
	
	public boolean isPaused() {
		return paused;
	}

	public Texture getNeturalAnimation() {
		return neturalAnimation;
	}

	public void setNeturalAnimation(Texture neturalAnimation) {
		this.neturalAnimation = neturalAnimation;
	}

	public Texture getUpAnimation1() {
		return upAnimation1;
	}

	public void setUpAnimation1(Texture upAnimation1) {
		this.upAnimation1 = upAnimation1;
	}

	public Texture getUpAnimation2() {
		return upAnimation2;
	}

	public void setUpAnimation2(Texture upAnimation2) {
		this.upAnimation2 = upAnimation2;
	}

	public Texture getDownAnimation1() {
		return downAnimation1;
	}

	public void setDownAnimation1(Texture downAnimation1) {
		this.downAnimation1 = downAnimation1;
	}

	public Texture getDownAnimation2() {
		return downAnimation2;
	}

	public void setDownAnimation2(Texture downAnimation2) {
		this.downAnimation2 = downAnimation2;
	}

	public Texture getLeftAnimation1() {
		return leftAnimation1;
	}

	public void setLeftAnimation1(Texture leftAnimation1) {
		this.leftAnimation1 = leftAnimation1;
	}

	public Texture getLeftAnimation2() {
		return leftAnimation2;
	}

	public void setLeftAnimation2(Texture leftAnimation2) {
		this.leftAnimation2 = leftAnimation2;
	}

	public Texture getRightAnimation1() {
		return rightAnimation1;
	}

	public void setRightAnimation1(Texture rightAnimation1) {
		this.rightAnimation1 = rightAnimation1;
	}

	public Texture getRightAnimation2() {
		return rightAnimation2;
	}

	public void setRightAnimation2(Texture rightAnimation2) {
		this.rightAnimation2 = rightAnimation2;
	}

	public Texture getUpLeftAnimation1() {
		return upLeftAnimation1;
	}

	public void setUpLeftAnimation1(Texture upLeftAnimation1) {
		this.upLeftAnimation1 = upLeftAnimation1;
	}

	public Texture getUpLeftAnimation2() {
		return upLeftAnimation2;
	}

	public void setUpLeftAnimation2(Texture upLeftAnimation2) {
		this.upLeftAnimation2 = upLeftAnimation2;
	}

	public Texture getDownLeftAnimation1() {
		return downLeftAnimation1;
	}

	public void setDownLeftAnimation1(Texture downLeftAnimation1) {
		this.downLeftAnimation1 = downLeftAnimation1;
	}

	public Texture getDownLeftAnimation2() {
		return downLeftAnimation2;
	}

	public void setDownLeftAnimation2(Texture downLeftAnimation2) {
		this.downLeftAnimation2 = downLeftAnimation2;
	}

	public Texture getUpRightAnimation1() {
		return upRightAnimation1;
	}

	public void setUpRightAnimation1(Texture upRightAnimation1) {
		this.upRightAnimation1 = upRightAnimation1;
	}

	public Texture getUpRightAnimation2() {
		return upRightAnimation2;
	}

	public void setUpRightAnimation2(Texture upRightAnimation2) {
		this.upRightAnimation2 = upRightAnimation2;
	}

	public Texture getDownRightAnimation1() {
		return downRightAnimation1;
	}

	public void setDownRightAnimation1(Texture downRightAnimation1) {
		this.downRightAnimation1 = downRightAnimation1;
	}

	public Texture getDownRightAnimation2() {
		return downRightAnimation2;
	}

	public void setDownRightAnimation2(Texture downRightAnimation2) {
		this.downRightAnimation2 = downRightAnimation2;
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getHealth() {
		return health;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
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
}
