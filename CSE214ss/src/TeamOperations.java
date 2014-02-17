/*
 * Zach Samuels
 * 108941490
 * Zachary.Samuels@stonybrook.edu
 * HW #1
 * CSE214
 * R05 - Vyassa Baratham
 */

/**
 * Menu-driven Team Operations
 * @author Zach Samuels
 *
 */

import java.util.Scanner;

public class TeamOperations {

	private static String inStr1 = "";
	private static String inStr2 = "";
	private static String inStr3 = "";
	private static String inStr4 = "";
	private static String menuChoice = "";
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		Team team = new Team();
		
		System.out.println("Team created.");
		printMenu();

		do {
			
			System.out.println("Enter an option: ");
			menuChoice = in.nextLine().toLowerCase();
			
			while (!(menuChoice.equals("a") || menuChoice.equals("g") || menuChoice.equals("l") 
					|| menuChoice.equals("r") || menuChoice.equals("p") || menuChoice.equals("s")
					|| menuChoice.equals("h") || menuChoice.equals("e") || menuChoice.equals("q") || menuChoice.equals(""))) {
				System.out.println("Enter a valid menu choice: ");
				clearInputs();
			}
			
			
			switch (menuChoice) {
				case "a": 
					System.out.println("Enter New Player's Name: ");
					inStr1 = in.nextLine();
					System.out.println("Enter New Player's # of Hits: ");
					inStr2 = in.nextLine();
					System.out.println("Enter New Player's # of Errors: ");
					inStr3 = in.nextLine();
					System.out.println("Enter New Player's Position: ");
					inStr4 = in.nextLine();
					try {
						team.addPlayer(new Player(inStr1, Integer.parseInt(inStr2), Integer.parseInt(inStr3)), Integer.parseInt(inStr4));
						System.out.println("Player added.");
					}
					catch (FullTeamException | NumberFormatException | IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					clearInputs();
					break;
				case "g":
					System.out.println("Enter Position of Player to Get: ");
					inStr1 = in.nextLine();
					try {
						System.out.println(team.getPlayer(Integer.parseInt(inStr1)).toString());
					}
					catch (IllegalArgumentException | NumberFormatException | EmptyTeamPositionException e) {
						System.out.println(e.getMessage());
					}
					clearInputs();
					break;
				case "l": 
					System.out.println("Enter Statistic to Find Leader of: ");
					inStr1 = in.nextLine();
					System.out.println("Leader of "+ inStr1.toUpperCase() + ": " + team.getLeader(inStr1).toString());
					clearInputs();
					break;
				case "r":
					System.out.println("Enter Position of Player to Remove: ");
					inStr1 = in.nextLine();
					try {
						team.removePlayer(Integer.parseInt(inStr1));
						System.out.println("Player removed.");
					}
					catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					clearInputs();
					break;
				case "p":
					team.printAllPlayers();
					clearInputs();
					break;
				case "s":
					System.out.println("Team Size: " + team.size());
					clearInputs();
					break;
				case "h":
					System.out.println("Enter Player's Name: ");
					inStr1 = in.nextLine();
					System.out.println("Enter New # of Hits: ");
					inStr2 = in.nextLine();
					try {
						team.getPlayerByName(inStr1).setHits(Integer.parseInt(inStr2));
						System.out.println("Changed # Hits.");
						}
					catch (LessThanZeroException | NumberFormatException | IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					clearInputs();
					break;
				case "e":
					System.out.println("Enter Player's Name: ");
					inStr1 = in.nextLine();
					System.out.println("Enter New # of Errors: ");
					inStr2 = in.nextLine();
					try {
						team.getPlayerByName(inStr1).setErrors(Integer.parseInt(inStr2));
						System.out.println("Changed # Errors.");
					}
					catch (LessThanZeroException | NumberFormatException | IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					clearInputs();
					break;
			}
			
		}
		while (!menuChoice.equals("q"));
		System.out.println("Exiting.");
		System.exit(0);
	}
	
	public static void printMenu() {
		System.out.println("---------------------------------------------------");
		System.out.printf("%-22s %5s %-20s %n", "Add Player:", "A", "<Name> <Hits> <Errors> <Position>");
		System.out.printf("%-22s %5s %-20s %n", "Get Player:", "G", "<Position>");
		System.out.printf("%-22s %5s %-20s %n", "Get leader in a stat:", "L", "<Stat>");
		System.out.printf("%-22s %5s %-20s %n", "Remove Player:", "R", "<Position>");
		System.out.printf("%-22s %5s %-20s %n", "Print all Players:", "P", "");
		System.out.printf("%-22s %5s %-20s %n", "Size:", "S", "");
		System.out.printf("%-22s %5s %-20s %n", "Update Hits:", "H", "<Name> <New # Hits>");
		System.out.printf("%-22s %5s %-20s %n", "Update Errors:", "E", "<Name> <New # Errors>");
		System.out.printf("%-22s %5s %-20s %n", "Quit:", "Q", "");
		System.out.println("---------------------------------------------------");
	}
	
	private static void clearInputs() {
		inStr1 = "";
		inStr2 = "";
		inStr3 = "";
		inStr4 = "";
		menuChoice = "";
		printMenu();
		
	}
}
