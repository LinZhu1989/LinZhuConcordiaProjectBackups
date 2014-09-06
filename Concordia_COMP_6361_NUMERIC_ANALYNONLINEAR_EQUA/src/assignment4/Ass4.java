package assignment4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import assignment2.Ass2Main;

public class Ass4 {

	private static double deltaT = 0.001;

	static double[] U = new double[6];
	static double[] V = new double[6];
	static double mu = 0.0;
	static double Jacobi = 0.0;
	static double Floquet = 0.0;
	static double Period = 0.0;
	static double Epsilon = 0.01;
	static double EpF = 0.0;
	static int nums=0;
	static double fixedX=0;
	static int meetT=0;

	// mu,Jacobi,Floquet,Period,deltaT
	static double[] PARAS = new double[4];

	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream bos = new FileOutputStream(
				"F:\\MatlabWorkspace\\COMP6361\\New\\A4\\A4_Data.txt");
		System.setOut(new PrintStream(bos));
		useDataSet(1);
		EpF=Epsilon/Floquet;
		//EpF+=(Epsilon-EpF)*2/3;
		fixedX=0.95;
		Period = 3;
		meetT=2;
		nums=100;
		for(int i=1;i<nums;i++)
		{	
			CR3BP_Solver a4 = new CR3BP_Solver(calInitals(i,normalize(V)), PARAS, V,fixedX,Period,meetT);
			boolean result = a4.solveCR3BP();
			//if(result){
			a4.printLineData();
			//}
		}

	}
	
	private static double[] calInitals(int ii,double[] vs) {
		double[] result=new double [vs.length];
		for (int i = 0; i < U.length; i++) {
			//result[i] = U[i] + 0.0001*ii * vs[i];
			result[i] = U[i] +(EpF+(ii)*(Epsilon-EpF)/(nums)) * vs[i];
			//result[i] = U[i];
		}
		return result;
	}
	
	private static double[] normalize(double[] v){
		
		double sum=0.0;
		double[] result=new double [v.length];
		for(int i=0;i<v.length;i++){
			sum+=v[i]*v[i];
		}
		sum=Math.sqrt(sum);
		for(int i=0;i<v.length;i++){
			result[i]=v[i]/sum;
		}
		return result;
		
	}

	private static void useDataSet(int set) {
		switch (set) {
		case 1:
			double[] u1 = { 8.2976576842E-01, -4.2264479268E-02,
					1.8906867099E-02, -3.4239060424E-02, 9.7848894188E-02,
					3.4202939816E-02 };
			double[] v1 = { 0.00060134884479, -0.00016305664048,
					0.000013381302356, 0.0015954721697, -0.00044767461257,
					-0.000080773572416 };
			mu = 1.2149999983E-02;
			Jacobi = 3.1810329887E+00;
			Floquet = 2.1538884476E+03;
			Period = 2.7472000000E+00;

			Ass2Main.copyArray(u1, U);
			Ass2Main.copyArray(v1, V);
			double[] para1 = { mu, Jacobi / (-2), Floquet,deltaT };
			Ass2Main.copyArray(para1, PARAS);
			break;
		case 2:
			double[] u2 = { 8.3115229326E-01, -4.9746048449E-02,
					3.7676780806E-02, -3.9624655668E-02, 1.1601122314E-01,
					6.4327964461E-02 };
			double[] v2 = { 7.9242137390E-04, -1.9113804824E-04,
					3.6784386178E-05, 2.0149115421E-03, -5.3351295792E-04,
					-1.9976432680E-04 };
			mu = 1.2149999983E-02;
			Jacobi = 3.1666209477E+00;
			Floquet = 1.6647697103E+03;
			Period = 2.7580999999E+00;

			Ass2Main.copyArray(u2, U);
			Ass2Main.copyArray(v2, V);
			double[] para2 = { mu, Jacobi / (-2), Floquet, deltaT };
			Ass2Main.copyArray(para2, PARAS);
			break;
			
		case 3:
			
			double[] u3 = { 8.3305989387E-01, -5.6669256272E-02,  5.2424359727E-02 ,-4.4997687199E-02 , 1.3294729223E-01 , 8.4317773256E-02};
			double[] v3 = {  1.0868296757E-03 ,-2.3245221367E-04,  7.5070046050E-05,  2.6368234680E-03, -6.7410579743E-04 ,-3.5913686803E-04};
			
			mu      = 1.2149999983E-02;
			 Jacobi  = 3.1507136274E+00;
			 Floquet = 1.2332015122E+03;
			 Period  = 2.7690000000E+00;
			 Ass2Main.copyArray(u3, U);
			Ass2Main.copyArray(v3, V);
			double[] para3 = { mu, Jacobi / (-2), Floquet, deltaT };
			Ass2Main.copyArray(para3, PARAS);
				break;

			
		case 13:
			double[] u13 = {   8.4374150701E-01, -7.6886702398E-02 , 1.0200636094E-01 ,-6.5499877332E-02,  1.8523448525E-01 , 1.2824067422E-01};
			double[] v13 = {  4.7589591108E-03, -4.9089830217E-04 , 9.9209073580E-04 , 9.5821635417E-03 ,-2.5322981245E-03 ,-2.6552737072E-03};
				 mu     =  1.2149999983E-02;
				 Jacobi  = 3.0840190522E+00;
				 Floquet = 2.7517102741E+02;
				 Period  = 2.7832000013E+00;
				 
					Ass2Main.copyArray(u13, U);
					Ass2Main.copyArray(v13, V);
					double[] para13 = { mu, Jacobi / (-2), Floquet, deltaT };
					Ass2Main.copyArray(para13, PARAS);
					break;

			
			
			
			
			
			
			case 23:
				double[] u23 = {  8.5990162347E-01 ,-8.0907690403E-02 , 1.4680913534E-01, -8.2588062519E-02,  2.1382188915E-01 , 1.3946316134E-01};
				double[] v23 = { 1.5808370554E-02,  6.8614515560E-03 , 1.1270120883E-02,  3.5208097959E-02 ,-3.4244976951E-03 ,-2.2396293700E-02};
				 mu      =   1.2149999983E-02;
				 Jacobi  =   3.0260672023E+00;
				 Floquet  =  3.4217938030E+01;
				 Period   =  2.6176000025E+00;

				Ass2Main.copyArray(u23, U);
				Ass2Main.copyArray(v23, V);
				double[] para23 = { mu, Jacobi / (-2), Floquet, deltaT };
				Ass2Main.copyArray(para23, PARAS);
				break;
				
		default:

		}

	}

}
