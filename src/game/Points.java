package game;

/**
 * A class to store the player's points.
 */
public class Points {

    /**
     * The base variable that stores the amount of points.
     */
    // 10000 points right at the start of the game ONLY FOR TESTING PURPOSES
    // PLS REVERT BEFORE SUBMISSION
    private int points = 10000;

    /**
     * Getter method for points.
     * @return The amount of points the player has.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Setter method for points.
     * @param points The amount of points to add to the player's current points.
     */
    public void setPoints(int points) {
        this.points += points;
    }

}
