package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.Color;
import java.awt.Graphics;

/**
 * This interface is used to define the region that can be 
 * drawn on the screen.
 */
public interface IDrawable {
	/**
	 * the callback for drawing the zone 
	 * @param g
	 */
	void draw(Graphics g);
	/***
	 * @return the border width
	 */
	int getBorderWidth();	
	/***
	 * 
	 * @return the border color 
	 */
	Color getBorderColor();
}
