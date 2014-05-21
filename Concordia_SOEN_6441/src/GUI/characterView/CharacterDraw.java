package GUI.characterView;

/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import utility.IDrawableUI;
import utility.IMouseClickSupport;
import utility.UserInterfaceType;
import Data.Character.GameCharacter;
import Data.GameData.CommonData;
import GUI.battleView.BattleLog;
import GUI.mainView.MainCanvas;

/**
 * this is the class drawing character view
 */
public class CharacterDraw implements IDrawableUI, IMouseClickSupport {

	protected CharacterResource R;
	private CharacterListener cl;
	private Frame frame;
	private JTextField userName;
	protected MainCanvas c;
	protected ArrayList<CharacterGUI> hero;
	protected int currenHero;
	protected boolean newCharacter;
	BattleLog battleLog;
	boolean logDraw;
	IDrawableUI m_ParentUI;

	/**
	 * this is the constructor of CharcterDraw
	 * 
	 * @param f
	 * @param c
	 */
	public CharacterDraw(Frame f, MainCanvas c) {

		this.frame = f;
		this.c = c;
		logDraw = false;
		R = new CharacterResource();
		cl = new CharacterListener(this);
		hero = new ArrayList<CharacterGUI>();
		for (int i = 1; i < 4; i++) {
			hero.add(new CharacterGUI(i, R, battleLog));
		}
		battleLog = new BattleLog(c, 250, 230, 1015, 470);
		currenHero = 0;
		newCharacter = false;
		R.charInfor.initCharacterInfoLabels(R.descriptionX, R.strengthY - 20, 300,
				600, hero.get(currenHero).getRole());
		R.charInfor.setTextColor(Color.red);
	}

	/**
	 * set parent UI
	 * 
	 * @param parentUI
	 */
	public void setParentUI(IDrawableUI parentUI) {
		if (parentUI != null)
			this.m_ParentUI = parentUI;
	}

	public void setRoleFromBattle(GameCharacter gc,IDrawableUI ui) {

		hero.add(new CharacterGUI(gc, R, battleLog,ui));
		currenHero = hero.size() -1;
		R.charInfor.setRole(hero.get(currenHero).getRole());
		R.charInfor.refreshCharacterInfos();
		hero.get(currenHero).transferString();
	}

	/**
	 * return the current editing Character
	 * 
	 * @return GameCharacter
	 */
	public GameCharacter getCurrentCharacter() {
		return this.hero.get(this.currenHero).getRole();
	}

	/**
	 * set the current character
	 * 
	 * @param GameCharacter
	 */
	public void setCurrentCharacter(GameCharacter character) {
		this.hero.get(this.currenHero).setRole(character);
	}

	/**
	 * update the UI
	 * 
	 * @return void
	 */
	public void updateUI() {
		this.hero.get(this.currenHero).transferString();
		this.c.repaint();
	}

	private void drawStatic(Graphics g) {
		if(m_ParentUI.getInterfaceType() == UserInterfaceType.BATTLE_UI){
			g.drawImage(R.imgCharacterView2, 0, 0, 1024, 800, c);
		}else{
			g.drawImage(R.imgCharacterView, 0, 0, 1024, 800, c);
		}

		// g.drawImage(R.imgAttack,R.descriptionX,R.basicAtkY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgDexterity,R.descriptionX,R.dexterityY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgPhysicalArmor,R.descriptionX,R.basicPhysicalArmorY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgMagicResist,R.descriptionX,R.basicMagicResistY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgAttackDistance,R.descriptionX,R.basicAttackDistanceY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgMoveDistance,R.descriptionX,R.basicMoveDistanceY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgStrength,R.descriptionX,R.strengthY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgConstitution,R.descriptionX,R.constitutionY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgIntelligence,R.descriptionX,R.intelligenceY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgWisdom,R.descriptionX,R.wisdomY,R.descriptionHeight,R.descriptionWidth,c);
		// g.drawImage(R.imgCharisma,R.descriptionX,R.charismaY,R.descriptionHeight,R.descriptionWidth,c);

		if (m_ParentUI.getInterfaceType() != UserInterfaceType.BATTLE_UI) {

			g.drawImage(R.imgArrowDown, R.arrowDownX, R.arrowDownY,
					R.arrowWidth, R.arrowHeight, c);
			g.drawImage(R.imgArrowUp, R.arrowUpX, R.arrowUpY, R.arrowWidth,
					R.arrowHeight, c);
			g.drawImage(R.imgArrowDown, R.arrowX, R.arrowDownStrengthY,
					R.arrowNumWidth, R.arrowNumHeight, c);
			g.drawImage(R.imgArrowUp, R.arrowX, R.arrowUpStrengthY,
					R.arrowNumWidth, R.arrowNumHeight, c);
			Graphics2D g2 = (Graphics2D) g;		
			Font f1 = new Font("ss", Font.ITALIC, 20);
			g2.setColor(Color.BLACK);
//			Font f1 = new Font("ss", Font.ITALIC, 15);
			g2.setFont(f1);
			g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_ROUND));
			g2.drawString("ITEM", R.itemX + 10, R.itemY
					+ R.AttributePointHeight - 10);
			g2.drawRect(R.itemX, R.itemY, R.AttributePointWidth,
					R.AttributePointHeight);
		}

		//
		// g.drawImage(R.imgHp,R.hpX,R.hpY,R.hpWidth,R.hpHeight,c);
		// g.drawImage(R.imgMp,R.hpX,R.hpY+R.hpHeight,R.hpWidth,R.hpHeight,c);

		// g2.setFont(f);
		// g2.setColor(Color.RED);
		// g2.drawString("Attack",R.descriptionStringX, R.basicAtkY);
		// g2.drawString("Dexterity", R.descriptionStringX, R.dexterityY);
		// g2.drawString("PhysicalArmor", R.descriptionStringX,
		// R.basicPhysicalArmorY);
		// g2.drawString("MagicResist",R.descriptionStringX,
		// R.basicMagicResistY);
		// g2.drawString("AttackDistance",R.descriptionStringX,
		// R.basicAttackDistanceY);
		// g2.drawString("MoveDistance", R.descriptionStringX,
		// R.basicMoveDistanceY);
		// g2.drawString("Strength", R.descriptionStringX, R.strengthY);
		// g2.drawString("Constitution",R.descriptionStringX, R.constitutionY);
		// g2.drawString("Intelligence", R.descriptionStringX, R.intelligenceY);
		// g2.drawString("Wisdom",R.descriptionStringX, R.wisdomY);
		// g2.drawString("Charisma",R.descriptionStringX, R.charismaY);
		// g2.drawString("HP",R.hpX-25,R.hpY+R.hpHeight);
		// g2.drawString("MP",R.hpX-25,R.hpY+2*R.hpHeight);
		//

		// g.drawImage(R.imgArrowDown,R.arrowX,R.arrowDownWisdomY,R.arrowNumWidth,R.arrowNumHeight,c);
		// g.drawImage(R.imgArrowUp,R.arrowX,R.arrowUpWisdomY,R.arrowNumWidth,R.arrowNumHeight,c);
		// g.drawImage(R.imgArrowDown,R.arrowX,R.arrowDownCharismaY,R.arrowNumWidth,R.arrowNumHeight,c);
		// g.drawImage(R.imgArrowUp,R.arrowX,R.arrowUpCharismaY,R.arrowNumWidth,R.arrowNumHeight,c);
		// g.drawImage(R.imgArrowDown,R.arrowX,R.arrowDownConstitutionY,R.arrowNumWidth,R.arrowNumHeight,c);
		// g.drawImage(R.imgArrowUp,R.arrowX,R.arrowUpConstitutionY,R.arrowNumWidth,R.arrowNumHeight,c);
		// g.drawImage(R.imgArrowDown,R.arrowX,R.arrowDownDexterityY,R.arrowNumWidth,R.arrowNumHeight,c);
		// g.drawImage(R.imgArrowUp,R.arrowX,R.arrowUpDexterityY,R.arrowNumWidth,R.arrowNumHeight,c);
		// g.drawImage(R.imgArrowDown,R.arrowX,R.arrowDownIntelligenceY,R.arrowNumWidth,R.arrowNumHeight,c);
		// g.drawImage(R.imgArrowUp,R.arrowX,R.arrowUpIntelligenceY,R.arrowNumWidth,R.arrowNumHeight,c);

	}

	private void drawCommand(Graphics g) {

		if (m_ParentUI.getInterfaceType() != UserInterfaceType.BATTLE_UI) {
			Graphics2D g2 = (Graphics2D) g;
			Font f = new Font("ss", Font.ITALIC, 25);
			g2.setColor(Color.BLACK);
			g2.setFont(f);
			g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_ROUND));
			g2.drawString("Create Role", R.rectCreateX + 5, R.rectCreateY + 25);
			g2.drawString("Load Role", R.rectLoadX + 5, R.rectLoadY + 25);
			g2.drawString("Save Role", R.rectSaveX + 5, R.rectSaveY + 25);
			g2.drawString("Back Main", R.rectExitX + 5, R.rectExitY + 25);

			g2.drawRect(R.rectCreateX, R.rectCreateY, R.rectCommandWidth,
					R.rectCommandHeight);
			g2.drawRect(R.rectLoadX, R.rectLoadY, R.rectCommandWidth,
					R.rectCommandHeight);
			g2.drawRect(R.rectSaveX, R.rectSaveY, R.rectCommandWidth,
					R.rectCommandHeight);
			g2.drawRect(R.rectExitX, R.rectExitY, R.rectCommandWidth,
					R.rectCommandHeight);
			if (newCharacter) {

				g2.drawRect(R.newDwarfX, R.newDwarfY, R.newHeroWidth,
						R.newHeroHeight);
				g2.drawRect(R.newElfX, R.newElfY, R.newHeroWidth,
						R.newHeroHeight);
				g2.drawRect(R.newWitchX, R.newWitchY, R.newHeroWidth,
						R.newHeroHeight);
				g2.drawRect(R.newOKX, R.newOKY, R.newHeroWidth / 2,
						R.newHeroHeight);

				g2.drawString(CommonData.CHARACTER_INFORMATION_FIGHTER_TYPES[0], R.newDwarfX + 5, R.newDwarfY + 25);
				g2.drawString(CommonData.CHARACTER_INFORMATION_FIGHTER_TYPES[1], R.newElfX + 5, R.newElfY + 25);
				g2.drawString(CommonData.CHARACTER_INFORMATION_FIGHTER_TYPES[2], R.newWitchX + 5, R.newWitchY + 25);
				g2.drawString("OK", R.newOKX + 5, R.newOKY + 25);

			}
		} else {
			Graphics2D g2 = (Graphics2D) g;
			Font f = new Font("ss", Font.ITALIC, 25);
			g2.setColor(Color.RED);
			g2.setFont(f);
			g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_ROUND));
			g2.drawString("Back Battle", R.rectExitX + 5, R.rectExitY + 25);
			g2.drawRect(R.rectExitX, R.rectExitY, R.rectCommandWidth,
					R.rectCommandHeight);
		}
	}

	private void drawHeroInfor(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		Font f = new Font("ss", Font.ITALIC, 20);
		
		g2.setFont(f);
		g2.setColor(Color.RED);
		g.drawImage(hero.get(currenHero).getImg(), R.heroX, R.heroY,
				R.heroWidth, R.heroHeight, c);
		// g2.drawString(hero.get(currenHero).basicAtkString,R.numbX,
		// R.numbBasicAtkY);
		// g2.drawString(hero.get(currenHero).dexterityString,
		// R.numbX,R.numbDexterityY);
		// g2.drawString(hero.get(currenHero).basicPhysicalArmorString,
		// R.numbX,R.numbBasicPhysicalArmorY);
		// g2.drawString(hero.get(currenHero).basicMagicResistString,R.numbX,
		// R.numbBasicMagicResistY);
		// g2.drawString(hero.get(currenHero).basicAttackDistanceString,R.numbX,R.numbBasicAttackDistanceY);
		// g2.drawString(hero.get(currenHero).basicMoveDistanceString, R.numbX,
		// R.numbBasicMoveDistanceY);
		// g2.drawString(hero.get(currenHero).strengthString,R.numbX,
		// R.numbStrengthY);
		// g2.drawString(hero.get(currenHero).constitutionString,R.numbX,
		// R.numbConstitutionY);
		// g2.drawString(hero.get(currenHero).intelligenceString,R.numbX,
		// R.numbIntelligenceY);
		// g2.drawString(hero.get(currenHero).wisdomString,R.numbX,R.numbWisdomY);
		// g2.drawString(hero.get(currenHero).charismaString,R.numbX,R.numbCharismaY);
		g2.drawString(hero.get(currenHero).heroName, R.nameX, R.nameY);
		// f = new Font("ss", Font.ITALIC, 15);
		// g2.setFont(f);
		// g2.drawString(hero.get(currenHero).HPString,R.numbHPX,R.numbHPY);
		// g2.drawString(hero.get(currenHero).MPString,R.numbMPX,R.numbMPY);
	}

	/**
	 * @overwrite canvas paint method
	 * @param g
	 */
	public void paint(Graphics g) {

		drawStatic(g);
		drawHeroInfor(g);
		drawCommand(g);
		drawSkillTree(g);
		// R.charInfor.setTextColor(Color.RED);
		R.zone.draw(g);
		
		drawPoint(g);
		
		
		if (logDraw) {
			battleLog.draw(g);
		}
		userName = new JTextField();
		userName.setBounds(new Rectangle(73, 115, 220, 25));
		frame.add(userName);
		frame.setVisible(true);

	}

	private void drawPoint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
//		Font f = new Font("ss", Font.ITALIC, 20);
//		g2.setColor(Color.BLACK);
//		g2.setFont(f);
//		g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_SQUARE,
//				BasicStroke.JOIN_ROUND));
//		g2.drawString("AttrPoint", R.AttributePointX, R.AttributePointY
//				+ R.AttributePointHeight);
//		g2.drawString("SkillPoint", R.skillPointX, R.skillPointY
//				+ R.skillPointHeight);
//		g2.drawString(hero.get(currenHero).attrPointString,
//				R.AttributePointNumX + 10, R.AttributePointNumY
//						+ R.AttributePointHeight - 5);
//		g2.drawString(hero.get(currenHero).skillPointString,
//				R.skillPointNumX + 10, R.skillPointNumY + R.skillPointHeight
//						- 5);
//
//		g2.drawRect(R.AttributePointNumX, R.AttributePointNumY,
//				R.AttributePointWidth, R.AttributePointHeight);
//		g2.drawRect(R.skillPointNumX, R.skillPointNumY, R.skillPointWidth,
//				R.skillPointHeight);
		
		if(m_ParentUI.getInterfaceType() != UserInterfaceType.BATTLE_UI){
			Font f = new Font("ss", Font.ITALIC, 40);
			g2.setColor(Color.RED);
			g2.setFont(f);
			g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_ROUND));
			g2.drawString("Create Character", R.AttributePointX, R.AttributePointY-10
					+ R.AttributePointHeight);
		}else{
		Font f = new Font("ss", Font.BOLD, 40);
		g2.setColor(Color.RED);
		g2.setFont(f);
		g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_ROUND));
		g2.drawString("Caracter View", R.AttributePointX, R.AttributePointY-10
				+ R.AttributePointHeight);
		}
		g2.setFont(new Font("ss", Font.BOLD, 20));

	}

	private void drawSkillTree(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Font f = new Font("ss", Font.BOLD, 20);
		g2.setColor(Color.BLACK);
		g2.setFont(f);
		g.drawImage(R.imgFrameVertical, R.frameVerticalX, R.frameVerticalY,
				R.frameVerticalWidth, R.frameVerticalHeight, c);
		g.drawImage(R.imgFrameHorizonal, R.frameHorizonal1X,
				R.frameHorizonal1Y, R.frameHorizonalWidth,
				R.frameHorizonalHeight, c);
		g.drawImage(R.imgFrameHorizonal, R.frameHorizonal2X,
				R.frameHorizonal2Y, R.frameHorizonalWidth,
				R.frameHorizonalHeight, c);
		g.drawImage(R.imgFrameHorizonal, R.frameHorizonal3X,
				R.frameHorizonal3Y, R.frameHorizonalWidth,
				R.frameHorizonalHeight, c);
		g.drawImage(R.imgFrameHorizonal, R.frameHorizonal4X,
				R.frameHorizonal4Y, R.frameHorizonalWidth,
				R.frameHorizonalHeight, c);

		g.drawImage(hero.get(currenHero).skill1_1, R.skill1_1X, R.skill1_1Y,
				R.skillWidth, R.skillHeight, c);
		g.drawImage(hero.get(currenHero).skill1_2, R.skill1_2X, R.skill1_2Y,
				R.skillWidth, R.skillHeight, c);
		g.drawImage(hero.get(currenHero).skill1_3, R.skill1_3X, R.skill1_3Y,
				R.skillWidth, R.skillHeight, c);

		g.drawImage(hero.get(currenHero).skill2_1, R.skill2_1X, R.skill2_1Y,
				R.skillWidth, R.skillHeight, c);
		g.drawImage(hero.get(currenHero).skill2_2, R.skill2_2X, R.skill2_2Y,
				R.skillWidth, R.skillHeight, c);
		g.drawImage(hero.get(currenHero).skill2_3, R.skill2_3X, R.skill2_3Y,
				R.skillWidth, R.skillHeight, c);

		g.drawImage(hero.get(currenHero).skill3_1, R.skill3_1X, R.skill3_1Y,
				R.skillWidth, R.skillHeight, c);
		g.drawImage(hero.get(currenHero).skill3_2, R.skill3_2X, R.skill3_2Y,
				R.skillWidth, R.skillHeight, c);
		g.drawImage(hero.get(currenHero).skill3_3, R.skill3_3X, R.skill3_3Y,
				R.skillWidth, R.skillHeight, c);

		// Draw String
		g2.setColor(Color.RED);
		g2.drawString(
				"Lv             " + hero.get(currenHero).skill1_1LvString,
				R.skill1_1X, R.skill1_1Y - 5);
		g2.drawString(
				"Lv             " + hero.get(currenHero).skill1_2LvString,
				R.skill1_2X, R.skill1_2Y - 5);
		g2.drawString(
				"Lv             " + hero.get(currenHero).skill1_3LvString,
				R.skill1_3X, R.skill1_3Y - 5);

		g2.drawString(
				"Lv             " + hero.get(currenHero).skill2_1LvString,
				R.skill2_1X, R.skill2_1Y - 5);
		g2.drawString(
				"Lv             " + hero.get(currenHero).skill2_2LvString,
				R.skill2_2X, R.skill2_2Y - 5);
		g2.drawString(
				"Lv             " + hero.get(currenHero).skill2_3LvString,
				R.skill2_3X, R.skill2_3Y - 5);

		g2.drawString(
				"Lv             " + hero.get(currenHero).skill3_1LvString,
				R.skill3_1X, R.skill3_1Y - 5);
		g2.drawString(
				"Lv             " + hero.get(currenHero).skill3_2LvString,
				R.skill3_2X, R.skill3_2Y - 5);
		g2.drawString(
				"Lv             " + hero.get(currenHero).skill3_3LvString,
				R.skill3_3X, R.skill3_3Y - 5);

		
		if(m_ParentUI.getInterfaceType() != UserInterfaceType.BATTLE_UI){
		// Draw Arrow
		g.drawImage(R.imgSkillArrow, R.skill1_1X + R.skillWidth + 10,
				R.skill1_1Y + R.skillHeight / 2 - 10, R.skillBlank - 20, 40, c);
		g.drawImage(R.imgSkillArrow, R.skill1_2X + R.skillWidth + 10,
				R.skill1_2Y + R.skillHeight / 2 - 10, R.skillBlank - 20, 40, c);
		g.drawImage(R.imgSkillArrow, R.skill2_1X + R.skillWidth + 10,
				R.skill2_1Y + R.skillHeight / 2 - 10, R.skillBlank - 20, 40, c);
		g.drawImage(R.imgSkillArrow, R.skill2_2X + R.skillWidth + 10,
				R.skill2_2Y + R.skillHeight / 2 - 10, R.skillBlank - 20, 40, c);
		g.drawImage(R.imgSkillArrow, R.skill3_1X + R.skillWidth + 10,
				R.skill3_1Y + R.skillHeight / 2 - 10, R.skillBlank - 20, 40, c);
		g.drawImage(R.imgSkillArrow, R.skill3_2X + R.skillWidth + 10,
				R.skill3_2Y + R.skillHeight / 2 - 10, R.skillBlank - 20, 40, c);

		// Draw Tiny Arrow
		g.drawImage(R.imgArrowUp, R.skill1_1ArrowUpX, R.skill1_1ArrowUpY,
				R.skillArrowWidth, R.skillArrowHeight, c);
		g.drawImage(R.imgArrowUp, R.skill1_2ArrowUpX, R.skill1_2ArrowUpY,
				R.skillArrowWidth, R.skillArrowHeight, c);
		g.drawImage(R.imgArrowUp, R.skill1_3ArrowUpX, R.skill1_3ArrowUpY,
				R.skillArrowWidth, R.skillArrowHeight, c);
		g.drawImage(R.imgArrowUp, R.skill2_1ArrowUpX, R.skill2_1ArrowUpY,
				R.skillArrowWidth, R.skillArrowHeight, c);
		g.drawImage(R.imgArrowUp, R.skill2_2ArrowUpX, R.skill2_2ArrowUpY,
				R.skillArrowWidth, R.skillArrowHeight, c);
		g.drawImage(R.imgArrowUp, R.skill2_3ArrowUpX, R.skill2_3ArrowUpY,
				R.skillArrowWidth, R.skillArrowHeight, c);
		g.drawImage(R.imgArrowUp, R.skill3_1ArrowUpX, R.skill3_1ArrowUpY,
				R.skillArrowWidth, R.skillArrowHeight, c);
		g.drawImage(R.imgArrowUp, R.skill3_2ArrowUpX, R.skill3_2ArrowUpY,
				R.skillArrowWidth, R.skillArrowHeight, c);
		g.drawImage(R.imgArrowUp, R.skill3_3ArrowUpX, R.skill3_3ArrowUpY,
				R.skillArrowWidth, R.skillArrowHeight, c);
		
			
		}else{
			
		}

	}

	/**
	 * this is the mouseListener for character view
	 * 
	 * @param e
	 */
	public void characterListener(MouseEvent e) {
		cl.mouseClickedListener(e);
	}

	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			cl.mouseClickedListener(e);
		}

	}

	@Override
	/**
	 * this is the method attach listener to mouse adapter
	 * @param mc
	 */
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
	/**
	 * this is mouse adapater for character view
	 * @return
	 */
	public MouseAdapter getMouseAdapter() {
		return new MyMouseAdapter();
	}

	@Override
	public UserInterfaceType getInterfaceType() {
		return UserInterfaceType.CHARACTER_UI;
	}
}
