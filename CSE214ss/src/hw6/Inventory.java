package hw6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Inventory implements Serializable {
	
	private Hashtable<String, Integer> quantities;
	private Hashtable<String, String> itemToLocation;
	private Hashtable<String, ArrayList<String>> locationToItem;
	
	/**
	 * Constructor. Creates three hashtables for storing inventory data.
	 */
	
	public Inventory() {
		quantities = new Hashtable<String, Integer>();
		itemToLocation = new Hashtable<String, String>();
		locationToItem = new Hashtable<String, ArrayList<String>>();
	}
	
	/**
	 * Add a new item to the inventory.
	 * @param item Name of item to add.
	 * @param location Location to add new item to.
	 * @param quantity Quantity of new item.
	 * @throws ItemInStoreException Item is already in inventory.
	 */
	
	public void newItem(String item, String location, int quantity) throws ItemInStoreException {
		if (quantities.containsKey(item))
			throw new ItemInStoreException("Item already exists in store.");
		quantities.put(item, quantity);
		itemToLocation.put(item, location);
		
		ArrayList<String> tempAL = new ArrayList<String>();
		tempAL.add(item);
		locationToItem.put(location, tempAL);
		System.out.println(quantity + "x " + item + " added to " + location + ".");
	}
	
	/**
	 * Completely remove an item from the inventory.
	 * @param item Item to remove.
	 * @throws ItemNotInStoreException Item is not in inventory.
	 */
	
	public void removeItem(String item) throws ItemNotInStoreException {
		if (!quantities.containsKey(item))
			throw new ItemNotInStoreException("Item does not exist in store.");
		quantities.remove(item);
		itemToLocation.remove(item);
		
		boolean removed = false;
		Enumeration<ArrayList<String>> tempEnum = locationToItem.elements();
		ArrayList<String> tempAL = tempEnum.nextElement();
		while (removed == false) {
			if (tempAL.contains(item)) {
				tempAL.remove(item);
				removed = true;
			}
			else tempAL = tempEnum.nextElement();
		}
		System.out.println(item + " removed from inventory.");			
	}
	
	/**
	 * Change the location of an item.
	 * @param item Item to change location of.
	 * @param newLocation New location of the item.
	 * @throws ItemNotInStoreException Item is not in inventory.
	 */
	
	public void moveItem(String item, String newLocation) throws ItemNotInStoreException {
		if (!quantities.containsKey(item))
			throw new ItemNotInStoreException("Item does not exist in store.");
		itemToLocation.remove(item);
		itemToLocation.put(item, newLocation);
		
		boolean removed = false;
		Enumeration<ArrayList<String>> tempEnum = locationToItem.elements();
		ArrayList<String> tempAL = tempEnum.nextElement();
		while (removed == false) {
			if (tempAL.contains(item)) {
				tempAL.remove(item);
				removed = true;
			}
			else tempAL = tempEnum.nextElement();
		}
		if (locationToItem.containsKey(newLocation)) {
			tempAL = locationToItem.get(newLocation);
			tempAL.add(item);
		}
		else {
			tempAL = new ArrayList<String>();
			tempAL.add(item);
			locationToItem.put(newLocation, tempAL);
		}
		System.out.println(item + " moved to " + newLocation + ".");
	}
	
	public void updateQuantity
	
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		try {
			inventory.newItem("niggadoodles", "BKR", 12);
			inventory.newItem("niggas", "hood", 10);
			
		} catch (ItemInStoreException e) {
			System.out.println(e.getMessage());
		}
		try {
			inventory.removeItem("niggas");
			inventory.moveItem("niggadoodles", "hood");
		} catch (ItemNotInStoreException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
