package game.speaking;

/**
 * Monologue class is a class that handle the speech text between Player and Toad
 */
public class Monologue {

    /**
     * Sentence of the monologue
     */
    private String sentence;

    /**
     * Speaker of this monologue
     */
    private Speakable speaker;

    /**
     * Is it available for the speaker to speak
     */
    private boolean isAvailable;

    /**
     * Constructor of the Monologue class
     * @param sentence sentence spoken by the speaker
     * @param speaker speaker that speak the sentence
     * @param isAvailable is the sentence available to speak
     */
    public Monologue(String sentence, Speakable speaker, boolean isAvailable) {
        this.sentence = sentence;
        this.speaker = speaker;
        this.isAvailable = isAvailable;
    }

    /**
     * Getter of isAvailable
     * @return boolean that indicate the monologue can be spoken or not
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * To string method of the Monologue object
     * @return the sentence of the monologue
     */
    @Override
    public String toString() {
        return sentence;
    }
}
