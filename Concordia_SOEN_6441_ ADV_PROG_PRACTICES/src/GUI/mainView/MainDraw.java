package GUI.mainView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.Frame;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

import Data.dataIO.MapLoad;
import GUI.battleView.BattleDraw;
import GUI.characterView.CharacterDraw;
import GUI.mapView.MapDraw;
import utility.IDrawableUI;
import utility.IMouseClickSupport;
import utility.TextZone;
import utility.UserInterfaceType;
import utility.Zone;
/**
 * this is class to draw the main view 
 *
 */
public class MainDraw implements IDrawableUI, IMouseClickSupport {

	private ImageIcon imgicon;
	private Image img1;
	private Image img2;
	private Image img3;
	private MainCanvas c;
	
	/**
	 * constructor MainDraw
	 * @param c
	 */
	public MainDraw(MainCanvas c){
		this.c=c;
		imgicon = new ImageIcon("./res/GUI/main_left.png");
	    img1 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/main_right.png");
	    img2 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/main_down.png");
	    img3 = imgicon.getImage();
	}
	
	/**
	 * paint the main view
	 */
	public void paint(Graphics g) {			
		drawMain(g);				
	}

	private void drawMain(Graphics g) {
		g.drawImage(img1,0,0,700,800,c); 
		g.drawImage(img2,700,0,324,150,c); 
		g.drawLine(1, 0, 900, 0); 
	    Graphics2D g2 = (Graphics2D )g;
		g.drawImage(img3,700,150,324,650,c); 
		 
		g2.setColor(Color.BLACK);
	
		g2.setStroke(new BasicStroke(4));
		
		
		
		g2.drawRect(750, 200, 250,100);
		g2.drawRect(750, 350, 250,100);
		g2.drawRect(750, 500, 250,100);
		g2.drawRect(750, 650, 250,100);
		g2.drawImage(new ImageIcon("./res/GUI/button1.jpg").getImage(), 750, 200, 250,100, null);
		g2.drawImage(new ImageIcon("./res/GUI/button2.jpg").getImage(), 750, 350, 250,100, null);
		g2.drawImage(new ImageIcon("./res/GUI/button3.jpg").getImage(), 750, 500, 250,100, null);
		g2.drawImage(new ImageIcon("./res/GUI/button4.jpg").getImage(), 750, 650, 250,100, null);
		//g2.setColor(new Color(0,0,204));
		Font f1 =new Font("ss",Font.BOLD,35);
		g2.setFont(f1);
		g2.setColor(Color.LIGHT_GRAY);
		g2.drawString("PRODUCERS",450,50 );
		g2.setColor(Color.RED);
		g2.drawString("@ Team 8",520,245 );
		Font f2 =new Font("ss",Font.BOLD,25);
		g2.setFont(f2);
		g2.setColor(Color.WHITE);
		g2.drawString("________________",450,60 );
		g2.drawString("JINGANG LI",470,110 );
		g2.drawString("DAN ZHANG",470,140 );
		g2.drawString("LIN ZHU",470,170 );
		g2.drawString("XIN SHAO",470,200 );


		
		Font f =new Font("ss",Font.BOLD,40);
		g2.setColor(new Color(204,0,0));
		g2.setFont(f);
		

		g2.drawString("Create Map",770,260 );
		g2.drawString("Create Role",765,410 );
		g2.drawString("Battle",820,560 );
		g2.drawString("Exit Game",780,710 );
		g.dispose();
	}
	
	/**
	 * inner class used for mouse event listener
	 *
	 */
	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			if (mx > 750 && mx < 1000) {
				if (my > 200 && my < 300) {
					c.switchTo(UserInterfaceType.MAP_UI,MainDraw.this);
					c.repaint();
				} else if (my > 350 && my < 450) {
					c.switchTo(UserInterfaceType.CHARACTER_UI,MainDraw.this);
					c.repaint();
				}
				else if (my > 500 && my < 600) {
					c.switchTo(UserInterfaceType.BATTLE_UI,MainDraw.this);
					c.repaint();
				}
				else if (my > 650 && my < 750) {
					System.out.println("4 " + mx + " " + my + "\n");
					c.quit();					
				}				
			}
		}
	}
	
	@Override
	/**
	 * attach mouse to this adapter 
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
	 * attach mouse to this adapter
	 */
	public MouseAdapter getMouseAdapter() {
		return new MyMouseAdapter();
	}

	@Override
	public UserInterfaceType getInterfaceType() {
		return UserInterfaceType.MAIN_UI;
	}
}
