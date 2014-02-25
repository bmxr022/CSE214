package hw2;

public class tester {
	public static void main(String[] args) {
		
		//switch fields back to private!
		
		MainMemory nig = new MainMemory(100);

	
		try {
			nig.malloc(10);
			nig.malloc(15);
			nig.malloc(20);
			nig.malloc(55);
			nig.free(0);
			nig.addMemory(100);
			
			System.out.println(nig.toString());

			
		}
		catch (OutOfMemoryException | SuboptimallyAllocatedMemoryException | TooSmallException | 
				AlreadyFreeException | BlockNotFoundException e){
			System.out.println(e.getMessage());
		}
		//MemoryBlock trav = nig.head;
		//for(int i = 1; i <= nig.numBlocks; i++) {
		//	System.out.println(i + ". Size: " + trav.getSize() + " Address: " + trav.getAddress()
		//			+ " Status: " + trav.getStatus());
		//	trav = trav.getNext();
	//	}
	}
}
