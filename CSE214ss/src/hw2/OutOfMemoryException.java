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
 * Exception: Not enough memory free.
 * @author Zach Samuels
 *
 */

public class OutOfMemoryException extends Exception {
	public OutOfMemoryException(String message) {
		super(message);
	}
}
