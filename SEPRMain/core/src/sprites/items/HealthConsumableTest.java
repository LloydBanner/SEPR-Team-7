package sprites.items;

import static org.junit.Assert.*;

import org.junit.Test;

import sprites.Player;

public class HealthConsumableTest {

	private HealthConsumable healthConsumable = new HealthConsumable(2);
	private Player player = new Player();
	
	@Test
	public void testConsumeHalfHealth() {
		player.decreaseHealth(2);
		healthConsumable.consume(player);
		assertEquals(4, player.getHealth());
	}
	
	public void testConsumableFullHealth(){
		healthConsumable.consume(player);
		assertEquals(4, player.getHealth());
		
	}
	

}
