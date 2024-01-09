package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Dirt;
import game.items.Coin;

/**
 * This class represents the action of Player walking over high grounds when Player has the INVINCIBLE effect
 */
public class InvincibleWalkAction extends Action {
    private Location moveToLocation;
    private String direction;

    /**
     * Default constructor for InvincibleWalkAction
     * @param moveToLocation the location actor would be moved to
     * @param direction direction of moving actor to the desired location
     */
    public InvincibleWalkAction(Location moveToLocation, String direction){
        this.moveToLocation = moveToLocation;
        this.direction = direction;

    }

    /**
     * Execution of this action, which converts high grounds to dirt and drop a $5 coin and move actor to the desired location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string mentioning actor has moved
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        MoveActorAction moveActorAction = new MoveActorAction(moveToLocation, direction);
        moveToLocation.setGround(new Dirt());
        moveToLocation.addItem(new Coin(5));
        return moveActorAction.execute(actor,map);
    }

    /**
     * Displays the description of action to move actor in console
     * @param actor The actor performing the action.
     * @return Description of action to move actor
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves " + direction;
    }
}
