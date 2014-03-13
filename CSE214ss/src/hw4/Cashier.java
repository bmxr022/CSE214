package hw4;

import java.util.Queue;
import java.util.LinkedList;

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
		queue.serve();
		if (queue.peek().getTimeLeft() == 0) {
			queue.dequeue();
			return true;
		}
		return false;
	}
}
