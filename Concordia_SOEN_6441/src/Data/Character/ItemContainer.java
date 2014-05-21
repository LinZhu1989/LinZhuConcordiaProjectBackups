/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Character;

import java.util.ArrayList;

import Data.GameData.CommonData;
import Data.Generator.ItemGenerator;
import Data.Item.Interface_Item;

/**
 * the class Container_Item handles a back pack for a Game Character
 * 
 * @author Lin Zhu
 * 
 */
public class ItemContainer {

	private int maxWeight;
	private int maxNumber;
	private ArrayList<Interface_Item> items;
	ItemGenerator itemGenerator = new ItemGenerator();

	/**
	 * Class BackPack Constructor (With fno argument)
	 */
	public ItemContainer() {
		this.items = new ArrayList<Interface_Item>();
		this.maxWeight = CommonData.DEFAULTBACKPACKWEIGHT;
		this.maxNumber = CommonData.DEFAULTBACKPACKNUMBER;
		this.initialItems();
	}
	
	/**
	 * Class BackPack Constructor (With  argument)
	 */
	public ItemContainer(String type) {
		this.items = new ArrayList<Interface_Item>();
		this.maxWeight = CommonData.DEFAULTBACKPACKWEIGHT;
		this.maxNumber = CommonData.DEFAULTBACKPACKNUMBER;
	}

	/**
	 * initial items
	 */
	private void initialItems() {
		items.add(this.itemGenerator.Item_ArmorRandomOneGenerator());
		items.add(this.itemGenerator.Item_BeltRandomOneGenerator());
		items.add(this.itemGenerator.Item_BootsRandomOneGenerator());
		items.add(this.itemGenerator.Item_HelmetRandomOneGenerator());
		items.add(this.itemGenerator.Item_PotionRandomOneGenerator());
		items.add(this.itemGenerator.Item_RingRandomOneGenerator());
		//items.add(this.itemGenerator.Item_WeaponsRandomOneGenerator());
		items.add(this.itemGenerator.Item_ShieldRandomOneGenerator());
		items.add(this.itemGenerator.Item_BracersRandomOneGenerator());
		items.add(this.itemGenerator.Item_GlovesRandomOneGenerator());
		items.addAll(this.itemGenerator.Item_WeaponsAllTypes());
	}

	/**
	 * add one item
	 * 
	 * @param newItem
	 * @return
	 */
	public boolean addItem(Interface_Item newItem) {

		if (checkTotalWeight(newItem) && checkTotalNumber()) {
			items.add(newItem);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * drop an item with an item ID
	 * 
	 * @param itemNum
	 */
	public void dropItem(int itemNum) {
		items.remove(itemNum);
	}

	/**
	 * get an item with an item ID
	 * 
	 * @param item
	 * @return
	 */
	public int getItemNum(Interface_Item item) {

		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getItemName()
					.equalsIgnoreCase(item.getItemName())) {
				return i;
			}
		}

		return -1;

	}

	/**
	 * get a arrayList of the items
	 * 
	 * @return
	 */
	public ArrayList<Interface_Item> getItems() {
		return items;
	}

	/**
	 * print out the information of all the items
	 */
	public void showItemList() {
		if (items.size() == 0) {
			System.out.println("No item in backpack!");
		} else {
			for (int i = 0; i < items.size(); i++) {
				System.out.println("No." + i + items.get(i).getItemName()
						+ "\t\t$ " + items.get(i).getItemPrice()
						+ "\t\tWeight: " + items.get(i).getItemWeight());
			}
		}

	}

	/**
	 * get the total weight of the items
	 * 
	 * @return
	 */
	public int getTotalWeight() {
		int totalWeight = 0;
		for (int i = 0; i < items.size(); i++) {
			totalWeight += items.get(i).getItemWeight();
		}
		return totalWeight;
	}

	/**
	 * get the total number of the items
	 * 
	 * @return
	 */
	public int getTotalNumber() {
		return items.size();
	}

	/**
	 * check the total weight of the items
	 * 
	 * @param newItem
	 * @return
	 */
	public boolean checkTotalWeight(Interface_Item newItem) {
		int totalWeight = 0;
		for (int i = 0; i < items.size(); i++) {
			totalWeight += items.get(i).getItemWeight();
		}
		if (totalWeight + newItem.getItemWeight() > maxWeight) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * check the total number of the items
	 * 
	 * @return
	 */
	public boolean checkTotalNumber() {
		if (items.size() + 1 > maxNumber) {
			return false;
		} else {
			return true;
		}
	}

}
