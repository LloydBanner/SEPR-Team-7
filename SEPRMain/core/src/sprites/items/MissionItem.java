package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Player;

public class MissionItem extends Item {
	
	private String id;

    public MissionItem (Sprite sprite, TiledMapTileLayer collisionLayer, String id, Player player) {

    	super(sprite, collisionLayer, player);
    	this.id = id;

    }
    
    public String getId () {
    	
    	return this.id;
    }

    @Override
    public void interact(Player player) {
    }
    
    
    
    
}


