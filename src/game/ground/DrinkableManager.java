package game.ground;

import edu.monash.fit2099.engine.positions.Location;

import java.util.HashMap;

/**
 * Drinkable manager to manages all the drinkable ground
 */
public class DrinkableManager {

    /**
     * Hashmap to store all drinkable grounds with location as key, drinkable ground as value
     */
    private HashMap<Location, Drinkable> drinkableGroundCollection;

    /**
     * Instance of drinkableManager
     */
    private static DrinkableManager instance;

    /**
     * Private constructor for hashmap drinkableGroundList
     */
    private DrinkableManager(){
        drinkableGroundCollection = new HashMap<>();
    }

    /**
     * Method to get instance of drinkableManager or initialize the instance if it has not been done yet
     *
     * @return instance of drinkable manager
     */
    public static DrinkableManager getInstance(){
        if (instance == null){
            instance = new DrinkableManager();
        }
        return instance;
    }

    /**
     * Method to append drinkable ground to hashmap of drinkable manager
     *
     * @param location location of the drinkable ground
     * @param drinkableGround drinkable ground
     */
    public void appendDrinkableGround(Location location, Drinkable drinkableGround){
        drinkableGroundCollection.put(location, drinkableGround);
    }

    /**
     * Method to get a copy of the drinkable ground hashMap
     *
     * @return a copy of hashmap of drinkableGroundCollection
     */
    public HashMap<Location, Drinkable> getDrinkableGroundCollection() {
        return new HashMap<>(drinkableGroundCollection);
    }
}
