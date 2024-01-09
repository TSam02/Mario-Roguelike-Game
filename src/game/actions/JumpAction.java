package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.ground.HighGround;

import java.util.Random;

/**
 * Action for actors to jump onto highGround
 */
public class JumpAction extends Action {

    /**
     * direction to jump to
     */
    private String direction;

    /**
     * location to jump to
     */
    private Location destination;

    /**
     * highGround to jump onto
     */
    private HighGround highGround;


    /**
     * Constructor for jumpAction
     *
     * @param direction   direction to jump to
     * @param destination destination to jump to
     * @param highGround  highGround to jump onto
     */
    public JumpAction(String direction, Location destination, HighGround highGround) {
        this.direction = direction;
        this.destination = destination;
        this.highGround = highGround;
    }

    /**
     * Execute jumpAction
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A string describing if the jump succeeds
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random rand = new Random();
        String result;
        double jumpSuccessRate = highGround.getJumpSuccessRate();
        int fallDamage = highGround.getFallDamage();

        // if actor consumed super mushroom and effect still lasts
        if (actor.hasCapability(Status.SUPER)) {
            jumpSuccessRate = 100;
        }

        // check if actor's jump fails
        if (rand.nextInt(100) >= jumpSuccessRate) {
            actor.hurt(fallDamage);
            result = actor + " falls during the jump!!! Receives " + fallDamage + " damage.";
            // check if actor dies
            if (!actor.isConscious()) {
                result += "\n" + actor + " is dead.";
            }
        } else {
            result = actor + " successfully jumps " + direction + " onto the " + highGround;
            map.moveActor(actor, destination);
        }
        return result;
    }

    /**
     * Method to print description of the option that player can choose
     *
     * @param actor The actor performing the action.
     * @return string describing the action of jumping to the direction
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " jumps to " + direction);
    }
}
