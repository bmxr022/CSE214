/*
 * Zach Samuels
 * 108941490
 * Zachary.Samuels@stonybrook.edu
 * HW #2
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw2;

import java.lang.Exception;
//import java.awt.SecondaryLoop;

/**
 * Simulation of memory.
 * @author Zach Samuels
 *
 */

public class MainMemory {
	private MemoryBlock head;
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
	
	/**
	 * Allocates memory using the algorithm specified by the status variable.
	 * @param size Size of MemoryBlock to be allocated.
	 * @return Address of allocated MemoryBlock.
	 * @throws OutOfMemoryException No more memory to allocate.
	 * @throws SuboptimallyAllocatedMemoryException Enough free memory, but no free block that is big enough.
	 * @throws TooSmallException Size of memory to allocate is too small (must be >= 1).
	 */
	
	public int malloc(int size) throws OutOfMemoryException, SuboptimallyAllocatedMemoryException, TooSmallException {
		if (size > availableMemory)
			throw new OutOfMemoryException("Out of memory.");
		if (size > largestAvailable.getSize())
			throw new SuboptimallyAllocatedMemoryException("Memory suboptimally allocated.");
		if (size < 1)
			throw new TooSmallException("Size must be greater than 1 to allocate.");
		if (bestFit)
			return mallocBestFit(size);
		else return mallocFirstFit(size);
		
	}
	
	/**
	 * Allocates MemoryBlock using best fit algorithm.
	 * @param size Size of MemoryBlock to be allocated
	 * @return Address of allocated MemoryBlock.
	 */
	
	private int mallocBestFit(int size) {
		MemoryBlock candidate = head;
		MemoryBlock traveler = head;
		availableMemory -= size;										
		allocatedMemory += size;
		
		for (int i = 1; i <= numBlocks; i++) {
			if ((traveler.getSize() == size) && (traveler.getStatus() == false)) {
				traveler.setStatus(true);
				if (largestAvailable == traveler)
					findLargestAvailable();
				return traveler.getAddress();
			}
			if ((traveler.getSize() > size) && (traveler.getStatus() == false)) {
				if (candidate.getStatus()) {
					candidate = traveler;
				}
				if (traveler.getSize() < candidate.getSize())
					candidate = traveler;
			}
			traveler = traveler.getNext();
		}
		
		MemoryBlock newBlock = new MemoryBlock(size);
		numBlocks++;
		newBlock.setNext(candidate);
		
		if (candidate == head) {
			candidate.setPrevious(newBlock);
			candidate.setSize(candidate.getSize() - size);
			candidate.setAddress(size);
			head = newBlock;
			head.setStatus(true);
			if (largestAvailable == candidate)
				findLargestAvailable();
			return newBlock.getAddress();
		}
		
		newBlock.setPrevious(candidate.getPrevious());
		candidate.setPrevious(newBlock);
		newBlock.getPrevious().setNext(newBlock);
		candidate.setSize(candidate.getSize() - size);
		newBlock.setAddress(newBlock.getPrevious().getAddress() + newBlock.getPrevious().getSize());
		candidate.setAddress(newBlock.getAddress() + size);
		newBlock.setStatus(true);
		if (largestAvailable == candidate)
			findLargestAvailable();
		return newBlock.getAddress();
		
		
	}
	
	/**
	 * Allocates MemoryBlock using first fit algorithm.
	 * @param size Size of MemoryBlock to be allocated.
	 * @return Address of allocated MemoryBlock.
	 */
	
	private int mallocFirstFit(int size) {
		MemoryBlock traveler = head;
		for (int i = 1; i <= numBlocks; i++) {
			if ((traveler.getSize() == size) && (traveler.getStatus() == false)) {
				traveler.setStatus(true);
				availableMemory -= size;										
				allocatedMemory += size;
				if (largestAvailable == traveler)
					findLargestAvailable();
				return traveler.getAddress();
			}
			if ((traveler.getSize() > size) && (traveler.getStatus() == false)) {
				MemoryBlock newBlock = new MemoryBlock(size);
				numBlocks++;
				availableMemory -= size;
				allocatedMemory =+ size;
				if (traveler == head) {
					newBlock.setNext(traveler);
					traveler.setPrevious(newBlock);
					head = newBlock;
					newBlock.setStatus(true);
					newBlock.setAddress(0);
					traveler.setAddress(newBlock.getSize());
					traveler.setSize(traveler.getSize() - newBlock.getSize());
					if (largestAvailable == traveler)
						findLargestAvailable();
					return traveler.getAddress();
					}
				newBlock.setNext(traveler);
				newBlock.setPrevious(traveler.getPrevious());
				traveler.setPrevious(newBlock);
				newBlock.getPrevious().setNext(newBlock);
				traveler.setSize(traveler.getSize() - newBlock.getSize());
				newBlock.setStatus(true);
				newBlock.setAddress(newBlock.getPrevious().getAddress() + newBlock.getPrevious().getSize());
				traveler.setAddress(newBlock.getSize() + newBlock.getAddress());
				if (largestAvailable == traveler)
					findLargestAvailable();
				return traveler.getAddress();
			}
			traveler = traveler.getNext();	
		}
		System.out.println("this shouldn't happen.");
		return traveler.getAddress();
	}
	
	/**
	 * Traverses the array and sets the largestAvailable variable to largest available MemoryBlock. 
	 */
	
	private void findLargestAvailable() {
		MemoryBlock traveler = head;
		for (int i = 1; i <= numBlocks; i++) {
			if((traveler.getSize() > largestAvailable.getSize()) && (traveler.getStatus() == false))
				largestAvailable = traveler;
			if (traveler.getNext() != null)
				traveler = traveler.getNext();
		}
	}
	
	/**
	 * Frees the MemoryBlock at the passed address and merges it with any neighboring, free MemoryBlocks.
	 * @param address Address of MemoryBlock to be freed.
	 * @throws AlreadyFreeException MemoryBlock is already free.
	 * @throws BlockNotFoundException No MemoryBlock found starting at that address.
	 */
	
	public void free(int address) throws AlreadyFreeException, BlockNotFoundException {
		MemoryBlock traveler = head;
		for (int i = 1; i <= numBlocks; i++) {
			if (traveler.getAddress() == address) {
				if (traveler.getStatus() == false)
					throw new AlreadyFreeException("That block is already free.");
				traveler.setStatus(false);
				availableMemory += traveler.getSize();
				if ((traveler == head) && (traveler == tail)) {
					traveler.setStatus(false);
					break;
				}
				if (traveler == head) {
					if (traveler.getNext() == tail) {
						if (traveler.getNext().getStatus() == false) {
							traveler.setSize(traveler.getNext().getSize() + traveler.getSize());
							traveler.setNext(null);
							tail = traveler;
							numBlocks--;
							break;
						}
					}
					if (traveler.getNext().getStatus() == false) {
						traveler.setSize(traveler.getNext().getSize() + traveler.getSize());
						traveler.setNext(traveler.getNext().getNext());
						traveler.getNext().getNext().setPrevious(traveler);
						numBlocks--;
						break;
					}
					break;
				}
				if (traveler == tail) {
					if (traveler.getPrevious() == head) {
						if (traveler.getPrevious().getStatus() == false) {
							head.setSize(head.getSize() + traveler.getSize());
							head.setNext(null);
							tail = head;
							numBlocks--;
							break;
						}
					}
					if (traveler.getPrevious().getStatus() == false) {
						traveler.setSize(traveler.getSize() + traveler.getPrevious().getSize());
						traveler.setAddress(traveler.getPrevious().getAddress());
						traveler.setPrevious(traveler.getPrevious().getPrevious());
						traveler.getPrevious().getPrevious().setNext(traveler);
						numBlocks--;
						break;
					}
					break;
				}
				if (traveler.getPrevious().getStatus() == false) {
					if (traveler.getPrevious() == head) {
						head.setSize(head.getSize() + traveler.getSize());
						head.setNext(traveler.getNext());
						traveler.getNext().setPrevious(head);
						numBlocks--;	
						traveler = head;
					}
					else {
						traveler.setSize(traveler.getSize() + traveler.getPrevious().getSize());
						traveler.setAddress(traveler.getPrevious().getAddress());
						traveler.getPrevious().getPrevious().setNext(traveler);
						traveler.setPrevious(traveler.getPrevious().getPrevious());
						numBlocks--;
						//break;
					}
				}
				if (traveler.getNext().getStatus() == false) {
					if (traveler.getNext() == tail) {
						traveler.setSize(tail.getSize() + traveler.getSize());
						tail = traveler;
						traveler.setNext(null);
						numBlocks--;
						break;
					}
					else {
						traveler.setSize(traveler.getSize() + traveler.getNext().getSize());
						traveler.setNext(traveler.getNext().getNext());
						traveler.getNext().setPrevious(traveler);
						numBlocks--;
						break;
					}
				}
				
			}
			if ((traveler.getNext() == null) && (traveler.getAddress() != address)) {
				throw new BlockNotFoundException("No MemoryBlock found at that address."); 
			}
			traveler = traveler.getNext();
		}
		findLargestAvailable();
	}
	
	/**
	 * Adds free memory to the last MemoryBlock.
	 * @param size Size of free MemoryBlock to be added.
	 */
	
	public void addMemory(int size) {
		availableMemory += size;
		if (tail.getStatus() == false) {
			tail.setSize(tail.getSize() + size);
		}
		else {
			MemoryBlock newBlock = new MemoryBlock(size);
			numBlocks++;
			newBlock.setPrevious(tail);
			tail.setNext(newBlock);
			newBlock.setAddress(tail.getAddress() + tail.getSize());
			tail = newBlock;
		}
		findLargestAvailable();
	}
	
	/**
	 * Sets the allocation algorithm to either best fit or first fit.
	 * @param algo Algorithm to use.
	 */
	
	public void setAllocationAlgorithm(String algo) throws IllegalArgumentException {
		if (algo.toLowerCase().equals("best fit"))
			bestFit = true;
		else if (algo.toLowerCase().equals("first fit"))
			bestFit = false;
		else throw new IllegalArgumentException("Enter \"best fit\" or \"first fit\".");
		
	}
	
	/**
	 * Checks whether best fit or first fit is being used.
	 * @return True for best fit, false for first fit.
	 */
	
	public boolean getBestFit() {
		return bestFit;
	}
	
	/**
	 * Get the largest available MemoryBlock.
	 * @return Largest available MemoryBlock.
	 */
	
	public MemoryBlock getLargestAvailable() {
		return largestAvailable;
	}
	
	/**
	 * Returns the size of the total available memory.
	 * @return Size of the total available memory.
	 */
	
	public int getAvailableMemory() {
		return availableMemory;
	}
	
	/**
	 * Returns the size of the total allocated memory.
	 * @return Size of the total allocated memory.
	 */
	
	public int getAllocatedMemory() {
		return allocatedMemory;
	}
	
	/**
	 * Returns a String representation of all MemoryBlocks.
	 * @return All MemoryBlocks as a single String.
	 */
	
	public String toString() {
		MemoryBlock traveler = head;
		String str = String.format("%-12s %-16s %-8s\n", "Address:", "Status:", "Size:");
		str += "------------------------------------\n";
		for (int i = 1; i <= numBlocks; i++) {
			str += traveler.toString() + "\n";
			traveler = traveler.getNext();
		}
		str += "------------------------------------\n";
		return str;
	}
}
