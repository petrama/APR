package hr.fer.zemris.apr.lab4.krizanje;

import java.util.Random;

import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Vector;

public abstract class Krizanje {
	
	public IVector krizaj(IVector prva,IVector druga,int n){
		Random r=new Random(System.currentTimeMillis());
		IVector novi=prva.copy();
		for(int i=0;i<novi.getDimension();i++){
			int point=r.nextInt(n);
			novi.set(i, krizaj(prva.get(i), druga.get(i), n,point));
		}
		return novi;
	}
	public abstract Double krizaj(double d, double e, int n, int point);
	
}
