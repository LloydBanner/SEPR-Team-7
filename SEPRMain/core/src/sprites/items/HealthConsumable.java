package sprites.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class HealthConsumable extends Consumable {

    private int healthBoost;
    Texture healthTex = new Texture("img/");	// add health asset

    public HealthConsumable (int healthBoost) {
       
    	new Sprite(healthTex);		
    	this.healthBoost = healthBoost;
    	
    }

    public int getHealthBoost() {
        return this.healthBoost;
    }

    @Override
    public void consume() {
    }
}