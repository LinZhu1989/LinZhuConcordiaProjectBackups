package comp6721_D2_Final_Version;

import java.util.ArrayList;

/**
 * class GameTreeNode
 * 
 * COMP 472/672: Artificial Intelligence Project Deliverable II
 * 
 * @author Yulong Song 6516599 Lin Zhu 6555659
 * 
 */

public class GameTreeNode {

	private GameTreeNode parent;
	private ArrayList<GameTreeNode> childern;
	private String player;
	private int deepth;
	private GameBoard state;
	private int score;

	GameTreeNode(String myPlayer, GameBoard theState) {

		deepth = 0;
		parent = null;
		state = theState;
		childern = new ArrayList<GameTreeNode>(10);
		player = myPlayer;
	}

	GameTreeNode(String myPlayer, int deep, GameTreeNode myParent,
			GameBoard theState) {

		deepth = deep;
		parent = myParent;
		state = theState;
		childern = new ArrayList<GameTreeNode>(80);
		player = myPlayer;
	}

	public void addChild(GameTreeNode child) {
		childern.add(child);
	}

	public void setParent(GameTreeNode theParent) {
		parent = theParent;
	}

	public void setDeepth(int theDeepth) {
		deepth = theDeepth;
	}

	public void setState(GameBoard theState) {
		state = theState;
	}

	public void setScore(int theScore) {
		score = theScore;
	}

	public int getScore() {
		return score;
	}

	public int getDeepth() {
		return deepth;
	}

	public String getPlayerName() {
		return player;
	}

	public GameBoard getState() {

		return state;
	}

	public GameTreeNode getParent() {

		return parent;
	}

	public ArrayList<GameTreeNode> children() {

		return childern;
	}

	public boolean ifTerminal() {

		if (this.getState().calPossibleDirections(this.getPlayerName(), this.getState()) == 0) {
			return true;
		} else {
			return false;
		}

	}

	public ArrayList<GameTreeNode> getChildren() {

		int curDeepth = this.getDeepth();
		String curPlayerName = this.getPlayerName();
		String nextPlayerName = null;
		if (curPlayerName == "P1") {
			nextPlayerName = "P2";
		} else {
			nextPlayerName = "P1";
		}
		GameBoard tempBoard = this.getState().getDeepCopyBoard(this.getState());
		ArrayList<ArrayList<Integer>> moves = this.getState()
				.findPossibleMoves(curPlayerName, tempBoard);
		if(moves.size()==0){
			return null;
		}	
		for (int i = 0; i < moves.size(); i++) {
			this.addChild(new GameTreeNode(nextPlayerName, curDeepth + 1, this,
					tempBoard.boardMove(curPlayerName, tempBoard, moves.get(i)
							.get(0), moves.get(i).get(1), moves.get(i).get(2),
							moves.get(i).get(3))));
		}
		return this.children();
	}

//	public int getHeuristic(String player) {
//		return this.getState().evaDirections(player, this.getState());
//	}
	
	public int getNewHeuristic(String player) {
		return this.getState().evaNewDirections(player, this.getState());
	}
}