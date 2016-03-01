package hr.fer.zemris.apr.lab2.funkcije;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;

public class Moja extends Funkcija {

	@Override
	public Double compute2(IVector v) {
		return v.get(0)*v.get(0)+v.get(1)*v.get(1);
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
