package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public abstract class Item extends Sprite {

	
	//for collisions
	private TiledMapTileLayer collisionLayer;
	
    public Item(Sprite sprite, TiledMapTileLayer collisionLayer) {
    	super(sprite);
    	this.setCollisionLayer(collisionLayer);
    }
    
	public void dispose () {
    	
    	this.getTexture().dispose();
    	
    }
	
	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
	
    public void takeItem () {
    	
    }

    public void dropItem () {
    }
    
}