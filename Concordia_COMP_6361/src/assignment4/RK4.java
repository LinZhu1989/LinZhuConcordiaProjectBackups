package assignment4;

import assignment2.Ass2Main;

public class RK4 {

	private int N; // by default
	private double[] cV;
	private double[] nV;
	private double deltaT;
	private double mu;
	private double lamda;
	private double[][] K;

	public RK4(double deT, double[] ini, double m) {

		this.N = ini.length;
		this.cV = new double[this.N];
		this.nV = new double[this.N];
		this.K = new double[4][this.N];
		Ass2Main.copyArray(ini, this.cV);
		this.deltaT = deT;
		this.mu = m;
		this.lamda=0;
		calNextValues();
	}

	public void calNextValues() {

		calculateK();
		for (int i = 0; i < this.N; i++) {
			this.nV[i] = this.cV[i]
					+ (this.deltaT / 6)
					* (this.K[0][i] + 2 * this.K[1][i] + 2 * this.K[2][i] + this.K[3][i]);
		}
	}

	public double[] getNextValues() {

		return this.nV;
	}

	private void calculateK() {
		Ass2Main.copyArray(function(this.cV), this.K[0]);
		Ass2Main.copyArray(
				function(addArray(this.cV, this.K[0], 1, this.deltaT / 2)),
				this.K[1]);
		Ass2Main.copyArray(
				function(addArray(this.cV, this.K[1], 1, this.deltaT / 2)),
				this.K[2]);
		Ass2Main.copyArray(
				function(addArray(this.cV, this.K[2], 1, this.deltaT)),
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
		values[0] = 1 * u[3];
		values[1] = 1 * u[4];
		values[2] = 1 * u[5];
		values[3] = 1
				* (2 * u[4] + u[0] - (1 - mu) * (u[0] + mu)
						* Math.pow(getR1(), -3) - mu * (u[0] - 1 + mu)
						* Math.pow(getR2(), -3) );
		values[4] = 1
				* (-2 * u[3] + u[1] - (1 - mu) * u[1]
						* Math.pow(getR1(), -3) - mu * u[1]
						* Math.pow(getR2(), -3) );
		values[5] = 1
				* (-(1 - mu) * u[2] * Math.pow(getR1(), -3) - mu * u[2]
						* Math.pow(getR2(), -3) );
//		values[0] = T * cV[3];
//		values[1] = T * cV[4];
//		values[2] = T * cV[5];
//		values[3] = T
//				* (2 * cV[4] + cV[0] - (1 - mu) * (cV[0] + mu)
//						* Math.pow(getR1(), -3) - mu * (cV[0] - 1 + mu)
//						* Math.pow(getR2(), -3) + lamda * cV[3]);
//		values[4] = T
//				* (-2 * cV[3] + cV[1] - (1 - mu) * cV[1]
//						* Math.pow(getR1(), -3) - mu * cV[1]
//						* Math.pow(getR2(), -3) + lamda * cV[4]);
//		values[5] = T
//				* (-(1 - mu) * cV[2] * Math.pow(getR1(), -3) - mu * cV[2]
//						* Math.pow(getR2(), -3) + lamda * cV[5]);
		return values;
	}

	private double getR1() {
		return Math.sqrt(Math.pow((cV[0] + mu), 2) + Math.pow(cV[1], 2)
				+ Math.pow(cV[2], 2));
	}

	private double getR2() {
		return Math.sqrt(Math.pow((cV[0] - 1 + mu), 2) + Math.pow(cV[1], 2)
				+ Math.pow(cV[2], 2));
	}

}
