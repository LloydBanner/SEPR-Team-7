package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public abstract class Consumable extends Item {

    public Consumable(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite, collisionLayer);
	}

	public abstract void consume();

}

