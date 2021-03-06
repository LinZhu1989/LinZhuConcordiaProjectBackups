package GUI.itemView;

/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */


import java.awt.Image;

import javax.swing.ImageIcon;

import utility.ItemResource;
import utility.ItemResource.ItemFilter;



/**
 * The definition of the string/values used by the equipment UI.
 */
public class ItemDrawResource extends ItemResource {
	final int ITEM_COL_COUNT = 12;
	final int ITEM_ROW_COUNT = 10;

	final int SELECTED_COL_COUNT = 9;


	final int BUTTON_HEIGHT = 50;

	final String TXT_NOTHING = "Nothing";

	final String TXT_ALL = "All";
	final String TXT_ITEM = "Item";
	final String TXT_ARMOR = "Armor";
	final String TXT_WEAPON = "Weapon";
	final String TXT_SAVE = "Save";
	final String TXT_QUIT = "Quit";

	final String BUTTON_TEXT[] = { TXT_ALL, TXT_ITEM, TXT_ARMOR, TXT_WEAPON,
			TXT_SAVE, TXT_QUIT };

	public final String TXT_HP_LEVEL = "HP";
	public final String TXT_ARMOR_CLASS = "ArmorClass";
	public final String TXT_ARMOR_MODIFIER = "ArmorModifier";
	public final String TXT_SHIELD_MODIFIER = "ShieldModifier";
	public final String TXT_BASE_ATTACK_BONUS = "BaseAttackBonus";
	public final String TXT_ATTACK_BONUS = "AttackBonus";

	public final String TXT_ATTACK_DISTANCE = "AttackDistance";
	public final String TXT_MOVE_DISTANCE = "MoveDistance";

	public final String TXT_DEXTERITY = "Dexterity";
	public final String TXT_STRENGTH = "Strength";
	public final String TXT_CONSTITUTION = "Constitution";
	public final String TXT_INTELLIGENCE = "Intelligence";
	public final String TXT_WISDOM = "Wisdom";
	public final String TXT_CHARISMA = "Charisma";

	public final String TXT_DEXTERITY_MODIFIER = "DexterityModifier";
	public final String TXT_STRENGTH_MODIFIER = "StrengthModifier";
	public final String TXT_CONSTITUTION_MODIFIER = "ConstitutionModifier";
	public final String TXT_INTELLIGENCE_MODIFIER = "IntelligenceModifier";
	public final String TXT_WISDOM_MODIFIER = "WisdomModifier";
	public final String TXT_CHARISMA_MODIFIER = "CharismaModifier";

	public final String INFO_TEXT[] = {

	TXT_HP_LEVEL, TXT_DEXTERITY, TXT_STRENGTH, TXT_CONSTITUTION, TXT_INTELLIGENCE,
			TXT_WISDOM, TXT_CHARISMA, TXT_DEXTERITY_MODIFIER,
			TXT_STRENGTH_MODIFIER, TXT_CONSTITUTION_MODIFIER,
			TXT_INTELLIGENCE_MODIFIER, TXT_WISDOM_MODIFIER,
			TXT_CHARISMA_MODIFIER, TXT_ARMOR_CLASS, TXT_ARMOR_MODIFIER,
			TXT_SHIELD_MODIFIER, TXT_BASE_ATTACK_BONUS, TXT_ATTACK_BONUS,
			TXT_ATTACK_DISTANCE, TXT_MOVE_DISTANCE, };


	final String ICON_UP_ARROW = "./res/GUI/imgArrowUp.png";
	final String ICON_DOWN_ARROW = "./res/GUI/imgArrowDown.png";


	final static String ICON_ARMOR1 = "./res/GUI/item/armor1.jpg";
	final static String ICON_ARMOR2 = "./res/GUI/item/armor2.jpg";
	final static String ICON_ARMOR3 = "./res/GUI/item/armor3.jpg";
	final static String ICON_ARMOR4 = "./res/GUI/item/armor4.jpg";
	final static String ICON_ARMOR5 = "./res/GUI/item/armor5.jpg";
	final static String ICON_ARMOR6 = "./res/GUI/item/armor6.jpg";
	final static String ICON_ARMOR7 = "./res/GUI/item/armor7.jpg";
	final static String ICON_ARMOR8 = "./res/GUI/item/armor8.jpg";

	final static String ICON_BELT1 = "./res/GUI/item/belt1.jpg";
	final static String ICON_BELT2 = "./res/GUI/item/belt2.jpg";
	final static String ICON_BELT3 = "./res/GUI/item/belt3.jpg";
	final static String ICON_BELT4 = "./res/GUI/item/belt4.jpg";
	final static String ICON_BELT5 = "./res/GUI/item/belt5.jpg";

	final static String ICON_BOOTS1 = "./res/GUI/item/boots1.jpg";
	final static String ICON_BOOTS2 = "./res/GUI/item/boots2.jpg";
	final static String ICON_BOOTS3 = "./res/GUI/item/boots3.jpg";
	final static String ICON_BOOTS4 = "./res/GUI/item/boots4.jpg";
	final static String ICON_BOOTS5 = "./res/GUI/item/boots5.jpg";

	final static String ICON_HELMET1 = "./res/GUI/item/Helmet1.jpg";
	final static String ICON_HELMET2 = "./res/GUI/item/Helmet2.jpg";
	final static String ICON_HELMET3 = "./res/GUI/item/Helmet3.jpg";
	final static String ICON_HELMET4 = "./res/GUI/item/Helmet4.jpg";
	final static String ICON_HELMET5 = "./res/GUI/item/Helmet5.jpg";

	final static String ICON_POTION1 = "./res/GUI/item/Potion1.jpg";
	final static String ICON_POTION2 = "./res/GUI/item/Potion2.jpg";
	final static String ICON_POTION3 = "./res/GUI/item/Potion3.jpg";
	final static String ICON_POTION4 = "./res/GUI/item/Potion4.jpg";
	final static String ICON_POTION5 = "./res/GUI/item/Potion5.jpg";

	final static String ICON_RING1 = "./res/GUI/item/Ring1.jpg";
	final static String ICON_RING2 = "./res/GUI/item/Ring2.jpg";
	final static String ICON_RING3 = "./res/GUI/item/Ring3.jpg";
	final static String ICON_RING4 = "./res/GUI/item/Ring4.jpg";
	final static String ICON_RING5 = "./res/GUI/item/Ring5.jpg";

	final static String ICON_WEAPONS1 = "./res/GUI/item/Weapons1.jpg";
	final static String ICON_WEAPONS2 = "./res/GUI/item/Weapons2.jpg";
	final static String ICON_WEAPONS3 = "./res/GUI/item/Weapons3.jpg";
	final static String ICON_WEAPONS4 = "./res/GUI/item/Weapons4.jpg";
	final static String ICON_WEAPONS5 = "./res/GUI/item/Weapons5.jpg";

	final static String ICON_BRACERS1 = "./res/GUI/item/bracers1.jpg";
	final static String ICON_BRACERS2 = "./res/GUI/item/bracers2.jpg";
	final static String ICON_BRACERS3 = "./res/GUI/item/bracers3.jpg";
	final static String ICON_BRACERS4 = "./res/GUI/item/bracers4.jpg";
	final static String ICON_BRACERS5 = "./res/GUI/item/bracers5.jpg";

	final static String ICON_GLOVES1 = "./res/GUI/item/gloves1.jpg";
	final static String ICON_GLOVES2 = "./res/GUI/item/gloves2.jpg";
	final static String ICON_GLOVES3 = "./res/GUI/item/gloves3.jpg";
	final static String ICON_GLOVES4 = "./res/GUI/item/gloves4.jpg";
	final static String ICON_GLOVES5 = "./res/GUI/item/gloves5.jpg";

	final static String ICON_SHIELD1 = "./res/GUI/item/shield1.jpg";
	final static String ICON_SHIELD2 = "./res/GUI/item/shield2.jpg";
	final static String ICON_SHIELD3 = "./res/GUI/item/shield3.jpg";
	final static String ICON_SHIELD4 = "./res/GUI/item/shield4.jpg";
	final static String ICON_SHIELD5 = "./res/GUI/item/shield5.jpg";
	
	final static String BACKGROUND = "./res/GUI/item/background.jpg";
	
	protected Image bottonBackground=new ImageIcon("./res/GUI/item/bottonBackground.jpg").getImage();
}
