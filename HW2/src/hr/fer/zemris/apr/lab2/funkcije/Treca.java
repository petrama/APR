package hr.fer.zemris.apr.lab2.funkcije;


import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;


public class Treca extends Funkcija {
 @Override
public Double compute2(IVector v) {
	 double suma=0;
	 for(int i=0;i<v.getDimension();i++){
		 suma+=(v.get(i)-(i+1))*(v.get(i)-(i+1));
	}
	 return suma;
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
