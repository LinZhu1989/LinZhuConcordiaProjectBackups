/**
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.dataIO;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utility.MonsterType;
import utility.RoleType;
import Data.Character.GameCharacter;
import Data.Character.GameMonster;
import Data.Factory.MonsterFactory;
import GUI.mapView.MapBlock;
import GUI.mapView.MapMonsterFactory;
import GUI.mapView.MapMonsterFactory.MapMonster;
import GUI.mapView.MapRoleFactory;
import GUI.mapView.MapRoleFactory.MapRole;

public class MonsterLoad {
	public GameMonster loadMonster(Element monsters){
		if(monsters==null)
			return null;
		GameMonster role= new GameMonster();
		NodeList firstList = monsters.getChildNodes();
		for (int i = 0; i < firstList.getLength(); ++i) {
			System.out.println("the first children node name is "
					+ firstList.item(i).getNodeName());
			Node firstChildren = firstList.item(i);
			// First children is info, ability,attribute,status,skill,bag
			if (firstChildren instanceof Element) {
				if (firstChildren.getNodeName().compareToIgnoreCase(
						MonsterSave.FIELD_MONSTER_NAME) == 0) {
					role.setMonsterName(firstChildren.getTextContent());
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						MonsterSave.FIELD_ABILITY) == 0) {
					loadAbility(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						MonsterSave.FIELD_ATTRIBUTE) == 0) {
					loadAttribute(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						MonsterSave.FIELD_STATUS) == 0) {
					loadStatus(role, firstChildren);
				}
			}
				
				
		}
		
		return role ;
	}
	
	public MapMonster loadMonster(Element monsters,MapBlock blk){
		MapMonsterFactory monsterFactory= new MapMonsterFactory();
		MapMonster mapMonster= (MapMonster)monsterFactory.create(MonsterType.MONSTER_1, blk);
		GameMonster role=mapMonster.getGameMonster();
		
		NodeList firstList = monsters.getChildNodes();
		for (int i = 0; i < firstList.getLength(); ++i) {
			System.out.println("the first children node name is "
					+ firstList.item(i).getNodeName());
			Node firstChildren = firstList.item(i);
			// First children is info, ability,attribute,status,skill,bag
			if (firstChildren instanceof Element) {
				if (firstChildren.getNodeName().compareToIgnoreCase(
						MonsterSave.FIELD_MONSTER_NAME) == 0) {
					role.setMonsterName(firstChildren.getTextContent());
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						MonsterSave.FIELD_ABILITY) == 0) {
					loadAbility(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						MonsterSave.FIELD_ATTRIBUTE) == 0) {
					loadAttribute(role, firstChildren);
				} else if (firstChildren.getNodeName().compareToIgnoreCase(
						MonsterSave.FIELD_STATUS) == 0) {
					loadStatus(role, firstChildren);
				}
			}
				
				
		}
		
		return mapMonster ;
	}
	
	/**
	 * this is the method allow user to load ability of a monster
	 * @param role
	 * @param firstChildren
	 */
	private void loadAbility(GameMonster role, Node firstChildren) {
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
	 * this is the method allow user to load status of a monster
	 * @param role
	 * @param firstChildren
	 */
	private void loadStatus(GameMonster role, Node firstChildren) {
		NodeList secondList = firstChildren.getChildNodes();
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);
			if (secondChildren instanceof Element) {
				System.out.println(" elemen content is : "
						+ secondChildren.getTextContent());
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
	 * allow users to load attribute of a monster
	 * @param role
	 * @param firstChildren
	 */
	private void loadAttribute(GameMonster role, Node firstChildren) {
		NodeList secondList = firstChildren.getChildNodes();
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);
			if (secondChildren instanceof Element) {
				System.out.println(" elemen content is : "
						+ secondChildren.getTextContent());
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
}
