package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

import java.util.Random;

/**
 * A Sapling class which is the subclass of Tree
 * It represents the second stage of Tree
 */
public class Sapling extends Tree implements GrowCapable {

    /**
     * Constructor of Sapling class
     */
    public Sapling() {
        super('t');
        this.setFallDamage(20);
        this.setJumpSuccessRate(80);
    }

    /**
     * A method that let the Sapling instance experience the flow of time
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        //Check if current location's ground type equal to Sapling
        //Perform this layer of checking due to the resetting implementation
        if (location.getGround() == this) {
            //if its age == 10, grow into the next stage of Sapling
            if (super.getAge() == 10) {
                grow(location);
            }
            spawn(location);
        }
    }

    /**
     * A method that controls the unique spawning method of Sapling instance
     *
     * @param location The location of the Ground
     */
    @Override
    public void spawn(Location location) {
        Random rand = new Random();
        final int coinDropChance = 10;

        //Drop a $20 coin if it matches the probability
        if ((rand.nextInt(100) < coinDropChance)) {
            Coin coin = new Coin(20);
            location.addItem(coin);
        }
    }

    /**
     * A method that implement the growing of Sapling into its next stage(Mature)
     *
     * @param location Location object to indicate the location
     */
    @Override
    public void grow(Location location) {
        Mature mature = new Mature();
        location.setGround(mature);
        //Use the default method in the growCapable interface to spawn/grow the Fire Flower
        spawnFireFlower(location);
    }

    /**
     * A method to get the name of the ground
     *
     * @return A string, i.e. the name of the Ground
     */

    @Override
    public String toString() {
        return "Sapling";
    }
}
