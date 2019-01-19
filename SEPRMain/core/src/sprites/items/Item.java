package sprites.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Character;
import sprites.Player;

public abstract class Item extends Sprite {

	
	//for collisions
	private TiledMapTileLayer collisionLayer;
	protected Player player;
	
    public Item(Sprite sprite, TiledMapTileLayer collisionLayer, Player player) {
    	super(sprite);
    	this.player = player;
    	this.setCollisionLayer(collisionLayer);
    }
    
    @Override
    public void draw(Batch batch) {
    	update(Gdx.graphics.getDeltaTime());
    	super.draw(batch);
    }
    
    public void update(float delta) {
    	boolean collisionX = collisionPlayerRight(player) || collisionPlayerLeft(player);
    	boolean collisionY = collisionPlayerTop(player) || collisionPlayerBottom(player);
    	
    	if (collisionX || collisionY) {
    		interact(player);
    	}
    	
    }
    
    /**
	 * Checks for a player collision to the right.
	 * @return boolean. True if the player touches the right of the item.
	 */
  	public boolean collisionPlayerRight(Character player) {
          if(5 < ((player.getX() + (player.getWidth() / 2)) - (getX() + (getWidth() / 2)))) {
          	if (((player.getX() + (player.getWidth() / 2)) - (getX() + (getWidth() / 2))) < 25) {
          		if (Math.abs((player.getY() + (player.getHeight() / 2)) - (getY() + (getHeight() / 2))) < 25) {
          			return true;
          		}   
          	}
          }
          return false;
  	}
  	
    /**
	 * Checks for a player collision to the left.
	 * @return boolean. True if the player touches the left of the item.
	 */
  	public boolean collisionPlayerLeft(Character player) {
          if(-5 > ((player.getX() + (player.getWidth() / 2)) - (getX() + (getWidth() / 2)))) {
          	if (((player.getX() + (player.getWidth() / 2)) - (getX() + (getWidth() / 2))) > -25) {
          		if (Math.abs((player.getY() + (player.getHeight() / 2)) - (getY() + (getHeight() / 2))) < 25) {
          			return true;    
          		}    
          	}
          }
          return false;
  	}
  	
    /**
	 * Checks for a player collision above.
	 * @return boolean. True if the player touches the top of the item.
	 */
  	public boolean collisionPlayerTop(Character player) {
          if(5 < ((player.getY() + (player.getHeight() / 2)) - (getY() + (getHeight() / 2)))) {
              if (((player.getY() + (player.getHeight() / 2)) - (getY() + (getHeight() / 2))) < 25) {
              	if (Math.abs((player.getX() + (player.getWidth() / 2)) - (getX() + (getWidth() / 2))) < 25) {
                  	return true;  
              	}
              }     	
          }
          return false;
  	}
  	
    /**
	 * Checks for a player collision below.
	 * @return boolean. True if the player touches the bottom of the item.
	 */
  	public boolean collisionPlayerBottom(Character player) {
          if(-5 > ((player.getY() + (player.getHeight() / 2)) - (getY() + (getHeight() / 2)))) {
          	if (((player.getY() + (player.getHeight() / 2)) - (getY() + (getHeight() / 2))) > -25) {
          		if (Math.abs((player.getX() + (player.getWidth() / 2)) - (getX() + (getWidth() / 2))) < 25) {
          			return true;
          		}
          	}
          }
          return false;
  	}
    
	public void dispose () {
		//removes the item when it is disposed
    	this.setX(1000000000);
    	this.setY(1000000000);
    	this.getTexture().dispose();
    	
    }
	
	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
	
	public Player getPlayer() {
		return this.player;
	}

    public void dropItem () {
    }

	public void interact(Player player) {
		this.dispose();
	}
    
}