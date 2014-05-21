package assignment1_Double_Version;

public class LinearTridiagonalSystemSolver {

	private TridiagonalMatrix JacobiMatrix;
	private int dimension;
	private double[] changeValueInMatrix;
	private double[] F;
	private double[] deltaU;
	private double deltaT;
	private double deltaX;
	private int problemType;
	private double lamda;
	private double[] knownValues;
	private double[] tempValues;
	private double[] finalValue;

	public LinearTridiagonalSystemSolver(double dT, double dX, double[] knowns,
			double[] guesses, int proType, double lamd) {
		this.deltaT = dT;
		this.deltaX = dX;
		this.lamda = lamd;
		this.dimension = knowns.length;
		this.changeValueInMatrix = new double[this.dimension];
		this.F = new double[this.dimension];
		this.deltaU = new double[this.dimension];
		this.knownValues =  new double[this.dimension];
		this.tempValues =  new double[this.dimension];
		MainTest.copyArray(knownValues, knowns);
		MainTest.copyArray(tempValues, guesses);

		this.finalValue = new double[this.dimension];
		this.JacobiMatrix = new TridiagonalMatrix(this.dimension);
		this.problemType = proType;
		this.doCalculation();
	}

	public double[] getResultvalues() {
		return this.finalValue;

	}

	public void changeTempFutureValues(double[] newValues) {
		MainTest.copyArray(tempValues, newValues);
	
		this.doCalculation();
	}

	public double[] getDeltaValue() {
		return this.deltaU;
	}

	private void doCalculation() {
		this.buildJacobiMatrix();
		this.getValuesOfF();
		this.calDeltaU();
		this.updateFinalValues();
	}

	private void calDeltaU() {
		GaussElimination GE = new GaussElimination(this.JacobiMatrix, this.F);
		MainTest.copyArray(deltaU, GE.getDeltaU());

	}

	private void updateFinalValues() {
		for (int i = 0; i < this.tempValues.length; i++) {
			this.finalValue[i] = this.tempValues[i] + this.deltaU[i];
		}

	}

	private void buildJacobiMatrix() {
		this.JacobiMatrix.setLowerValue(-this.deltaT / (this.deltaX)
				/ (this.deltaX));
		this.JacobiMatrix.setUpperValue(-this.deltaT / (this.deltaX)
				/ (this.deltaX));
		this.JacobiMatrix.setMiddleValueConstant(1 + 2 * this.deltaT
				/ (this.deltaX) / (this.deltaX));
		this.JacobiMatrix.setMiddleValueVariable(this.getValuesOfD(
				this.problemType, this.tempValues));
	}

	private double[] getValuesOfD(int proType, double[] uJ) {
		switch (proType) {
		case 1:
			for (int i = 0; i < this.changeValueInMatrix.length; i++) {
				this.changeValueInMatrix[i] = -this.deltaT
						* this.lamda*(1 - 2 * uJ[i]);
			}
			// f(u) = lamda * u (1 锟?u) for the Fisher equation
			// f'(u) = lamda*(1 - 2 * u)
			break;
		case 2:
			for (int i = 0; i < this.changeValueInMatrix.length; i++) {

				this.changeValueInMatrix[i] = -this.deltaT
						* (this.lamda * Math.pow(Math.E, uJ[i]));
			}
			// f (u) = 位 e^u for the Gelfand-Bratu equation
			// f'(u) = 位 e^u
			break;
		case 3:
			for (int i = 0; i < this.changeValueInMatrix.length; i++) {

				this.changeValueInMatrix[i] = -this.deltaT
						* (this.lamda * 1+3*Math.pow(uJ[i], 2)-5*Math.pow(uJ[i], 4));
			}
			// f (u) = 位 (u+u^3-u^5) for the Gelfand-Bratu equation
			// f'(u) = 位 (1+3u^2-5u^4)
			break;
		}
		return this.changeValueInMatrix;

	}

	private void getValuesOfF() {
		switch (this.problemType) {
		case 1:
			this.F[0] = -(this.tempValues[0] - this.knownValues[0]
					- this.deltaT / (this.deltaX) / (this.deltaX)
					* (0 - 2 * this.tempValues[0] + this.tempValues[1]) - this.deltaX
					* this.lamda
					* this.tempValues[0]
					* (1 - this.tempValues[0]));

			for (int i = 1; i < this.F.length - 1; i++) {
				this.F[i] = -(this.tempValues[i]
						- this.knownValues[i]
						- this.deltaT
						/ (this.deltaX)
						/ (this.deltaX)
						* (this.tempValues[i - 1] - 2 * this.tempValues[i] + this.tempValues[i + 1]) - this.deltaX
						* this.lamda
						* this.tempValues[i]
						* (1 - this.tempValues[i]));
			}
			this.F[this.F.length - 1] = -(this.tempValues[this.F.length - 1]
					- this.knownValues[this.F.length - 1]
					- this.deltaT
					/ (this.deltaX)
					/ (this.deltaX)
					* (this.tempValues[this.F.length - 2] - 2
							* this.tempValues[this.F.length - 1] + 0) - this.deltaX
					* this.lamda
					* this.tempValues[this.F.length - 1]
					* (1 - this.tempValues[this.F.length - 1]));
			break;
		case 2:
			this.F[0] = -(this.tempValues[0] - this.knownValues[0]
					- this.deltaT / (this.deltaX) / (this.deltaX)
					* (0 - 2 * this.tempValues[0] + this.tempValues[1]) - this.deltaX
					* this.lamda * Math.pow(Math.E, this.tempValues[0]));

			for (int i = 1; i < this.F.length - 1; i++) {
				this.F[i] = -(this.tempValues[i]
						- this.knownValues[i]
						- this.deltaT
						/ (this.deltaX)
						/ (this.deltaX)
						* (this.tempValues[i - 1] - 2 * this.tempValues[i] + this.tempValues[i + 1]) - this.deltaX
						* this.lamda * Math.pow(Math.E, this.tempValues[i]));
			}
			this.F[this.F.length - 1] = -(this.tempValues[this.F.length - 1]
					- this.knownValues[this.F.length - 1]
					- this.deltaT
					/ (this.deltaX)
					/ (this.deltaX)
					* (this.tempValues[this.F.length - 2] - 2
							* this.tempValues[this.F.length - 1] + 0) - this.deltaX
					* this.lamda
					* Math.pow(Math.E, this.tempValues[this.F.length - 1]));
			break;
		case 3:
			this.F[0] = -(this.tempValues[0] - this.knownValues[0]
					- this.deltaT / (this.deltaX) / (this.deltaX)
					* (0 - 2 * this.tempValues[0] + this.tempValues[1]) - this.deltaX
					* this.lamda
					* this.tempValues[0] + this.tempValues[0]
							* this.tempValues[0] * this.tempValues[0] - this.tempValues[0]
							* this.tempValues[0]
							* this.tempValues[0]
							* this.tempValues[0] * this.tempValues[0]);

			for (int i = 1; i < this.F.length - 1; i++) {
				this.F[i] = -(this.tempValues[i]
						- this.knownValues[i]
						- this.deltaT
						/ (this.deltaX)
						/ (this.deltaX)
						* (this.tempValues[i - 1] - 2 * this.tempValues[i] + this.tempValues[i + 1]) - this.deltaX
						* this.lamda * this.tempValues[i] + this.tempValues[i]
								* this.tempValues[i] * this.tempValues[i] - this.tempValues[i]
										* this.tempValues[i]
										* this.tempValues[i]
										* this.tempValues[i] * this.tempValues[i]);
			}
			this.F[this.F.length - 1] = -(this.tempValues[this.F.length - 1]
					- this.knownValues[this.F.length - 1]
					- this.deltaT
					/ (this.deltaX)
					/ (this.deltaX)
					* (this.tempValues[this.F.length - 2] - 2
							* this.tempValues[this.F.length - 1] + 0) - this.deltaX
					* this.lamda
					* this.tempValues[this.F.length - 1] + this.tempValues[this.F.length - 1]
							* this.tempValues[this.F.length - 1] * this.tempValues[this.F.length - 1] - this.tempValues[this.F.length - 1]
							* this.tempValues[this.F.length - 1]
							* this.tempValues[this.F.length - 1]
							* this.tempValues[this.F.length - 1] * this.tempValues[this.F.length - 1]);
			break;
		}
	}

}
