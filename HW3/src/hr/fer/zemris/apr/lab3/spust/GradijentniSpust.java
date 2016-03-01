package hr.fer.zemris.apr.lab3.spust;



import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.apr.lab2.zlatnirez.Interval;
import hr.fer.zemris.apr.lab2.zlatnirez.ZlatniRez;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;


public class GradijentniSpust {
	
	
		

	public static void spust(Funkcija f,IVector x0,Double epsilon,boolean zlatniRez){
		IVector gradient=f.gradient(x0).scalarMultiply(-1);
		double norma=gradient.norm();
		int b=0;
		while (norma>epsilon){
			//if(b>100) break;
			b++;
			System.out.println("Trenutna tocka: "+x0);
			System.out.println("Gradient u toj tocki: "+gradient+" norma= "+norma);
			
			
			
			if(zlatniRez){
				gradient.normalize();
				System.out.println("Normalizirani gradijent: "+gradient+"norma= "+gradient.norm());
				//jednodim fja koja predstavlja pravac s poč točkom x0 i vektorom smjera norm gradijenta
				//Ona se predaje algoritmu zlatnog reza koji nalazi optimalni lambda
				//koji definira novu točku
				Funkcija pravac=new Funkcija() {
					
					@Override
					public Double compute2(IVector v) {
						double lambda=v.get(0);
					
						return f.compute(x0.nAdd(gradient.nScalarMultiply(lambda)));
					
					}

					@Override
					public IMatrix hesseova2(IVector v) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public IVector gradient2(IVector t) {
						// TODO Auto-generated method stub
						return null;
					}
				};
				//minimizira po lambdi
				Interval i=ZlatniRez.zlatniRez(5, epsilon, pravac);
				System.out.println("Interval: "+i);
				double lam=(i.getE()-i.getS())*0.5+i.getS();//dobivena lambda
				System.out.println("Lambda: "+lam);
				
				x0.add(gradient.nScalarMultiply(lam));
			
			}else{
				x0.add(gradient);
			}
			
			IVector pom=f.gradient(x0).scalarMultiply(-1);
			for(int j=0;j<gradient.getDimension();j++)gradient.set(j, pom.get(j));
			//gradient=f.gradient(x0).scalarMultiply(-1); ne smijem ga izravno mjenjati, mora biti final
			norma=gradient.norm();
		
			if(Double.isInfinite(norma)){
				System.err.println("Norma vektora izdivergirala...");
				break;
			}
			
		}
		System.out.println("Nadjena tocka je "+x0);
	}

}
