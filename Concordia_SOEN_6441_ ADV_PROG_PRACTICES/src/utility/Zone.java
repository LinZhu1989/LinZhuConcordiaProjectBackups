package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;


/**
 * This is the class define the Zone
 * A zone is a rectangular area at a location and has width and height 
 * This class uses the composite design pattern
 */
public class Zone implements IDrawable, IClickable, ICommand {
	private Point mLocation;//the top-left point of the zone
	private int mWidth;//size in X direction
	private int mHeight;//size in Y direction
	
	private Zone mParent;//the parent of the Zone.
	private List <Zone> mChildren;//the children of the zone.
	
	private ICommand mCommand;
	private ImageIcon mImg;
	
	public Zone() {
		this(new Point(0,0),0,0,null,"");		
	}
	
	public Zone(Point pt, int w, int h, Zone parent, String imgName) {
		setWidth(w);
		setHeight(h);
		setLocation(pt);
		setParent(parent);
		mChildren = new ArrayList <Zone>();
		this.mCommand = null;
		setImageName(imgName);
	}
	
	public void setImageName(String imgName) {
		if (!imgName.isEmpty())
			mImg = new ImageIcon(imgName);
		else
			mImg = null;
	}
	
	/**
	 * add zone z to the children of the Zone
	 * @param z the target zone.
	 * @return true for succeed false otherwise.
	 */
	public boolean addZone(Zone z) {
		if (z == null)
			return false;
		
		if (mChildren.contains(z))
			return false;
		
		if (mChildren == null){
			mChildren = new ArrayList<Zone>();
		}
		mChildren.add(z);
		return true;
	}
	
	/**
	 * attach the command to this zone
	 * @param handler
	 */
	public void attachCommand(ICommand handler) {
		if (handler != this)
			this.mCommand = handler;
	}
	
	/**
	 * Invoke the attached command if any.
	 */
	public boolean execute(IClickable source, MouseEvent e) {
		if (mCommand == null)
			return false;
		mCommand.execute(this, e);
		return true;
	}
	
	/**
	 * @return the list of the children of current zone.
	 */
	public List<Zone> getSubZones() {
		return mChildren;
	}
	
	/**
	 * return the height of the Zone
	 * @return
	 */
	public int getHeight() {
		return mHeight;
	}
	
	/**
	 * set the Height of the Zone
	 * @param h
	 */
	public void setHeight(int h) {
		if (h < 0) {
			h = 0;
		}
		this.mHeight = h;
	}
	
	/**
	 * return the width of the Zone
	 * @return
	 */
	public int getWidth() {
		return mWidth;
	}
	
	/**
	 * set the Width of the Zone
	 * @param w
	 */
	public void setWidth(int w) {
		if (w < 0) {
			w = 0;
		}
		this.mWidth = w;
	}
	
	public Point getLocation() {
		return new Point(mLocation);
	}
	
	public void setLocation(Point loc) {
		this.mLocation = loc;
	}
	
	protected Point calculateTopLeft() {
		Point result = this.getLocation();
		Zone upper = this.mParent;
		
		while (upper != null) {
			Point loc = upper.getLocation();
			result.x += loc.x;
			result.y += loc.y;
			upper = upper.getParent();
		}		
		return result;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Point pt = calculateTopLeft();

		if (this.mImg != null) {			
			g.drawImage(mImg.getImage(),pt.x,pt.y,this.getWidth(),this.getHeight(), null);			
		}
		
		g2.setColor(this.getBorderColor());
		
		if (getBorderWidth() != 0) {
			g2.setStroke(new BasicStroke(getBorderWidth()));
			g2.drawRect(pt.x, pt.y, this.getWidth(), this.getHeight());				
		}

		for (Zone z : mChildren) {
			z.draw(g);
		}
	}

	public int getBorderWidth() {
		return 2;
	}

	@Override
	public Color getBorderColor() {
		return new Color(0,0,0);
	}

	/**
	 * @return the mContainerZone, the parent of the Zone
	 * null means the top Zone
	 */
	private Zone getParent() {
		return mParent;
	}

	/**
	 * @param parent the mContainerZone to set
	 */
	private void setParent(Zone parent) {
		this.mParent = parent;
	}

	/**
	 * check if the mouse is clicked inside the zone.
	 * and always return the lowest level zone.
	 */
	@Override
	public Zone HitTest(MouseEvent e) {
		Zone result = null;
		for (Zone z : mChildren) {
			result = z.HitTest(e);
			if (result != null)
				return result;
		}
		
		Point pt = calculateTopLeft();
		Rectangle rec = new Rectangle(pt.x,pt.y, this.getWidth(),this.getHeight());
		if (rec.contains(e.getPoint())) {
			return this;
		}			
		else {
			return null;
		}
	}

	public void setColorWhite() {
		// TODO Auto-generated method stub
		
	}
}
