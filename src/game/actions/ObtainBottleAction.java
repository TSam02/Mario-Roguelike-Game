package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;

/**
 * Action for actor to obtain bottle from toad
 */
public class ObtainBottleAction extends Action {

    /**
     * Execute obtainBottleAction and add the bottle to actor's inventory
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string describing the action of obtaining bottle
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(Bottle.getInstance());
        return "Bottle is obtained from Toad";
    }

    /**
     * Menu description for user to obtain the bottle from toad
     *
     * @param actor The actor performing the action.
     * @return string to describe the action that can be chosen
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains bottle from Toad.";
    }
}
