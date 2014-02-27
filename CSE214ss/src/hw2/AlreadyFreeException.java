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
 * Exception: Memory is already free.
 * @author Zach Samuels
 *
 */

public class AlreadyFreeException extends Exception {
	public AlreadyFreeException(String message) {
		super(message);
	}
}
