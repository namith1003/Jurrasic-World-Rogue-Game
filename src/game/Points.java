package game;

public class Points {

    // 1000 points right at the start of the game ONLY FOR TESTING PURPOSES
    // PLS REVERT BEFORE SUBMISSION
    private int points = 1000;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

}
