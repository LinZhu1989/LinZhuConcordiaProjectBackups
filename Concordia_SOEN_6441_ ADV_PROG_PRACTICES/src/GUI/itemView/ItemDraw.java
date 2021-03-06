package GUI.itemView;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import utility.InfoZone;
import utility.IClickable;
import utility.ICommand;
import utility.IDrawableUI;
import utility.IMouseClickSupport;
import utility.ItemButton;
import utility.TextZone;
import utility.UserInterfaceType;
import utility.Zone;
import Data.Character.GameCharacter;
import Data.Item.Interface_Item;
import Data.Item.Item_Armor;
import Data.Item.Item_Belt;
import Data.Item.Item_Boots;
import Data.Item.Item_Bracers;
import Data.Item.Item_Gloves;
import Data.Item.Item_Helmet;
import Data.Item.Item_Ring;
import Data.Item.Item_Shield;
import Data.Item.Item_Weapons;
import GUI.mainView.MainCanvas;
import GUI.mainView.MainFrame;
/**
 * this is the UI for the Equipment definition UI used 
 * in the D2D game.
 * @author Jingang.Li
 *
 */
public class ItemDraw implements IDrawableUI, IMouseClickSupport {

	ItemDrawResource R = new ItemDrawResource();
	private Zone m_Zone;
	private MainCanvas m_Canves;
	private InfoZone cInfoZone;
	
	//the parent UI
	IDrawableUI m_ParentUI;
	
	private GameCharacter m_Character;
	
	private ItemEquipmentButton <Item_Helmet> m_Helmet;	
	private ItemEquipmentButton <Item_Armor> m_Armor;
	private ItemEquipmentButton <Item_Shield> m_Shield;
	
	private ItemEquipmentButton <Item_Belt> m_Belt;	
	private ItemEquipmentButton <Item_Bracers> m_Bracers;
	private ItemEquipmentButton <Item_Gloves> m_Gloves;	
	private ItemEquipmentButton <Item_Ring> m_Ring;	
	private ItemEquipmentButton <Item_Weapons> m_Weapons;	
	private ItemEquipmentButton <Item_Boots> m_Boots;
	
	ArrayList<ItemButton> m_ItemButtons;
	//private HashMap<String,CharacterInfoLable> m_CharacterInfors;

	/**
	 * draw item
	 * @param frame
	 * @param c
	 * @param character
	 * @param parentUI
	 */
	public ItemDraw(MainFrame frame, MainCanvas c, GameCharacter character, IDrawableUI parentUI){
		this.m_ParentUI = parentUI;
		this.m_Character = character;
		this.m_Canves = c;
		Insets insets = frame.getInsets();
		Rectangle rec = frame.getBounds();	
		int w = rec.width - insets.left;
		int h = rec.height - insets.top;
		m_Zone = new Zone(new Point(0,0), w, h,null,"");
		m_Zone.setImageName(R.BACKGROUND);
		int itemWidth = initItemButtons(w);
		
		int locX = R.ITEM_WIDTH_GAP + R.ITEM_COL_COUNT * (R.ITEM_WIDTH_GAP + itemWidth);
		int locY = R.ITEM_WIDTH_GAP;
		int infoWidth = w - ( locX + R.ITEM_WIDTH_GAP);
		int infoHeight = R.ITEM_ROW_COUNT * (R.ITEM_WIDTH_GAP + itemWidth) - R.ITEM_WIDTH_GAP;
		cInfoZone = new InfoZone(m_Zone);
//		initCharacterInfoLabels(locX, locY, infoWidth, infoHeight);
		cInfoZone.initCharacterInfoLabels(locX, locY, infoWidth, infoHeight, m_Character);
		cInfoZone.setTextColor(Color.WHITE);
		
		locX = R.ITEM_WIDTH_GAP;
		locY = R.ITEM_ROW_COUNT * (R.ITEM_WIDTH_GAP + itemWidth) + R.ITEM_WIDTH_GAP;
		initItemCommandButtons(w, locY);		

		locX = R.ITEM_WIDTH_GAP;
		locY = locY + R.BUTTON_HEIGHT + R.ITEM_WIDTH_GAP;
		initEquipmentButtons(w, h, locY);		
		
		refreshCharacterInforToUI();
	}

	/**
	 * set parent UI
	 * @param parentUI
	 */
	public void setParentUI(IDrawableUI parentUI) {
		if (parentUI != null)
			this.m_ParentUI = parentUI;
	}
	
	/**
	 * is empty item
	 * @param item
	 * @return
	 */
	private boolean isEmptyItem(Interface_Item item) {
		if (item == null) {
			return true;
		} else {
			return item.getItemName().equalsIgnoreCase(R.TXT_NOTHING);
		}
	}
	
	/**
	 * refresh character infor to UI
	 */
	private void refreshCharacterInforToUI() {
		if (this.m_Character == null)
			return;
		
		refreshEquipmentButton();		

		this.R.refreshItemButtons(this.R.filterItems(utility.ItemResource.ItemFilter.eAll, m_Character.bag.getItems()), this.m_ItemButtons);
		cInfoZone.refreshCharacterInfos();

	}

	private <U extends Interface_Item> void setEquipmentToButton(ItemEquipmentButton < U > btn, U item) {
		if(isEmptyItem(item)) {			
			btn.setItem(null);
		} else {
			btn.setItem(item);
		}					
	}
	
	/**
	 * refresh equipment button
	 */
	private void refreshEquipmentButton() {
		setEquipmentToButton(ItemDraw.this.m_Armor, m_Character.getEquipment().getArmor());
		setEquipmentToButton(ItemDraw.this.m_Shield, m_Character.getEquipment().getShield());
		setEquipmentToButton(ItemDraw.this.m_Belt, m_Character.getEquipment().getBelt());
		setEquipmentToButton(ItemDraw.this.m_Boots, m_Character.getEquipment().getBoots());
		setEquipmentToButton(ItemDraw.this.m_Helmet, m_Character.getEquipment().getHelmet());
		setEquipmentToButton(ItemDraw.this.m_Ring, m_Character.getEquipment().getRing());
		setEquipmentToButton(ItemDraw.this.m_Weapons, m_Character.getEquipment().getWeapons());
		setEquipmentToButton(ItemDraw.this.m_Bracers, m_Character.getEquipment().getBracers());
		setEquipmentToButton(ItemDraw.this.m_Gloves, m_Character.getEquipment().getGloves());
	
	}
	
	/**
	 * get item image name
	 * @param name
	 * @return
	 */
	public static String getItemImgName(String name) {
		return ItemDrawResource.getItemImageMap().get(name);
	}

	private int initItemButtons(int w) {
		m_ItemButtons = new ArrayList<ItemButton>();		
		//add the Item buttons
		int itemWidth = w / (R.ITEM_COL_COUNT + 6);
		for (int i = 0; i < R.ITEM_COL_COUNT; ++i) {
			int x = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + itemWidth) * i;
			
			for (int j = 0; j < R.ITEM_ROW_COUNT; j++) {
				int y = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + itemWidth) * j;
				Zone z = null;
				
				if (isUpArrowZone(j,i)) {
					z = new Zone(new Point (x,y),itemWidth,itemWidth,m_Zone,R.ICON_UP_ARROW);
					z.attachCommand(new UpArrowCommand());
				}else if (isDownArrowZone(j,i)) {
					z = new Zone(new Point (x,y),itemWidth,itemWidth,m_Zone,R.ICON_DOWN_ARROW);
					z.attachCommand(new DownArrowCommand());
				}else {
					ItemButton btn = new ItemButton(new Point (x,y),itemWidth,itemWidth,m_Zone,"");
					btn.attachCommand(new ItemCommand());	
					btn.setTextHeight(20);
					m_ItemButtons.add(btn);
					z = btn;
				}
				m_Zone.addZone(z);
			}			
		}
		return itemWidth;
	}


	/**
	 * initial equipment button
	 * @param w
	 * @param h
	 * @param locY
	 */
	private void initEquipmentButtons(int w, int h, int locY) {
		int selWidth = (w - (R.SELECTED_COL_COUNT + 1) * R.ITEM_WIDTH_GAP) / R.SELECTED_COL_COUNT;
		int selHeight = h - locY - 2 * R.ITEM_WIDTH_GAP;
		
		//add the ItemEquipmentButtons
		int i = 0;
		int selx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + selWidth) * i;			
		this.m_Helmet = new ItemEquipmentButton <Item_Helmet> (new Point(selx,locY + R.ITEM_WIDTH_GAP),selWidth, selHeight,m_Zone,"");			
		m_Zone.addZone(this.m_Helmet);
		this.m_Helmet.attachCommand(new EquipmentCommand());
		++i;
		
		selx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + selWidth) * i;
		this.m_Armor = new ItemEquipmentButton <Item_Armor> (new Point(selx,locY + R.ITEM_WIDTH_GAP),selWidth, selHeight,m_Zone,"");			
		m_Zone.addZone(this.m_Armor);
		this.m_Armor.attachCommand(new EquipmentCommand());
		++i;
		
		selx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + selWidth) * i;
		this.m_Belt = new ItemEquipmentButton <Item_Belt> (new Point(selx,locY + R.ITEM_WIDTH_GAP),selWidth, selHeight,m_Zone,"");			
		m_Zone.addZone(this.m_Belt);
		this.m_Belt.attachCommand(new EquipmentCommand());
		++i;
		
		selx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + selWidth) * i;
		this.m_Ring = new ItemEquipmentButton <Item_Ring> (new Point(selx,locY + R.ITEM_WIDTH_GAP),selWidth, selHeight,m_Zone,"");			
		m_Zone.addZone(this.m_Ring);
		this.m_Ring.attachCommand(new EquipmentCommand());
		++i;
		
		selx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + selWidth) * i;
		this.m_Weapons = new ItemEquipmentButton <Item_Weapons> (new Point(selx,locY + R.ITEM_WIDTH_GAP),selWidth, selHeight,m_Zone,"");			
		m_Zone.addZone(this.m_Weapons);
		this.m_Weapons.attachCommand(new EquipmentCommand());
		++i;
		
		selx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + selWidth) * i;
		this.m_Boots = new ItemEquipmentButton <Item_Boots> (new Point(selx,locY + R.ITEM_WIDTH_GAP),selWidth, selHeight,m_Zone,"");			
		m_Zone.addZone(this.m_Boots);
		this.m_Boots.attachCommand(new EquipmentCommand());
		++i;		
		
		selx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + selWidth) * i;
		this.m_Bracers = new ItemEquipmentButton <Item_Bracers> (new Point(selx,locY + R.ITEM_WIDTH_GAP),selWidth, selHeight,m_Zone,"");			
		m_Zone.addZone(this.m_Bracers);
		this.m_Bracers.attachCommand(new EquipmentCommand());
		++i;
		
		selx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + selWidth) * i;
		this.m_Gloves = new ItemEquipmentButton <Item_Gloves> (new Point(selx,locY + R.ITEM_WIDTH_GAP),selWidth, selHeight,m_Zone,"");			
		m_Zone.addZone(this.m_Gloves);
		this.m_Gloves.attachCommand(new EquipmentCommand());
		++i;

		selx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + selWidth) * i;
		this.m_Shield = new ItemEquipmentButton <Item_Shield> (new Point(selx,locY + R.ITEM_WIDTH_GAP),selWidth, selHeight,m_Zone,"");			
		m_Zone.addZone(this.m_Shield);
		this.m_Shield.attachCommand(new EquipmentCommand());
		++i;		
	}

	/**
	 * initial item command button
	 * @param w
	 * @param locY
	 */
	@SuppressWarnings("static-access")
	private void initItemCommandButtons(int w, int locY) {
		int tzWidth = (w - (R.BUTTON_TEXT.length + 1) * R.ITEM_WIDTH_GAP) / R.BUTTON_TEXT.length; 
		for (int i = 0; i < R.BUTTON_TEXT.length; ++i) {			
			int tzx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + tzWidth) * i ;
			TextZone tz = new TextZone(new Point(tzx, locY),tzWidth, R.BUTTON_HEIGHT,m_Zone,"");
			tz.setBackgroundImage(R.bottonBackground);
			tz.setText(R.BUTTON_TEXT[i]);
			tz.attachCommand(new ItemButtonCommand());
			tz.setTextHeight(22);
			m_Zone.addZone(tz);
		}
	}
	
	
	/**
	 * set the current character
	 */
	public void setCharacter(GameCharacter currentCharacter) {
		this.m_Character = currentCharacter;		
		refreshCharacterInforToUI();
	}
	
	private boolean isUpArrowZone(int row, int col) {
		if (row == 0 && col == R.ITEM_COL_COUNT - 1)
			return true;
		else
			return false;
	} 
	
	private boolean isDownArrowZone(int row, int col) {
		if (row == R.ITEM_ROW_COUNT - 1 && col == R.ITEM_COL_COUNT - 1)
			return true;
		else
			return false;
	}
	
	class UpArrowCommand implements ICommand {
		@Override
		public boolean execute(IClickable source,MouseEvent e) {
			System.out.println("Up Arrow");
			return true;
		}		
	}	
	
	class DownArrowCommand implements ICommand {
		@Override
		public boolean execute(IClickable source,MouseEvent e) {
			System.out.println("Down Arrow");
			return true;
		}		
	}		
	
	/**
	 * class to handling the command for the Item zones
	 * @author Jingang.Li
	 *
	 */
	class ItemCommand implements ICommand {

		@Override
		public boolean execute(IClickable source,MouseEvent e) {
			if (e.getClickCount() == 2) {
				if (source == null)
					return false;
				
				ItemButton iBtn = (ItemButton) source;
				if (iBtn.getItem() == null)
					return true;
				
				if (iBtn.getItem() instanceof Item_Helmet) {
					ItemDraw.this.m_Helmet.setItem((Item_Helmet) iBtn.getItem());
					Interface_Item oldItem = m_Character.equipment.getHelmet();
					if (!isEmptyItem(oldItem))
						m_Character.bag.addItem(oldItem);
					ItemDraw.this.m_Character.equipment.setHelmet((Item_Helmet)iBtn.getItem());
				}else if (iBtn.getItem() instanceof Item_Armor) {
					ItemDraw.this.m_Armor.setItem((Item_Armor) iBtn.getItem());
					Interface_Item oldItem = m_Character.equipment.getArmor();
					if (!isEmptyItem(oldItem))
						m_Character.bag.addItem(oldItem);
					ItemDraw.this.m_Character.equipment.setArmor((Item_Armor)iBtn.getItem());
				}else if (iBtn.getItem() instanceof Item_Belt) {
					ItemDraw.this.m_Belt.setItem((Item_Belt) iBtn.getItem());
					Interface_Item oldItem = m_Character.equipment.getBelt();
					if (!isEmptyItem(oldItem))
						m_Character.bag.addItem(oldItem);
					ItemDraw.this.m_Character.equipment.setBelt((Item_Belt)iBtn.getItem());
				}else if (iBtn.getItem() instanceof Item_Weapons) {
					ItemDraw.this.m_Weapons.setItem((Item_Weapons) iBtn.getItem());
					Interface_Item oldItem = m_Character.equipment.getWeapons();
					if (!isEmptyItem(oldItem))
						m_Character.bag.addItem(oldItem);
					ItemDraw.this.m_Character.equipment.setWeapons((Item_Weapons)iBtn.getItem());
				}else if (iBtn.getItem() instanceof Item_Ring) {
					ItemDraw.this.m_Ring.setItem((Item_Ring) iBtn.getItem());
					Interface_Item oldItem = m_Character.equipment.getRing();
					if (!isEmptyItem(oldItem))
						m_Character.bag.addItem(oldItem);
					ItemDraw.this.m_Character.equipment.setRing((Item_Ring)iBtn.getItem());
				}else if (iBtn.getItem() instanceof Item_Boots) {
					ItemDraw.this.m_Boots.setItem((Item_Boots) iBtn.getItem());
					Interface_Item oldItem = m_Character.equipment.getBoots();
					if (!isEmptyItem(oldItem))
						m_Character.bag.addItem(oldItem);
					ItemDraw.this.m_Character.equipment.setBoots((Item_Boots)iBtn.getItem());
				}else if (iBtn.getItem() instanceof Item_Bracers) {
					ItemDraw.this.m_Bracers.setItem((Item_Bracers) iBtn.getItem());
					Interface_Item oldItem = m_Character.equipment.getBracers();
					if (!isEmptyItem(oldItem))
						m_Character.bag.addItem(oldItem);
					ItemDraw.this.m_Character.equipment.setBracers((Item_Bracers)iBtn.getItem());
				}else if (iBtn.getItem() instanceof Item_Gloves) {
					ItemDraw.this.m_Gloves.setItem((Item_Gloves) iBtn.getItem());
					Interface_Item oldItem = m_Character.equipment.getGloves();
					if (!isEmptyItem(oldItem))
						m_Character.bag.addItem(oldItem);
					ItemDraw.this.m_Character.equipment.setGloves((Item_Gloves)iBtn.getItem());
				}else if (iBtn.getItem() instanceof Item_Shield) {
					ItemDraw.this.m_Shield.setItem((Item_Shield) iBtn.getItem());
					Interface_Item oldItem = m_Character.equipment.getShield();
					if (!isEmptyItem(oldItem))
						m_Character.bag.addItem(oldItem);
					ItemDraw.this.m_Character.equipment.setShield((Item_Shield)iBtn.getItem());
				}else {
					return false;
				}
				
				m_Character.bag.dropItem(m_Character.bag.getItemNum(iBtn.getItem()));

				ItemDraw.this.R.refreshItemButtons(m_Character.bag.getItems(), ItemDraw.this.m_ItemButtons);
				cInfoZone.refreshCharacterInfos();

				ItemDraw.this.refreshEquipmentButton();
				ItemDraw.this.m_Canves.repaint();
			}
			return true;
		}		
	}
		
	/**
	 * class to handling the command for the equipment zones
	 * @author Jingang.Li
	 *
	 */
	class EquipmentCommand implements ICommand {
		@Override
		public boolean execute(IClickable source,MouseEvent e) {
			if (e.getClickCount() == 2) {
				if (source == null)
					return false;
				
				ItemEquipmentButton<?> iBtn = (ItemEquipmentButton<?>) source;
				if (iBtn.getItem() == null)
					return true;
				
				if (iBtn.getItem() instanceof Item_Helmet) {					
					m_Character.bag.addItem(iBtn.getItem());
					m_Character.equipment.setHelmet(new Item_Helmet());
					iBtn.setItem(null);
				}else if (iBtn.getItem() instanceof Item_Armor) {
					m_Character.bag.addItem(iBtn.getItem());
					m_Character.equipment.setArmor(new Item_Armor());
					iBtn.setItem(null);
				}else if (iBtn.getItem() instanceof Item_Belt) {
					m_Character.bag.addItem(iBtn.getItem());
					m_Character.equipment.setBelt(new Item_Belt());
					iBtn.setItem(null);
				}else if (iBtn.getItem() instanceof Item_Weapons) {
					m_Character.bag.addItem(iBtn.getItem());
					m_Character.equipment.setWeapons(new Item_Weapons());
					iBtn.setItem(null);
				}else if (iBtn.getItem() instanceof Item_Ring) {
					m_Character.bag.addItem(iBtn.getItem());
					m_Character.equipment.setRing(new Item_Ring());
					iBtn.setItem(null);
				}else if (iBtn.getItem() instanceof Item_Boots) {
					m_Character.bag.addItem(iBtn.getItem());
					m_Character.equipment.setBoots(new Item_Boots());
					iBtn.setItem(null);
				}else if (iBtn.getItem() instanceof Item_Bracers) {
					m_Character.bag.addItem(iBtn.getItem());
					m_Character.equipment.setBracers(new Item_Bracers());
					iBtn.setItem(null);
				}else if (iBtn.getItem() instanceof Item_Gloves) {
					m_Character.bag.addItem(iBtn.getItem());
					m_Character.equipment.setGloves(new Item_Gloves());
					iBtn.setItem(null);
				}else if (iBtn.getItem() instanceof Item_Shield) {
					m_Character.bag.addItem(iBtn.getItem());
					m_Character.equipment.setShield(new Item_Shield());
					iBtn.setItem(null);
				}

				ItemDraw.this.R.refreshItemButtons(m_Character.bag.getItems(), ItemDraw.this.m_ItemButtons);	
				cInfoZone.refreshCharacterInfos();

				ItemDraw.this.m_Canves.repaint();
			}
			return true;
		}		
	}
	
	class ItemButtonCommand implements ICommand {
		@Override
		public boolean execute(IClickable source,MouseEvent e) {
			TextZone tz = (TextZone) source;
			String cmdTxt = tz.getText();
			if (cmdTxt.compareToIgnoreCase(R.TXT_ALL) == 0) {
				System.out.println("cmd All");
				ItemDraw.this.R.refreshItemButtons(ItemDraw.this.R.filterItems(utility.ItemResource.ItemFilter.eAll, m_Character.bag.getItems()), ItemDraw.this.m_ItemButtons);
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_ITEM) == 0) {
				System.out.println("cmd Item");
				ItemDraw.this.R.refreshItemButtons(ItemDraw.this.R.filterItems(utility.ItemResource.ItemFilter.eItem, m_Character.bag.getItems()), ItemDraw.this.m_ItemButtons);
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_ARMOR) == 0) {
				System.out.println("cmd Armor");
				ItemDraw.this.R.refreshItemButtons(ItemDraw.this.R.filterItems(utility.ItemResource.ItemFilter.eArmor, m_Character.bag.getItems()), ItemDraw.this.m_ItemButtons);
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_WEAPON) == 0) {
				System.out.println("cmd Weapon");
				ItemDraw.this.R.refreshItemButtons(ItemDraw.this.R.filterItems(utility.ItemResource.ItemFilter.eWeapon, m_Character.bag.getItems()), ItemDraw.this.m_ItemButtons);
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_SAVE) == 0) {
				System.out.println("cmd Save");
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_QUIT) == 0) {
				System.out.println("cmd quit");
				ItemDraw.this.m_Canves.switchTo(ItemDraw.this.m_ParentUI.getInterfaceType(), ItemDraw.this);
			} else {
				return false;
			}
					
			ItemDraw.this.m_Canves.repaint();
			return true;
		}		
	}

	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			Zone z = m_Zone.HitTest(e);
			if (z == null) {
				System.out.printf("NOT hit on Zone");
			} else {				
					z.execute(z,e);
					System.out.printf("Location: %s\n", z.getLocation().toString());
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		if (m_Zone != null)
			m_Zone.draw(g);		
	}

	@Override
	public MouseAdapter getMouseAdapter() {
		return new MyMouseAdapter();
	}

	@Override
	public void AttachTo(Canvas mc) {
		for (MouseListener l : mc.getMouseListeners()) {
			mc.removeMouseListener(l);
		}		
		
		mc.addMouseListener(getMouseAdapter());
		
		for (MouseMotionListener l : mc.getMouseMotionListeners()) {
			mc.removeMouseMotionListener(l);
		}	
	}
	
	@Override
	public UserInterfaceType getInterfaceType() {
		return UserInterfaceType.ITEM_UI;
	}
}

