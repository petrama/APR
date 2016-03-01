package hr.fer.zemris.apr.hw5;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.Matrix;

public class Trapez extends Postupak {
	IMatrix R;
	IMatrix S;
	
	public Trapez(IMatrix a,IMatrix b,Double period) {
		IMatrix eye=a.copy().makeIdentity();
		IMatrix at2=a.nScalarMultiply(period*0.5);
		IMatrix prva=(eye.nSub(at2)).nInvert();
		IMatrix druga=(eye.nAdd(at2));
		R=prva.nMultiply(druga);
		S=prva.nMultiply(b.nScalarMultiply(period*0.5));
	}
	
	@Override
	public IMatrix getNext(IMatrix xk) {
		return R.nMultiply(xk).add(S);
	}

}
