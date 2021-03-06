package assignment2;

public class Runge_Kutta_4th {

	private int problemType;
	private int N = 3; // by default
	private double[] curValues;
	private double[] nextValues;
	private double deltaT;

	private double D;
	private double sigma;
	private double beta;
	private double alpha;
	private double B;

	private double[][] K;

	public Runge_Kutta_4th(int proType, double deT, double[] ini, double dValues,
			double sigmaValues, double betaValues, double alphaValues,
			double bValues) {

		this.problemType = proType;
		this.N = ini.length;
		this.curValues = new double[this.N];
		this.nextValues = new double[this.N];
		this.K = new double[4][this.N];

		Ass2Main.copyArray(ini, this.curValues);

		this.deltaT = deT;
		this.D = dValues;
		this.sigma = sigmaValues;
		this.beta = betaValues;
		this.alpha = alphaValues;
		this.B = bValues;

	}

	public double[] getNextValues() {

		calculateK();
		for (int i = 0; i < this.N; i++) {
			this.nextValues[i] = this.curValues[i]
					+ (this.deltaT / 6)
					* (this.K[0][i] + 2 * this.K[1][i] + 2 * this.K[2][i] + this.K[3][i]);
		}

		return this.nextValues;
	}

	private void calculateK() {
		Ass2Main.copyArray(function(this.curValues), this.K[0]);
		Ass2Main.copyArray(
				function(addArray(this.curValues, this.K[0], 1, this.deltaT / 2)),
				this.K[1]);
		Ass2Main.copyArray(
				function(addArray(this.curValues, this.K[1], 1, this.deltaT / 2)),
				this.K[2]);
		Ass2Main.copyArray(
				function(addArray(this.curValues, this.K[2], 1, this.deltaT)),
				this.K[3]);

	}

	private double[] addArray(double[] u1, double[] u2, double k1, double k2) {
		assert (u1.length == u2.length);
		double[] values = new double[u1.length];
		for (int i = 0; i < u1.length; i++) {
			values[i] = k1 * u1[i] + k2 * u2[i];
		}
		return values;

	}

	private double[] function(double[] u) {
		double[] values = new double[this.N];
		if (this.problemType == 1) {
			values[0] = -u[0] + this.D * (1 - u[0]) * Math.pow(Math.E, u[2]);
			values[1] = -u[1] + this.D * (1 - u[0]) * Math.pow(Math.E, u[2])
					- this.D * this.sigma * u[1] * Math.pow(Math.E, u[2]);
			values[2] = -u[2] - this.beta * u[2] + this.D * this.B * (1 - u[0])
					* Math.pow(Math.E, u[2]) + this.D * this.B * this.alpha
					* this.sigma * u[1] * Math.pow(Math.E, u[2]);
		}
		return values;
	}

}
