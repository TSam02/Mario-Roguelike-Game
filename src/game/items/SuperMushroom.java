package game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;


/**
 * Super Mushroom which grants actor SUPER capability upon consumed
 * SUPER actors' MAX HP would be increased by 50, display character would be changed to M
 * Actors can also jump freely with 100% success rate and no fall damage
 * NOTE: In our implementation, player's can't consume another Super Mushroom if player still has SUPER effect
 */
public class SuperMushroom extends ConsumableItem implements BuyableItem {

    /**
     * Default constructor for Super Mushroom
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', false);
    }

    /**
     * Upon consuming Super Mushroom, actor's MAX HP will be increased by 50 and actor will be granted SUPER capability
     *
     * @param actor the actor that is consuming the consumable item
     * @param map   the game map that we are looking at for the actor to consume item
     */
    @Override
    public void consumeItem(Actor actor, GameMap map) {
        actor.increaseMaxHp(50);
        actor.addCapability(Status.SUPER);

        Location currentLocation = map.locationOf(actor);
        if (currentLocation.getItems().contains(this)) {   // check if the consumable item is on ground
            currentLocation.removeItem(this);
        } else if (actor.getInventory().contains(this)) {    // check if the consumable item is in actor's inventory
            actor.removeItemFromInventory(this);
        }

    }

    /**
     * Gets the price of Super Mushroom
     *
     * @return the price of Super Mushroom, which is $400
     */
    @Override
    public int getBuyableItemPrice() {
        return 400;
    }

    /**
     * Method for actor to purchase superMushroom, deduct wallet balance and add superMushroom to inventory
     *
     * @param actor actor who purchases the superMushroom
     */
    @Override
    public void purchasedBy(Actor actor) {
        actor.addItemToInventory(this);
    }
}

