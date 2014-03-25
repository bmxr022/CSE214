/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #4
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw4;

public class Cashier {
	
	private CustomerQueue queue = new CustomerQueue();
	private Kiosk kioskToFix = null;
	private int timeToFixKiosk = 0;
	
	/**
	 * Creates a default Cashier.
	 */
	
	public Cashier() {}
	
	/**
	 * Adds a new Customer to the queue with specified time.
	 * @param time Time to complete this Customer's order.
	 */
	
	public void arrive(int time) {
		queue.enqueue(time);
	}
	
	/**
	 * Assigns the Cashier to fix the specified Kiosk for the given amount of time. 
	 * @param k Kiosk to fix.
	 * @param time Time that the Cashier spend fixing the Kiosk.
	 * @throws KioskAlreadyAssignedException Cashier is already fixing a Kiosk.
	 */
	
	public void assignToKiosk(Kiosk k, int time) throws KioskAlreadyAssignedException {
		if (kioskToFix != null)
			throw new KioskAlreadyAssignedException("This Cashier is already assigned to a Kiosk.");
		kioskToFix = k;
		k.breakMe();
		timeToFixKiosk = time;
	}
	
	/**
	 * Cashier either fixes their assigned Kiosk or serves their Customer.
	 * Returns true if the Cashier completed a Customer's order.
	 * @return
	 */
	
	public boolean act() {
		if (kioskToFix != null) {
			timeToFixKiosk--;
			if (timeToFixKiosk == 0) {
				kioskToFix.fixMe();
				kioskToFix = null;
			}
			return false;
		}
		if (queue.isEmpty())
			return false;
		queue.serve();
		if (queue.peek().getTimeLeft() == 0) {
			queue.dequeue();
			return true;
		}
		return false;
	}

	/**
	 * Prints the time left for each Customer on line.
	 */
	
	public void printQueue() {
		if (kioskToFix != null)
			System.out.print("fixing ");
		for (Customer i: queue) {
			System.out.print(i.getTimeLeft() + " ");
		}
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
}
