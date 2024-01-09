package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.reset.Resettable;


/**
 * Power Star which grants actor INVINCIBLE capability upon consumed
 * INVINCIBLE actors would be healed by 200 HP, walk normally over high grounds
 * Any high grounds being stepped on would be converted to Dirt and Coins would be dropped
 * Actors would also receive no damage when attack and actors would be able to instantly kill enemies if actors didn't miss
 * NOTE: In our implementation, players can't consume another Power Star if player still has INVINCIBLE effect
 */
public class PowerStar extends ConsumableItem implements BuyableItem, Resettable {

    /**
     * Turns left of effect of powerStar
     */
    private int turnsLeft = 10;

    /**
     * Default constructor for Power Star
     */
    public PowerStar() {
        super("Power Star", '*', false);
        this.registerInstance();
    }

    /**
     * Constructor for Power Star when Power Star is created in Toad's inventory
     *
     * @param turnsLeft the turns left until Power Star fades
     */
    public PowerStar(int turnsLeft) {
        super("Power Star", '*', false);
        this.turnsLeft = turnsLeft;
        this.registerInstance();
    }

    /**
     * Consume Power Star would heal actor by 200 HP and give actor the INVINCIBLE capability
     *
     * @param actor the actor that is consuming the consumable item
     * @param map   the game map that we are looking at for the actor to consume item
     */
    @Override
    public void consumeItem(Actor actor, GameMap map) {
        actor.heal(200);
        this.addCapability(Status.INVINCIBLE);     // invincible effect comes from holding power star
        this.resetTurns();
        this.removeAction(getConsumeAction());           // remove consume item action after consumimg power star

        if (!actor.getInventory().contains(this)) { // if we are consuming power star directly from ground, add it to our inventory as INVINCIBLE effect comes from holding power star
            actor.addItemToInventory(this);
        }
    }

    /**
     * Tick method when Power Star is on the ground
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        this.turnsLeft -= 1;

        if (this.turnsLeft == 0) {
            currentLocation.removeItem(this);      // if turns left reach 0, remove it from ground so that it fades
        }
    }

    /**
     * Tick method when Power Star is in player's inventory
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        //If it needs to be reset, it will be removed from the inventory to reset the player's status
        if (this.hasCapability(Status.RESET)) {
            actor.removeItemFromInventory(this);
        }

        super.tick(currentLocation);
        this.turnsLeft -= 1;
        if (this.turnsLeft == 0) {
            actor.removeItemFromInventory(this);    // if turns left reach 0, remove from inventory so that it fades
        }
    }

    /**
     * Reset turnsLeft to 11 so that in the game player actually has 10 turns of invincibility
     */
    public void resetTurns() {
        this.turnsLeft = 11;
    }

    /**
     * Give price of powerStar
     *
     * @return price of powerStar
     */
    @Override
    public int getBuyableItemPrice() {
        return 600;
    }

    /**
     * Method for actor to purchase powerStar, deduct wallet balance and add powerStar to inventory
     *
     * @param actor actor who purchases powerStar
     */
    @Override
    public void purchasedBy(Actor actor) {
        actor.addItemToInventory(this);
    }

    /**
     * Describe state of powerStar
     *
     * @return turns left of powerStar
     */
    @Override
    public String toString() {
        if (this.turnsLeft == 11) {       // When turns left for Power Star is 11, means its in Toad's inventory
            return super.toString();     // In this case, we just return name of Power Star without the turns left
        } else {
            if (this.hasCapability(Status.INVINCIBLE)) {
                return super.toString() + " (" + this.turnsLeft + " turns of effect left)";
            } else {
                return super.toString() + " (" + this.turnsLeft + " turns left)"; // Returns name of Power Star with turns left as either Power Star is on ground or in inventory
            }
        }

    }

    /**
     * Method to reset enemy
     */
    @Override
    public void resetInstance() {
        //If the PowerStar has been consumed by the player, it will need to be reset
        if (this.hasCapability(Status.INVINCIBLE)) {
            this.addCapability(Status.RESET);
        }
    }
}
