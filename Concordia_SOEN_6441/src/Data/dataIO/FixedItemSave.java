/**
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.dataIO;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import Data.Character.GameFixedItem;
import Data.Character.GameMonster;
import Data.Item.Interface_Item;


/**
 * this is the class to save the fixed item , either chest or merchant
 * @author xinshao
 *
 */
public class FixedItemSave {
	public final static String FIELD_FIXED_ITEM ="fixedItem";
	public final static String FIELD_FIXED_ITEM_TYPE = "fixedItemType";
	public final static String FIELD_FIXED_ITEMS = "fixedItems";
	/**
	 * this is the main function of save fixed item
	 * @param doc
	 * @param elementNode
	 * @param fixedItem
	 * @return
	 */
	public static boolean saveFixedItem(Document doc, Node elementNode,
			GameFixedItem fixedItem){
		
		Element rootElement = doc.createElement(FixedItemSave.FIELD_FIXED_ITEM);
		elementNode.appendChild(rootElement);
		
		Element typeElement = doc.createElement(FixedItemSave.FIELD_FIXED_ITEM_TYPE);
	    typeElement.setTextContent(fixedItem.getFixedItemType().toString());	
	    rootElement.appendChild(typeElement);
	    
	    Element itemsElement = doc.createElement(FixedItemSave.FIELD_FIXED_ITEMS);
	    rootElement.appendChild(itemsElement);
	    

		ArrayList<Interface_Item> items = fixedItem.getFixedItem();
		for (int i = 0; i < items.size(); i++) {
			Interface_Item currentItem = items.get(i);
			CharacterSave.saveItem(doc, itemsElement, currentItem);
		}
		return false;
		
	}

}
