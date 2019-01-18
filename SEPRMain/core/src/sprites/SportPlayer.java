package sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SportPlayer extends Player {

	public SportPlayer(Sprite sprite, Game game) {
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
		
		setDamage(2);
	}

}
