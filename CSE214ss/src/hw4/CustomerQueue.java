package hw4;

import java.util.Queue;
import java.util.LinkedList;

public class CustomerQueue extends LinkedList<Customer> implements Queue<Customer> {

	private int queueSize = 0;		// Size of queue.
	
	/**
	 * Default CustomerQueue constructor. Empty Queue using a LinkedList.
	 */
	
	public CustomerQueue() {
		super();
	}
	
	/**
	 * Creates a Customer with specified time remaining and adds it to the end of the Queue.
	 * @param time Initial time remaining for Customer.
	 */
	
	public void enqueue(int time) {
		add(new Customer(time));
		queueSize++;
	}
	
	/**
	 * Removes the Customer from the front of the Queue and returns their time remaining.
	 * @return The removed Customer's time remaining.
	 */
	
	public int dequeue() {
		queueSize--;
		return remove().getTimeLeft();
	}
	
	/**
	 * Decrements time of first Customer by 1.
	 */
	
	public void serve() {
		peek().decrementTime();
	}
	
	/**
	 * How many Customers are in the queue.
	 * @return Size of queue.
	 */
	
	public int size() {
		return queueSize;
	}
	
	/**
	 * Checks whether or not the Queue is empty.
	 * @return True if empty.
	 */
	
	public boolean isEmpty() {
		return (queueSize == 0);
	}
	
	public static void main(String[] args) {
		CustomerQueue q = new CustomerQueue();
		q.enqueue(10);
		q.enqueue(11);
		q.enqueue(21);
		q.serve();
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.isEmpty());
	}
}
