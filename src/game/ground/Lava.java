package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Blazing fire ground
 */
public class Lava extends Ground {

    /**
     * Burn damage for lava ground
     */
    private int burnDamage;

    /**
     * Constructor of lava ground
     */
    public Lava() {
        super('L');
        burnDamage = 15;
    }

    /**
     * Method to set lava ground so that only player can enter, enemy cannot enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Method to hurt actor if actor stands on it
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.getActor() != null) {
            location.getActor().hurt(burnDamage);
        }
    }

    /**
     * Method to return name of lava ground
     *
     * @return string name of lava
     */
    public String toString() {
        return "Lava";
    }
}
