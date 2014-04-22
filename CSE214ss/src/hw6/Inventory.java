/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #6
 * CSE214
 * R05 - Vyassa Baratham
 */
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
		
		if (!locationToItem.containsKey(location)) {
			ArrayList<String> tempAL = new ArrayList<String>();
			tempAL.add(item);
			locationToItem.put(location, tempAL);
		}
		else locationToItem.get(location).add(item);
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
			else if (tempEnum.hasMoreElements())
				tempAL = tempEnum.nextElement();
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
	
	/**
	 * Changes quantity of an item already in inventory.
	 * @param item Item to change quantity of.
	 * @param newQuantity New quantity of item.
	 * @throws ItemNotInStoreException Item not in inventory.
	 */
	
	public void updateQuantity(String item, int newQuantity) throws ItemNotInStoreException {
		if (!quantities.containsKey(item))
			throw new ItemNotInStoreException("Item does not exist in store.");
		quantities.remove(item);
		quantities.put(item, newQuantity);
	}
	
	/**
	 * Check if item is in inventory.
	 * @param item Item to check.
	 * @return True if item is in inventory.
	 */
	
	public boolean hasItem(String item) {
		return quantities.containsKey(item);
	}
	
	/**
	 * Get location of the specified item in inventory.
	 * @param item Item to find location of.
	 * @return Location of the item.
	 * @throws ItemNotInStoreException Item not in inventory.
	 */
	
	public String locationOf(String item) throws ItemNotInStoreException {
		if (!quantities.containsKey(item))
			throw new ItemNotInStoreException("Item does not exist in store.");
		return itemToLocation.get(item);
	}
	
	/**
	 * Get quantity of specified item in inventory.
	 * @param item Item to find quantity of.
	 * @return Quantity of items.
	 * @throws ItemNotInStoreException Item not in inventory.
	 */
	
	public int quantityOf(String item) throws ItemNotInStoreException {
		if (!quantities.containsKey(item))
			throw new ItemNotInStoreException("Item does not exist in store.");
		return quantities.get(item);
	}
	
	/**
	 * Get list of items at specified location in inventory.
	 * @param location Location of items.
	 * @return ArrayList of all items at specified location.
	 * @throws LocationNotInStoreException Location not in inventory.
	 */
	
	public ArrayList<String> itemsAt(String location) throws LocationNotInStoreException {
		if (!locationToItem.containsKey(location))
			throw new LocationNotInStoreException("Location does not exist in store.");
		return locationToItem.get(location);
	}
	
	/**
	 * Get the item with the maximum stock at the given location.
	 * @param location Location to look for maximum stocked item.
	 * @return Item with the most stock at the given location.
	 * @throws LocationNotInStoreException Location not in the store.
	 * @throws EmptyLocationException No items at that location.
	 */
	
	public String maxStockAt(String location) throws LocationNotInStoreException, EmptyLocationException {
		if (!locationToItem.containsKey(location))
			throw new LocationNotInStoreException("Location does not exist in store.");
		int max = 0;
		int quantity = 0;
		String maxItem = "";
		for (String item : locationToItem.get(location)) {
			quantity = quantities.get(item);
			if (quantity > max) {
				max = quantity;
				maxItem = item;
			}
		}
		return maxItem;
	}
	
	/**
	 * Prints entire inventory.
	 */
	
	public void printAllItems() {
		System.out.println("Entire inventory: ");	
		System.out.println(locationToItem.toString());
	}
}
