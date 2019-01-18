package sprites.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Player;

public class SpeedConsumable extends Consumable {

    private int speedBoost;
    private static Sprite speed = new Sprite(new Texture("img/speed.png"));

    public SpeedConsumable (TiledMapTileLayer collisionLayer, int speedBoost, Player player) {
    	super(speed, collisionLayer, player);
    	this.speedBoost = speedBoost;
    	
    }

    public int getSpeedBoost() {
        return this.speedBoost;
    }

    @Override
    public void interact(Player player) {
    	consume(player);
    	super.interact(player);
    }    
    
    @Override
    public void consume(Player player) {
    	player.speedPowerUp(speedBoost);
    }
}
