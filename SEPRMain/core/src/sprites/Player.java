package sprites;

import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sprites.items.HealthConsumable;
import sprites.items.Item;
import sprites.items.MissionItem;
import sprites.items.SpeedConsumable;

public class Player extends Character implements InputProcessor {
	
	//need to decide on player inventory
	private HashMap<Item, Integer> inventory;
	private Collection<String> missionItems;
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.setCollisionLayer(collisionLayer);
	}

	@Override
	public boolean keyDown(int keycode) {
		//set speed in direction of key press
		switch(keycode) {
		case Keys.W:
			setVelocityY(getSpeed());
			break;
		case Keys.S:
			setVelocityY(-getSpeed());
			break;
		case Keys.A:
			setVelocityX(-getSpeed());
			break;
		case Keys.D:
			setVelocityX(getSpeed());
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		//Decides if player should stop when key released
		switch(keycode) {
		case Keys.A:
			if(getVelocity().x == -getSpeed()) {
				setVelocityX(0);
			}
			break;
		case Keys.D:
			if(getVelocity().x == getSpeed()) {
				setVelocityX(0);
			}
			break;
		case Keys.W:
			if(getVelocity().y == getSpeed()) {
				setVelocityY(0);
			}
			break;
		case Keys.S:
			if(getVelocity().y == -getSpeed()) {
				setVelocityY(0);
			}
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public HashMap<Item, Integer> getInventory(){
		return inventory;
	}
	
	public void addItem(Item item) {
		
		if (inventory.containsKey(item)) {

            int oldValue = inventory.get(item);
            inventory.put(item, oldValue + 1);
            item.dispose();		// adding an item multiple times may be difficult
            
        } else {

            inventory.put(item, 1);
            item.dispose();
        }
	}

	public void removeItem(Item item) {
		
		if (inventory.get(item) > 1) {

            int oldValue = inventory.get(item);
            inventory.put(item, oldValue - 1);
            // add draw item method
        

        } else {

            inventory.remove(item);
            // add draw item method

        }
	}

	public void addMissionItem(MissionItem missionItem) {
		missionItems.add(missionItem.getId());
		missionItem.dispose();
	}
	
	public void consume (HealthConsumable consumable) {
		
		this.increaseHealth(consumable.getHealthBoost());
		consumable.dispose();
		
	}
	
	public void consume (SpeedConsumable consumable) {
		
		this.increaseSpeed(consumable.getSpeedBoost());	
		consumable.dispose();
		
	}
	
}
