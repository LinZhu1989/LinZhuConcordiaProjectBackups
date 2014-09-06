/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Builder;

import java.util.Arrays;
import java.util.Random;

import GUI.battleView.BattleLog;


/**
 * the class DiceTool helps to get the random number
 * 
 * @author Lin Zhu
 * 
 */
public class DiceTool {

	private int numOfSurface;
	private Random random = new Random();

	/**
	 * Class DiceTool Constructor (set a max integer)
	 */
	public DiceTool(int surface) {
		this.random = new Random();
		this.numOfSurface = surface;
	}

	/**
	 * get a random value
	 * 
	 * @return
	 */
	public int getValue() {
		return this.random.nextInt(this.numOfSurface)+1;// Please do not change here. +1 must be there.
	}
	
	public int getValue(BattleLog bl) {
		int result = this.random.nextInt(this.numOfSurface)+1;// Please do not change here. +1 must be there.
		bl.setLogInfor("   Throw result is "+result+" of D"+numOfSurface+".");
		return result;
	}
	
	public int throwDice(int times) {
		int result=0;
		for(int i=0;i<times;i++){
			result+=(this.random.nextInt(this.numOfSurface)+1);
		}		
		return result;
	}
	
	public int[][] throwDiceChooseMax(int times,int maxTime) {
		int[] result = new int[times];
		int[][] finalResult= new int[3][];
		finalResult[0]=new int[1];
		finalResult[1]=new int[times];
		finalResult[2]=new int[maxTime];
		for(int i=0;i<times;i++){
			result[i]=(this.random.nextInt(this.numOfSurface)+1);
			finalResult[1][i]=result[i];
		}		
		Arrays.sort(result);
		
		for(int j=0;j<maxTime;j++){
			finalResult[2][j]=result[times-1-j];
			finalResult[0][0]+=result[times-1-j];
		}
		return finalResult;
	}
	
	public int throwDiceChooseMaxReturnResultOnly(int times,int maxTime) {
		int[] result = new int[times];
		int finalResult= 0;
		
		for(int i=0;i<times;i++){
			result[i]=(this.random.nextInt(this.numOfSurface)+1);
		}		
		Arrays.sort(result);
		
		for(int j=0;j<maxTime;j++){
			finalResult+=result[times-1-j];
		}
		return finalResult;
	}
	
	public int throwDiceChooseMaxOutputLog(int times,int maxTime,BattleLog bl) {
		int[] result = new int[times];
		String throwNum="   ";
		String maxNum="   ";
		int finalResult=0;
		for(int i=0;i<times;i++){
			result[i]=(this.random.nextInt(this.numOfSurface)+1);
			throwNum+=" ";
			throwNum+=result[i];
		}		
		Arrays.sort(result);
		
		for(int j=0;j<maxTime;j++){
			finalResult+=result[times-1-j];
			if(j!=0){
				maxNum+=" + ";
			}
			maxNum+=result[times-1-j];
		}
		
		maxNum+=" = ";
		maxNum+=finalResult;
		
		bl.setLogInfor("   Choose "+maxTime+" Highest Numbers of " + times);
		bl.setLogInfor("   Throw Numbers: ");
		bl.setLogInfor(throwNum);
		bl.setLogInfor("   Sum of All Highest Numbers: ");
		bl.setLogInfor(maxNum);
		return finalResult;
	}
}
