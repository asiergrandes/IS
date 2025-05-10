package packagemodelo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import modelo.Tamagotchi;

public class Tablero extends Observable{

	private static Tablero miTablero = new Tablero();
	private int[][] matrizCasillas = new int[8][12]; 
	private int filaTama;
	private int colTama;
	private int filaBollo;
	private int colBollo;
	private int puntuacion = Tamagotchi.getTamagotchi().getScore();
	private Timer timer;
	private int tiempo;
	
	private Tablero() {
		tiempo=0;
	}
	
	
	public static Tablero getTablero() {
		return miTablero;
	}
	
	public void crearCasillas() {
		int casilla = 0;
		for(int i=0; i<8;i++) {
			for(int j=0; j<12;j++) {
				casilla++;
				System.out.println(Integer.toString(i)+":"+Integer.toString(j));
				Random random = new Random();
				int dureza = random.nextInt(3)+1;
				Object[] objectArray = new Object[] {"A�adirCasilla",i, j, dureza};
				matrizCasillas[i][j] = dureza;
				setChanged();
				notifyObservers(objectArray);
					}
				}
		}
	
	public void crearBollo() {
		Random random = new Random();
		filaBollo = random.nextInt(7) + 1;  
		colBollo = random.nextInt(11) + 1;
		while (TamaMini.getTamaMini().getFila()==filaBollo && TamaMini.getTamaMini().getColumna()==colBollo) {
			filaBollo = random.nextInt(7) + 1;  
			colBollo = random.nextInt(11) + 1;
		}
		int dureza = matrizCasillas[filaBollo][colBollo];
		Object[] objectArray = new Object[] {"A�adirBollo",filaBollo,colBollo,dureza};
		System.out.println(filaBollo+":"+colBollo);
		setChanged();
		notifyObservers(objectArray);
	}
	
	public void gestionarCasillas(String pCasilla) {
		
		if (pCasilla.equals("ActCoordenadasTama")) {
			Object[] objectArray = new Object[] {"ActTama", filaTama, colTama, 0, 0};
			matrizCasillas[filaTama][colTama] = 0;
			int fila = TamaMini.getTamaMini().getFila();
			int col = TamaMini.getTamaMini().getColumna();
			
			if(esBollo(fila,col)) {
				Tamagotchi.getTamagotchi().sumarPuntuacion(20);
				String mensaje = "HAS GANADO";
				pararCont();
				setChanged();
				notifyObservers(mensaje);
				Tamagotchi.getTamagotchi().iniciarCont();
			}
			
			else if (matrizCasillas[fila][col]==0) {
				filaTama = fila;
				colTama = col;
				matrizCasillas[filaTama][colTama] = -1;
				objectArray[3] = filaTama;
				objectArray[4] = colTama;	
			}
			else {
				objectArray[0] = "NoPintarNuevo";
			}
			
			setChanged();
			notifyObservers(objectArray);
			
		}
		else {
			String[] coor = pCasilla.split(":");
			int fila = Integer.parseInt(coor[0]);
			int col = Integer.parseInt(coor[1]);
			
			Object[] objectArray = new Object[4];
			
			
			if (matrizCasillas[fila][col] == 3) {
				objectArray[0]="Cambiar";
				matrizCasillas[fila][col]  = 2;
				objectArray[1] = fila;
				objectArray[2] = col;
				objectArray[3] = 2;
			}
			
			else if (matrizCasillas[fila][col]  == 2) {
				objectArray[0]="Cambiar";
				matrizCasillas[fila][col]  = 1;
				objectArray[1] = fila;
				objectArray[2] = col;
				objectArray[3] = 1;
			}
			
			else if ((matrizCasillas[fila][col]  == 1) && esTama(fila, col)) {
				objectArray[0]="Cambiar";
				matrizCasillas[fila][col]  = -1;
				objectArray[1] = fila;
				objectArray[2] = col;
				objectArray[3] = -1;
				filaTama = fila;
				colTama = col;
			}
			else if ((matrizCasillas[fila][col]  == 1) && esBollo(fila, col)) {
				objectArray[0] ="A�adirBollo";
				matrizCasillas[fila][col]  = -1;
				objectArray[1] = fila;
				objectArray[2] = col;
				objectArray[3] = -1;
			}
			else if ((matrizCasillas[fila][col]  == -1)) {
				objectArray[0]="Cambiar";
				objectArray[1] = 0;
				objectArray[2] = 0;
				objectArray[3] = 0;
			}
			
			else {
				objectArray[0]="Cambiar";
				matrizCasillas[fila][col]  = 0;
				objectArray[1] = fila;
				objectArray[2] = col;
				objectArray[3] = 0;
			}
			setChanged();
			notifyObservers(objectArray);
		}
		
		
	}
	
	public void gestionarBotones(String pBoton) {
		
		String mensaje = null;
		
		if (pBoton.equals("Exit"))
		{
			mensaje="Exit";
			System.out.println("Exit manin");
			pararCont();
			Tamagotchi.getTamagotchi().iniciarCont();
		}
		
		setChanged();
		notifyObservers(mensaje);

	}
	
	private boolean esTama(int pFila, int pCol) {
		return (TamaMini.getTamaMini().getFila()==pFila) && (TamaMini.getTamaMini().getColumna()==pCol);
	}
	private boolean esBollo(int pFila, int pCol) {
		return (filaBollo==pFila) && (colBollo==pCol);
	}
	
	public int getDurezaCasilla(int fila, int columna) {
	    return matrizCasillas[fila][columna];
	}
	
	public void iniciarCont() 
	{
		//TODO (timer de 20s)
		tiempo=0;
		TimerTask crono = new TimerTask()
		{
			
			@Override
			public void run() {
				//metodo
				cuentaAtras();
			}

			private void cuentaAtras() {
				tiempo++;
				if (tiempo==20)
				{
					pararCont();
					//hay q poner q envie msj
					String mensaje = "Tiempo";
					Tamagotchi.getTamagotchi().sumarPuntuacion(-20);
					setChanged();
					notifyObservers(mensaje);
					Tamagotchi.getTamagotchi().iniciarCont();
				}
			}
		};
				
		timer = new Timer();
		timer.scheduleAtFixedRate(crono, 0, 1000);
	}
	
	public void pararCont() {
		timer.cancel();
	}
}