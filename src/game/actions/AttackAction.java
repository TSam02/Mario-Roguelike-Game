package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Fire;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 * @param direction direction to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Executes AttackAction and processes certain prerequisites.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return result of the attack in string
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();

		//Check if actor misses when attacking
		if (!(rand.nextInt(100) < weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		// If actor has FIRE ATTACK capability, actor will drop Fire whenever attacking
		if(actor.hasCapability(Status.FIRE_ATTACK)){
			map.locationOf(target).addItem(new Fire());
		}

		//If actor doesn't miss and actor has INVINCIBLE effect
		if (actor.hasCapability(Status.INVINCIBLE)){
			map.removeActor(target);
			return actor + " INSTANT KILLS " + target + " at " + direction;
		}

		// Check if target is INVINCIBLE, if yes, no damage is dealt
		int damage;
		if (target.hasCapability(Status.INVINCIBLE)){
			damage = 0;
		}
		else{
			damage= weapon.damage();
		}

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		// If target has SUPER effect, remove actor's SUPER buff as it is being attacked
		if (target.hasCapability(Status.SUPER)){
			target.removeCapability(Status.SUPER);
		}

		if (!target.isConscious()) {
			//If target is Koopa, make Koopa enter dormant state
			if (target.hasCapability(Status.NOT_DORMANT)){
				target.addCapability(Status.DORMANT);
				target.removeCapability(Status.NOT_DORMANT);
				result += System.lineSeparator() + target + " is now in dormant state.";
			}
			else{
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
		}
		return result;
	}

	/**
	 * Attack action description showed on console
	 * @param actor The actor performing the action.
	 * @return description of attack action to be displayed on console
	 */
	@Override
	public String menuDescription(Actor actor) {
		String actionMessage = actor + " attacks " + target + " at " + direction;
		// Menu description for attack would be added a "with fire" to signify that player is performing fire attack
		if (actor.hasCapability(Status.FIRE_ATTACK)){
			actionMessage += " with fire!";
		}
		return actionMessage;
	}
}
