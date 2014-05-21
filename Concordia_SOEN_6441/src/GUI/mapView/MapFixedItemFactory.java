package GUI.mapView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import utility.FixedItemType;
import utility.MapBlockType;
import utility.MonsterType;
import Data.Character.GameFixedItem;
import Data.Character.GameMonster;
import GUI.mapView.MapMonsterFactory.MapMonster;

/**
 * this is the class in charge of creation of the
 * "fixed" object on the map.
 * Namely Monster or Chest
 * @author Jingang.Li
 *
 */
public class MapFixedItemFactory {
	/**
	 * creat fixed item according to fixed item type
	 * @param mt
	 * @param blk
	 * @return
	 */
	public IMapFixedItem create (FixedItemType mt,MapBlock blk) {
		return new MapFixedItem(mt,blk);
	}

	/**
	 * creat fixed item by fixed item type
	 * @param mt
	 * @param blk
	 * @return
	 */
	public IMapFixedItem create (GameFixedItem mt,MapBlock blk) {
		return new MapFixedItem(mt,blk);
	}
	
	/**
	 * define map fixed item class 
	 *
	 */
	public class MapFixedItem extends MapItem implements IMapFixedItem {
		
		private FixedItemType m_Type;
		private GameFixedItem m_FixedItem;

		/**
		 * the constructor 
		 * @param t
		 * @param blk
		 */
		private MapFixedItem(FixedItemType t,MapBlock blk) {
			m_Type = t;
			m_FixedItem = new GameFixedItem(t);
			super.setOwnerBlock(blk);
		}
		
		/**
		 * the constructor with argument of GameFixedItem 
		 * @param item
		 * @param blk
		 */
		private MapFixedItem(GameFixedItem item,MapBlock blk) {
			m_Type = item.getFixedItemType();
			m_FixedItem = item;
			super.setOwnerBlock(blk);
		}		
		
		/**
		 * get map fixed item
		 * @return
		 */
		public GameFixedItem getMapFixedItem(){
			return m_FixedItem;
		}
	
		@Override
		/**
		 * get block type
		 */
		public MapBlockType getBlockType() {
			if (m_Type == FixedItemType.MERCHANT)
				return MapBlockType.BLOCK_MERCHANT;
			else
				return MapBlockType.BLOCK_CHEST;
		}

		@Override
		/**
		 * draw the item
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
		 * get the image
		 */
		public Image getImage() {
			return MapResource.getImageByMerchantType(m_Type);
		}

		/**
		 * get the item of GameFixedItem type
		 * @return
		 */
		public GameFixedItem getGameFixedItem() {
			return m_FixedItem;
		}
		
		/**
		 * override the toString method
		 */
		public String toString() {
			String result = this.m_Type.toString();
			result += "\nhas items:\n";
			result += m_FixedItem.toString();
			return result;
		}
	}
}
