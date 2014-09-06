package GUI.characterView;
/**
 * @author DanZhang
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import java.awt.Point;

import GUI.itemView.CharacterInfoLable;
import utility.InfoZone;
import utility.TextZone;
import utility.Zone;
/**
 * this is the class define the GUI data 
 *
 */
public class CharacterResource {
	protected Image imgCharacterView;
	protected Image imgCharacterView2;
	protected Image imgAttack;
	protected Image imgDexterity;
	protected Image imgPhysicalArmor;
	protected Image imgMagicResist;
	protected Image imgAttackDistance;
	protected Image imgMoveDistance;
	protected Image imgStrength;
	protected Image imgConstitution;
	protected Image imgIntelligence;
	protected Image imgWisdom;
	protected Image imgCharisma;
	protected Image imgArrowDown;
	protected Image imgArrowUp;

	protected Image imgHp;
	protected Image imgMp;
	protected ImageIcon imgicon;
	protected Character hero;
	
	protected int descriptionHeight;
	protected int descriptionWidth;
	protected int descriptionX;
	protected int descriptionStringX;	
	protected int strengthY; 
	protected int dexterityY; 
	protected int constitutionY; 
	protected int intelligenceY;
	protected int wisdomY; 
	protected int charismaY; 
	protected int basicAtkY;
	protected int basicPhysicalArmorY;
	protected int basicMagicResistY;
	protected int basicMoveDistanceY;
	protected int basicAttackDistanceY;
	
	protected int nameX;
	protected int nameY;
	protected int numbX; 
	protected int numbStrengthY; 
	protected int numbDexterityY; 
	protected int numbConstitutionY; 
	protected int numbIntelligenceY;
	protected int numbWisdomY; 
	protected int numbCharismaY; 
	protected int numbBasicAtkY;
	protected int numbBasicPhysicalArmorY;
	protected int numbBasicMagicResistY;
	protected int numbBasicMoveDistanceY;
	protected int numbBasicAttackDistanceY;
	protected int numbHPX;
	protected int numbHPY;
	protected int numbMPX;
	protected int numbMPY;
	
	protected int arrowNumWidth; 
	protected int arrowNumHeight; 
	protected int arrowX; 
	protected int arrowUpStrengthY; 
	protected int arrowUpDexterityY; 
	protected int arrowUpConstitutionY; 
	protected int arrowUpIntelligenceY;
	protected int arrowUpWisdomY; 
	protected int arrowUpCharismaY; 
	protected int arrowUpBasicAtkY;
	protected int arrowUpBasicPhysicalArmorY;
	protected int arrowUpBasicMagicResistY;
	protected int arrowUpBasicMoveDistanceY;
	protected int arrowUpBasicAttackDistanceY;
	protected int arrowDownStrengthY; 
	protected int arrowDownDexterityY; 
	protected int arrowDownConstitutionY; 
	protected int arrowDownIntelligenceY;
	protected int arrowDownWisdomY; 
	protected int arrowDownCharismaY; 
	protected int arrowDownBasicAtkY;
	protected int arrowDownBasicPhysicalArmorY;
	protected int arrowDownBasicMagicResistY;
	protected int arrowDownBasicMoveDistanceY;
	protected int arrowDownBasicAttackDistanceY;
	
	
	protected int arrowDownX;
	protected int arrowDownY;
	protected int arrowUpY;
	protected int arrowUpX;
	protected int arrowHeight;
	protected int arrowWidth;
	protected int heroX;
	protected int heroY;
	protected int heroWidth;
	protected int heroHeight;
	
	protected int rectCreateX;
	protected int rectCreateY;
	protected int rectSaveX;
	protected int rectSaveY;
	protected int rectLoadX;
	protected int rectLoadY;
	protected int rectExitX;
	protected int rectExitY;
	protected int rectCommandWidth;
	protected int rectCommandHeight;
	protected int rectCommandBlank;
	protected int newDwarfX;
	protected int newDwarfY;
	protected int newElfX;
	protected int newElfY;
	protected int newWitchX;
	protected int newWitchY;
	protected int newHeroWidth;
	protected int newHeroHeight;
	protected int newOKX;
	protected int newOKY;
	
	protected int frameVerticalX;
	protected int frameVerticalY;
	protected int frameHorizonal1X;
	protected int frameHorizonal1Y;
	protected int frameHorizonal2X;
	protected int frameHorizonal2Y;
	protected int frameHorizonal3X;
	protected int frameHorizonal3Y;
	protected int frameHorizonal4X;
	protected int frameHorizonal4Y;
	protected int frameVerticalWidth;
	protected int frameVerticalHeight;
	protected int frameHorizonalWidth;
	protected int frameHorizonalHeight;
	protected Image imgFrameVertical;
	protected Image imgFrameHorizonal;
	
	
	protected int skillPointX;
	protected int skillPointY;
	protected int skillPointNumX;
	protected int skillPointNumY;
	protected int skillPointWidth;
	protected int skillPointHeight;
	protected int AttributePointX;
	protected int AttributePointY;
	protected int AttributePointNumX;
	protected int AttributePointNumY;
	protected int AttributePointWidth;
	protected int AttributePointHeight;
	
	protected Image imgSkillDwarf1_1;
	protected Image imgSkillDwarf1_2;
	protected Image imgSkillDwarf1_3;
	protected Image imgSkillDwarf2_1;
	protected Image imgSkillDwarf2_2;
	protected Image imgSkillDwarf2_3;
	protected Image imgSkillDwarf3_1;
	protected Image imgSkillDwarf3_2;
	protected Image imgSkillDwarf3_3;
	
	protected Image imgSkillElf1_1;
	protected Image imgSkillElf1_2;
	protected Image imgSkillElf1_3;
	protected Image imgSkillElf2_1;
	protected Image imgSkillElf2_2;
	protected Image imgSkillElf2_3;
	protected Image imgSkillElf3_1;
	protected Image imgSkillElf3_2;
	protected Image imgSkillElf3_3;
	
	protected Image imgSkillWitch1_1;
	protected Image imgSkillWitch1_2;
	protected Image imgSkillWitch1_3;
	protected Image imgSkillWitch2_1;
	protected Image imgSkillWitch2_2;
	protected Image imgSkillWitch2_3;
	protected Image imgSkillWitch3_1;
	protected Image imgSkillWitch3_2;
	protected Image imgSkillWitch3_3;
	protected Image imgSkillArrow;
	
	protected int skill1_1X;
	protected int skill1_2X;
	protected int skill1_3X;
	protected int skill2_1X;
	protected int skill2_2X;
	protected int skill2_3X;
	protected int skill3_1X;
	protected int skill3_2X;
	protected int skill3_3X;
	
	protected int skill1_1Y;
	protected int skill1_2Y;
	protected int skill1_3Y;
	protected int skill2_1Y;
	protected int skill2_2Y;
	protected int skill2_3Y;
	protected int skill3_1Y;
	protected int skill3_2Y;
	protected int skill3_3Y;
	
	
	protected int skill1_1ArrowUpX;
	protected int skill1_2ArrowUpX;
	protected int skill1_3ArrowUpX;
	protected int skill2_1ArrowUpX;
	protected int skill2_2ArrowUpX;
	protected int skill2_3ArrowUpX;
	protected int skill3_1ArrowUpX;
	protected int skill3_2ArrowUpX;
	protected int skill3_3ArrowUpX;
	
	protected int skill1_1ArrowUpY;
	protected int skill1_2ArrowUpY;
	protected int skill1_3ArrowUpY;
	protected int skill2_1ArrowUpY;
	protected int skill2_2ArrowUpY;
	protected int skill2_3ArrowUpY;
	protected int skill3_1ArrowUpY;
	protected int skill3_2ArrowUpY;
	protected int skill3_3ArrowUpY;
	
	protected int skill1_1ArrowDownX;
	protected int skill1_2ArrowDownX;
	protected int skill1_3ArrowDownX;
	protected int skill2_1ArrowDownX;
	protected int skill2_2ArrowDownX;
	protected int skill2_3ArrowDownX;
	protected int skill3_1ArrowDownX;
	protected int skill3_2ArrowDownX;
	protected int skill3_3ArrowDownX;
	
	protected int skill1_1ArrowDownY;
	protected int skill1_2ArrowDownY;
	protected int skill1_3ArrowDownY;
	protected int skill2_1ArrowDownY;
	protected int skill2_2ArrowDownY;
	protected int skill2_3ArrowDownY;
	protected int skill3_1ArrowDownY;
	protected int skill3_2ArrowDownY;
	protected int skill3_3ArrowDownY;
	
	protected int skillBlank;
	protected int skillHeight;
	protected int skillWidth;
	protected int skillArrowBlank;
	protected int skillArrowHeight;
	protected int skillArrowWidth;
	
	protected Image imgRole_1_G;
	protected Image imgRole_2_G;
	protected Image imgRole_3_G;
	protected int hpY;
	protected int hpX;
	protected int hpWidth;
	protected int hpHeight;
	protected int blank;
	protected int itemX;
	protected int itemY;
	public TextZone zone;
	public InfoZone charInfor;
	/**
	 * constructor for CharacterResource
	 */
	public CharacterResource(){
	
		initImg();
		initWidthHeight();
		initCommand();
		initSkillTree();
		initPoint();
		intZone();
	}
	
	private void intZone() {
		// TODO Auto-generated method stub
		zone = new TextZone(new Point(0,0),250,800,null,"");
		zone.setTextColor(Color.RED);
		charInfor = new InfoZone(zone);
	}

	/**
	 *  available point value
	 */
	private void initPoint() {
		skillPointX=800; 
		skillPointY=5;
		skillPointNumX=950;
		skillPointNumY=5;
		skillPointWidth=60;
		skillPointHeight=40;
		
		
		AttributePointX =250;
		AttributePointY =5;
		AttributePointNumX =400;
		AttributePointNumY=5;
		AttributePointWidth =70;
		AttributePointHeight=40;
		itemX =AttributePointNumX+300;
		itemY =AttributePointNumY;
		nameY=AttributePointY+AttributePointHeight;
		nameX=AttributePointX+AttributePointWidth+250;
	}
	
	/**
	 * initiate skill tree
	 */
	private void initSkillTree() {
		frameVerticalWidth=10;
		frameVerticalHeight=600;
		frameVerticalX=250;
		frameVerticalY=50;
		
		frameHorizonalWidth=800;
		frameHorizonalHeight=10;
		frameHorizonal1X =frameVerticalX;
		frameHorizonal1Y =frameVerticalY;
		frameHorizonal2X =frameVerticalX;
		frameHorizonal2Y =frameVerticalY+200;
		frameHorizonal3X =frameVerticalX;
		frameHorizonal3Y =frameVerticalY+400;
		frameHorizonal4X =frameVerticalX;
		frameHorizonal4Y =frameVerticalY+600;
		
		skillBlank =150;
		skillWidth=120;
		skillHeight=100;
		
		skill1_1X = frameVerticalX+60;
		skill1_2X = skill1_1X+skillWidth+skillBlank;
		skill1_3X = skill1_2X+skillWidth+skillBlank;
		skill2_1X = skill1_1X;
		skill2_2X = skill1_2X;
		skill2_3X = skill1_3X;
		skill3_1X = skill1_1X;
		skill3_2X = skill1_2X;
		skill3_3X = skill1_3X;
		
		skill1_1Y = frameHorizonal1Y+55;
		skill1_2Y = skill1_1Y;
		skill1_3Y = skill1_1Y;
		skill2_1Y = frameHorizonal2Y+55;
		skill2_2Y = skill2_1Y;
		skill2_3Y = skill2_1Y;
		skill3_1Y = frameHorizonal3Y+55;
		skill3_2Y = skill3_1Y;
		skill3_3Y = skill3_1Y;
		
		skillArrowBlank=40;
		skillArrowWidth=40;
		skillArrowHeight=40;
	
		skill1_1ArrowUpX = skill1_1X+skillArrowBlank;
		skill1_2ArrowUpX = skill1_2X+skillArrowBlank;
		skill1_3ArrowUpX = skill1_3X+skillArrowBlank;
		skill2_1ArrowUpX = skill2_1X+skillArrowBlank;
		skill2_2ArrowUpX = skill2_2X+skillArrowBlank;
		skill2_3ArrowUpX = skill2_3X+skillArrowBlank;
		skill3_1ArrowUpX = skill3_1X+skillArrowBlank;
		skill3_2ArrowUpX = skill3_2X+skillArrowBlank;
		skill3_3ArrowUpX = skill3_3X+skillArrowBlank;
		
		skill1_1ArrowUpY = skill1_1Y+skillHeight;
		skill1_2ArrowUpY = skill1_2Y+skillHeight;
		skill1_3ArrowUpY = skill1_3Y+skillHeight;
		skill2_1ArrowUpY = skill2_1Y+skillHeight;
		skill2_2ArrowUpY = skill2_2Y+skillHeight;
		skill2_3ArrowUpY = skill2_3Y+skillHeight;
		skill3_1ArrowUpY = skill3_1Y+skillHeight;
		skill3_2ArrowUpY = skill3_2Y+skillHeight;
		skill3_3ArrowUpY = skill3_3Y+skillHeight;
		
		skill1_1ArrowDownX = skill1_1X+2*skillArrowBlank;
		skill1_2ArrowDownX = skill1_2X+2*skillArrowBlank;
		skill1_3ArrowDownX = skill1_3X+2*skillArrowBlank;
		skill2_1ArrowDownX = skill2_1X+2*skillArrowBlank;
		skill2_2ArrowDownX = skill2_2X+2*skillArrowBlank;
		skill2_3ArrowDownX = skill2_3X+2*skillArrowBlank;
		skill3_1ArrowDownX = skill3_1X+2*skillArrowBlank;
		skill3_2ArrowDownX = skill3_2X+2*skillArrowBlank;
		skill3_3ArrowDownX = skill3_3X+2*skillArrowBlank;
		
		skill1_1ArrowDownY = skill1_1Y+skillHeight;
		skill1_2ArrowDownY = skill1_2Y+skillHeight;
		skill1_3ArrowDownY = skill1_3Y+skillHeight;
		skill2_1ArrowDownY = skill2_1Y+skillHeight;
		skill2_2ArrowDownY = skill2_2Y+skillHeight;
		skill2_3ArrowDownY = skill2_3Y+skillHeight;
		skill3_1ArrowDownY = skill3_1Y+skillHeight;
		skill3_2ArrowDownY = skill3_2Y+skillHeight;
		skill3_3ArrowDownY = skill3_3Y+skillHeight;
		
		
	}
	
	/**
	 * initiate command
	 */
	private void initCommand(){
		rectCommandBlank=50;
		
		rectCommandWidth =150;
		rectCommandHeight =50;	
		
	
		rectLoadX =710;
		rectLoadY =basicAttackDistanceY-20 ;
		rectExitX =rectLoadX+rectCommandWidth;
	    rectExitY =rectLoadY ;
		rectCreateX= rectLoadX;
		rectSaveX  =rectExitX;
		
		rectCreateY=rectExitY-rectCommandHeight;
		rectSaveY=rectCreateY;
		
		newHeroWidth=100;
		newHeroHeight=50;
		newDwarfX=250;
		newDwarfY=rectCreateY+30;
		newElfX=newDwarfX+40+newHeroWidth;
		newElfY=newDwarfY;
		newWitchX=newElfX+40+newHeroWidth;
		newWitchY=newDwarfY;
		newOKX=newWitchX+20+newHeroWidth;
		newOKY =newWitchY;
	}
	
	/**
	 * initiate widthHeight
	 */
	private void initWidthHeight(){
		
		blank=100;
		heroX=0;
		heroY=0;
		heroWidth=200;
		heroHeight=150;
		hpY=150;
		hpX=descriptionX+100;
		hpWidth=100;
		hpHeight=15;
		arrowHeight=60;
		arrowWidth=40;
		
		arrowUpY=70-arrowHeight;
		arrowUpX=200;
		
		arrowDownY =80;
		arrowDownX=200;
		
		descriptionX=25;
		descriptionStringX=10;
		descriptionHeight=40;
		descriptionWidth=40;
		int temp;
		strengthY= 180; 
		
		int blank=15;
		temp=strengthY+blank;
		dexterityY= temp+descriptionHeight; 
		temp+=descriptionHeight+blank;
		constitutionY=temp+descriptionHeight;  
		temp+=descriptionHeight+blank;
		wisdomY=temp+descriptionHeight;
		temp+=descriptionHeight+blank;
		intelligenceY=temp+descriptionHeight;
		temp+=descriptionHeight+blank;
		charismaY=temp+descriptionHeight;
		temp+=descriptionHeight+blank;
		basicAtkY=temp+descriptionHeight; 
		temp+=descriptionHeight+blank;
		basicPhysicalArmorY=temp+descriptionHeight;
		temp+=descriptionHeight+blank;
		basicMagicResistY=temp+descriptionHeight;
		temp+=descriptionHeight+blank;
		basicMoveDistanceY=temp+descriptionHeight;
		temp+=descriptionHeight+blank;
		basicAttackDistanceY=temp+descriptionHeight;
		
		numbX =descriptionStringX+descriptionWidth+100; 
		numbStrengthY =strengthY+descriptionHeight-5; 
		numbDexterityY=dexterityY+descriptionHeight-5; 
		numbConstitutionY=constitutionY+descriptionHeight-5; 
		numbIntelligenceY=intelligenceY+descriptionHeight-5; 
		numbWisdomY=wisdomY+descriptionHeight-5; 
		numbCharismaY=charismaY+descriptionHeight-5; 
		numbBasicAtkY=basicAtkY+descriptionHeight-5; 
		numbBasicPhysicalArmorY=basicPhysicalArmorY+descriptionHeight-5; 
		numbBasicMagicResistY=basicMagicResistY+descriptionHeight-5; 
		numbBasicMoveDistanceY=basicMoveDistanceY+descriptionHeight-5; 
		numbBasicAttackDistanceY=basicAttackDistanceY+descriptionHeight-5; 
		
		arrowNumWidth=20; 
		arrowNumHeight=20; 
		arrowX=210; 
		arrowUpStrengthY=strengthY; 
		arrowUpDexterityY=dexterityY; 
		arrowUpConstitutionY=constitutionY; 
		arrowUpIntelligenceY=intelligenceY;
		arrowUpWisdomY=wisdomY; 
		arrowUpCharismaY=charismaY; 
		arrowUpBasicAtkY=basicAtkY;
		arrowUpBasicPhysicalArmorY=basicPhysicalArmorY;
		arrowUpBasicMagicResistY=basicMagicResistY;
		arrowUpBasicMoveDistanceY=basicMoveDistanceY;
		arrowUpBasicAttackDistanceY=basicAttackDistanceY;
		
		arrowDownStrengthY=strengthY+arrowNumHeight; 
		arrowDownDexterityY=dexterityY+arrowNumHeight; 
		arrowDownConstitutionY=constitutionY+arrowNumHeight;  
		arrowDownIntelligenceY=intelligenceY+arrowNumHeight; 
		arrowDownWisdomY=wisdomY+arrowNumHeight; 
		arrowDownCharismaY=charismaY+arrowNumHeight; 
		arrowDownBasicAtkY=basicAtkY+arrowNumHeight; 
		arrowDownBasicPhysicalArmorY=basicPhysicalArmorY+arrowNumHeight; 
		arrowDownBasicMagicResistY=basicMagicResistY+arrowNumHeight; 
		arrowDownBasicMoveDistanceY=basicMoveDistanceY+arrowNumHeight; 
		arrowDownBasicAttackDistanceY=basicAttackDistanceY+arrowNumHeight; 
		
		
	
		
		numbHPX=hpX-25+hpWidth+30;
		numbHPY=hpY+hpHeight;
		numbMPX=hpX-25+hpWidth+30;
		numbMPY=hpY+2*hpHeight;
	}
	
	/**
	 * initiate image
	 */
	private void initImg() {
		imgicon = new ImageIcon("./res/GUI/imgCharacterView.png"); // Draw Sand
		imgCharacterView = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgCharacterView2.jpg"); // Draw Sand
		imgCharacterView2 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgAttack.png"); // Draw Sand
		imgAttack= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgPhysicalArmor.png"); // Draw Sand
		imgPhysicalArmor= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgMagicResist.png"); // Draw Sand
		imgMagicResist= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgAttackDistance.png"); // Draw Sand
		imgAttackDistance= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgMoveDistance.png"); // Draw Sand
		imgMoveDistance= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgStrength.png"); // Draw Sand
		imgStrength= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgConstitution.png"); // Draw Sand
		imgConstitution= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgIntelligence.png"); // Draw Sand
		imgIntelligence= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgWisdom.png"); // Draw Sand
		imgWisdom= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgCharisma.png"); // Draw Sand
		imgCharisma= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgDexterity.png"); // Draw Sand
		imgDexterity= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgArrowUp.png"); // Draw Sand
		imgArrowUp= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgArrowDown.png"); // Draw Sand
		imgArrowDown= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/dwarf_1.png");
		imgRole_1_G = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Elf_1.png");
		imgRole_2_G = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Witch_1.png");
		imgRole_3_G = imgicon.getImage();
	
		
		imgicon = new ImageIcon("./res/GUI/imgHp.png");
		imgHp = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgMp.png");
		imgMp = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgVertical.png");
		imgFrameVertical = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgHorizonal.png");
		imgFrameHorizonal = imgicon.getImage();
	
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillDwarf1_1.png");
		imgSkillDwarf1_1= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillDwarf1_2.png");
		imgSkillDwarf1_2= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillDwarf1_3.png");
		imgSkillDwarf1_3= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillDwarf2_1.png");
		imgSkillDwarf2_1= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillDwarf2_2.png");
		imgSkillDwarf2_2= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillDwarf2_3.png");
		imgSkillDwarf2_3= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillDwarf3_1.png");
		imgSkillDwarf3_1= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillDwarf3_2.png");
		imgSkillDwarf3_2= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillDwarf3_3.png");
		imgSkillDwarf3_3= imgicon.getImage();
		
		
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillElf1_1.png");
		imgSkillElf1_1= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillElf1_2.png");
		imgSkillElf1_2= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillElf1_3.png");
		imgSkillElf1_3= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillElf2_1.png");
		imgSkillElf2_1= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillElf2_2.png");
		imgSkillElf2_2= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillElf2_3.png");
		imgSkillElf2_3= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillElf3_1.png");
		imgSkillElf3_1= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillElf3_2.png");
		imgSkillElf3_2= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillElf3_3.png");
		imgSkillElf3_3= imgicon.getImage();
		
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillWitch1_1.png");
		imgSkillWitch1_1= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillWitch1_2.png");
		imgSkillWitch1_2= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillWitch1_3.png");
		imgSkillWitch1_3= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillWitch2_1.png");
		imgSkillWitch2_1= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillWitch2_2.png");
		imgSkillWitch2_2= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillWitch2_3.png");
		imgSkillWitch2_3= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillWitch3_1.png");
		imgSkillWitch3_1= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillWitch3_2.png");
		imgSkillWitch3_2= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillWitch3_3.png");
		imgSkillWitch3_3= imgicon.getImage();
		
		imgicon = new ImageIcon("./res/GUI/Skill/imgSkillArrow.png");
		imgSkillArrow= imgicon.getImage();
		
	}

}
