package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class MissionItem extends Item {
	
	private String id;

    public MissionItem (Sprite sprite, TiledMapTileLayer collisionLayer, String id) {

    	super(sprite, collisionLayer);
    	this.id = id;

    }
    
    public String getId () {
    	
    	return this.id;
    }

    @Override
    public void takeItem () {
    }
    
    
    
    
}


