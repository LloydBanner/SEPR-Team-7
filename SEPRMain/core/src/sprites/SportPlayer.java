package sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SportPlayer extends Player {

	public SportPlayer(Sprite sprite, Game game) {
		super(sprite, game);
		// animation
		setNeturalAnimation(new Texture("img/class2 (10).png"));
		setUpAnimation1(new Texture("img/class2 (3).png"));
		setUpAnimation2(new Texture("img/class2 (5).png"));
		setDownAnimation1(new Texture("img/class2 (11).png"));
		setDownAnimation2(new Texture("img/class2 (13).png"));
		setLeftAnimation1(new Texture("img/class2 (7).png"));
		setLeftAnimation2(new Texture("img/class2 (9).png"));
		setRightAnimation1(new Texture("img/class2 (15).png"));
		setRightAnimation2(new Texture("img/class2 (16).png"));
		setUpLeftAnimation1(new Texture("img/class2 (7).png"));
		setUpLeftAnimation2(new Texture("img/class2 (9).png"));
		setDownLeftAnimation1(new Texture("img/class2 (7).png"));
		setDownLeftAnimation2(new Texture("img/class2 (9).png"));
		setUpRightAnimation1(new Texture("img/class2 (15).png"));
		setUpRightAnimation2(new Texture("img/class2 (16).png"));
		setDownRightAnimation1(new Texture("img/class2 (15).png"));
		setDownRightAnimation2(new Texture("img/class2 (16).png"));
		
		setDamage(2);
	}

}
