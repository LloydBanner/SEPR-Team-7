package sprites.items;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class HealthConsumable extends Consumable {

    private int healthBoost;

    public HealthConsumable (Sprite sprite, TiledMapTileLayer collisionLayer, int healthBoost) {
       
    	super(sprite, collisionLayer);
    	this.healthBoost = healthBoost;
    	
    }

    public int getHealthBoost() {
        return this.healthBoost;
    }

    @Override
    public void consume() {
    }
    
    @Override
	public void draw(Batch batch) {
		super.draw(batch);
	}
}