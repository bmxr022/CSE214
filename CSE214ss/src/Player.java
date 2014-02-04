/* Zach Samuels
 * 108941490
 * Zach.Samuels@stonybrook.edu
 * Homework #1
 * CSE 214
 * R05
 */

/**
 * Holds information about players including:
 * name, # of hits, # of errors.
 * @author Zach Samuels
 *
 */

public class Player {
	private String name;
	private int hits;
	private int errors;

/**
 * 	Creates a new Player object with a default name of "Default Name," 0 hits, and 0 errors.
 */
	
	public Player(){
		name = "Default Name";
		hits = 0;
		errors = 0;
	}
	
	/**
	 * Creates a new Player object with a specified name, # of hits, and # of errors.
	 * If a number below 0 is passed in for the hits/errors variables, they will default to 0.
	 * @param name Name of the Player.
	 * @param hits # of hits. If lower than 0, hits will default to 0.
	 * @param errors # of errors. If lower than 0, errors will default to 0.
	 */
	
	public Player(String name, int hits, int errors){
		this.name = name;
		if (hits >= 0)
			this.hits = hits;
		else this.hits = 0;
		if (errors >= 0)
			this.errors = errors;
		else this.errors = 0;
	}
	
	
	/**
	 * Accessor for name variable.
	 * @return this Player's name.
	 */
	
	public String getName(){
		return name;
	}
	
	/**
	 * Accessor for hits variable.
	 * @return number of Player's hits.
	 */
	
	public int getHits(){
		return hits;
	}
	
	/**
	 * Accessor for errors variable.
	 * @return number of Player's errors.
	 */
	
	public int getErrors(){
		return errors;
	}
	
	/**
	 * Mutator for name variable.
	 * @param name Name of Player.
	 */
	
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Mutator for hits variable.
	 * @param hits Number of Player's hits.
	 * @throws LessThanZeroException hits cannot be less than 0.
	 */
	
	public void setHits(int hits){
		try {	
			if (hits >= 0)
				this.hits = hits;
			else throw new LessThanZeroException("Hits can't be < 0");
		}
		catch (LessThanZeroException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Mutator for errors variable.
	 * @param errors Number of Player's errors.
	 */
	
	public void setErrors(int errors){
		try {	
			if (errors >= 0)
				this.errors = errors;
			else throw new LessThanZeroException("Errors can't be < 0");
		}
		catch (LessThanZeroException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Puts all of a Player's data into a nice, readable string.
	 * @return String representation of a Player's data.
	 */
	public String toString(){
		String s = new String("");
		s += "Name: " + name + ". Hits: " + hits + ". Errors: " + errors + ".";
		return s;
	}
	
	/**
	 * Checks if the Player's data is the same as another object.
	 * Name field is case insensitive.
	 * @param obj Object to be compared.
	 * @return Whether or not the Player is equal to the object being compared.
	 */
	
	public boolean equals(Object obj){
		if (obj instanceof Player) {
			if ((((Player)obj).getName().toLowerCase().equals
			  (getName().toLowerCase())) &&
			  (((Player)obj).getHits() == (getHits())) &&
			  (((Player)obj).getErrors() == (getErrors())))  
				return true;
			else return false;
		}
		else{
			return false;
		}

	}
public static void main(String[] args) {
	Player nig = new Player();
	System.out.println(nig.toString());
	Player gin = new Player();
	System.out.println(gin.toString());
	
	nig.setName("Nig Bojangles");
	nig.setHits(99);
	nig.setErrors(10);
	
	System.out.println(nig.toString());
	
	gin.setName("nig Bojangles");
	gin.setHits(99);
	gin.setErrors(-10);
	
	System.out.println(gin.toString());
	
	System.out.println(nig.equals(gin));
}
}
