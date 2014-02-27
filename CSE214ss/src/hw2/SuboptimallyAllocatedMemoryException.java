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
 * Exception: Enough free memory, but no individual blocks big enough.
 * @author Zach Samuels
 *
 */

public class SuboptimallyAllocatedMemoryException extends Exception {
	public SuboptimallyAllocatedMemoryException(String message) {
		super(message);
	}
}
