/*
 * Zach Samuels
 * 108941490
 * Zachary.Samuels@stonybrook.edu
 * HW #2
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw2;
import java.util.Scanner;
/**
 * Menu-driven program that simulates the management of memory. Default size is 1000.
 * @author Zach Samuels
 */

public class MemoryManager {
	private static MainMemory memory = new MainMemory(1000);
	private static String menuChoice = "";
	private static String inStr = "";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println(memory.toString());
		
		printMenu();
		
		do {
			
			System.out.println("Enter a menu command: ");
			menuChoice = in.nextLine().toLowerCase();
			
			while (!(menuChoice.equals("a") || menuChoice.equals("d") || menuChoice.equals("m") || menuChoice.equals("f")
					|| menuChoice.equals("s") || menuChoice.equals("v") || menuChoice.equals("w") 
					|| menuChoice.equals("l") || menuChoice.equals("q") || menuChoice.equals(""))) {
				System.out.println("Invalid menu command.");
				clearInputs();
			}
			
			switch (menuChoice) {
				case "a":
					System.out.println("Size to add?: ");
					inStr = in.nextLine();
					try {
						memory.addMemory(Integer.parseInt(inStr));
					}
					catch (NumberFormatException e) {
						System.out.println("Invalid entry. Enter a valid integer.");
					}
					clearInputs();
					break;
					
				case "d":
					System.out.println(memory.toString());
					clearInputs();
					break;
					
				case "m":
					System.out.println("Size of block to be allocated?: ");
					inStr = in.nextLine();
					try {
						memory.malloc(Integer.parseInt(inStr));
					}
					catch (NumberFormatException | OutOfMemoryException | SuboptimallyAllocatedMemoryException | TooSmallException e) {
						if (e instanceof NumberFormatException)
							System.out.println("Invalid entry. Enter a valid integer.");
						else System.out.println(e.getMessage());
					}
					clearInputs();
					break;
					
				case "f":
					System.out.println("Address to free?: ");
					inStr = in.nextLine();
					try {
						memory.free(Integer.parseInt(inStr));
					}
					catch (AlreadyFreeException | BlockNotFoundException e) {
						System.out.println(e.getMessage());
					}
					clearInputs();
					break;
					
				case "s":
					System.out.println("Memory allocation algorithm? (best fit or first fit) ");
					inStr = in.nextLine();
					try {
						memory.setAllocationAlgorithm(inStr);
					}
					catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					clearInputs();
					break;
					
				case "v":
					System.out.println("Available memory: " + memory.getAvailableMemory());
					clearInputs();
					break;
					
				case "w":
					System.out.println("Allocated memory: " + memory.getAllocatedMemory());
					clearInputs();
					break;
					
				case "l":
					System.out.println("Largest available block size: " + memory.getLargestAvailable().getSize());
					clearInputs();
					break;
			}
		}
		while (!menuChoice.equals("q"));
		System.out.println("Exiting...");
		System.exit(0);
	}
	
	/**
	 * Clears data from input fields.
	 */
	
	private static void clearInputs() {
		menuChoice = "";
		inStr = "";
		printMenu();
	}
	
	/**
	 * Prints option menu.
	 */
	
	private static void printMenu() {
		System.out.println("------------------------------------");
		System.out.printf("A: Add memory\n"
				+ "D: Display memory contents\n"
				+ "M: Malloc\n"
				+ "F: Free\n"
				+ "S: Set memory allocation algorithm\n"
				+ "V: Get available memory\n"
				+ "W: Get allocated memory\n"
				+ "L: Get largest available block size\n"
				+ "Q: Quit\n");
		System.out.println("------------------------------------");
	}
}
