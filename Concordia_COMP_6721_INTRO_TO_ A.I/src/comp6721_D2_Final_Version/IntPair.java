package comp6721_D2_Final_Version;

/**
 * class IntPair
 * 
 * COMP 472/672: Artificial Intelligence Project Deliverable II
 * 
 * @author Yulong Song 6516599 Lin Zhu 6555659
 * 
 */

class IntPair {
	private int row;
	private int column;

	IntPair() {

	}

	IntPair(int x, int y) {
		this.row = x;
		this.column = y;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int x) {
		this.row = x;
	}

	public void setColumn(int y) {
		this.column = y;
	}

	public boolean ifEqual(int x, int y) {

		if (this.row == x) {
			if (this.column == y) {
				return true;
			}
		}
		return false;
	}

}
