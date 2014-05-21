package GUI.mapView;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import Data.Character.GameMonster;
import utility.MapBlockType;
import utility.MonsterType;

public class MapMonsterFactory {
	public IMapMonster create (MonsterType mt,MapBlock blk) {
		return new MapMonster(mt,blk);
	}
	
	public class MapMonster extends MapItem implements IMapMonster {
		
		private MonsterType m_Type;
		private GameMonster m_Monster;
		private MapMonster(MonsterType t,MapBlock blk) {
			m_Type = t;
			m_Monster = new GameMonster(1);
			super.setOwnerBlock(blk);
		}
		
		/**
		 * get game monster
		 * @return
		 */
		public GameMonster getGameMonster(){
			return m_Monster;
		}
		@Override
		/**
		 * get block type
		 */
		public MapBlockType getBlockType() {
			return MapBlockType.BLOCK_MONSTER;
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
			return MapResource.getImageByMonsterType(m_Type);
		}
	}
}
