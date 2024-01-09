package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.DestroyShellAction;
import game.actions.SuicideAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.speaking.Monologue;

import java.util.ArrayList;

/**
 * Class representing a Walking Koopa.
 */
public abstract class Koopa extends Enemy {

    /**
     * Default constructor for Walking Koopa that adds NOT_DORMANT capability, representing that Koopa has not entered DORMANT state, WanderBehaviour is also added
     *
     * @param name        name of Koopa
     * @param displayChar character to display Koopa
     * @param hitPoints   HP of Koopa
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.NOT_DORMANT);
        addBehaviour(11, new WanderBehaviour());
    }

    /**
     * Gets the allowable actions that can be done on Koopa
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // If Koopa has not entered DORMANT state, actor can attack Koopa as usual
        if (this.hasCapability(Status.NOT_DORMANT)) {
            actions = super.allowableActions(otherActor, direction, map);
        }

        // If Koopa has entered DORMANT state, actor can only destroy its shell if actor has capability HOSTILE_TO_ENEMY and DESTROY_SHELL
        else if (this.hasCapability(Status.DORMANT)) {
            if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.hasCapability(Status.DESTROY_SHELL)) {
                actions.add(new DestroyShellAction(this, direction, map));
            }
        }

        return actions;
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        super.playTurn(actions, lastAction, map, display);
        // If Koopa needs to be reset, it will just suicide
        if (this.hasCapability(Status.RESET)) {
            if (map.contains(this)) {
                return new SuicideAction(this);
            }
        }

        // Check if player is around Koopa, if yes, add AttackBehaviour and FollowBehaviour targeted towards player
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            Actor otherActor = destination.getActor();
            if (otherActor != null) {
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    addBehaviour(9, new FollowBehaviour(otherActor));
                    addBehaviour(8, new AttackBehaviour(otherActor, ""));
                }
            }
        }

        // If Koopa has entered DORMANT state, it does nothing
        if (this.hasCapability(Status.DORMANT)) {
            return new DoNothingAction();
        }

        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Gets display character of Koopa, usually is K, but after it enters dormant state, becomes D
     *
     * @return display character of player when it has entered DORMANT state and when it hasn't
     */
    @Override
    public char getDisplayChar() {
        return this.hasCapability(Status.DORMANT) ? 'D' : super.getDisplayChar();
    }

    /**
     * Gets the monologue that Koopa can speak
     *
     * @param target The target that the speaker is speaking to
     * @return an ArrayList of Monologue which signifies the sentences that Koopa can speak
     */
    @Override
    public ArrayList<Monologue> generateMonologue(Actor target) {
        ArrayList<Monologue> sentences = new ArrayList<>();
        sentences.add(new Monologue("Never gonna make you cry!", this, true));
        sentences.add(new Monologue("Koopi koopi koopii~!", this, true));

        return sentences;
    }
}
