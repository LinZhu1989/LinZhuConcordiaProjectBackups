package assignment1_Double_Version;

public class TridiagonalMatrix {

	private int dimension;
	private double[][] values;
	private double[] lowers;
	private double[] uppers;
	private double[] middles;

	public TridiagonalMatrix() {

	}

	public TridiagonalMatrix(int d) {
		this.dimension = d;
		this.values = new double[d][d];
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				this.values[i][j] = 0.000000000000000;
			}
		}
		this.lowers = new double[this.dimension - 1];
		this.uppers = new double[this.dimension - 1];
		this.middles = new double[this.dimension];
	}

	public double[][] getValues() {
		return this.values;
	}

	public int getDimension() {
		return this.dimension;
	}

	public double[] getLowerValues() {

		return this.lowers;
	}

	public double[] getUpperValues() {

		return this.uppers;
	}

	public double[] getMiddleValues() {

		return this.middles;
	}

	public double getTheLowerValue() {
		return this.values[1][0];
	}

	public double getTheUpperValue() {
		return this.values[0][1];
	}

	public double getTheMiddleValueWithIndex(int i) {
		return this.values[i][i];
	}

	public void setLowerValue(double lower) {
		for (int i = 1; i < this.dimension; i++) {
			this.values[i][i - 1] = lower;
		}

		for (int i = 0; i < this.dimension - 1; i++) {
			lowers[i] = this.getTheLowerValue();
		}
	}

	public void setUpperValue(double upper) {
		for (int i = 0; i < this.dimension - 1; i++) {
			this.values[i][i + 1] = upper;
		}

		for (int i = 0; i < this.dimension - 1; i++) {
			uppers[i] = this.getTheUpperValue();
		}
	}

	public void setMiddleValueConstant(double middleConstant) {
		for (int i = 0; i < this.dimension; i++) {
			this.values[i][i] += middleConstant;
			middles[i] += middleConstant;
		}

	}

	public void setMiddleValueVariable(double[] middleVariable) {
		if (middleVariable.length == this.dimension) {
			for (int i = 0; i < this.dimension; i++) {
				this.values[i][i] += middleVariable[i];
				middles[i] += middleVariable[i];
			}

		} else {

		}

	}

}
