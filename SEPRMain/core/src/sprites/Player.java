package sprites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
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
	private boolean escPressed = false;
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		//always use same sprite for player so don't need to take it as an input
		super(sprite);
		this.setCollisionLayer(collisionLayer);
		inventory = new HashMap<Item, Integer>();
		missionItems = new ArrayList<String>();
		
		//animation
		//needs to be changed when classes are added
		//these sets should be moved in to the classes' class so ech one can have different animations 
		setNeturalAnimation(new Texture("img/player.png"));
		setUpAnimation1(new Texture("img/zombie.png"));
		setUpAnimation2(new Texture("img/player.png"));
		setDownAnimation1(new Texture("img/zombie.png"));
		setDownAnimation2(new Texture("img/player.png"));
		setLeftAnimation1(new Texture("img/zombie.png"));
		setLeftAnimation2(new Texture("img/player.png"));
		setRightAnimation1(new Texture("img/playerRight1.png"));
		setRightAnimation2(new Texture("img/playerRight2.png"));
		setUpLeftAnimation1(new Texture("img/zombie.png"));
		setUpLeftAnimation2(new Texture("img/player.png"));
		setDownLeftAnimation1(new Texture("img/zombie.png"));
		setDownLeftAnimation2(new Texture("img/player.png"));
		setUpRightAnimation1(new Texture("img/zombie.png"));
		setUpRightAnimation2(new Texture("img/player.png"));
		setDownRightAnimation1(new Texture("img/zombie.png"));
		setDownRightAnimation2(new Texture("img/player.png"));
	}

	@Override
	public boolean keyDown(int keycode) {
		//set speed in direction of key press
		if (!isPaused()) {
			switch(keycode) {
			case Keys.W:
				setVelocityY(getSpeed());
				Sprite newSprite = new Sprite(new Texture("img/zombie.png"));
				newSprite.setPosition(getX(), getY());
				this.set(newSprite);
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
		}

		switch(keycode) {
		//esc for pause
		case Keys.ESCAPE:
			escPressed = true;
			break;
		//O and P used to test health increase and decrease
		case Keys.O:
			increaseHealth(1);
			break;
		case Keys.P:
			decreaseHealth(1);
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
	
	public boolean isEscPressed() {
		boolean check = this.escPressed;
		this.escPressed = false;
		return check;
	}
	
	public HashMap<Item, Integer> getInventory(){
		return inventory;
	}
	
	public void addItem(Item item) {
		
		if (inventory.containsKey(item)) {

            int oldValue = inventory.get(item);
            inventory.put(item, oldValue + 1);
            item.dispose();		
            
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
