/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.ActionHelper;

import java.awt.Point;
import java.util.ArrayList;

/**
 * the class MovableTarget helps the action of a character
 * 
 * @author Lin Zhu
 * 
 */
public class MovableTarget {
	private Point target;
	private ArrayList<Point> pathSet;

	/**
	 * Class MovableTarget Constructor (With no argument)
	 */
	public MovableTarget() {
		this.target = new Point();
		this.pathSet = new ArrayList<Point>(5);
	}

	/**
	 * get the targer
	 * 
	 * @return
	 */
	public Point getTarget() {
		return this.target;
	}

	/**
	 * get a point set of path
	 * 
	 * @return
	 */
	public ArrayList<Point> getPathSet() {
		return this.pathSet;
	}

	/**
	 * set a target with a point
	 * 
	 * @param t
	 */
	public void setTarget(Point t) {
		this.target = t;
	}

	/**
	 * add the path in front
	 * 
	 * @param t
	 */
	public void addPathInFront(Point t) {
		this.pathSet.add(0, t);
		;
	}

}
