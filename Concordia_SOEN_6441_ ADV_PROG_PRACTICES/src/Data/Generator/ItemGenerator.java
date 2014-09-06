/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Generator;

import java.util.ArrayList;

import junit.framework.Assert;
import Data.Builder.DiceTool;
import Data.GameData.CommonData;
import Data.Item.*;

/**
 * the class ItemGenerator helps to generate the item character
 * 
 * @author Lin Zhu
 * 
 */
public class ItemGenerator {

	/**
	 * Class ItemGenerator Constructor (With no argument)
	 */
	public ItemGenerator() {

	}

	/**
	 * Item_Armor Random One Generator
	 * 
	 * @return
	 */
	public Item_Armor Item_ArmorRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_ARMORNAMES.length);
		return Item_ArmorGeneratorWithNumberRequst(
				CommonData.ITEM_ARMORNAMES.length).get(dice.getValue() - 1);
	}

	/**
	 * Item_Belt Random One Generator
	 * 
	 * @return
	 */
	public Item_Belt Item_BeltRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_BELTNAMES.length);
		return Item_BeltGeneratorWithNumberRequst(
				CommonData.ITEM_BELTNAMES.length).get(dice.getValue() - 1);
	}

	/**
	 * Item_Boots Random One Generator
	 * 
	 * @return
	 */
	public Item_Boots Item_BootsRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_BOOTSNAMES.length);
		return Item_BootsGeneratorWithNumberRequst(
				CommonData.ITEM_BOOTSNAMES.length).get(dice.getValue() - 1);
	}

	/**
	 * Item_Helmet Random One Generator
	 * 
	 * @return
	 */
	public Item_Helmet Item_HelmetRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_HELMETNAMES.length);
		return Item_HelmetGeneratorWithNumberRequst(
				CommonData.ITEM_HELMETNAMES.length).get(dice.getValue() - 1);
	}

	/**
	 * Item_Potion Random One Generator
	 * 
	 * @return
	 */
	public Item_Potion Item_PotionRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_POTIONNAMES.length);
		return Item_PotionGeneratorWithNumberRequst(
				CommonData.ITEM_POTIONNAMES.length).get(dice.getValue() - 1);
	}

	/**
	 * Item_Ring Random One Generator
	 * 
	 * @return
	 */
	public Item_Ring Item_RingRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_RINGNAMES.length);
		return Item_RingGeneratorWithNumberRequst(
				CommonData.ITEM_RINGNAMES.length).get(dice.getValue() - 1);
	}

	/**
	 * Item_Weapons Random One Generator
	 * 
	 * @return
	 */
	public Item_Weapons Item_WeaponsRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_WEAPONSNAMES.length);
		return Item_WeaponsGeneratorWithNumberRequst(
				CommonData.ITEM_WEAPONSNAMES.length).get(dice.getValue() - 1);
	}

	public Item_Shield Item_ShieldRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_SHIELDNAMES.length);
		return Item_ShieldGeneratorWithNumberRequst(
				CommonData.ITEM_SHIELDNAMES.length).get(dice.getValue() - 1);
	}

	public Item_Bracers Item_BracersRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_BRACERSNAMES.length);
		return Item_BracersGeneratorWithNumberRequst(
				CommonData.ITEM_BRACERSNAMES.length).get(dice.getValue() - 1);
	}

	public Item_Gloves Item_GlovesRandomOneGenerator() {
		DiceTool dice = new DiceTool(CommonData.ITEM_GLOVESNAMES.length);
		return Item_GlovesGeneratorWithNumberRequst(
				CommonData.ITEM_GLOVESNAMES.length).get(dice.getValue() - 1);
	}

	public Item_Armor Item_ArmorGeneratorWithLevel(int level) {
		DiceTool dice = new DiceTool(CommonData.ITEM_ARMORNAMES.length);
		int index = dice.getValue() - 1;
		return new Item_Armor(level, CommonData.ITEM_ARMORNAMES[index],
				CommonData.ITEM_ARMORWEIGHTS[index],
				CommonData.ITEM_ARMORPRICES[index],
				CommonData.ITEM_ARMORFIXVALUES[index]);
	}

	public Item_Shield Item_ShieldGeneratorWithLevel(int level) {
		DiceTool dice = new DiceTool(CommonData.ITEM_SHIELDNAMES.length);
		int index = dice.getValue() - 1;
		return new Item_Shield(level, CommonData.ITEM_SHIELDNAMES[index],
				CommonData.ITEM_SHIELDWEIGHTS[index],
				CommonData.ITEM_SHIELDPRICES[index],
				CommonData.ITEM_SHIELDFIXVALUES[index]);
	}

	/**
	 * Item_Armor Generator With Number Requst
	 * 
	 * @param number
	 * @return
	 */
	public ArrayList<Item_Armor> Item_ArmorGeneratorWithNumberRequst(int number) {
		DiceTool d9 = new DiceTool(8);
		ArrayList<Item_Armor> results = new ArrayList<Item_Armor>(number);
		for (int index = 0; index < CommonData.ITEM_ARMORNAMES.length; index++) {
			results.add(new Item_Armor(d9.getValue(),
					CommonData.ITEM_ARMORNAMES[index],
					CommonData.ITEM_ARMORWEIGHTS[index],
					CommonData.ITEM_ARMORPRICES[index],
					CommonData.ITEM_ARMORFIXVALUES[index]));
		}
		return results;
	}

	/**
	 * Item_Belt Generator With Number Requst
	 * 
	 * @param number
	 * @return
	 */
	public ArrayList<Item_Belt> Item_BeltGeneratorWithNumberRequst(int number) {

		ArrayList<Item_Belt> results = new ArrayList<Item_Belt>(number);
		for (int index = 0; index < CommonData.ITEM_BELTNAMES.length; index++) {
			results.add(new Item_Belt(CommonData.ITEM_BELTNAMES[index],
					CommonData.ITEM_BELTWEIGHTS[index],
					CommonData.ITEM_BELTPRICES[index],
					CommonData.ITEM_BELTFIXVALUES[index]));
		}
		return results;
	}

	/**
	 * Item_Boots Generator With Number Requst
	 * 
	 * @param number
	 * @return
	 */
	public ArrayList<Item_Boots> Item_BootsGeneratorWithNumberRequst(int number) {
		// DiceTool diceName = new DiceTool(CommonData.item_BootsNames.length);
		ArrayList<Item_Boots> results = new ArrayList<Item_Boots>(number);
		for (int index = 0; index < CommonData.ITEM_BOOTSNAMES.length; index++) {
			results.add(new Item_Boots(CommonData.ITEM_BOOTSNAMES[index],
					CommonData.ITEM_BOOTSWEIGHTS[index],
					CommonData.ITEM_BOOTSPRICES[index],
					CommonData.ITEM_BOOTSFIXVALUES[index]));
		}
		return results;
	}

	/**
	 * Item_Helmet Generator With Number Requst
	 * 
	 * @param number
	 * @return
	 */
	public ArrayList<Item_Helmet> Item_HelmetGeneratorWithNumberRequst(
			int number) {
		// DiceTool diceName = new DiceTool(CommonData.item_HelmetNames.length);
		ArrayList<Item_Helmet> results = new ArrayList<Item_Helmet>(number);
		for (int index = 0; index < CommonData.ITEM_HELMETNAMES.length; index++) {
			results.add(new Item_Helmet(CommonData.ITEM_HELMETNAMES[index],
					CommonData.ITEM_HELMETWEIGHTS[index],
					CommonData.ITEM_HELMETPRICES[index],
					CommonData.ITEM_HELMETFIXVALUES[index]));
		}
		return results;
	}

	/**
	 * Item_Potion Generato rWith Number Requst
	 * 
	 * @param number
	 * @return
	 */
	public ArrayList<Item_Potion> Item_PotionGeneratorWithNumberRequst(
			int number) {
		// DiceTool diceName = new DiceTool(CommonData.item_PotionNames.length);
		ArrayList<Item_Potion> results = new ArrayList<Item_Potion>(number);
		for (int index = 0; index < CommonData.ITEM_POTIONNAMES.length; index++) {
			results.add(new Item_Potion(CommonData.ITEM_POTIONNAMES[index],
					CommonData.ITEM_POTIONWEIGHTS[index],
					CommonData.ITEM_POTIONPRICES[index],
					CommonData.ITEM_POTIONFIXVALUES[index]));
		}
		return results;
	}

	/**
	 * Item_Ring Generator With Number Requst
	 * 
	 * @param number
	 * @return
	 */
	public ArrayList<Item_Ring> Item_RingGeneratorWithNumberRequst(int number) {
		// DiceTool diceName = new DiceTool(CommonData.item_RingNames.length);
		ArrayList<Item_Ring> results = new ArrayList<Item_Ring>(number);
		for (int index = 0; index < CommonData.ITEM_RINGNAMES.length; index++) {
			results.add(new Item_Ring(CommonData.ITEM_RINGNAMES[index],
					CommonData.ITEM_RINGWEIGHTS[index],
					CommonData.ITEM_RINGPRICES[index],
					CommonData.ITEM_RINGFIXVALUES[index]));
		}
		return results;
	}

	public ArrayList<Item_Weapons> Item_WeaponsGeneratorWithNumberRequst(
			int number) {
		// DiceTool d2 = new DiceTool(2);
		// DiceTool diceName = new
		// DiceTool(CommonData.item_WeaponsNames.length);
		ArrayList<Item_Weapons> results = new ArrayList<Item_Weapons>(number);
		for (int index = 0; index < CommonData.ITEM_WEAPONSNAMES.length; index++) {
			int type = 1;
			if (index >= 3) {
				type = 2;
			}
			results.add(new Item_Weapons(type,
					CommonData.ITEM_WEAPONSNAMES[index],
					CommonData.ITEM_WEAPONSWEIGHTS[index],
					CommonData.ITEM_WEAPONSPRICES[index],
					CommonData.ITEM_WEAPONSFIXVALUES[index]));
		}
		return results;
	}

	public ArrayList<Item_Shield> Item_ShieldGeneratorWithNumberRequst(
			int number) {
		DiceTool d3 = new DiceTool(3);
		// DiceTool diceName = new
		// DiceTool(CommonData.item_WeaponsNames.length);
		ArrayList<Item_Shield> results = new ArrayList<Item_Shield>(number);
		for (int index = 0; index < CommonData.ITEM_SHIELDNAMES.length; index++) {
			results.add(new Item_Shield(d3.getValue(),
					CommonData.ITEM_SHIELDNAMES[index],
					CommonData.ITEM_SHIELDWEIGHTS[index],
					CommonData.ITEM_SHIELDPRICES[index],
					CommonData.ITEM_SHIELDFIXVALUES[index]));
		}
		return results;
	}

	public ArrayList<Item_Bracers> Item_BracersGeneratorWithNumberRequst(
			int number) {
		ArrayList<Item_Bracers> results = new ArrayList<Item_Bracers>(number);
		for (int index = 0; index < CommonData.ITEM_RINGNAMES.length; index++) {
			results.add(new Item_Bracers(CommonData.ITEM_BRACERSNAMES[index],
					CommonData.ITEM_BRACERSWEIGHTS[index],
					CommonData.ITEM_BRACERSPRICES[index],
					CommonData.ITEM_BRACERSFIXVALUES[index]));
		}
		return results;
	}

	public ArrayList<Item_Gloves> Item_GlovesGeneratorWithNumberRequst(
			int number) {
		ArrayList<Item_Gloves> results = new ArrayList<Item_Gloves>(number);
		for (int index = 0; index < CommonData.ITEM_GLOVESNAMES.length; index++) {
			results.add(new Item_Gloves(CommonData.ITEM_GLOVESNAMES[index],
					CommonData.ITEM_GLOVESWEIGHTS[index],
					CommonData.ITEM_GLOVESPRICES[index],
					CommonData.ITEM_GLOVESFIXVALUES[index]));
		}
		return results;
	}

	@SuppressWarnings("deprecation")
	public ArrayList<Item_Armor> Item_ArmorALLGenerator() {
		ArrayList<Item_Armor> results = new ArrayList<Item_Armor>(8);
		Assert.assertTrue(CommonData.ITEM_ARMORNAMES.length == 8);
		;
		for (int index = 0; index < CommonData.ITEM_ARMORNAMES.length; index++) {
			results.add(new Item_Armor(index + 1,
					CommonData.ITEM_ARMORNAMES[index],
					CommonData.ITEM_ARMORWEIGHTS[index],
					CommonData.ITEM_ARMORPRICES[index],
					CommonData.ITEM_ARMORFIXVALUES[index]));
		}
		return results;
	}

	public ArrayList<Item_Weapons> Item_WeaponsALLGenerator() {
		ArrayList<Item_Weapons> results = new ArrayList<Item_Weapons>(5);
		for (int index = 0; index < CommonData.ITEM_WEAPONSNAMES.length; index++) {
			int type = 1;
			if (index >= 3) {
				type = 2;
			}	
			results.add(new Item_Weapons(type,
					CommonData.ITEM_WEAPONSNAMES[index],
					CommonData.ITEM_WEAPONSWEIGHTS[index],
					CommonData.ITEM_WEAPONSPRICES[index],
					CommonData.ITEM_WEAPONSFIXVALUES[index]));
		}
		return results;
	}

	public ArrayList<Item_Shield> Item_ShieldAllGenerator() {

		ArrayList<Item_Shield> results = new ArrayList<Item_Shield>(5);
		for (int index = 0; index < CommonData.ITEM_SHIELDNAMES.length; index++) {
			results.add(new Item_Shield(index % 3 + 1,
					CommonData.ITEM_SHIELDNAMES[index],
					CommonData.ITEM_SHIELDWEIGHTS[index],
					CommonData.ITEM_SHIELDPRICES[index],
					CommonData.ITEM_SHIELDFIXVALUES[index]));
		}
		return results;
	}

	public ArrayList<Item_Weapons> Item_WeaponsAllTypes() {
		ArrayList<Item_Weapons> results = new ArrayList<Item_Weapons>(5);
		DiceTool d2 = new DiceTool(2);
		DiceTool d3 = new DiceTool(3);
		int i1 = d3.getValue() - 1;
		int i2 = d2.getValue() + 2;
		results.add(new Item_Weapons(1, CommonData.ITEM_WEAPONSNAMES[i1],
				CommonData.ITEM_WEAPONSWEIGHTS[i1],
				CommonData.ITEM_WEAPONSPRICES[i1],
				CommonData.ITEM_WEAPONSFIXVALUES[i1]));
		results.add(new Item_Weapons(2, CommonData.ITEM_WEAPONSNAMES[i2],
				CommonData.ITEM_WEAPONSWEIGHTS[i2],
				CommonData.ITEM_WEAPONSPRICES[i2],
				CommonData.ITEM_WEAPONSFIXVALUES[i2]));

		return results;
	}
}
