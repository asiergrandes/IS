package vista;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class Jugador extends Observable{
	private int score;
	private String nombre;
	
	public Jugador(int pScore, String pNombre) {
		this.score = pScore;
		this.nombre = pNombre;
		
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	@SuppressWarnings("unused")
	private void guardarNombre() {
		Jugador j = new Jugador(this.score,this.nombre);
		setChanged();
		notifyObservers(j);
	}
	
}