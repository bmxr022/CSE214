/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #4
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw4;

import java.util.Random;
import java.util.Scanner;

public class Simulator {
	
	Cashier[] cashiers;
	Kiosk[] kiosks;
	static int orders;
	int totalTimeWaited;
	int numCashiers;
	int numKiosks;
	int minCashierTime;
	int maxCashierTime;
	int minKioskTime;
	int maxKioskTime;
	int minRepairTime;
	int maxRepairTime;
	int numBrokenKiosks;
	int numAvailableCashiers;
	double arrivalProb;
	double kioskProb;
	double malfunctionProb;
	
	/**
	 * Prompts user for parameters of simulation. Runs simulation until user opts to stop.
	 */
	
	public static void main(String[] args) {
		//Simulator sim = new Simulator(2, 2, 3, 8, 1, 4, 4, 8, .5, .6, .1);
		//System.out.println("Average wait time: " + sim.simulate(20));
		//System.out.println("Orders completed: " + orders);
		int t1, t2, t3, t4, t5, t6, t7, t8;
		double t9, t10, t11;
		Scanner s = new Scanner(System.in);
		String inStr = "";
		
		while (true) {
			System.out.println("Enter number of cashiers: ");
			t1 = s.nextInt();
			System.out.println("Enter number of kiosks: ");
			t2 = s.nextInt();
			System.out.println("Enter minimum time for a cashier to complete an order: ");
			t3 = s.nextInt();
			System.out.println("Enter maximum time for a cashier to complete an order: ");
			t4 = s.nextInt();
			System.out.println("Enter minimum time for a kiosk to complete an order: ");
			t5 = s.nextInt();
			System.out.println("Enter maximum time for a kiosk to complete an order: ");
			t6 = s.nextInt();
			System.out.println("Enter minimum time to repair a kiosk: ");
			t7 = s.nextInt();
			System.out.println("Enter maximum time to repair a kiosk: ");
			t8 = s.nextInt();
			System.out.println("Enter probability of customer arrival (from 0 to 1): ");
			t9 = s.nextDouble();
			System.out.println("Enter probability of a customer choosing to use a kiosk (from 0 to 1): ");
			t10 = s.nextDouble();
			System.out.println("Enter probability of a kiosk malfunctioning (from 0 to 1): ");
			t11 = s.nextDouble();
			System.out.println("Enter duration of simulation: ");
			
			Simulator sim = new Simulator(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11);
			System.out.println("Average wait time: "+ sim.simulate(s.nextInt()));
			System.out.println("Orders completed: " + orders);
			
			System.out.println("Enter Q to quit or anything else to run another simulation: ");
			inStr = s.next();
			if (inStr.toLowerCase().equals("q"))
				break;
		}
		System.exit(0);
		
		
	}

	/**
	 * Constructor
	 * @param numCashiers Number of cashiers
	 * @param numKiosks Number of kiosks
	 * @param minCashierTime Minumum time for cashiers to finish an order
	 * @param maxCashierTime Maximum time for cashiers to finish an order
	 * @param minKioskTime Minimum time for a kiosk to finish an order
	 * @param maxKioskTime Maximum time for a kiosk to finish an order
	 * @param minRepairTime Minimum time to repair a kiosk
	 * @param maxRepairTime Maximum time to repair a kiosk
	 * @param arrivalProb Probability of a customer arriving
	 * @param kioskProb Probability that a customer will use a kiosk
	 * @param malfunctionProb Probability that a kiosk will malfunction
	 */
	
	public Simulator(int numCashiers, int numKiosks, int minCashierTime, int maxCashierTime, int minKioskTime, int maxKioskTime,
			int minRepairTime, int maxRepairTime, double arrivalProb, double kioskProb, double malfunctionProb) {
		cashiers = new Cashier[numCashiers];
		kiosks = new Kiosk[numKiosks];
		orders = 0;
		totalTimeWaited = 0;
		numAvailableCashiers = numCashiers;
		this.numCashiers = numCashiers;
		this.numKiosks = numKiosks;
		this.minCashierTime = minCashierTime;
		this.maxCashierTime = maxCashierTime;
		this.minKioskTime = minKioskTime;
		this.maxKioskTime = maxKioskTime;
		this.minRepairTime = minRepairTime;
		this.maxRepairTime = maxRepairTime;
		this.arrivalProb = arrivalProb;
		this.kioskProb = kioskProb;
		this.malfunctionProb = malfunctionProb;
		
		for (int i = 0; i < numCashiers; i++) {
			cashiers[i] = new Cashier();
		}
		for (int i = 0; i < numKiosks; i++) {
			kiosks[i] = new Kiosk();
		}
	}
	
	/**
	 * Simulates a fast food restaurant.
	 * @param duration Duration of the simulation.
	 * @return Average wait time of Customers.
	 */
	
	public int simulate(int duration) {
		int averageWait = 0;
		int tempWait = 0;
		int currentTime = 0;
		while (duration > 0) {
			if (givenProb(arrivalProb)) {				//If customer arrives
				if (givenProb(kioskProb)) {				//If assigned to kiosk
					kiosks[randomInt(0, numKiosks - 1)].arrive(randomInt(minKioskTime, maxKioskTime));		//Random kiosk gets a new customer with 
				}																							//a random wait time.
				else {
					cashiers[randomInt(0, numCashiers - 1)].arrive(randomInt(minCashierTime, maxCashierTime));	//Random cashier gets a new cust.
				}
			}
			for (int i = 0; i < numKiosks; i++) {		//Go through all kiosks, break if they're unlucky.
				if (givenProb(malfunctionProb)) {		//if malfunction
					kiosks[i].breakMe();				//break that kiosk
					System.out.println("Kiosk at index " + i + " broken. ");
					numBrokenKiosks++;
					while(numBrokenKiosks < numAvailableCashiers) {				//while there aren't too many broken kiosks, look for a free cashier
						try {											//and assign them to fix.
							cashiers[randomInt(0, numCashiers - 1)].assignToKiosk(kiosks[i], randomInt(minRepairTime, maxRepairTime));
							System.out.println("Assigned a cashier to fix a broken kiosk.");
							numAvailableCashiers--;
							break;
						}
						catch (KioskAlreadyAssignedException e) {
							System.out.println("Kiosk already assigned. Looking for another.");
							continue;
						}
					}
					if (numBrokenKiosks > numCashiers) {
						cashiers[randomInt(0, numCashiers - 1)].arrive(randomInt(minCashierTime, maxCashierTime));
						System.out.println("All kiosks being repaired. Customer had to go to a cashier.");
					}
				}
			}
			for (int i = 0; i < numKiosks; i++) {
				totalTimeWaited++;
				if (kiosks[i].isEmpty() == false){
					tempWait = kiosks[i].getCustTime();
				}
				if (kiosks[i].isBroken()) {
					kiosks[i].act();
					if (kiosks[i].isBroken() == false) {
						System.out.println("Kiosk at index " + i + " fixed.");
						numBrokenKiosks--;
						numAvailableCashiers++;
					}
					continue;
				}
				if (kiosks[i].act()) {
					System.out.println("Order completed by a kiosk.");
					orders++;
					averageWait += (tempWait + 1);
				}
			}
			for (int i = 0; i < numCashiers; i++) {
				if (cashiers[i].isEmpty() == false){
					tempWait = cashiers[i].getCustTime();
				}
				totalTimeWaited++;
				if (cashiers[i].act()) {
					orders++;
					averageWait += (tempWait + 1);
				}
			}
			currentTime++;
			duration--;
			System.out.println("Time: " + currentTime + ".");
			System.out.print("Cashiers: ");
			for (int i = 0; i < numCashiers; i++) {
				System.out.print("(");
				cashiers[i].printQueue();
				System.out.print(")");
			}
			System.out.println();
			System.out.print("Kiosks: ");
			for (int i = 0; i < numKiosks; i++) {
				System.out.print("(");
				kiosks[i].printQueue();
				System.out.print(")");
			}
			System.out.println();
			System.out.println("----------------");
			System.out.println();
		}
		return (averageWait / orders);
	}
	
	/**
	 * Determines outcome given a probability from [0-1).
	 * @param prob Probability of being true.
	 * @return Outcome of probability.
	 */
	
	private boolean givenProb(double prob) {
		if (Math.random() < prob)
			return true;
		else return false;
	}
	
	/**
	 * Generates a random integer within the given range.
	 * @param min Minimum number.
	 * @param max Maximum number.
	 * @return Random integer within the given range.
	 */
	
	private int randomInt(int min, int max) {
		Random rand = new Random();
		int randNum = rand.nextInt((max - min) + 1) + min;
		return randNum;
	}
}
