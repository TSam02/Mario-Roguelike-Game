package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.speaking.Monologue;
import game.Status;
import game.actions.SuicideAction;
import game.behaviours.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {

    /**
     * Constructor for Goomba. Goomba would be given WanderBehaviour as it can wander around
     */
    public Goomba() {
        super("Goomba", 'g', 20);
        addBehaviour(11, new WanderBehaviour());
        this.setIntrinsicWeapon(new IntrinsicWeapon(10, "kicks"));
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        super.playTurn(actions, lastAction, map, display);
        Random rand = new Random();

        // Goomba has a 10% chance to suicide or will suicide when Goomba is resetted
        if ((rand.nextInt(100) < 10) || this.hasCapability(Status.RESET)) {
            if (map.contains(this)) {
                return new SuicideAction(this);
            }
        }

        // If player is beside Goomba, Goomba will be given FollowBehaviour and AttackBehaviour
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

        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Generate the possible monologues that can be spoken by the speaker
     *
     * @param target The target that the speaker is speaking to
     * @return An ArrayList of Monologue which stores the possible monologues that can be spoken
     */
    @Override
    public ArrayList<Monologue> generateMonologue(Actor target) {
        ArrayList<Monologue> sentences = new ArrayList<>();
        sentences.add(new Monologue("Mugga mugga!", this, true));
        sentences.add(new Monologue("Ugha ugha... (Never gonna run around and desert you...)", this, true));
        sentences.add(new Monologue("Ooga-Chaka Ooga-Ooga!", this, true));

        return sentences;
    }
}
