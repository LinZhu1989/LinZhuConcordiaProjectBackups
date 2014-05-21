package utility;

import java.util.ArrayList;
import java.util.HashMap;

import Data.Item.Interface_Item;
import Data.Item.Item_Armor;
import Data.Item.Item_Weapons;

public class ItemResource {

	public enum ItemFilter {
		eAll,
		eItem,
		eArmor,
		eWeapon
	}

	protected static final String ICON_ARMOR1 = "./res/GUI/item/armor1.jpg";
	protected static final String ICON_ARMOR2 = "./res/GUI/item/armor2.jpg";
	protected static final String ICON_ARMOR3 = "./res/GUI/item/armor3.jpg";
	protected static final String ICON_ARMOR4 = "./res/GUI/item/armor4.jpg";
	protected static final String ICON_ARMOR5 = "./res/GUI/item/armor5.jpg";
	protected static final String ICON_ARMOR6 = "./res/GUI/item/armor6.jpg";
	protected static final String ICON_ARMOR7 = "./res/GUI/item/armor7.jpg";
	protected static final String ICON_ARMOR8 = "./res/GUI/item/armor8.jpg";
	protected static final String ICON_BELT1 = "./res/GUI/item/belt1.jpg";
	protected static final String ICON_BELT2 = "./res/GUI/item/belt2.jpg";
	protected static final String ICON_BELT3 = "./res/GUI/item/belt3.jpg";
	protected static final String ICON_BELT4 = "./res/GUI/item/belt4.jpg";
	protected static final String ICON_BELT5 = "./res/GUI/item/belt5.jpg";
	protected static final String ICON_BOOTS1 = "./res/GUI/item/boots1.jpg";
	protected static final String ICON_BOOTS2 = "./res/GUI/item/boots2.jpg";
	protected static final String ICON_BOOTS3 = "./res/GUI/item/boots3.jpg";
	protected static final String ICON_BOOTS4 = "./res/GUI/item/boots4.jpg";
	protected static final String ICON_BOOTS5 = "./res/GUI/item/boots5.jpg";
	protected static final String ICON_HELMET1 = "./res/GUI/item/Helmet1.jpg";
	protected static final String ICON_HELMET2 = "./res/GUI/item/Helmet2.jpg";
	protected static final String ICON_HELMET3 = "./res/GUI/item/Helmet3.jpg";
	protected static final String ICON_HELMET4 = "./res/GUI/item/Helmet4.jpg";
	protected static final String ICON_HELMET5 = "./res/GUI/item/Helmet5.jpg";
	protected static final String ICON_POTION1 = "./res/GUI/item/Potion1.jpg";
	protected static final String ICON_POTION2 = "./res/GUI/item/Potion2.jpg";
	protected static final String ICON_POTION3 = "./res/GUI/item/Potion3.jpg";
	protected static final String ICON_POTION4 = "./res/GUI/item/Potion4.jpg";
	protected static final String ICON_POTION5 = "./res/GUI/item/Potion5.jpg";
	protected static final String ICON_RING1 = "./res/GUI/item/Ring1.jpg";
	protected static final String ICON_RING2 = "./res/GUI/item/Ring2.jpg";
	protected static final String ICON_RING3 = "./res/GUI/item/Ring3.jpg";
	protected static final String ICON_RING4 = "./res/GUI/item/Ring4.jpg";
	protected static final String ICON_RING5 = "./res/GUI/item/Ring5.jpg";
	protected static final String ICON_WEAPONS1 = "./res/GUI/item/Weapons1.jpg";
	protected static final String ICON_WEAPONS2 = "./res/GUI/item/Weapons2.jpg";
	protected static final String ICON_WEAPONS3 = "./res/GUI/item/Weapons3.jpg";
	protected static final String ICON_WEAPONS4 = "./res/GUI/item/Weapons4.jpg";
	protected static final String ICON_WEAPONS5 = "./res/GUI/item/Weapons5.jpg";
	protected static final String ICON_BRACERS1 = "./res/GUI/item/bracers1.jpg";
	protected static final String ICON_BRACERS2 = "./res/GUI/item/bracers2.jpg";
	protected static final String ICON_BRACERS3 = "./res/GUI/item/bracers3.jpg";
	protected static final String ICON_BRACERS4 = "./res/GUI/item/bracers4.jpg";
	protected static final String ICON_BRACERS5 = "./res/GUI/item/bracers5.jpg";
	protected static final String ICON_GLOVES1 = "./res/GUI/item/gloves1.jpg";
	protected static final String ICON_GLOVES2 = "./res/GUI/item/gloves2.jpg";
	protected static final String ICON_GLOVES3 = "./res/GUI/item/gloves3.jpg";
	protected static final String ICON_GLOVES4 = "./res/GUI/item/gloves4.jpg";
	protected static final String ICON_GLOVES5 = "./res/GUI/item/gloves5.jpg";
	protected static final String ICON_SHIELD1 = "./res/GUI/item/shield1.jpg";
	protected static final String ICON_SHIELD2 = "./res/GUI/item/shield2.jpg";
	protected static final String ICON_SHIELD3 = "./res/GUI/item/shield3.jpg";
	protected static final String ICON_SHIELD4 = "./res/GUI/item/shield4.jpg";
	protected static final String ICON_SHIELD5 = "./res/GUI/item/shield5.jpg";

	/**
	 * this map is used to record the name-image pair.
	 * so that later on we can use it to get the image name 
	 * for the selected equipment.
	 * @return
	 */
	public static HashMap<String,String> getItemImageMap() {
		HashMap<String,String> aMap = new HashMap<String,String>();
		aMap.put("Armor1",ItemResource.ICON_ARMOR1);
		aMap.put("Armor2",ItemResource.ICON_ARMOR2);
		aMap.put("Armor3",ItemResource.ICON_ARMOR3);
		aMap.put("Armor4",ItemResource.ICON_ARMOR4);
		aMap.put("Armor5",ItemResource.ICON_ARMOR5);
		aMap.put("Armor6",ItemResource.ICON_ARMOR6);
		aMap.put("Armor7",ItemResource.ICON_ARMOR7);
		aMap.put("Armor8",ItemResource.ICON_ARMOR8);
		
		aMap.put("Belt1",ItemResource.ICON_BELT1);	
		aMap.put("Belt2",ItemResource.ICON_BELT2);
		aMap.put("Belt3",ItemResource.ICON_BELT3);
		aMap.put("Belt4",ItemResource.ICON_BELT4);
		aMap.put("Belt5",ItemResource.ICON_BELT5);		
		
		aMap.put("Boots1",ItemResource.ICON_BOOTS1);	
		aMap.put("Boots2",ItemResource.ICON_BOOTS2);
		aMap.put("Boots3",ItemResource.ICON_BOOTS3);
		aMap.put("Boots4",ItemResource.ICON_BOOTS4);
		aMap.put("Boots5",ItemResource.ICON_BOOTS5);				
		
		aMap.put("Helmet1",ItemResource.ICON_HELMET1);	
		aMap.put("Helmet2",ItemResource.ICON_HELMET2);
		aMap.put("Helmet3",ItemResource.ICON_HELMET3);
		aMap.put("Helmet4",ItemResource.ICON_HELMET4);
		aMap.put("Helmet5",ItemResource.ICON_HELMET5);			
		
		aMap.put("Potion1",ItemResource.ICON_POTION1);	
		aMap.put("Potion2",ItemResource.ICON_POTION2);
		aMap.put("Potion3",ItemResource.ICON_POTION3);
		aMap.put("Potion4",ItemResource.ICON_POTION4);
		aMap.put("Potion5",ItemResource.ICON_POTION5);
		
		aMap.put("Ring1",ItemResource.ICON_RING1);	
		aMap.put("Ring2",ItemResource.ICON_RING2);
		aMap.put("Ring3",ItemResource.ICON_RING3);
		aMap.put("Ring4",ItemResource.ICON_RING4);
		aMap.put("Ring5",ItemResource.ICON_RING5);
		
		aMap.put("Weapons1",ItemResource.ICON_WEAPONS1);	
		aMap.put("Weapons2",ItemResource.ICON_WEAPONS2);
		aMap.put("Weapons3",ItemResource.ICON_WEAPONS3);
		aMap.put("Weapons4",ItemResource.ICON_WEAPONS4);
		aMap.put("Weapons5",ItemResource.ICON_WEAPONS5);	
		
		aMap.put("Bracers1",ItemResource.ICON_BRACERS1);	
		aMap.put("Bracers2",ItemResource.ICON_BRACERS2);
		aMap.put("Bracers3",ItemResource.ICON_BRACERS3);
		aMap.put("Bracers4",ItemResource.ICON_BRACERS4);
		aMap.put("Bracers5",ItemResource.ICON_BRACERS5);	
		
		aMap.put("Gloves1",ItemResource.ICON_GLOVES1);	
		aMap.put("Gloves2",ItemResource.ICON_GLOVES2);
		aMap.put("Gloves3",ItemResource.ICON_GLOVES3);
		aMap.put("Gloves4",ItemResource.ICON_GLOVES4);
		aMap.put("Gloves5",ItemResource.ICON_GLOVES5);	
		
		aMap.put("Shield1",ItemResource.ICON_SHIELD1);	
		aMap.put("Shield2",ItemResource.ICON_SHIELD2);
		aMap.put("Shield3",ItemResource.ICON_SHIELD3);
		aMap.put("Shield4",ItemResource.ICON_SHIELD4);
		aMap.put("Shield5",ItemResource.ICON_SHIELD5);	
		
		return aMap;
	}

	public final int BUTTON_HEIGHT = 50;
	public final String TXT_NOTHING = "Nothing";
	public final String TXT_ALL = "All";
	public final String TXT_ITEM = "Item";
	public final String TXT_ARMOR = "Armor";
	public final String TXT_WEAPON = "Weapon";
	public final String TXT_SAVE = "Save";
	public final String TXT_QUIT = "Quit";
	public final String BUTTON_TEXT[] = {TXT_ALL,TXT_ITEM,TXT_ARMOR,TXT_WEAPON,TXT_SAVE,TXT_QUIT};
	public final int ITEM_WIDTH_GAP = 5;

	public ItemResource() {
		super();
	}

	public void refreshItemButtons(ArrayList<Interface_Item> items, ArrayList<ItemButton> buttons) {
		for (ItemButton btn : buttons) {
			btn.setItem(null);
		}
		
		for (int i = 0; i < items.size()  && i < buttons.size() ; ++i) {
			ItemButton btn = buttons.get(i);
			Interface_Item item = items.get(i);
			btn.setItem(item);
			String name = item.getItemName();
			btn.setText(name.substring(0, 2));
		}				
	}

	public ArrayList <Interface_Item> filterItems(ItemFilter eall, ArrayList<Interface_Item> sourceList) {
		ArrayList <Interface_Item> result = new ArrayList <Interface_Item>();
		switch (eall) {
		case eAll:
			return sourceList;
		case eArmor:
			for (Interface_Item item : sourceList) {
				if (item instanceof Item_Armor) {
					result.add(item);
				}
			}
			break;
		case eItem:
			for (Interface_Item item : sourceList) {
				if (item instanceof Item_Armor == false && 
					item instanceof Item_Weapons == false) {
					result.add(item);
				}
			}
			break;			
		case eWeapon:
			for (Interface_Item item : sourceList) {
				if (item instanceof Item_Weapons) {
					result.add(item);
				}
			}
			break;			
		}		
		return result;		
	}

}