package hr.fer.zemris.apr.lab2.simpleks;

import java.util.Arrays;

import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Vector;

public class NelderMead {
	public static double alpha = 1;
	public static double beta = 0.5;
	public static double gamma = 2;
	public static double sigma = 0.5;

	public static double epsilon = 1E-6;

	public static IVector alg(IVector pocetna, Double pomak, Funkcija f) {
		IVector[] x = new Vector[pocetna.getDimension() + 1];
		double[] fx = new double[x.length];
		for (int i = 0; i < pocetna.getDimension(); i++) {// kreacija simpleksa
			x[i] = pocetna.copy().set(i, pocetna.get(i) + pomak);
			fx[i] = f.compute(x[i]);
		}
		x[pocetna.getDimension()] = pocetna;
		fx[pocetna.getDimension()] = f.compute(pocetna);

		double kriterij = 0;
		do {
			System.out.println("Simpleks: " + Arrays.toString(x));
			System.out.println("Vrijednosti: " + Arrays.toString(fx));
			double min = fx[0];
			double max = fx[0];
			int l = 0;
			int h = 0;
			for (int i = 1; i < x.length; i++) {
				//fx[i] = f.compute(x[i]);
				if (fx[i] > max) {
					max = fx[i];
					h = i;
				} else if (fx[i] < min) {
					min = fx[i];
					l = i;
				}
			}

			System.out.println("Min: " + l + " f=" + min);
			System.out.println("Max: " + h + " f=" + max);
			// centroid
			IVector xc = x[l].copy();
			for (int i = 0; i < x.length; i++)
				if (i != l && i != h)
					xc.add(x[i]);
			xc = xc.nScalarMultiply(1. / (x.length - 1));
			double fxc = f.compute(xc);
			System.out.println("centroid: " + xc + " f= " + fxc);

			// refleksija
			IVector xr = xc.nScalarMultiply(1 + alpha).nSub(x[h]);
			double fxr = f.compute(xr);
			System.out.println("xr= " + xr + " fxr=" + fxr);

			if (fxr < fx[l]) {// ako je refleksija bila jako uspjesna
				// probaj ekspanziju
				IVector xe = xc.nScalarMultiply(1 - gamma).nAdd(
						xr.nScalarMultiply(gamma));
				double fxe = f.compute(xe);
				System.out.println("xe= " + xe + " fxe=" + fxe);
				if (fxe < fx[l]) {
					x[h] = xe;
					fx[h] = fxe;
				} else {
					x[h] = xr;
					fx[h] = fxr;
				}

			} else {// refleksija nije bila jako uspjesna
				boolean grozna = true;
				for (int i = 0; i < fx.length; i++)
					if (i != h && fxr < fx[i])
						grozna = false;
				if (grozna) {
					if (fxr < fx[h]) {
						x[h] = xr;
						fx[h] = fxr;
					}
					IVector xk = xc.nScalarMultiply(1 - beta).nAdd(
							x[h].nScalarMultiply(beta));
					double fxk = f.compute(xk);
					if (fxk < fx[h]) {
						System.err.println("Kontrakcija");
						x[h] = xk;
						fx[h] = fxk;
					} else {
						// pomak prema xl
						System.err.println("Pomak prema xl= " + x[l]);
						for (int i = 0; i < x.length; i++) {
							if (i != l) {
								x[i] = x[i].nAdd(x[l]).nScalarMultiply(sigma);
								fx[i] = f.compute(x[i]);

							}
						}
					}
				} else {
					x[h] = xr;
					fx[h] = f.compute(x[h]);
				}
			}
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
				
				return xc;
			}
			// if(f.getBrojPoziva()>50){
			// System.out.println("velik broj poziva fje, prekidam..");
			// break;
			// }

		} while (true);

	}

}
