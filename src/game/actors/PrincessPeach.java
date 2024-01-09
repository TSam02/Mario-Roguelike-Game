package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;
import game.speaking.Monologue;
import game.Status;
import game.actions.VictoryAction;

import java.util.ArrayList;

/**
 * Class representing Princess Peach which can be interacted with Player to end the game
 */
public class PrincessPeach extends NonPlayerCharacter {

    /**
     * Constructor for Princess Peach
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 1000);
    }

    /**
     * Provides VictoryAction to Player if Player has obtained Key from defeating Bowser
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions that can be done on Princess Peach
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = new ActionList();
        list = super.allowableActions(otherActor, direction, map);
        // If otherActor has Status.VICTORY means otherActor has Key
        if (otherActor.hasCapability(Status.VICTORY)) {
            list.add(new VictoryAction());
        }

        return list;
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Call super's play turn to get speak behaviour
        super.playTurn(actions, lastAction, map, display);

        // Princess Peach will only have SpeakBehaviour to getAction from
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
        sentences.add(new Monologue("Dear Mario, I'll be waiting for you...", this, true));
        sentences.add(new Monologue("Never gonna give you up!", this, true));
        sentences.add(new Monologue("Release me, or I will kick you!", this, true));

        return sentences;
    }
}

