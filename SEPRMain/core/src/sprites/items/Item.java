package sprites.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import sprites.Player;
import java.util.HashMap;

public abstract class Item extends Sprite {

    // Tells us if item is in map or in Player inventory
    public boolean isTaken;

    public void takeItem (Item item, Player player) {

        HashMap<Item, Integer> inventory = player.getInventory();

        if (inventory.containsKey(item)) {

            int oldValue = inventory.get(item);
            player.addItem(item, oldValue + 1);
            item.isTaken = true;

        } else {

            player.addItem(item, 1);
            item.isTaken = true;

        }
    }

    public void dropItem (Item item, Player player) {

        HashMap<Item, Integer> inventory = player.getInventory();

        if (inventory.containsKey(item)) {

            if (inventory.get(item) > 1) {

                int oldValue = inventory.get(item);
                player.addItem(item, oldValue - 1);
                item.isTaken = false;

            } else {

                player.removeItem(item);
                item.isTaken = false;

            }

        } else {

            // replace with appropriate method
            System.out.println("Player does not have item");

        }
    }
}