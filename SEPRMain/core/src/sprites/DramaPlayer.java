package sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class DramaPlayer extends Player {

	public DramaPlayer(Sprite sprite, Game game) {
		super(sprite, game);
		// animation
		setNeturalAnimation(new Texture("img/class1 (1).png"));
		setUpAnimation1(new Texture("img/class1 (10).png"));
		setUpAnimation2(new Texture("img/class1 (12).png"));
		setDownAnimation1(new Texture("img/class1 (2).png"));
		setDownAnimation2(new Texture("img/class1 (4).png"));
		setLeftAnimation1(new Texture("img/class1 (14).png"));
		setLeftAnimation2(new Texture("img/class1 (16).png"));
		setRightAnimation1(new Texture("img/class1 (6).png"));
		setRightAnimation2(new Texture("img/class1 (8).png"));
		setUpLeftAnimation1(new Texture("img/class1 (14).png"));
		setUpLeftAnimation2(new Texture("img/class1 (16).png"));
		setDownLeftAnimation1(new Texture("img/class1 (14).png"));
		setDownLeftAnimation2(new Texture("img/class1 (16).png"));
		setUpRightAnimation1(new Texture("img/class1 (6).png"));
		setUpRightAnimation2(new Texture("img/class1 (8).png"));
		setDownRightAnimation1(new Texture("img/class1 (6).png"));
		setDownRightAnimation2(new Texture("img/class1 (8).png"));
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
		setNeturalAnimation(new Texture("img/class1 (1).png"));
		setUpAnimation1(new Texture("img/class1 (10).png"));
		setUpAnimation2(new Texture("img/class1 (12).png"));
		setDownAnimation1(new Texture("img/class1 (2).png"));
		setDownAnimation2(new Texture("img/class1 (4).png"));
		setLeftAnimation1(new Texture("img/class1 (14).png"));
		setLeftAnimation2(new Texture("img/class1 (16).png"));
		setRightAnimation1(new Texture("img/class1 (6).png"));
		setRightAnimation2(new Texture("img/class1 (8).png"));
		setUpLeftAnimation1(new Texture("img/class1 (14).png"));
		setUpLeftAnimation2(new Texture("img/class1 (16).png"));
		setDownLeftAnimation1(new Texture("img/class1 (14).png"));
		setDownLeftAnimation2(new Texture("img/class1 (16).png"));
		setUpRightAnimation1(new Texture("img/class1 (6).png"));
		setUpRightAnimation2(new Texture("img/class1 (8).png"));
		setDownRightAnimation1(new Texture("img/class1 (6).png"));
		setDownRightAnimation2(new Texture("img/class1 (8).png"));
	}

}
