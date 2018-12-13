package sprites;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


public class Enemy extends Character{
	
	private float timeCount;
	private float oldX = getX(), oldY = getY();
	private Player player;

	public Enemy(Sprite sprite, TiledMapTileLayer collisionLayer, Player player) {
		super(sprite);
		this.setCollisionLayer(collisionLayer);
		decreaseSpeed(100);
		this.player = player; 
		
		//animation
		setNeturalAnimation(new Texture("img/zombie.png"));
		setUpAnimation1(new Texture("img/zombie.png"));
		setUpAnimation2(new Texture("img/zombie.png"));
		setDownAnimation1(new Texture("img/zombie.png"));
		setDownAnimation2(new Texture("img/zombie.png"));
		setLeftAnimation1(new Texture("img/zombie.png"));
		setLeftAnimation2(new Texture("img/zombie.png"));
		setRightAnimation1(new Texture("img/zombie.png"));
		setRightAnimation2(new Texture("img/zombie.png"));
		setUpLeftAnimation1(new Texture("img/zombie.png"));
		setUpLeftAnimation2(new Texture("img/zombie.png"));
		setDownLeftAnimation1(new Texture("img/zombie.png"));
		setDownLeftAnimation2(new Texture("img/zombie.png"));
		setUpRightAnimation1(new Texture("img/zombie.png"));
		setUpRightAnimation2(new Texture("img/zombie.png"));
		setDownRightAnimation1(new Texture("img/zombie.png"));
		setDownRightAnimation2(new Texture("img/zombie.png"));
	}
	
	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta) {
		super.update(delta);
		timeCount += delta;	
		
		// Deals with player collisions
		if(!isPaused()) {
		
			// Every 2 seconds change movement randomly unless in attack range of player
			if(timeCount > 2 && !isAttacking()) {
				timeCount = 0;
				randomMove();
			} 
			else if(timeCount > 2 && isAttacking()) {
				setVelocityX(0); 
				setVelocityY(0);
				attackPlayer();
			}
		}
	}
	
	private void randomMove() {
		Random rand = new Random();
		int direction = rand.nextInt(10);
		float speedMod = getSpeed() + rand.nextInt(10);
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
		// Increases speed when chasing player, + 50 is the player's speed
		int chaseSpeed = getSpeed() + 35;
		float dx = player.getX() - getX();
		float dy = player.getY() - getY();
		double norm = Math.sqrt(dx * dx + dy * dy);
		// Velocity directs towards player
		if (norm > 0) {
		    dx *= (chaseSpeed / norm);
		    dy *= (chaseSpeed / norm);
		    setVelocityX(dx);		
			setVelocityY(dy);
		}
		player.getHealth();
	}
	
	private boolean isAttacking() {
        if(Math.abs(player.getX() - getX()) < 125 && Math.abs(player.getY() - getY()) < 125) {
            return true;       	
        }
        return false;
	}
	
}
