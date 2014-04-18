/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #5
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw5;

public class IllegalArgumentException extends Exception {
	
	/**
	 * Thrown when ParticleTree can't find a specified ParticleNode.
	 * @param message Message to be printed.
	 */
	
	public IllegalArgumentException(String message) {
		super(message);
	}
}
