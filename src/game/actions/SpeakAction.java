package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.speaking.Monologue;
import game.speaking.Speakable;

import java.util.ArrayList;
import java.util.Random;

/**
 * SpeakAction class is to handle the speak action of the actors
 */
public class SpeakAction extends Action {

    /**
     * The speaker that speak the monologue
     */
    private Speakable speaker;

    /**
     * Constructor for SpeakAction
     *
     * @param speaker the speaker that responsible to speak the monologue
     */
    public SpeakAction(Speakable speaker) {
        this.speaker = speaker;
    }

    /**
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return the chosen sentence that the speaker will speak
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random rand = new Random();
        ArrayList<Monologue> availableMonologues = new ArrayList<>();
        ArrayList<Monologue> speakerMonologues = speaker.generateMonologue(actor);
        //Add the available sentence into the availableMonologues
        for (Monologue monologue : speakerMonologues) {
            if (monologue.isAvailable()) {
                availableMonologues.add(monologue);
            }
        }

        //Choose one of the sentence from available sentence and concatenate it into a String
        String speakSentence = speaker + ": " + availableMonologues.get(rand.nextInt(availableMonologues.size()));

        return speakSentence;
    }

    /**
     * Menu description
     *
     * @param actor The actor performing the action.
     * @return menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with Toad";
    }
}

