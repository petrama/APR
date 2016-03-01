package hr.fer.zemris.apr.lab2.funkcije;



import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Matrix;
import hr.fer.zemris.linearna.Vector;


public class Druga extends Funkcija {
	@Override
	public Double compute2(IVector v) {
		return (v.get(0)-4)*(v.get(0)-4)+4*(v.get(1)-2)*(v.get(1)-2);
	}


	@Override
	public IMatrix hesseova2(IVector v) {
		return Matrix.parseSimple("2 0|0 8");
	}

	@Override
	public IVector gradient2(IVector t) {
		return new Vector(2*(t.get(0)-4),8*(t.get(1)-2));
	}
}
