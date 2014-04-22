/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #6
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManager {

	private static String inStr = "";
	private static String inStr2 = "";
	private static String inStr3 = "";
	private static int quantIn = 0;

	public static void main(String[] args) {
		Inventory inventory;
		try {
			FileInputStream in = new FileInputStream("store.obj");
			ObjectInputStream s = new ObjectInputStream(in);
			inventory = (Inventory) s.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error. Creating new Inventory object.");
			inventory = new Inventory();
		}
		
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.println();
			printMenu();
			System.out.println();
			clearInputs();
			inStr = s.nextLine().toUpperCase();
			
			switch (inStr) {
				
				case "A": {
					System.out.println("Enter name of item to add: ");
					inStr2 = s.nextLine();
					System.out.println("Enter location to put item: ");
					inStr3 = s.nextLine();
					System.out.println("Enter quantity of " + inStr2 + ": ");
					try {
						quantIn = s.nextInt();
					}
					catch (InputMismatchException e) {
						System.out.println("Quantity must be an integer.");
						break;
					}
					try {
						inventory.newItem(inStr2, inStr3, quantIn);
					} catch (ItemInStoreException e) {
						System.out.println(e.getMessage());
						break;
					}
					break;	
				}
				case "R": {
					System.out.println("Enter name of item to remove: ");
					inStr2 = s.nextLine();
					try {
						inventory.removeItem(inStr2);
					} catch (ItemNotInStoreException e) {
						System.out.println(e.getMessage());
						break;
					}
					break;
				}
				
				case "M": {
					System.out.println("Enter name of item to move: ");
					inStr2 = s.nextLine();
					System.out.println("Enter location to move item to: ");
					inStr3 = s.nextLine();
					try {
						inventory.moveItem(inStr2, inStr3);
					} catch (ItemNotInStoreException e) {
						System.out.println(e.getMessage());
						break;
					}
					break;
				}
				case "I": { 
					System.out.println("Enter name of item to find: ");
					inStr2 = s.nextLine();
					try {
						System.out.println(inventory.quantityOf(inStr2) + "x " + inStr2 + " at " 
								+ inventory.locationOf(inStr2));
					} catch (ItemNotInStoreException e) {
						System.out.println(e.getMessage());
						break;
					}
					break;
				}
				case "U": {
					System.out.println("Enter item to change quantity of: ");
					inStr2 = s.nextLine();
					System.out.println("Enter new quantity of item: ");
					try {
						quantIn = s.nextInt();
					}
					catch (InputMismatchException e) {
						System.out.println("Quantity must be an integer.");
						break;
					}
					try {
						inventory.updateQuantity(inStr2, quantIn);
					} catch (ItemNotInStoreException e) {
						System.out.println(e.getMessage());
						break;
					}
					break;
				}
				case "L": {
					System.out.println("Enter a location: ");
					inStr2 = s.nextLine();
					try {
						ArrayList<String> locList = inventory.itemsAt(inStr2);
						for (String item : locList) {
							System.out.println(item);
						}
					} catch (LocationNotInStoreException e) {
						System.out.println(e.getMessage());
						break;
					}
					break;
				}
				case "O": {
					System.out.println("Enter a location: ");
					inStr2 = s.nextLine();
					try {
						System.out.println(inventory.maxStockAt(inStr2) + " has the highest stock at that location.");
					} catch (LocationNotInStoreException | EmptyLocationException e) {
						System.out.println(e.getMessage());
						break;
					}
					break;
				}
				case "P": {
					inventory.printAllItems();
					break;
				}
				
			}
		} while (!inStr.equalsIgnoreCase("Q"));
		
		try {
			FileOutputStream f = new FileOutputStream("store.obj");
			ObjectOutputStream ostr = new ObjectOutputStream(f);
			//Inventory anObject = new Inventory(); //or any object that implements Serializable
			//String anotherObject = “The String class implements Serializable too”;
			ostr.writeObject(inventory);
			//s.writeObject(anotherObject);
		} catch (IOException e) {
			System.out.println("IOException. Didn't save.");
		}
		
		System.exit(0);
		
	}

	private static void clearInputs() {
		inStr = "";
		inStr2 = "";
		inStr3 = "";
		quantIn = 0;
	}
	
	private static void printMenu() {
		System.out.println("A: Add item");
		System.out.println("R: Remove item");
		System.out.println("M: Move item");
		System.out.println("I: Get item");
		System.out.println("U: Update quantity");
		System.out.println("L: List all items at a given location");
		System.out.println("O: Get item with max quantity at a given location");
		System.out.println("P: Print all items in the store grouped by location");
		System.out.println("Q: Quit (and save the data to store.obj");
		System.out.println("Enter an option: ");
	}
}

