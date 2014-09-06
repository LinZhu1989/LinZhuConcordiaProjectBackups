package assignment2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Ass2Main {

	private static int MaxTimes = 10000001;
	private static double[] initials = new double[3];
	private static double accuracy = 0.000001;
	private static ArrayList<double[]> allResults;
	private static double deltaT = 0.001;
	private static double D;
	private static double sigma = 0.04;
	private static double beta = 1.57;
	private static double alpha = 1;
	private static double B = 8;
	private static int problemType = 1;

	private static int timesOfD =2501;
	private static double minD = 0.0;
	private static double maxD = 0.25;
	private static double deltaD = (maxD - minD) / (timesOfD - 1);
	private static DecimalFormat df = new DecimalFormat("0.00 ");

	public static void main(String[] args) throws FileNotFoundException {


		//double[] Beta={1.58,1.61};
		//minD, maxD. timeOfD, initialu1,u2,u3
		double[][] data58={{0.0,0.2102,1051,0,0,0},
				{0.21027,0.236079,300,0.51670355,0.48725627,1.55997427},
				{0.236179,0.273091,450,0.81077819,0.69928956,2.90488955},
				{0.26532,0.40,500,0.80112226,0.59671553,2.68429989},
				{0.222,0.247455,150,0.52689494,0.43927681,1.47208988}};
		
		double[][] data61={{0.0,0.21544,1077,0,0,0},
				{0.21544,0.242437,400,0.60297293,0.56826857,1.95181658},
				{0.242437,0.287122,450,0.76826617,0.6724752,2.60774048},
				{0.28118,0.40,1000,0.82594556,0.59719988,2.7970394},
				{0.234,0.244553,40,0.63052873,0.50954874,1.83998626}};

		
		beta=1.58;
		String pathForLocal="";
		for(int i=10;i<data58.length-0;i++){
			pathForLocal="DAN_DATA(Beta=";
			pathForLocal+=beta+",";
			initials = new double[3];
			initials[0]=data58[i][3];
			initials[1]=data58[i][4];
			initials[2]=data58[i][5];
			minD=data58[i][0] ;
			maxD=data58[i][1];
			timesOfD=(int) data58[i][2];
			
			deltaD = (maxD - minD) / (timesOfD - 1);
			pathForLocal+="NO."+(i+1)+",D=[ "+minD+" , "+maxD+ " ]).txt";
			FileOutputStream bos = new FileOutputStream(pathForLocal);
			System.setOut(new PrintStream(bos));
			getDataWithChangingD();
			pathForLocal="";
		}

		initials[0]=0.99019963;
		initials[1]=0.19641293;
		initials[2]=5.53177684;
		minD=0.4 ;
		maxD=0.25;
		timesOfD=750;
		
		deltaD = (maxD - minD) / (timesOfD - 1);
		pathForLocal+="3+4.txt";
		FileOutputStream bos = new FileOutputStream(pathForLocal);
		System.setOut(new PrintStream(bos));
		getDataWithChangingD();

	
			
		//}

	}
	
	
	

	public static final void copyArray(double[] copier, double[] target) {
		assert (copier.length == target.length);
		for (int i = 0; i < copier.length; i++) {
			target[i] = copier[i];
		}

	}

	private static double getNorm(double[] v) {
		return Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
	}

	private static void getDataWithAValueD(double vD) {

		D = vD;
		MathSolverODE_IVP solver = new MathSolverODE_IVP(problemType, MaxTimes,
				accuracy, deltaT, initials, D, sigma, beta, alpha, B);
		solver.printData();
	}

	private static void getDataWithChangingD() {
		System.out.println("D,CONVERGE TIME,MAXU3,U1,U2,U3(MIN),SOLUTION TYPE");
		for (int i = 0; i < timesOfD; i++) {
			D = minD + deltaD * i;
			MathSolverODE_IVP solver = new MathSolverODE_IVP(problemType,
					MaxTimes, accuracy, deltaT, initials, D, sigma, beta,
					alpha, B);
			
				solver.printStableData();

			Ass2Main.copyArray(solver.getNextInitials(), initials);
			solver.getAllResults().clear();
			solver=null;
			System.gc();
		}
	}

}
