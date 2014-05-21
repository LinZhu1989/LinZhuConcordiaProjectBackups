package GUI.mainView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

import Data.Character.GameCharacter;
import Data.Character.GameFixedItem;
import Data.dataIO.MapLoad;
import GUI.battleView.BattleDraw;
import GUI.battleView.BattleListener;
import GUI.characterView.CharacterDraw;
import GUI.chestView.ChestDraw;
import GUI.itemView.ItemDraw;
import GUI.mapView.MapDraw;
import utility.UserInterfaceType;
import utility.IDrawableUI;
/**
 * this is the controller of the main frame
 *
 */
public class MainCanvas extends Canvas {

	private static final String MAIN_UI_IMAGE = "./res/GUI/main.png";
	IDrawableUI m_CurrentUI;
	IDrawableUI m_MainUI;
	IDrawableUI m_CharacterUI;
	IDrawableUI m_BattleUI;
	IDrawableUI m_MapUI;
	IDrawableUI m_ItemUI;
	IDrawableUI m_ChestUI;
	
	MainFrame frame;
	
	private Image canvasBuffer;
	Graphics cg;
	private ImageIcon imgicon;

	MainCanvas(MainFrame f){		
		frame = f;
		
		imgicon = new ImageIcon(MAIN_UI_IMAGE);
		canvasBuffer = imgicon.getImage();
		
		m_CurrentUI = null;
		m_MainUI = null;
		m_CharacterUI = null;
		m_BattleUI = null;
		m_MapUI = null;
		m_ItemUI = null;
		m_ChestUI = null;
		
		switchTo(UserInterfaceType.MAIN_UI,null);				
	}
	
	/**
	 * this is the method to close the window
	 */
	public void quit() {
		WindowEvent windowClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
		frame.dispatchEvent(windowClosing);		
	}
	
	/**
	 * switch to different UI view
	 * @param uType
	 * @param whereFrom
	 */
	public void switchTo(UserInterfaceType uType, IDrawableUI whereFrom) {
		frame.setVisible(false);		
		switch(uType) {
		case MAIN_UI:
			if (m_MainUI == null) {
			m_MainUI = new MainDraw(this);
			}
			m_CurrentUI = m_MainUI;
			break;
		case CHARACTER_UI:
			if (m_CharacterUI == null) {
			m_CharacterUI = new CharacterDraw(frame,this);
			}			

			m_CurrentUI = m_CharacterUI;
			CharacterDraw cd2 = (CharacterDraw) m_CharacterUI;
			cd2.setParentUI(whereFrom);
			
			if(whereFrom.getInterfaceType()==UserInterfaceType.BATTLE_UI){
				
				GameCharacter battleCharacter = null;
				BattleDraw bd = (BattleDraw) whereFrom;
				battleCharacter = bd.getCurrentCharacter();
				cd2.setRoleFromBattle(battleCharacter,bd);
			}
			cd2.updateUI();
			break;
		case MAP_UI:
			if (m_MapUI == null) {
			m_MapUI = new MapDraw(this);
			}
			m_CurrentUI = m_MapUI;
			break;
		case BATTLE_UI:
			if (m_BattleUI == null) {
				m_BattleUI = new BattleDraw(this);
			}
			m_CurrentUI = m_BattleUI;
			break;
		case ITEM_UI:
			GameCharacter character = null;
			if (whereFrom != null) {
				if (whereFrom instanceof CharacterDraw) {
				CharacterDraw cd = (CharacterDraw) whereFrom;
				character = cd.getCurrentCharacter();
				} else if (whereFrom instanceof BattleDraw) {
					BattleDraw bd = (BattleDraw) whereFrom;
					character = bd.getCurrentCharacter();
				}
			}			
			if (m_ItemUI == null) {
				m_ItemUI = new ItemDraw(frame,this,character,whereFrom);
			} else {
				ItemDraw itemUI = (ItemDraw) m_ItemUI;
				itemUI.setParentUI(whereFrom);
				itemUI.setCharacter(character);
			}
			m_CurrentUI = m_ItemUI;			
			break;
		case CHEST_UI:
			GameCharacter currentRole = null;
			GameFixedItem currentChest = null;
			if (whereFrom != null) {
				if (whereFrom instanceof BattleDraw) {
					BattleDraw bd = (BattleDraw) whereFrom;
					currentRole = bd.getCurrentCharacter();
					currentChest = bd.getCurrentFixedItem();
				}
			}			
			if (m_ChestUI == null) {
				m_ChestUI = new ChestDraw(frame,this,currentRole,currentChest,whereFrom);
			} else {
				ChestDraw chestUI = (ChestDraw) m_ChestUI;
				chestUI.setParentUI(whereFrom);
				chestUI.setCharacter(currentRole);
				chestUI.setChest(currentChest);
			}
			m_CurrentUI = m_ChestUI;	
			break;
		default:
			m_CurrentUI = new MainDraw(this);
		}
		
		if (m_CurrentUI != null) {
			m_CurrentUI.AttachTo(this);		
		}
		frame.setVisible(true);
	}
	
	/**
	 * update the canvas
	 */
	public void update(Graphics g){
		canvasBuffer =createImage(this.getWidth(), this.getHeight());
		cg = canvasBuffer.getGraphics();
		paint(cg);
		cg.dispose();
		g.drawImage(canvasBuffer,0,0,this);		
	}
	
	/**
	 * paint the canvas
	 */
	public void paint(Graphics g) {			
		if (m_CurrentUI != null)
			m_CurrentUI.paint(g);
	}
}


	
