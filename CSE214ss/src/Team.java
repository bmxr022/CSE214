
public class Team implements Cloneable {

	private final int NUM_PLAYERS_ON_TEAM = 40;
	private Player[] teamArray;
	
	/**
	 * Creates a team whose size is specified by NUM_PLAYERS_ON_TEAM.
	 */
	
	public Team(){
		 teamArray = new Player[NUM_PLAYERS_ON_TEAM];
	}
	
	/**
	 * Makes a clone of the team. Any changes made to the clone will not affect the original and vice versa.
	 * @return Clone of the team. *Cast to Team to use*
	 * @throws CloneNotSupportedException 
	 */
	
	public Object clone() throws CloneNotSupportedException {
		Team temp = new Team();
		for (int i = 0; i < teamArray.length; i++) {
			temp.teamArray[i] = new Player(teamArray[i].getName(), teamArray[i].getHits(), teamArray[i].getErrors());
		}
		return temp;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Team) {	
			for (int i = 0; i < teamArray.length; i++){							
				if (!(((Team) obj).teamArray[i].equals(teamArray[i])))
					return false;
			}
			return true;
		}
		else return false;
		
		
/*		boolean temp = true;
		if (obj instanceof Team) {
			for (int i = 0; i < teamArray.length; i++) {
				if (!teamArray[i].equals((obj.teamArray[i])))
					return false;
			}
			return true;
			
		}
		else return false;
*/	}
	
	public static void main(String[] args) {
		Team giants = new Team();
		giants.
	}
}
