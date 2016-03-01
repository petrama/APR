package hr.fer.zemris.apr.lab2.hooke;


import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.linearna.IVector;

public class HookeJeevs {
	
	public static IVector hooke(IVector pocetna,double dx,double e,Funkcija f){
		IVector xp=pocetna.copy();
		IVector xb=pocetna.copy();
		
		do{
			
			IVector xn=istrazi(xp,dx,f);
			double fp=f.compute(xp);
			double fn=f.compute(xn);
			double fb=f.compute(xb);
			System.out.println("Bazna: "+xb+" f="+fb);
			System.out.println("Pocetna: "+xp+" f="+fp);
			System.out.println("Pretrazi: "+xn+" f="+fn);
			if (f.compute(xn)<f.compute(xb)){
				xp=xn.nScalarMultiply(2).nSub(xb);
				xb=xn.copy();
				
			}else{
				dx/=2;
				xp=xb.copy();
				System.err.println("dx= "+dx);
			}
		}while(dx>=e);
		System.out.println("Trazena tocka je: "+xb+" f="+f.compute(xb));
		return xb;
		
	}

	private static IVector istrazi(IVector xp, double dx,Funkcija f) {
		IVector x=xp.copy();
		for(int i=0;i<x.getDimension();i++){
			double p=f.compute(x);
			x.set(i, x.get(i)+dx);
			double n=f.compute(x);
			if(n>p){
				System.out.println("Ne valja pozitivni "+i);
				x.set(i, x.get(i)-2*dx);
				n=f.compute(x);
				if(n>p){
					System.out.println("Ne valja negativni "+i);
					x.set(i, x.get(i)+dx);
				}
			}
		
		}
		return x;
	}

}
