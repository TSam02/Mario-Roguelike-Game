package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Fire flower
 */
public class FireFlower extends ConsumableItem {

    /**
     * Turns left for effect
     */
    private int turnsLeft = 21;

    /***
     * Constructor
     */
    public FireFlower() {
        super("FireFlower", 'f', false);
    }

    @Override
    public void consumeItem(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        if (currentLocation.getItems().contains(this)) {   // check if the consumable item is on ground
            currentLocation.removeItem(this);
        }
        this.addCapability(Status.FIRE_ATTACK);
        actor.addItemToInventory(this);
        this.removeAction(getConsumeAction());
    }

    /**
     * Tick method when fire flower is in player's inventory after consuming
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);

        turnsLeft -= 1;
        if (turnsLeft <= 0) {
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Method to describe fireFlower and turns left
     *
     * @return string description of fireFlower and turns left for effect
     */
    @Override
    public String toString() {
        if (this.turnsLeft == 21) {
            return super.toString();
        } else {
            if (this.hasCapability(Status.FIRE_ATTACK)) {
                return super.toString() + " (" + turnsLeft + " turns of effect left)";
            } else {
                return super.toString();
            }
        }
    }
}
