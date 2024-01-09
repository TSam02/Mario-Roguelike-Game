package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

import java.util.Random;

/**
 * A Sapling class which is the subclass of Tree
 * It represents the first stage of Tree
 */
public class Sprout extends Tree implements GrowCapable {

    /**
     * Constructor of Sprout class
     */
    public Sprout() {
        super('+');
        this.setFallDamage(10);
        this.setJumpSuccessRate(90);
    }


    /**
     * A method that let the Sapling instance experience the flow of time
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        //Check if current location's ground type equal to Sprout
        //Perform this layer of checking due to the resetting implementation
        if (location.getGround() == this) {
            //If its age == 10, grow into the next stage of Sprout
            if (super.getAge() == 10) {
                grow(location);
            }
            spawn(location);
        }

    }

    /**
     * A method that controls the unique spawning method of Sprout
     *
     * @param location The location of the Ground
     */
    @Override
    public void spawn(Location location) {
        Random rand = new Random();
        final int goombaSpawnChance = 10;

        //Spawn Goomba if it matches the probability & there is no actor on the current location
        if ((rand.nextInt(100) < goombaSpawnChance) && (!location.containsAnActor())) {
            Goomba goomba = new Goomba();
            location.addActor(goomba);
        }
    }

    /**
     * A method that implements the growing of Sprout into its next stage(Sapling)
     *
     * @param location Location object to indicate the location of the Ground
     */
    @Override
    public void grow(Location location) {
        //Grow into sapling
        Sapling sapling = new Sapling();
        location.setGround(sapling);
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
        return "Sprout";
    }
}
