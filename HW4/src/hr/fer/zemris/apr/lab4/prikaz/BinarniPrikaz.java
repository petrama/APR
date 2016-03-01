package hr.fer.zemris.apr.lab4.prikaz;

import java.util.Arrays;
import java.util.Random;











import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.apr.lab4.krizanje.Krizanje;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Vector;

public class BinarniPrikaz extends Prikaz {
	private int brojBitova;
	private int maxBroj;
	private double dg;
	private double gg;
	
	private Krizanje krizanje;
	
	public BinarniPrikaz(double preciznost,double dg,double gg,Krizanje k) {
		double arg=(gg-dg)*1.0/preciznost;
		brojBitova=(int)Math.ceil(Math.log(arg)/Math.log(2));
		this.maxBroj=(int)Math.pow(2, brojBitova)-1;
		this.dg=dg;
		this.gg=gg;
		krizanje=k;
	}
	
	
	public int getBrojBitova() {
		return brojBitova;
	}
	
	public Double pretvori(Double broj){
		return dg+broj/maxBroj*(gg-dg);
	}
	public IVector pretvori(IVector binarni){
		IVector novi=binarni.copy();
		for(int i=0;i<novi.getDimension();i++){
			novi.set(i,pretvori(novi.get(i)));
		}
		return novi;
	}
	
	public IVector[] pretvori(IVector[] populacija){
		IVector[] novo=populacija.clone();
		for(int i=0;i<novo.length;i++){
			novo[i]=pretvori(novo[i]);
		}
		return novo;
	}
	

	

	@Override
	public IVector[] stvoriPopulaciju(double dg, double gg, double preciznost,
			int dimenzionalnostVarijable, int velicinaPopulacije) {
		IVector[] populacija=new IVector[velicinaPopulacije];
		Random r=new Random(System.currentTimeMillis());
		for(int i=0;i<velicinaPopulacije;i++){
			double[] pom=new double[dimenzionalnostVarijable];
			for(int j=0;j<dimenzionalnostVarijable;j++){
				pom[j]=r.nextInt(maxBroj+1);
			}
			populacija[i]=new Vector(pom);
		}
		return populacija;
		
	}


	


	@Override
	public void ispisiPopulaciju(IVector[] populacija) {
		System.out.println(Arrays.toString(populacija)+"\n"+Arrays.toString(pretvori(populacija)));
		
	}

	@Override
	public IVector krizaj(IVector prva,IVector druga){
		return krizanje.krizaj(prva, druga, brojBitova);
	}
	@Override
	public IVector mutiraj(IVector jedink, Double p) {
		Random r=new Random(System.currentTimeMillis());
		IVector jedinka=jedink.copy();
		for(int i=0;i<jedinka.getDimension();i++){
			String maska="";
			for(int j=0;j<brojBitova;j++){
				if(r.nextDouble()<p){
					maska+=1;
				}else{
					maska+=0;
				}
			}
			jedinka.set(i, (int)jedinka.get(i)^Integer.parseInt(maska, 2));
		}
		return jedinka;
	}


	@Override
	public Double vrijednostFunkcije(Funkcija f,IVector jedinka) {
		return f.compute(pretvori(jedinka));
	}

}
