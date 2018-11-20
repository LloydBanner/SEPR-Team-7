package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Item extends Sprite {

    public void dispose () {
    	
    	this.getTexture().dispose();
    	
    }
    
    public void takeItem () {
    	
    }

    public void dropItem () {
    }
    
}