package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Consumable extends Item {

    public Consumable(Sprite sprite) {
		super(sprite);
	}

	public abstract void consume();

}

