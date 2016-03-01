package hr.fer.zemris.apr.lab3.box;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.apr.lab3.ogranicenje.Ogranicenje;
import hr.fer.zemris.linearna.IVector;

public class Box {
	public static double epsilon = 1E-6;
	public static double alpha = 1;

	public static void box(Funkcija f, IVector v0, IVector xd, IVector xg,
			List<Ogranicenje> implicitna) {
		Random ran = new Random(System.currentTimeMillis());
		if (zadovoljavaEksplicitno(xd, xg, v0) == false
				|| zadovoljavaImplicitna(implicitna, v0) == false) {
			System.out.println("Pocetna tocka ne zadovoljava ogranicenja!");
			return;
		}
		int n = v0.getDimension();
		IVector xc = v0;
		IVector[] x = new IVector[2 * n];
		double[] fx = new double[2 * n];
		x[0] = v0;
		fx[0] = f.compute(v0);
		for (int t = 1; t < 2 * n; t++) {
			double r = ran.nextDouble();
			x[t] = xd.nAdd(xg.nSub(xd).scalarMultiply(r));
			while (zadovoljavaImplicitna(implicitna, x[t]) == false) {
				x[t].add(xc).scalarMultiply(0.5);
			}
			fx[t] = f.compute(x[t]);
			xc = x[0].copy();
			for (int s = 1; s <= t; s++)
				xc.add(x[s]);
			xc.scalarMultiply(1. / t);
		}

		double kriterij = 0;
		do {
			System.out.println("Simpleks: " + Arrays.toString(x));
			System.out.println("Vrijednosti: " + Arrays.toString(fx));
			double max = fx[0];
			
			int h = 0;
			for (int i = 1; i < x.length; i++) {
				//fx[i] = f.compute(x[i]);
				if (fx[i] > max) {
				max = fx[i];
				h = i;
				}
			}
			int h2=0;
			if(h==0)h2=1;
			double max2 = fx[h2];
			for(int i=0;i<x.length;i++){
				if(i==h)continue;
				if (fx[i] > max2) {
					max2 = fx[i];
					h2 = i;
					}
			}
			System.out.println("Max: " + h + " f=" + max);
			System.out.println("Max2: " + h2 + " f=" + max2);
			// centroid
			xc = x[h2].copy();
			System.out.println(xc);
			for (int i = 0; i < x.length; i++)
				if (i != h && i!=h2){
					xc.add(x[i]);
					System.out.println(xc);
				
				}
					
			xc.scalarMultiply(1. / (x.length - 1));
			double fxc = f.compute(xc);
			System.out.println("centroid: " + xc + " f= " + fxc);
			
			// refleksija
			IVector xr = xc.nScalarMultiply(1 + alpha).nSub(x[h]);
			double fxr = f.compute(xr);
			System.out.println("xr= " + xr + " fxr=" + fxr);

			if(!zadovoljavaEksplicitno(xd, xg, xr)){
				for(int j=0;j<xr.getDimension();j++){
					if(xr.get(j)<xd.get(j))xr.set(j, xd.get(j));
					if(xr.get(j)>xg.get(j))xr.set(j, xg.get(j));
					
				}
			}
			while(!zadovoljavaImplicitna(implicitna, xr)){
				xr.add(xc).scalarMultiply(0.5);
			}
			if(f.compute(xr)>f.compute(x[h2])){
				xr.add(xc).scalarMultiply(0.5);
			}
			x[h]=xr;
			fx[h]=f.compute(xr);
			
			kriterij = 0;
			for (int i = 0; i < fx.length; i++) {
				kriterij += (fx[i] - fxc) * (fx[i] - fxc);
			}
			kriterij /= fx.length;
			kriterij = Math.sqrt(kriterij);
			System.err.println("Odstupanje= " + kriterij);
			if (kriterij <= epsilon) {
				System.out.println("Simpleks: " + Arrays.toString(x));
				System.out.println("Vrijednosti: " + Arrays.toString(fx));
				System.out.println("Dobivena tocka je xc=" + xc + " f=" + fxc);

				break;
			}
			// if(f.getBrojPoziva()>50){
			// System.out.println("velik broj poziva fje, prekidam..");
			// break;
			// }

		} while (true);

	}

	private static boolean zadovoljavaImplicitna(List<Ogranicenje> implicitna,
			IVector v) {
		for (Ogranicenje o : implicitna) {
			if (o.zadovoljava(v) == false)
				return false;
		}
		return true;
	}

	private static boolean zadovoljavaEksplicitno(IVector xd, IVector xg,
			IVector v) {
		
		for (int i = 0; i < v.getDimension(); i++)
			if ((xg!=null && v.get(i) > xg.get(i)) || (xd!=null && v.get(i) < xd.get(i)))
				return false;
		return true;
	}

}
