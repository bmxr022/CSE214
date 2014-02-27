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
 * Exception: Size is too small for a MemoryBlock.
 * @author Zach Samuels
 *
 */

public class TooSmallException extends Exception {
	public TooSmallException(String message) {
		super(message);
	}
}
