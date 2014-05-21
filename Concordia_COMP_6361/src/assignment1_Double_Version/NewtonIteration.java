/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package assignment1_Double_Version;

/**
 * 
 */
public class NewtonIteration {
	//private int dimension;
	private double[] deltaU;
	private double deltaT;
	private double deltaX;
	private int proType;
	private double lamda;
	private double[] currenTimeLevelValues;
	private double[] tempFutureValues;
	private double[] finalFutureValues;
	private double accuracy;
	
	
	
	
	public NewtonIteration(double dT, double dX, double[] valuesAtCurrentTime,
			int type,double lamd) {
		this.deltaT=dT;
		this.deltaX=dX;
		this.lamda=lamd;
		this.proType=type;
		this.currenTimeLevelValues=new double[valuesAtCurrentTime.length];
		this.tempFutureValues=new double[valuesAtCurrentTime.length];
		this.finalFutureValues=new double[valuesAtCurrentTime.length];
		
		
		MainTest.copyArray(currenTimeLevelValues, valuesAtCurrentTime);
		MainTest.copyArray(tempFutureValues, currenTimeLevelValues);
		MainTest.copyArray(finalFutureValues, tempFutureValues);
	
//		this.currenTimeLevelValues=valuesAtCurrentTime;
//		this.tempFutureValues=currenTimeLevelValues;
//		this.finalFutureValues=this.tempFutureValues;
		this.accuracy=0.000001;
		this.deltaU=new double[currenTimeLevelValues.length];
	}
	
	
	public double[] getValuesAtNextTimeLevel(){
		this.doNewtonIteration();
		MainTest.copyArray(finalFutureValues, this.tempFutureValues);
		return this.finalFutureValues;
	}
	
	
	private void doNewtonIteration() {

		LinearTridiagonalSystemSolver triSolver = new LinearTridiagonalSystemSolver(this.deltaT,this.deltaX,
				this.currenTimeLevelValues, this.tempFutureValues,this.proType,this.lamda);
//		this.tempFutureValues = triSolver.getResultvalues();
//		this.deltaU=triSolver.getDeltaValue();
		MainTest.copyArray(tempFutureValues, triSolver.getResultvalues());
		MainTest.copyArray(deltaU, triSolver.getDeltaValue());

		int time=1;
		while(!stopCondition()){
			time++;
			triSolver.changeTempFutureValues(this.tempFutureValues);
			MainTest.copyArray(tempFutureValues, triSolver.getResultvalues());
			MainTest.copyArray(deltaU, triSolver.getDeltaValue());
			
			
			if(time>10){
				break;
			}
			//System.out.println("DeltaU: "+this.deltaU[this.deltaU.length-1]);				

		}
		System.out.println("Newton Time = "+time);
		//System.out.println();
		
	}


	private boolean stopCondition() {
		if (getInfinityNorm(this.deltaU) / getInfinityNorm(this.tempFutureValues) < this.accuracy) {
			return true;
		} else {
			return false;
		}

	}

	private double getInfinityNorm(double[] value) {
		double absMax = 0;
		for (int i = 0; i < value.length; i++) {
			if (Math.abs(value[i]) > absMax) {
				absMax = Math.abs(value[i]);
			}
		}
		return absMax;
	}
}
