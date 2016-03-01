package hr.fer.zemris.apr.lab2.funkcije;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;


public abstract class Funkcija {
	private double brojPoziva;
	private double brojPozivaGradijenta;
	private double brojPozivaHesse;
	
	public Funkcija() {
		setBrojPoziva(0);
		setBrojPozivaGradijenta(0);
		setBrojPozivaHesse(0);
		
	}

	
	public Double compute(IVector v){
		setBrojPoziva(getBrojPoziva() + 1);
		return compute2(v);
	}
	public abstract Double compute2(IVector v);
	
	public  abstract IMatrix hesseova2(IVector v);
	
	public  IMatrix hesseova(IVector v){
		setBrojPozivaHesse(getBrojPozivaHesse()+1);
		return hesseova2(v);
	}
	
	
	public abstract IVector gradient2(IVector t);
	
	public IVector gradient(IVector t){
		setBrojPozivaGradijenta(getBrojPozivaGradijenta()+1);
	
		return gradient2(t);
	}
	
	

	public double getBrojPoziva() {
		return brojPoziva;
	}

	public void setBrojPoziva(double brojPoziva) {
		this.brojPoziva = brojPoziva;
	}
	
	private void setBrojPozivaGradijenta(double i) {
		this.brojPozivaGradijenta=i;
		
	}
	public double getBrojPozivaGradijenta() {
		return brojPozivaGradijenta;
	}

	public double getBrojPozivaHesse() {
		return brojPozivaHesse;
	}
	
	public void setBrojPozivaHesse(double brojPozivaHesse) {
		this.brojPozivaHesse = brojPozivaHesse;
	}

	
	
}
