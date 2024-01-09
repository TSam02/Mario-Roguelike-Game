package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Enemy;
import game.actors.Player;

/**
 * Power water that empowers actors' attacks
 */
public class PowerWater extends Water {

    /**
     * Constructor
     */
    public PowerWater() {
        super("Power Water", ' ', false);
    }

    /**
     * Method to consume item and empower actor's intrinsic weapon damage
     *
     * @param actor the actor that is consuming the consumable item
     * @param map the game map that we are looking at for the actor to consume item
     */
    @Override
    public void consumeItem(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            ((Player) actor).empowerIntrinsicWeapon();
        } else {
            ((Enemy) actor).empowerIntrinsicWeapon();
        }
    }
}
