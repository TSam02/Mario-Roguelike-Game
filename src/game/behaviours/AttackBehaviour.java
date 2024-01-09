package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

/**
 * A behaviour which will be provided to an actor so that actor can automatically attack its target, which is Player for now
 */
public class AttackBehaviour implements Behaviour {
    private final Actor target;
    private final String direction;

    /**
     * Default constructor for Attack Behaviour
     *
     * @param target    the actor which the enemy is targetting to attack
     * @param direction the direction of attack from enemy to actor
     */
    public AttackBehaviour(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Gets the action that would result from having this behaviour
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an AttackAction that is targeted towards our target with the respective direction
     * @see AttackAction for more details on AttackAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // If not in range to attack target
        if (!inRange(actor, target, map)) {
            return null;
        }
        return new AttackAction(target, direction);
    }

    /**
     * Check if target is within the actor's exits, if not, actor is not in range to attack target
     *
     * @param actor  the actor that has this attack behaviour
     * @param target the target that would be attacked
     * @param map    the game map that we are referring at
     * @return if the actor is in range to attack target
     */
    private boolean inRange(Actor actor, Actor target, GameMap map) {
        // Loop through exits to find target
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getActor() == target) {
                return true;
            }
        }
        return false;
    }
}
