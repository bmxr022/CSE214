package hw4;

public class Simulator {
	
	Cashier[] cashiers;
	Kiosk[] kiosks;
	int orders;
	int numCashiers;
	int numKiosks;
	int minCashierTime;
	int maxCashierTime;
	int minKioskTime;
	int maxKioskTime;
	int minRepairTime;
	int MaxRepairTime;
	double arrivalProb;
	double kioskProb;
	double malfunctionProb;
	
	public Simulator(int numCashiers, int numKiosks, int minCashierTime, int maxCashierTime, int minKioskTime, int maxKioskTime,
			int minRepairTime, int MaxRepairTime, double arrivalProb, double kioskProb, double malfunctionProb) {
		cashiers = new Cashier[numCashiers];
		kiosks = new Kiosk[numKiosks];
		
	}
	
	
	
}
