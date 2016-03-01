package hr.fer.zemris.apr.lab3.tans;

import java.util.List;

import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.apr.lab2.simpleks.NelderMead;
import hr.fer.zemris.apr.lab3.ogranicenje.Ogranicenje;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;

public class Transformacija {
	public static Double delta = 0.5;
	public static Double epsilon = 1E-6;
	public Double r = 1.0;

	public void transformiraj(Funkcija f, List<Ogranicenje> implicitna,
			IVector pocetna) {
		IVector prva = pocetna;
		do {
			Funkcija fja = new Funkcija() {

				@Override
				public Double compute2(IVector v) {
					double p = f.compute(v);
					for (Ogranicenje o : implicitna) {
						double ogr = o.getF().compute(v);
						if (o.isJdzba()) {
							p += (1. / r) * ogr * ogr;
						} else {
							p -= r * Math.log(ogr);

						}
					}
					return p;
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
			// IVector druga=HookeJeevs.hooke(pocetna,delta, epsilon, fja);
			IVector druga = NelderMead.alg(pocetna, delta, fja);
			if (druga.nSub(prva).norm() < epsilon) {
				System.out.println("Nadjena je tocka: " + druga + " f="
						+ f.compute(druga));

				break;
			}
			prva = druga;
			r /= 10;
		} while (true);

	}
}
