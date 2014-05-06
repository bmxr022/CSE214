package hw7;

import java.io.File;
import java.util.ArrayList;

import big.data.DataSource;
import big.data.DataSourceIterator;

public class DataSet {
	
	private ArrayList<RaceResult> dataList;
	private String name;
	private double[] average;
	private int numRows;
	
	/**
	 * Constructor. Reads and stores data from a .csv file.
	 * @param csvFilename Filename of data.
	 * @param name Name of data.
	 * @throws FileNotFoundException File not found or not .csv filetype
	 */
	
	public DataSet(String csvFilename, String name) throws FileNotFoundException  {
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
	
	/**
	 * Returns a String representation of the DataSet.
	 * @return String representation of the DataSet.
	 */
	
	public String toString() {
		String result = String.format("%-10s %-18d %-9.2f %-9.2f %-9.2f %-9.2f %-9.2f", name, numRows, average[0], 
				average[1], average[2], average[3], average[4]);
		return result;
	}
	
	/**
	 * Change name of this DataSet.
	 * @param newName New name of this DataSet.
	 */
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * Return name of this DataSet.
	 * @return Name of this DataSet.
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Return the data from this DataSet.
	 * @return Data from this DataSet.
	 */
	
	public ArrayList<RaceResult> getDataList() {
		return dataList;
	}
	
	/**
	 * Return the averages of the races.
	 * @return Averages of the races.
	 */
	
	public double[] getAverage() {
		return average;
	}
	
	/**
	 * Return number of rows in this DataSet.
	 * @return Number of rows in this DataSet.
	 */
	
	public int getNumRows() {
		return numRows;
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		try {
			DataSet ds = new DataSet("honda.csv");
			DataSet ds2 = new DataSet("hyundai.csv");
			System.out.println(ds.toString());
			System.out.println(ds2.toString());
			DataSetNameComparator dsnc = new DataSetNameComparator();
			System.out.println(dsnc.compare(ds2, ds));
			System.out.println(dsnc.equals(ds2));
			
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
