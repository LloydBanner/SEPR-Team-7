package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.Player;

public class Weapon extends Item {

    private int damageBoost;
    private int durability;

    public Weapon (Sprite sprite, TiledMapTileLayer collisionLayer, int damageBoost, int durability, Player player) {

    	super(sprite, collisionLayer, player);		
        this.damageBoost = damageBoost;
        this.durability = durability;

    }

    public int getDamageBoost () {
        return this.damageBoost;
    }

    public int getDurability () {
        return this.durability;
    }
}
