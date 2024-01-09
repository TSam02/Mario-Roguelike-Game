package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

/**
 * Wrench is a weapon item that can be bought from Toad that enables the actor to destroy Koopa's shell.
 */
public class Wrench extends WeaponItem implements BuyableItem {
    /**
     * Constructor for wrench that automatically gives the actor that holds it the ability to destroy Koopa's shell.
     */
    public Wrench() {
        super("Wrench", 'p', 50, "whacks", 80);
        //togglePortability();
        addCapability(Status.DESTROY_SHELL);
    }

    /**
     * Gets the price to purchase Wrench from Toad
     *
     * @return the price of Wrench, which is $200
     */
    @Override
    public int getBuyableItemPrice() {
        return 200;
    }

    /**
     * Method for actor to purchase wrench, deduct wallet balance and add wrench to inventory
     *
     * @param actor actor who purchases the wrench
     */
    @Override
    public void purchasedBy(Actor actor) {
        actor.addItemToInventory(this);
    }
}
