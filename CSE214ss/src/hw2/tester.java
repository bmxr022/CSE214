package hw2;

public class tester {
	public static void main(String[] args) {
		
		//switch fields back to private!
		
		MainMemory nig = new MainMemory(10);
		nig.setBestFit(true);
	
		try {
			nig.malloc(1);
			nig.malloc(2);
			nig.malloc(6);
			
		}
		catch (OutOfMemoryException | SuboptimallyAllocatedMemoryException | TooSmallException e){
			System.out.println(e.getMessage());
		}
		MemoryBlock trav = nig.head;
		for(int i = 1; i <= nig.numBlocks; i++) {
			System.out.println(i + ". Size: " + trav.getSize() + " Address: " + trav.getAddress()
					+ " Status: " + trav.getStatus());
			trav = trav.getNext();
		}
	}
}
