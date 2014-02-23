package hw2;

public class MemoryBlock {
	private int address;
	private int size;
	private boolean status;			//true: allocated.
	private MemoryBlock next;
	private MemoryBlock previous;
	
	/**
	 * Default constructor for a MemoryBlock. Address and size are initialized to 0. Status is false.
	 */
	
	public MemoryBlock() {
		address = 0;
		size = 0;
		status = false;
		next = null;
		previous = null;
	}
	
	/**
	 * Constructor for a MemoryBlock.
	 * @param address Address of MemoryBlock.
	 * @param size Size of MemoryBlock.
	 */
	
	public MemoryBlock(int size) {
		address = 0;
		this.size = size;
		status = false;
		next = null;
		previous = null;
	}
	
	/**
	 * Gets the MemoryBlock's address.
	 * @return Address of MemoryBlock.
	 */
	
	public int getAddress() {
		return address;
	}
	
	/**
	 * Sets the MemoryBlock's address.
	 * @param address Address of MemoryBlock.
	 */
	
	public void setAddress(int address) {
		this.address = address;
	}
	
	/**
	 * Gets the size of the MemoryBlock.
	 * @return Size of the MemoryBlock.
	 */
	
	public int getSize() { 
		return size;
	}
	
	/**
	 * Sets the MemoryBlock's size.
	 * @param size Size of the MemoryBlock.
	 */
	
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Gets the MemoryBlock's status of being allocated/unallocated. True: allocated. False: unallocated.
	 * @return Whether or not the MemoryBlock is allocated.
	 */
	
	public boolean getStatus() {
		return status;
	}
	
	/**
	 * Sets the status of the MemoryBlock.
	 * @param status Whether or not the MemoryBlock is allocated.
	 */
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Gets next MemoryBlock.
	 * @return Next MemoryBlock.
	 */
	
	public MemoryBlock getNext() {
		return next;
	}
	
	/**
	 * Sets next MemoryBlock.
	 * @param next Next MemoryBlock.
	 */
	
	public void setNext(MemoryBlock next) {
		this.next = next;
	}
	
	/**
	 * Gets previous MemoryBlock.
	 * @return Previous MemoryBlock.
	 */
	
	public MemoryBlock getPrevious() {
		return previous;
	}
	
	/**
	 * Sets previous MemoryBlock.
	 * @param previous Previous MemoryBlock.
	 */
	
	public void setPrevious(MemoryBlock previous) {
		this.previous = previous;
	}
}
