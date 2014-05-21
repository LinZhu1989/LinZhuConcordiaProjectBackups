package GUI.battleView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.event.MouseEvent;

public class BattleListener {
	protected BattleDraw bd;
	protected BattleCommandListener bcl;
	public BattleBoardListener bbl;
	private BattleLogListener bll;
	protected BattleResource R;
	protected boolean onMove;
	public boolean onPath;
	public boolean onAttack;
	/**
	 * constructor of battle listener
	 * @param bd
	 */
	public BattleListener(BattleDraw bd) {
		
		bbl = new BattleBoardListener(this);
		bll = new BattleLogListener();
		this.bd= bd;
		this.R=bd.R;
		bcl = new BattleCommandListener(this);
		onMove=false;
		onPath=false;
		onAttack=false;
	}
	
	/**
	 * listener of mouse click
	 * @param e
	 */
	public void mouseClickedListener(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(e.getButton() == MouseEvent.BUTTON1){
		switch (checkArea(mx, my)) {
		case 1:
			bbl.boardAction(mx, my);
			break;
		case 2:
			bcl.commandAction(mx, my);
			break;
		case 3:
			bll.logAction(mx, my,bd);
			break;
		}
		}
		else{
			switch (checkArea(mx, my)) {
			case 1:
				bbl.boardActionLeftClick(mx, my);
				break;
			
			}
		}
	}
	
	/**
	 * check the mouse in which area
	 * @param mx
	 * @param my
	 * @return
	 */
	private int checkArea(int mx, int my) {
		if(mx<800&&my<600)
			return 1;
		else if(mx>800)
			return 2;
		else 
			return 3;
		
	}
}
