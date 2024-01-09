package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ObtainBottleAction;
import game.speaking.Monologue;
import game.Status;
import game.actions.TradeAction;
import game.actions.SpeakAction;
import game.behaviours.Behaviour;
import game.items.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A guy that stands in the middle, speaks to actor and sells item, Toad
 */
public class Toad extends NonPlayerCharacter {

    /**
     * List of buyableItem that can be bought from toad
     */
    private ArrayList<BuyableItem> buyableItemList;

    /**
     * Constructor for toad
     */
    public Toad() {
        super("Toad", 'O', 1000);
        refillBuyableItemList();
    }

    /**
     * Method to refill item inventory before every tradeAction happens
     */
    public void refillBuyableItemList() {
        this.buyableItemList = new ArrayList<>(Arrays.asList(new PowerStar(11), new SuperMushroom(), new Wrench()));
    }

    /**
     * Method to add allowableActions to list
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of allowableActions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);

        //If the other actor is player, then add a speak action for it, else return empty list
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            list.add(new SpeakAction(this));

            refillBuyableItemList();
            for (BuyableItem buyableItem : this.buyableItemList) {
                list.add(new TradeAction(buyableItem));
            }

            if (!otherActor.getInventory().contains(Bottle.getInstance())) {
                list.add(new ObtainBottleAction());
            }
        }

        return list;
    }

    /**
     * Give toad its turn
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to do nothing
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        super.playTurn(actions, lastAction, map, display);
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
        boolean hasWrench = false, isInvincible = false;

        //If toad is speaking with the other actor, then check the condition, else always isAvailable will always be true
        if (target != this) {
            hasWrench = target.hasCapability(Status.DESTROY_SHELL);
            isInvincible = target.hasCapability(Status.INVINCIBLE);
        }

        //If the player does not hold a Wrench, or he is self speaking, Toad will speak this as one of the option
        sentences.add(new Monologue("You might need a wrench to smash Koopa's hard shells.", this, !hasWrench));

        //If the player power star's effect is not active, or he is self speaking, Toad will speak this as one of the option
        sentences.add(new Monologue("You better get back to finding the Power Stars.", this, !isInvincible));
        sentences.add(new Monologue("The Princess is depending on you! You are our only hope.", this, true));
        sentences.add(new Monologue("Being imprisoned in these walls can drive a fungus crazy :(", this, true));

        return sentences;
    }
}