package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class HealthConsumable extends Consumable {

    private int healthBoost;

    public HealthConsumable (Sprite sprite, int healthBoost) {
       
    	super(sprite);
    	this.healthBoost = healthBoost;
    	
    }

    public int getHealthBoost() {
        return this.healthBoost;
    }

    @Override
    public void consume() {
    }
}