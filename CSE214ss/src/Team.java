/*
 * Zach Samuels
 * 108941490
 * Zachary.Samuels@stonybrook.edu
 * HW #1
 * CSE214
 * R05 - Vyassa Baratham
 */

/**
 * Class of Team objects. Default size of 5.
 * @author Zach Samuels
 *
 */
public class Team implements Cloneable {

	private final int NUM_PLAYERS_ON_TEAM = 5;
	private int currentNumPlayers;
	private Player[] teamArray;
	
	/**
	 * Creates a team whose size is specified by NUM_PLAYERS_ON_TEAM.
	 */
	
	public Team(){
		 teamArray = new Player[NUM_PLAYERS_ON_TEAM];
		 currentNumPlayers = 0;
	}
	
	/**
	 * Makes a clone of the team. Any changes made to the clone will not 
	 * affect the original and vice versa.
	 * @return Clone of the team. *Cast to Team to use*
	 */
	
	public Object clone() {
		Team temp = new Team();
//		try {
			for (int i = 0; i < teamArray.length; i++) {
				temp.teamArray[i] = new Player(teamArray[i].getName(), teamArray[i].getHits(), teamArray[i].getErrors());
				temp.currentNumPlayers++;
			}
			return temp;
//		}
//		catch (CloneNotSupportedException e) {
//			System.out.println(e.getMessage());
//		}
//		return temp;
	}
	
	/**
	 * Tests if two teams are equivalent using the equals method of the
	 * player class.
	 * @param obj Object to be compared.
	 * @return Boolean: whether or not the Teams are equal.
	 */
	
	public boolean equals(Object obj) {
		if (obj instanceof Team) {	
			for (int i = 0; i < teamArray.length; i++){							
				if (!(((Team) obj).teamArray[i].equals(teamArray[i])))
					return false;
			}
			return true;
		}
		else return false;
	}
	
	/**
	 * Returns size of Team by counting non-null positions in the array.
	 * @return current size of team. 
	 */
	
	public int size(){
/*		int temp = 0;
		try { 
			if (this == null)
				throw new ObjectNotInstantiatedException("Object not instantiated! size");
			for (int i = 0; i < NUM_PLAYERS_ON_TEAM; i++) {
				if (teamArray[i] != null)
					temp++;
			}
		}
		catch (ObjectNotInstantiatedException e){
			System.out.println(e.getMessage());
		}
		return temp;
*/
		return currentNumPlayers;
	}
	
	/**
	 * Adds a Player to the Team.
	 * @param p Player to be added.
	 * @param position Position in the Team (not the array).
	 * @throws IllegalArgumentException Position too high.
	 * @throws FullTeamException Not enough room on team.
	 */
	
	public void addPlayer(Player p, int position) throws FullTeamException, IllegalArgumentException{
//		try {
			if ((currentNumPlayers + 1) > NUM_PLAYERS_ON_TEAM)
				throw new FullTeamException("No more room on team.");
			else if (position > (NUM_PLAYERS_ON_TEAM - 1))
				throw new IllegalArgumentException("That position is too high for this team.");
			else if (position > (currentNumPlayers + 1))
				throw new IllegalArgumentException("The other positions are not filled yet.");
			else {
				if (teamArray[(position - 1)] == null)
					teamArray[(position - 1)] = p;
				else {
					for (int i = currentNumPlayers; i > position - 1; i--) {
						teamArray[i] = teamArray[i - 1];
					}
					teamArray[position - 1] = p;
					
				}
				currentNumPlayers++;	
			}
//		}
//		catch (FullTeamException | IllegalArgumentException e) {
//			System.out.println(e.getMessage());
//		}
	}
	
	/**
	 * Remove Player from passed position on Team.
	 * @param position Position on team (not index of array).
	 * @throws IllegalArgumentException Position is out of bounds
	 */
	
	public void removePlayer(int position) throws IllegalArgumentException {
//		try {
			if ((position > (NUM_PLAYERS_ON_TEAM)) || (position < 1))
				throw new IllegalArgumentException("That position is out of bounds.");
			else {
				teamArray[position - 1] = null;
				for (int i = (position - 1); i < (NUM_PLAYERS_ON_TEAM - 1); i++) {
					if (i == (NUM_PLAYERS_ON_TEAM - 1)) {
						teamArray[i+1] = null;
						break;
					}
					teamArray[(i)] = teamArray[i+1];
					
				}
				currentNumPlayers--;
			}
//		}
//		catch (IllegalArgumentException e) {
//			System.out.println(e.getMessage());
//		}
	}
	
	/**
	 * Returns player at specified position.
	 * @param position Position of player.
	 * @return Player at specified position. If an invalid position is 
	 * entered, the Player at position 1 is returned.
	 * @throws IllegalArgumentException position is too high/low.
	 * @throws EmptyTeamPositionException position on Team is empty.
	 */
	
	public Player getPlayer(int position) throws IllegalArgumentException, EmptyTeamPositionException{
//		try { 
			if (position > (currentNumPlayers))
				throw new IllegalArgumentException("That position is too high.");
			else if ((position < 1) || (currentNumPlayers < 1))
				throw new IllegalArgumentException("That position is too low.");
			else if (teamArray[(position - 1)] == null)
				throw new EmptyTeamPositionException("There is no player at that position.");
			else return teamArray[(position - 1)];
//		}
//		catch (IllegalArgumentException | EmptyTeamPositionException e) {
//			System.out.println(e.getMessage() + "...Returning position 1.");
//			return teamArray[0];
//		}
		//return teamArray[(position - 1)];
	}
	
	/**
	 * Returns Player on Team with the passed name (case insensitive) otherwise returns a default player.
	 * @param name Name of Player (case insensitive).
	 * @return Player with same name (or default player if none is found).
	 * @throws IllegalArgumentException No matching Player names found.
	 */

	public Player getPlayerByName(String name) throws IllegalArgumentException {
		boolean exists = false;
		Player temp = new Player();
//		try {
			for (int i = 0; i < (currentNumPlayers); i++) {
				if (name.toLowerCase().equals(teamArray[i].getName().toLowerCase())) {
					exists = true;
					temp = teamArray[i];
				}
					
			}
			if (exists == false)
				throw new IllegalArgumentException("No Players match that name.");
//		}
//		catch (IllegalArgumentException e) {
//			System.out.println(e.getMessage() + "... returning new default player.");
//		}
		return temp;
	}
	
	/**
	 * Get leader of chosen statistic.
	 * @param stat Statistic wanted. Either hits or errors.
	 * @return Player with the best of the passed stat.
	 * @throws IllegalArgumentException Invalid entry.
	 */
	
	public Player getLeader(String stat) {
		Player leader = teamArray[0];
		try { 
			if (stat.equals("hits")) {
				for(int i = 0; i < currentNumPlayers; i++) {
					if (teamArray[i].getHits() > leader.getHits())
						leader = teamArray[i];
				}
				return leader;
			}
			else if (stat.equals("errors")) {
				for(int i = 0; i < currentNumPlayers; i++) {
					if (teamArray[i].getErrors() < leader.getErrors())
						leader = teamArray[i];
				}
				return leader;
			}
		else throw new IllegalArgumentException("Enter either hits or errors");
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return leader;
		}
	}
	
	public String toString() {
		String str = "";
		str = str.format("%-25s %10s %10s %n", "Position/Name:", "Hits:", "Errors:");
		str+= "-----------------------------------------------\n";
		int counter = 1;
		for(int i = 0; i < (currentNumPlayers); i++) {
			str+= counter + ". ";
			counter++;
			str+= teamArray[i].toString();
		}
		return str;
	}
	
	public void printAllPlayers() {
		System.out.println(toString());
	}
	
}
