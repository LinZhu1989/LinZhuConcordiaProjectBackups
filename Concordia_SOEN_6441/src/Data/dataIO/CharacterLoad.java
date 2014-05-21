package Data.dataIO;

/**
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

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

import utility.RoleType;

import javax.swing.JFileChooser;

import Data.Character.*;
import Data.Factory.ItemFactory;
import Data.GameData.CommonData;
import Data.Item.*;
import GUI.mapView.IMapRole;
import GUI.mapView.MapBlock;
import GUI.mapView.MapRoleFactory;
import GUI.mapView.MapRoleFactory.MapRole;
/**
 * this is the class load the character from XML file
 */
public class CharacterLoad {
	
	/**
	 * this is the function to open a file 
	 * @return
	 */
	public String openFile() {

		JFileChooser fileChooser = null;
		String path = "";
		try {
			// new a fileChooser object
			fileChooser = new JFileChooser("C:\\");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
			fileChooser.setDialogTitle("Open file");
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				path = fileChooser.getSelectedFile().getAbsolutePath();
//				System.out.println(path);
			}
			return path;
		} catch (Exception e) {
			return path;
		}

	}

	/**
	 * this is method that allow user to load Character from XML file
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public boolean characterLoad(String path, GameCharacter role) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			File f = new File(path);
			Document rDoc = builder.parse(f);
			Element datasNode = rDoc.getDocumentElement();

			NodeList firstList = datasNode.getChildNodes();
			for (int i = 0; i < firstList.getLength(); ++i) {
				
				Node firstChildren = firstList.item(i);
				// First children is info, ability,attribute,status,skill,bag
				if (firstChildren instanceof Element) {
					if (firstChildren.getNodeName().compareToIgnoreCase(
							CharacterSave.FIELD_INFO) == 0) {
						loadInfo(role, firstChildren);
					} else if (firstChildren.getNodeName().compareToIgnoreCase(
							CharacterSave.FIELD_ABILITY) == 0) {
						loadAbility(role, firstChildren);
					} else if (firstChildren.getNodeName().compareToIgnoreCase(
							CharacterSave.FIELD_ATTRIBUTE) == 0) {
						loadAttribute(role, firstChildren);
					} else if (firstChildren.getNodeName().compareToIgnoreCase(
							CharacterSave.FIELD_STATUS) == 0) {
						loadStatus(role, firstChildren);
					} else if (firstChildren.getNodeName().compareToIgnoreCase(
							CharacterSave.FIELD_SKILL) == 0) {
						loadSkill(role, firstChildren);
					} else if (firstChildren.getNodeName().compareToIgnoreCase(
							CharacterSave.FIELD_BAG) == 0) {
						loadBag(role, firstChildren);
						
					} else if (firstChildren.getNodeName().compareToIgnoreCase(
							CharacterSave.FIELD_EQUIPMENT) == 0) {
						loadEquipment(role, firstChildren);
					}
					else
						return false;
				}
					
					
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * this is the function allow user to load items in bag
	 * @param role
	 * @param firstChildren
	 */
	private void loadBag(GameCharacter role, Node firstChildren) {
		NodeList secondList = firstChildren.getChildNodes();
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);
			if (secondChildren instanceof Element) {
				
				if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ITEM) == 0) {
					NodeList itemList = secondChildren
							.getChildNodes();

					loadBagItem(role,itemList);
				}
			
			}
		}
	}
	
	/**
	 * this is the function allow user to load items in equipment
	 * @param role
	 * @param firstChildren
	 */
	private void loadEquipment(GameCharacter role, Node firstChildren) {
		NodeList secondList = firstChildren.getChildNodes();
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);
			if (secondChildren instanceof Element) {
				
				if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ITEM) == 0) {
					NodeList itemList = secondChildren
							.getChildNodes();

					loadEquipmentItem(role,itemList);
				}
			
			}
		}
	}
	
	/**
	 * allow users to load attribute of a character
	 * @param role
	 * @param firstChildren
	 */
	private void loadAttribute(GameCharacter role, Node firstChildren) {
		NodeList secondList = firstChildren.getChildNodes();
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);
			if (secondChildren instanceof Element) {
				
				if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_BASIC_ATK) == 0) {
					role.attribute.setBasicAttack((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren)
						.getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_BASIC_PHYSIC_ALARMOR) == 0) {
					role.attribute.setBasicPhysicalArmor((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren)
						.getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_BASIC_MAGIC_RESIST) == 0) {
					role.attribute.setBasicMagicResist((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren)
						.getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_BASIC_MOVEDISTANCE) == 0) {
					role.attribute.setBasicMoveDistance((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren)
						.getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_BASIC_ATTACK_DISTANCE) == 0) {
					role.attribute.setBasicAttackDistance((Integer
							.parseInt(secondChildren
									.getTextContent())));

				}
			}
			
		}
	}

	/**
	 * allow user to load skill of a character 
	 * @param role
	 * @param firstChildren
	 */
	private void loadSkill(GameCharacter role, Node firstChildren) {
		NodeList secondList = firstChildren.getChildNodes();
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);
			if (secondChildren instanceof Element) {
				
				if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILL1_1LV) == 0) {
					role.skill.getMagic(1).setMagicLevel(
							(Integer.parseInt(secondChildren
									.getTextContent())));
				//	System.out.println("missing");
				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILL1_2LV) == 0) {
					role.skill.getMagic(2).setMagicLevel(
							(Integer.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILL1_3LV) == 0) {
					role.skill.getMagic(3).setMagicLevel(
							(Integer.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILL2_1LV) == 0) {
					role.skill.getMagic(4).setMagicLevel(
							(Integer.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILL2_2LV) == 0) {
					role.skill.getMagic(5).setMagicLevel(
							(Integer.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILL2_3LV) == 0) {
					role.skill.getMagic(6).setMagicLevel(
							(Integer.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILL3_1LV) == 0) {
					role.skill.getMagic(7).setMagicLevel(
							(Integer.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILL3_2LV) == 0) {
					role.skill.getMagic(8).setMagicLevel(
							(Integer.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILL3_3LV) == 0) {
					role.skill.getMagic(9).setMagicLevel(
							(Integer.parseInt(secondChildren
									.getTextContent())));

				}
				
			}
			
		}
	}
	/**
	 * this is the method allow user to load status of a character
	 * @param role
	 * @param firstChildren
	 */
	private void loadStatus(GameCharacter role, Node firstChildren) {
		NodeList secondList = firstChildren.getChildNodes();
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);
			if (secondChildren instanceof Element) {
//				System.out.println(" elemen content is : "
//						+ secondChildren.getTextContent());
				if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_BASICHP) == 0) {
					role.status.setBasicHP((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_CURRENTHP) == 0) {
					role.status.setCurrentHP((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_MAXHP) == 0) {
					role.status.setMAXHP((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_BASICMP) == 0) {
					role.status.setBasicMP((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_CURRENTMP) == 0) {
					role.status.setCurrentMP((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_MAXMP) == 0) {
					role.status.setMAXMP((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_LEVEL) == 0) {
					role.status.setLevel((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_SKILLPOINT) == 0) {
					role.status.setSkillPoint((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ABILITYPOINT) == 0) {
					role.status.setAbilityPoint((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_MONEY) == 0) {
					role.status.setMoney((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} 
			}
			
		}
	}
	/**
	 * this is the method allow user to load ability of a character
	 * @param role
	 * @param firstChildren
	 */
	private void loadAbility(GameCharacter role, Node firstChildren) {
		NodeList secondList = firstChildren.getChildNodes();
		// Second children strength.dexterity....
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);
			if (secondChildren instanceof Element) {
				if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_STRENGTH) == 0) {
					role.ability.setStrength(Integer
							.parseInt(secondChildren
									.getTextContent()));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_DEXTERITY) == 0) {
					role.ability.setDexterity(Integer
							.parseInt(secondChildren
									.getTextContent()));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_CONSTITUTION) == 0) {
					role.ability.setConstitution(Integer
							.parseInt(secondChildren
									.getTextContent()));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_WISDOM) == 0) {
					role.ability.setWisdom((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_INTELLEGENCE) == 0) {
					role.ability.setIntelligence((Integer
							.parseInt(secondChildren
									.getTextContent())));

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_CHARISMA) == 0) {
					role.ability.setCharisma((Integer
							.parseInt(secondChildren
									.getTextContent())));

				}
				
			}
			
		}
	}
	/**
	 * this is the method allow user to load character information (name , race)
	 * @param role
	 * @param firstChildren
	 */
	private void loadInfo(GameCharacter role, Node firstChildren) {
		NodeList secondList = firstChildren.getChildNodes();
		// Second children  name,race
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);
			
//			System.out.println("the second children node name is "
//					+ secondChildren.getNodeName());

			if (secondChildren instanceof Element) {
//				System.out.println(" elemen content is : "
//						+ secondChildren.getTextContent());
				// those are the functions to set info
				if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_NAME) == 0) {
					role.ChaInfo.setName(secondChildren
							.getTextContent());

				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_RACE) == 0) {
					role.ChaInfo.setFighterType(secondChildren
							.getTextContent());
					role.skill = new Skills(secondChildren
							.getTextContent());
		
				}
			}
		}
	}
	
	/**
	 * this is the method allow user to load item in a bag 
	 * @param role
	 * @param itemList
	 */
	private void loadBagItem(GameCharacter role,NodeList itemList) {

		String itemName=" " ;
		int itemWeight=0;
		int itemPrice=0;
		ItemFactory itemFc = null;
		String equipmentType = " ";
		String itemType = " ";
		int[] fixData = new int[15];

		for (int j = 0; j < itemList.getLength(); j++) {
			Node itemChildren = itemList.item(j);
			if (itemChildren instanceof Element) {

				if (((Element) itemChildren).getTagName().compareToIgnoreCase(
						CharacterSave.FIELD_EQUIPMENT_TYPE) == 0) {
					itemFc = new ItemFactory(itemChildren.getTextContent());

				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_TYPE) == 0) {
					
						itemType = itemChildren.getTextContent();

				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_NAME) == 0) {
					itemName = itemChildren.getTextContent();
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_WEIGHT) == 0) {
					itemWeight = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_PRICE) == 0) {
					itemPrice = (Integer
							.parseInt(itemChildren.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_ATK) == 0) {
					fixData[0] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_PHYSIC_ALARMOR) == 0) {
					fixData[1] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_MAGIC_RESIST) == 0) {
					fixData[2] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_ADATTACK_DISTANCE) == 0) {
					fixData[3] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_MOVE_DISTANCE) == 0) {
					fixData[4] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_STRENGTH) == 0) {
					fixData[5] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_DEXTERITY) == 0) {
					fixData[6] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CONSTITUTION) == 0) {
					fixData[7] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_WISDOM) == 0) {
					fixData[8] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_INTELLIGENCE) == 0) {
					fixData[9] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CHARISMA) == 0) {
					fixData[10] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CURRENT_HP) == 0) {
					fixData[11] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CURRENT_MP) == 0) {
					fixData[12] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_MAX_HP) == 0) {
					fixData[13] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_MAX_MP) == 0) {
					fixData[14] = (Integer.parseInt(itemChildren
							.getTextContent()));
						
					 role.getBackPack().getItems().add(itemFc.getItem(itemType,itemName,
					 itemWeight, itemPrice, fixData));
				}
				

			}
		}
	}
	
	/**
	 * this is the method allow user to load item in equipments
	 * @param role
	 * @param itemList
	 */
	private void loadEquipmentItem(GameCharacter role,NodeList itemList) {

		String itemName=" " ;
		int itemWeight=0;
		int itemPrice=0;
		ItemFactory itemFc = null;
		String equipmentType = " ";
		String itemType = " ";
		int[] fixData = new int[15];

		for (int j = 0; j < itemList.getLength(); j++) {
			Node itemChildren = itemList.item(j);
			if (itemChildren instanceof Element) {

				if (((Element) itemChildren).getTagName().compareToIgnoreCase(
						CharacterSave.FIELD_EQUIPMENT_TYPE) == 0) {
					itemFc = new ItemFactory(itemChildren.getTextContent());
					
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_TYPE) == 0) {
					
						itemType = itemChildren.getTextContent();

				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_NAME) == 0) {
					itemName = itemChildren.getTextContent();
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_WEIGHT) == 0) {
					itemWeight = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ITEM_PRICE) == 0) {
					itemPrice = (Integer
							.parseInt(itemChildren.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_ATK) == 0) {
					fixData[0] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_PHYSIC_ALARMOR) == 0) {
					fixData[1] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_MAGIC_RESIST) == 0) {
					fixData[2] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_ADATTACK_DISTANCE) == 0) {
					fixData[3] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_MOVE_DISTANCE) == 0) {
					fixData[4] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_STRENGTH) == 0) {
					fixData[5] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_DEXTERITY) == 0) {
					fixData[6] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CONSTITUTION) == 0) {
					fixData[7] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_WISDOM) == 0) {
					fixData[8] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_INTELLIGENCE) == 0) {
					fixData[9] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CHARISMA) == 0) {
					fixData[10] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CURRENT_HP) == 0) {
					fixData[11] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(
								CharacterSave.FIELD_ADJUST_CURRENT_MP) == 0) {
					fixData[12] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_MAX_HP) == 0) {
					fixData[13] = (Integer.parseInt(itemChildren
							.getTextContent()));
				} else if (((Element) itemChildren).getTagName()
						.compareToIgnoreCase(CharacterSave.FIELD_ADJUST_MAX_MP) == 0) {
					fixData[14] = (Integer.parseInt(itemChildren
							.getTextContent()));
						
						role.equipment.setEquipement(itemFc.getItem(itemType,itemName,
								 itemWeight, itemPrice, fixData));
					
					

				}
				

			}
		}
	}
	
	/**
	 * this is the main function of this class
	 * @param role
	 * @return
	 */
	public int characterLoad(GameCharacter role) {
		
		String path = null;
		path = openFile();
		int mark =-2;

		if (path.isEmpty()) {
			mark =0;
		} else {
			if(characterLoad(path, role)==false){
				mark =-1;
			}else{
				mark =1;
			}
		}
		return mark;

	}
	/**
	 * allow load character which is in a map
	 * @param doc
	 * @return
	 */
	public GameCharacter loadCharacter(Element characters){
		GameCharacter role= new GameCharacter();
		
		NodeList firstList = characters.getChildNodes();
		for (int i = 0; i < firstList.getLength(); ++i) {
			
			Node firstChildren = firstList.item(i);
			// First children is info, ability,attribute,status,skill,bag
			if (firstChildren instanceof Element) {
				if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_INFO) == 0) {
					loadInfo(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_ABILITY) == 0) {
					loadAbility(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_ATTRIBUTE) == 0) {
					loadAttribute(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_STATUS) == 0) {
					loadStatus(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_SKILL) == 0) {
					loadSkill(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_BAG) == 0) {
					loadBag(role, firstChildren);
					
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_EQUIPMENT) == 0) {
					loadEquipment(role, firstChildren);
				}
			}
				
				
		}
		
		return role ;
	}
	/**
	 * I add this for simplify. There are also other ways to achieve it.
	 * @param characters
	 * @param blk
	 * @return
	 */
	public MapRole loadCharacter(Element characters,MapBlock blk){
		MapRoleFactory roleFactory= new MapRoleFactory();
		MapRole mapRole= (MapRole)roleFactory.create(RoleType.BULLY, blk);
		//RoleType.DWARF is just a temp type, since after you load the info,
		//Both the type name and skills will be updated
		GameCharacter role=mapRole.getGameCharacter();
		role.bag.getItems().clear();
		role.equipment.getEquipments().clear();
		NodeList firstList = characters.getChildNodes();
		for (int i = 0; i < firstList.getLength(); ++i) {
			
			Node firstChildren = firstList.item(i);
			// First children is info, ability,attribute,status,skill,bag
			if (firstChildren instanceof Element) {
				if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_INFO) == 0) {
					loadInfo(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_ABILITY) == 0) {
					loadAbility(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_ATTRIBUTE) == 0) {
					loadAttribute(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_STATUS) == 0) {
					loadStatus(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_SKILL) == 0) {
					loadSkill(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_BAG) == 0) {
					loadBag(role, firstChildren);
					
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						CharacterSave.FIELD_EQUIPMENT) == 0) {
					loadEquipment(role, firstChildren);
				}
			}
				
				
		}
		
		return mapRole ;
	}
}
		
