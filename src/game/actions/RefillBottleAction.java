package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.Fountain;
import game.items.Bottle;

/**
 * Action to refill bottle with water from fountain
 */
public class RefillBottleAction extends Action {

    /**
     * Fountain which contains limited amount of water of specific type
     */
    private Fountain fountain;

    /**
     * Bottle to be refilled
     */
    private Bottle bottle;

    /**
     * Constructor for refillBottleAction
     *
     * @param fountain fountain to refill water from
     * @param bottle bottle to be refilled
     */
    public RefillBottleAction(Fountain fountain, Bottle bottle) {
        this.fountain = fountain;
        this.bottle = bottle;
    }

    /**
     * Execute refill bottle action by decrease water in fountain and fill bottle with water
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string description of the water filled into the water and the fountain where the action is done
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        fountain.decreaseWater(1);
        bottle.fillWater(fountain.getWater());
        return actor + " refills bottle from " + fountain + " with " + fountain.getWater() + ".";
    }

    /**
     * Menu description for user to refill bottle action
     *
     * @param actor The actor performing the action.
     * @return string to describe the action of refilling bottle
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills " + fountain.getWater() + " from " + fountain;
    }
}
