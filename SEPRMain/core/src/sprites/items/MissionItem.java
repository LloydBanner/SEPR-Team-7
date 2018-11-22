package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class MissionItem extends Item {
	
	private String id;

    public MissionItem (Sprite sprite, String id) {

    	super(sprite);
    	this.id = id;

    }
    
    public String getId () {
    	
    	return this.id;
    }

    @Override
    public void takeItem () {
    }
}


