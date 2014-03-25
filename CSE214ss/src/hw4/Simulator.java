package hw4;

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
		while (duration > 0)
	}
	
	private boolean givenProb(double prob) {
		if (Math.random() < prob)
			return true;
		else return false;
	}
	
}
