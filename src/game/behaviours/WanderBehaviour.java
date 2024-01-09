package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Behaviour to allow enemy to wander around
 */
public class WanderBehaviour extends Action implements Behaviour {

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Random random = new Random();
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        } else {
            return null;
        }

    }

    /**
     * Describes what would happen after executing this Action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return string to describe about the enemy
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * Describe what would be displayed on menu as an option
     *
     * @param actor The actor performing the action.
     * @return string to describe about the enemy
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Raagrh...";
    }
}

