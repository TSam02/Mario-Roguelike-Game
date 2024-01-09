package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
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
import game.behaviours.FollowBehaviour;
import game.items.Key;

import java.util.ArrayList;

/**
 * Class representing Bowser
 */
public class Bowser extends Enemy {

    /**
     * Default constructor for Bowser. Key is added into Bowser's inventory which will be dropped when Bowser is defeated.
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        this.addCapability(Status.FIRE_ATTACK);
        this.addItemToInventory(new Key());
        this.setIntrinsicWeapon(new IntrinsicWeapon(80, "punches"));
    }

    /**
     * Gets the allowable actions that can be done on Bowser
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

        if (hasCapability(Status.RESET)){
            if (isConscious()) {
                this.heal(this.getMaxHp());
                this.getBehaviours().clear(); // Remove Bowser's behaviours upon resetting
                // Check if there is other actors, if yes, remove the actor
                if (map.at(17,7).containsAnActor()){
                    Actor otherActor = map.at(17,7).getActor();
                    if (otherActor!= this){
                        map.removeActor(otherActor);
                        map.moveActor(this,map.at(17,7));
                    }
                }
                // If no actors, move Bowser to original location
                else{
                    map.moveActor(this,map.at(17,7));
                }
                removeCapability(Status.RESET);
            }
        }

        // If player is beside Bowser, add FollowBehaviour and AttackBehaviour targeted towards player
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
     * Gets the monologue that Bowser can speak
     *
     * @param target The target that the speaker is speaking to
     * @return an ArrayList of Monologue which signifies the sentences that Bowser can speak
     */
    @Override
    public ArrayList<Monologue> generateMonologue(Actor target) {
        ArrayList<Monologue> sentences = new ArrayList<>();
        sentences.add(new Monologue("What was that sound? Oh, just a fire.", this, true));
        sentences.add(new Monologue("Princess Peach! You are formally invited... to the creation of my new kingdom!", this, true));
        sentences.add(new Monologue("Never gonna let you down!", this, true));
        sentences.add(new Monologue("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!", this, true));

        return sentences;
    }

    /**
     * Defines what would happen to Bowser upon resetting
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }
}
