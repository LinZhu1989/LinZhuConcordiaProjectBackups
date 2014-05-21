package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.Point;

import Data.Item.Interface_Item;
import GUI.itemView.ItemDraw;

/**
 * this class is used to encapsulate the Item button in the
 * Item dialog
 * @author Jingang.Li
 *
 */
public class ItemButton extends TextZone {

	private Interface_Item m_Item;
	
	public ItemButton(Point pt, int w, int h, Zone parent, String imgName) {
		super(pt, w, h, parent,imgName);
		m_Item = null;
	}
	
	public Interface_Item getItem() {
		return m_Item;
	}

	/**
	 * set the item associated to the button
	 *
	 * @param item
	 * 
	 */
	public void setItem(Interface_Item item) {
		m_Item = item;
		if (m_Item == null) {
			this.setText("");
			this.setImageName("");
		} else {
			String name = ItemDraw.getItemImgName(m_Item.getItemName());
			if (name != null)
				this.setImageName(name);
		}
	}
}
