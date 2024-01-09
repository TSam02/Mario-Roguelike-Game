package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.DrinkBehaviour;
import game.reset.Resettable;

/**
 * Abstract class for Enemies that automatically attack players when in range and follow players after engaged in a fight
 */
public abstract class Enemy extends NonPlayerCharacter implements Resettable {

    /**
     * Intrinsic weapon of enemy
     */
    private IntrinsicWeapon intrinsicWeapon;

    /**
     * Default constructor for Enemy. Immediately adds DrinkBehaviour so that enemies can drink water from fountain whenever possible
     *
     * @param name        enemy's name
     * @param displayChar display character for enemy
     * @param hitPoints   HP of enemy
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.registerInstance();
        this.addBehaviour(10, new DrinkBehaviour());
        this.setIntrinsicWeapon(super.getIntrinsicWeapon());
    }

    /**
     * Increase damage of intrinsic weapon by 15 and set this as new intrinsic weapon
     */
    public void empowerIntrinsicWeapon() {
        this.intrinsicWeapon = new IntrinsicWeapon(this.intrinsicWeapon.damage() + 15, "punches");
    }

    /**
     * Get the intrinsic weapon
     *
     * @return the intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return intrinsicWeapon;
    }

    /**
     * A setter to set the new intrinsic weapon
     *
     * @param intrinsicWeapon the instrinsic weapon to be set as the new intrinsic weapon
     */
    protected void setIntrinsicWeapon(IntrinsicWeapon intrinsicWeapon) {
        this.intrinsicWeapon = intrinsicWeapon;
    }

    /**
     * Gets the allowable actions that can be done on Enemy
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Method to reset enemy
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    @Override
    public String toString() {
        return super.toString() + printHp();
    }

}
