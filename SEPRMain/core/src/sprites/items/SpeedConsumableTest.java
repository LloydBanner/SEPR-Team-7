package sprites.items;

import static org.junit.Assert.*;

import org.junit.Test;

import sprites.Player;

public class SpeedConsumableTest {

	private SpeedConsumable speedConsumable = new SpeedConsumable(50);
	private Player player = new Player();
	
	@Test
	public void testConsume() {
		speedConsumable.consume(player);
		assertEquals(170, player.getSpeed());
		
	}

}
