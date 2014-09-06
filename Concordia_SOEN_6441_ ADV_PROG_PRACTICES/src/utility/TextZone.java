package utility;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;


/**
 * this is the zone can contain a text
 */
public class TextZone  extends Zone {
	
	private String mText;
	private Color mTextColor;
	private Image mBackground;
	private int mTextSize;
	private Justify mJustify;
	
	public enum Justify {
		CENTER,
		LEFT
	}
	
	public TextZone(Point pt, int w, int h, Zone parent,String imgName) {
		super(pt,w,h,parent,imgName);
		this.mText = "";
		this.mTextColor = new Color(0,0,0);
		this.mTextSize = 18;
		this.mJustify = Justify.CENTER;
	}
	
	public void setJustify(Justify j) {
		mJustify = j;
	}

	public String getText() {
		return mText;
	}

	public void setText(String mText) {
		this.mText = mText;
	}	
	
	public void setTextHeight(int size) {
		mTextSize = size;
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		Graphics2D g2 = (Graphics2D) g;
		Point pt = calculateTopLeft();	
		g2.setColor(this.getTextColor());
		Font f =new Font("ss",Font.BOLD, mTextSize);
		g2.setFont(f);

        int width = this.getWidth();
        int height = this.getHeight();

        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics metrics = f.getLineMetrics(this.getText(), frc);
        float messageWidth =
            (float)f.getStringBounds(this.getText(), frc).getWidth();        
        
        float descent = metrics.getDescent();
        float x = 0;        
        float y = 0;
        
        if (this.mJustify == Justify.CENTER) {
			x = (width - messageWidth) / 2;
			y = (height + metrics.getHeight()) / 2 - descent;        	
        } else {
			x = 0;
			y = (height + metrics.getHeight()) / 2 - descent;        	
        }
   	
		g2.drawImage(mBackground, pt.x, pt.y, width, height,  null);
		g2.drawString(this.getText(),pt.x + x, pt.y + y);
	}

	public Color getTextColor() {
		return mTextColor;
	}

	public void setTextColor(Color mTextColor) {
		this.mTextColor = mTextColor;
	}
	
	public void setBackgroundImage(Image background) {
		this.mBackground = background;
	}
}
