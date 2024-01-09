package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.PickUpCoinAction;
import game.reset.Resettable;

/**
 * Coin object that can be picked up by actors to add into their wallet balance.
 */
public class Coin extends Item implements Resettable {

    /**
     * value of coin
     */
    private int value;

    /**
     * Constructor for Coin that immediately adds the PickUpCoinAction so that actors can pick it up,
     * @param value the value that this Coin represents
     */
    public Coin(int value){
        super("Coin",'$',false);
        this.value = value;
        this.registerInstance();
        addAction(new PickUpCoinAction(this));
    }

    /**
     * A method that let the coin instance experience the flow of time
     * @param currentLocation The location of the Coin
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        if (this.hasCapability(Status.RESET)) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * Gets the value of this Coin
     * @return the value of this Coin
     */
    public int getValue(){
        return value;
    }

    /**
     * Method to reset tree
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
