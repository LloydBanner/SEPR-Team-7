package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Player;

public abstract class Consumable extends Item {

    public Consumable(Sprite sprite, TiledMapTileLayer collisionLayer, Player player) {
		super(sprite, collisionLayer, player);
	}
    
    //Tester Constructors
    public Consumable() {
    	
    }

	public abstract void consume(Player player);

	public void interact(Player player) {
		super.interact(player);
	}
	
}

