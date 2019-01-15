package sprites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.Input;
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
	private int direction;
	private boolean cdActive = false;
	private double timer;
	private double cdTime = 0.6;
	
	public Player(Sprite sprite) {
		//always use same sprite for player so don't need to take it as an input
		super(sprite);
		//this.setCollisionLayer(collisionLayer);
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
	
	public void update(float delta) {
		super.update(delta);
		
		// If the cooldown is running then start our timer 
		if(cdActive)
		{
			timer += delta;		
			// Check when timer reaches cdTime
			if(timer>cdTime) {
				// Turn the cooldown off
				cooldown(false);
				// Reset the timer
				timer = 0;
			}
		}

	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		// Keypresses for player movement
		if (!isPaused()) {
			switch(keycode) {
			// Up
			case Keys.W:
				setVelocityY(getSpeed());
				Sprite newSprite = new Sprite(new Texture("img/zombie.png"));
				newSprite.setPosition(getX(), getY());
				this.set(newSprite);
				break;
			// Down
			case Keys.S:
				setVelocityY(-getSpeed());
				break;
			// Left
			case Keys.A:
				setVelocityX(-getSpeed());
				break;
			// Right
			case Keys.D:
				setVelocityX(getSpeed());
				break;
			}
		}

		switch(keycode) {
		// Escape for pause
		case Keys.ESCAPE:
			escPressed = true;
			break;
		// O and P used to test health increase and decrease
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
	
	public void cooldown(boolean cd) {
		if(cd) {
			// Set this global variable to true after attacking, timer starts in update()
			cdActive = true;
		}
		else {
			// Set to false after timer > cdTime
			cdActive = false;
		}
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// i is a counter, the min and max variables refer to coordinates around the screen
		int i;
		float min1 = 0;
		float max1 = 480;
		float min2 = 480;
		float max2 = 800;
		float min3 = 800;
		float max3 = 1280;
		float min4 = 227;
		float max4 = 493; 
		float min5 = 1280;
		float max5 = 800; 
		float min6 = 800;
		float max6 = 480; 
		float min7 = 480;
		float max7 = 0; 
		float min8 = 493;
		float max8 = 227; 
		
		// Checks for cursor in the top regions of the screen
		for(i=0;i<360;i++) {
			if(screenY == i) {
				if(min1 < screenX && screenX < max1) {
					direction = 1;
					break;
				} 
				if(min2 <= screenX && screenX <= max2) {
					direction = 2;
					break;
				}
				if(min3 < screenX && screenX < max3) {
					direction = 3;
					break;
				}
			}
			if(i>227) {
				min1+=4.812;
				max3-=4.812;
			}
			max1+=0.4432;
			min2+=0.4432;
			max2-=0.4432;
			min3-=0.4432;
		}
		
		// Checks for cursor in the right region of the screen
		for(i=1280;i>640;i--) {
			if(screenX == i) {
				if(min4 <= screenY && screenY <= max4) {
					direction = 4;
					break;
				} 		
			}
			min4+=0.1847;
			max4-=0.1847;
		}
		
		// Checks for cursor in the lower region of the screen
		for(i=720;i>361;i--) {
			if(screenY == i) {
				if(max5 < screenX && screenX < min5) {
					direction = 5;
					break;
				} 
				if(max6 <= screenX && screenX <= min6) {
					direction = 6;
					break;
				}
				if(max7 < screenX && screenX < min7) {
					direction = 7;
					break;
				}
			}
			// change gradients
			if(i<493) {
				min5-=4.812;
				max7+=4.812;
			}
			max5-=0.4432;
			min6-=0.4432;
			max6+=0.4432;
			min7+=0.4432;
		}

		// Checks for cursor in the left region of the screen
		for(i=0;i<640;i++) {
			if(screenX == i) {
				if(max8 <= screenY && screenY <= min8) {
					direction = 8;
					break;
				} 	
			}
			min8-=0.1847;
			max8+=0.1847;
		}
		
		// Checks left mouse button is pressed and that the game is not paused
		if(button == 0 && !isPaused())
		{
			// Increase damage based on weapon?
			// If the cooldown is active (cdActive == true) the player can't attack
			if(!cdActive) {
				attack(getDamage(), direction);
				// Resets direction to avoid possible errors
				direction = 0;
			}
		}
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
