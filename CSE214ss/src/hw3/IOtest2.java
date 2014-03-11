package hw3;

import java.io.*;
import java.util.Scanner;

public class IOtest2 {
	public static void main(String[] args) throws IOException {
		
		BufferedWriter writer = null;
		Scanner s = new Scanner(System.in);
		String inStr = "";
		
		try {
			String fileName = "myfile.txt";
			File outFile = new File(fileName);
            System.out.println(outFile.getCanonicalPath());
            
            writer = new BufferedWriter(new FileWriter(outFile));
            while (!inStr.equals("quit")) {
            	inStr = s.nextLine();
            	writer.write(inStr);
            	writer.newLine();
            }			
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				writer.close();
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public IntNode getNthNode(int n) throws IllegalArgumentException {
			if (n < 1) throw new IllegalArgumentException("Node must be > 1");
			IntNode traveler = head;
			int counter = 1;
			while (counter <= n) {
				if (counter == n)
					return traveler;
				if (traveler.getLink() == null)
					throw new IllegalArgumentException("Node doesn't exist.");
				traveler = traveler.getLink();
				counter++;
			}
		}
	}
}
