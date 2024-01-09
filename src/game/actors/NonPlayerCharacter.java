package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.speaking.SpeakBehaviour;
import game.speaking.Speakable;
import game.behaviours.Behaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing NonPlayerCharacters (NPCs) who all have behaviours
 */
public abstract class NonPlayerCharacter extends Actor implements Speakable {

    /**
     * HashMap for behaviours of NPC
     */
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Default constructor for NPCs.
     *
     * @param name name of NPC
     * @param displayChar display character of NPC on map
     * @param hitPoints hit points value of NPC
     */
    public NonPlayerCharacter(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!behaviours.containsKey(7)) {
            addBehaviour(7, new SpeakBehaviour(this, display)); // All NPCs will have SpeakBehaviour that allows them to speak
        }
        return null;
    }

    /**
     * Adds a behaviour with the priority into collection of behaviours for Enemy
     *
     * @param priority  the priority of behaviour, the lower the number, the higher the priority of behaviour's action being obtained
     * @param behaviour behaviour that will be added into behaviours
     */
    public void addBehaviour(int priority, Behaviour behaviour) {
        this.behaviours.put(priority, behaviour);
    }

    /**
     * Gets all the behaviours of enemy
     *
     * @return a HashMap containing the collection of behaviours
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

}
