package game;

/**
 * A class to store the player's points.
 */
public class Points {

    /**
     * The base variable that stores the amount of points.
     */
    private int points = 0;

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
