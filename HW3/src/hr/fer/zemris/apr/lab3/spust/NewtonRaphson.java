package hr.fer.zemris.apr.lab3.spust;

import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.apr.lab2.zlatnirez.Interval;
import hr.fer.zemris.apr.lab2.zlatnirez.ZlatniRez;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.lup.LUP;

public class NewtonRaphson {

	public static void alg(Funkcija f, IVector poc, double epsilon,
			boolean zlatni) {

		IMatrix hesse = f.hesseova(poc);
		IVector grad = f.gradient(poc).scalarMultiply(-1);
		IVector dX = LUP.rjesiSustav(hesse, grad.toColumnMatrix(true), true)
				.toVector(true);
		double norm = dX.norm();
		while (norm > epsilon) {
			if (zlatni) {
				dX.normalize();
				// jednodim fja koja predstavlja pravac s poč točkom x0 i
				// vektorom smjera norm gradijenta
				// Ona se predaje algoritmu zlatnog reza koji nalazi optimalni
				// lambda
				// koji definira novu točku
				Funkcija pravac = new Funkcija() {

					@Override
					public IVector gradient(IVector t) {
						return null;
					}

					@Override
					public Double compute2(IVector v) {
						double lambda = v.get(0);
						return f.compute(poc.nAdd(dX
								.nScalarMultiply(lambda)));

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
				};
				// minimizira po lambdi
				Interval i = ZlatniRez.zlatniRez(1, epsilon, pravac);
				System.out.println("Interval: " + i);
				double lam = (i.getE() - i.getS()) * 0.5 + i.getS();// dobivena
																	// lambda
				System.out.println("Lambda: " + lam);
				poc.add(dX.nScalarMultiply(lam));
			} else {
				poc.add(dX);
			}
			System.out.println("Tocka x=" + poc);

			hesse = f.hesseova(poc);
			
			 grad=f.gradient(poc).scalarMultiply(-1);
			 IVector pom = LUP.rjesiSustav(hesse, grad.toColumnMatrix(true), true)
						.toVector(true);
				for (int j = 0; j < dX.getDimension(); j++)//zbog final price
					dX.set(j, pom.get(j));
			
			norm = dX.norm();
			System.out.println("Dx= " + dX);
			System.out.println("norma=" + norm);
			
		}
		System.out.println("Dobivena tocka je: " + poc);

	}

}