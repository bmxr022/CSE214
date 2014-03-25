/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #4
 * CSE214
 * R05 - Vyassa Baratham
 */
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
		if (queue.isEmpty()) {
			return false;
		}
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
	
	/**
	 * Check whether kiosk is broken or not.
	 * @return Status of kiosk.
	 */
	
	public boolean isBroken() {
		return broken;
	}
	
	/**
	 * Check whether or not the Kiosk's line is empty.
	 * @return True for empty.
	 */
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	/**
	 * Get time waited by Customer currently being served.
	 * @return Elapsed time waited by customer.
	 */
	
	public int getCustTime() {
		return queue.peek().getTimeWaited();
	}
	
	/**
	 * Prints the time left for each customer on line.
	 */
	
	public void printQueue() {
		for (Customer i: queue) {
			System.out.print(i.getTimeLeft() + " ");
		}
	}
}
