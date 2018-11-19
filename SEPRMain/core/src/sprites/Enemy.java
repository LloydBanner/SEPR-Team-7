package sprites;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;


public class Enemy extends Character{
	
	//removed some methods as they were supported in character
	
	private boolean isAttacking;
	private float timeCount;

	public Enemy(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.setCollisionLayer(collisionLayer);
		decreaseSpeed(90);
	}
	
	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta) {
		super.update(delta);
		
		timeCount += delta;
		if(timeCount > 2) {
			timeCount = 0;
			randomMove();
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
			case 2: setVelocityY(0);		
					setVelocityX(speedMod);	
					break;
			// Left
			case 3: setVelocityY(0);		
					setVelocityX(-speedMod);
					break;
			// Up-Right
			case 4: setVelocityY(speedMod);		
					setVelocityX(speedMod);	
					break;
			// Down-Right
			case 5: setVelocityY(-speedMod);		
					setVelocityX(speedMod);	
					break;
			// Up-Left
			case 6: setVelocityY(speedMod);		
					setVelocityX(-speedMod);
					break;
			// Down-Left
			case 7: setVelocityY(-speedMod);		
					setVelocityX(-speedMod);
					break;
			// Stop
			default: setVelocityY(0);		
					 setVelocityX(0);
					 break;
		}
	}
}
