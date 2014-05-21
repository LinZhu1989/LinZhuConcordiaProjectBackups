package assignment1_Double_Version;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainTest {

	private static double deltaT = 0.01000000000000;
	private static double deltaX = 0.01000000000000;
	private static double maxT = 100.0000000000000;
	private static double maxX = 1.00000000000000;
	private static int funcType = 1; // 3 is g(x)=0.5, 4 is g(x)=0.1, 5 is
										// g(x)=0.0
	private static int probType = 1; // 1 for fisher, 2 for another
	private static double lamda = 9.0000000000000;
	private static int matLabPrinterOrNot = 2; // 1 means yes
	
	public static void copyArray(double [] a1,double [] a2){
		for(int i=0;i<a1.length;i++){
			a1[i]=a2[i];
		}
		
	}

	public static void main(String[] args) throws FileNotFoundException {

		if (matLabPrinterOrNot == 1) {
			NonlinearDiffusionEquationsSolver pro = new NonlinearDiffusionEquationsSolver(
					deltaT, deltaX, maxT, maxX, funcType, probType, lamda);
			FileOutputStream bos = new FileOutputStream(
					"F://MatlabWorkspace//COMP6361//New//MatlabPrintData.txt");
			System.setOut(new PrintStream(bos));

			/**
			 * For print figures using Matlab, choose methods here!!!!!
			 */
			matlabFunctionPrinter(pro, 0, 1.00);
			// 0: integral U, No need set t;
			// 1:one function at time, set a particular t;
			// 2: two functions at time, one is t = 0.00, set another particular
			// t;
			// 3:functions in range, set a particular t, starts from 0;
		} else {

			NonlinearDiffusionEquationsSolver pro = new NonlinearDiffusionEquationsSolver(
					deltaT, deltaX, maxT, maxX, funcType, probType, lamda);
			//FileOutputStream bos = new FileOutputStream("LoacalData.txt");
			//System.setOut(new PrintStream(bos));
			/**
			 * For print data locally, choose methods here!!!!!
			 */
			System.out.println("**************** Results **************** \n");
			localPrinter(pro, 1);
			// 0:all data ; 1: integral U£¬2£º MaxU and Stable Integral Value
		}

	}

	public static void localPrinter(NonlinearDiffusionEquationsSolver pro, int i) {
		switch (i) {
		case 0:
			printAllData(pro);
			break;
		case 1:
			printIntegralValues(pro);
			break;
		case 2:
			printImportant(pro);
			break;
		case 3:

			break;
		}

	}

	public static void matlabFunctionPrinter(
			NonlinearDiffusionEquationsSolver pro, int i, double t) {
		switch (i) {
		case 0:
			matlabPrintIntegralValues(pro);
			break;
		case 1:
			matlabPrintFunctionAtTime(pro, t);
			break;
		case 2:
			matlabPrintFunctionsAtTwoTime(pro, 0.00, t);
			break;
		case 3:
			matlabPrintFunctionsAtTimeRange(pro, 0.00, t);
			break;
		case 4:
			break;
		}

	}

	private static void printImportant(NonlinearDiffusionEquationsSolver pro) {
		double[][] result = pro.getAllResultValues();
		double[] result2 = pro.getAllIntegralValue();
		System.out.print("Max U :" + result[result.length - 1][50]);
		System.out.println();
		System.out.print("Stable Integral Value :"
				+ result2[result2.length - 1]);
		System.out.println();

	}

	public static void printAllData(NonlinearDiffusionEquationsSolver pro) {
		double[][] result = pro.getAllResultValues();
		for (int i = 0; i < result.length; i++) {
			System.out.print("******** Time is " + deltaT * i + "******** \n");
			System.out.println();
			for (int j = 0; j < result[0].length; j++) {
				System.out.print("x[" + j + "]=" + result[i][j] + ",");
			}
			System.out.println();
		}
	}

	public static void printIntegralValues(NonlinearDiffusionEquationsSolver pro) {
		double[] result = pro.getAllIntegralValue();
		for (int i = 0; i < result.length; i++) {
			System.out.print("******** Time is " + deltaT * i + "******** \n");
			System.out.println();
			System.out.println("Integral of u(x) at time " + result[i]);
			System.out.println();
		}
	}

	public static void matlabPrintIntegralValues(
			NonlinearDiffusionEquationsSolver pro) {
		double[] result = pro.getAllIntegralValue();
		for (int i = 0; i < result.length; i++) {
			System.out.println(deltaT * i + "," + result[i]);
		}
	}

	public static void matlabPrintFunctionAtTime(
			NonlinearDiffusionEquationsSolver pro, double time) {
		double[][] result = pro.getAllResultValues();
		for (int i = 0; i < result[(int) (time / deltaT)].length; i++) {
			System.out.println(deltaX * i + ","
					+ result[(int) (time / deltaT)][i]);
		}
	}

	public static void matlabPrintFunctionsAtTimeRange(
			NonlinearDiffusionEquationsSolver pro, double time1, double time2) {
		double[][] result = pro.getAllResultValues();
		for (int i = 0; i < result[0].length; i++) {

			System.out.print(deltaX * i);
			for (int j = 0; j < (int) ((time2 - time1) / deltaT) + 1; j++) {
				System.out.print("," + result[(int) (time1 / deltaT) + j][i]);
			}
			System.out.println();
		}

	}

	public static void matlabPrintFunctionsAtTwoTime(
			NonlinearDiffusionEquationsSolver pro, double time1, double time2) {
		double[][] result = pro.getAllResultValues();
		for (int i = 0; i < result[0].length; i++) {

			System.out.print(deltaX * i);
			System.out.print("," + result[(int) (time1 / deltaT)][i]);
			System.out.print("," + result[(int) (time2 / deltaT)][i]);
			System.out.println();
		}

	}

}
