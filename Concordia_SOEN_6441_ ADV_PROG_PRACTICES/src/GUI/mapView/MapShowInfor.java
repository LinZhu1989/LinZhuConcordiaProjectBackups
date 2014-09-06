package GUI.mapView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
/**
 * show the map information
 *
 */
public class MapShowInfor {

	private boolean onShow;
	private int x;
	private int y;
	private String content;
	private Canvas c;
	private Image img;
	private Image previous;
	private Graphics g;
	public MapShowInfor(Canvas c){
		onShow =false;
		x=0;
		y=0;
		this.c =c;
	}
	
	/**
	 * load the map information
	 * @param x
	 * @param y
	 * @param content
	 * @param img
	 */
	public void loadInfor(int x,int y,String content,Image img){
		this.x =x;
		this.y= y;
		this.content =content;
		this.img = img;
		calculateRect();
		onShow = true;
	}
	
	/**
	 * draw the map information
	 * @param g
	 */
	public void drawInfor(Graphics g){
		previous= c.createImage(1024, 800);
		g.drawImage(img, x,y, 300, 300, c);
		int localY=y;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		 for (String line : content.split("\n"))
			 g2.drawString(line, x, localY+=g.getFontMetrics().getHeight());
		
		this.g=g;
		
	}
	
	/**
	 * repaint the map
	 */
	public void stopInfor(){
		onShow = false;
		c.repaint();
		
	}
	
	/**
	 * show the information on screen
	 * @return
	 */
	public boolean onShow(){
		return onShow;
	}

	private void calculateRect(){
		if(1024-x<300) //Left Space is not enough,change right
		x-=300;
		if(800-y<300)// Down Space is not enough, change up
		y-=300;
	}
}
