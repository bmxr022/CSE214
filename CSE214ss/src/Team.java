
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
	 * @throws CloneNotSupportedException 
	 */
	
	public Object clone() throws CloneNotSupportedException {
		Team temp = new Team();
		for (int i = 0; i < teamArray.length; i++) {
			temp.teamArray[i] = new Player(teamArray[i].getName(), teamArray[i].getHits(), teamArray[i].getErrors());
			temp.currentNumPlayers++;
		}
		return temp;
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
	 * @return int for size of Team. 
	 */
	
	public int size(){
		int temp = 0;
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
	}
	
	/**
	 * Adds a Player to the Team.
	 * @param p Player to be added.
	 * @param position Position in the Team (not the array).
	 * @throws IllegalArgumentException Position too high.
	 * @throws FullTeamException Not enough room on team.
	 */
	
	public void addPlayer(Player p, int position) {
		try {
			if (size() >= NUM_PLAYERS_ON_TEAM)
				throw new FullTeamException("No more room on team. addPlayer");
			else if (position > (NUM_PLAYERS_ON_TEAM - 1))
				throw new IllegalArgumentException("That position is too high for this team. addPlayer");
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
		}
		catch (FullTeamException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Remove Player from passed position on Team.
	 * @param position Position on team (not index of array).
	 * @throws IllegalArgumentException Position is out of bounds
	 */
	
	public void removePlayer(int position) {
		try {
			if ((position > (NUM_PLAYERS_ON_TEAM)) || (position < 1))
				throw new IllegalArgumentException("That position is out of bounds. removePlayer");
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
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Player getPlayer(int position) {
		try { 
			if ((position > (NUM_PLAYERS_ON_TEAM)) || (position < 1))
				throw new IllegalArgumentException("That position is out of bounds. getPlayer");
			else if (teamArray[(position - 1)] == null)
				throw new EmptyTeamPositionException("There is no player at that position. getPlayer");
			else return teamArray[(position - 1)];
		}
		catch (IllegalArgumentException | EmptyTeamPositionException e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < (currentNumPlayers); i++) {
			str+= teamArray[i].toString();
			str+="\n";
		}
		return str;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Team giants = new Team();
		Player eli = new Player("Eli Manning", 100, 0);
		Player nicks = new Player("Hakeem Nicks", 99, 1);
		Player zach = new Player("Zachy", 101, 0);
		Player rayj = new Player("rayj", 69, 0);
		Player kobro = new Player("kobro", 111, 0);
		Player dillo = new Player("dillo", 24, 1);
		
		giants.addPlayer(eli, 1);
		giants.addPlayer(nicks, 2);
		giants.addPlayer(zach, 5);
		giants.addPlayer(rayj, 3);
		giants.addPlayer(dillo, 2);
		giants.addPlayer(kobro, 4);
		System.out.println(giants.toString());
		System.out.println("^Before removal.\n");
		giants.removePlayer(1);
		//Team hoodz = (Team)giants.clone();
		//Team giants2 = (Team)giants.clone();
		System.out.println(giants.toString());
		System.out.println(giants.getPlayer(6).toString());
		
	}
}
