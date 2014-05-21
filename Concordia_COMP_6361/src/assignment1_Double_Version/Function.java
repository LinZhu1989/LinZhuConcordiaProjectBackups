/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package assignment1_Double_Version;

/**
 * 
 */
public class Function {

	private int functionType;
	private int variableSize;

	public Function(int type, int size) {
		this.functionType = type;
		this.variableSize = size;
	}

	public double[] getFuncValues(double[] indVars) {
		switch (this.functionType) {
		case 1:
			return this.sin(indVars);
		case 2:
			return this.cosX(indVars);
		case 3:
			return this.constanT(0.500000);
		case 4:
			return this.constanT(0.100000);
		case 5:
			return this.constanT(0.000000);
		default:
			return this.constanT(0.000000);
		}
	}

	private double[] sin(double[] vars) {

		double result[] = new double[this.variableSize];
		for (int i = 0; i < this.variableSize; i++) {
			result[i] = Math.sin(vars[i]*Math.PI)/10;
		}

		return result;
	}

	private double[] cosX(double[] vars) {

		double result[] = new double[this.variableSize];
		for (int i = 0; i < this.variableSize; i++) {
			result[i] = Math.cos(vars[i]);
		}

		return result;
	}

	private double[] constanT(double cst) {

		double result[] = new double[this.variableSize];
		for (int i = 0; i < this.variableSize; i++) {
			result[i] = cst;
		}

		return result;
	}
}
