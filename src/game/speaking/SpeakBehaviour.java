package game.speaking;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.behaviours.Behaviour;

/**
 * A behaviour which will be provided to all the NonPlayerCharacter(NPC) so that the NPC can automatically speak
 * (self-speaking)
 */
public class SpeakBehaviour implements Behaviour {

    /**
     * An integer that keep track of the number of turns
     */
    private int turns = 0;
    /**
     * The speaker that speak the monologue
     */
    private Speakable speaker;
    private Display display;

    /**
     * Constructor of SpeakBehaviour
     * @param speaker the speaker that speak the monologue
     * @param display the I/O object to which messages may be written
     */
    public SpeakBehaviour(Speakable speaker,Display display) {
        this.speaker = speaker;
        this.display = display;
    }

    /**
     * Gets the action that would result from having this behaviour
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return always null (so that the self-speaking of the speaker and other action can concurrently happen)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        //Create a new speakAction object
        SpeakAction speakAction = new SpeakAction(speaker);

        turns++;
        //The speaker will speak the chosen sentence for every even turn
        if (turns > 0 && turns % 2 == 0) {
            String speakSentence = speakAction.execute(actor, map);
            display.println(speakSentence);
        }

        return null;
    }
}
