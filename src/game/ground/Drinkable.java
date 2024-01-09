package game.ground;

import game.items.Water;

/**
 * Interface for all grounds have water and are drinkable
 */
public interface Drinkable {

    /**
     * Method to get the amount of water in the drinkable ground
     *
     * @return integer number of water amount
     */
    int getWaterAmount();

    /**
     * Method to get water of the drinkable ground
     * @return water
     */
    Water getWater();

    /**
     * Method to drink water from drinkable ground
     */
    void drink();

}


