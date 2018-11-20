package sprites.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MissionItem extends Item {
	
	private String id;
    Texture missTex = new Texture("img/");		// add mission item asset

    public MissionItem (String id) {

    	new Sprite(missTex);		
    	this.id = id;

    }
    
    public String getId () {
    	
    	return this.id;
    }

    @Override
    public void takeItem () {
    }
}


