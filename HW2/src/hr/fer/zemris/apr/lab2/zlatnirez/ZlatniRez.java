package hr.fer.zemris.apr.lab2.zlatnirez;
import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.linearna.Vector;


public class ZlatniRez {
	
	public static Interval zlatniRez(double pocetna,double e,Funkcija f){
		Interval unim=unimodalniInterval(1, pocetna, f);
		//System.out.println("Unimodalni: "+unim);
		return zlatniRez(unim.getS(), unim.getE(), e, f);
	}
	
	public static Interval zlatniRez(double a,double b,double e,Funkcija f){
		double k=0.5*(Math.sqrt(5)-1);
		double c = b - k * (b - a);
		double d = a + k * (b - a);
		double fc = f.compute(new Vector(c));
		double fd = f.compute(new Vector(d));
		while((b - a) > e) {
			//System.out.println("a: "+a+" c: "+c+" d:"+d+" b:"+b);
			if(fc < fd) {
				b = d;
				d = c;
				c = b - k * (b - a);
				fd = fc;
				fc =  f.compute(new Vector(c));
			} else {
				a = c;
				c = d;
				d = a + k * (b - a);
				fc = fd;
				fd =  f.compute(new Vector(d));
			}
		}
		return new Interval(a, b); // ili nove vrijednosti a i b
	}
	
	public static Interval unimodalniInterval(double h,double tocka,Funkcija ciljnaFunkcija) {
		double l = tocka - h;
		double r = tocka + h;
		double m = tocka;
		int korak = 1;

		double fm = ciljnaFunkcija.compute(new Vector(tocka));
		double fl = ciljnaFunkcija.compute(new Vector(l));
		double fr = ciljnaFunkcija.compute(new Vector(r));

		if (fm < fr && fm < fl)
			return new Interval(l, r);
		else if (fm > fr)
			do {
				l = m;
				m = r;
				fm = fr;
				r = tocka + h * (korak *= 2);
				fr = ciljnaFunkcija.compute(new Vector(r));
			} while (fm > fr);
		else
			do {
				r = m;
				m = l;
				fm = fl;
				l = tocka -h * (korak *= 2);
				fl = ciljnaFunkcija.compute(new Vector(l));

			} while (fm > fl);
		return new Interval(l, r);

	}
	

}
