package hr.fer.zemris.apr.hw5;



import hr.fer.zemris.linearna.IMatrix;


public class RungeKutta4 extends Postupak {
	private IMatrix a;
	private IMatrix b;
	private Double period;
	

	
	public RungeKutta4(IMatrix a, IMatrix b,Double period) {
		super();
		this.a = a;
		this.b = b;
		this.period=period;
	}




	public  IMatrix getNext(IMatrix xk){
		IMatrix m1=a.nMultiply(xk).add(b);
		System.out.println(m1);
		IMatrix m2=a.nMultiply(m1.nScalarMultiply(period*0.5).add(xk)).add(b);
		System.out.println(m2);
		IMatrix m3=a.nMultiply(m2.nScalarMultiply(period*0.5).add(xk)).add(b);
		System.out.println(m3);
		IMatrix m4=a.nMultiply(m3.nScalarMultiply(period*1.0).add(xk)).add(b);
		return xk.nAdd(m1.add(m2.scalarMultiply(2)).add(m3.scalarMultiply(2)).add(m4).scalarMultiply(period/6));
		
	}
	
	
	
	

}
