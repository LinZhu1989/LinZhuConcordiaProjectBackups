package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.Graphics;

/**
 * this interface is used to define the user draw UI
 */
public interface IDrawableUI extends IEventHandlerSupport {
	/**
	 * callback for drawing the UI
	 * @param g
	 */
	void paint(Graphics g);
	
	UserInterfaceType getInterfaceType();
}
