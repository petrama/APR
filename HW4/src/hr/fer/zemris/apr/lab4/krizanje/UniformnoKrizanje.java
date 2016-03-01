package hr.fer.zemris.apr.lab4.krizanje;




public class UniformnoKrizanje extends Krizanje{

	

	@Override
	public Double krizaj(double a, double b, int n, int r) {

		return (((int)a&(int)b)|((int)r&(int)a)|((int)r&(int)b))*1.0;
	}

}
