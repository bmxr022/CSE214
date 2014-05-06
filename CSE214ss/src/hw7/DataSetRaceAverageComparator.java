package hw7;

import java.util.Comparator;

public class DataSetRaceAverageComparator implements Comparator<DataSet> {
	private int raceNum;
	
	public DataSetRaceAverageComparator(int raceNum) throws RaceNumberOutOfBoundsException {
		if ((raceNum > 5)||(raceNum < 1))
			throw new RaceNumberOutOfBoundsException("Invalid race number.");
		this.raceNum = raceNum - 1;
	}
	
	public int compare(DataSet ds1, DataSet ds2) {
		
		double result = ds1.getAverage()[raceNum] - ds2.getAverage()[raceNum];
		
		if (result < 0)
			return -1;
		if (result > 0)
			return 1;
		else return 0;
	
	}

}
