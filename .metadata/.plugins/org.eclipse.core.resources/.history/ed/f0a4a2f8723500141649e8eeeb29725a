package comp6721_Project;

import java.util.ArrayList;

/**
 * class GameBoard
 * 
 * COMP 472/6721: Artificial Intelligence Project Deliverable IV
 * 
 * @author Yulong Song 6516599 Lin Zhu 6555659
 * 
 */

public class GameBoard {

	private MyPair[][] board = null;

	public GameBoard() {
		board = new MyPair[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = new MyPair("II", 0);
			}
		}
		board[0][0].setName("P1");
		board[3][3].setName("P2");
		board[0][0].setValue(10);
		board[3][3].setValue(10);
	}

	public MyPair[][] board() {
		return board;
	}

	public GameBoard getDeepCopyBoard(GameBoard theBoard) {
		GameBoard newBoard = new GameBoard();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newBoard.board()[i][j] = theBoard.board()[i][j].deepClone();
			}
		}
		return newBoard;
	}

	public int evaDirections(String player, GameBoard theBoard) {

		String oppPlayer = (player.equalsIgnoreCase("P2")) ? "P1" : "P2";

		if (calPossibleDirections(player, theBoard) <= 2) {
			return -Integer.MAX_VALUE;
		} else if (calPossibleDirections(oppPlayer, theBoard) >= 10) {
			return -Integer.MAX_VALUE;
		}

		return calPossibleDirections(player, theBoard)
				- calPossibleDirections(oppPlayer, theBoard);
	}
	
	public int evaNewDirections(String player, GameBoard theBoard) {

		String oppPlayer = (player.equalsIgnoreCase("P2")) ? "P1" : "P2";

		return calPossibleDirections(player, theBoard)
				- calPossibleDirections(oppPlayer, theBoard);
	}

	public int evaChessmen(String player, GameBoard theBoard) {

		String oppPlayer = (player.equalsIgnoreCase("P2")) ? "P1" : "P2";

		if (calChessmen(player, theBoard) <= 1) {
			return -Integer.MAX_VALUE;
		} else if (calCentralMen(player, theBoard) <= 1) {
			return -Integer.MAX_VALUE;
		} else if (calCentralMen(oppPlayer, theBoard) >= 3) {
			return -Integer.MAX_VALUE;
		}

		return calChessmen(player, theBoard) + 2
				* calCentralMen(player, theBoard)
				- calChessmen(oppPlayer, theBoard) - 2
				* calCentralMen(oppPlayer, theBoard);
	}

	public int calPossibleDirections(String player, GameBoard theBoard) {

		int directions = 0;
		ArrayList<IntPair> position = findPosition(player, theBoard);

		if (position.size() == 0) {
			return 0;
		} else {
			for (int j = 0; j < position.size(); j++) {
				for (int i = 0; i < 8; i++) {
					if (computeSteps(player, position.get(j).getRow(), position
							.get(j).getColumn(), i, theBoard) > 0) {
						directions++;
					}
				}
			}
			return directions;
		}
	}

	public int calChessmen(String player, GameBoard theBoard) {
		ArrayList<IntPair> position = findPosition(player, theBoard);
		return position.size();
	}

	public int calCentralMen(String player, GameBoard theBoard) {
		ArrayList<IntPair> position = findCentralPosition(player, theBoard);
		return position.size();
	}

	public ArrayList<IntPair> findPosition(String player, GameBoard theBoard) {

		ArrayList<IntPair> position = new ArrayList<IntPair>(10);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (theBoard.board()[i][j].getName().equalsIgnoreCase(player)) {
					position.add(new IntPair(i, j));
				}
			}
		}
		return position;
	}

	public ArrayList<IntPair> findCentralPosition(String player,
			GameBoard theBoard) {

		ArrayList<IntPair> position = new ArrayList<IntPair>(10);
		for (int i = 1; i < 3; i++) {
			for (int j = 1; j < 3; j++) {
				if (theBoard.board()[i][j].getName().equalsIgnoreCase(player)) {
					position.add(new IntPair(i, j));
				}
			}
		}
		return position;
	}

	public ArrayList<ArrayList<Integer>> findPossibleMoves(String player,
			GameBoard theBoard) {

		ArrayList<IntPair> position = findPosition(player, theBoard);
		ArrayList<ArrayList<Integer>> choiceList = new ArrayList<ArrayList<Integer>>(
				80);
		int steps = 0;
		if (position.size() == 0) {
			return null;
		} else {
			for (int j = 0; j < position.size(); j++) {
				for (int i = 0; i < 8; i++) {
					steps = computeSteps(player, position.get(j).getRow(),
							position.get(j).getColumn(), i, theBoard);
					if (steps > 0) {
						ArrayList<Integer> choices = new ArrayList<Integer>();
						choices.add(position.get(j).getRow());
						choices.add(position.get(j).getColumn());
						choices.add(i);
						choices.add(steps);
						choiceList.add(choices);
					}
				}
			}
			return choiceList;
		}
	}

	public int computeSteps(String player, int x, int y, int direction,
			GameBoard theBoard) {
		int remainStepCounter = 0;
		String playerName = null;
		int value = theBoard.board()[x][y].getValue();
		switch (direction) {
		case 0:
			for (int i = x - 1; i >= 0; i--) {
				playerName = theBoard.board()[i][y].getName();

				if (playerName.equalsIgnoreCase(player)
						|| playerName.equalsIgnoreCase("II")) {
					remainStepCounter += 1;
				} else {
					break;
				}
			}
			if (value > 3) {
				return remainStepCounter;
			} else if (value > 1) {
				return Math.min(remainStepCounter, 2);
			} else if (value == 1) {
				return Math.min(remainStepCounter, 1);
			}
		case 1:
			for (int i = y + 1; i <= 3 && x > 0; i++) {
				playerName = theBoard.board()[--x][i].getName();
				if (playerName.equalsIgnoreCase(player)
						|| playerName.equalsIgnoreCase("II")) {
					remainStepCounter += 1;
				} else {
					break;
				}
			}
			if (value > 3) {
				return remainStepCounter;
			} else if (value > 1) {
				return Math.min(remainStepCounter, 2);
			} else if (value == 1) {
				return Math.min(remainStepCounter, 1);
			}

		case 2:
			for (int i = y + 1; i <= 3; i++) {
				playerName = theBoard.board()[x][i].getName();
				if (playerName.equalsIgnoreCase(player)
						|| playerName.equalsIgnoreCase("II"))

				{
					remainStepCounter += 1;
				} else {
					break;
				}
			}
			if (value > 3) {
				return remainStepCounter;
			} else if (value > 1) {
				return Math.min(remainStepCounter, 2);
			} else if (value == 1) {
				return Math.min(remainStepCounter, 1);
			}

		case 3:
			for (int i = x + 1; i <= 3 && y < 3; i++) {

				playerName = theBoard.board()[i][++y].getName();
				if (playerName.equalsIgnoreCase(player)
						|| playerName.equalsIgnoreCase("II")) {
					remainStepCounter += 1;
				} else {
					break;
				}
			}
			if (value > 3) {
				return remainStepCounter;
			} else if (value > 1) {
				return Math.min(remainStepCounter, 2);
			} else if (value == 1) {
				return Math.min(remainStepCounter, 1);
			}

		case 4:
			for (int i = x + 1; i <= 3; i++) {
				playerName = theBoard.board()[i][y].getName();
				if (playerName.equalsIgnoreCase(player)
						|| playerName.equalsIgnoreCase("II")) {
					remainStepCounter += 1;
				} else {
					break;
				}
			}
			if (value > 3) {
				return remainStepCounter;
			} else if (value > 1) {
				return Math.min(remainStepCounter, 2);
			} else if (value == 1) {
				return Math.min(remainStepCounter, 1);
			}

		case 5:
			for (int i = x + 1; i <= 3 && y > 0; i++) {
				playerName = theBoard.board()[i][--y].getName();
				if (playerName.equalsIgnoreCase(player)
						|| playerName.equalsIgnoreCase("II")) {
					remainStepCounter += 1;
				} else {
					break;
				}
			}
			if (value > 3) {
				return remainStepCounter;
			} else if (value > 1) {
				return Math.min(remainStepCounter, 2);
			} else if (value == 1) {
				return Math.min(remainStepCounter, 1);
			}
		case 6:
			for (int i = y - 1; i >= 0; i--) {
				playerName = theBoard.board()[x][i].getName();
				if (playerName.equalsIgnoreCase(player)
						|| playerName.equalsIgnoreCase("II")) {
					remainStepCounter += 1;
				} else {
					break;
				}

			}
			if (value > 3) {
				return remainStepCounter;
			} else if (value > 1) {
				return Math.min(remainStepCounter, 2);
			} else if (value == 1) {
				return Math.min(remainStepCounter, 1);
			}

		case 7:
			for (int i = y - 1; i >= 0 && x > 0; i--) {
				playerName = theBoard.board()[--x][i].getName();
				if (playerName.equalsIgnoreCase(player)
						|| playerName.equalsIgnoreCase("II")) {
					remainStepCounter += 1;
				} else {
					break;
				}
			}
			if (value > 3) {
				return remainStepCounter;
			} else if (value > 1) {
				return Math.min(remainStepCounter, 2);
			} else if (value == 1) {
				return Math.min(remainStepCounter, 1);
			}

		}
		return remainStepCounter;
	}

	public GameBoard boardMove(String player, GameBoard board, int x, int y,
			int direction, int steps) {

		GameBoard theBoard = board.getDeepCopyBoard(board);
		switch (direction) {
		case 0:
			if (steps == 1) {
				int a = theBoard.board()[x - 1][y].getValue();
				int b = theBoard.board()[x][y].getValue();
				theBoard.board()[x - 1][y].setValue(a + b);
				theBoard.board()[x - 1][y].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 2) {
				int a = theBoard.board()[x - 2][y].getValue();
				int b = theBoard.board()[x - 1][y].getValue();
				int c = theBoard.board()[x][y].getValue();
				theBoard.board()[x - 2][y].setValue(a + c - 1);
				theBoard.board()[x - 1][y].setValue(b + 1);
				theBoard.board()[x - 2][y].setName(player);
				theBoard.board()[x - 1][y].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 3) {
				int a = theBoard.board()[x - 3][y].getValue();
				int b = theBoard.board()[x - 2][y].getValue();
				int c = theBoard.board()[x - 1][y].getValue();
				int d = theBoard.board()[x][y].getValue();
				theBoard.board()[x - 3][y].setValue(a + d - 3);
				theBoard.board()[x - 3][y].setName(player);
				theBoard.board()[x][y].clear();
				theBoard.board()[x - 2][y].setValue(b + 2);
				theBoard.board()[x - 2][y].setName(player);
				theBoard.board()[x - 1][y].setValue(c + 1);
				theBoard.board()[x - 1][y].setName(player);
				return theBoard;
			}
		case 1:
			if (steps == 1) {
				int a = theBoard.board()[x - 1][y + 1].getValue();
				int b = theBoard.board()[x][y].getValue();
				theBoard.board()[x - 1][y + 1].setValue(a + b);
				theBoard.board()[x - 1][y + 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 2) {
				int a = theBoard.board()[x - 2][y + 2].getValue();
				int b = theBoard.board()[x - 1][y + 1].getValue();
				int c = theBoard.board()[x][y].getValue();
				theBoard.board()[x - 2][y + 2].setValue(a + c - 1);
				theBoard.board()[x - 1][y + 1].setValue(b + 1);
				theBoard.board()[x - 2][y + 2].setName(player);
				theBoard.board()[x - 1][y + 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 3) {
				int a = theBoard.board()[x - 3][y + 3].getValue();
				int b = theBoard.board()[x - 2][y + 2].getValue();
				int c = theBoard.board()[x - 1][y + 1].getValue();
				int d = theBoard.board()[x][y].getValue();
				theBoard.board()[x - 3][y + 3].setValue(a + d - 3);
				theBoard.board()[x - 3][y + 3].setName(player);
				theBoard.board()[x][y].clear();
				theBoard.board()[x - 2][y + 2].setValue(b + 2);
				theBoard.board()[x - 2][y + 2].setName(player);
				theBoard.board()[x - 1][y + 1].setValue(c + 1);
				theBoard.board()[x - 1][y + 1].setName(player);
				return theBoard;
			}
		case 2:
			if (steps == 1) {
				int a = theBoard.board()[x][y + 1].getValue();
				int b = theBoard.board()[x][y].getValue();
				theBoard.board()[x][y + 1].setValue(a + b);
				theBoard.board()[x][y + 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 2) {
				int a = theBoard.board()[x][y + 2].getValue();
				int b = theBoard.board()[x][y + 1].getValue();
				int c = theBoard.board()[x][y].getValue();
				theBoard.board()[x][y + 2].setValue(a + c - 1);
				theBoard.board()[x][y + 1].setValue(b + 1);
				theBoard.board()[x][y + 2].setName(player);
				theBoard.board()[x][y + 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 3) {
				int a = theBoard.board()[x][y + 3].getValue();
				int b = theBoard.board()[x][y + 2].getValue();
				int c = theBoard.board()[x][y + 1].getValue();
				int d = theBoard.board()[x][y].getValue();
				theBoard.board()[x][y + 3].setValue(a + d - 3);
				theBoard.board()[x][y + 3].setName(player);
				theBoard.board()[x][y].clear();
				theBoard.board()[x][y + 2].setValue(b + 2);
				theBoard.board()[x][y + 2].setName(player);
				theBoard.board()[x][y + 1].setValue(c + 1);
				theBoard.board()[x][y + 1].setName(player);
				return theBoard;
			}
		case 3:
			if (steps == 1) {
				int a = theBoard.board()[x + 1][y + 1].getValue();
				int b = theBoard.board()[x][y].getValue();
				theBoard.board()[x + 1][y + 1].setValue(a + b);
				theBoard.board()[x + 1][y + 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 2) {
				int a = theBoard.board()[x + 2][y + 2].getValue();
				int b = theBoard.board()[x + 1][y + 1].getValue();
				int c = theBoard.board()[x][y].getValue();
				theBoard.board()[x + 2][y + 2].setValue(a + c - 1);
				theBoard.board()[x + 1][y + 1].setValue(b + 1);
				theBoard.board()[x + 2][y + 2].setName(player);
				theBoard.board()[x + 1][y + 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 3) {
				int a = theBoard.board()[x + 3][y + 3].getValue();
				int b = theBoard.board()[x + 2][y + 2].getValue();
				int c = theBoard.board()[x + 1][y + 1].getValue();
				int d = theBoard.board()[x][y].getValue();
				theBoard.board()[x + 3][y + 3].setValue(a + d - 3);
				theBoard.board()[x + 3][y + 3].setName(player);
				theBoard.board()[x][y].clear();
				theBoard.board()[x + 2][y + 2].setValue(b + 2);
				theBoard.board()[x + 2][y + 2].setName(player);
				theBoard.board()[x + 1][y + 1].setValue(c + 1);
				theBoard.board()[x + 1][y + 1].setName(player);
				return theBoard;
			}
		case 4:
			if (steps == 1) {
				int a = theBoard.board()[x + 1][y].getValue();
				int b = theBoard.board()[x][y].getValue();
				theBoard.board()[x + 1][y].setValue(a + b);
				theBoard.board()[x + 1][y].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 2) {
				int a = theBoard.board()[x + 2][y].getValue();
				int b = theBoard.board()[x + 1][y].getValue();
				int c = theBoard.board()[x][y].getValue();
				theBoard.board()[x + 2][y].setValue(a + c - 1);
				theBoard.board()[x + 1][y].setValue(b + 1);
				theBoard.board()[x + 2][y].setName(player);
				theBoard.board()[x + 1][y].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 3) {
				int a = theBoard.board()[x + 3][y].getValue();
				int b = theBoard.board()[x + 2][y].getValue();
				int c = theBoard.board()[x + 1][y].getValue();
				int d = theBoard.board()[x][y].getValue();
				theBoard.board()[x + 3][y].setValue(a + d - 3);
				theBoard.board()[x + 3][y].setName(player);
				theBoard.board()[x][y].clear();
				theBoard.board()[x + 2][y].setValue(b + 2);
				theBoard.board()[x + 2][y].setName(player);
				theBoard.board()[x + 1][y].setValue(c + 1);
				theBoard.board()[x + 1][y].setName(player);
				return theBoard;
			}

		case 5:
			if (steps == 1) {
				int a = theBoard.board()[x + 1][y - 1].getValue();
				int b = theBoard.board()[x][y].getValue();
				theBoard.board()[x + 1][y - 1].setValue(a + b);
				theBoard.board()[x + 1][y - 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 2) {
				int a = theBoard.board()[x + 2][y - 2].getValue();
				int b = theBoard.board()[x + 1][y - 1].getValue();
				int c = theBoard.board()[x][y].getValue();
				theBoard.board()[x + 2][y - 2].setValue(a + c - 1);
				theBoard.board()[x + 1][y - 1].setValue(b + 1);
				theBoard.board()[x + 2][y - 2].setName(player);
				theBoard.board()[x + 1][y - 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 3) {
				int a = theBoard.board()[x + 3][y - 3].getValue();
				int b = theBoard.board()[x + 2][y - 2].getValue();
				int c = theBoard.board()[x + 1][y - 1].getValue();
				int d = theBoard.board()[x][y].getValue();
				theBoard.board()[x + 3][y - 3].setValue(a + d - 3);
				theBoard.board()[x + 3][y - 3].setName(player);
				theBoard.board()[x][y].clear();
				theBoard.board()[x + 2][y - 2].setValue(b + 2);
				theBoard.board()[x + 2][y - 2].setName(player);
				theBoard.board()[x + 1][y - 1].setValue(c + 1);
				theBoard.board()[x + 1][y - 1].setName(player);
				return theBoard;
			}
		case 6:
			if (steps == 1) {
				int a = theBoard.board()[x][y - 1].getValue();
				int b = theBoard.board()[x][y].getValue();
				theBoard.board()[x][y - 1].setValue(a + b);
				theBoard.board()[x][y - 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 2) {
				int a = theBoard.board()[x][y - 2].getValue();
				int b = theBoard.board()[x][y - 1].getValue();
				int c = theBoard.board()[x][y].getValue();
				theBoard.board()[x][y - 2].setValue(a + c - 1);
				theBoard.board()[x][y - 1].setValue(b + 1);
				theBoard.board()[x][y - 2].setName(player);
				theBoard.board()[x][y - 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 3) {
				int a = theBoard.board()[x][y - 3].getValue();
				int b = theBoard.board()[x][y - 2].getValue();
				int c = theBoard.board()[x][y - 1].getValue();
				int d = theBoard.board()[x][y].getValue();
				theBoard.board()[x][y - 3].setValue(a + d - 3);
				theBoard.board()[x][y - 3].setName(player);
				theBoard.board()[x][y].clear();
				theBoard.board()[x][y - 2].setValue(b + 2);
				theBoard.board()[x][y - 2].setName(player);
				theBoard.board()[x][y - 1].setValue(c + 1);
				theBoard.board()[x][y - 1].setName(player);
				return theBoard;
			}

		case 7:
			if (steps == 1) {
				int a = theBoard.board()[x - 1][y - 1].getValue();
				int b = theBoard.board()[x][y].getValue();
				theBoard.board()[x - 1][y - 1].setValue(a + b);
				theBoard.board()[x - 1][y - 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 2) {
				int a = theBoard.board()[x - 2][y - 2].getValue();
				int b = theBoard.board()[x - 1][y - 1].getValue();
				int c = theBoard.board()[x][y].getValue();
				theBoard.board()[x - 2][y - 2].setValue(a + c - 1);
				theBoard.board()[x - 1][y - 1].setValue(b + 1);
				theBoard.board()[x - 2][y - 2].setName(player);
				theBoard.board()[x - 1][y - 1].setName(player);
				theBoard.board()[x][y].clear();
				return theBoard;
			}
			if (steps == 3) {
				int a = theBoard.board()[x - 3][y - 3].getValue();
				int b = theBoard.board()[x - 2][y - 2].getValue();
				int c = theBoard.board()[x - 1][y - 1].getValue();
				int d = theBoard.board()[x][y].getValue();
				theBoard.board()[x - 3][y - 3].setValue(a + d - 3);
				theBoard.board()[x - 3][y - 3].setName(player);
				theBoard.board()[x][y].clear();
				theBoard.board()[x - 2][y - 2].setValue(b + 2);
				theBoard.board()[x - 2][y - 2].setName(player);
				theBoard.board()[x - 1][y - 1].setValue(c + 1);
				theBoard.board()[x - 1][y - 1].setName(player);
				return theBoard;
			}

		}
		return theBoard;
	}

}
