package hr.fer.zemris.apr.lab4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import hr.fer.zemris.apr.lab2.funkcije.Banana;
import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.apr.lab4.krizanje.Krizanje;
import hr.fer.zemris.apr.lab4.krizanje.SinglePoint;
import hr.fer.zemris.apr.lab4.krizanje.UniformnoKrizanje;
import hr.fer.zemris.apr.lab4.prikaz.BinarniPrikaz;
import hr.fer.zemris.apr.lab4.prikaz.Prikaz;

public class Main {
	public static void main(String[] args) {
		int velicinaPopulacije=6;
		double dg=-50;
		double gg=150;
		double preciznost=1E-3;
		int dimenzi=2;
		Krizanje krizanje=new SinglePoint();
		Prikaz prikaz=new BinarniPrikaz(preciznost, dg, gg, krizanje);
		double p=0.1;
		Funkcija f=new Banana();
		
		
		GenetskiAlgoritam ga=new GenetskiAlgoritam(velicinaPopulacije, dg,gg,preciznost,dimenzi,prikaz ,p , f);
		ga.algoritam(5000, 3);
	}
}
