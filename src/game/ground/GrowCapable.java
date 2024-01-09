package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.items.FireFlower;

import java.util.Random;

/**
 * An interface called GrowCapable that provides the ability of growing into the next stages
 */
public interface GrowCapable {

    /**
     * A method that needs to override by the classes that implement this method
     * The actual implementation of growing
     * @param location Location object to indicate the location
     */
    void grow(Location location);

    /**
     * To spawn the Fire Flower at the current location if it meets the probability
     * @param location Location object to indicate the location
     */
    default void spawnFireFlower(Location location) {
        Random rand = new Random();
        final int fireFlowerSpawnChance = 50;

        //Add Fire Flower to the current location if it meets the probability of spawning
        if (rand.nextInt(100) < fireFlowerSpawnChance) {
            location.addItem(new FireFlower());
        }
    }
}

