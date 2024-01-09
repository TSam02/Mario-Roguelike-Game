package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	/**
	 * A constructor of Floor class
	 */
	public Floor() {
		super('_');
	}

	/**
	 * A method that check whether the actor can enter the floor
	 * @param actor A Actor object
	 * @return true if the actor is a player, false otherwise
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
	}

	/**
	 * Method to return name of floor
	 *
	 * @return string name of floor
	 */
	@Override
	public String toString() {
		return "Floor";
	}
}
