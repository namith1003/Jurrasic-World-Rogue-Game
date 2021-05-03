package game;

public class Points {

    // 10000 points right at the start of the game ONLY FOR TESTING PURPOSES
    // PLS REVERT BEFORE SUBMISSION
    private int points = 10000;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

}
