package hr.fer.zemris.linearna.lup;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Matrix;

public class LUP {
	//public static double epsilon=1e-9;
	private static void swapRows(IMatrix m,int i,int j){
		for (int z = 0; z < m.getColsCount(); z++) {
			double temp = m.get(i, z);
			m.set(i, z, m.get(j, z));
			m.set(j, z, temp);
		}
	}
	public static IMatrix getLUMatrix(IMatrix a,boolean lup) {
		if (a.getRowsCount() != a.getColsCount())
			return null;
		int n = a.getRowsCount();
		IMatrix permutacijska=a.copy().makeIdentity();
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if(lup){
					double pivot = 0;
					int indeks = -1;
					for (int jj = i; jj < n; jj++) {
						if (Math.abs(a.get(jj, i)) > pivot) {
							pivot = Math.abs(a.get(jj, i));
							indeks = jj;
						}
					}
					if (Math.abs(pivot )<Matrix .epsilon) {
						return null;
					}
					swapRows(a, i,indeks);
					swapRows(permutacijska,i,indeks);
				}

				if (Math.abs(a.get(i, i)) >Matrix.epsilon ) {
					a.set(j, i, a.get(j, i) / a.get(i, i));
				} else {
					System.out.println("Sustav nije moguce rjesiti LU dekompozicijom, stozerni element jednak je nuli!");
					return null;
				}
				for (int k = i + 1; k < n; k++) {
					a.set(j, k,
							a.get(j, k) - a.get(j, i) * a.get(i, k));
				}
			}
		}
		return permutacijska;
	}
	
	public static IMatrix unaprijed(IMatrix l,IMatrix bb){
		IVector b=bb.toVector(false);
		IVector y=bb.toVector(false);
	
		for(int i=0;i<y.getDimension();i++){
			double sum=0;
			for(int j=0;j<i;j++){
				sum+=l.get(i, j)*y.get(j);
			}
			y.set(i, b.get(i)-sum);
		} 
		return y.toColumnMatrix(false);
		
	}
	
	public static IMatrix unatrag(IMatrix u,IMatrix yy){
		IVector x=yy.toVector(false);
		IVector y=yy.toVector(false);
		for(int i=x.getDimension()-1;i>=0;--i){
			double sum=0;
			if(Math.abs(u.get(i,i))<Matrix.epsilon){
				System.out.println("Sustav nema rješenja!");
				return null;
			}
			for(int j=x.getDimension()-1;j>i;j--) sum+=u.get(i, j)*x.get(j);
			x.set(i, (y.get(i)-sum)/u.get(i, i));
		}
		return x.toColumnMatrix(false);
		
	}
	
	public static IMatrix rjesiSustav(IMatrix a,IMatrix b,boolean lup){
		if(lup){
			System.out.println("LUP dekompozicija\n----------\n");
		}else{
			System.out.println("LU dekompozicija\n----------\n");
		}
		System.out.println("A matrica:\n"+a);
		System.out.println("b vektor:\n"+b);
		IMatrix perm=LUP.getLUMatrix(a,lup);
		if(perm==null)return  null;
		System.out.println("LU matrica:\n"+a);
		System.out.println("P matrica:\n"+perm);
		IMatrix y=LUP.unaprijed(a, perm.nMultiply(b));
		System.out.println("y vektor:\n"+y);
		IMatrix x=LUP.unatrag(a, y);
		System.out.println("Rjesenje sustava x=\n"+x);
		return x;
	}

}
