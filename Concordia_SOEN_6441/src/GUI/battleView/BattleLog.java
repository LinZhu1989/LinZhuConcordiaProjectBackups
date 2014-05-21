package GUI.battleView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import GUI.mainView.MainCanvas;

public class BattleLog {

	public int arrowDownY;
	public int arrowNumHeight;
	public int arrowUpY;
	public int arrowDownX;
	public int arrowUpX;
	public int arrowNumWidth;
	private ArrayList<String> logInfor;
	private int logPosition;
	private MainCanvas mainCanvas;
	private Image imgUp;
	private Image imgDown;
	private Image imgBackGround;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int sequence;
	public BattleLog(MainCanvas mainCanvas,int startX,int startY,int endX,int endY) {
		arrowNumHeight=50;
		arrowDownY=endY-arrowNumHeight-25;
	
		arrowUpY=startY+20;
		arrowNumWidth=50;
		arrowUpX=arrowDownX=endX-arrowNumWidth;
		sequence=1;
		this.mainCanvas=mainCanvas;
		logPosition=0;
		ImageIcon imgicon = new ImageIcon("./res/GUI/imgArrowUp.png"); // Draw Sand
		imgUp= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgArrowDown.png"); // Draw Sand
		imgDown= imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/black.png"); // Draw Sand
		imgBackGround= imgicon.getImage();
		
		logInfor = new  ArrayList<String>();
		logInfor.add("WELCOME TO D2D BATTLE ");
		logInfor.add("LOAD MAP FIRST ");
		logInfor.add("THEN LOAD CHARACTER  ");
		logInfor.add("ALL INFORMATION SHOWED HERE ");
		logInfor.add("ENJOY ");
		logPosition=0;
		this.startX= startX;
		this.startY= startY+18;
		this.endX=endX;
		this.endY=endY;
	}
	
	/**
	 * arrow down 
	 */
	public void arrowDown() {
		logPosition++;
		if(logPosition==logInfor.size()-4)
			logPosition--;
	}

	/**
	 * arrow up
	 */
	public void arrowUp() {
		logPosition--;
		if(logPosition<0)
			logPosition++;
	}

	/**
	 * draw the battle
	 * @param g
	 */
	public void draw(Graphics g) {
		g.drawImage(imgBackGround,startX,startY, endX-startX, endY-startY-18, mainCanvas);
		g.drawImage(imgUp,arrowUpX,arrowUpY, arrowNumWidth, arrowNumHeight, mainCanvas);
		g.drawImage(imgDown,arrowDownX,arrowDownY, arrowNumWidth, arrowNumHeight, mainCanvas);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.RED);
		int position=30;
		for(int i=logPosition;i<logPosition+5;i++){
			g2.drawString(logInfor.get(i), startX+50, startY+position);
			position+=32;
		}
	}

	/**
	 * set log information
	 * @param temp
	 */
	public void setLogInfor(String temp) {
		
		String[] datas;
		datas = temp.split("\n");
		
		for (int i = 0; i < datas.length; ++i) {
			logInfor.add(sequence+"  . " + datas[i]);
			logPosition++;
			mainCanvas.repaint();
			sequence++;
		}
		System.out.println(temp);
	}

}
