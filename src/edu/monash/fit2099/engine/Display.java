package edu.monash.fit2099.engine;

import java.util.Scanner;

/**
 * Class that manages I/O for the system
 */
public class Display  {

	private Scanner keyboard = new Scanner(System.in);

	/**
	 * Display a displayable object.
	 *
	 * @param printable the object to display
	 */
	public void print(Printable printable) {
		System.out.print(printable.getDisplayChar());
	}

	/**
	 * Prints a String and then terminates the line.
	 * @param s the string to print
	 */
	public void println(String s) {
		System.out.println(s);
	}

	/**
	 * Terminates the line.
	 */
	public void endLine() {
		System.out.println("");
	}

	/**
	 * Read a char from the keyboard.
	 * 
	 * @return the first char of the next entered string.
	 */
	public char readChar() {
		String s = keyboard.next();
		return s.charAt(0);
	}
}
