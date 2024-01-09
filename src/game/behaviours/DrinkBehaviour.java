package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeItemAction;
import game.ground.Drinkable;
import game.ground.DrinkableManager;

/**
 * A behaviours which allows enemy to drink water and obtain the corresponding effect from drinkable ground
 */
public class DrinkBehaviour implements Behaviour {

    /**
     * Method to getAction due to drinkBehaviour by checking if the ground is in DrinkableManager and if the ground
     * has enough water to be drunk.
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return consumeAction of the water of the drinkable ground if the ground is drinkable and it has enough water
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // if current location of actor is a drinkableGround recorded in drinkableManager
        if (DrinkableManager.getInstance().getDrinkableGroundCollection().containsKey(map.locationOf(actor))) {
            Drinkable drinkableGround = DrinkableManager.getInstance().
                    getDrinkableGroundCollection().get(map.locationOf(actor));

            // if has enough water, refill water & decrease water & consume water
            if (drinkableGround.getWaterAmount() >= 5) {
                drinkableGround.drink();
                return new ConsumeItemAction(drinkableGround.getWater());
            }
        }
        return null;
    }
}
