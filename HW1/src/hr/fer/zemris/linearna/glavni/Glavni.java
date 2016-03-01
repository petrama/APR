package hr.fer.zemris.linearna.glavni;

import java.io.IOException;





import hr.fer.zemris.linearna.AbstractMatrix;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Matrix;
import hr.fer.zemris.linearna.Vector;
import hr.fer.zemris.linearna.lup.LUP;

public class Glavni {
	public static void main(String[] args) throws IOException {
		prvi();
		//drugi();
		//treci();
		//ceti();
		//peti();
		//sesti();
	}
	
	public static void prvi(){
		IMatrix m=Matrix.parseSimple("5.5 4.5 8.1| 75.5 4.4 6.1| 4.5 12.4 4.4");
		IMatrix c=m.nScalarMultiply(1e-5);
		IMatrix d=c.nScalarMultiply(1e5);
		System.out.println(m.equals(d));
		System.out.println(m);
		System.out.println(d);
		System.out.println(m.nInvert());
		System.out.println(((AbstractMatrix)m).nInvertUsingLUP());
	}
	
	public static void drugi() throws IOException{
		Matrix m2=Matrix.loadFromFile("1mat");
		Matrix v2=Matrix.loadFromFile("1vec");
		LUP.rjesiSustav(m2.copy(), v2,false);
		LUP.rjesiSustav(m2.copy(), v2,true);
	
	}
	
	public static void treci() throws IOException{
		Matrix m=Matrix.loadFromFile("treci");
		Matrix v=Matrix.parseSimple("1|3|1");	
		LUP.rjesiSustav(m.copy(), v, false);
		LUP.rjesiSustav(m.copy(), v, true);
		m.nInvert();
	}
	public static void ceti() throws IOException {
		Matrix m4=Matrix.loadFromFile("4mat");
		Matrix v4=Matrix.loadFromFile("4vec");
		LUP.rjesiSustav(m4.copy(), v4, false);
		LUP.rjesiSustav(m4.copy(), v4, true);
	}
	
	public static void peti() throws IOException {
		Matrix m5=Matrix.loadFromFile("5mat");
		Matrix v5=Matrix.loadFromFile("5vec");
		LUP.rjesiSustav(m5.copy(), v5, false);
		LUP.rjesiSustav(m5.copy(), v5, true);
	}
	public static void sesti() throws IOException {
		Matrix m6=Matrix.loadFromFile("6mat");
		Matrix v6=Matrix.loadFromFile("6vec");
		LUP.rjesiSustav(m6.copy(), v6, false);
		LUP.rjesiSustav(m6.copy(), v6, true);
	}
	

}
