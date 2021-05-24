package game;

import edu.monash.fit2099.engine.*;

import java.util.Scanner;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
	static Points points = new Points();
	private int gameMode = 0;
	private int numberOfTurns = 0;
	private int targetPoints = 0;
	private int targetTurns = 0;
	private Scanner keyboard = new Scanner(System.in);

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);

	}

	/**
	 * The method to get all available actions for the player
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return A menu to show all available actions.
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		while (gameMode != -1) {
			if (gameMode == 0) {
				numberOfTurns = 0;
				Player.points = new Points();

				display.println("""
							------------------------------------
							You have started the Game!
							Pick Which Game Mode You Would Like To Play
							------------------------------------
							1. Challenge Game Mode
							2. SandBox Game Mode
							3. End Game
							------------------------------------
							Insert Valid Option here:
							""");

				gameMode = Character.getNumericValue(display.readChar());

				while (gameMode <= 0 || gameMode >= 4) {
					display.println("""
							------------------------------------
							You have Entered an Invalid Option!
							Pick Which Game Mode You Would Like To Play
							------------------------------------
							1. Challenge Game Mode
							2. SandBox Game Mode
							3. End Game
							------------------------------------
							Insert Valid Option here:
							""");

					gameMode = Character.getNumericValue(display.readChar());
				}

				if (gameMode == 1) {
					targetPoints = 0;
					targetTurns = 0;
					display.println("""
							------------------------------------
							You have started the Challenge Game Mode!
							Enter Your Target Points and Number of Turns
							------------------------------------
							Insert Target Points to reach:
							        """);
					targetPoints = keyboard.nextInt();

					display.println("""
							Insert Number of Turns to reach Target Points:
							        """);

					targetTurns = keyboard.nextInt();
				}

				if (gameMode == 3) {
					break;
				}
			}

			if (gameMode == 1) {
				if (Player.points.getPoints() > targetPoints) {
					display.println("""
							------------------------------------
							Congratulations You have Completed the Challenge!
							You May Try Another Game Mode or Try a new Challenge
							------------------------------------
							Target Points: """ + targetPoints + """
														
							Number of Turns Passed: """ + numberOfTurns + """
																					
							Points Reached: """ + Player.points.getPoints() + """
																					
							You May Retry a Challenge Or Try Another Game Mode
							------------------------------------
							       
							""");
					gameMode = 0;
					continue;
				}
				if (numberOfTurns == targetTurns) {
					display.println("""
							------------------------------------
							You have failed to Complete the Challenge in the predicted Number of Turns!
							Target Points: """ + targetPoints + """
							
							Number of Turns Passed: """ + numberOfTurns + """
														
							Points Reached: """ + Player.points.getPoints() + """
														
							You May Retry Or Try Another Game Mode
							------------------------------------
							""");
					gameMode = 0;
					continue;
				}
			}
			numberOfTurns++;

			actions.add(new QuitGameAction(false));
			// Handle multi-turn Actions
			if (lastAction.getNextAction() != null)
				return lastAction.getNextAction();
			return menu.showMenu(this, actions, display);
		}

		return new QuitGameAction(true);
	}


}
