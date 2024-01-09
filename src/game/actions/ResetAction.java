package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

/**
 * ResetAction is a class that triggers the reset implementation of all the resettable objects
 */
public class ResetAction extends Action {

    /**
     * The execution method of ResetAction that triggers the resetting of all the resettable objects
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return String that indicate what has been done
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String resetMsg = "Game has been reset";
        ResetManager.getInstance().run();
        ResetManager.hasBeenReset();
        return resetMsg;
    }

    /**
     * A method that return the description of the menu
     *
     * @param actor The actor performing the action.
     * @return A String that shows the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }

    /**
     * A method to get the hotkey of the ResetAction
     *
     * @return A string that indicate the hotkey of ResetAction
     */
    @Override
    public String hotkey() {
        return "r";
    }
}

