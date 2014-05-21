package assignment1_Double_Version;

/**
 * 
 */
public class GaussElimination {

	
	private double[] GEresult;
	private double[] Beta;
	private double[] Gamma;
	private double[] g;
	private double[] f;
	private double[] a;
	private double[] b;
	private double[] c;
	private int dimension;
	
	
	public GaussElimination(TridiagonalMatrix jacobiMatrix, double[] rf){
		this.dimension=rf.length;
		this.a = new double[this.dimension-1];
		this.c = new double[this.dimension-1];
		this.b = new double[this.dimension];
		this.f = new double[this.dimension];
		MainTest.copyArray(a, jacobiMatrix.getLowerValues());
		MainTest.copyArray(c, jacobiMatrix.getUpperValues());
		MainTest.copyArray(b, jacobiMatrix.getMiddleValues());
		MainTest.copyArray(f, rf);
		
//		this.a=jacobiMatrix.getLowerValues();
//		this.c=jacobiMatrix.getUpperValues();
//		this.b=jacobiMatrix.getMiddleValues();
//		this.f=rf;
		this.Beta=new double[this.dimension];
		this.Gamma=new double[this.dimension];
		this.g=new double[this.dimension];
		this.GEresult=new double[this.dimension];
	}
	
	
	public void performAlgo(){
		this.Beta[0]=this.b[0];
		this.g[0]=this.f[0];
		for(int i=1;i<this.dimension;i++){
			this.Gamma[i]=this.a[i-1]/this.Beta[i-1];
			this.Beta[i]=this.b[i]-this.Gamma[i]*this.c[i-1];
			this.g[i]=this.f[i]-this.Gamma[i]*this.g[i-1];
			//System.out.println("No"+i+" yakebiD[] "+this.b[i]+"beta[] "+this.Beta[i]+"g[i] "+this.g[i]);
		}		
		
		
	}


	
	public double[] getDeltaU() {
		performAlgo();
		this.GEresult[this.dimension-1]=this.g[this.dimension-1]/this.Beta[this.dimension-1];
		for(int i=this.dimension-2;i>=0;i--){
			this.GEresult[i]=(this.g[i]-this.c[i]*this.GEresult[i+1])/this.Beta[i];
		}
		return this.GEresult;
	}
}
