package game.ground;

import game.items.PowerWater;

/**
 * Power fountain containing power water
 */
public class PowerFountain extends Fountain {

    /**
     * Constructor for powerFountain
     */
    public PowerFountain() {
        super('A');
    }

    /**
     * Method to refill power water
     */
    @Override
    public void refillWater() {
        water = new PowerWater();
    }

    /**
     * Method to return name and status of water amount of fountain
     *
     * @return string to describe power fountain
     */
    @Override
    public String toString() {
        return "Power Fountain (" + waterAmount + "/" + capacity + ")";
    }
}
