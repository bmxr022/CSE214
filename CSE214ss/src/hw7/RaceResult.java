package hw7;

public class RaceResult {

	private String model;
	private double[] times;
	
	/**
	 * Constructor.
	 * @param model Car model.
	 * @param times Race times.
	 */
	
	public RaceResult (String model, double[] times) {
		
		this.model = model;
		this.times = times;
		
		/*this.model = new String(model);
		this.times = new double[times.length];
		System.arraycopy(times, 0, this.times, 0, times.length);
		*/
	}
	
	/**
	 * Returns model of car.
	 * @return Model of car.
	 */
	
	public String getModel() {
		
		return model;
		
	}
	
	/**
	 * Returns array of race times.
	 * @return Array of race times.
	 */
	
	public double[] getTimes() {
		
		return times;
	
	}
}
