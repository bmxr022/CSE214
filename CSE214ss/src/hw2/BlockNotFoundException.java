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
 * Exception: No MemoryBlock found.
 * @author Zach Samuels
 *
 */
public class BlockNotFoundException extends Exception {
	public BlockNotFoundException(String message) {
		super(message);
	}
}
