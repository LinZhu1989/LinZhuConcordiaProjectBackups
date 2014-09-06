package GUI.mainView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 * main frame for the whole GUI
 *
 */
public class MainFrame extends JFrame {
	static final int MAIN_UI_WIDTH = 1024;
	static final int MAIN_UI_HEIGHT = 800;
	static final String MAIN_ICON = "./res/GUI/d&d_icon.png";
	
	private ImageIcon imgicon;
	private Image img;
	public MainCanvas mc;
	
	/**
	 * constructor of MainFrame
	 */
	public MainFrame() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setSize(MAIN_UI_WIDTH, MAIN_UI_HEIGHT);
		mc = new MainCanvas(this);
		add(mc);

		imgicon = new ImageIcon(MAIN_ICON);
		img = imgicon.getImage();
		setIconImage(img);
		setResizable(false);
	
		setTitle("D&D");
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
