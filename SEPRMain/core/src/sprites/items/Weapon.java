package sprites.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon extends Item {

    private int damageBoost;
    private int durability;
	Texture weaponTex = new Texture("img/");		

    public Weapon (int damageBoost, int durability) {

    	new Sprite(weaponTex);		
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
