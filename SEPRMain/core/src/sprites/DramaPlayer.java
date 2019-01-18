package sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class DramaPlayer extends Player {

	public DramaPlayer(Sprite sprite, Game game) {
		super(sprite, game);
		// animation
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
	public void specialAbility() {
		super.specialAbility();
		for (Enemy enemy : getEnemies()) {
			enemy.setCanSeePlayer(false);
			//this is 20 while the cooldown for the player is 10, has to be double in enemy class
			enemy.setSightCooldown(20);
		}
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
	public void restoreDefaults() {
		super.restoreDefaults();
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

}
