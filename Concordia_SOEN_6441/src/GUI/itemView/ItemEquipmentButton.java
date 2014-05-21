package GUI.itemView;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.Color;
import java.awt.Point;

import Data.Item.Interface_Item;
import utility.TextZone;
import utility.Zone;


/**
 * this is the class for show the selected equipment on the UI
 * @author Jingang.Li
 *
 */
public class ItemEquipmentButton <U extends Interface_Item>  extends TextZone {

	private U m_Item;
	public ItemEquipmentButton(Point pt, int w, int h, Zone parent,
			String imgName) {
		super(pt, w, h, parent, imgName);	
		
		this.setTextColor(Color.WHITE);
	}
	
	public U getItem() {
		return m_Item;
	}
	
	void setItem(U Item) {
		this.m_Item = Item;
		if (m_Item == null) {
			this.setText("");
			this.setImageName("");
		}else {
			this.setText(m_Item.getItemName());
			
			String name = ItemDraw.getItemImgName(m_Item.getItemName());
			if (name != null)
				this.setImageName(name);			
		}
	}
}
