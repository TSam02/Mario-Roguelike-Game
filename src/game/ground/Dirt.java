package game.ground;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground implements Fertile{

	/**
	 * Constructor for dirt
	 */
	public Dirt() {
		super('.');
		this.addToFertileManager();
	}

	/**
	 * Method to return name of dirt
	 *
	 * @return string name of dirt
	 */
	@Override
	public String toString() {
		return "Dirt";
	}
}
