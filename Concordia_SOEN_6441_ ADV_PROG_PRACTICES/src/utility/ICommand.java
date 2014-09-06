package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.event.MouseEvent;

/**
 * this interface is used to abstract the command need to be 
 * attached to the UI components and can be executed when it 
 * is needed.
 */
public interface ICommand {
	/**
	 * when the mouse is clicked, the ICommand can be 
	 * invoked by the owner (source) and the event e
	 * indicate the mouse's information such as button 
	 * informations.
	 * @param source
	 * @param e
	 * @return
	 */
	boolean execute(IClickable source, MouseEvent e);
}
