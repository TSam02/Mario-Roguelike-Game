package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * A Key would be dropped by Bowser when Bowser is defeated for Mario to interact with Princess Peach
 */
public class Key extends Item {

    /**
     * Sets Key as portable and gives Key capability of Status.VICTORY
     */
    public Key() {
        super("Key", 'k', true);
        addCapability(Status.VICTORY);
    }
}
