package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing action to be executed for Player to win the game
 */
public class VictoryAction extends Action {
    /**
     * What happens after VictoryAction is executed
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string to display result of executing this action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor); //remove actor so the game will end
        return "CONGRATULATIONS FOR WINNING THE GAME!!";
    }

    /**
     * Sentence that will be displayed on menu
     *
     * @param actor The actor performing the action.
     * @return a string thay would be displayed on menu as option to choose this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlock Princess Peach's handcuffs with key";
    }
}
