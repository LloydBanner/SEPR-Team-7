package sprites.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Player;

public class ShieldConsumable extends Consumable {
	
	private static Sprite shield = new Sprite(new Texture("img/shield.png"));

	public ShieldConsumable(TiledMapTileLayer collisionLayer, Player player) {
		super(shield, collisionLayer, player);
		// TODO Auto-generated constructor stub
	}

    @Override
    public void interact(Player player) {
    	consume(player);
    	super.interact(player);
    }  
    
	@Override
	public void consume(Player player) {
		player.shieldPowerUp(10);
	}

}
