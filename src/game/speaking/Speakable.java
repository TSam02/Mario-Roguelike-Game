package game.speaking;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

/**
 * An interface that provides the capability of speaking
 */
public interface Speakable {

    /**
     * Generate the possible monologues that can be spoken by the speaker
     * @param target The target that the speaker is speaking to
     * @return An ArrayList of Monologue which stores the possible monologues that can be spoken
     */
    ArrayList<Monologue> generateMonologue(Actor target);
}
