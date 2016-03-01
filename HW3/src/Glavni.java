import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.apr.lab2.funkcije.Banana;
import hr.fer.zemris.apr.lab2.funkcije.Druga;
import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.apr.lab3.box.Box;
import hr.fer.zemris.apr.lab3.funkcije.Cetvrta;
import hr.fer.zemris.apr.lab3.funkcije.Treca;
import hr.fer.zemris.apr.lab3.ogranicenje.Ogranicenje;
import hr.fer.zemris.apr.lab3.spust.GradijentniSpust;
import hr.fer.zemris.apr.lab3.spust.NewtonRaphson;
import hr.fer.zemris.apr.lab3.tans.Transformacija;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Vector;

public class Glavni {
	public static void main(String[] args) {
		 //prvi();
		 //drugi();
		treci();
		  //ceta();
		
	}

	public static void prvi() {
		Banana b = new Banana();
		Treca tt = new Treca();
		Druga d = new Druga();

		Funkcija t = b;

		
		//b
		// GradijentniSpust.spust(b, new Vector(-1.9,2), 1E-6,true);
		 NewtonRaphson.alg(b, new Vector(-1.9,2), 1E-6,true);
		
		//d
		// GradijentniSpust.spust(t, new Vector(-0.1, 0.3), 1E-6, true);
		//NewtonRaphson.alg(t, new Vector(-0.1, 0.3), 1E-6, true);
		
		//tt
//		 GradijentniSpust.spust(t, new Vector(0,0), 1E-6,false);
//		 GradijentniSpust.spust(t, new Vector(0,0), 1E-6,true);
	

	
		

		System.out.println("Broj poziva funkcije: " + t.getBrojPoziva());
		System.out.println("Broj poziva gradijenta: "
				+ t.getBrojPozivaGradijenta());
		System.out.println("Broj poziva hesse matrice: "
				+ t.getBrojPozivaHesse());
	}

	public static void drugi() {
		Banana b = new Banana();
		Druga d = new Druga();

		Funkcija f1 = new Funkcija() {

			@Override
			public Double compute2(IVector v) {
				return v.get(1) - v.get(0);
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

		Ogranicenje prvo = new Ogranicenje(f1) {

			@Override
			public boolean zadovoljava(IVector v) {
				return (getF().compute(v) >= 0);
			}

			@Override
			public boolean isJdzba() {
				return false;
			};
		};
		Funkcija f2 = new Funkcija() {

			@Override
			public Double compute2(IVector v) {
				return 2 - v.get(0);
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
		Ogranicenje drugo = new Ogranicenje(f2) {

			@Override
			public boolean zadovoljava(IVector v) {
				return (getF().compute(v) >= 0);
			}

			@Override
			public boolean isJdzba() {
				return false;
			};
		};
		List<Ogranicenje> o = new ArrayList<Ogranicenje>();
		o.add(prvo);
		o.add(drugo);
		Box.box(b, new Vector(-1.9, 2), new Vector(-100, -100), new Vector(100,
				100), o);
//		 Box.box(d,new Vector(-0.1,0.3), new Vector(-100,-100), new
//		 Vector(100,100),o );
	}

	public static void treci() {
		Banana b = new Banana();
		Druga d = new Druga();

		Funkcija f1 = new Funkcija() {

			@Override
			public Double compute2(IVector v) {
				return v.get(1) - v.get(0);
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

		Ogranicenje prvo = new Ogranicenje(f1) {

			@Override
			public boolean zadovoljava(IVector v) {
				return (getF().compute(v) >= 0);
			}

			@Override
			public boolean isJdzba() {
				return false;
			};
		};
		Funkcija f2 = new Funkcija() {

			@Override
			public Double compute2(IVector v) {
				return 2 - v.get(0);
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
		Ogranicenje drugo = new Ogranicenje(f2) {

			@Override
			public boolean zadovoljava(IVector v) {
				return (getF().compute(v) >= 0);
			}

			@Override
			public boolean isJdzba() {
				return false;
			};
		};
		List<Ogranicenje> o = new ArrayList<Ogranicenje>();
		o.add(prvo);
		o.add(drugo);
		Transformacija t = new Transformacija();
//		t.transformiraj(b,  o,
//				new Vector(1.5,2));
		t.transformiraj(d,  o,
				new Vector(0.1,0.3));
	}
	
	private static void ceta() {
		Cetvrta c=new Cetvrta();
		Funkcija f1=new Funkcija() {
			
			@Override
			public Double compute2(IVector v) {
				return 3-v.get(0)-v.get(1);
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
		Ogranicenje o1=new Ogranicenje(f1) {
			
			@Override
			public boolean zadovoljava(IVector v) {
				return (getF().compute(v) >= 0);
			}
			
			@Override
			public boolean isJdzba() {
				return false;
			}
		};
		Funkcija f2=new Funkcija() {
			
			@Override
			public Double compute2(IVector v) {
				return 3+1.5*v.get(0)-v.get(1);
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
		Ogranicenje o2=new Ogranicenje(f2) {
			
			@Override
			public boolean zadovoljava(IVector v) {
				return (getF().compute(v) >= 0);
			}
			
			@Override
			public boolean isJdzba() {
				return false;
			}
		};
		Funkcija f3=new Funkcija() {
			
			@Override
			public Double compute2(IVector v) {
				return v.get(1)-1;
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
		Ogranicenje o3=new Ogranicenje(f3) {
			
			@Override
			public boolean zadovoljava(IVector v) {
				return (getF().compute(v) >= 0);
			}
			
			@Override
			public boolean isJdzba() {
				return true;
			}
		};
		List<Ogranicenje> o = new ArrayList<Ogranicenje>();
		o.add(o1);
		o.add(o2);
		o.add(o3);
		Transformacija t = new Transformacija();
		t.transformiraj(c,  o,
				new Vector(0,0));
	}
}
