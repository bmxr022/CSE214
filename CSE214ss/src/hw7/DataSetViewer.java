package hw7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataSetViewer {

	static String inStr1 = "";
	static String inStr2 = "";
	static String inStr3 = "";
	
		public static void main(String[] args) {
			
			ArrayList<DataSet> dataSetList = new ArrayList<DataSet>();
			Scanner s = new Scanner(System.in);
			int lastSort = 0; 			//0: unsorted. 1: name. 2: row. 3: average.
			int lastAvgNum = 0;			//last race number used to sort by average.
			
			do {
				clearInputs();
				printMenu();
				inStr1 = s.nextLine().toUpperCase();
				
				switch (inStr1) {
					
					case "A": {
						
						System.out.println("Enter a filename to add: ");
						inStr2 = s.nextLine();
						System.out.println("Enter a name for this data (or leave blank to infer from filename)");
						inStr3 = s.nextLine();
						
						try {
							
							if (inStr3.compareTo("") == 0) {
							
								dataSetList.add(new DataSet(inStr2));
								inStr3 = inStr2.replace(".csv", "");
							}
							else 
								dataSetList.add(new DataSet(inStr2, inStr3));
						} catch (FileNotFoundException e) {
							System.out.println(e.getMessage());
							break;
						}
						System.out.println(inStr2 + " added, named " + inStr3 + ".");
						break;
					}
					
					case "N": {
						Collections.sort(dataSetList, new DataSetNameComparator());
						lastSort = 1;
						System.out.println("     Name:     Rows:   Averages:   Race #1"
								+ "   Race #2   Race #3   Race #4   Race #5");
						int counter = 0;
						for (DataSet ds : dataSetList) {
							counter++;
							System.out.println(counter + ".   " + ds.toString());
						}
						break;
					}
					case "R": {
						Collections.sort(dataSetList, new DataSetNumRowsComparator());
						lastSort = 2;
						System.out.println("     Name:     Rows:   Averages:   Race #1"
								+ "   Race #2   Race #3   Race #4   Race #5");
						int counter = 0;
						for (DataSet ds : dataSetList) {
							counter++;
							System.out.println(counter + ".   " + ds.toString());
						}
						break;
					}
					case "V": {
						try {
							System.out.println("Enter race number to compare (1-5): ");
							lastAvgNum = s.nextInt();		
							Collections.sort(dataSetList, new DataSetRaceAverageComparator(lastAvgNum));
							lastSort = 3;
							System.out.println("     Name:     Rows:   Averages:   Race #1"
									+ "   Race #2   Race #3   Race #4   Race #5");
							int counter = 0;
							for (DataSet ds : dataSetList) {
								counter++;
								System.out.println(counter + ".   " + ds.toString());
							}
							break;
						} catch (NumberFormatException | RaceNumberOutOfBoundsException e) {
							System.out.println(e.getMessage());
							break;
						} catch (InputMismatchException e) {
							System.out.println("Input must be an integer between 1 and 5.");
							clearInputs();
							break;							
						}
					}
				}
			}
			while (!inStr1.equalsIgnoreCase("Q"));
			System.out.println("Exiting normally.");
			System.exit(0);
			
		}
		
	public static void printMenu() {
		System.out.println();
		System.out.println("A) Add data source.");
		System.out.println("N) Sort by name.");
		System.out.println("R) Sort by row.");
		System.out.println("V) Sort by average.");
		System.out.println("G) Get data.");
		System.out.println("Q) Quit.");
		System.out.println("Enter a menu option: ");
		System.out.println();
	}
	
	public static void clearInputs() {
		inStr1 = "";
		inStr2 = "";
		inStr3 = "";
	}
}
