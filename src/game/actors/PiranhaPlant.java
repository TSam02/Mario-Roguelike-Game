package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.speaking.Monologue;
import game.Status;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;

import java.util.ArrayList;

/**
 * Class representing Piranha Plant
 */
public class PiranhaPlant extends Enemy {

    /**
     * Constructor for Piranha Plant.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.setIntrinsicWeapon(new IntrinsicWeapon(90, "chomps"));
    }

    /**
     * Gets the allowable actions that can be done on Piranha Plant
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return super.allowableActions(otherActor, direction, map);
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        super.playTurn(actions, lastAction, map, display);

        // If player is next to Piranha Plant, add AttackBehaviour
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            Actor otherActor = destination.getActor();
            if (otherActor != null) {
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    addBehaviour(8, new AttackBehaviour(otherActor, ""));
                }
            }
        }
        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * Gets the monologue that Piranha Plant can speak
     *
     * @param target The target that the speaker is speaking to
     * @return an ArrayList of Monologue which signifies the sentences that Piranha Plant can speak
     */
    @Override
    public ArrayList<Monologue> generateMonologue(Actor target) {
        ArrayList<Monologue> sentences = new ArrayList<>();
        sentences.add(new Monologue("Slsstssthshs~! (Never gonna say goodbye~)", this, true));
        sentences.add(new Monologue("Ohmnom nom nom nom.", this, true));

        return sentences;
    }

    /**
     * Defines what would happen to Piranha Plant upon resetting
     */
    @Override
    public void resetInstance() {
        //need edit in warp pipe to spawn piranha plant if dont have it
        if (isConscious()) {
            this.increaseMaxHp(50);
            this.heal(this.getMaxHp());
        }
    }
}
