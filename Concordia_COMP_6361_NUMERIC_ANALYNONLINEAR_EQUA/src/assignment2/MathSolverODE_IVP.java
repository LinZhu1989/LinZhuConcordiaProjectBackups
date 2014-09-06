package assignment2;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 
 */
public class MathSolverODE_IVP {

	private int MaxTimes;
	private double[] curValues;
	private double accuracy;
	private ArrayList<double[]> results;
	private double deltaT;
	private double D;
	private double sigma;
	private double beta;
	private double alpha;
	private double B;

	private int problemType;
	private int stopTime;

	private double maxU3 = -Integer.MAX_VALUE;
	private double minU3 = Integer.MAX_VALUE;
	private int periodicT1 = 0;
	private int periodicT2 = 0;
	private int solutionType;
	private int findMax = 0;
	private int findposition = 0;

	private static DecimalFormat df = new DecimalFormat("0.000000 ");
	private static DecimalFormat df2 = new DecimalFormat("0.00000000 ");

	public MathSolverODE_IVP(int proType, int max, double acc, double deT,
			double[] ini, double d, double s, double bet, double al, double b) {
		this.MaxTimes = max;
		this.problemType = proType;
		this.accuracy = acc;
		this.results = new ArrayList<double[]>(0);
		this.deltaT = deT;

		this.curValues = new double[ini.length];
		Ass2Main.copyArray(ini, this.curValues);

		this.D = d;
		this.sigma = s;
		this.beta = bet;
		this.alpha = al;
		this.B = b;
		this.stopTime = 0;
		this.algoRungeKutta();

	}

	private void algoRungeKutta() {

		for (int i = 0; i < this.MaxTimes - 1; i++) {

			double[] temp = new double[this.curValues.length];
			Ass2Main.copyArray(this.curValues, temp);
			this.results.add(temp);
			Runge_Kutta_4th rk = new Runge_Kutta_4th(this.problemType,
					this.deltaT, this.curValues, this.D, this.sigma, this.beta,
					this.alpha, this.B);
			Ass2Main.copyArray(rk.getNextValues(), this.curValues);

			if (this.stationaryCondition(i)) {
				this.stopTime = i;
				solutionType = 1;
				break;
			} else if (this.preiodicCondition(i)) {
				this.stopTime = i;
				solutionType = 2;
				break;
			}
		}
		
		if(solutionType!=1&&solutionType!=2){
			this.stopTime=this.MaxTimes - 2;
		}

	}

	public int getCalTimes() {
		return this.stopTime + 1;
	}

	public ArrayList<double[]> getAllResults() {
		return this.results;
	}

	public double[] getStableResult() {
		return this.results.get(this.stopTime);
	}

	public double getStableU3() {
		return this.results.get(this.stopTime)[3];
	}

	public double[] getNextInitials() {
		if (this.solutionType == 1) {
			return this.results.get(this.stopTime);
		} else if (this.solutionType == 2) {
			return this.results.get(this.stopTime);
		} else {
			System.out.println("Not stable yet!");
			return new double[3];
		}
	}

	private boolean stationaryCondition(int i) {
		if (Math.abs((this.curValues[2] - results.get(i)[2])
				/ this.deltaT) < this.accuracy*0.1) {
			solutionType = 1;
			return true;
		}
		return false;
	}

	public double getMaxU3(){
		if(solutionType == 1){
			return this.results.get(this.stopTime)[2];
		}else if(solutionType == 2){
			return this.results.get(this.stopTime - (int) (this.periodicT2 / 2))[2];
		}else{
			System.out.println("Not stable yet!");
			return 0;
		}
	}
	
	private boolean preiodicCondition(int i) {

		if (i < 2) {
			return false;
		}

		if ((this.curValues[2] - results.get(i)[2]) > 0
				&& (results.get(i)[2] - results.get(i - 1)[2]) < 0) {
			
			if (Math.abs(this.maxU3 - results.get(i)[2]) < accuracy) {
				if (this.findMax == 1) {
					this.periodicT1 = i - this.findposition;
					this.findposition = i;
					this.findMax = 2;
					return false;
				} else if (this.findMax == 2) {
					this.periodicT2 = i - this.findposition;
					if (Math.abs((this.periodicT1-this.periodicT2)/this.periodicT1)< accuracy) {
						solutionType = 2;
						return true;
					} else {
						this.periodicT1 = this.periodicT2;
						this.periodicT2 = 0;
						this.findposition = i;
						this.findMax = 2;
						return false;
					}
				}
			} else {
				this.maxU3 = results.get(i)[2];
				this.findMax = 1;
				this.findposition = i;
				this.periodicT1 = 0;
				this.periodicT2 = 0;
			}
		}else{
			return false;
		}
		return false;



	}
	
	public void outputAll(int i){
		System.out.println("Cur:" +i+"maxU3:"+maxU3+"  findMax"+findMax+"    findposition"+this.findposition);
	}

	public void printData() {
		for (int i = 0; i < results.size(); i++) {
			System.out.println(df.format(i * deltaT) + ","
					+ df2.format(results.get(i)[0]) + ","
					+ df2.format(results.get(i)[1]) + ","
					+ df2.format(results.get(i)[2]));
		}
	}

	public void printStableData() {
		if(solutionType ==1){
			System.out.println(df.format(D) + ","
					+ df.format(this.stopTime * deltaT) + ","+df2.format(getMaxU3())+","
					+ df2.format(results.get(this.stopTime)[0]) + ","
					+ df2.format(results.get(this.stopTime)[1]) + ","
					+ df2.format(results.get(this.stopTime)[2])+","+this.solutionType);
		}else{
			System.out.println(df.format(D) + ","
					+ df.format(this.stopTime * deltaT) + ","+df2.format(getMaxU3())+","
					+ df2.format(results.get(this.stopTime)[0]) + ","
					+ df2.format(results.get(this.stopTime)[1]) + ","
					+ df2.format(results.get(this.stopTime)[2])+","+this.solutionType);
		}
		
	}

	public double arrayMax(double[] arr) {

		double maxArr = Math.abs(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (Math.abs(arr[i]) > maxArr)
				maxArr = Math.abs(arr[i]);
		}
		return maxArr;

	}

	public String getSolutionType() {
		if (this.solutionType == 1) {
			return "Stationary";
		} else if (this.solutionType == 2) {
			return "Periodic";
		} else {
			return "Unknown";
		}
	}
}
