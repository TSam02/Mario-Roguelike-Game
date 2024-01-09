package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItem;

/**
 * Action for consuming consumable items
 */
public class ConsumeItemAction extends Action {

    /**
     * Consumable item
     */
    protected ConsumableItem consumableItem;

    /**
     * Default constructor for this action
     * @param consumableItem the item that will be consumed
     */
    public ConsumeItemAction(ConsumableItem consumableItem){
        this.consumableItem = consumableItem;
    }

    /**
     * Execution of consuming the consumable item
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message showing showing that actor has consumed consumable item
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumableItem.consumeItem(actor,map);    // actor consume item and after effects will be applied to actor
        return actor + " has consumed " + consumableItem + "!!";
    }

    /**
     * Consume item action description showed on console
     * @param actor The actor performing the action.
     * @return description of consume item action to be displayed on console
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumableItem;
    }
}

