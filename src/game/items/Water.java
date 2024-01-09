package game.items;

/**
 * Water that can be consumed and brings magical effects
 */
public abstract class Water extends ConsumableItem{

    /**
     * Constructor for consumable items and adds ConsumeItemAction so that actors can consume it.
     *
     * @param itemName    the name of this consumable item
     * @param displayChar the display character of this consumable item
     * @param portable    whether this consumable item can be picked up or dropped
     */
    public Water(String itemName, char displayChar, boolean portable) {
        super(itemName, displayChar, portable);
    }
}
