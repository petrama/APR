package hr.fer.zemris.apr.lab4;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JEditorPane;

import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.apr.lab4.prikaz.Prikaz;
import hr.fer.zemris.linearna.IVector;

public class GenetskiAlgoritam {
	private int velicinaPopulacije;
	private IVector[] populacija;
	private double dg;
	private double gg;

	private Prikaz prikaz;
	private Double vjerojatnostMutacije;

	private int bestIndex;
	private int worstIndex;
	private Double best;
	private Double worst;

	private Funkcija fjaCilja;
	private double[] dobrotaJedinki;

	public GenetskiAlgoritam(int velicinaPopulacije, double dg, double gg,
			double preciznost, int dimenzionalnost, Prikaz prikaz,
			double vjerojatnostMutacije, Funkcija f) {
		super();
		this.velicinaPopulacije = velicinaPopulacije;
		dobrotaJedinki=new double[velicinaPopulacije];
		this.dg = dg;
		this.gg = gg;
		this.prikaz = prikaz;
		populacija = prikaz.stvoriPopulaciju(dg, gg, preciznost,
				dimenzionalnost, velicinaPopulacije);
		fjaCilja = f;
		bestIndex = 0;
		best = prikaz.vrijednostFunkcije(f, populacija[bestIndex]);
		worstIndex = 0;
		worst = prikaz.vrijednostFunkcije(f, populacija[worstIndex]);
		this.vjerojatnostMutacije=vjerojatnostMutacije;
	}

	public void odrediNajboljuINajgoru() {
		for (int i = 0; i < velicinaPopulacije; i++) {
			Double pom = prikaz.vrijednostFunkcije(fjaCilja, populacija[i]);
			if (pom < prikaz
					.vrijednostFunkcije(fjaCilja, populacija[bestIndex])) {
				bestIndex = i;
				best = pom;
			}
			if (pom > prikaz.vrijednostFunkcije(fjaCilja,
					populacija[worstIndex])) {
				worstIndex = i;
				worst = pom;
			}
		}
	}
	
	public void algoritam(int brojIteracija,int k){
		odrediNajboljuINajgoru();
		evaluirajPopulaciju();
		Random r=new Random(System.currentTimeMillis());
		Integer[] indeksiOdabranih=new Integer[k];
		List<Integer> moguciIndeksi=new ArrayList<Integer>(velicinaPopulacije);
		for(int i=0;i<velicinaPopulacije;i++)moguciIndeksi.add(i);
		int brojac=0;
		System.out.println("Populacija: ");
		prikaz.ispisiPopulaciju(populacija);
		System.out.println(Arrays.toString(dobrotaJedinki));
		System.out.println("indeks best "+bestIndex);
		System.out.println("indeks worst "+worstIndex);
		int brojacBoljih=0;
		do{
			
			System.err.println("Iteracija: "+brojac);
			System.err.println("y="+prikaz.vrijednostFunkcije(fjaCilja, populacija[bestIndex]));
			//slucajan odabir k jedinki
			Collections.shuffle(moguciIndeksi,new Random(System.currentTimeMillis()));
			moguciIndeksi.subList(0, k).toArray(indeksiOdabranih);
			System.out.println("Indeksi odabranih: "+Arrays.toString(indeksiOdabranih));
			//trazenje najlosije od odabranih
			int localWorstIndex=0;
			double localWorstFitness=dobrotaJedinki[indeksiOdabranih[localWorstIndex]];
			
			for(int i=1;i<k;i++){
				double pom=dobrotaJedinki[indeksiOdabranih[i]];
				if(pom<localWorstFitness){
					localWorstFitness=pom;
					localWorstIndex=i;
				}
			}
			int prviLocalIndex;
			int drugiLocalIndex;
			do prviLocalIndex=r.nextInt(k); while( prviLocalIndex==localWorstIndex);
			do drugiLocalIndex=r.nextInt(k); while(drugiLocalIndex==localWorstIndex || drugiLocalIndex==prviLocalIndex);
			
			int indeksPrvogRoditelja=indeksiOdabranih[prviLocalIndex];
			int indeksDrugogRoditelja=indeksiOdabranih[drugiLocalIndex];
			int indeksOdbacenika=indeksiOdabranih[localWorstIndex];
			
			System.out.println("Indeks odbacenika: "+indeksOdbacenika);
			System.out.println("indeks prvog: "+indeksPrvogRoditelja);
			System.out.println("Indeks drugog: "+indeksDrugogRoditelja);
			
			IVector novaJedinka=prikaz.krizaj(populacija[indeksPrvogRoditelja], populacija[indeksDrugogRoditelja]);
			System.out.println("Nova: "+novaJedinka);
			IVector mutiranaNova=prikaz.mutiraj(novaJedinka, vjerojatnostMutacije);
			System.out.println("Mutirana nova: "+mutiranaNova);
			Double fitnessNove=fitness(mutiranaNova);
			
			//if(fitnessNove>localWorstFitness){
				//brojacBoljih++;
				populacija[indeksOdbacenika]=mutiranaNova;
				dobrotaJedinki[indeksOdbacenika]=fitnessNove;
				boolean promjena=false;
				if(fitnessNove>best){
					best=fitnessNove;
					bestIndex=indeksOdbacenika;
					promjena=true;
				}
				if(worstIndex==indeksOdbacenika){//izbacen je upravo onaj globalno najgori
					odrediNajboljuINajgoru();
					promjena=true;
				}
				if(promjena)evaluirajPopulaciju();
				
		//	}
			
			
			
		}while(++brojac<brojIteracija);
		System.out.println(prikaz.vrijednostFunkcije(fjaCilja, populacija[bestIndex]));
		System.err.println("Od toga je korisnih bilo: "+brojacBoljih);
	}

	private void evaluirajPopulaciju() {
		for(int i=0;i<velicinaPopulacije;i++){
			dobrotaJedinki[i]=fitness(populacija[i]);
		}
		
	}

	public double fitness(IVector jedinka){
		return (prikaz.vrijednostFunkcije(fjaCilja, jedinka)-worst)/(best-worst);
		
	}

}
