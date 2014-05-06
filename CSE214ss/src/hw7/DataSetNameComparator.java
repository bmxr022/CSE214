package hw7;

import java.util.Comparator;

public class DataSetNameComparator implements Comparator<DataSet> {

	/**
	 * Check which of two DataSets' names come first alphabetically.
	 * @return Negative number if ds1's name comes before ds2's.
	 * Positive number if ds1's name comes after ds2's.
	 * 0 otherwise.
	 */
	
	public int compare(DataSet ds1, DataSet ds2) {
		/*String dsStr1 = new String(ds1.getName().toLowerCase());
		String dsStr2 = new String(ds1.getName().toLowerCase());*/
		
		return ds1.getName().compareToIgnoreCase(ds2.getName());
		/*if (result < 0)
			return -1;
		if (result == 0)
			return 0;
		else return 1;*/
	}
	
/*	public boolean equals(Object obj) {
		return ((DataSetNameComparator)obj == this); 
	}*/

}
