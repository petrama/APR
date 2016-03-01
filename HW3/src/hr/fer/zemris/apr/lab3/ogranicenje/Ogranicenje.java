package hr.fer.zemris.apr.lab3.ogranicenje;

import hr.fer.zemris.apr.lab2.funkcije.Funkcija;
import hr.fer.zemris.linearna.IVector;

public abstract class Ogranicenje {
	private Funkcija f;
	

	public Ogranicenje(Funkcija f) {
		this.f = f;
	}

	public Funkcija getF() {
		return f;
	}
	

	public abstract boolean zadovoljava(IVector v);
	public abstract boolean isJdzba();
}
