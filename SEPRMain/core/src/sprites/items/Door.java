package sprites.items;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.zombiegame.game.SEPRZombieGame;

import sprites.Player;

public class Door extends Item {

	private int nextLocation;
	private Game currentGame;
	
	public Door(Sprite sprite, int nextLocation, TiledMapTileLayer collisionLayer, 
				Player player, Game currentGame) {
		
		super(sprite, collisionLayer, player);
		this.nextLocation = nextLocation;
		this.currentGame = currentGame;
	
	}
	
	@Override
	public void interact() {
		
		((SEPRZombieGame) currentGame).setLevel(nextLocation, player);	
			
	}
		
}
	
	
