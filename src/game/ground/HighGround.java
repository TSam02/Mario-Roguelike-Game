package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

import game.actions.InvincibleWalkAction;
import game.actions.JumpAction;


/**
 * Abstract class for all highGround
 */
public abstract class HighGround extends Ground {

    /**
     * Success rate of jumping to the highGround
     */
    private double jumpSuccessRate;

    /**
     * Damage received if the jump fails
     */
    private int fallDamage;

    /**
     * Setter for jumpSuccessRate
     *
     * @param jumpSuccessRate success rate of jump
     */
    public void setJumpSuccessRate(double jumpSuccessRate) {
        this.jumpSuccessRate = jumpSuccessRate;
    }

    /**
     * Setter for fallDamage
     *
     * @param fallDamage fall damage of failed jump
     */
    public void setFallDamage(int fallDamage) {
        this.fallDamage = fallDamage;
    }

    /**
     * Getter for jumpSuccessRate
     *
     * @return success rate of jump
     */
    public double getJumpSuccessRate() {
        return jumpSuccessRate;
    }

    /**
     * Getter for fall damage
     *
     * @return fall damage of failed jump
     */
    public int getFallDamage() {
        return fallDamage;
    }

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Method to set highGround to the state that actor cannot move into
     *
     * @param actor the Actor to check
     * @return false which represents actor cannot move into it
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        // If actor has FLY capability, actor can always fly over
        return actor.hasCapability(Status.FLY);
    }

    /**
     * Method to add list of allowable actions
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of allowableActions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (actor.hasCapability(Status.INVINCIBLE)) {
            actions.add(new InvincibleWalkAction(location, direction));
        } else if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !direction.equals("")) {
            actions.add(new JumpAction(direction, location, this));
        }
        return actions;
    }
}

