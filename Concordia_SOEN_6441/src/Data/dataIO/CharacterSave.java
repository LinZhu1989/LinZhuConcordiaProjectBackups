/**
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.dataIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Data.Item.*;
import Data.Character.*;
import Data.GameData.CommonData;
import Data.Generator.HeroGenerator;

/**
 * this is the class allow user to save current character on XML file
 * 
 */
public class CharacterSave {

	public final static String FIELD_ROOTCHARACTER = "characters";
	public final static String FIELD_INFO = "info";
	public final static String FIELD_ABILITY = "ability";
	public final static String FIELD_ATTRIBUTE = "attribute";
	public final static String FIELD_STATUS = "status";
	public final static String FIELD_BAG = "bag";
	public final static String FIELD_EQUIPMENT = "equipments";
	public final static String FIELD_SKILL = "skill";
	public final static String FIELD_DISPLAY = "display";

	// those values defined in info class
	public final static String FIELD_NAME = "name";
	public final static String FIELD_RACE = "race";

	// those values defined in ability class
	public final static String FIELD_STRENGTH = "strength";
	public final static String FIELD_DEXTERITY = "dexterity";
	public final static String FIELD_CONSTITUTION = "constitution";
	public final static String FIELD_WISDOM = "wisdom";
	public final static String FIELD_INTELLEGENCE = "intellegence";
	public final static String FIELD_CHARISMA = "charmisma";

	// those values defined in attribute class
	public final static String FIELD_BASIC_ATK = "basicAtk";
	public final static String FIELD_BASIC_PHYSIC_ALARMOR = "basicPhysicalArmor";
	public final static String FIELD_BASIC_MAGIC_RESIST = "basicMagicResist";
	public final static String FIELD_BASIC_MOVEDISTANCE = "basicMoveDistance";
	public final static String FIELD_BASIC_ATTACK_DISTANCE = "basicAttackDistance";
	public final static String FILED_BASIC_ATTACK_BONUS = "basicAttackBonus";

	// those values defined in status class
	public final static String FIELD_BASICHP = "basicHP";
	public final static String FIELD_CURRENTHP = "currentHP";
	public final static String FIELD_MAXHP = "MAXHP";
	public final static String FIELD_BASICMP = "basicMP";
	public final static String FIELD_CURRENTMP = "currentMP";
	public final static String FIELD_MAXMP = "MAXMP";
	public final static String FIELD_LEVEL = "level";
	public final static String FIELD_NEEDXP = "needXP";
	public final static String FIELD_SKILLPOINT = "skillPoint";
	public final static String FIELD_ABILITYPOINT = "abilityPoint";
	public final static String FIELD_MONEY = "money";
	// those values defined in skill class
	public final static String FIELD_SKILL1_1LV = "skill1_1Lv";
	public final static String FIELD_SKILL1_2LV = "skill1_2Lv";
	public final static String FIELD_SKILL1_3LV = "skill1_3Lv";
	public final static String FIELD_SKILL2_1LV = "skill2_1Lv";
	public final static String FIELD_SKILL2_2LV = "skill2_2Lv";
	public final static String FIELD_SKILL2_3LV = "skill2_3Lv";
	public final static String FIELD_SKILL3_1LV = "skill3_1Lv";
	public final static String FIELD_SKILL3_2LV = "skill3_2Lv";
	public final static String FIELD_SKILL3_3LV = "skill3_3Lv";

	// those values defined in item class
	public final static String FIELD_ITEM = "item";
	public final static String FIELD_EQUIPMENT_TYPE = "equipemtType";
	public final static String FIELD_ITEM_TYPE = "itemType";
	public final static String FIELD_ITEM_NAME = "itemName";
	public final static String FIELD_ITEM_WEIGHT = "itemWeight";
	public final static String FIELD_ITEM_PRICE = "itemPrice";
	public final static String FIELD_ADJUST_PHYSIC_ALARMOR = "adjustPhysicalArmor";
	public final static String FIELD_ADJUST_ATK = "adjustATK";
	public final static String FIELD_ADJUST_MAGIC_RESIST = "adjustMajicResist";
	public final static String FIELD_ADJUST_ADATTACK_DISTANCE = "adjustAttackDistance";
	public final static String FIELD_ADJUST_MOVE_DISTANCE = "adjustMoveDistance";
	public final static String FIELD_ADJUST_STRENGTH = "adjustStrength";
	public final static String FIELD_ADJUST_DEXTERITY = "adjustDexterity";
	public final static String FIELD_ADJUST_CONSTITUTION = "adjustConstituton";
	public final static String FIELD_ADJUST_WISDOM = "adjustWisdom";
	public final static String FIELD_ADJUST_INTELLIGENCE = "adjustIntelligence";
	public final static String FIELD_ADJUST_CHARISMA = "adjustCharisma";
	public final static String FIELD_ADJUST_CURRENT_HP = "adjustCurrentHP";
	public final static String FIELD_ADJUST_CURRENT_MP = "adjustCurrentMP";
	public final static String FIELD_ADJUST_MAX_HP = "adjustMaxHP";
	public final static String FIELD_ADJUST_MAX_MP = "adjustMaxMP";

	public final static String FIELD_EQUIPT = "equipt";
	JFileChooser fileChooser = null;
	String path;
	/**
	 * this is the method to creat a new node and set the content in it 
	 * @param doc
	 * @param nodeName
	 * @param nodeValue
	 * @return
	 */
	public static Element newTextNode(Document doc, String nodeName,
			String nodeValue) {
		Element result = doc.createElement(nodeName);
		result.setTextContent(nodeValue);
		return result;
	}
	/**
	 * this is the method to creat a new node 
	 * @param doc
	 * @param nodeName
	 * @return
	 */
	public static Element newTextNode(Document doc, String nodeName) {
		Element result = doc.createElement(nodeName);
		return result;
	}
	/**
	 * this is the method judge the item type accourding to the item
	 * the return value of type is : Armor, Belts , Boots , etc
	 * @param item
	 * @return
	 */
	private static String judgeTextNode(Interface_Item item) {
		String type = null;
		if (item instanceof Interface_Armor) {
			type = CommonData.ITEM_COMMONNAMES[0];
		} else if (item instanceof Interface_Belt) {
			type = CommonData.ITEM_COMMONNAMES[1];
		} else if (item instanceof Interface_Boots) {
			type = CommonData.ITEM_COMMONNAMES[2];
		} else if (item instanceof Interface_Helmet) {
			type = CommonData.ITEM_COMMONNAMES[3];
		} else if (item instanceof Interface_Potion) {
			type = CommonData.ITEM_COMMONNAMES[6];
		} else if (item instanceof Interface_Ring) {
			type = CommonData.ITEM_COMMONNAMES[4];
		} else if (item instanceof Interface_Weapons) {
			type = CommonData.ITEM_COMMONNAMES[5];
		} else if (item instanceof Interface_Shield) {
			type = CommonData.ITEM_COMMONNAMES[7];
		} else if (item instanceof Interface_Bracers) {
			type = CommonData.ITEM_COMMONNAMES[8];
		} else if (item instanceof Interface_Gloves) {
			type = CommonData.ITEM_COMMONNAMES[9];
		}
		return type;

	}
	/**
	 * save Character to and add the information on certain node 
	 * @param doc
	 * @param elementNode
	 * @param hero2
	 * @return
	 */
	public static boolean saveCharacter(Document doc, Node elementNode,
			GameCharacter hero2) {
		Element rootElement = doc
				.createElement(CharacterSave.FIELD_ROOTCHARACTER);
		elementNode.appendChild(rootElement);

		// define first list elements
		Element infoElement = doc.createElement(CharacterSave.FIELD_INFO);
		Element abilityElement = doc.createElement(CharacterSave.FIELD_ABILITY);
		Element attributeElement = doc
				.createElement(CharacterSave.FIELD_ATTRIBUTE);
		Element statusElement = doc.createElement(CharacterSave.FIELD_STATUS);
		Element skillElement = doc.createElement(CharacterSave.FIELD_SKILL);
		Element bagElement = doc.createElement(CharacterSave.FIELD_BAG);
		Element equipElement = doc.createElement(CharacterSave.FIELD_EQUIPMENT);

		// append childrens to root
		rootElement.appendChild(infoElement);
		rootElement.appendChild(abilityElement);
		rootElement.appendChild(attributeElement);
		rootElement.appendChild(statusElement);
		rootElement.appendChild(skillElement);
		rootElement.appendChild(bagElement);
		rootElement.appendChild(equipElement);

		infoElement.appendChild(newTextNode(doc, CharacterSave.FIELD_NAME,
				hero2.ChaInfo.getName()));
		infoElement.appendChild(newTextNode(doc, CharacterSave.FIELD_RACE,
				hero2.ChaInfo.getRace()));
		saveAbility(doc, hero2.ability, abilityElement);

		saveAttribute(doc, hero2.attribute, attributeElement);

		saveStatus(doc, hero2.status, statusElement);

		saveSkill(doc, hero2.skill, skillElement);

		ArrayList<Interface_Item> items = hero2.bag.getItems();
		for (int i = 0; i < items.size(); i++) {
			Interface_Item currentItem = items.get(i);
			saveItem(doc, bagElement, currentItem);
		}

		ArrayList<Interface_Item> equipments = hero2.equipment.getEquipments();
		for (int i = 0; i < equipments.size(); i++) {
			Interface_Item currentItem = equipments.get(i);
			saveItem(doc, equipElement, currentItem);
		}

		return true;
	}
	/**
	 * save skill of character or monster
	 * @param doc
	 * @param skill
	 * @param skillElement
	 */
	public static void saveSkill(Document doc, Skills skill,
			Element skillElement) {
		skillElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILL1_1LV, new Integer(skill
						.getMagic(1).getMagicLevel()).toString()));
		skillElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILL1_2LV, new Integer(skill
						.getMagic(2).getMagicLevel()).toString()));
		skillElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILL1_3LV, new Integer(skill
						.getMagic(3).getMagicLevel()).toString()));
		skillElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILL2_1LV, new Integer(skill
						.getMagic(4).getMagicLevel()).toString()));
		skillElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILL2_2LV, new Integer(skill
						.getMagic(5).getMagicLevel()).toString()));
		skillElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILL2_3LV, new Integer(skill
						.getMagic(6).getMagicLevel()).toString()));
		skillElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILL3_1LV, new Integer(skill
						.getMagic(7).getMagicLevel()).toString()));
		skillElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILL3_2LV, new Integer(skill
						.getMagic(8).getMagicLevel()).toString()));
		skillElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILL3_3LV, new Integer(skill
						.getMagic(9).getMagicLevel()).toString()));
	}
	/**
	 * save status of either a character of a monster
	 * @param doc
	 * @param status
	 * @param statusElement
	 */
	public static void saveStatus(Document doc, Status status,
			Element statusElement) {
		statusElement.appendChild(newTextNode(doc, CharacterSave.FIELD_BASICHP,
				new Integer(status.getBasicHP()).toString()));
		statusElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_CURRENTHP,
				new Integer(status.getCurrentHP()).toString()));
		statusElement.appendChild(newTextNode(doc, CharacterSave.FIELD_MAXHP,
				new Integer(status.getMAXHP()).toString()));
		statusElement.appendChild(newTextNode(doc, CharacterSave.FIELD_BASICMP,
				new Integer(status.getBasicMP()).toString()));
		statusElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_CURRENTMP,
				new Integer(status.getCurrentMP()).toString()));
		statusElement.appendChild(newTextNode(doc, CharacterSave.FIELD_MAXMP,
				new Integer(status.getMAXMP()).toString()));
		statusElement.appendChild(newTextNode(doc, CharacterSave.FIELD_LEVEL,
				new Integer(status.getLevel()).toString()));
		statusElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_SKILLPOINT,
				new Integer(status.getSkillPoint()).toString()));
		statusElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ABILITYPOINT,
				new Integer(status.getAbilityPoint()).toString()));
		statusElement.appendChild(newTextNode(doc, CharacterSave.FIELD_MONEY,
				new Integer(status.getMoney()).toString()));
	}
	/**
	 * save attribute of either a character of a monster
	 * @param doc
	 * @param attribute
	 * @param attributeElement
	 */
	public static void saveAttribute(Document doc,Attribute attribute,
			Element attributeElement) {
		attributeElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_BASIC_ATK,
				new Integer(attribute.getBasicAttack()).toString()));
		attributeElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_BASIC_PHYSIC_ALARMOR, new Integer(
						attribute.getBasicPhysicalArmor()).toString()));
		attributeElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_BASIC_MAGIC_RESIST, new Integer(
						attribute.getBasicMagicResist()).toString()));
		attributeElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_BASIC_MOVEDISTANCE, new Integer(
						attribute.getBasicMoveDistance()).toString()));
		attributeElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_BASIC_ATTACK_DISTANCE, new Integer(
						attribute.getBasicAttackDistance()).toString()));
	}
	/**
	 * save status of either a character of a monster
	 * @param doc
	 * @param ability
	 * @param abilityElement
	 */
	public static void saveAbility(Document doc, Ability ability,
			Element abilityElement) {
		abilityElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_STRENGTH,
				new Integer( ability.getStrength()).toString()));
		abilityElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_DEXTERITY,
				new Integer( ability.getDexterity()).toString()));
		abilityElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_CONSTITUTION,
				new Integer( ability.getConstitution()).toString()));
		abilityElement.appendChild(newTextNode(doc, CharacterSave.FIELD_WISDOM,
				new Integer( ability.getWisdom()).toString()));
		abilityElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_INTELLEGENCE,
				new Integer( ability.getIntelligence()).toString()));
		abilityElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_CHARISMA,
				new Integer( ability.getCharisma()).toString()));
	}

	/**
	 * main function of this class
	 * 
	 * @param hero2
	 * @return
	 */
	public boolean saveCharacter(GameCharacter hero2) {
		// open a fileChooser ,allow user to choose a certain place
		fileChooser = new JFileChooser("C:\\");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooser.setDialogTitle("Save file");

		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			path = fileChooser.getSelectedFile().getAbsolutePath();
			System.out.println(path);
		} else {
			return false;
		}

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			saveCharacter(doc, doc, hero2);

			// construct the do-nothing transformation
			Transformer t = TransformerFactory.newInstance().newTransformer();
			// set indentation
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
					"2");
			// apply the do-nothing transformation and send the output to a file
			File f = new File(path);
			t.transform(new DOMSource(doc), new StreamResult(
					new FileOutputStream(f)));

		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * save current item in bag or equipment
	 * @param doc
	 * @param elementNode
	 * @param currentItem
	 */
	public static void saveItem(Document doc, Element elementNode,
			Interface_Item currentItem) {
		Element itemElement = doc.createElement(CharacterSave.FIELD_ITEM);
		elementNode.appendChild(itemElement);

		String type = judgeTextNode(currentItem);
		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_EQUIPMENT_TYPE, type));

		if (type.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[0])) {

			Item_Armor item = (Item_Armor) currentItem;
			itemElement.appendChild(newTextNode(doc,
					CharacterSave.FIELD_ITEM_TYPE, item.getType().toString()));
		} else if (type.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[5])) {
			Item_Weapons item = (Item_Weapons) currentItem;
			itemElement.appendChild(newTextNode(doc,
					CharacterSave.FIELD_ITEM_TYPE, item.getType().toString()));
		} else if (type.equalsIgnoreCase(CommonData.ITEM_COMMONNAMES[7])) {
			Item_Shield item = (Item_Shield) currentItem;
			itemElement.appendChild(newTextNode(doc,
					CharacterSave.FIELD_ITEM_TYPE, item.getType().toString()));
		}

		itemElement.appendChild(newTextNode(doc, CharacterSave.FIELD_ITEM_NAME,
				currentItem.getItemName()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ITEM_WEIGHT,
				new Integer(currentItem.getItemWeight()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ITEM_PRICE,
				new Integer(currentItem.getItemPrice()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_ATK,
				new Integer(currentItem.adjustATK()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_PHYSIC_ALARMOR, new Integer(
						currentItem.adjustPhysicalArmor()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_MAGIC_RESIST, new Integer(
						currentItem.adjustMagicResist()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_ADATTACK_DISTANCE, new Integer(
						currentItem.adjustAttackDistance()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_MOVE_DISTANCE, new Integer(
						currentItem.adjustMoveDistance()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_STRENGTH,
				new Integer(currentItem.adjustStrength()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_DEXTERITY,
				new Integer(currentItem.adjustDexterity()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_CONSTITUTION, new Integer(
						currentItem.adjustConstitution()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_WISDOM,
				new Integer(currentItem.adjustWisdom()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_INTELLIGENCE, new Integer(
						currentItem.adjustIntelligence()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_CHARISMA,
				new Integer(currentItem.adjustCharisma()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_CURRENT_HP,
				new Integer(currentItem.adjustCurrentHP()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_CURRENT_MP,
				new Integer(currentItem.adjustCurrentMP()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_MAX_HP,
				new Integer(currentItem.adjustMaxHP()).toString()));

		itemElement.appendChild(newTextNode(doc,
				CharacterSave.FIELD_ADJUST_MAX_MP,
				new Integer(currentItem.adjustMaxMP()).toString()));
	}

}
