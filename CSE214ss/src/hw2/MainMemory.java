package hw2;

import java.lang.Exception;
//import java.awt.SecondaryLoop;

public class MainMemory {
	public MemoryBlock head;
	private MemoryBlock tail;
	private MemoryBlock largestAvailable;
	private boolean bestFit;		//true: best fit. false: first fit.
	private int availableMemory;
	private int allocatedMemory;
	public int numBlocks;
	
	
	/**
	 * Constructor for MainMemory. Creates a new MemoryBlock with an address of 0 and the passed size.
	 * @param size Size of memory.
	 */
	
	public MainMemory(int size) {
		head = new MemoryBlock(size);
		tail = head;
		largestAvailable = head;
		availableMemory = size;
		allocatedMemory = 0;
		bestFit = true;
		numBlocks = 1;
	}
	
	public int malloc(int size) throws OutOfMemoryException, SuboptimallyAllocatedMemoryException {
		if (size > availableMemory)
			throw new OutOfMemoryException("Out of memory.");
		if (size > largestAvailable.getSize())
			throw new SuboptimallyAllocatedMemoryException("Memory suboptimally allocated.");
//		if (bestFit)
//			return mallocBestFit(size);
		else return mallocFirstFit(size);
		
	}
	
//	private int mallocBestFit(int size) {
		
//	}
	
	private int mallocFirstFit(int size) {
		MemoryBlock traveler = head;
		for (int i = 1; i <= numBlocks; i++) {
			if ((traveler.getSize() == size) && !traveler.getStatus()) {
				traveler.setStatus(true);
				availableMemory -= size;
				allocatedMemory += size;
				if (largestAvailable == traveler)
					findLargestAvailable();
				return traveler.getAddress();
			}
			if ((traveler.getSize() > size) && !traveler.getStatus()) {
				MemoryBlock newBlock = new MemoryBlock(size);
				numBlocks++;
				if (traveler == head) {
					newBlock.setNext(traveler);
					traveler.setPrevious(newBlock);
					head = newBlock;
					newBlock.setStatus(true);
					newBlock.setAddress(0);
					traveler.setAddress(newBlock.getSize());
					traveler.setSize(traveler.getSize() - newBlock.getSize());
					}
				else if (traveler == tail) {
					
				//traveler.setSize(traveler.getSize() - newBlock.getSize());
				}
				newBlock.setNext(traveler);
				newBlock.setPrevious(traveler.getPrevious());
				traveler.setPrevious(newBlock);
				newBlock.getPrevious().setNext(newBlock);
				traveler.setSize(traveler.getSize() - newBlock.getSize());
				newBlock.setStatus(true);
				newBlock.setAddress(newBlock.getPrevious().getAddress() + newBlock.getPrevious().getSize());
				traveler.setAddress(newBlock.getSize() + newBlock.getAddress());				
			}
				
		}
		return traveler.getAddress();
	}
	
	private void findLargestAvailable() {
		MemoryBlock traveler = head;
		for (int i = 1; i <= numBlocks; i++) {
			if(traveler.getSize() > largestAvailable.getSize())
				largestAvailable = traveler;
			if (traveler.getNext() != null)
				traveler = traveler.getNext();
		}
	}
	
	/**
	 * Check whether best fit or first fit is being used.
	 * @return True for best fit, false for first fit.
	 */
	
	public boolean getBestFit() {
		return bestFit;
	}
	
	/**
	 * Sets the memory allocation algorithm.
	 * @param bestFit True for best fit, false for first fit.
	 */
	
	public void setBestFit(boolean bestFit) {
		this.bestFit = bestFit;
	}
}
