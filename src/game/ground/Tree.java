package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.reset.Resettable;

import java.util.Random;

/**
 * Abstract class representing tree
 */
public abstract class Tree extends HighGround implements Resettable {

    /**
     * age of tree
     */
    private int age = 0;

    /**
     * Constructor for tree
     * @param displayChar display character of tree in map
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
    }

    /**
     * setter for age of Tree
     *
     * @param newAge new age of tree
     */
    public void setAge(int newAge) {
        age = newAge;
    }

    /**
     * getter for age of Tree
     *
     * @return age of tree
     */
    public int getAge() {
        return age;
    }

    /**
     * Override method to tick the location and increment the age of tree
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        // Check if trees should be reset or not
        if (this.hasCapability(Status.RESET)) {
            Random rand = new Random();
            int treeDieChance = 50;


            if ((rand.nextInt(100) < treeDieChance)) {
                Dirt dirt = new Dirt();
                location.setGround(dirt);
            }
            this.removeCapability(Status.RESET);
        }
        age++;
    }


    /**
     * Abstract method for spawning ability of tree
     *
     * @param location location for tree to spawn
     */
    public abstract void spawn(Location location);

    /**
     * Method to reset tree
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}


