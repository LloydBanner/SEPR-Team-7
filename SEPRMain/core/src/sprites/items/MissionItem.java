package sprites.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Player;

public class MissionItem extends Item {
	
	private String id;
    private static Sprite key = new Sprite(new Texture("img/key.png"));

    public MissionItem (TiledMapTileLayer collisionLayer, String id, Player player) {

    	super(key, collisionLayer, player);
    	this.id = id;

    }
    
    public String getId () {
    	
    	return this.id;
    }

    @Override
    public void interact(Player player) {
    	player.addMissionItem(this);
    	super.interact(player);
    }
    
    
    
    
}


