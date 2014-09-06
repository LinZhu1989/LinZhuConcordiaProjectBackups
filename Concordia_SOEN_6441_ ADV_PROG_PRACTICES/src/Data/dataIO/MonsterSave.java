/**
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.dataIO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import Data.Character.GameCharacter;
import Data.Character.GameMonster;
/**
 * this is the class allow user to save current monster on XML file
 * @author xinshao
 *
 */
public class MonsterSave {
	public final static String FIELD_MONSTERS = "monster";
	public final static String FIELD_MONSTER_NAME ="monsterName";
	public final static String FIELD_ABILITY = "ability";
	public final static String FIELD_ATTRIBUTE = "attribute";
	public final static String FIELD_STATUS = "Status";
	

		
		public static boolean saveMonster(Document doc, Node elementNode,
				GameMonster monster) {
			if (doc == null && elementNode == null && monster ==null){
				return false;
			}
			Element rootElement = doc
					.createElement(MonsterSave.FIELD_MONSTERS);
			elementNode.appendChild(rootElement);

			// define first list elements
			Element nameElement = doc.createElement(MonsterSave.FIELD_MONSTER_NAME);
			nameElement.setTextContent(monster.getMonsterName());
			Element abilityElement = doc.createElement(MonsterSave.FIELD_ABILITY);
			Element attributeElement = doc.createElement(MonsterSave.FIELD_ATTRIBUTE);
			Element statusElement = doc.createElement(MonsterSave.FIELD_STATUS);

			// append childrens to root
			rootElement.appendChild(nameElement);
			rootElement.appendChild(abilityElement);
			rootElement.appendChild(attributeElement);
			rootElement.appendChild(statusElement);
			
			

			CharacterSave monsterSave= new CharacterSave();
			monsterSave.saveAbility(doc, monster.ability, abilityElement);
			monsterSave.saveAttribute(doc, monster.attribute, attributeElement);
			monsterSave.saveStatus(doc, monster.status, statusElement);
			return false;

		}


}
