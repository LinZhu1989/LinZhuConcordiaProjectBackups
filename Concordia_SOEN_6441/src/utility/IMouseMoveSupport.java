package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.event.MouseMotionAdapter;

/**
 * this interface is used to describe that the UI
 * supports the mouse move operation
 */
public interface IMouseMoveSupport extends IEventHandlerSupport {
	/**
	 * return the mouse motion adapter for the current 
	 * object.
	 * @return
	 */
	MouseMotionAdapter getMouseMotionAdapter();
}
