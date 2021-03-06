package sprites;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


public class Enemy extends Character{
	
	private float timer;
	private float oldX = getX(), oldY = getY();
	private Player player;
    private static Sprite zombie = new Sprite(new Texture("img/zombie.png"));
	private boolean canSeePlayer;
	private int sightCooldown = 0;
	private float sightTimer = 0;

	public Enemy(TiledMapTileLayer collisionLayer, Player player) {
		super(zombie);
		this.setCollisionLayer(collisionLayer);
		decreaseSpeed(100);
		this.player = player; 
		
		//animation
		//need to add textures for animations
		setNeturalAnimation(new Texture("img/zombie01.png"));
		setUpAnimation1(new Texture("img/zombie11.png"));
		setUpAnimation2(new Texture("img/zombie09.png"));
		setDownAnimation1(new Texture("img/zombie00.png"));
		setDownAnimation2(new Texture("img/zombie02.png"));
		setLeftAnimation1(new Texture("img/zombie03.png"));
		setLeftAnimation2(new Texture("img/zombie05.png"));
		setRightAnimation1(new Texture("img/zombie06.png"));
		setRightAnimation2(new Texture("img/zombie08.png"));
		setUpLeftAnimation1(new Texture("img/zombie03.png"));
		setUpLeftAnimation2(new Texture("img/zombie05.png"));
		setDownLeftAnimation1(new Texture("img/zombie03.png"));
		setDownLeftAnimation2(new Texture("img/zombie05.png"));
		setUpRightAnimation1(new Texture("img/zombie06.png"));
		setUpRightAnimation2(new Texture("img/zombie08.png"));
		setDownRightAnimation1(new Texture("img/zombie06.png"));
		setDownRightAnimation2(new Texture("img/zombie08.png"));
	}
	
	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta) {
		super.update(delta);
		timer += delta;	
		
		// Deals with player collisions
		if(!isPaused()) {
			// Every 2 seconds change movement randomly unless in attack range of player
			if(timer > 2 && !isAttacking()) {
				timer = 0;
				randomMove();
			} 
			else if(timer > 2 && isAttacking() && isCanSeePlayer()) {
				setVelocityX(0); 
				setVelocityY(0);
				attackPlayer();
				if (timer > 4.5) {
					if(Math.abs(getX()-player.getX()) < 30) {
						if(Math.abs(getY()-player.getY()) < 30) {
							player.decreaseHealth(1);
						}
					}
					timer = 3;
				}
			}
		}
		
		if (sightCooldown > 0) {
			sightTimer += delta;
			if (sightTimer >= 1) {
				sightTimer = 0;
				sightCooldown -= 1;
			}
		} else {
			sightCooldown = 0;
			canSeePlayer = true;
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
		// Increases speed when chasing player
		int chaseSpeed = getSpeed() + 40;
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

	public boolean isCanSeePlayer() {
		return canSeePlayer;
	}

	public void setCanSeePlayer(boolean canSeePlayer) {
		this.canSeePlayer = canSeePlayer;
	}
	
	public void setSightCooldown(int cooldown) {
		sightCooldown = cooldown;
	}
	
}
