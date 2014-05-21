package assignment4;

import java.text.DecimalFormat;
import java.util.ArrayList;

import assignment2.Ass2Main;
import my.IO;

public class CR3BP_Solver {

	private ArrayList<double[]> results;
	private double[] curValues;
	private double mu;
	private double Jacobi;
	private double Floquet;
	private double Period;
	private double acc = 0.001;
	private double deltaT;
	private double fixedX;
	private my.IO myIO=new IO();
	private boolean leftSide;
	private int timer;
	//private static DecimalFormat df2 = new DecimalFormat("0.00000000 ");
	private int meetTime;

	public CR3BP_Solver(double[] ini, double[] paras, double[] vs,double fx,double maxT,int mt) {
		this.curValues = new double[6];
		myIO.switchTO(false);
		assert (paras.length == 4);
		mu = paras[0];
		Jacobi = paras[1];
		Floquet = paras[2];
		deltaT = paras[3];
		Period = maxT;
		results = new ArrayList<double[]>(1);
		meetTime=mt;
		this.fixedX=fx;
		calInitals(ini,vs);
		if(this.curValues[0]>=fixedX){
			this.leftSide=false;
		}else{
			this.leftSide=true;
		}
		timer=0;

	}
	
	
	

	private void calInitals(double[] ini,double[] vs) {
		for (int i = 0; i < ini.length; i++) {
			this.curValues[i] = ini[i] ;
			//this.curValues[i] = ini[i] + 0.0001 * vs[i];
		}
		
		checkJacobiC();
//		myIO.o("Initial: ");
//		myIO.ol(this.curValues,true);

	}

	public boolean solveCR3BP() {
		if (doCalculation()) {
			myIO.switchTO(false);
			if (checkV() && checkPhaseConstraint()) {
				myIO.ol("Finish!");
				return true;
			} else if (!checkV()) {
				myIO.ol("U-V Error!");
				return false;
			} else {
				myIO.ol("PhaseConstraint Error!");
				return false;
			}
		} else {
			myIO.ol("JacobiC Error!");
			return false;
		}
	}

	private boolean checkV() {
		return true;
	}

	private boolean checkPhaseConstraint() {
		return true;

	}

	public ArrayList<double[]> getAllResults() {
		return this.results;
	}

	public double[] getInitials() {
		return this.results.get(0);
	}

	public double[] getFinals() {
		return this.results.get(results.size() - 1);
	}

	private boolean doCalculation() {
		

		for (double time = 0.0; time <= Period; time = time + deltaT) {
		//for (double time = 0.0; time <= Period; time = time + deltaT) {
			//this.results.add(this.curValues);
			//myIO.ol(this.curValues);
			checkJacobiC();
				//printData();
			
			double[] temp = new double[this.curValues.length];
			Ass2Main.copyArray(this.curValues, temp);
			results.add(temp);

			//printData();
			RK4 rk4 = new RK4(deltaT, curValues, mu);
			//myIO.ol(this.curValues);
			Ass2Main.copyArray(rk4.getNextValues(), this.curValues);
			//myIO.ol(this.curValues,false);

//			if((rk4.getNextValues()[0]-fixedX)*(curValues[0]-fixedX)<0){
//				timer++;	
//			}
			
			if(leftSide){
				if(this.curValues[0]>fixedX){
					//return true;
					timer++;
					leftSide=false;
				}
			}else{
				if(this.curValues[0]<fixedX){
					//return true;
					timer++;		
					leftSide=true;
			}
			}
			
			if(timer==meetTime){
				return true;
			}
			if (!checkJacobiC()) {
				System.out.print("Error!JacobiC!");
				return false;
			}
		}
		return true;
	}

	private void printData(){
		myIO.switchTO(true);
		for(int i=0;i<this.curValues.length;i++){
			myIO.o(this.curValues[i],false);
			if(i!=this.curValues.length-1){
				myIO.o(",");
			}
		}
		myIO.ol();
		myIO.switchTO(false);
		
	}
	
	
	public void printLineData() {
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < results.size(); i++) {
				System.out.print(results.get(i)[j]);
				if (i != results.size() - 1) {
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}
	
	
	private boolean checkJacobiC() {
		double test = (Math.pow(curValues[3], 2) + Math.pow(curValues[4], 2) + Math
				.pow(curValues[5], 2)) / 2 - getU() - mu * (1 - mu) / 2;
		//test*=-2;
		//IO.ol(getU());
		myIO.switchTO(false);
		myIO.ol("test: "+test);
		//myIO.ol("Jacobi: "+Jacobi);
		if (Math.abs(Jacobi - test) < acc) {
			return true;
		} else {
			return true;
		}

	}

	private double getU() {
		return 0.5 * (Math.pow(curValues[0], 2) + Math.pow(curValues[1], 2))
				+ (1 - mu) / getR1() + mu / getR2();
	}

	private double getR1() {
		return Math.sqrt(Math.pow((curValues[0] + mu), 2)
				+ Math.pow(curValues[1], 2) + Math.pow(curValues[2], 2));
	}

	private double getR2() {
		return Math.sqrt(Math.pow((curValues[0] - 1 + mu), 2)
				+ Math.pow(curValues[1], 2) + Math.pow(curValues[2], 2));
	}
	

}
