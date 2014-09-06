package utility;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;



import Data.Character.GameCharacter;
import Data.Character.GameMonster;
import GUI.itemView.CharacterInfoLable;
import GUI.itemView.ItemDrawResource;

public class InfoZone {
	
	private ItemDrawResource R = new ItemDrawResource();
	private HashMap<String,CharacterInfoLable> m_CharacterInfors;
	private HashMap<String,CharacterInfoLable> m_MonsterInfors;
	private GameCharacter m_Character;
	private GameMonster m_Monster;
	private Zone m_Zone;
	public InfoZone(Zone zone){
		m_Zone=zone;
	}
	
	
	public void initCharacterInfoLabels(int locX, int locY, int infoWidth, int infoHeight,GameCharacter gc){

		m_Character = gc;
		m_CharacterInfors = new HashMap<String,CharacterInfoLable>() ;
		int infoBtnHeight = infoHeight / R.INFO_TEXT.length;
	
		for (int i = 0; i < R.INFO_TEXT.length; ++i) {
			CharacterInfoLable cZone = new CharacterInfoLable(new Point (locX,locY),
					infoWidth,infoBtnHeight,m_Zone,R.INFO_TEXT[i]);
			m_Zone.addZone(cZone);
			m_CharacterInfors.put(R.INFO_TEXT[i], cZone);
			locY += infoBtnHeight;
		}
		refreshCharacterInfos();
	}
	
	public void initMonsterInfoLabels(int locX, int locY, int infoWidth, int infoHeight,GameMonster gm){

		m_Monster = gm;
		m_MonsterInfors = new HashMap<String,CharacterInfoLable>() ;
		int infoBtnHeight = infoHeight / R.INFO_TEXT.length;
	
		for (int i = 0; i < R.INFO_TEXT.length; ++i) {
			CharacterInfoLable cZone = new CharacterInfoLable(new Point (locX,locY),
					infoWidth,infoBtnHeight,m_Zone,R.INFO_TEXT[i]);
			m_Zone.addZone(cZone);
			m_MonsterInfors.put(R.INFO_TEXT[i], cZone);
			locY += infoBtnHeight;
		}
		refreshMonsterInfos();
	}

	public void setRole(GameCharacter gc){
		m_Character = gc;
	}
	
	public void setMonster(GameMonster gm){
		m_Monster = gm;
	}

	public void refreshCharacterInfos() {
		if (m_Character == null)
			return;
		
		m_CharacterInfors.get(R.TXT_DEXTERITY).setValue(" "+new Integer(m_Character.getFixedDexterity()).toString());	
		m_CharacterInfors.get(R.TXT_STRENGTH).setValue(" "+new Integer(m_Character.getFixedStrength()).toString());
		m_CharacterInfors.get(R.TXT_CONSTITUTION).setValue(" "+new Integer(m_Character.getFixedConstitution()).toString());
		m_CharacterInfors.get(R.TXT_INTELLIGENCE).setValue(" "+new Integer(m_Character.getFixedIntelligence()).toString());
		m_CharacterInfors.get(R.TXT_WISDOM).setValue(" "+new Integer(m_Character.getFixedWisdom()).toString());
		m_CharacterInfors.get(R.TXT_CHARISMA).setValue(" "+new Integer(m_Character.getFixedCharisma()).toString());
		
		m_CharacterInfors.get(R.TXT_DEXTERITY_MODIFIER).setValue(" "+new Integer(m_Character.getDexterityModifier()).toString());	
		m_CharacterInfors.get(R.TXT_STRENGTH_MODIFIER).setValue(" "+new Integer(m_Character.getStrengthModifier()).toString());
		m_CharacterInfors.get(R.TXT_CONSTITUTION_MODIFIER).setValue(" "+new Integer(m_Character.getConstitutionModifier()).toString());
		m_CharacterInfors.get(R.TXT_INTELLIGENCE_MODIFIER).setValue(" "+new Integer(m_Character.getIntelligenceModifier()).toString());
		m_CharacterInfors.get(R.TXT_WISDOM_MODIFIER).setValue(" "+new Integer(m_Character.getWisdomModifier()).toString());
		m_CharacterInfors.get(R.TXT_CHARISMA_MODIFIER).setValue(" "+new Integer(m_Character.getCharismaModifier()).toString());
		
		m_CharacterInfors.get(R.TXT_HP_LEVEL).setValue(" "+m_Character.getHPLevelString());
		m_CharacterInfors.get(R.TXT_ARMOR_MODIFIER).setValue(" "+new Integer(m_Character.getArmorModifier()).toString());
		m_CharacterInfors.get(R.TXT_ARMOR_CLASS).setValue(" "+new Integer(m_Character.getArmorClass()).toString());
		m_CharacterInfors.get(R.TXT_BASE_ATTACK_BONUS).setValue(" "+m_Character.getBaseAttackBonusString());
		m_CharacterInfors.get(R.TXT_ATTACK_BONUS).setValue(" "+m_Character.getAttackBonusString());
		m_CharacterInfors.get(R.TXT_SHIELD_MODIFIER).setValue(" "+new Integer(m_Character.getShieldModifier()).toString());
		m_CharacterInfors.get(R.TXT_ATTACK_DISTANCE).setValue(" "+new Integer(m_Character.getFixedAttackDistance()).toString());
		m_CharacterInfors.get(R.TXT_MOVE_DISTANCE).setValue(" "+new Integer(m_Character.getFixedMoveDistance()).toString());
				
	}
	
	public void refreshMonsterInfos() {
		if (m_Monster == null)
			return;
		
		m_MonsterInfors.get(R.TXT_DEXTERITY).setValue(new Integer(m_Monster.getFixedDexterity()).toString());	
		m_MonsterInfors.get(R.TXT_STRENGTH).setValue(new Integer(m_Monster.getFixedStrength()).toString());
		m_MonsterInfors.get(R.TXT_CONSTITUTION).setValue(new Integer(m_Monster.getFixedConstitution()).toString());
		m_MonsterInfors.get(R.TXT_INTELLIGENCE).setValue(new Integer(m_Monster.getFixedIntelligence()).toString());
		m_MonsterInfors.get(R.TXT_WISDOM).setValue(new Integer(m_Monster.getFixedWisdom()).toString());
		m_MonsterInfors.get(R.TXT_CHARISMA).setValue(new Integer(m_Monster.getFixedCharisma()).toString());
		
		m_MonsterInfors.get(R.TXT_DEXTERITY_MODIFIER).setValue(new Integer(m_Monster.getDexterityModifier()).toString());	
		m_MonsterInfors.get(R.TXT_STRENGTH_MODIFIER).setValue(new Integer(m_Monster.getStrengthModifier()).toString());
		m_MonsterInfors.get(R.TXT_CONSTITUTION_MODIFIER).setValue(new Integer(m_Monster.getConstitutionModifier()).toString());
		m_MonsterInfors.get(R.TXT_INTELLIGENCE_MODIFIER).setValue(new Integer(m_Monster.getIntelligenceModifier()).toString());
		m_MonsterInfors.get(R.TXT_WISDOM_MODIFIER).setValue(new Integer(m_Monster.getWisdomModifier()).toString());
		m_MonsterInfors.get(R.TXT_CHARISMA_MODIFIER).setValue(new Integer(m_Monster.getCharismaModifier()).toString());
		
		m_MonsterInfors.get(R.TXT_HP_LEVEL).setValue(m_Monster.getHPLevelString());
		m_MonsterInfors.get(R.TXT_ARMOR_MODIFIER).setValue(new Integer(m_Monster.getArmorModifier()).toString());
		m_MonsterInfors.get(R.TXT_ARMOR_CLASS).setValue(new Integer(m_Monster.getArmorClass()).toString());
		m_MonsterInfors.get(R.TXT_BASE_ATTACK_BONUS).setValue(m_Monster.getBaseAttackBonusString());
		m_MonsterInfors.get(R.TXT_ATTACK_BONUS).setValue(m_Monster.getAttackBonusString());
		m_MonsterInfors.get(R.TXT_SHIELD_MODIFIER).setValue(new Integer(m_Monster.getShieldModifier()).toString());
		m_MonsterInfors.get(R.TXT_ATTACK_DISTANCE).setValue(new Integer(m_Monster.getFixedAttackDistance()).toString());
		m_MonsterInfors.get(R.TXT_MOVE_DISTANCE).setValue(new Integer(m_Monster.getFixedMoveDistance()).toString());
				
	}
	
	public void setTextColor(Color c){
		if(m_CharacterInfors!=null){
			for(String labels:m_CharacterInfors.keySet()){
				m_CharacterInfors.get(labels).setTextColor(c);
			}	
		}
		if(m_MonsterInfors!=null){
			for(String labels:m_MonsterInfors.keySet()){
				m_MonsterInfors.get(labels).setTextColor(c);
			}	
		}
		
	}
}
