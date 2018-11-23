package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class SpeedConsumable extends Consumable {

    private int speedBoost;

    public SpeedConsumable (Sprite sprite, TiledMapTileLayer collisionLayer, int speedBoost) {
        
    	super(sprite, collisionLayer);
    	this.speedBoost = speedBoost;
    	
    }

    public int getSpeedBoost() {
        return this.speedBoost;
    }

    @Override
    public void consume() {
    }
}
