package hr.fer.zemris.apr.lab2;

import java.util.Random;

import hr.fer.zemris.apr.lab2.funkcije.Banana;
import hr.fer.zemris.apr.lab2.funkcije.Druga;
import hr.fer.zemris.apr.lab2.funkcije.Jakoboviceva;
import hr.fer.zemris.apr.lab2.funkcije.Moja;
import hr.fer.zemris.apr.lab2.funkcije.PrviZad;
import hr.fer.zemris.apr.lab2.funkcije.Schaffer;
import hr.fer.zemris.apr.lab2.funkcije.Treca;
import hr.fer.zemris.apr.lab2.hooke.HookeJeevs;
import hr.fer.zemris.apr.lab2.simpleks.NelderMead;
import hr.fer.zemris.apr.lab2.zlatnirez.ZlatniRez;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Vector;

public class Glavni {

	public static void main(String[] args) {
		Banana b=new Banana();
		Druga d=new Druga();
		Treca t=new Treca();
		Jakoboviceva j=new Jakoboviceva();
		Schaffer s=new Schaffer();
		//ZlatniRez.zlatniRez(new Vector(-1.9,2), 1E-6, b);
	
		
		NelderMead.alg(new Vector(-1.9,2), 1.0,b);
		System.out.println("Broj poziva: "+b.getBrojPoziva());
		b.setBrojPoziva(0);
//		HookeJeevs.hooke(new Vector(-1.9,2), 0.5, 1E-6, b);
//		System.out.println("Broj poziva: "+b.getBrojPoziva());
		
//		NelderMead.alg(new Vector(0.1,0.3),1.0,d);
//		System.out.println("Broj poziva: "+d.getBrojPoziva());
//		d.setBrojPoziva(0);
//		HookeJeevs.hooke(new Vector(0.1,0.3), 0.5, 1E-6, d);
//		System.out.println("Broj poziva: "+d.getBrojPoziva());
		
//		NelderMead.alg(new Vector(0,0,0,0,0,0,0,0,0,0),1.0,t);
//		System.out.println("Broj poziva: "+t.getBrojPoziva());
//		t.setBrojPoziva(0);
//		HookeJeevs.hooke(new Vector(0,0,0,0,0,0,0,0,0), 0.5, 1E-6, t);
//		System.out.println("Broj poziva: "+t.getBrojPoziva());
//		
//		NelderMead.alg(new Vector(5.1,1.1),1.0,j);
//		System.out.println("Broj poziva: "+j.getBrojPoziva());
//		j.setBrojPoziva(0);
		//HookeJeevs.hooke(new Vector(5.1,1.1), 5, 1E-6, j);
		System.out.println("Broj poziva: "+j.getBrojPoziva());
		
		
		
//		NelderMead.alg(new Vector(0,0,0),1.0,s);
//		System.out.println("Broj poziva: "+s.getBrojPoziva());
//		s.setBrojPoziva(0);
//		HookeJeevs.hooke(new Vector(0,0,0), 0.5, 1E-6, s);
//		System.out.println("Broj poziva: "+s.getBrojPoziva());
		
		//prvi();
		//tri();
		//ceti();
		//peti();
	}
	public static void prvi(){
		PrviZad fja=new PrviZad();
		NelderMead.alg(new Vector(10),1.0,fja);
		System.out.println("Broj poziva: "+fja.getBrojPoziva());
		//HookeJeevs.hooke(new Vector(2000), 0.5, 1E-6, fja);
		//System.out.println(ZlatniRez.zlatniRez(10, 1E-6, fja));
		
	}
	public static void treci(){
		Jakoboviceva j=new Jakoboviceva();
	//NelderMead.alg(new Vector(20,20),1.0,j);
//		System.out.println("Broj poziva: "+j.getBrojPoziva());
//		j.setBrojPoziva(0);
		HookeJeevs.hooke(new Vector(1,1),1, 1E-6, j);
		System.out.println("Broj poziva: "+j.getBrojPoziva());
	}
	
	public static void ceti(){
		Jakoboviceva j=new Jakoboviceva();
		NelderMead.alg(new Vector(20,20),20.0,j);
		System.out.println("Broj poziva: "+j.getBrojPoziva());
	}
	
	public static void peti(){
		Schaffer s=new Schaffer();
		Random r=new Random(System.currentTimeMillis());
		double a=r.nextDouble()*100-50;
		double b=r.nextDouble()*100-50;
		NelderMead.alg(new Vector(a,b),1.0,s);
		//HookeJeevs.hooke(new Vector(a,b), 1, 1E-12, s);
		System.out.println("Broj poziva: "+s.getBrojPoziva());
		
		
	}
}
