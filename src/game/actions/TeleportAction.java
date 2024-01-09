package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.WarpPipe;

/**
 * Action to let actor teleport from warp pipe in a map to the warp pipe of another map
 */
public class TeleportAction extends Action {

    /**
     * GameMap to teleport to
     */
    private GameMap destinationMap;

    /**
     * Location to teleport to
     */
    private Location destinationLocation;

    /**
     * Warp pipe to teleport to
     */
    private WarpPipe destinationWarpPipe;

    /**
     * Location to teleport from
     */
    private Location sourceLocation;

    /**
     * Warp pipe to teleport from
     */
    private WarpPipe sourceWarpPipe;

    /**
     * GameMap to teleport from
     */
    private GameMap sourceMap;

    /**
     * Constructor for teleport action
     *
     * @param destinationMap map of the destination
     * @param destinationLocation location of the destination
     * @param destinationWarpPipe warp pipe of the destination
     * @param sourceLocation location of the source
     * @param sourceWarpPipe warp pipe of the source
     * @param sourceMap map of the source
     */
    public TeleportAction(GameMap destinationMap, Location destinationLocation, WarpPipe destinationWarpPipe,
                          Location sourceLocation, WarpPipe sourceWarpPipe, GameMap sourceMap) {
        this.destinationMap = destinationMap;
        this.destinationLocation = destinationLocation;
        this.destinationWarpPipe = destinationWarpPipe;
        this.sourceLocation = sourceLocation;
        this.sourceWarpPipe = sourceWarpPipe;
        this.sourceMap = sourceMap;
    }

    /**
     * Execute the teleport action by moving actor
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string description of the outcome of teleportation
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = "";

        // if actor teleports onto a piranha plant, destroy it
        if (destinationMap.isAnActorAt(destinationLocation)) {
            destinationMap.removeActor(destinationMap.getActorAt(destinationLocation));
            result = actor + " teleports onto another actor at the destination map, killing the actor!";
        } else {
            result = actor + " successfully teleports to destination map.";
        }

        // move actor to destination map
        map.moveActor(actor, destinationLocation);

        // record the information of from location to the warp pipe on destination location
        destinationWarpPipe.setDestinationMap(sourceMap);
        destinationWarpPipe.setDestinationLocation(sourceLocation);
        destinationWarpPipe.setDestinationWarpPipe(sourceWarpPipe);

        return result;
    }

    /**
     * Menu description for the user to teleports
     *
     * @param actor The actor performing the action.
     * @return string description of the teleport action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to destination map.";
    }
}
