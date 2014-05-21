package GUI.mapView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import Data.Builder.*;
import Data.Character.GameCharacter;
import Data.GameData.CommonData;
import Data.Generator.HeroGenerator;
import utility.MapBlockType;
import utility.RoleType;

/**
 * this class use the factory pattern to create
 * the Map role used in the UI
 * @author Jingang.Li
 *
 */
public class MapRoleFactory {
	
	/**
	 * the method to creat role type
	 * @param r
	 * @param blk
	 * @return
	 */
	public IMapRole create (RoleType r,MapBlock blk) {
		return new MapRole(r,blk);
	}
	
	public class MapRole extends MapItem implements IMapRole {
		private RoleType m_Type;
		GameCharacter m_Character;
		private MapRole(RoleType r,MapBlock blk) {
			m_Type = r;
			super.setOwnerBlock(blk);
			//HeroGenerator hg = new HeroGenerator();
			CharacterDirector roleDirector= new CharacterDirector();
			BullyBuilder bullyBuilder= new BullyBuilder();
			NimbleBuilder nimbleBuilder= new NimbleBuilder();
			TankBuilder tankBuilder= new TankBuilder();
			switch(m_Type)
			{	
			case BULLY:
				roleDirector.setCharacterBuilder(bullyBuilder);
				roleDirector.constructCharacter();
				m_Character = roleDirector.getGameCharacter();
				//m_Character = hg.getOneHeroWithRaceSet(CommonData.FIGHTER_TYPE_BULLY);
				break;
			case NIMBLE:
				roleDirector.setCharacterBuilder(nimbleBuilder);
				roleDirector.constructCharacter();
				m_Character = roleDirector.getGameCharacter();
				//m_Character = hg.getOneHeroWithRaceSet(CommonData.FIGHTER_TYPE_NIMBLE);
				break;				
			case TANK:
				roleDirector.setCharacterBuilder(tankBuilder);
				roleDirector.constructCharacter();
				m_Character = roleDirector.getGameCharacter();
				//m_Character = hg.getOneHeroWithRaceSet(CommonData.FIGHTER_TYPE_TANK);
				break;				
			}
		}
		
		/**
		 * get game character 
		 * @return
		 */
		public GameCharacter getGameCharacter(){
			return m_Character;
		}
		
		@Override
		/**
		 * get block type
		 */
		public MapBlockType getBlockType() {
			return MapBlockType.BLOCK_ROLE;
		}

		@Override
		/**
		 * draw 
		 */
		public void draw(Graphics g) {		
		}

		@Override
		/**
		 * get border width
		 */
		public int getBorderWidth() {
			return 0;
		}

		@Override
		/**
		 * get border color
		 */
		public Color getBorderColor() {
			return null;
		}

		@Override
		/**
		 * get image
		 */
		public Image getImage() {
			return MapResource.getImageByRoleType(m_Type);
		}
		
		/**
		 * set game character
		 * @param role
		 */
		public void setGameCharacter(GameCharacter role) {
			this.m_Character=role;
		}
	}	
}

