/**
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.dataIO;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utility.FixedItemType;
import utility.IMovable;
import Data.Character.GameCharacter;
import Data.Character.GameFixedItem;
import Data.Factory.ItemFactory;
import GUI.mapView.MapBlock;
import GUI.mapView.MapFixedItemFactory;
import GUI.mapView.MapFixedItemFactory.MapFixedItem;

/**
 * this is the class to load fixed item , either a chest or a merchant
 * 
 * @author xinshao
 * 
 */
public class FixedItemLoad {

	public IMovable loadFixedItem(NodeList itemList, MapBlock blk) {

		return null;
	}

	/**
	 * this is the method allow user to load item in a list of items
	 * 
	 * @param merchantOrChest
	 * @param itemList
	 */
	private void loadItem(GameFixedItem merchantOrChest, NodeList itemList) {

		String itemName = " ";
		int itemWeight = 0;
		int itemPrice = 0;
		ItemFactory itemFc = null;
		String equipmentType = " ";
		String itemType = " ";
		int[] fixData = new int[15];
	
		for (int j = 0; j < itemList.getLength(); j++) {
			Node itemChildren = itemList.item(j);
			 //the itemChildren should be item
			NodeList childrenOfItemList = itemChildren.getChildNodes();

			for(int k = 0 ; k< childrenOfItemList.getLength();k++ ){
				Node childrenOfItem = childrenOfItemList.item(k);
			if (childrenOfItem instanceof Element) {

				if (((Element) childrenOfItem).getTagName().compareToIgnoreCase(
						CharacterSave.FIELD_EQUIPMENT_TYPE) == 0) {
					itemFc = new ItemFactory(childrenOfItem.getTextContent());
					System.out.println("the equipment type is "+childrenOfItem.getTextContent());

				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_TYPE) == 0) {

					itemType = childrenOfItem.getTextContent();

				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_NAME) == 0) {
					itemName = childrenOfItem.getTextContent();
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_WEIGHT) == 0) {
					itemWeight = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_PRICE) == 0) {
					itemPrice = (Integer
							.parseInt(childrenOfItem.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_ATK) == 0) {
					fixData[0] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_PHYSIC_ALARMOR) == 0) {
					fixData[1] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_MAGIC_RESIST) == 0) {
					fixData[2] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_ADATTACK_DISTANCE) == 0) {
					fixData[3] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_MOVE_DISTANCE) == 0) {
					fixData[4] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_STRENGTH) == 0) {
					fixData[5] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_DEXTERITY) == 0) {
					fixData[6] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CONSTITUTION) == 0) {
					fixData[7] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_WISDOM) == 0) {
					fixData[8] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_INTELLIGENCE) == 0) {
					fixData[9] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CHARISMA) == 0) {
					fixData[10] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CURRENT_HP) == 0) {
					fixData[11] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CURRENT_MP) == 0) {
					fixData[12] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_MAX_HP) == 0) {
					fixData[13] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
				} else if (((Element) childrenOfItem).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_MAX_MP) == 0) {
					fixData[14] = (Integer.parseInt(childrenOfItem
							.getTextContent()));
					merchantOrChest.getFixedItem().add(
							itemFc.getItem(itemType, itemName, itemWeight,
									itemPrice, fixData));
				}

			}
		}
		}
	}

	/**
	 * load fixed item
	 * @param children
	 * @param blk
	 */
	public void loadFixedItem(Element children, MapBlock blk) {
		FixedItemType fType = FixedItemType.CHEST;

		NodeList firstList = children.getChildNodes();
		for (int i = 0; i < firstList.getLength(); ++i) {
			Node firstChildren = firstList.item(i);
			// firstChildren is fixedItemType or fixedItems
			if (firstChildren instanceof Element) {
				if (firstChildren.getNodeName().compareToIgnoreCase(
						FixedItemSave.FIELD_FIXED_ITEM_TYPE) == 0) {
					// loadInfo(role, firstChildren);
					System.out.println("the fixedItemType is "
							+ firstChildren.getTextContent());
					fType = FixedItemType.valueOf(firstChildren
							.getTextContent());
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						FixedItemSave.FIELD_FIXED_ITEMS) == 0) {
					NodeList itemList = firstChildren.getChildNodes();
					MapFixedItemFactory mapFixedItemFactory = new MapFixedItemFactory();
					MapFixedItem fitem = (MapFixedItem) mapFixedItemFactory.create(fType, blk);

					GameFixedItem gItem = fitem.getGameFixedItem();
					gItem.getFixedItem().clear();
					loadItem(gItem, itemList);
					blk.setFixedItem(gItem);
				}
			}
		}
	}

}
