package Data.Factory;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import Data.GameData.CommonData;
import Data.Generator.ItemGenerator;
import Data.Item.*;

public class ItemFactory {
	

	private String itemType=null;
	
	public ItemFactory(String type){
		//"Armor","Belt","Boots","Helmet","Ring","Weapons".....
		this.itemType=type;
	}
	
	/**
	 * get item
	 * @param level
	 * @return
	 */
	public Interface_Item getItem(int level){
		ItemGenerator itemGene = new ItemGenerator();
		if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[0])){
			return itemGene.Item_ArmorGeneratorWithLevel(level);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[1])){
			return itemGene.Item_BeltRandomOneGenerator();
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[2])){
			return itemGene.Item_BootsRandomOneGenerator();
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[3])){
			return itemGene.Item_HelmetRandomOneGenerator();
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[4])){
			return itemGene.Item_RingRandomOneGenerator();
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[5])){
			return itemGene.Item_WeaponsRandomOneGenerator();
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[6])){
			return itemGene.Item_PotionRandomOneGenerator();
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[7])){
			return itemGene.Item_ShieldGeneratorWithLevel(level);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[8])){
			return itemGene.Item_BracersRandomOneGenerator();
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[9])){
			return itemGene.Item_GlovesRandomOneGenerator();
		}
		return null;
	}
	
	
	
	
	/**
	 * get item	
	 * @param type
	 * @param name
	 * @param w
	 * @param p
	 * @param data
	 * @return
	 */
	public Interface_Item getItem(String type,String name,int w,int p,int[] data) {
		//"Armor","Belt","Boots","Helmet","Ring","Weapons","Potion"
		if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[0])){
			return new Item_Armor(type,name,w,p,data);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[1])){
			return new Item_Belt(name,w,p,data);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[2])){
			return new Item_Boots(name,w,p,data);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[3])){
			return new Item_Helmet(name,w,p,data);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[4])){
			return new Item_Ring(name,w,p,data);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[5])){
			return new Item_Weapons(type,name,w,p,data);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[6])){
			return new Item_Potion(name,w,p,data);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[7])){
			return new Item_Shield(type,name,w,p,data);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[8])){
			return new Item_Bracers(name,w,p,data);
		}else if(itemType.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[9])){
			return new Item_Gloves(name,w,p,data);
		}
		return null;
}

}
