package Data.Character;

import java.util.ArrayList;

import utility.FixedItemType;
import Data.Builder.DiceTool;
import Data.Generator.ItemGenerator;
import Data.Item.Interface_Item;
import Data.Item.Item_Armor;
import Data.Item.Item_Shield;
import Data.Item.Item_Weapons;
import GUI.battleView.BattleLog;

public class GameFixedItem {

	private ArrayList<Interface_Item> items;
	ItemGenerator itemGenerator = new ItemGenerator();
	private FixedItemType type;
	
	public void addItem(Interface_Item item) {
		if (items.contains(item)) {
			assert(false);
			return;
		}		
		items.add(item);
	}
	
	/**
	 * remove the item from the container
	 * @param item
	 * @return
	 */
	public boolean removeItem(Interface_Item item) {
		if (item == null)
			return false;
		
		if (items.contains(item)) {
			return items.remove(item); 
		}		
		return false;
	}
	
	public String toString() {
		String result = "";
		
		for (Interface_Item i : items) {
			result += "*****";
			if (i instanceof Item_Shield) {
				Item_Shield shield = (Item_Shield) i;
				result += shield.getType();					
			} else if (i instanceof Item_Weapons) {
				Item_Weapons weapon = (Item_Weapons) i;
				result += weapon.getType();
			} else if (i instanceof Item_Armor){
				Item_Armor armor = (Item_Armor) i;
				result += armor.getType();				
			} else {
				result += "*****";
			}
			result += ":";
			result += i.getItemName();
			result += "\n";
		}
		
		return result;
	}
	
	public FixedItemType getFixedItemType(){
		return this.type;
	}

	public GameFixedItem(FixedItemType itemType) {
		this.items = new ArrayList<Interface_Item>(1);
		this.type = itemType;
		this.initialItems();
	}

	
	public void updateItems(int i, BattleLog bl) {
		if(this.type.equals(FixedItemType.CHEST)){
			bl.setLogInfor("Updating CHEST...");
			//this.initialItems();
			int aLevel=adjustArmorLevel(i);
			int sLevel=adjustShieldLevel(i);
			bl.setLogInfor(this.items.size()+" items already in the chest.");
			bl.setLogInfor("Add 2 Item Armor with level"+aLevel);
			bl.setLogInfor("Add 2 Item Shiel with level"+sLevel);
			bl.setLogInfor("Updated Items in CHEST with Fighter Level"+i);
			items.add(this.itemGenerator.Item_ArmorGeneratorWithLevel(aLevel));
			items.add(this.itemGenerator.Item_ShieldGeneratorWithLevel(sLevel));
			items.add(this.itemGenerator.Item_ArmorGeneratorWithLevel(aLevel));
			items.add(this.itemGenerator.Item_ShieldGeneratorWithLevel(sLevel));
			
		}else{
			bl.setLogInfor("Updating MERCHANT...");
			bl.setLogInfor("Updated Items in MERCHANT with Fighter Level"+i);
			this.initialItems();
		}
		
		
	}
	private int adjustArmorLevel(int a){
		if(a<=7){
			return a;
		}else{
			return 8;
		}
	}
	
	private int adjustShieldLevel(int s){
		if(s<=2){
			return s;
		}else{
			return 3;
		}
	}

	/**
	 * initial items
	 */
	private void initialItems() {
		items.clear();
		if (this.type.equals(FixedItemType.CHEST)) {
			for (int i = 0; i < 3; i++) {
				DiceTool dice3 = new DiceTool(10);
				switch (dice3.getValue()) {
				case 1:
					items.add(this.itemGenerator.Item_ArmorRandomOneGenerator());
					break;
				case 2:
					items.add(this.itemGenerator.Item_BeltRandomOneGenerator());
					break;
				case 3:
					items.add(this.itemGenerator.Item_BootsRandomOneGenerator());
					break;
				case 4:
					items.add(this.itemGenerator
							.Item_HelmetRandomOneGenerator());
					break;
				case 5:
					items.add(this.itemGenerator
							.Item_PotionRandomOneGenerator());
					break;
				case 6:
					items.add(this.itemGenerator.Item_RingRandomOneGenerator());
					break;
				case 7:
					items.add(this.itemGenerator
							.Item_WeaponsRandomOneGenerator());
					break;
				case 8:
					items.add(this.itemGenerator
							.Item_ShieldRandomOneGenerator());
					break;
				case 9:
					items.add(this.itemGenerator
							.Item_BracersRandomOneGenerator());
					break;
				case 10:
					items.add(this.itemGenerator
							.Item_GlovesRandomOneGenerator());
					break;
				default:
					items.add(this.itemGenerator.Item_ArmorRandomOneGenerator());
					break;
				}
			}

		} else {

			items.addAll(this.itemGenerator.Item_ArmorALLGenerator());
			items.addAll(this.itemGenerator.Item_ShieldAllGenerator());
			items.addAll(this.itemGenerator.Item_WeaponsALLGenerator());
			
			items.addAll(this.itemGenerator
					.Item_BeltGeneratorWithNumberRequst(5));
			items.addAll(this.itemGenerator
					.Item_BootsGeneratorWithNumberRequst(5));
			items.addAll(this.itemGenerator
					.Item_HelmetGeneratorWithNumberRequst(5));
			items.addAll(this.itemGenerator
					.Item_PotionGeneratorWithNumberRequst(5));
			items.addAll(this.itemGenerator
					.Item_RingGeneratorWithNumberRequst(5));
			items.addAll(this.itemGenerator
					.Item_BracersGeneratorWithNumberRequst(5));
			items.addAll(this.itemGenerator
					.Item_GlovesGeneratorWithNumberRequst(5));
		}

	}
	public ArrayList<Interface_Item> getFixedItem() {

		return this.items;
	}

	public void addItem(GameFixedItem chestBuilderr) {
		if(this.items==null){
			this.items = new ArrayList<Interface_Item>(1);
		}
		this.items.addAll(chestBuilderr.getFixedItem());
	}


}
