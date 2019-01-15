package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Player;

public abstract class Consumable extends Item {

    public Consumable(Sprite sprite, TiledMapTileLayer collisionLayer, Player player) {
		super(sprite, collisionLayer, player);
	}

	public abstract void consume(Player player);
	
}

