package assignment2;
//package Assignment2;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
//public class Ass2COPY {
//
//	private static int MaxTimes = 10000001;
//	private static double[] initials = new double[3];
//	private static double accuracy = 0.000001;
//	private static ArrayList<double[]> allResults;
//	private static double deltaT = 0.001;
//	private static double D;
//	private static double sigma = 0.04;
//	private static double beta = 1.57;
//	private static double alpha = 1;
//	private static double B = 8;
//	private static int problemType = 1;
//
//	private static int timesOfD =2501;
//	private static double minD = 0.0;
//	private static double maxD = 0.25;
//	private static double deltaD = (maxD - minD) / (timesOfD - 1);
//	private static DecimalFormat df = new DecimalFormat("0.00 ");
//
//	public static void main(String[] args) throws FileNotFoundException {
//
////		System.out.println("Beta:"+beta);
////		System.out.println("Max Realtime:"+df.format((MaxTimes-1)*deltaT));
////		String pathForMatLabPlot = "F://MatlabWorkspace//COMP6361//New//A2 NEW//MatlabPrintData.txt";
////		String pathForLocal = "LocalData(beta=";
////		pathForLocal+=beta;
////		pathForLocal+=")";
////		
////		FileOutputStream bos = new FileOutputStream(pathForMatLabPlot);
////		System.setOut(new PrintStream(bos));
//		
//
//		//getDataWithAValueD(0.23);
//		double[] Beta={1.57,1.58,1.61};
//		//for(int i=0;i<Beta.length;i++){
//			initials = new double[3];
//			beta=Beta[2];
////			System.out.println("Beta:"+beta);
////			System.out.println("Max Realtime:"+df.format((MaxTimes-1)*deltaT));
//
//			//			String pathForMatLabPlot = "F://MatlabWorkspace//COMP6361//New//A2 NEW//";
//			//String pathForLocal = "LocalData(beta=";
//			String pathForLocal = "";
////			pathForLocal+=beta;
////			pathForLocal+=").txt";
////			
////			pathForLocal="temp_"+beta+"4.txt";
//			timesOfD =1001;
//			minD=0.0   ;
//			maxD=0.5;
//			deltaD = (maxD - minD) / (timesOfD - 1);
//
////			initials[0]=0.63052873  ;
////			initials[1]=0.50954874;
////			initials[2]=1.83998626;
//			
//			pathForLocal="SONG_DATA_Beta=";
//			pathForLocal+=beta;
//			pathForLocal+="_D("+minD+","+maxD+","+deltaD+","+timesOfD+").txt";
//			FileOutputStream bos = new FileOutputStream(pathForLocal);
//			System.setOut(new PrintStream(bos));
//			getDataWithChangingD();
//		//}
//
//	}
//	
//	
//	
//
//	public static final void copyArray(double[] copier, double[] target) {
//		assert (copier.length == target.length);
//		for (int i = 0; i < copier.length; i++) {
//			target[i] = copier[i];
//		}
//
//	}
//
//	private static double getNorm(double[] v) {
//		return Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
//	}
//
//	private static void getDataWithAValueD(double vD) {
//
//		D = vD;
//		MathSolverODE_IVP solver = new MathSolverODE_IVP(problemType, MaxTimes,
//				accuracy, deltaT, initials, D, sigma, beta, alpha, B);
//		solver.printData();
//	}
//
//	private static void getDataWithChangingD() {
//		for (int i = 0; i < timesOfD; i++) {
//			D = minD + deltaD * i;
//			MathSolverODE_IVP solver = new MathSolverODE_IVP(problemType,
//					MaxTimes, accuracy, deltaT, initials, D, sigma, beta,
//					alpha, B);
//			
//				solver.printStableData();
//
//			Ass2COPY.copyArray(solver.getNextInitials(), initials);
//
//		}
//	}
//
//}
