package hw7;

public class test {

	public static void main(String[] args) {
		double[] dar = new double[5];
		dar[0] = 22.22;
		
		RaceResult rr = new RaceResult("yolo", dar);
		rr.times[0] = 1.1;
		System.out.println("dar: " + dar[0]);
		
	}
}
