package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.Canvas;

/**
 * this interface is used to define the event
 * handler can be attached to the UI component
 */
public interface IEventHandlerSupport {
	/**
	 * attach the object to the Canvas
	 * @param mc
	 */
	void AttachTo(Canvas mc);
}
