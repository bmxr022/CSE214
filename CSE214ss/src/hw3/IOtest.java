package hw3;
import java.io.*;

public class IOtest {

	public static void main(String[] args) {
		String fileName = new String("myfile.txt");
		
		try {
			FileInputStream fis = new FileInputStream(fileName); 
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader stdin = new BufferedReader(inStream);
			
			String data = stdin.readLine();
			while (data != null) {
				System.out.println(data);
				data = stdin.readLine();
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
