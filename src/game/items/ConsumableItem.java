package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeItemAction;

/**
 * Abstract class representing all the items that can be consumed
 */
public abstract class ConsumableItem extends Item{
    private Action consumeAction;

    /**
     * Constructor for consumable items and adds ConsumeItemAction so that actors can consume it.
     * @param itemName  the name of this consumable item
     * @param displayChar the display character of this consumable item
     * @param portable whether this consumable item can be picked up or dropped
     */
    public ConsumableItem(String itemName, char displayChar, boolean portable){
        super(itemName,displayChar,portable);
        consumeAction = new ConsumeItemAction(this);
        addAction(consumeAction);
    }

    /**
     * Defines the after effects of the actor consuming the consumable item
     * @param actor the actor that is consuming the consumable item
     * @param map the game map that we are looking at for the actor to consume item
     */
    public abstract void consumeItem(Actor actor, GameMap map);

    /**
     * Getter for consumeAction
     *
     * @return consumeAction
     */
    public Action getConsumeAction(){
        return consumeAction;
    }


}
