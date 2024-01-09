package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action to present the suicidal of NPCs
 */
public class SuicideAction extends Action {

    /**
     * Actor who suicides
     */
    protected Actor actor;

    /**
     * Default constructor Suicide Action
     *
     * @param actor that is going to suicide
     */
    public SuicideAction(Actor actor) {
        this.actor = actor;
    }

    /**
     * Execution of SuicideAction will remove NPC and print out a message
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return the message of NPC suiciding if it exists
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (map.contains(actor)) {
            map.removeActor(actor);
            return menuDescription(actor);
        }
        return "";

    }

    /**
     * Return a message saying actor has suicided
     *
     * @param actor The actor performing the action.
     * @return string description of the suicide
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " decided that life is tough and it suicided... ";
    }
}
