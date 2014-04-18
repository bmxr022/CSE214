/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #5
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw5;

import java.io.IOException;
import java.util.Scanner;

public class ParticleDriver {

	/**
	 * Main method of homework #5. Menu-driven program to create binary trees from text files.
	 * @param args
	 */
	
	public static void main(String[] args) {
		String inStr = null;
		ParticleTree tree = null;
		Scanner s = new Scanner(System.in);
		boolean treeBuilt = false;
		
		do {
			printMenu();
			System.out.println("Enter an option: ");
			inStr = s.nextLine().toUpperCase();
			
			switch (inStr) {
				case "T": {
					System.out.println("Enter filename of tree to build: ");
					inStr = s.nextLine();
					try {
						tree = new ParticleTree(inStr);
						System.out.println("Tree created from " + inStr + ".txt");
						treeBuilt = true;
					}
					catch (IOException | IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					inStr = "";
					break;
				}
				case "F": {
					if (treeBuilt == false) {
						System.out.println("Can't do anything until tree is built!");
						break;
					}
					System.out.println("Enter particle name to find: ");
					inStr = s.nextLine();
					try {
						ParticleNode node = tree.findParticle(inStr);
						System.out.print(node.toString() + " ");
						if (node.getLeftChild() != null)
							System.out.print(node.getLeftChild() + " ");
						if (node.getRightChild() != null)
							System.out.println(node.getRightChild());
						else System.out.println();
					}
					catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					inStr = "";
					break;
				}
				case "P": {
					if (treeBuilt == false) {
						System.out.println("Can't do anything until tree is built!");
						break;
					}
					tree.printAllParticles();
					inStr = "";
					break;
				}
				case "H": {
					if (treeBuilt == false) {
						System.out.println("Can't do anything until tree is built!");
						break;
					}
					System.out.println("Tree depth: " + tree.depth());
					inStr = "";
					break;
				}
				case "D": {
					if (treeBuilt == false) {
						System.out.println("Can't do anything until tree is built!");
						break;
					}
					System.out.println("Enter particle name: ");
					inStr = s.nextLine();
					try {
						ParticleNode focusNode = tree.findParticle(inStr);
						tree.preOrderTrav(focusNode);
					}
					catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					inStr = "";
					break;
				}
			}
		}
		while (!inStr.equalsIgnoreCase("Q"));
		System.exit(0);
	}
	
	/**
	 * Prints the menu.
	 */
	
	private static void printMenu() {
		System.out.println("T: Build a tree\n"
				+ "F: Find a particle\n"
				+ "P: Print all particles\n"
				+ "H: Depth\n"
				+ "D: Compute decay modes\n"
				+ "Q: Quit");
	}
	
}
