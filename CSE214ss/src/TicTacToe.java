import java.util.Scanner;

public class TicTacToe {
	private char[][] boardArray = { {'-', '-', '-'} ,
									{'-', '-', '-'}, 
									{'-', '-', '-'} };
	private boolean playing = false;
	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner in = new Scanner(System.in);
		
		game.printBoard();
		while (playing) {
		System.out.println();	
		}
	}
	
	public TicTacToe() {
		playing = true;
	}
	
	private void printBoard() {
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(boardArray[j][i] + "  ");
			}
			System.out.println();
		}
	}
	
	
}
