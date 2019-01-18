package sprites.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Player;

public class HealthConsumable extends Consumable {

    private int healthBoost;
    private static Sprite health = new Sprite(new Texture("img/health.png"));

    public HealthConsumable (TiledMapTileLayer collisionLayer, int healthBoost, Player player) {
    	super(health, collisionLayer, player);
    	this.healthBoost = healthBoost;
    	
    }

    public int getHealthBoost() {
        return this.healthBoost;
    }

    @Override
    public void consume(Player player) {
    	player.increaseHealth(healthBoost);
    }
    
    @Override
    public void interact(Player player) {
    	consume(player);
    	super.interact();
    }
    
    @Override
	public void draw(Batch batch) {
		super.draw(batch);
	}
}