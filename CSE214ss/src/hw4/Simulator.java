package hw4;

import java.util.Random;

public class Simulator {
	
	Cashier[] cashiers;
	Kiosk[] kiosks;
	int orders;
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
	double arrivalProb;
	double kioskProb;
	double malfunctionProb;
	
	public Simulator(int numCashiers, int numKiosks, int minCashierTime, int maxCashierTime, int minKioskTime, int maxKioskTime,
			int minRepairTime, int maxRepairTime, double arrivalProb, double kioskProb, double malfunctionProb) {
		cashiers = new Cashier[numCashiers];
		kiosks = new Kiosk[numKiosks];
		orders = 0;
		totalTimeWaited = 0;
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
	}
	
	public int Simulate(int duration) {
		int averageWait = 0;
		while (duration > 0) {
			if (givenProb(arrivalProb)) {				//If customer arrives
				if (givenProb(kioskProb)) {				//If assigned to kiosk
					kiosks[randomInt(0, numKiosks)].arrive(randomInt(minKioskTime, maxKioskTime));		//Random kiosk gets a new customer with 
				}																						//a random wait time.
				else {
					cashiers[randomInt(0, numCashiers)].arrive(randomInt(minCashierTime, maxCashierTime));
				}
			}
			for (int i = 0; i < numKiosks; i++) {		//Go through all kiosks, break if they're unlucky.
				if (givenProb(malfunctionProb)) {
					kiosks[i].breakMe();
					numBrokenKiosks++;
					while(numBrokenKiosks < numCashiers) {						
						try {
							cashiers[randomInt(0, numCashiers)].assignToKiosk(kiosks[i], randomInt(minRepairTime, maxRepairTime));
							break;
						}
						catch (KioskAlreadyAssignedException e) {
							System.out.println("Kiosk already assigned. Looking for another.");
							continue;
						}
					}
				}
			}
			for (int i = 0; i < numKiosks; i++) {
				totalTimeWaited++;
				if (kiosks[i].isBroken()) {
					kiosks[i].act();
					if (kiosks[i].isBroken() == false) {
						numBrokenKiosks--;
					}
					continue;
				}
				if (kiosks[i].act()) {
					orders++;
				}
			}
			for (int i = 0; i < numCashiers; i++) {
				totalTimeWaited++;
				if (cashiers[i].act())
					orders++;
			}
		}
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
	
	public static void main(String[] args) {
		for (int i = 0; i < 11; i++) {
		//	System.out.println(randomInt(0, 10));
		}
	}
}
