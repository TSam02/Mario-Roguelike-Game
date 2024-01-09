package game.ground;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.FlyingKoopa;
import game.actors.Koopa;
import game.actors.WalkingKoopa;

import java.util.ArrayList;
import java.util.Random;

/**
 * A Mature class which is the subclass of Tree
 * It represents the third stage of the Tree
 */
public class Mature extends Tree {

    /**
     * An int that indicate the wither chance of Mature instance
     */
    private final int witherChance = 20;
    /**
     * An ArrayList of type Location that store the location with Fertile ground around the Mature instance
     */
    private ArrayList<Location> surroundFertileList = new ArrayList<>();

    /**
     * Constructor of Mature class
     */
    public Mature() {
        super('T');
        this.setFallDamage(30);
        this.setJumpSuccessRate(70);
    }

    /**
     * A method that let the Mature instance experience the flow of time
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Random rand = new Random();
        super.tick(location);

        //Check if current location's ground type equal to Mature
        //Perform this layer of checking due to the resetting implementation
        if (location.getGround() == this) {
            spawn(location);

            //20% chance to wither and die(become Dirt)
            if ((rand.nextInt(100) < witherChance)) {
                Dirt dirt = new Dirt();
                location.setGround(dirt);
            }
        }
    }

    /**
     * A method that controls the unique spawning ability of Mature instance
     *
     * @param location The location of the Ground
     */
    @Override
    public void spawn(Location location) {
        int turn = super.getAge();
        final int koopaSpawnChance = 15;
        boolean locationContainsActor = location.containsAnActor();
        Random rand = new Random();

        //Spawn Koopa if it matches the probability & there is no actor on the current location
        if ((rand.nextInt(100) < koopaSpawnChance) && !locationContainsActor) {
            Koopa koopa;
            if (rand.nextInt(100) < 50) {
                koopa = new WalkingKoopa();
            } else {
                koopa = new FlyingKoopa();
            }
            location.addActor(koopa);
        }

        //grow sprout at one of the surrounding fertile ground is there is one for every 5 turns
        if (turn != 0 && (turn % 5 == 0)) {
            for (Exit exit : location.getExits()) {
                Location destination = exit.getDestination();
                Ground ground = destination.getGround();
                boolean isFertile = FertileManager.getInstance().getFertileGroundList().contains(ground);

                //if the surrounding ground type is fertile, add the location to the list
                if (isFertile) {
                    surroundFertileList.add(destination);
                }
            }
            //if there is fertile ground at the surrounding of Mature, then simply choose one of them and spawn sprout
            if (!surroundFertileList.isEmpty()) {
                Sprout sprout = new Sprout();
                Location spawnLocation = surroundFertileList.get(rand.nextInt(surroundFertileList.size()));
                spawnLocation.setGround(sprout);
            }
        }
    }

    /**
     * A method to get the name of the ground
     *
     * @return A string, i.e. the name of the Ground
     */

    @Override
    public String toString() {
        return "Mature";
    }
}

