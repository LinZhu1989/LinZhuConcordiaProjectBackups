package comp6721_Project;

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
	
	public GameBoard MinMax_BestMove_NoAlpha(GameOfGotcha player) {

		int plyDepth = checkPieces(player.getBoard().board());
		if(plyDepth==3){
			return myMinMaxL3Move(player);
		}else{
			return myMinMaxL4Move(player);
		}
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
			return currentNode.getNewHeuristic(name);
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


	public GameBoard nagaMaxMove(GameOfGotcha player, int plydeepth) {

		int bestScore = -Integer.MAX_VALUE;
		int plyDepth = plydeepth;
		int size = 0;
		int posi = 0;
		GameBoard copyBoard = player.getDeepCopyBoard(player.getBoard());
		GameTreeNode gameTreeRoot = new GameTreeNode(player.getPlayerName(),
				copyBoard);
		ArrayList<GameTreeNode> child = gameTreeRoot.getChildren();
		size = child.size();

		for (int i = 0; i < size; i++) {
			int score = -Integer.MAX_VALUE;
			score = -Math.max(score, nagaMax(child.get(i), plyDepth - 1));
			if (child.get(i).children().size()==0) {
				return child.get(i).getState();
			}
			if (score >= bestScore) {
				bestScore = score;
				posi = i;
			}
		}
		return child.get(posi).getState();
	}

	private int nagaMax(GameTreeNode currentNode, int depth) {

		if (depth <= 0 || currentNode.ifTerminal()) {
			return currentNode.getNewHeuristic(currentNode.getPlayerName());
		}
		int bestScore = -Integer.MAX_VALUE;
		ArrayList<GameTreeNode> children = currentNode.getChildren();
		int size = children.size();
		for (int i = 0; i < size; i++) {
			int value = -nagaMax(children.get(i), depth - 1);
			bestScore = Math.max(bestScore, value);
		}
		return bestScore;

	}

	public GameBoard minMaxMove(GameOfGotcha player, int plydeepth) {

		int bestScore = -Integer.MAX_VALUE;
		int plyDepth = plydeepth;
		int size = 0;
		int posi = 0;
		GameBoard copyBoard = player.getDeepCopyBoard(player.getBoard());
		GameTreeNode gameTreeRoot = new GameTreeNode(player.getPlayerName(),
				copyBoard);
		ArrayList<GameTreeNode> child = gameTreeRoot.getChildren();
		size = child.size();

		for (int i = 0; i < size; i++) {
			int score = -Integer.MAX_VALUE;
			score = Math.max(score, minMax(child.get(i), plyDepth - 1, false));
			if (child.get(i).children().size()==0) {
				return child.get(i).getState();
			}
			if (score >= bestScore) {
				bestScore = score;
				posi = i;
			}
		}
		return child.get(posi).getState();
	}

	private int minMax(GameTreeNode currentNode, int depth,
			boolean maximizingPlayer) {

		if (depth <= 0 || currentNode.ifTerminal()) {
			return currentNode.getNewHeuristic("P2");
		}
		if (maximizingPlayer) {
			int bestScore = -Integer.MAX_VALUE;
			ArrayList<GameTreeNode> children1 = currentNode.getChildren();
			int size1 = children1.size();
			for (int i = 0; i < size1; i++) {
				int value = minMax(children1.get(i), depth - 1, false);
				bestScore = Math.max(bestScore, value);
			}
			return bestScore;
		} else {
			int bestScore = Integer.MAX_VALUE;
			ArrayList<GameTreeNode> children2 = currentNode.getChildren();
			int size2 = children2.size();
			for (int i = 0; i < size2; i++) {
				int value = minMax(children2.get(i), depth - 1, true);
				bestScore = Math.min(bestScore, value);
			}
			return bestScore;
		}
	}

	public GameBoard myNagaMaxL2Move(GameOfGotcha player) {
		GameBoard copyBoard = player.getDeepCopyBoard(player.getBoard());
		GameTreeNode gameTreeRoot = new GameTreeNode(player.getPlayerName(),
				copyBoard);
		int maxScore1 = -Integer.MAX_VALUE;
		int maxScore2 = -Integer.MAX_VALUE;
		int posi = 0;
		int size1 = 0;
		int size2 = 0;

		ArrayList<GameTreeNode> lvl1Child = gameTreeRoot.getChildren();
		size1 = lvl1Child.size();
		ArrayList<GameTreeNode> lvl2Child = new ArrayList<GameTreeNode>(80);
		for (int i = 0; i < size1; i++) {
			lvl2Child = lvl1Child.get(i).getChildren();
			if (lvl2Child == null) {
				posi = i;
				return lvl1Child.get(posi).getState();
			}
			size2 = lvl2Child.size();
			for (int j = 0; j < size2; j++) {
				int l2Score = -lvl2Child.get(j).getNewHeuristic(player.getPlayerName());
				if (l2Score >= maxScore2) {
					maxScore2 = l2Score;
				}
				lvl1Child.get(i).setScore(maxScore2);
			}
			lvl2Child.clear();
			maxScore2 = -Integer.MAX_VALUE;
			int l1Score = -lvl1Child.get(i).getScore();
			if (l1Score >= maxScore1) {
				maxScore1 = l1Score;
				posi = i;
			}
		}
		return lvl1Child.get(posi).getState();
	}

	public GameBoard myNagaMaxL3Move(GameOfGotcha player) {

		GameBoard copyBoard = player.getDeepCopyBoard(player.getBoard());
		GameTreeNode gameTreeRoot = new GameTreeNode(player.getPlayerName(),
				copyBoard);

		int posi = 0;
		int size1 = 0;
		int size2 = 0;
		int size3 = 0;
		int l3MaxScore = -Integer.MAX_VALUE;
		int l2MaxScore = -Integer.MAX_VALUE;
		int l1MaxScore = -Integer.MAX_VALUE;
		ArrayList<GameTreeNode> lvl1Child = gameTreeRoot.getChildren();
		size1 = lvl1Child.size();
		ArrayList<GameTreeNode> lvl2Child = new ArrayList<GameTreeNode>(80);
		ArrayList<GameTreeNode> lvl3Child = new ArrayList<GameTreeNode>(500);
		for (int i = 0; i < size1; i++) {
			lvl2Child = lvl1Child.get(i).getChildren();
			if (lvl2Child == null) {
				posi = i;
				return lvl1Child.get(posi).getState();
			}
			size2 = lvl2Child.size();
			for (int j = 0; j < size2; j++) {
				lvl3Child = lvl2Child.get(j).getChildren();
				if (lvl3Child == null) {
					lvl2Child.get(j).setScore(Integer.MAX_VALUE);
					break;
				}
				size3 = lvl3Child.size();
				for (int m = 0; m < size3; m++) {
					int l3Score = -lvl3Child.get(m).getNewHeuristic(player.getPlayerName());
					if (l3Score >= l3MaxScore) {
						l3MaxScore = l3Score;
					}
					lvl2Child.get(j).setScore(l3MaxScore);
				}
				lvl3Child.clear();
				l3MaxScore = -Integer.MAX_VALUE;
				int l2Score = -lvl2Child.get(j).getScore();
				if (l2Score >= l2MaxScore) {
					l2MaxScore = l2Score;
				}
				lvl1Child.get(i).setScore(l2MaxScore);
			}
			lvl2Child.clear();
			l2MaxScore = -Integer.MAX_VALUE;
			int l1Score = -lvl1Child.get(i).getScore();
			if (l1Score >= l1MaxScore) {
				l1MaxScore = l1Score;
				posi = i;

			}
		}
		return lvl1Child.get(posi).getState();
	}

	public GameBoard myNagaMaxL4Move(GameOfGotcha player) {

		GameBoard copyBoard = player.getDeepCopyBoard(player.getBoard());
		GameTreeNode gameTreeRoot = new GameTreeNode(player.getPlayerName(),
				copyBoard);

		int posi = 0;
		int size1 = 0;
		int size2 = 0;
		int size3 = 0;
		int size4 = 0;
		int l4MaxScore = -Integer.MAX_VALUE;
		int l3MaxScore = -Integer.MAX_VALUE;
		int l2MaxScore = -Integer.MAX_VALUE;
		int l1MaxScore = -Integer.MAX_VALUE;
		ArrayList<GameTreeNode> lvl1Child = gameTreeRoot.getChildren();
		size1 = lvl1Child.size();
		ArrayList<GameTreeNode> lvl2Child = new ArrayList<GameTreeNode>(80);
		ArrayList<GameTreeNode> lvl3Child = new ArrayList<GameTreeNode>(500);
		ArrayList<GameTreeNode> lvl4Child = new ArrayList<GameTreeNode>(1000);
		for (int i = 0; i < size1; i++) {
			lvl2Child = lvl1Child.get(i).getChildren();
			if (lvl2Child == null) {
				posi = i;
				return lvl1Child.get(posi).getState();
			}
			size2 = lvl2Child.size();
			for (int j = 0; j < size2; j++) {
				lvl3Child = lvl2Child.get(j).getChildren();
				if (lvl3Child == null) {
					lvl2Child.get(j).setScore(Integer.MAX_VALUE);
					break;
				}
				size3 = lvl3Child.size();
				for (int m = 0; m < size3; m++) {
					lvl4Child = lvl3Child.get(m).getChildren();
					if (lvl4Child == null) {
						posi = i;
						return lvl1Child.get(posi).getState();
					}
					size4 = lvl4Child.size();
					for (int n = 0; n < size4; n++) {
						int l4Score = -lvl4Child.get(n).getNewHeuristic(player.getPlayerName());
						if (l4Score >= l4MaxScore) {
							l4MaxScore = l4Score;
						}
						lvl3Child.get(m).setScore(l4MaxScore);
					}
					lvl4Child.clear();
					l4MaxScore = -Integer.MAX_VALUE;
					int l3Score = -lvl3Child.get(m).getScore();
					if (l3Score >= l3MaxScore) {
						l3MaxScore = l3Score;
					}
					lvl2Child.get(j).setScore(l3MaxScore);
				}
				lvl3Child.clear();
				l3MaxScore = -Integer.MAX_VALUE;
				int l2Score = -lvl2Child.get(j).getScore();
				if (l2Score >= l2MaxScore) {
					l2MaxScore = l2Score;
				}
				lvl1Child.get(i).setScore(l2MaxScore);
			}
			lvl2Child.clear();
			l2MaxScore = -Integer.MAX_VALUE;
			int l1Score = -lvl1Child.get(i).getScore();
			if (l1Score >= l1MaxScore) {
				l1MaxScore = l1Score;
				posi = i;
			}
		}

		return lvl1Child.get(posi).getState();

	}

	public GameBoard myMinMaxL2Move(GameOfGotcha player) {
		GameBoard copyBoard = player.getDeepCopyBoard(player.getBoard());
		GameTreeNode gameTreeRoot = new GameTreeNode(player.getPlayerName(),
				copyBoard);
		int minScore = Integer.MAX_VALUE;
		int maxScore = -Integer.MAX_VALUE;
		int posi = 0;
		int size1 = 0;
		int size2 = 0;

		ArrayList<GameTreeNode> lvl1Child = gameTreeRoot.getChildren();
		size1 = lvl1Child.size();
		ArrayList<GameTreeNode> lvl2Child = new ArrayList<GameTreeNode>(80);
		for (int i = 0; i < size1; i++) {
			lvl2Child = lvl1Child.get(i).getChildren();
			if (lvl2Child == null) {
				posi = i;
				return lvl1Child.get(posi).getState();
			}
			size2 = lvl2Child.size();
			for (int j = 0; j < size2; j++) {
				int l2Score = lvl2Child.get(j).getNewHeuristic(player.getPlayerName());
				// Level 2 is a Max level,so choose the min score
				if (l2Score <= minScore) {
					minScore = l2Score;
				}
				lvl1Child.get(i).setScore(minScore);
			}
			lvl2Child.clear();
			minScore = Integer.MAX_VALUE;
			int l1Score = lvl1Child.get(i).getScore();
			// Level 1 is a Min level,so choose the max score
			if (l1Score >= maxScore) {
				maxScore = l1Score;
				posi = i;
			}
		}
		return lvl1Child.get(posi).getState();
	}

	public GameBoard myMinMaxL3Move(GameOfGotcha player) {

		GameBoard copyBoard = player.getDeepCopyBoard(player.getBoard());
		GameTreeNode gameTreeRoot = new GameTreeNode(player.getPlayerName(),
				copyBoard);

		int posi = 0;
		int size1 = 0;
		int size2 = 0;
		int size3 = 0;
		int l3MaxScore = -Integer.MAX_VALUE;
		int l2MinScore = Integer.MAX_VALUE;
		int l1MaxScore = -Integer.MAX_VALUE;
		ArrayList<GameTreeNode> lvl1Child = gameTreeRoot.getChildren();
		size1 = lvl1Child.size();
		ArrayList<GameTreeNode> lvl2Child = new ArrayList<GameTreeNode>(80);
		ArrayList<GameTreeNode> lvl3Child = new ArrayList<GameTreeNode>(500);
		for (int i = 0; i < size1; i++) {
			lvl2Child = lvl1Child.get(i).getChildren();
			if (lvl2Child == null) {
				posi = i;
				return lvl1Child.get(posi).getState();
			}
			size2 = lvl2Child.size();
			for (int j = 0; j < size2; j++) {
				lvl3Child = lvl2Child.get(j).getChildren();
				if (lvl3Child == null) {
					lvl2Child.get(j).setScore(Integer.MAX_VALUE);
					break;
				}
				size3 = lvl3Child.size();
				for (int m = 0; m < size3; m++) {
					int l3Score = lvl3Child.get(m).getNewHeuristic(player.getPlayerName());
					// Level 3 is a Min level,so choose the max score
					if (l3Score >= l3MaxScore) {
						l3MaxScore = l3Score;
					}
					lvl2Child.get(j).setScore(l3MaxScore);
				}
				lvl3Child.clear();
				l3MaxScore = -Integer.MAX_VALUE;
				int l2Score = lvl2Child.get(j).getScore();
				// Level 2 is a Max level,so choose the min score
				if (l2Score <= l2MinScore) {
					l2MinScore = l2Score;
				}
				lvl1Child.get(i).setScore(l2MinScore);
			}
			lvl2Child.clear();
			l2MinScore = Integer.MAX_VALUE;
			int l1Score = lvl1Child.get(i).getScore();
			// Level 1 is a Min level,so choose the max score
			if (l1Score >= l1MaxScore) {
				l1MaxScore = l1Score;
				posi = i;

			}
		}
		return lvl1Child.get(posi).getState();
	}

	public GameBoard myMinMaxL4Move(GameOfGotcha player) {

		GameBoard copyBoard = player.getDeepCopyBoard(player.getBoard());
		GameTreeNode gameTreeRoot = new GameTreeNode(player.getPlayerName(),
				copyBoard);

		int posi = 0;
		int size1 = 0;
		int size2 = 0;
		int size3 = 0;
		int size4 = 0;
		int l4MinScore = Integer.MAX_VALUE;
		int l3MaxScore = -Integer.MAX_VALUE;
		int l2MinScore = Integer.MAX_VALUE;
		int l1MaxScore = -Integer.MAX_VALUE;
		ArrayList<GameTreeNode> lvl1Child = gameTreeRoot.getChildren();
		size1 = lvl1Child.size();
		ArrayList<GameTreeNode> lvl2Child = new ArrayList<GameTreeNode>(80);
		ArrayList<GameTreeNode> lvl3Child = new ArrayList<GameTreeNode>(500);
		ArrayList<GameTreeNode> lvl4Child = new ArrayList<GameTreeNode>(1000);
		for (int i = 0; i < size1; i++) {
			lvl2Child = lvl1Child.get(i).getChildren();
			if (lvl2Child == null) {
				posi = i;
				return lvl1Child.get(posi).getState();
			}
			size2 = lvl2Child.size();
			for (int j = 0; j < size2; j++) {
				lvl3Child = lvl2Child.get(j).getChildren();
				if (lvl3Child == null) {
					lvl2Child.get(j).setScore(Integer.MAX_VALUE);
					break;
				}
				size3 = lvl3Child.size();
				for (int m = 0; m < size3; m++) {
					lvl4Child = lvl3Child.get(m).getChildren();
					if (lvl4Child == null) {
						posi = i;
						return lvl1Child.get(posi).getState();
					}
					size4 = lvl4Child.size();
					for (int n = 0; n < size4; n++) {
						int l4Score = lvl4Child.get(n).getNewHeuristic(player.getPlayerName());
						// Level 4 is a Max level,so choose the min score
						if (l4Score <= l4MinScore) {
							l4MinScore = l4Score;
						}
						lvl3Child.get(m).setScore(l4MinScore);
					}
					lvl4Child.clear();
					l4MinScore = Integer.MAX_VALUE;
					int l3Score = lvl3Child.get(m).getScore();
					// Level 3 is a Min level,so choose the max score
					if (l3Score >= l3MaxScore) {
						l3MaxScore = l3Score;
					}
					lvl2Child.get(j).setScore(l3MaxScore);
				}
				lvl3Child.clear();
				l3MaxScore = -Integer.MAX_VALUE;
				int l2Score = lvl2Child.get(j).getScore();
				// Level 2 is a Max level,so choose the min score
				if (l2Score <= l2MinScore) {
					l2MinScore = l2Score;
				}
				lvl1Child.get(i).setScore(l2MinScore);
			}
			lvl2Child.clear();
			l2MinScore = Integer.MAX_VALUE;
			int l1Score = lvl1Child.get(i).getScore();
			// Level 1 is a Min level,so choose the max score
			if (l1Score >= l1MaxScore) {
				l1MaxScore = l1Score;
				posi = i;
			}
		}

		return lvl1Child.get(posi).getState();

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

		if (counter <= 6) {
			return 4;
		} else {
			return 3;
		}
	}

}
