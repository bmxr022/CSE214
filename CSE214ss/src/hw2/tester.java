package hw2;

public class tester {
	public static void main(String[] args) {
		
		//switch fields back to private!
		
		MainMemory nig = new MainMemory(100);
		MemoryBlock trav = nig.head;
		for(int i = 1; i <= nig.numBlocks; i++) {
			System.out.println("s: " + trav.getSize());
			trav = trav.getNext();
		}
	}
}
