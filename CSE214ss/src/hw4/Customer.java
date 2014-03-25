/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #4
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw4;

public class Customer {
	private int timeLeft;
	private int timeWaited;
	
	/**
	 * Creates a Customer with specified time left.
	 * @param timeLeft Initial remaining time.
	 */
	
	public Customer(int timeLeft) {
		this.timeLeft = timeLeft;
		timeWaited = 0;
	}
	
	/**
	 * Get time remaining for Customer.
	 * @return Current time remaining.
	 */
	
	public int getTimeLeft() {
		return timeLeft;
	}
	
	/**
	 * Get the amount of time the Customer has spent waiting.
	 * @return Amount of time the Customer has waited.
	 */
	
	public int getTimeWaited() {
		return timeWaited;
	}
	
	/**
	 * Lowers Customer's time remaining by 1.
	 * Increase their time spent waiting by 1.
	 */
	
	public void decrementTime() {
		timeLeft--;
		timeWaited++;
	}
}
