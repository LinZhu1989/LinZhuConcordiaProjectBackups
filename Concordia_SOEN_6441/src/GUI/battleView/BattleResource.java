package GUI.battleView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Point;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Data.ActionHelper.MovableTarget;
import GUI.mapView.MapBlock;
import GUI.mapView.MapInfor;

public class BattleResource {
	protected ImageIcon imgicon;
	protected Image imgBoard;
	protected Image imgCommandBackground;
	protected Image imgLogBackground;
	protected Image imgFrameHorizonal;
	protected Image fire;
	protected Image blood;
	protected Image happy;
	
	protected int rectExitX;
	protected int rectExitY;
	protected int saveX;
	protected int saveY;
	protected int loadMapX;
	protected int loadMapY;
	protected int loadGameX;
	protected int loadGameY;
	
	protected int heroX;
	protected int heroY;
	protected int itemX;
	protected int itemY;	
	protected int moveX;
	protected int moveY;
	protected int restX;
	protected int restY;
	protected int attackX;
	protected int attackY;
	protected int skillX;
	protected int skillY;
	protected int YesX;
	protected int YesY;
	protected int NoX;
	protected int NoY;
	protected int choiceHeight;
	protected MapBlock board[][];
	protected int rectCommandWidth;
	protected int rectCommandHeight;
	protected int showBlockHeight;
	protected int showBlockWidth;
	//private BattleDraw bd;
	protected boolean mapDraw;
	public   MapInfor mapInfor;
	public boolean moveDraw;
	public ArrayList<MovableTarget> moveResult;
	public ArrayList<Point> path;
	public ArrayList<Point> attackBlks;
	public boolean pathDraw;
	protected static Integer moveLock;
	//public Boolean moveDone;
	//public Boolean roundDone;
	
	//true indicates the current move is done by the AI
	public Boolean aiMove;
	//public boolean playerDone;
	public boolean attackDraw;
	public boolean outDraw;
	
	public BattleResource(BattleDraw bd){
//		this.bd=bd;
		mapInfor=new MapInfor();
		initImg();
		initWidthHeight();		
	}
	
	/**
	 * initiate map initiate width and height
	 */
	private void initWidthHeight() {
//		moveDone=new Boolean(false);
//		roundDone=new Boolean(false);
		mapDraw=false;
		outDraw=false;
		moveDraw=false;
		pathDraw=false;
		attackDraw=false;
		aiMove=false;
		moveLock =new Integer(1);
//		playerDone=false;
		showBlockHeight=mapInfor.getBlockHeight()/5*3;
		showBlockWidth=mapInfor.getBlockWidth()/5*3;
		rectExitX =930;
	    rectExitY =680 ;
	    saveX=810;
	    saveY=rectExitY;
		rectCommandWidth =80;
		rectCommandHeight =50;	
		
		loadMapX=rectExitX;
		loadMapY=rectExitY-130;
		loadGameX=saveX;
		loadGameY=loadMapY;
		
		heroX =loadGameX ;
		heroY =loadGameY-rectCommandHeight-30;
		itemX =loadMapX;
		itemY =heroY;	
		
		moveX =loadGameX;
		moveY =heroY-rectCommandHeight-30;
		restX =loadMapX;
		restY =moveY;
		attackX =loadGameX;
		attackY=moveY-rectCommandHeight-30;
		skillX =loadMapX ;
		skillY =attackY ;
		
		YesX=attackX;
		YesY=250;
		NoX=loadMapX;
		NoY= YesY;
		choiceHeight=20;
		
	}
	
	/**
	 * initiate image
	 */
	private void initImg() {
		imgicon = new ImageIcon("./res/GUI/Battle/imgBoard.png"); // Draw Sand
		imgBoard = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Battle/imgCommandBackground.png"); // Draw Sand
		imgCommandBackground = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Battle/imgLogBackground.png"); // Draw Sand
		imgLogBackground = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Battle/imgHorizonal.png");
		imgFrameHorizonal = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Battle/Fire.png"); 
		fire = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Battle/blood.jpg"); 
		blood = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Battle/happy.jpg"); 
		happy = imgicon.getImage();

	}
}
