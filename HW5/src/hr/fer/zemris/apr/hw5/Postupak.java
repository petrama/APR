package hr.fer.zemris.apr.hw5;

import hr.fer.zemris.linearna.IMatrix;

public abstract class Postupak {
	
	
	
	public abstract IMatrix getNext(IMatrix xk);
	
	public  IMatrix[] getKNext(double period,IMatrix x0,double tmax){
		int n=(int)Math.ceil(tmax/period);
		IMatrix[] result=new IMatrix[n];
		result[0]=x0;
		for(int i=1;i<n;i++){
		result[i]=getNext(result[i-1]);
		}
		
		return result;
	}
	
}
