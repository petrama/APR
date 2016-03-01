package hr.fer.zemris.apr.lab2.zlatnirez;

public class Interval {
	private double s;
	private double e;
	
	public Interval(double s, double e) {
		super();
		this.s = s;
		this.e = e;
	}

	public double getS() {
		return s;
	}

	public void setS(double s) {
		this.s = s;
	}

	public double getE() {
		return e;
	}

	public void setE(double e) {
		this.e = e;
	}

	@Override
	public String toString() {
		return "Interval [s=" + s + ", e=" + e + "]";
	}
	
	
	
	
}
