package sprites.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpeedConsumable extends Consumable {

    private int speedBoost;
    Texture speedTex = new Texture("img/");		// add speed asset

    public SpeedConsumable (int speedBoost) {
        
    	new Sprite(speedTex);		
    	this.speedBoost = speedBoost;
    	
    }

    public int getSpeedBoost() {
        return this.speedBoost;
    }

    @Override
    public void consume() {
    }
}
