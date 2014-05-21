package assignment1_Double_Version;

/**
 * Using Implicit Euler
 */
public class NonlinearDiffusionEquationsSolver {
	private double deltaT;
	private double deltaX;
	private double maxT; // by default, starts from 0
	private double maxX; // by default, starts from 0
	private int T; // \
	private int N; //
	private double lamda;

	private int problemType;
	private Function iniFunction;
	private int funcType;
	private double[] xValues;
	private double[] initialValues;

	private double[] valuesAtCurrentTimeLevel;
	private double[] valuesAtNextTimeLevel;
	private double[][] allValues;
	private double[] integralValue;

	private int currentLevel;

	public NonlinearDiffusionEquationsSolver(double dT, double dX, double mT,
			double mX, int iniFun, int proType, double lamd) {
		this.deltaT = dT;
		this.deltaX = dX;
		this.lamda = lamd;
		this.maxT = mT;
		this.maxX = mX;
		this.T = (int) (this.maxT / this.deltaT) + 1;
		this.N = (int) (this.maxX / this.deltaX) - 1;
		this.problemType = proType;
		this.funcType = iniFun;
		this.iniFunction = new Function(this.funcType, this.N);
		initalXValues();
		this.initialValues = this.iniFunction.getFuncValues(this.xValues);
		this.valuesAtCurrentTimeLevel = this.initialValues;
		this.valuesAtNextTimeLevel = new double[this.N];
		this.allValues = new double[this.T][this.N + 2];
		this.integralValue = new double[this.T];
		this.currentLevel = 0;
		setKnownBoundaryValues();
		doAllCalculation();
	}

	private void initalXValues() {
		this.xValues = new double[this.N];
		xValues[0] = 0 + this.deltaX;
		for (int i = 1; i < this.N; i++) {
			this.xValues[i] = this.xValues[i - 1] + this.deltaX;
		}
	}

	public void doAllCalculation() {

		for (int i = 0; i < this.T - 1; i++) {
			this.currentLevel++;
			NewtonIteration solver = new NewtonIteration(this.deltaT,
					this.deltaX, this.valuesAtCurrentTimeLevel,
					this.problemType, this.lamda);
			MainTest.copyArray(valuesAtNextTimeLevel, solver.getValuesAtNextTimeLevel());
			
		
			for (int j = 0; j < this.N; j++) {
				this.allValues[this.currentLevel][j + 1] = this.valuesAtNextTimeLevel[j];
			}
			if (this.stableCondition()) {
				break;
			}
			
			MainTest.copyArray(this.valuesAtCurrentTimeLevel,this.valuesAtNextTimeLevel);
		}
	}

	private boolean stableCondition() {
		double maxCurrent = 0.0;
		double maxNext = 0.0;

		for (int i = 0; i < this.valuesAtCurrentTimeLevel.length; i++) {
			if(this.valuesAtCurrentTimeLevel[i]>maxCurrent){
				maxCurrent=this.valuesAtCurrentTimeLevel[i];
			}
		}
		for (int i = 0; i < this.valuesAtNextTimeLevel.length; i++) {
			if(this.valuesAtNextTimeLevel[i]>maxCurrent){
				maxNext=this.valuesAtNextTimeLevel[i];
			}
		}
		if (Math.abs(maxNext - maxCurrent) / this.deltaT <= 0.000001) {
			return true;
		} else {
			return false;
		}
	}

	private void setKnownBoundaryValues() {
		for (int i = 0; i < this.T; i++) {
			this.allValues[i][0] = 0;
			this.allValues[i][this.N + 1] = 0;
		}
		for (int j = 0; j < this.N; j++) {
			this.allValues[0][j + 1] = this.initialValues[j];
		}

	}
	
	public int getCurrentLevel(){
		return this.currentLevel;
	}

	public double[][] getAllResultValues() {
		return this.allValues;
	}

	public double[] getAllIntegralValue() {
		double sum = 0;
		for (int i = 0; i < this.allValues.length; i++) {
			sum = 0;
			for (int j = 0; j < this.allValues[0].length; j++) {
				sum += this.allValues[i][j] * this.deltaX;
			}
			this.integralValue[i] = sum;
		}
		return this.integralValue;
	}
}
