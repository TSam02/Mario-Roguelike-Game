package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for item that can be bought
 */
public interface BuyableItem {

    /**
     * Make sure classes that implements this interface would determine their prices.
     * @return the price of this item to be purchased from Toad
     */
    int getBuyableItemPrice();

    /**
     * Method to handle purchasing
     *
     * @param actor actor who purchases the buyable item
     */
    void purchasedBy(Actor actor);
}
