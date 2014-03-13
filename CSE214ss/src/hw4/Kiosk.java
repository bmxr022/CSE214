package hw4;

public class Kiosk {
	
	private CustomerQueue queue = new CustomerQueue();
	private boolean broken = false;
	
	/**
	 * Create a default Kiosk.
	 */
	
	public Kiosk() {}
	
	/**
	 * Adds a new Customer to the queue with specified time.
	 * @param time Time to complete Customer's order.
	 */
	
	public void arrive(int time) {
		queue.enqueue(time);
	}
	
	/**
	 * Decrement the Customer's time left. Does nothing if Kiosk is broken.
	 * Returns whether or not the Kiosk finished someone's order.
	 * @return True if a Customer's order has been completed.
	 */
	
	public boolean act() {
		if (broken)
			return false;
		queue.serve();
		if (queue.peek().getTimeLeft() == 0) {
			queue.dequeue();
			return true;
		}
		return false;
	}
	
	/**
	 * Break the Kiosk.
	 */
	
	public void breakMe() {
		broken = true;
	}
	
	/**
	 * Fix the Kiosk.
	 */
	
	public void fixMe() {
		broken = true;
	}
}
