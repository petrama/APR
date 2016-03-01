package hr.fer.zemris.apr.lab2.funkcije;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;

public class Schaffer extends Funkcija {
	@Override
	public Double compute2(IVector v) {
	double sum=0;
		for(int i=0;i<v.getDimension();i++){
		sum+=v.get(i)*v.get(i);
	}
		return 0.5+(Math.sin(Math.sqrt(sum))*Math.sin(Math.sqrt(sum))-0.5)/((1+0.001*sum)*(1+0.001*sum));
		
	}

	@Override
	public IMatrix hesseova2(IVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVector gradient2(IVector t) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
