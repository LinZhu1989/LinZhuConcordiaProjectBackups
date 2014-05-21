package GUI.chestView;
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

import Data.Character.GameCharacter;
import Data.Character.GameFixedItem;
import Data.Item.Interface_Item;
import GUI.itemView.ItemDraw;
import GUI.mainView.MainCanvas;
import GUI.mainView.MainFrame;
import utility.IClickable;
import utility.ICommand;
import utility.IDrawableUI;
import utility.IMouseClickSupport;
import utility.ItemButton;
import utility.TextZone;
import utility.UserInterfaceType;
import utility.Zone;

/**
 * This is the class to represent the open chest operation.
 * 
 * @author Jingang.Li
 * 
 */
public class ChestDraw implements IDrawableUI, IMouseClickSupport {
	private ChestDrawResource R = new ChestDrawResource();
	private Zone m_Zone;
	private MainCanvas m_Canves;

	// the parent UI
	IDrawableUI m_ParentUI;

	private ArrayList<ItemButton> m_ChestItemButtons;
	private ArrayList<ItemButton> m_RoleItemButtons;	
	private ArrayList<ItemButtonCommand> m_CommandButtons;

	private GameCharacter m_Character;
	private GameFixedItem m_Chest;

	public ChestDraw(MainFrame frame, MainCanvas mainCanvas,
			GameCharacter characterChest, GameFixedItem currentChest,
			IDrawableUI whereFrom) {
		this.m_ParentUI = whereFrom;
		this.m_Character = characterChest;
		this.m_Chest = currentChest;
		this.m_Canves = mainCanvas;
		Insets insets = frame.getInsets();
		Rectangle rec = frame.getBounds();
		int w = rec.width - insets.left;
		int h = rec.height - insets.top;
	
		Point loc = new Point(0,0);
		m_Zone = new Zone(loc, w, h, null, "");
		m_Zone.setImageName("./res/GUI/item/chestBackground.jpg");
		m_ChestItemButtons = new ArrayList<ItemButton>();
		m_RoleItemButtons = new ArrayList<ItemButton>();
		 m_CommandButtons = new ArrayList<ItemButtonCommand>();
		
		loc = initItemButtons(w,loc, m_ChestItemButtons);
		loc = initItemCommandButtons(w,loc,m_CommandButtons);
		loc = initItemButtons(w,loc, m_RoleItemButtons);
		refreshCharacterInforToUI();
	}

	private void refreshCharacterInforToUI() {
		if (this.m_Character == null) {
			ChestDraw.this.m_Canves.repaint();
			return;	
		}
		
		if (m_Chest != null) {
			this.R.refreshItemButtons(this.R.filterItems(
					utility.ItemResource.ItemFilter.eAll,
					m_Chest.getFixedItem()), this.m_ChestItemButtons);			
		}
		
		if (m_Character != null) {
			this.R.refreshItemButtons(this.R.filterItems(
					utility.ItemResource.ItemFilter.eAll,
					m_Character.bag.getItems()), this.m_RoleItemButtons);			
		}
		
		ChestDraw.this.m_Canves.repaint();
	}	
	
	public void setParentUI(IDrawableUI parentUI) {
		if (parentUI != null)
			this.m_ParentUI = parentUI;
	}

	public void setCharacter(GameCharacter currentCharacter) {
		this.m_Character = currentCharacter;		
	}
	
	public void setChest(GameFixedItem currentChest) {
		this.m_Chest = currentChest;
	}	

	class ItemButtonCommand implements ICommand {
		@Override
		public boolean execute(IClickable source,MouseEvent e) {
			TextZone tz = (TextZone) source;
			String cmdTxt = tz.getText();
			if (cmdTxt.compareToIgnoreCase(R.TXT_ALL) == 0) {
				System.out.println("cmd All");
				ChestDraw.this.R.refreshItemButtons(ChestDraw.this.R.filterItems(utility.ItemResource.ItemFilter.eAll, m_Chest.getFixedItem()), ChestDraw.this.m_ChestItemButtons);
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_ITEM) == 0) {
				System.out.println("cmd Item");
				ChestDraw.this.R.refreshItemButtons(ChestDraw.this.R.filterItems(utility.ItemResource.ItemFilter.eItem,m_Chest.getFixedItem()), ChestDraw.this.m_ChestItemButtons);
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_ARMOR) == 0) {
				System.out.println("cmd Armor");
				ChestDraw.this.R.refreshItemButtons(ChestDraw.this.R.filterItems(utility.ItemResource.ItemFilter.eArmor, m_Chest.getFixedItem()), ChestDraw.this.m_ChestItemButtons);
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_WEAPON) == 0) {
				System.out.println("cmd Weapon");
				ChestDraw.this.R.refreshItemButtons(ChestDraw.this.R.filterItems(utility.ItemResource.ItemFilter.eWeapon, m_Chest.getFixedItem()), ChestDraw.this.m_ChestItemButtons);
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_SAVE) == 0) {
				System.out.println("cmd Save");
			} else if (cmdTxt.compareToIgnoreCase(R.TXT_QUIT) == 0) {
				System.out.println("cmd quit");
				ChestDraw.this.m_Canves.switchTo(ChestDraw.this.m_ParentUI.getInterfaceType(), ChestDraw.this);
			} else {
				return false;
			}
			
			ChestDraw.this.m_Canves.repaint();
			return true;
		}		
	}	
	
	private Point initItemCommandButtons(int w, Point loc,ArrayList<ItemButtonCommand> buttons) {
		Point bottom = new Point();
		int tzWidth = (w - (R.BUTTON_TEXT.length + 1) * R.ITEM_WIDTH_GAP) / R.BUTTON_TEXT.length; 
		for (int i = 0; i < R.BUTTON_TEXT.length; ++i) {			
			int tzx = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + tzWidth) * i ;
			TextZone tz = new TextZone(new Point(tzx, loc.y),tzWidth, R.BUTTON_HEIGHT,m_Zone,"");
			tz.setText(R.BUTTON_TEXT[i]);
			tz.attachCommand(new ItemButtonCommand());
			tz.setTextHeight(22);
			tz.setBackgroundImage(R.bottonBackground);
			m_Zone.addZone(tz);
			bottom = tz.getLocation();
			bottom.y +=  + tz.getHeight();
		}
		
		bottom.y += R.ITEM_WIDTH_GAP;
		
		//make sure the bottom locate at the head of the row.
		bottom.x = 0;
		return bottom;		
	}	
	
	private Point initItemButtons(int w, Point loc, ArrayList<ItemButton> buttons) {
		// add the Item buttons
		Point bottom = new Point();
		int itemWidth = (w - ((R.ITEM_COL_COUNT + 1) * R.ITEM_WIDTH_GAP))
				/ (R.ITEM_COL_COUNT);
		for (int i = 0; i < R.ITEM_COL_COUNT; ++i) {
			int x = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + itemWidth) * i;

			for (int j = 0; j < R.ITEM_ROW_COUNT; j++) {
				int y = R.ITEM_WIDTH_GAP + (R.ITEM_WIDTH_GAP + itemWidth) * j;
				Zone z = null;

				ItemButton btn = new ItemButton(new Point(x + loc.x, y + loc.y), itemWidth,
						itemWidth, m_Zone, "");
				btn.attachCommand(new ItemCommand());
				btn.setTextHeight(20);
				buttons.add(btn);
				z = btn;

				m_Zone.addZone(z);
				bottom = z.getLocation();
				bottom.y += z.getHeight();
			}
		}
		
		bottom.y += R.ITEM_WIDTH_GAP ;
		
		//make sure the bottom locate at the head of the row.
		bottom.x = 0;
		return bottom;
	}

	/**
	 * class to handling the command for the Item zones
	 * 
	 * @author Jingang.Li
	 * 
	 */
	class ItemCommand implements ICommand {

		@Override
		public boolean execute(IClickable source, MouseEvent e) {
			if (e.getClickCount() == 2) {
				if (source == null)
					return false;

				ItemButton iBtn = (ItemButton) source;
				if (iBtn.getItem() == null)
					return true;
				else {
					
					if (ChestDraw.this.m_Chest == null ||
						ChestDraw.this.m_Character == null)
						return false;
					
					if (ChestDraw.this.m_ChestItemButtons.contains(iBtn)) {
						Interface_Item item = iBtn.getItem();
						ChestDraw.this.m_Chest.removeItem(item);
						ChestDraw.this.m_Character.bag.addItem(item);
						iBtn.setItem(null);
						ChestDraw.this.refreshCharacterInforToUI();
						return true;
					} else if (ChestDraw.this.m_RoleItemButtons.contains(iBtn)) {
						Interface_Item item = iBtn.getItem();
						ChestDraw.this.m_Chest.addItem(item);
						ChestDraw.this.m_Character.bag.dropItem(m_Character.bag.getItemNum(item));
						iBtn.setItem(null);
						ChestDraw.this.refreshCharacterInforToUI();
						return true;
					}
					return false;
				}
			}
			return false;
		}
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

	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			Zone z = m_Zone.HitTest(e);
			if (z == null) {
				System.out.printf("NOT hit on Zone");
			} else {
				z.execute(z, e);
				System.out.printf("Location: %s\n", z.getLocation().toString());
			}
		}
	}

	@Override
	public MouseAdapter getMouseAdapter() {
		return new MyMouseAdapter();
	}

	@Override
	public void paint(Graphics g) {
		if (m_Zone != null)
			m_Zone.draw(g);
	}

	@Override
	public UserInterfaceType getInterfaceType() {
		return UserInterfaceType.CHEST_UI;
	}

}
