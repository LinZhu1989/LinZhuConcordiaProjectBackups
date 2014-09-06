package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.event.MouseEvent;
/**
 * this Interface is used to define the Mouse click check interface.
 * the object which need the mouse click check should implement this 
 * interface
 */
public interface IClickable {
	/**
	 * return the zone that the mouse clicked in it.
	 * otherwise return the null.
	 * @param e
	 * @return
	 */
	Zone HitTest(MouseEvent e);
}
