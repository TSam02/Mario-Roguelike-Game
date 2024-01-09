package game.ground;

/**
 * The Fertile interface needs to be implemented by the ground type that are classified as fertile ground
 */
public interface Fertile {

    /**
     * This method is aim to add/register the Fertile instance that implement this class to an Array List in Fertile
     * Manager class
     */
    default void addToFertileManager(){
        FertileManager.getInstance().appendFertileGround(this);
    }
}
