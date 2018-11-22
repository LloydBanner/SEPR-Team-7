package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpeedConsumable extends Consumable {

    private int speedBoost;

    public SpeedConsumable (Sprite sprite, int speedBoost) {
        
    	super(sprite);
    	this.speedBoost = speedBoost;
    	
    }

    public int getSpeedBoost() {
        return this.speedBoost;
    }

    @Override
    public void consume() {
    }
}
