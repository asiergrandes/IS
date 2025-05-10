package packagemodelo;

import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

public class TamaMini extends Observable{
	
	private int fila;
	private int columna;
	private boolean vivo=true;
	private static TamaMini miTamaMini = new TamaMini();
	
	private TamaMini() {
		obtCoordenadasRandom();
	}
	
	public static TamaMini getTamaMini() {
		return miTamaMini;
	}
	
	private void obtCoordenadasRandom() {
		Random random = new Random();
		fila = random.nextInt(7) + 1;  
		columna = random.nextInt(11) + 1;
		int[] coordenadas = new int[] {fila, columna};
	}
	
	public int getFila() {
		return fila;
	}
	
	public int getColumna() {
		return columna;
	}
	
	public void gestionarTeclas(int pKeyCode) {
		if (pKeyCode == 37) {
			if (columna > 0) {
				columna = columna - 1;
			}
		}
		else if (pKeyCode == 38) {
			if (fila > 0) {
				fila = fila - 1;
			}
		}
		else if (pKeyCode == 39) {
			if (columna < 11) {
				columna = columna + 1;
			}
		}
		else if (pKeyCode == 40) {
			if (fila < 7) {
				fila = fila + 1;
			}
		}
		/*int[] coordenadas = new int[] {fila, columna};
		setChanged();
		notifyObservers(coordenadas);*/
	}
}