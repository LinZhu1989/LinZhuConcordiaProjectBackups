/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Character;

import java.util.ArrayList;

import Data.GameData.CommonData;
import Data.Item.*;

/**
 * the class Equipment handles the equipment for a Game Character
 * 
 * @author Lin Zhu
 * 
 */
public class Equipment {

	private Item_Armor armor;
	private Item_Belt belt;
	private Item_Boots boots;
	private Item_Helmet helmet;
	private Item_Ring ring;
	private Item_Weapons weapon;
	private Item_Shield shield;
	private Item_Bracers bracers;
	private Item_Gloves gloves;
	CommonData comData = new CommonData();

	/**
	 * Class Equipment Constructor (With no argument)
	 */
	public Equipment() {
		this.armor = new Item_Armor();
		this.belt = new Item_Belt();
		this.boots = new Item_Boots();
		this.helmet = new Item_Helmet();
		this.ring = new Item_Ring();
		this.weapon = new Item_Weapons();
		this.shield = new Item_Shield();
		this.bracers = new Item_Bracers();
		this.gloves = new Item_Gloves();
	}

	/**
	 * Class Equipment Constructor (With argument)
	 */
	public Equipment(String noItems) {
		this.armor = new Item_Armor();
		this.belt = new Item_Belt();
		this.boots = new Item_Boots();
		this.helmet = new Item_Helmet();
		this.ring = new Item_Ring();
		this.weapon = new Item_Weapons();
		this.shield = new Item_Shield();
		this.bracers = new Item_Bracers();
		this.gloves = new Item_Gloves();
	}

	/**
	 * get an armor
	 * 
	 * @return
	 */
	public Item_Armor getArmor() {
		return this.armor;
	}

	/**
	 * get a belt
	 * 
	 * @return
	 */
	public Item_Belt getBelt() {
		return this.belt;
	}

	/**
	 * get a boot
	 * 
	 * @return
	 */
	public Item_Boots getBoots() {
		return this.boots;
	}

	/**
	 * get a helmet
	 * 
	 * @return
	 */
	public Item_Helmet getHelmet() {
		return this.helmet;
	}

	/**
	 * get a ring
	 * 
	 * @return
	 */
	public Item_Ring getRing() {
		return this.ring;
	}

	/**
	 * get the weapons
	 * 
	 * @return
	 */
	public Item_Weapons getWeapons() {
		return this.weapon;
	}

	/**
	 * get the shield
	 * 
	 * @return
	 */
	public Item_Shield getShield() {
		return this.shield;
	}

	/**
	 * set armor with a new one
	 * 
	 * @param newItem
	 */
	public void setArmor(Item_Armor newItem) {
		this.armor = newItem;
	}

	/**
	 * set belt with a new one
	 * 
	 * @param newItem
	 */
	public void setBelt(Item_Belt newItem) {
		this.belt = newItem;
	}

	/**
	 * set boots with a new one
	 * 
	 * @param newItem
	 */
	public void setBoots(Item_Boots newItem) {
		this.boots = newItem;
	}

	/**
	 * set helmet with a new one
	 * 
	 * @param newItem
	 */
	public void setHelmet(Item_Helmet newItem) {
		this.helmet = newItem;
	}

	/**
	 * set ring with a new one
	 * 
	 * @param newItem
	 */
	public void setRing(Item_Ring newItem) {
		this.ring = newItem;
	}

	/**
	 * set weapon with a new one
	 * 
	 * @param newItem
	 */
	public void setWeapons(Item_Weapons newItem) {
		this.weapon = newItem;
	}

	/**
	 * set shield with a new one
	 * 
	 * @param newItem
	 */
	public void setShield(Item_Shield newItem) {
		this.shield = newItem;
	}

	public void setBracers(Item_Bracers newItem) {
		this.bracers = newItem;
	}
	
	public Item_Bracers getBracers() {
		return this.bracers;
	}	

	public void setGloves(Item_Gloves newItem) {
		this.gloves = newItem;
	}
	
	public Item_Gloves getGloves() {
		return this.gloves;
	}	
	
	public void setEquipement(Interface_Item newItem) {

		if (newItem instanceof Item_Armor) {
			this.setArmor((Item_Armor) newItem);
		} else if (newItem instanceof Item_Belt) {
			this.setBelt((Item_Belt) newItem);
		} else if (newItem instanceof Item_Boots) {
			this.setBoots((Item_Boots) newItem);
		} else if (newItem instanceof Item_Helmet) {
			this.setHelmet((Item_Helmet) newItem);
		} else if (newItem instanceof Item_Ring) {
			this.setRing((Item_Ring) newItem);
		} else if (newItem instanceof Item_Weapons) {
			this.setWeapons((Item_Weapons) newItem);
		} else if (newItem instanceof Item_Shield) {
			this.setShield((Item_Shield) newItem);
		}else if (newItem instanceof Item_Bracers) {
			this.setBracers((Item_Bracers) newItem);
		}else if (newItem instanceof Item_Gloves) {
			this.setGloves((Item_Gloves) newItem);
		}

	}

	/**
	 * get a arrayList of all the items in equipment
	 * 
	 * @return
	 */
	public ArrayList<Interface_Item> getEquipments() {
		ArrayList<Interface_Item> equipments = new ArrayList<Interface_Item>(6);
		equipments.add(armor);
		equipments.add(belt);
		equipments.add(boots);
		equipments.add(helmet);
		equipments.add(ring);
		equipments.add(weapon);
		equipments.add(shield);
		equipments.add(bracers);
		equipments.add(gloves);
		return equipments;
	}

}
