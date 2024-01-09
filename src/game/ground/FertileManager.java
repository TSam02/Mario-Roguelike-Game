package game.ground;

import java.util.ArrayList;

/**
 * Fertile Manager is a class that is responsible for maintaining all the instance that implemented the Fertile
 * interface
 */
public class FertileManager {

    /**
     * An ArrayList that containing Fertile objects, i.e. Instance of the classes that implement Fertile interfaced
     */
    private ArrayList<Fertile> fertileGroundList;

    /**
     * A static instance of Fertile Manager class
     */
    private static FertileManager instance;

    /**
     * Constructor of Fertile Manager class
     */
    //private to prevent anyone else from instantiating
    private FertileManager() { fertileGroundList = new ArrayList<>();}

    /**
     * A method that return the static instance of Fertile Manager class
     * @return instance of Fertile Manager class
     */
    public static FertileManager getInstance() {
        if (instance == null){
            instance = new FertileManager();
        }
        return instance;
    }

    /**
     * A method that append the Fertile object to the ArrayList of Fertile
     * @param ground Fertile object
     */
    public void appendFertileGround(Fertile ground) {this.fertileGroundList.add(ground);}

    /**
     * Getter for fertileGroundList
     * @return a new copy of fertileGroundList
     */
    public ArrayList<Fertile> getFertileGroundList() {
        return new ArrayList<Fertile>(this.fertileGroundList);
    }

}
