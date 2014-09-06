package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.event.MouseAdapter;

/**
 * this interface is used to describe the UI can use mouse to 
 * click on it 
 */
public interface IMouseClickSupport extends IEventHandlerSupport {
	/**
	 * return the mouse adapter for current object.
	 * @return
	 */
	MouseAdapter getMouseAdapter();
}
