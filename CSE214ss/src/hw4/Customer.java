package hw4;

public class Customer {
	private int timeLeft;
	
	/**
	 * Creates a Customer with specified time left.
	 * @param timeLeft Initial remaining time.
	 */
	
	public Customer(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	/**
	 * Time remaining for Customer.
	 * @return Current time remaining.
	 */
	
	public int getTimeLeft() {
		return timeLeft;
	}
	
	/**
	 * Lowers Customer's time remaining by 1.
	 */
	
	public void decrementTime() {
		timeLeft--;
	}
}
