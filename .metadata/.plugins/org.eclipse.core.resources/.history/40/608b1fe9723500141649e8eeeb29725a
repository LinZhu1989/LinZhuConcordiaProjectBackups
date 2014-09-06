package comp6721_Tournament;

import java.util.ArrayList;

/**
 * class AIHeuristic
 * 
 * COMP 472/6721: Artificial Intelligence Project Deliverable IV
 * 
 * @author Yulong Song 6516599 Lin Zhu 6555659
 * 
 */

public class AIHeuristic {

	public AIHeuristic() {

	}
	
	
	public GameBoard MinMax_BestMove_WithAlpha(GameOfGotcha player) {

		int bestScore = -Integer.MAX_VALUE;
		int plyDepth = checkPieces(player.getBoard().board());
		int posi = 0;
		GameBoard copyBoard = player.getDeepCopyBoard(player.getBoard());
		GameTreeNode gameTreeRoot = new GameTreeNode(player.getPlayerName(),
				copyBoard);
		ArrayList<GameTreeNode> child = gameTreeRoot.getChildren();
		int size = child.size();

		for (int i = 0; i < size; i++) {
			int score = alphabet(child.get(i), plyDepth - 1, -Integer.MAX_VALUE, Integer.MAX_VALUE, false,player.getPlayerName());			
			if (child.get(i).children().size()==0) {
				return child.get(i).getState();
			}
			if (score > bestScore) {
				bestScore = score;
				posi = i;
			}
		}
		return child.get(posi).getState();
	}

	private int alphabet(GameTreeNode currentNode, int depth, int alpha,
			int beta, boolean maximizingPlayer,String name) {
		if (depth <= 0 || currentNode.ifTerminal()) {
			return currentNode.getHeuristic(name);
		}
		if (maximizingPlayer) {
			ArrayList<GameTreeNode> children1 = currentNode.getChildren();
			int size1 = children1.size();
			for (int i = 0; i < size1; i++) {
				alpha = Math.max(
						alpha,
						alphabet(children1.get(i), depth - 1, alpha, beta,
								false,name));
				if (alpha >= beta) {
					break;
				}
			}
			return alpha;
		} else {
			ArrayList<GameTreeNode> children2 = currentNode.getChildren();
			int size2 = children2.size();
			for (int i = 0; i < size2; i++) {
				beta = Math
						.min(beta,
								alphabet(children2.get(i), depth - 1, alpha,
										beta, true,name));
				if (alpha >= beta) {
					break;
				}
			}
			return beta;
		}
	}
	

	public static int checkPieces(MyPair[][] player) {
		int counter = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (player[i][j].getValue() != 0) {
					counter++;
				}
			}
		}

		if (counter <= 5) {
			return 4;
		} else {
			return 3;
		}
	}

}
