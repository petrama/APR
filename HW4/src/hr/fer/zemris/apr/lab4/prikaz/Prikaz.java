package hr.fer.zemris.apr.lab4.prikaz;

import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.linearna.IVector;

public abstract class Prikaz {
	
	public abstract IVector[] stvoriPopulaciju(double dg,double gg,double preciznost,int dimenzionalnostVarijable,int velicinaPopulacije);
	
	public abstract void ispisiPopulaciju(IVector[] populacija);
	public abstract IVector mutiraj(IVector jedinka,Double p);
	public abstract IVector krizaj(IVector prva,IVector druga);
	public abstract Double vrijednostFunkcije(Funkcija f,IVector x);
}
