package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.RefillBottleAction;
import game.items.Bottle;
import game.items.Water;

/**
 * Abstract class fountain which contains water that will give effect upon consuming
 */
public abstract class Fountain extends Ground implements Drinkable{

    /**
     * Water of the fountain
     */
    protected Water water;

    /**
     * Amount of water in the fountain
     */
    protected int waterAmount;

    /**
     * Maximum capacity of water amount of the fountain
     */
    protected int capacity;

    /**
     * Turns for fountain to replenish when it get exhausted
     */
    protected int turnsToReplenish;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        waterAmount = 10;
        capacity = 10;
        turnsToReplenish = 5;
    }

    /**
     * Method to get water of this fountain
     *
     * @return water
     */
    public Water getWater() {
        return water;
    }

    /**
     * Method to get amount of water
     *
     * @return integer number representing amount of water
     */
    public int getWaterAmount() {
        return waterAmount;
    }

    /**
     * Method to refill water in the fountain when fountain still has enough water
     */
    public abstract void refillWater();

    /**
     * Method to replenish fountain to full capacity after the fountain gets exhausted
     */
    public void replenishFountain() {
        waterAmount = capacity;
    }

    /**
     * Method to decrease water in fountain
     *
     * @param waterDrunk integer amount of water decreased
     */
    public void decreaseWater(int waterDrunk) {
        waterAmount -= waterDrunk;
    }

    /**
     * Overridden method for enemy to drink water from fountain which refill and decrease water
     */
    @Override
    public void drink() {
        this.refillWater();
        this.decreaseWater(5);
    }

    /**
     * Method to check if the fountain is exhausted and replenish it by tracking the counter of turnsToReplenish
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // if fountain is empty
        if (waterAmount == 0) {
            if (turnsToReplenish > 0){
                turnsToReplenish -= 1;
            }
            else {
                replenishFountain();
                turnsToReplenish = 5;
            }
        }
    }

    /**
     * Methods to get the list of actions for the fountain which would add refill bottle action if actor stands on it
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (location.containsAnActor()
                && actor.hasCapability(Status.HOSTILE_TO_ENEMY)
                && actor.getInventory().contains(Bottle.getInstance())
                && waterAmount > 0) {
            refillWater();
            actions.add(new RefillBottleAction(this, Bottle.getInstance()));
        }
        return actions;
    }

}
