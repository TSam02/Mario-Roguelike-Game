package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Healing water that heals actor
 */
public class HealingWater extends Water {

    /**
     * Constructor
     */
    public HealingWater() {
        super("Healing Water", ' ', false);
    }

    /**
     * Method to consume healing water and heal actor
     *
     * @param actor the actor that is consuming the consumable item
     * @param map the game map that we are looking at for the actor to consume item
     */
    @Override
    public void consumeItem(Actor actor, GameMap map) {
        actor.heal(50);
    }
}
