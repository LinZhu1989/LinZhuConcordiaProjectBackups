package assignment3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import assignment2.Ass2Main;
import assignment2.MathSolverODE_IVP;
import my.IO;

public class Ass3 {
	private static int MaxTimes =500;
	private static double[] initials = new double[2];
	private static double accuracy = 0.0000001;
	private static double deltaT = 0.001;

	private static int problemType = 3;
	private static double r = 0.0001;
	private static double epsilon = 0.5;
	private static double rou = 28;
	private static double maxL =11111;
	private static double sita = 0.0;
	

	private static DecimalFormat df = new DecimalFormat("0.00 ");

	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream bos = new FileOutputStream(
				"F:\\MatlabWorkspace\\COMP6361\\New\\A3\\A3_Data.txt");
		System.setOut(new PrintStream(bos));
		getAllDataWithManyParasNormal();
		// getAllDataWithManyEpsilons();
		// getAllDataWithAcEpsilons();

	}

	private static void setInitials() {
		
		
		if (problemType == 1) {
			
//			initials[0] = 5.2*0.001;
//			initials[1] = (1+0.06*sita)*0.001;
			
			initials[0] = r * Math.cos(sita);
			initials[1] = r * Math.sin(sita);
		} else if (problemType == 3) {
			double[] eigenV1 = { -0.6148, 0.7887, 0 };
			double[] eigenV2 = { 0, 0, 1 };
			//double[] eigenV2 = { -0.4165, -0.9091, 0 };
			//sita=1.5707963267948966;
			initials = new double[3];
			initials[0] = r * (Math.cos(sita)*eigenV1[0]+Math.sin(sita)*eigenV2[0]);
			initials[1] = r * (Math.cos(sita)*eigenV1[1]+Math.sin(sita)*eigenV2[1]);
			initials[2] = r * (Math.cos(sita)*eigenV1[2]+Math.sin(sita)*eigenV2[2]);
		}

	}

	private static void getAllDataWithAcEpsilons() throws FileNotFoundException {

		for (int s = 0; s < 2000; s++) {

			double s1 = 0 + 0.000000000001 * s;
			sita=Math.PI / 180 * (s1);
			setInitials();
			getDataWithAPara();
			sita=Math.PI / 180 * (-s1);
			setInitials();
			getDataWithAPara();
		}

	}

	private static void getAllDataWithManyEpsilons()
			throws FileNotFoundException {

		int[] times = { 100, 80, 60, 40, 20 };
		double[] deltas = { 1 / times[0], 1 / times[1], 1 / times[2],
				1 / times[3], 1 / times[4] };
		for (int s = 0; s < 360; s++) {

			int degree = 0;
			if (s < 90) {
				degree = s - 0;
			} else if (s < 180) {
				degree = 180 - s;
			} else if (s < 270) {
				degree = s - 180;
			} else {
				degree = 360 - s;
			}

			if (degree >= 0 && degree < 5) {
				for (double s1 = 0; s1 < times[degree]; s1++) {
					sita=Math.PI / 180 * (s + s1 * deltas[degree]);
					setInitials();
					getDataWithAPara();
				}
			} else if (degree >= 15) {
				sita=Math.PI / 180 * s;
				setInitials();
				s += 2;
				getDataWithAPara();
			} else if (degree >= 45) {
				setInitials();
				sita=Math.PI / 180 * s;
				s += 2;
				getDataWithAPara();
			} else if (degree >= 65) {
				sita=Math.PI / 180 * s;
				setInitials();
				s += 2;
				getDataWithAPara();
			} else {
				sita=Math.PI / 180 * s;
				setInitials();
				getDataWithAPara();
			}

		}

	}

	private static void getAllDataWithManyParasNormal()
			throws FileNotFoundException {

		if (problemType == 1) {
			for (int s = 0; s < 300; s = s + 1) {
				//setInitials(s);
				sita=(Math.PI / 180) *((s*0.1-15));
				setInitials();
				getDataWithAPara();

			}
		} else if (problemType == 3) {
			for (int s =0; s < 360; s=s+6) {
				// m.outl("Degree:"+(Math.PI / 180 )* s/10);
				sita=(Math.PI / 180) * s;
				setInitials();
				getDataWithAPara();

			}
		}

	}

	private static void getDataWithAPara() throws FileNotFoundException {

		A3_MathSolverODE_IVP solver = new A3_MathSolverODE_IVP(problemType,
				MaxTimes, accuracy, deltaT, initials, epsilon, rou, maxL);
		solver.printData();
		//my.IO.o("Sita="+sita);;
		//solver.printTime();
		//solver = null;
		//System.gc();
	}

}
