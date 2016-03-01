package hr.fer.zemris.apr.lab2.funkcije;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.Matrix;
import hr.fer.zemris.linearna.Vector;
import hr.fer.zemris.linearna.IVector;

public class Banana extends Funkcija {

	@Override
	public Double compute2(IVector v) {
		return 100 * (v.get(1) - v.get(0) * v.get(0))
				* (v.get(1) - v.get(0) * v.get(0)) + (1 - v.get(0))
				* (1 - v.get(0));
	}

	@Override
	public IVector gradient2(IVector v) {
		double x1 = v.get(0);
		double x2 = v.get(1);
		//double g1 = 2 * (200 * x1 * x1 * x1 - 200 * x2 * x1 + x1 - 1);
		double g1=400*x1*x1*x1-400*x1*x2+2*x1-2;
		double g2 = 200 * (x2 - x1 * x1);
		
		return new Vector(g1, g2);

	}

	@Override
	public IMatrix hesseova2(IVector v) {
		double x1 = v.get(0);
		double x2 = v.get(1);
		Matrix m = new Matrix(2, 2, new double[][] {
				{ 1200 * x1 * x1 - 400 * x2 + 2, -400 * x1 },
				{ -400 * x1, 200 } }, true);

		return m;
	}
}