package assignment3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import assignment2.Ass2Main;

/**
 * 
 */
public class A3_MathSolverODE_IVP {

	private int MaxTimes;
	private double[] curValues;
	private double accuracy;
	private ArrayList<double[]> results;
	private double deltaT;
	private double epsilon;
	private double rou;
	private double L;
	private double maxL;
	private int problemType;
	private int stopTime;

	private static DecimalFormat df = new DecimalFormat("0.000000 ");
	private static DecimalFormat df2 = new DecimalFormat("0.00000000 ");

	public A3_MathSolverODE_IVP(int proType, int max, double acc, double deT,
			double[] ini, double epsilons, double rous, double MaxL)
			throws FileNotFoundException {
		this.MaxTimes = max;
		this.problemType = proType;
		this.accuracy = acc;
		this.results = new ArrayList<double[]>(0);
		this.deltaT = deT;
		this.L = 0;
		this.maxL = MaxL;
		this.curValues = new double[ini.length];
		Ass2Main.copyArray(ini, this.curValues);

		this.epsilon = epsilons;
		this.rou = rous;
		this.stopTime = 0;
		this.algoRungeKutta();

	}

	private void algoRungeKutta() {

		
		if(problemType==3){
			this.deltaT=-this.deltaT;
		}
		//this.MaxTimes=10000+1000;
		for (int i = 0; i < this.MaxTimes - 1; i++) {
			
			
//			if(i<10000){
//				deltaT=-0.0001; //1
//			}else if(i<10000+1000){ //1.25
//				deltaT=-0.0001;//1.25
//			}
//			else if(i<1000+2500+1000){ //1.3
//				deltaT=-0.00005;
//			}else if(i<1000+2500+1000+50000){ //1.35
//				deltaT=-0.000001;
//			}
			
			double[] temp = new double[this.curValues.length];
			Ass2Main.copyArray(this.curValues, temp);
			
			
//			if(i>145000||i%1000==0){
//				this.results.add(temp);
//			}
//			if(i<(150000-2500)*10){
//				
//				if(i%1000==0){
//					this.results.add(temp);
//				}else{
//					
//				}
//			}else{
				this.results.add(temp);
//			}
//			
		
			
			
			//this.results.add(temp);

			A3_Runge_Kutta_4th rk = new A3_Runge_Kutta_4th(this.problemType,
					this.deltaT, this.curValues, this.epsilon, this.rou);
			addLength(rk);
//			addLength(rk.getNextValues()[0],rk.getNextValues()[1],curValues[0],curValues[1]);
//			addLength(rk.getNextValues()[0],rk.getNextValues()[1],rk.getNextValues()[2],curValues[0],curValues[1],curValues[2]);
			if (reachMaxL()) {
				this.stopTime = i;
				break;
			}
//		
//			if( i>1000){
//				this.stopTime = i;
//				break;
//			}
			Ass2Main.copyArray(rk.getNextValues(), this.curValues);

		}

	}

	private void addLength(double a, double b ,double c, double a1, double b1,
			double c1) {
		double sum = 0.0;
		sum=(a-a1)*(a-a1)+(b-b1)*(b-b1)+(c-c1)*(c-c1);
		this.L += Math.sqrt(sum);
		System.out.println("L:"+L);
		
	}
	
	private void addLength(double a, double b , double a1, double b1) {
		double sum = 0.0;
		sum=(a-a1)*(a-a1)+(b-b1)*(b-b1);
		this.L += Math.sqrt(sum);
		System.out.println("L:"+L);
		
	}


	private boolean reachMaxTime(int i) {
		if (this.deltaT*i - this.MaxTimes > 0) {
			return true;
		}
		return false;
	}

	private void addLength(A3_Runge_Kutta_4th ds) {

		double sum = 0.0;
		for (int i = 0; i < this.curValues.length; i++) {
			
//sum += Math.pow((ds[i]-this.curValues[i])/this.deltaT, 2);	
					
			sum += ((ds.getNextValues()[i] - this.curValues[i]))
					* ((ds.getNextValues()[i] - this.curValues[i]));
		}	
			this.L += Math.sqrt(sum);
			//my.IO.ol(this.L);
	}

	private boolean reachMaxL() {

		if (this.L - this.maxL > 0) {
			return true;
		}
		return false;
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

	public double[] getNextInitials() {
		return this.results.get(this.stopTime);

	}

	public void printData() {
		for (int j = 0; j < results.get(0).length; j++) {
			for (int i = 0; i < results.size(); i++) {
				System.out.print(df2.format(results.get(i)[j]));
				if (i != results.size() - 1) {
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}
	
	public void printTime() {

			System.out.println("StopTime"+this.stopTime);
		
	}

	public double arrayMax(double[] arr) {

		double maxArr = Math.abs(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (Math.abs(arr[i]) > maxArr)
				maxArr = Math.abs(arr[i]);
		}
		return maxArr;

	}

}
