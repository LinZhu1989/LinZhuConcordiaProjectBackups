package GUI.battleView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
public class BattleLogListener {
	/**
	 * this is the area to show the log
	 * @param mx
	 * @param my
	 */
	public void logAction(int mx, int my,BattleDraw bd) {
		if(mx>bd.battleLog.arrowDownX&&mx<bd.battleLog.arrowDownX+bd.battleLog.arrowNumWidth)
		{
			//System.out.println("here");
			if(my>bd.battleLog.arrowUpY&&my<bd.battleLog.arrowUpY+bd.battleLog.arrowNumHeight)
			{
				//System.out.println("up");
				bd.battleLog.arrowUp();
			}
			else if(my>bd.battleLog.arrowDownY&&my<bd.battleLog.arrowDownY+bd.battleLog.arrowNumHeight)
			{
				bd.battleLog.arrowDown();
				//System.out.println("down");
			}
			bd.c.repaint();
		}
	}

	

}
