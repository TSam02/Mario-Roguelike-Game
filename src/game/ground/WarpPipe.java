package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.TeleportAction;
import game.actors.PiranhaPlant;
import game.reset.Resettable;

/**
 * Warp pipe which spawns piranha plant, kill it to teleport here
 */
public class WarpPipe extends HighGround implements Resettable {

    /**
     * Contain information of gameMap and location where the actor uses to teleport to this warp pipe
     */
    private GameMap destinationMap;
    private Location destinationLocation;
    private WarpPipe destinationWarpPipe;
    private boolean hasSpawned;

    /**
     * 3 parameters constructor
     *
     * @param destinationMap map to teleport to
     * @param destinationLocation location to teleport to
     * @param destinationWarpPipe warp pipe to teleport to
     */
    public WarpPipe(GameMap destinationMap, Location destinationLocation, WarpPipe destinationWarpPipe) {
        super('C');
        this.setJumpSuccessRate(100);
        this.setFallDamage(0);
        this.hasSpawned = false;
        this.destinationMap = destinationMap;
        this.destinationLocation = destinationLocation;
        this.destinationWarpPipe = destinationWarpPipe;
    }

    /**
     * 0 parameter constructor
     */
    public WarpPipe() {
        super('C');
        this.setJumpSuccessRate(100);
        this.setFallDamage(0);
        this.hasSpawned = false;
    }

    /**
     * Setter for destination location
     *
     * @param destinationLocation location to teleport to
     */
    public void setDestinationLocation(Location destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    /**
     * Setter for destination warp pipe
     *
     * @param destinationWarpPipe warp pipe to teleport to
     */
    public void setDestinationWarpPipe(WarpPipe destinationWarpPipe) {
        this.destinationWarpPipe = destinationWarpPipe;
    }

    /**
     * Setter for destination map
     *
     * @param destinationMap mao to teleport to
     */
    public void setDestinationMap(GameMap destinationMap) {
        this.destinationMap = destinationMap;
    }

    /**
     * Method to spawn piranha on warp pipe for th first time and every time reset game get called
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // if haven't spawn: case1: first time   case2: due to reset
        if (!this.hasSpawned) {
            this.hasSpawned = true;

            // case1: haven't spawn and case2b: previous piranha in this place is killed, piranha doesn't exist here
            // if an actor is here, don't spawn
            if (location.getActor() == null) {
                location.addActor(new PiranhaPlant());
            }
        }
    }

    /**
     * Method to return teleport action if actor standing on it is plauer
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);

        if (location.getActor() != null && location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new TeleportAction(destinationMap, destinationLocation, destinationWarpPipe,
                    location, this, location.map()));
        }

        return actions;
    }

    /**
     * Method to set hasSpawned to false
     */
    @Override
    public void resetInstance() {
        this.hasSpawned = false;
    }

    /**
     * Method to return name of warp pipe
     *
     * @return string name of warp pipe
     */
    @Override
    public String toString() {
        return "Warp Pipe";
    }
}
