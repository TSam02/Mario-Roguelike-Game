package game.ground;

import game.items.HealingWater;

/**
 * Health fountain containing healing water
 */
public class HealthFountain extends Fountain {

    /**
     * Constructor for health fountain
     */
    public HealthFountain() {
        super('H');
    }

    /**
     * Refill healing water in health fountain
     */
    public void refillWater() {
        water = new HealingWater();
    }

    /**
     * Method to return name of health fountain and status of water amount
     *
     * @return string to describe health fountain
     */
    @Override
    public String toString() {
        return "Health Fountain (" + waterAmount + "/" + capacity + ")";
    }
}
