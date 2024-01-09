package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeItemAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Bottle class that can be used to fill and store unlimited amount of water
 */
public class Bottle extends ConsumableItem{

    /**
     * Stack of water
     */
    private Stack<Water> waterStack;

    /**
     * Instance of bottle
     */
    private static Bottle instance;

    /***
     * Constructor
     */
    private Bottle() {
        super("Bottle", 'b', false);
        waterStack = new Stack<>();
    }

    /**
     * Method to get instance or initialize one if it hasn't been
     *
     * @return instance of bottle
     */
    public static Bottle getInstance() {
        if (instance == null) {
            instance = new Bottle();
        }
        return instance;
    }

    /**
     * Method to fill water into bottle
     *
     * @param water water
     */
    public void fillWater(Water water){
        waterStack.push(water);
    }

    /**
     * Method to get water of bottle
     *
     * @return water
     */
    public Water getWater() {
        return waterStack.get(waterStack.size() - 1);
    }

    /**
     * Method to get water and remove that water from bottle
     *
     * @return water
     */
    public Water consumeWater() {
        return waterStack.pop();
    }

    /**
     * Method to add consume water action if bottle has water
     *
     * @return list of actions
     */
    @Override
    public List<Action> getAllowableActions() {
        ArrayList<Action> actions = new ArrayList<>();

        if (!waterStack.isEmpty()) {
            actions.add(new ConsumeItemAction(this));
        }

        return actions;
    }

    /**
     * Method to describe about bottle and the waters contained
     *
     * @return string description of bottle and waters inside
     */
    @Override
    public String toString() {
        String result = "Bottle[";

        for (int i = 0; i < waterStack.size(); i++){
            result += waterStack.get(i).toString();
            if (i < waterStack.size()-1){
                result += ", ";
            }
        }

        result += "]";
        return result;
    }

    /**
     * Method to consume bottle which called consume action of water inside
     *
     * @param actor the actor that is consuming the consumable item
     * @param map the game map that we are looking at for the actor to consume item
     */
    @Override
    public void consumeItem(Actor actor, GameMap map) {
        this.consumeWater().consumeItem(actor, map);
    }
}
