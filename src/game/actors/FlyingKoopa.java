package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.speaking.Monologue;

import java.util.ArrayList;

/**
 * Class representing a Flying Koopa.
 */
public class FlyingKoopa extends Koopa {

    /**
     * Default constructor for Flying Koopa that sets FLyingKoopa's intrinsicWeapon with the default damage, Flying Koopa is also given capability FLY
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        this.addCapability(Status.FLY);
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
        // FlyingKoopa has similar traits with Koopa
        return super.allowableActions(otherActor, direction, map);
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // FlyingKoopa has similar traits with Koopa
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Gets the monologue that Flying Koopa can speak
     *
     * @param target The target that the speaker is speaking to
     * @return an ArrayList of Monologue which signifies the sentences that Flying Koopa can speak
     */
    @Override
    public ArrayList<Monologue> generateMonologue(Actor target) {
        ArrayList<Monologue> sentences = super.generateMonologue(target); // Get the monologue from Koopa
        sentences.add(new Monologue("Pam pam pam!", this, true));
        return sentences;
    }
}
