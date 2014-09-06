package assignment2;

public final class DataUseful {
	private static double[] initials = new double[3];
	private static int timesOfD;
	private static double minD;
	private static double maxD;
	private static double deltaD = (maxD - minD) / (timesOfD - 1);
/**
 * This is just a data backup.
 */
	public final static void temp157Initials(){
		// beta=1.57 高处用
		timesOfD =201;
		minD=0.5;
		maxD=0.21;
		deltaD = (maxD - minD) / (timesOfD - 1);
		initials[0]=0.58837408;
		initials[1]=0.48679491;
		initials[2]=1.71480495;
		// then
		timesOfD =201;
		minD=0.23;
		maxD=0.25;
		deltaD = (maxD - minD) / (timesOfD - 1);
		initials[0]=0.58837408;
		initials[1]=0.48679491;
		initials[2]=1.71480495;
		// beta=1.57第一段红线用
		timesOfD =100;
		minD=0.2077;
		maxD=0.224;
		deltaD = (maxD - minD) / (timesOfD - 1);
		initials[0]=0.59001163;
		initials[1]=0.55797747;
		initials[2]=1.93768065;
		// beta=1.57第二段红线+蓝线用
		timesOfD =100;
		minD=0.25;
		maxD=0.225;
		deltaD = (maxD - minD) / (timesOfD - 1);
		initials[0]=0.86325623;
		initials[1]=0.68918852;
		initials[2]=3.22888934;
		// beta=1.57最后红线+蓝线用-1
		timesOfD =50;
		minD=0.275;
		maxD=0.25;
		deltaD = (maxD - minD) / (timesOfD - 1);
		initials[0]=0.84917093;
		initials[1]=0.59733391;
		initials[2]=2.99790838;
		// beta=1.57最后红线+蓝线用-2
		timesOfD =2000;
		minD=0.275;
		maxD=0.50;
		deltaD = (maxD - minD) / (timesOfD - 1);
		initials[0]=0.84917093;
		initials[1]=0.59733391;
		initials[2]=2.99790838;
		// beta=1.57最后红线+蓝线用-3
		timesOfD =1500;
		minD=0.3594;
		maxD=0.50;
		deltaD = (maxD - minD) / (timesOfD - 1);
		initials[0]=0.98609475;
		initials[1]=0.25091896;
		initials[2]=5.28463911;


}
}
