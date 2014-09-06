/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package assignment1_DataGet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;

import assignment1_Double_Version.NonlinearDiffusionEquationsSolver;

/**
 * 
 */
public class DataGet {

	private static double deltaT = 0.01000000000000;
	private static double deltaX = 0.01000000000000;
	private static double maxT = 100.8600000000000;
	private static double maxX = 1.00000000000000;
	private static int funcType = 3; // 3 is g(x)=0.5, 4 is g(x)=0.1, 5 is
										// g(x)=0.0
	private static int probType = 2; // 1 for fisher, 2 for another

	// private static double lamda = 0.5000000000000;
	public static void main(String[] args) throws FileNotFoundException {
		DecimalFormat df = new DecimalFormat( "0.00 ");  
//		FileOutputStream bos = new FileOutputStream("LoacalData6.txt");
//		System.setOut(new PrintStream(bos));
		//double [] valueLamda={1,2,3,4,5,6,7,8,9,10,10.5,10.6,10.7,10.8,11,12,14,16,25,30,50};
		//double [] valueLamda={0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9,2.0,2.1,2.2,2.3,2.4,2.5,2.6,2.7,2.8,2.9,3.0,3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.8,3.9,4.0,5,6,7,8,9,10,11,12,14,16,25,30,40,50};
		//double [] valueLamda={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50};
		//double [] valueLamda={9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,10.0,10.1,10.2,10.3,10.4,10.5};
		//double [] valueLamda={0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9,2.0,2.1,2.2,2.3,2.4,2.5,2.6,2.7,2.8,2.9,3.0,3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.8,3.9,4.0,4.1,4.2,4.3,4.4,4.5};
		//double [] valueLamda={3.6,3.7,3.8,3.9,4.0,4.1,4.2,4.3,4.4,4.5};
		double [] valueLamda={9.0};
		for(int i=0;i<valueLamda.length;i++){
			
//			NonlinearDiffusionEquationsSolver pro1 = new NonlinearDiffusionEquationsSolver(
//					deltaT, deltaX, maxT, maxX, funcType, 1, valueLamda[i]);// 	
			NonlinearDiffusionEquationsSolver pro1 = new NonlinearDiffusionEquationsSolver(
					deltaT, deltaX, maxT, maxX, funcType, 1, valueLamda[i]);
//			NonlinearDiffusionEquationsSolver pro3 = new NonlinearDiffusionEquationsSolver(
//					deltaT, deltaX, maxT, maxX, funcType, 3, valueLamda[i]);
			double[][] result11 = pro1.getAllResultValues();
			double[] result12 = pro1.getAllIntegralValue();
			//double[][] result21 = pro2.getAllResultValues();
			//double[] result22 = pro2.getAllIntegralValue();			
			//double[][] result31 = pro3.getAllResultValues();
			//double[] result32 = pro3.getAllIntegralValue();
			System.out.print(valueLamda[i]+",");
			System.out.print(result11[1023][50]+",");
			System.out.print( result12[1023]+",");
			System.out.print( df.format(pro1.getCurrentLevel()*deltaT));
			//System.out.print( result22[result22.length - 1]+",");
			//System.out.print( result32[result32.length - 1]);
			System.out.println();

		}


	}
}
