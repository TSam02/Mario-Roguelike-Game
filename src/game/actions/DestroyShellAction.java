package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Koopa;
import game.items.SuperMushroom;

/**
 * This action represents the action of actors destroying Koopa's shell after Koopa entered dormant state
 */
public class DestroyShellAction extends Action {

    /**
     * Target koopa
     */
    protected Koopa target;

    /**
     * GameMap of koopa
     */
    protected GameMap map;

    /**
     * Direction of koopa
     */
    protected String direction;

    /**
     * Default constructor for DestroyShellAction
     * @param target the Koopa who's shell would be destroyed
     * @param direction where Koopa's hard shell would be destroyed
     * @param map the game map that this action is taking place in
     */
    public DestroyShellAction(Koopa target, String direction, GameMap map){
        this.target = target;
        this.map = map;
        this.direction = direction;
    }

    /**
     * Execution of DestroyShellAction would remove Koopa from map and drop a super mushroom
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message saying Koopa's hard shell was destroyed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(target).addItem(new SuperMushroom());
        map.removeActor(target);
        return "Poor Koopa's shell is destroyed..But Super Mushroom is dropped!";
    }

    /**
     * Destroy shell option showed on console
     * @param actor The actor performing the action.
     * @return sentence saying actor could destroy Koopa's shell
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroys " + target + "'s shell at " + direction;
    }
}

