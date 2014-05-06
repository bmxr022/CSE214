package hw7;

import java.util.Comparator;

public class DataSetNumRowsComparator implements Comparator<DataSet> {

	/**
	 * Check which of two DataSets has a fewer number of rows.
	 * @return Negative number if ds1's rows < ds2's.
	 * Positive number if ds1's rows > ds2's.
	 * 0 otherwise.
	 */
	
	public int compare(DataSet ds1, DataSet ds2) {

		return (ds1.getNumRows() - ds2.getNumRows());

	}

}
