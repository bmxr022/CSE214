/*
 * Zach Samuels
 * 108941490
 * Zachary.Samuels@stonybrook.edu
 * HW #2
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw2;

/**
 * Exception: Invalid parameters entered.
 * @author Zach Samuels
 *
 */

public class IllegalArgumentException extends Exception {
	public IllegalArgumentException(String message) {
		super(message);
	}
}
