package game.ground;

/**
 * Wall class which is a highGround
 */
public class Wall extends HighGround {

    /**
     * Constructor for wall
     */
    public Wall() {
        super('#');
        this.setFallDamage(20);
        this.setJumpSuccessRate(80);
    }

    /**
     * Method to return if wall can block thrown object
     *
     * @return true which means it can block thrown object
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Method to get wall name
     *
     * @return wall
     */
    @Override
    public String toString() {
        return "Wall";
    }
}