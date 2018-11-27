package sprites;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


public class Enemy extends Character{
	
	//removed some methods as they were supported in character
	
	private boolean isAttacking;
	private float timeCount;
	private Player player;

	public Enemy(Sprite sprite, TiledMapTileLayer collisionLayer, Player player) {
		super(sprite);
		this.setCollisionLayer(collisionLayer);
		decreaseSpeed(90);
		this.player = player; 
	}
	
	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta) {
		super.update(delta);
		
		timeCount += delta;
		
		if(Math.abs(player.getX() - getX()) < 100 && Math.abs(player.getY() - getY()) < 100) {
			isAttacking = true;
		} 
		else {
			isAttacking = false;
		}
		
		if(timeCount > 2 && !isAttacking) {
			timeCount = 0;
			randomMove();
		}
		else if(timeCount > 2 && isAttacking){
			setVelocityX(0); 
			setVelocityY(0);
			attackPlayer();
		}		
	}
	
	private void randomMove() {
		Random rand = new Random();
		int direction = rand.nextInt(10);
		float speedMod = getSpeed() + rand.nextInt(6);
		// Change Direction based on RNG
		switch (direction) {
			// Up
			case 0: setVelocityY(speedMod);		
					setVelocityX(0);	
					break;
			// Down
			case 1: setVelocityY(-speedMod);		
					setVelocityX(0);	
					break;
			// Right
			case 2: setVelocityX(speedMod);		
					setVelocityY(0);	
					break;
			// Left
			case 3: setVelocityX(-speedMod);		
					setVelocityY(0);
					break;
			// Up-Right
			case 4: setVelocityX(speedMod);		
					setVelocityY(speedMod);	
					break;
			// Down-Right
			case 5: setVelocityX(speedMod);		
					setVelocityY(-speedMod);	
					break;
			// Up-Left
			case 6: setVelocityX(-speedMod);		
					setVelocityY(speedMod);
					break;
			// Down-Left
			case 7: setVelocityX(-speedMod);		
					setVelocityY(-speedMod);
					break;
			// Stop
			default: setVelocityX(0);		
					 setVelocityY(0);
					 break;
		}
	}
	
	private void attackPlayer() {
		int chaseSpeed = getSpeed() + 20;
		float dx = player.getX() - getX();
		float dy = player.getY() - getY();
		double norm = Math.sqrt(dx * dx + dy * dy);
		
		if (norm > 0){
		    dx *= (chaseSpeed / norm);
		    dy *= (chaseSpeed / norm);
		    setVelocityX(dx);		
			setVelocityY(dy);
		}
	}
}
