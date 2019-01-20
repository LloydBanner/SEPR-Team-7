package sprites.items;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.zombiegame.game.SEPRZombieGame;

import sprites.Player;

public class Door extends Item {

	private int nextLocation;
	private Game currentGame;
	private MissionItem key;
    private static Sprite door = new Sprite(new Texture("img/door.png"));
	
	public Door(int nextLocation, TiledMapTileLayer collisionLayer, 
				Player player, Game currentGame, MissionItem key) {
		
		super(door, collisionLayer, player);
		this.nextLocation = nextLocation;
		this.currentGame = currentGame;
		this.key = key;
	
	}
	
	@Override
	public void interact(Player player) {
		
		if (player.hasMissionItem(key)) {
			
			player.removeMissionItem(key);
			((SEPRZombieGame) currentGame).setLevel(nextLocation);
			
		} 
	}
		
}
	
	
