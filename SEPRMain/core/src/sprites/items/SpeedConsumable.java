package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Player;

public class SpeedConsumable extends Consumable {

    private int speedBoost;

    public SpeedConsumable (Sprite sprite, TiledMapTileLayer collisionLayer, int speedBoost, Player player) {
        
    	super(sprite, collisionLayer, player);
    	this.speedBoost = speedBoost;
    	
    }

    public int getSpeedBoost() {
        return this.speedBoost;
    }

    @Override
    public void consume(Player player) {
    }
}
