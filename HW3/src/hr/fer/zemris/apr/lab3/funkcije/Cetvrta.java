package hr.fer.zemris.apr.lab3.funkcije;

import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Vector;

public class Cetvrta extends Funkcija {

	@Override
	public Double compute2(IVector v) {
		// TODO Auto-generated method stub
		return (v.get(0)-3)*(v.get(0)-3)+v.get(1)*v.get(1);
	}

	@Override
	public IVector gradient(IVector t) {
		return new Vector(2*(t.get(0)-3),2*t.get(1));
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
