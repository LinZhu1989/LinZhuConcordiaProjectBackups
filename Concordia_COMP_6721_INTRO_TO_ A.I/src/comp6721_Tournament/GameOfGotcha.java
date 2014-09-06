package comp6721_Tournament;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * class GameOfGotcha
 * 
 * COMP 472/6721: Artificial Intelligence Project Deliverable IV
 * 
 * @author Yulong Song 6516599 Lin Zhu 6555659
 * 
 */
public class GameOfGotcha {
	public static GameBoard board = null;
	private String playerName;

	public GameOfGotcha() {
		board = new GameBoard();
	}

	public GameOfGotcha(String name) {
		this.playerName = name;
	}

	public GameOfGotcha(String name, GameBoard curBoard) {
		this.playerName = name;
		board.getDeepCopyBoard(curBoard);
	}

	public GameBoard getBoard() {
		return board;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String newName) {
		playerName = newName;
	}

	public enum Direction {
		NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST
	}

	public Direction getUserChoice(int direction) {
		Direction result = null;
		// switch(direction);
		if (direction == 0) {
			result = Direction.NORTH;
			return result;
		}
		if (direction == 1) {
			result = Direction.NORTH_EAST;
			return result;
		}
		if (direction == 2) {
			result = Direction.EAST;
			return result;
		}
		if (direction == 3) {
			result = Direction.SOUTH_EAST;
			return result;
		}
		if (direction == 4) {
			result = Direction.SOUTH;
			return result;
		}
		if (direction == 5) {
			result = Direction.SOUTH_WEST;
			return result;
		}
		if (direction == 6) {
			result = Direction.WEST;
			return result;
		}
		if (direction == 7) {
			result = Direction.NORTH_WEST;
			return result;
		}
		return result;
	}

	public boolean move(int x, int y, int direction) {

		// Direction D = getUserChoice(direction);
		int steps = computeSteps(x, y, direction);
		boolean result = false;
		switch (direction) {
		case 0:
			if (steps == 0) {
				result = false;
				return result;
			}
			if (steps == 1) {
				int a = board.board()[x - 1][y].getValue();
				int b = board.board()[x][y].getValue();
				board.board()[x - 1][y].setValue(a + b);
				board.board()[x - 1][y].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 2) {
				int a = board.board()[x - 2][y].getValue();
				int b = board.board()[x - 1][y].getValue();
				int c = board.board()[x][y].getValue();
				board.board()[x - 2][y].setValue(a + c - 1);
				board.board()[x - 1][y].setValue(b + 1);
				board.board()[x - 2][y].setName(this.getPlayerName());
				board.board()[x - 1][y].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 3) {
				int a = board.board()[x - 3][y].getValue();
				int b = board.board()[x - 2][y].getValue();
				int c = board.board()[x - 1][y].getValue();
				int d = board.board()[x][y].getValue();
				board.board()[x - 3][y].setValue(a + d - 3);
				board.board()[x - 3][y].setName(this.getPlayerName());
				board.board()[x][y].clear();
				board.board()[x - 2][y].setValue(b + 2);
				board.board()[x - 2][y].setName(this.getPlayerName());
				board.board()[x - 1][y].setValue(c + 1);
				board.board()[x - 1][y].setName(this.getPlayerName());
				result = true;
				return result;
			}
		case 1:
			if (steps == 0) {
				result = false;
				return result;
			}
			if (steps == 1) {
				int a = board.board()[x - 1][y + 1].getValue();
				int b = board.board()[x][y].getValue();
				board.board()[x - 1][y + 1].setValue(a + b);
				board.board()[x - 1][y + 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 2) {
				int a = board.board()[x - 2][y + 2].getValue();
				int b = board.board()[x - 1][y + 1].getValue();
				int c = board.board()[x][y].getValue();
				board.board()[x - 2][y + 2].setValue(a + c - 1);
				board.board()[x - 1][y + 1].setValue(b + 1);
				board.board()[x - 2][y + 2].setName(this.getPlayerName());
				board.board()[x - 1][y + 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 3) {
				int a = board.board()[x - 3][y + 3].getValue();
				int b = board.board()[x - 2][y + 2].getValue();
				int c = board.board()[x - 1][y + 1].getValue();
				int d = board.board()[x][y].getValue();
				board.board()[x - 3][y + 3].setValue(a + d - 3);
				board.board()[x - 3][y + 3].setName(this.getPlayerName());
				board.board()[x][y].clear();
				board.board()[x - 2][y + 2].setValue(b + 2);
				board.board()[x - 2][y + 2].setName(this.getPlayerName());
				board.board()[x - 1][y + 1].setValue(c + 1);
				board.board()[x - 1][y + 1].setName(this.getPlayerName());
				result = true;
				return result;
			}
		case 2:
			if (steps == 0) {
				result = false;
				return result;
			}
			if (steps == 1) {
				int a = board.board()[x][y + 1].getValue();
				int b = board.board()[x][y].getValue();
				board.board()[x][y + 1].setValue(a + b);
				board.board()[x][y + 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 2) {
				int a = board.board()[x][y + 2].getValue();
				int b = board.board()[x][y + 1].getValue();
				int c = board.board()[x][y].getValue();
				board.board()[x][y + 2].setValue(a + c - 1);
				board.board()[x][y + 1].setValue(b + 1);
				board.board()[x][y + 2].setName(this.getPlayerName());
				board.board()[x][y + 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 3) {
				int a = board.board()[x][y + 3].getValue();
				int b = board.board()[x][y + 2].getValue();
				int c = board.board()[x][y + 1].getValue();
				int d = board.board()[x][y].getValue();
				board.board()[x][y + 3].setValue(a + d - 3);
				board.board()[x][y + 3].setName(this.getPlayerName());
				board.board()[x][y].clear();
				board.board()[x][y + 2].setValue(b + 2);
				board.board()[x][y + 2].setName(this.getPlayerName());
				board.board()[x][y + 1].setValue(c + 1);
				board.board()[x][y + 1].setName(this.getPlayerName());
				result = true;
				return result;
			}
		case 3:
			if (steps == 0) {
				result = false;
				return result;
			}
			if (steps == 1) {
				int a = board.board()[x + 1][y + 1].getValue();
				int b = board.board()[x][y].getValue();
				board.board()[x + 1][y + 1].setValue(a + b);
				board.board()[x + 1][y + 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 2) {
				int a = board.board()[x + 2][y + 2].getValue();
				int b = board.board()[x + 1][y + 1].getValue();
				int c = board.board()[x][y].getValue();
				board.board()[x + 2][y + 2].setValue(a + c - 1);
				board.board()[x + 1][y + 1].setValue(b + 1);
				board.board()[x + 2][y + 2].setName(this.getPlayerName());
				board.board()[x + 1][y + 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 3) {
				int a = board.board()[x + 3][y + 3].getValue();
				int b = board.board()[x + 2][y + 2].getValue();
				int c = board.board()[x + 1][y + 1].getValue();
				int d = board.board()[x][y].getValue();
				board.board()[x + 3][y + 3].setValue(a + d - 3);
				board.board()[x + 3][y + 3].setName(this.getPlayerName());
				board.board()[x][y].clear();
				board.board()[x + 2][y + 2].setValue(b + 2);
				board.board()[x + 2][y + 2].setName(this.getPlayerName());
				board.board()[x + 1][y + 1].setValue(c + 1);
				board.board()[x + 1][y + 1].setName(this.getPlayerName());
				result = true;
				return result;
			}
		case 4:
			if (steps == 0) {
				result = false;
				return result;
			}
			if (steps == 1) {
				int a = board.board()[x + 1][y].getValue();
				int b = board.board()[x][y].getValue();
				board.board()[x + 1][y].setValue(a + b);
				board.board()[x + 1][y].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 2) {
				int a = board.board()[x + 2][y].getValue();
				int b = board.board()[x + 1][y].getValue();
				int c = board.board()[x][y].getValue();
				board.board()[x + 2][y].setValue(a + c - 1);
				board.board()[x + 1][y].setValue(b + 1);
				board.board()[x + 2][y].setName(this.getPlayerName());
				board.board()[x + 1][y].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 3) {
				int a = board.board()[x + 3][y].getValue();
				int b = board.board()[x + 2][y].getValue();
				int c = board.board()[x + 1][y].getValue();
				int d = board.board()[x][y].getValue();
				board.board()[x + 3][y].setValue(a + d - 3);
				board.board()[x + 3][y].setName(this.getPlayerName());
				board.board()[x][y].clear();
				board.board()[x + 2][y].setValue(b + 2);
				board.board()[x + 2][y].setName(this.getPlayerName());
				board.board()[x + 1][y].setValue(c + 1);
				board.board()[x + 1][y].setName(this.getPlayerName());
				result = true;
				return result;
			}

		case 5:
			if (steps == 0) {
				result = false;
				return result;
			}
			if (steps == 1) {
				int a = board.board()[x + 1][y - 1].getValue();
				int b = board.board()[x][y].getValue();
				board.board()[x + 1][y - 1].setValue(a + b);
				board.board()[x + 1][y - 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 2) {
				int a = board.board()[x + 2][y - 2].getValue();
				int b = board.board()[x + 1][y - 1].getValue();
				int c = board.board()[x][y].getValue();
				board.board()[x + 2][y - 2].setValue(a + c - 1);
				board.board()[x + 1][y - 1].setValue(b + 1);
				board.board()[x + 2][y - 2].setName(this.getPlayerName());
				board.board()[x + 1][y - 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 3) {
				int a = board.board()[x + 3][y - 3].getValue();
				int b = board.board()[x + 2][y - 2].getValue();
				int c = board.board()[x + 1][y - 1].getValue();
				int d = board.board()[x][y].getValue();
				board.board()[x + 3][y - 3].setValue(a + d - 3);
				board.board()[x + 3][y - 3].setName(this.getPlayerName());
				board.board()[x][y].clear();
				board.board()[x + 2][y - 2].setValue(b + 2);
				board.board()[x + 2][y - 2].setName(this.getPlayerName());
				board.board()[x + 1][y - 1].setValue(c + 1);
				board.board()[x + 1][y - 1].setName(this.getPlayerName());
				result = true;
				return result;
			}
		case 6:
			if (steps == 0) {
				result = false;
				return result;
			}
			if (steps == 1) {
				int a = board.board()[x][y - 1].getValue();
				int b = board.board()[x][y].getValue();
				board.board()[x][y - 1].setValue(a + b);
				board.board()[x][y - 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 2) {
				int a = board.board()[x][y - 2].getValue();
				int b = board.board()[x][y - 1].getValue();
				int c = board.board()[x][y].getValue();
				board.board()[x][y - 2].setValue(a + c - 1);
				board.board()[x][y - 1].setValue(b + 1);
				board.board()[x][y - 2].setName(this.getPlayerName());
				board.board()[x][y - 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 3) {
				int a = board.board()[x][y - 3].getValue();
				int b = board.board()[x][y - 2].getValue();
				int c = board.board()[x][y - 1].getValue();
				int d = board.board()[x][y].getValue();
				board.board()[x][y - 3].setValue(a + d - 3);
				board.board()[x][y - 3].setName(this.getPlayerName());
				board.board()[x][y].clear();
				board.board()[x][y - 2].setValue(b + 2);
				board.board()[x][y - 2].setName(this.getPlayerName());
				board.board()[x][y - 1].setValue(c + 1);
				board.board()[x][y - 1].setName(this.getPlayerName());
				result = true;
				return result;
			}

		case 7:
			if (steps == 0) {
				result = false;
				return result;
			}
			if (steps == 1) {
				int a = board.board()[x - 1][y - 1].getValue();
				int b = board.board()[x][y].getValue();
				board.board()[x - 1][y - 1].setValue(a + b);
				board.board()[x - 1][y - 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 2) {
				int a = board.board()[x - 2][y - 2].getValue();
				int b = board.board()[x - 1][y - 1].getValue();
				int c = board.board()[x][y].getValue();
				board.board()[x - 2][y - 2].setValue(a + c - 1);
				board.board()[x - 1][y - 1].setValue(b + 1);
				board.board()[x - 2][y - 2].setName(this.getPlayerName());
				board.board()[x - 1][y - 1].setName(this.getPlayerName());
				board.board()[x][y].clear();
				result = true;
				return result;
			}
			if (steps == 3) {
				int a = board.board()[x - 3][y - 3].getValue();
				int b = board.board()[x - 2][y - 2].getValue();
				int c = board.board()[x - 1][y - 1].getValue();
				int d = board.board()[x][y].getValue();
				board.board()[x - 3][y - 3].setValue(a + d - 3);
				board.board()[x - 3][y - 3].setName(this.getPlayerName());
				board.board()[x][y].clear();
				board.board()[x - 2][y - 2].setValue(b + 2);
				board.board()[x - 2][y - 2].setName(this.getPlayerName());
				board.board()[x - 1][y - 1].setValue(c + 1);
				board.board()[x - 1][y - 1].setName(this.getPlayerName());
				result = true;
				return result;
			}

		}
		return result;
	}

	public int computeSteps(int x, int y, int direction) {
		int remainStepCounter = 0;
		String playerName = null;
		int value = board.board()[x][y].getValue();
		switch (direction) {
		case 0:
			for (int i = x - 1; i >= 0; i--) {
				playerName = board.board()[i][y].getName();

				if (playerName.equalsIgnoreCase(this.getPlayerName())
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
				playerName = board.board()[--x][i].getName();
				if (playerName.equalsIgnoreCase(this.getPlayerName())
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
				playerName = board.board()[x][i].getName();
				if (playerName.equalsIgnoreCase(this.getPlayerName())
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

				playerName = board.board()[i][++y].getName();
				if (playerName.equalsIgnoreCase(this.getPlayerName())
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
				playerName = board.board()[i][y].getName();
				if (playerName.equalsIgnoreCase(this.getPlayerName())
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
				playerName = board.board()[i][--y].getName();
				if (playerName.equalsIgnoreCase(this.getPlayerName())
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
				playerName = board.board()[x][i].getName();
				if (playerName.equalsIgnoreCase(this.getPlayerName())
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
				playerName = board.board()[--x][i].getName();
				if (playerName.equalsIgnoreCase(this.getPlayerName())
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

	public void printBoard() {
		for (int i = 0; i < board.board().length; i++) {
			System.out.println();
			System.out.print("-----------------------------");
			for (int j = 0; j < board.board().length; j++) {
				if (j % 4 == 0) {
					System.out.println();
					System.out.printf("|");
				}
				if (board.board()[i][j].getName().equalsIgnoreCase("II")) {
					System.out.printf("%s", "******" + "|");
				} else
					System.out.printf("%s", board.board()[i][j] + "|");
			}
		}
		System.out.print("\n-----------------------------");
	}

	public void printInitialBoard() {

		for (int i = 0; i < board.board().length; i++) {
			System.out.println();
			System.out.print("---------------------------------");

			for (int j = 0; j < board.board().length; j++) {

				if (j % 4 == 0) {
					System.out.println();
					System.out.printf("|");
				}
				if (board.board()[i][j].getName().equalsIgnoreCase("II")) {
					System.out.printf("%s", "*******" + "|");
				} else
					System.out.printf("%s", board.board()[i][j] + "|");
			}
		}
		System.out.print("\n---------------------------------");

	}

	/**
	 * *************************************************************************
	 * **********
	 * ***************************************************************
	 * ********************
	 */

	public ArrayList<IntPair> findPosition(String player) {

		ArrayList<IntPair> position = new ArrayList<IntPair>(10);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board.board()[i][j].getName().equalsIgnoreCase(player)) {
					position.add(new IntPair(i, j));
				}
			}
		}
		return position;
	}

	public ArrayList<ArrayList<Integer>> findPossibleMoves(String player) {

		ArrayList<IntPair> position = findPosition(player);
		ArrayList<ArrayList<Integer>> choiceList = new ArrayList<ArrayList<Integer>>(
				40);
		if (position.size() == 0) {
			return null;
		} else {
			for (int j = 0; j < position.size(); j++) {
				for (int i = 0; i < 8; i++) {
					if (computeSteps(position.get(j).getRow(), position.get(j)
							.getColumn(), i) > 0) {
						ArrayList<Integer> choices = new ArrayList<Integer>();
						choices.add(position.get(j).getRow());
						choices.add(position.get(j).getColumn());
						choices.add(i);
						choiceList.add(choices);
					}
				}
			}
			return choiceList;
		}
	}

	public ArrayList<Integer> findPosiDirectionForOneCell(String player, int x,
			int y) {

		ArrayList<Integer> decrectionList = new ArrayList<Integer>(8);
		for (int i = 0; i < 8; i++) {
			if (computeSteps(x, y, i) > 0) {
				decrectionList.add(i);
			}
		}
		return decrectionList;
	}

	public int calPossibleDirections(String player) {

		int directions = 0;
		ArrayList<IntPair> position = findPosition(player);

		if (position.size() == 0) {
			return 0;
		} else {
			for (int j = 0; j < position.size(); j++) {
				for (int i = 0; i < 8; i++) {
					if (computeSteps(position.get(j).getRow(), position.get(j)
							.getColumn(), i) > 0) {
						directions++;
					}
				}
			}
			return directions;
		}
	}

	public ArrayList<Integer> randomMove(GameOfGotcha play) {
		String player = play.getPlayerName();
		boolean flagMove = true;
		final Random rand = new Random();
		ArrayList<IntPair> myPositons = findPosition(player);
		ArrayList<Integer> myChoices = new ArrayList<Integer>(3);
		int time = 0;

		while (flagMove) {
			int directionNum = rand.nextInt(8);
			int positionNum = rand.nextInt(myPositons.size());
			int rowNum = myPositons.get(positionNum).getRow();
			int columnNum = myPositons.get(positionNum).getColumn();

			if (play.computeSteps(rowNum, columnNum, directionNum) == 0) {
				flagMove = true;
			} else {
				myChoices.add(rowNum);
				myChoices.add(columnNum);
				myChoices.add(directionNum);
				flagMove = false;
				return myChoices;
			}
			++time;
			if (time > 200) {
				break;
			}
		}
		return myChoices;
	}

	public MyPair[][] copyBoard() {

		MyPair[][] copy = new MyPair[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy[i][j] = this.getBoard().board()[i][j].deepClone();
			}
		}

		return copy;
	}

	public GameBoard getDeepCopyBoard(GameBoard board) {
		GameBoard newBoard = new GameBoard();
		for (int i = 0; i < board.board().length; i++) {
			for (int j = 0; j < board.board().length; j++) {
				newBoard.board()[i][j] = board.board()[i][j].deepClone();
			}
		}
		return newBoard;
	}

	public void updateBoard(GameBoard newBoard) {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.getBoard().board()[i][j] = newBoard.board()[i][j]
						.deepClone();
			}
		}
	}

	public boolean checkIfSelfPosition(int x, int y) {

		ArrayList<IntPair> selfPosition = this.findPosition(this
				.getPlayerName());
		for (int i = 0; i < selfPosition.size(); i++) {
			if (selfPosition.get(i).ifEqual(x, y)) {
				return true;
			}
		}
		return false;

	}

	public boolean checkIfSelfDirections(int x, int y, int z) {

		ArrayList<Integer> selfDirection = this.findPosiDirectionForOneCell(
				this.getPlayerName(), x, y);
		return selfDirection.contains(z);

	}

	public GameBoard aiMove(GameOfGotcha player) {

		AIHeuristic moveMethod = new AIHeuristic();	
			return moveMethod.MinMax_BestMove_WithAlpha(player);
	}

	public static void showMenu() {
		System.out.println("\n**********Welcome to Game of Gotcha**********\n");
		System.out.println(">> Please select the game mode:");
		System.out.println(">> 1. Human-Human ");
		System.out.println(">> 2. Human-Computer (Human First , Start from P1)");
		System.out.println(">> 3. Human-Computer (Human First , Start from P2)");
		System.out.println(">> 4. Computer-Human (Computer First , Start from P2)");
		System.out.println(">> 5. Computer-Human (Computer First , Start from P1)");
		System.out.println(">> 6. Exit");
	}

	public static void showMenu2() {
		System.out
				.println("\n********Welcome to Human-Computer Mode********\n\n");		
	}


	/**
	 * *************************************************************************
	 * **********
	 * ***************************************************************
	 * ********************
	 */
	public static void main(String[] Args) {
		GameOfGotcha ge = new GameOfGotcha();
		GameOfGotcha P1 = new GameOfGotcha("P1");
		GameOfGotcha P2 = new GameOfGotcha("P2");

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int x = 0;
		int y = 0;
		char choiceY = ' ';
		int direction = 0;
		boolean flagP1 = false;
		boolean flagP2 = false;

		showMenu();
		Scanner keyboard = new Scanner(System.in);
		int userChoice = 0;
		int gameMode = 0;

		while (true) {

			Boolean valid = false;

			while (!valid) {
				try {
					userChoice = keyboard.nextInt();
					valid = true;
				} catch (Exception e) {
					System.out
							.println(">> Invalid Input, please enter an Integer");
					valid = false;
					keyboard.nextLine();
				}
			}

			switch (userChoice) {
			case 1:
				gameMode = 1;
				break;
			case 2:
				gameMode = 2;
				showMenu2();
				break;
			case 3:
				gameMode = 3;
				showMenu2();
				break;
			case 4:
				gameMode = 4;
				showMenu2();
				break;
			case 5:
				gameMode = 5;
				showMenu2();
				break;
			case 6:
				System.out.println("Thanks for playing!");
				keyboard.close();
				System.exit(0);
			default:
				System.out.println("Invalid Input, please try again.");
			}
			break;
		}

		if (gameMode == 2) {

			ge.printInitialBoard(); // Print out the board.board() at first
			System.out.println("\n\nYou are going to start from P1.\n");

			while (true) {
				boolean validx = false;
				boolean validy = false;
				boolean validz = false;
				boolean canMove = false;

				while (true) {

					while (!validx) {
						try {
							System.out
									.println("\nHere is your turn, please input the row x (1-4):");
							x = sc.nextInt() - 1; // minus 1 here
							validx = true;
						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter an Integer");
							validx = false;
							sc.nextLine();
						}
					}

					while (!validy) {
						try {
							System.out
									.println("x entered, please input the column y (A,B,C,D):");
							
							String input=sc.next();
							if(input.length()!=1){
								System.out
								.println(">> Invalid Input, please enter ONLY A Letter from A,B,C,D\n");
							}else{
								
								choiceY = input.toUpperCase().charAt(0);
								switch (choiceY){
								case 'A':
									y=0;
									validy = true;
									break;
								case 'B':
									y=1;
									validy = true;
									break;
								case 'C':
									y=2;
									validy = true;
									break;
								case 'D':
									y=3;
									validy = true;
									break;
								default:
									System.out
									.println(">> Invalid Input, please enter a Letter from A,B,C,D\n");
									System.out
									.println(">> Asking for re-input... \n");
								}
							}
							
							

						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter a Letter!\n");
							validy = false;
							sc.nextLine();
						}

					}

					if (!P1.checkIfSelfPosition(x, y)) {
						validx = false;
						validy = false;
						System.out.println("( " + (x + 1) + " , " + (y + 1)
								+ " ) is not your cell! Please re-input!\n");
					} else {
						break;
					}
				}

				while (true) {

					while (!validz) {
						try {
							System.out
									.println("P1("+x+","+choiceY+") entered,please choose a direction:");
							System.out.println("1. North	(up)		");
							System.out.println("2. North-East	(up-right)	");
							System.out.println("3. East		(right)		");
							System.out.println("4. South-East	(down-right)");
							System.out.println("5. South	(down)		");
							System.out.println("6. South-West	(down-left)	");
							System.out.println("7. West		(left)		");
							System.out.println("8. North-West	(up-left)	");
							direction = sc.nextInt() - 1;
							validz = true;
						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter an Integer");
							validz = false;
							sc.nextLine();
						}
						if (!P1.checkIfSelfDirections(x, y, direction)) {
							validz = false;
							System.out
									.println("You can not go in ths direction from ( "
											+ (x + 1)
											+ " , "
											+ (y + 1)
											+ " )! Please re-input!\n");
						} else {
							break;
						}
					}

					System.out
							.println("If you want to re-input, please enter *** here:");
					sc.nextLine();
					String ask = sc.nextLine();
					if (ask.equalsIgnoreCase("***")) {
						System.out.println("Asking re-input successfully!");
						break;
					} else {
						System.out.println("Input successfully, move!");
						canMove = true;
						break;
					}
				}

				if (canMove && validx == true && validy == true
						&& validz == true) {
					flagP1 = P1.move(x, y, direction);

					if (flagP1 == false) {
						System.out.println("\nUnfortunately..YOU LOSE");
						System.exit(0);
					}

					int possibleWay = P2.calPossibleDirections(P2
							.getPlayerName());
					if (possibleWay <= 0) {
						System.out.println("\nCongratulations! YOU WIN!");
						System.exit(0);
					} else {
						long time1 = System.currentTimeMillis();
						P2.updateBoard(P2.aiMove(P2));
						System.out
								.println("\nComputer(P2) has moved successfully!\n");
						P2.printBoard();
						long time2 = System.currentTimeMillis();
						System.out.println("\n\nThinking time of AI(P2) is: "
								+ (time2 - time1) + " milliseconds");
					}

					int possibleWay2 = P1.calPossibleDirections(P1
							.getPlayerName());
					if (possibleWay2 <= 0) {
						System.out.println("\nUnfortunately..YOU LOSE");
						System.exit(0);
					}
				}
			}

		}else if (gameMode == 3) {

			ge.printInitialBoard(); // Print out the board.board() at first
			System.out.println("\n\nYou are going to start from P2.\n");
			while (true) {
				boolean validx = false;
				boolean validy = false;
				boolean validz = false;
				boolean canMove = false;

				while (true) {

					while (!validx) {
						try {
							System.out
									.println("\nHere is your turn, please input the row x (1-4):");
							x = sc.nextInt() - 1; // minus 1 here
							validx = true;
						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter an Integer");
							validx = false;
							sc.nextLine();
						}
					}

					while (!validy) {
						try {
							System.out
									.println("x entered, please input the column y (A,B,C,D):");
							
							String input=sc.next();
							if(input.length()!=1){
								System.out
								.println(">> Invalid Input, please enter ONLY A Letter from A,B,C,D\n");
							}else{
								
								choiceY = input.toUpperCase().charAt(0);
								switch (choiceY){
								case 'A':
									y=0;
									validy = true;
									break;
								case 'B':
									y=1;
									validy = true;
									break;
								case 'C':
									y=2;
									validy = true;
									break;
								case 'D':
									y=3;
									validy = true;
									break;
								default:
									System.out
									.println(">> Invalid Input, please enter a Letter from A,B,C,D\n");
									System.out
									.println(">> Asking for re-input... \n");
								}
							}
							
							

						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter a Letter!\n");
							validy = false;
							sc.nextLine();
						}

					}

					if (!P2.checkIfSelfPosition(x, y)) {
						validx = false;
						validy = false;
						System.out.println("( " + (x + 1) + " , " + (y + 1)
								+ " ) is not your cell! Please re-input!\n");
					} else {
						break;
					}
				}

				while (true) {

					while (!validz) {
						try {
							System.out
									.println("P2("+x+","+choiceY+") entered,please choose a direction:");
							System.out.println("1. North	(up)		");
							System.out.println("2. North-East	(up-right)	");
							System.out.println("3. East		(right)		");
							System.out.println("4. South-East	(down-right)");
							System.out.println("5. South	(down)		");
							System.out.println("6. South-West	(down-left)	");
							System.out.println("7. West		(left)		");
							System.out.println("8. North-West	(up-left)	");
							direction = sc.nextInt() - 1;
							validz = true;
						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter an Integer");
							validz = false;
							sc.nextLine();
						}
						if (!P2.checkIfSelfDirections(x, y, direction)) {
							validz = false;
							System.out
									.println("You can not go in ths direction from ( "
											+ (x + 1)
											+ " , "
											+ (y + 1)
											+ " )! Please re-input!\n");
						} else {
							break;
						}
					}

					System.out
							.println("If you want to re-input, please enter *** here:");
					sc.nextLine();
					String ask = sc.nextLine();
					if (ask.equalsIgnoreCase("***")) {
						System.out.println("Asking re-input successfully!");
						break;
					} else {
						System.out.println("Input successfully, move!");
						canMove = true;
						break;
					}
				}

				if (canMove && validx == true && validy == true
						&& validz == true) {
					flagP2 = P2.move(x, y, direction);

					if (flagP2 == false) {
						System.out.println("\nUnfortunately..YOU LOSE");
						System.exit(0);
					}

					int possibleWay = P1.calPossibleDirections(P1
							.getPlayerName());
					if (possibleWay <= 0) {
						System.out.println("\nCongratulations! YOU WIN!");
						System.exit(0);
					} else {
						long time1 = System.currentTimeMillis();
						P1.updateBoard(P1.aiMove(P1));
						System.out
								.println("\nComputer(P1) has moved successfully!\n");
						P1.printBoard();
						long time2 = System.currentTimeMillis();
						System.out.println("\n\nThinking time of AI(P1) is: "
								+ (time2 - time1) + " milliseconds");
					}

					int possibleWay2 = P2.calPossibleDirections(P2
							.getPlayerName());
					if (possibleWay2 <= 0) {
						System.out.println("\nUnfortunately..YOU LOSE");
						System.exit(0);
					}
				}
			}

		}else if (gameMode == 4) {

			
			ge.printInitialBoard(); // Print out the board.board() at first
			System.out.println("\n\nComputer is going to start from P2.\n");

			while (true) {
				
				int possibleWay = P2.calPossibleDirections(P2
						.getPlayerName());
				if (possibleWay <= 0) {
					System.out.println("\nCongratulations! YOU WIN!");
					System.exit(0);
				} else {
					long time1 = System.currentTimeMillis();
					P2.updateBoard(P2.aiMove(P2));
					System.out
							.println("\nComputer(P2) has moved successfully!\n");
					P2.printBoard();
					long time2 = System.currentTimeMillis();
					System.out.println("\n\nThinking time of AI(P2) is: "
							+ (time2 - time1) + " milliseconds");
				}

				int possibleWay2 = P1.calPossibleDirections(P1
						.getPlayerName());
				if (possibleWay2 <= 0) {
					System.out.println("\nUnfortunately..YOU LOSE");
					System.exit(0);
				}
				
				boolean validx = false;
				boolean validy = false;
				boolean validz = false;

				while (true) {

					while (!validx) {
						try {
							System.out
									.println("\nHere is your turn, please input the row x (1-4):");
							x = sc.nextInt() - 1; // minus 1 here
							validx = true;
						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter an Integer");
							validx = false;
							sc.nextLine();
						}
					}

					while (!validy) {
						try {
							System.out
									.println("x entered, please input the column y (A,B,C,D):");
							
							String input=sc.next();
							if(input.length()!=1){
								System.out
								.println(">> Invalid Input, please enter ONLY A Letter from A,B,C,D\n");
							}else{
								
								choiceY = input.toUpperCase().charAt(0);
								switch (choiceY){
								case 'A':
									y=0;
									validy = true;
									break;
								case 'B':
									y=1;
									validy = true;
									break;
								case 'C':
									y=2;
									validy = true;
									break;
								case 'D':
									y=3;
									validy = true;
									break;
								default:
									System.out
									.println(">> Invalid Input, please enter a Letter from A,B,C,D\n");
									System.out
									.println(">> Asking for re-input... \n");
								}
							}
							
							

						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter a Letter!\n");
							validy = false;
							sc.nextLine();
						}

					}

					if (!P1.checkIfSelfPosition(x, y)) {
						validx = false;
						validy = false;
						System.out.println("( " + (x + 1) + " , " + (y + 1)
								+ " ) is not your cell! Please re-input!\n");
					} else {
						break;
					}
				}

				while (true) {

					while (!validz) {
						try {
							System.out
									.println("P1("+x+","+choiceY+") entered,please choose a direction:");
							System.out.println("1. North	(up)		");
							System.out.println("2. North-East	(up-right)	");
							System.out.println("3. East		(right)		");
							System.out.println("4. South-East	(down-right)");
							System.out.println("5. South	(down)		");
							System.out.println("6. South-West	(down-left)	");
							System.out.println("7. West		(left)		");
							System.out.println("8. North-West	(up-left)	");
							direction = sc.nextInt() - 1;
							validz = true;
						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter an Integer");
							validz = false;
							sc.nextLine();
						}
						if (!P1.checkIfSelfDirections(x, y, direction)) {
							validz = false;
							System.out
									.println("You can not go in ths direction from ( "
											+ (x + 1)
											+ " , "
											+ (y + 1)
											+ " )! Please re-input!\n");
						} else {
							break;
						}
					}

					System.out
							.println("If you want to re-input, please enter *** here:");
					sc.nextLine();
					String ask = sc.nextLine();
					if (ask.equalsIgnoreCase("***")) {
						System.out.println("Asking re-input successfully!");
						break;
					} else {
						System.out.println("Input successfully, move!");
						break;
					}
				}

				
					flagP1 = P1.move(x, y, direction);

					if (flagP1 == false) {
						System.out.println("\nUnfortunately..YOU LOSE");
						System.exit(0);
					}


				
			}

		}else if (gameMode == 5) {

			ge.printInitialBoard(); // Print out the board.board() at first
			System.out.println("\n\nComputer is going to start from P1.\n");

			while (true) {
				
				int possibleWay = P1.calPossibleDirections(P1
						.getPlayerName());
				if (possibleWay <= 0) {
					System.out.println("\nCongratulations! YOU WIN!");
					System.exit(0);
				} else {
					long time1 = System.currentTimeMillis();
					P1.updateBoard(P1.aiMove(P1));
					System.out
							.println("\nComputer has moved successfully!\n");
					P1.printBoard();
					long time2 = System.currentTimeMillis();
					System.out.println("\n\nThinking time of AI is: "
							+ (time2 - time1) + " milliseconds");
				}

				int possibleWay2 = P2.calPossibleDirections(P2
						.getPlayerName());
				if (possibleWay2 <= 0) {
					System.out.println("\nUnfortunately..YOU LOSE");
					System.exit(0);
				}
				
				boolean validx = false;
				boolean validy = false;
				boolean validz = false;

				while (true) {

					while (!validx) {
						try {
							System.out
									.println("\nHere is your turn, please input the row x (1-4):");
							x = sc.nextInt() - 1; // minus 1 here
							validx = true;
						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter an Integer");
							validx = false;
							sc.nextLine();
						}
					}

					while (!validy) {
						try {
							System.out
									.println("x entered, please input the column y (A,B,C,D):");
							
							String input=sc.next();
							if(input.length()!=1){
								System.out
								.println(">> Invalid Input, please enter ONLY A Letter from A,B,C,D\n");
							}else{
								
								choiceY = input.toUpperCase().charAt(0);
								switch (choiceY){
								case 'A':
									y=0;
									validy = true;
									break;
								case 'B':
									y=1;
									validy = true;
									break;
								case 'C':
									y=2;
									validy = true;
									break;
								case 'D':
									y=3;
									validy = true;
									break;
								default:
									System.out
									.println(">> Invalid Input, please enter a Letter from A,B,C,D\n");
									System.out
									.println(">> Asking for re-input... \n");
								}
							}
							
							

						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter a Letter!\n");
							validy = false;
							sc.nextLine();
						}

					}

					if (!P2.checkIfSelfPosition(x, y)) {
						validx = false;
						validy = false;
						System.out.println("( " + (x + 1) + " , " + (y + 1)
								+ " ) is not your cell! Please re-input!\n");
					} else {
						break;
					}
				}

				while (true) {

					while (!validz) {
						try {
							System.out
									.println("P2("+x+","+choiceY+") entered,please choose a direction:");
							System.out.println("1. North	(up)		");
							System.out.println("2. North-East	(up-right)	");
							System.out.println("3. East		(right)		");
							System.out.println("4. South-East	(down-right)");
							System.out.println("5. South	(down)		");
							System.out.println("6. South-West	(down-left)	");
							System.out.println("7. West		(left)		");
							System.out.println("8. North-West	(up-left)	");
							direction = sc.nextInt() - 1;
							validz = true;
						} catch (Exception e) {
							System.out
									.println(">> Invalid Input, please enter an Integer");
							validz = false;
							sc.nextLine();
						}
						if (!P2.checkIfSelfDirections(x, y, direction)) {
							validz = false;
							System.out
									.println("You can not go in ths direction from ( "
											+ (x + 1)
											+ " , "
											+ (y + 1)
											+ " )! Please re-input!\n");
						} else {
							break;
						}
					}

					System.out
							.println("If you want to re-input, please enter *** here:");
					sc.nextLine();
					String ask = sc.nextLine();
					if (ask.equalsIgnoreCase("***")) {
						System.out.println("Asking re-input successfully!");
						break;
					} else {
						System.out.println("Input successfully, move!");
						break;
					}
				}

				
					flagP2 = P2.move(x, y, direction);

					if (flagP2 == false) {
						System.out.println("\nUnfortunately..YOU LOSE");
						System.exit(0);
					}


				
			}

		}else if (gameMode == 1) {

			System.out.println("\n******************************************");
			System.out.println("*****You are playing Human-Human mode*****");
			System.out
					.println("******************************************\n\n");
			ge.printInitialBoard(); // Print out the board at first
			while (true) {
				System.out.println();
				System.out
						.println("Here is P1's turn, please input the row x(1-4):");
				x = sc.nextInt() - 1; // minus 1 here
				System.out
						.println("x entered, please input the column y(1-4):");
				y = sc.nextInt() - 1; // minus 1 here

				System.out
						.println("P1(x,y) entered,please choose a direction:");
				System.out.println("1. North	(up)		");
				System.out.println("2. North-East	(up-right)	");
				System.out.println("3. East		(right)		");
				System.out.println("4. South-East	(down-right)");
				System.out.println("5. South	(down)		");
				System.out.println("6. South-West	(down-left)	");
				System.out.println("7. West		(left)		");
				System.out.println("8. North-West	(up-left)	");
				direction = sc.nextInt() - 1;
				System.out.println("Done!");
				flagP1 = P1.move(x, y, direction);
				if (flagP1 == false) {
					System.out.println("\nUnfortunately..P1 LOSE");
					System.exit(0);
				} else {
					int possibleWay = P2.calPossibleDirections(P2
							.getPlayerName());
					if (possibleWay <= 0) {
						System.out.println("\nCongratulations! P1 WIN!");
						System.exit(0);
					}
				}
				P1.printBoard();
				System.out.println();
				System.out
						.println("Here is P2's turn, please input the row x(1-4):");
				x = sc.nextInt() - 1; // minus 1 here
				System.out
						.println("x entered, please input the column y(1-4):");
				y = sc.nextInt() - 1; // minus 1 here
				System.out
						.println("P2(x,y) entered,please choose a direction:");

				System.out.println("1. North	(up)		");
				System.out.println("2. North-East	(up-right)	");
				System.out.println("3. East		(right)		");
				System.out.println("4. South-East	(down-right)");
				System.out.println("5. South	(down)		");
				System.out.println("6. South-West	(down-left)	");
				System.out.println("7. West		(left)		");
				System.out.println("8. North-West	(up-left)	");
				direction = sc.nextInt() - 1;
				System.out.println("Done!");

				flagP2 = P2.move(x, y, direction);
				if (flagP2 == false) {
					System.out.println("\nUnfortunately..P2 LOSE");
					System.exit(0);
				} else {
					int possibleWay2 = P1.calPossibleDirections(P1
							.getPlayerName());
					if (possibleWay2 <= 0) {
						System.out.println("\nCongratulations! P2 WIN!");
						System.exit(0);
					}
				}
				P2.printBoard();

			}

		} 
	}
}
