package hw3;
import java.util.*;
import java.io.*;

public class CodeFormatter {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Stack stack = new Stack();
		String fileName = "";
		String data = "";
		String allCode = "";
		
		System.out.println("Enter name of file to format: ");
		fileName = scanner.nextLine();
		
		try {
			FileInputStream fis = new FileInputStream(fileName); 
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader stdin = new BufferedReader(inStream);
				
			while (data != null) {
				data = stdin.readLine();
				allCode = allCode + "\n" + data;		
			}
			//System.out.println(allCode);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		for (int i = 0; i < allCode.length(); i++) {
			switch(allCode.charAt(i)) {
				case '{':
					
		}
		
	}
}
