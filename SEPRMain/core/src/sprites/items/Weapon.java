package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon extends Item {

    private int damageBoost;
    private int durability;

    public Weapon (Sprite sprite, int damageBoost, int durability) {

    	super(sprite);		
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
