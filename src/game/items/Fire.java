package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A Fire would be dropped upon a Fire Attack that burns anyone for 20 damage. Fire would fade after 3 turns
 */
public class Fire extends Item {
    private int turnsLeft = 3;

    /**
     * Default constructor for Fire. Fire is not portable.
     */
    public Fire() {
        super("Fire", 'v', false);
    }

    /**
     * Allow Fire to burn any actors standing on it and fade after 3 turns
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if (this.turnsLeft == 0){
            currentLocation.removeItem(this);
        }
        else{
            if (currentLocation.containsAnActor()) {
                Actor target = currentLocation.getActor();
                target.hurt(20);

                if (!target.isConscious()) {
                    //If target is Koopa, make Koopa enter dormant state
                    if (target.hasCapability(Status.NOT_DORMANT)){
                        target.addCapability(Status.DORMANT);
                        target.removeCapability(Status.NOT_DORMANT);

                    //If target is not Koopa, drop all target's items and remove target from map
                    } else if (!target.hasCapability(Status.DORMANT)) {
                        ActionList dropActions = new ActionList();
                        // drop all items
                        for (Item item : target.getInventory())
                            dropActions.add(item.getDropAction(target));
                        for (Action drop : dropActions)
                            drop.execute(target, currentLocation.map());
                        // remove actor
                        currentLocation.map().removeActor(target);
                    }
                }
            }
            this.turnsLeft -= 1;
        }
    }
}
