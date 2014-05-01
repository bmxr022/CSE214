package hw7;

import java.io.File;
import java.util.ArrayList;

import big.data.DataSource;
import big.data.DataSourceIterator;

public class DataSet {
	
	private ArrayList<RaceResult> dataList;
	private String name;
	private double[] average;
	int numRows;
	
	/**
	 * Constructor. Reads and stores data from a .csv file.
	 * @param csvFilename Filename of data.
	 * @param name Name of data.
	 * @throws FileNotFoundException File not found or not .csv filetype
	 */
	
	private DataSet(String csvFilename, String name) throws FileNotFoundException  {
		File csvFile = new File(System.getProperty("user.dir") + "\\" + csvFilename);
		if ((csvFile.exists()) && csvFilename.endsWith(".csv")) {
			
			this.name = name;
			numRows = 0;
			
			DataSource ds = DataSource.connectCSV(csvFilename);			
			ds.load();
			DataSourceIterator iter = ds.iterator();
			dataList = new ArrayList<RaceResult>();
			average = new double[5];
			
			while (iter.hasData()) {
				
				double[] times = new double[5];
				
				for (int i = 0;  i < 5; i++) {
					
					times[i] = iter.fetchDouble("race" + (i + 1));		//import race times from data to times[]
					average[i] += times[i];								//add race times from times[] to average[]
					
				}
				
				dataList.add(new RaceResult(iter.fetchString("model"), times));		//create new RaceResult from the data and
				numRows++;														//add that RaceResult to the dataList
				iter.loadNext();
				
			}
			
			if (numRows > 1) {
				for (int i = 0; i < 5; i++) {
					average[i] = average[i] / numRows;			////take average of each race's times
				}	
			}
		}		
		else throw new FileNotFoundException("File not found or not .csv filetype: " + csvFile.getAbsolutePath());		
	}
	
	/**
	 * Constructor. Infers name from filename.
	 * @param csvFilename Filename of data.
	 */
	
	public DataSet(String csvFilename) throws FileNotFoundException {
		
		this(csvFilename, csvFilename.replace(".csv", ""));
		
	}
	
	/**
	 * Returns result of the specified race.
	 * @param raceNum Number of race from 1-5.
	 * @return Result of specified race.
	 * @throws RaceNumberOutOfBoundsException Invalid race number.
	 */
	
	public RaceResult getResult(int raceNum) throws RaceNumberOutOfBoundsException {
		
		if ((raceNum > 0) && (raceNum < 6))
			return dataList.get(raceNum - 1);
		else throw new RaceNumberOutOfBoundsException("Invalid race number. Enter 1-5");
		
	}
	
	/**
	 * Returns number of rows in this data set.
	 * @return Number of rows in this data set.
	 */
	
	public int numRows() {
		return numRows;
	}
	
	public String toString() {
		String result = String.format("%-10s %-3d %-10.2f %-10.2f %-10.2f %-10.2f %-10.2f", name, numRows, average[0], 
				average[1], average[2], average[3], average[4]);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		try {
			DataSet ds = new DataSet("honda.csv");
			System.out.println(ds.toString());
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		/*for (RaceResult rr : dataList) {
			for (double num : rr.times) {
				System.out.println(num);
			}
		}*/
	}
}
