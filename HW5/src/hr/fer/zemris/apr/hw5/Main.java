package hr.fer.zemris.apr.hw5;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Matrix;


public class Main {
public static void main(String[] args) throws IOException {
	List<String> linije=Files.readAllLines(new File("parametri.txt").toPath());
	//IMatrix a=Matrix.parseSimple("0 1|-200 -102");
	IMatrix a=Matrix.loadFromFile("A2.txt");
	IMatrix b=Matrix.loadFromFile("B1.txt");
	IMatrix x0=Matrix.loadFromFile("x01.txt");
	double tmax=Double.parseDouble(linije.get(1));
	double period=Double.parseDouble(linije.get(0));
	Postupak rk4=new RungeKutta4(a, b,period);
	Postupak trapez=new Trapez(a, b, period);
	List<IVector> listaRk4=Arrays.stream(rk4.getKNext(period, x0, tmax)).map(s->s.toVector(true)).collect(Collectors.toList());
	List<IVector> listaTrapez=Arrays.stream(trapez.getKNext(period, x0, tmax)).map(s->s.toVector(true)).collect(Collectors.toList());
	PrintStream psTrapez=new PrintStream(new File("trapez.data"));
	PrintStream psRk4=new PrintStream(new File("rk4.data"));
	ispisiRezultate(listaTrapez, psTrapez);
	ispisiRezultate(listaRk4, psRk4);
	psRk4.close();
	psTrapez.close();
}

private static void ispisiRezultate(List<IVector> lista, PrintStream out) throws IOException {
	for (IVector iVector : lista) {
		out.write((iVector.toString()+"\n").getBytes());
	}
}

	
}

