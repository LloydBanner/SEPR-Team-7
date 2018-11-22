package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Item extends Sprite {

    public Item(Sprite sprite) {
    	super(sprite);
    }
    
	public void dispose () {
    	
    	this.getTexture().dispose();
    	
    }
    
    public void takeItem () {
    	
    }

    public void dropItem () {
    }
    
}