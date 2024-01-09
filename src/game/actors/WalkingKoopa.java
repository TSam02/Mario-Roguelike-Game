package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.speaking.Monologue;

import java.util.ArrayList;

/**
 * Class representing a Walking Koopa.
 */
public class WalkingKoopa extends Koopa {

    /**
     * Default constructor for Walking Koopa that sets WalkingKoopa's intrinsicWeapon with the default damage
     */
    public WalkingKoopa() {
        super("Walking Koopa", 'K', 100);
        this.setIntrinsicWeapon(new IntrinsicWeapon(30, "punches"));
    }

    /**
     * Gets the allowable actions that can be done on Walking Koopa
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        // WalkingKoopa has similar traits with Koopa
        return super.allowableActions(otherActor, direction, map);
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // WalkingKoopa has similar traits with Koopa
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Gets the monologue that Walking Koopa can speak
     *
     * @param target The target that the speaker is speaking to
     * @return an ArrayList of Monologue which signifies the sentences that Walking Koopa can speak
     */
    @Override
    public ArrayList<Monologue> generateMonologue(Actor target) {
        //Does not have any specialise monologue, thus retrieve the general monologue of Koopa
        return super.generateMonologue(target);
    }
}

